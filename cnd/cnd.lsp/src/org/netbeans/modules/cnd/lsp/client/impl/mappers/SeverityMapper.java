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
package org.netbeans.modules.cnd.lsp.client.impl.mappers;

import org.eclipse.lsp4j.DiagnosticSeverity;
import org.netbeans.spi.editor.hints.Severity;

/**
 * Mapper from LSP severities to NetBeans severities. NOTE: This must be a
 * thread-safe class.
 *
 * @author antvie
 */
public final class SeverityMapper {

    private static final SeverityMapper INSTANCE = new SeverityMapper();

    public static SeverityMapper getInstance() {
        return INSTANCE;
    }

    private SeverityMapper() {
    }

    public Severity toHintsSeverity(DiagnosticSeverity lspSeverity) {
        switch (lspSeverity) {
            case Error:
                return Severity.ERROR;
            case Hint:
                return Severity.HINT;
            case Information:
                return Severity.HINT;
            case Warning:
            default:
                return Severity.WARNING;
        }
    }

}
