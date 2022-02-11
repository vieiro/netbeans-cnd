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

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import javax.swing.text.StyledDocument;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.netbeans.api.editor.document.LineDocument;
import org.netbeans.modules.cnd.lsp.client.impl.NBLanguageClient;
import org.netbeans.modules.cnd.lsp.client.utils.Utilities;
import org.netbeans.spi.editor.hints.ErrorDescription;

/**
 *
 * @author antvie
 */
public class DiagnosticsPublisherTask implements Callable<Void> {

    private final NBLanguageClient client;
    private final PublishDiagnosticsParams diagnostics;

    public DiagnosticsPublisherTask(NBLanguageClient client, PublishDiagnosticsParams diagnostics) {
        this.diagnostics = diagnostics;
        this.client = client;
    }

    @Override
    public Void call() throws Exception {
        StyledDocument document = Utilities.getDocument(diagnostics.getUri());
        if (document == null) {
            return null;
        }

        DiagnosticToErrorDescriptionMapper diagnosticMapper
                = new DiagnosticToErrorDescriptionMapper(((LineDocument) document));

        // Convert List<Diagnostic> to List<ErrorDescription> (EditorHints)
        List<ErrorDescription> errorDescriptions
                = diagnostics.getDiagnostics().stream().map(diagnosticMapper::toErrorDescription).collect(Collectors.toList());

        return null;
    }

}
