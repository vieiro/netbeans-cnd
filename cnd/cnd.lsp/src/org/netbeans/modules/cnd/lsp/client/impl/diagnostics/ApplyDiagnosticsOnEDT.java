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
package org.netbeans.modules.cnd.lsp.client.impl.diagnostics;

import org.netbeans.modules.cnd.lsp.client.impl.mappers.DiagnosticToErrorDescriptionMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledDocument;
import org.eclipse.lsp4j.CodeActionOptions;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.netbeans.api.editor.document.LineDocument;
import org.netbeans.modules.cnd.lsp.client.api.LSPFeatures;
import org.netbeans.modules.cnd.lsp.client.impl.AbstractLanguageClient;
import org.netbeans.modules.cnd.lsp.client.utils.Utilities;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.HintsController;
import org.openide.filesystems.FileObject;

/**
 * Applies a bunch of diagnostics sent by the LSP server to an editor.
 *
 * @author antvie
 */
public class ApplyDiagnosticsOnEDT implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplyDiagnosticsOnEDT.class.getName());

    private final AbstractLanguageClient client;
    private final PublishDiagnosticsParams diagnostics;
    private final ServerCapabilities serverCapabilities;
    private final boolean serverSendsCodeActions;
    private final FileObject fileObject;

    public ApplyDiagnosticsOnEDT(AbstractLanguageClient client, PublishDiagnosticsParams diagnostics) {
        // We create these objects on a LSP server-initiated thread, not in the EDT.
        assert !SwingUtilities.isEventDispatchThread();
        this.diagnostics = diagnostics;
        this.client = client;
        this.serverCapabilities = client.getServerCapabilities();
        Either<Boolean, CodeActionOptions> codeActions = serverCapabilities.getCodeActionProvider();
        this.serverSendsCodeActions = codeActions != null && (!codeActions.isLeft() || codeActions.getLeft());
        // Which file are we talking about?
        this.fileObject = Utilities.findFileObject(diagnostics.getUri());
    }

    @Override
    public void run() {
        // We apply changes in the EDT, not in a LSP-server initiated thread.
        assert SwingUtilities.isEventDispatchThread();
        if (fileObject == null) {
            // D'oh, LSP server is talking about an unknown fileobject
            LOG.log(Level.INFO, "No fileobject in diagnostics");
            return;
        }
        StyledDocument document = Utilities.getDocument(diagnostics.getUri());
        if (document == null) {
            LOG.log(Level.INFO, "No document in diagnostics for uri {0}", diagnostics.getUri());
            // D'oh, the given file is not being edited anymore :-(
            return;
        }

        DiagnosticToErrorDescriptionMapper diagnosticMapper
                = new DiagnosticToErrorDescriptionMapper(fileObject, client, 
                        ((LineDocument) document), serverSendsCodeActions);

        List<ErrorDescription> errorDescriptions = 
        diagnostics.getDiagnostics().stream().map( diagnosticMapper::toErrorDescription ).collect(Collectors.toList());

        LOG.log(Level.INFO, "Diagnostics: Applying errorDescriptions: {0}", errorDescriptions.size());
        HintsController.setErrors(fileObject, ApplyDiagnosticsOnEDT.class.getName(), errorDescriptions);
    }

}
