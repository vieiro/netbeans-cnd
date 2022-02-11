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

import java.util.Collections;
import org.eclipse.lsp4j.Diagnostic;
import org.netbeans.api.editor.document.LineDocument;
import org.netbeans.modules.cnd.lsp.client.impl.mappers.SeverityMapper;
import org.netbeans.modules.cnd.lsp.client.utils.Utilities;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.Severity;

/**
 * DiagnosticToErrorDescriptionMapper maps LSP Diagnostic to NetBeans
 * ErrorDescription.
 *
 * @author antvie
 */
public final class DiagnosticToErrorDescriptionMapper {

    private final LineDocument lineDocument;

    public DiagnosticToErrorDescriptionMapper(LineDocument lineDocument) {
        this.lineDocument = lineDocument;
    }

    public ErrorDescription toErrorDescription(Diagnostic diagnostic) {
        SeverityMapper severityMapper = SeverityMapper.getInstance();
        Severity nbSeverity = severityMapper.toHintsSeverity(diagnostic.getSeverity());
        String description = diagnostic.getMessage();
        int startOffset = Utilities.offset(lineDocument, diagnostic.getRange().getStart());
        int endOffset = Utilities.offset(lineDocument, diagnostic.getRange().getEnd());

        ErrorDescriptionFactory.lazyListForFixes(Collections.emptyList());

        return null;
    }

}
