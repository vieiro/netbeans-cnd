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

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.TextDocumentContentChangeEvent;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.TextDocumentSyncOptions;
import org.eclipse.lsp4j.VersionedTextDocumentIdentifier;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.netbeans.api.editor.document.LineDocument;
import org.netbeans.editor.BaseDocumentEvent;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.modules.cnd.lsp.client.impl.AbstractLanguageClient;
import org.netbeans.modules.cnd.lsp.client.utils.Utilities;

/**
 * Responsible for sending changes in documents to the LSP server.
 *
 * @author antonio
 */
public class TextSyncDocumentListener implements DocumentListener {

    private static final Logger LOG = Logger.getLogger(TextSyncDocumentListener.class.getName());

    private final JTextComponent component;
    private final Document document;
    private final AbstractLanguageClient client;
    private final TextDocumentService textDocumentService;
    private TextDocumentSyncKind syncKind;

    public TextSyncDocumentListener(AbstractLanguageClient client,
            JTextComponent component) {
        this.component = component;
        this.document = component.getDocument();
        this.client = client;
        this.textDocumentService = client.getLanguageServer().getTextDocumentService();
        ServerCapabilities serverCapabilities = client.getServerCapabilities();
        Either<TextDocumentSyncKind, TextDocumentSyncOptions> serverSyncCapabilities = serverCapabilities.getTextDocumentSync();
        syncKind = TextDocumentSyncKind.None;
        if (serverSyncCapabilities != null) {
            if (serverSyncCapabilities.isLeft()) {
                syncKind = serverSyncCapabilities.getLeft();
            } else {
                TextDocumentSyncKind change = serverSyncCapabilities.getRight().getChange();
                if (change != null) {
                    syncKind = change;
                }
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        switch (syncKind) {
            case None:
                return;
            case Full:
                fireFullTextSyncEvent();
                return;
            default:
                break;
        }
        try {
            Document document = e.getDocument();
            int offset = e.getOffset();
            int length = e.getLength();
            String insertedText = e.getDocument().getText(offset, length);
            String removedText = "";
            fireTextDocumentContentChange(offset, insertedText, removedText);
        } catch (BadLocationException ex) {
            LOG.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        switch (syncKind) {
            case None:
                return;
            case Full:
                fireFullTextSyncEvent();
                return;
            default:
                break;
        }
        try {
            Document document = e.getDocument();
            int offset = e.getOffset();
            int length = e.getLength();
            String insertedText = "";
            BaseDocumentEvent bde = (BaseDocumentEvent) e;
            String removedText = bde.getText();
            fireTextDocumentContentChange(offset, insertedText, removedText);
        } catch (BadLocationException ex) {
            LOG.log(Level.WARNING, ex.getMessage(), ex);
        }

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // Empty
    }

    /**
     * Sends information about inserted/removed text back to the LSP server.
     *
     * @param offset The offset where the text has been inserted/deleted
     * @param insertedText The inserted text.
     * @param deletedText The removed text.
     * @throws BadLocationException If a bad location is generated.
     */
    private void fireTextDocumentContentChange(int offset,
            String insertedText, String deletedText) throws BadLocationException {

        // NOTE: We're on the EDT, we should be fast here...
        assert SwingUtilities.isEventDispatchThread();

        LineDocument lineDocument = (LineDocument) document;
        Position startPosition = Utilities.createPosition(lineDocument, offset);
        Position endPosition = Utilities.computeEndPositionForRemovedText(startPosition, deletedText);
        Range deletedRange = new Range(startPosition, endPosition);
        TextDocumentContentChangeEvent textDocumentChangeEvent = new TextDocumentContentChangeEvent(deletedRange, deletedText.length(), insertedText);
        fireTextDocumentContentChangedEvent(textDocumentChangeEvent);

    }

    private void fireFullTextSyncEvent() {
        final StringBuilder sb = new StringBuilder();
        document.render(() -> {
            try {
                sb.append(document.getText(0, document.getLength() - 1));
            } catch (BadLocationException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        });
        TextDocumentContentChangeEvent textDocumentChangeEvent = new TextDocumentContentChangeEvent(sb.toString());
        fireTextDocumentContentChangedEvent(textDocumentChangeEvent);
    }

    private void fireTextDocumentContentChangedEvent(TextDocumentContentChangeEvent textDocumentChangeEvent) {
        long version = DocumentUtilities.getDocumentVersion(document);
        String uri = Utilities.getFileObject(component).toURI().toString();
        VersionedTextDocumentIdentifier versionedTextDocumentIdentifier
                = new VersionedTextDocumentIdentifier(uri, (int) version);
        DidChangeTextDocumentParams didChangeTextDocumentParams
                = new DidChangeTextDocumentParams(versionedTextDocumentIdentifier,
                        Collections.singletonList(textDocumentChangeEvent));

        client.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                textDocumentService.didChange(didChangeTextDocumentParams);
                return null;
            }
        });

    }

}
