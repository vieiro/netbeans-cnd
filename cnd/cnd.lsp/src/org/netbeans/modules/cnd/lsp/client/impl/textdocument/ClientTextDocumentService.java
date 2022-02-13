/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.cnd.lsp.client.impl.textdocument;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.lsp4j.TextDocumentItem;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.project.Project;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.modules.cnd.lsp.client.constants.LSPLanguageIdentifiers;
import org.netbeans.modules.cnd.lsp.client.impl.AbstractLanguageClient;
import org.netbeans.modules.cnd.lsp.client.impl.mappers.LSPLanguageIdentifiersMapper;
import org.netbeans.modules.cnd.lsp.client.utils.Utilities;
import org.openide.filesystems.FileObject;

/**
 * The client-side NetBeans specific implementation of the server's
 * TextDocumentService.
 *
 * @author antonio
 */
public final class ClientTextDocumentService {

    private static final Logger LOG = Logger.getLogger(ClientTextDocumentService.class.getName());

    private final AbstractLanguageClient client;
    private final LanguageServer languageServer;
    private final TextDocumentService textDocumentService;
    private final PropertyChangeListener editorRegistryPropertyChangeListener;

    // The JTextComponents that are synchronized with the LSP server
    private HashSet<JTextComponent> ourTextComponents;
    private HashMap<JTextComponent, TextSyncDocumentListener> documentListeners;

    public ClientTextDocumentService(AbstractLanguageClient client) {
        this.client = client;
        this.languageServer = client.getLanguageServer();
        this.textDocumentService = client.getLanguageServer().getTextDocumentService();
        this.ourTextComponents = new HashSet<>();
        this.documentListeners = new HashMap<>();
        this.editorRegistryPropertyChangeListener = this::editorRegistryChanged;
        LOG.info("A ClientTextDocumentService started.");
        EditorRegistry.addPropertyChangeListener(editorRegistryPropertyChangeListener);
    }

    public void stop() {
        LOG.info("A ClientTextDocumentService stopped.");
        EditorRegistry.removePropertyChangeListener(editorRegistryPropertyChangeListener);
    }

    private void editorRegistryChanged(PropertyChangeEvent event) {
        if (SwingUtilities.isEventDispatchThread()) {
            editorRegistryChangedEDT(event);
        } else {
            SwingUtilities.invokeLater(() -> {
                editorRegistryChangedEDT(event);
            });
        }
    }

    private boolean isThisTextComponentOurs(JTextComponent component) {
        if (component == null) {
            return false;
        }
        FileObject fo = Utilities.getFileObject(component);
        if (fo == null) {
            return false;
        }
        Project project = client.isResponsibleFor(fo);
        if (project == null) {
            return false;
        }
        return true;
    }

    private void editorRegistryChangedEDT(PropertyChangeEvent event) {
        // We need to run on the EDT because EditorRegistry.componentList
        assert SwingUtilities.isEventDispatchThread();
        // Retrieve the list of opened JTextComponents under our control
        Set<JTextComponent> currentlyOpenedTextComponents
                = EditorRegistry.componentList().stream()
                        .filter(this::isThisTextComponentOurs)
                        .collect(Collectors.toSet());
        // Compute the list of closed components.
        Set<JTextComponent> closed = new HashSet<>(ourTextComponents);
        closed.removeAll(currentlyOpenedTextComponents);
        if (!closed.isEmpty()) {
            sendDidCloseDocuments(closed);
            ourTextComponents.removeAll(closed);
        }
        // Compute the list of newly opened documents.
        currentlyOpenedTextComponents.removeAll(ourTextComponents);
        if (!currentlyOpenedTextComponents.isEmpty()) {
            sendDidOpenDocuments(currentlyOpenedTextComponents);
            ourTextComponents.addAll(currentlyOpenedTextComponents);
        }
    }

    private void sendDidCloseDocuments(Set<JTextComponent> closed) {
        for (JTextComponent component : closed) {
            TextSyncDocumentListener listener = documentListeners.get(component);
            if (listener != null) {
                documentListeners.remove(component);
                component.getDocument().removeDocumentListener(listener);
            }
            FileObject fo = Utilities.getFileObject(component);
            if (fo != null) {
                URI uri = fo.toURI();
                TextDocumentIdentifier textDocumentID = new TextDocumentIdentifier(uri.toString());
                DidCloseTextDocumentParams p = new DidCloseTextDocumentParams(textDocumentID);
                LOG.log(Level.INFO, "Closing document {0}", uri);
                final Callable<Void> task = new Callable<Void>() {
                    public Void call() throws Exception {
                        textDocumentService.didClose(p);
                        return null;
                    }
                };
                client.submit(task);
            }
        }
    }

    private void sendDidOpenDocuments(Set<JTextComponent> newlyOpenedDocuments) {
        for (JTextComponent component : newlyOpenedDocuments) {
            FileObject fo = Utilities.getFileObject(component);
            if (fo != null) {
                URI uri = fo.toURI();
                LSPLanguageIdentifiers languageID = LSPLanguageIdentifiersMapper.getInstance().toLSPLanguageIdentifier(fo);
                long version = DocumentUtilities.getDocumentVersion(component.getDocument());
                TextDocumentItem textDocumentItem = new TextDocumentItem(uri.toString(),
                        languageID.id, (int) version, component.getText());
                DidOpenTextDocumentParams p = new DidOpenTextDocumentParams(textDocumentItem);
                LOG.log(Level.INFO, "Opening document {0} with language {1}", new Object[]{uri, languageID.id});
                final Callable<Void> task = new Callable<Void>() {
                    public Void call() throws Exception {
                        textDocumentService.didOpen(p);
                        return null;
                    }
                };
                client.submit(task);
            }
            TextSyncDocumentListener listener = new TextSyncDocumentListener(client, component);
            documentListeners.put(component, listener);
            component.getDocument().addDocumentListener(listener);
        }
    }

}
