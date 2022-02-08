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
package org.netbeans.modules.cnd.lsp.client.capabilities.textdocument;

import java.util.Collections;
import org.eclipse.lsp4j.CodeActionCapabilities;
import org.eclipse.lsp4j.CodeActionKindCapabilities;
import org.eclipse.lsp4j.CodeActionLiteralSupportCapabilities;
import org.eclipse.lsp4j.CodeActionResolveSupportCapabilities;

/**
 * @see <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#codeActionClientCapabilities">CodeActionClientCapabilities</a>
 *
 * @author antonio
 */
public class NBCodeActionCapabilities extends CodeActionCapabilities {

    public NBCodeActionCapabilities() {
        setCodeActionLiteralSupport(createCodeActionLiteralSupport());
        // 'true' when the code action supports the 'data' property
        // which is preserved between 'textDocument/codeAction' and 
        // 'codeAction/resolve' calls
        setDataSupport(true);
        // If we support the 'disabled' action property.
        setDisabledSupport(true);
        setDynamicRegistration(true);
        setHonorsChangeAnnotations(false);
        // If we support the 'isPreferred' action property
        setIsPreferredSupport(true);
        setResolveSupport(createCodeActionResolveSupport());
    }

    /**
     * We support code action literals as a valid response of the
     * `textDocument/codeAction`.
     * @see <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-16/#textDocument_codeAction">textDocument/codeAction</a>
     *
     * @return
     */
    private CodeActionLiteralSupportCapabilities createCodeActionLiteralSupport() {
        CodeActionLiteralSupportCapabilities c = new CodeActionLiteralSupportCapabilities();
        c.setCodeActionKind(createCodeActionKindCapabilities());
        return c;
    }

    private CodeActionKindCapabilities createCodeActionKindCapabilities() {
        CodeActionKindCapabilities c = new CodeActionKindCapabilities();
        // TODO: Review what this means.
        c.setValueSet(Collections.emptyList());
        return c;
    }

    /**
     * Whether we support resolving additional
     * code actions through a separate 'codeAction/resolve' request.
     * @see <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#codeActionClientCapabilities"</a>
     * @return 
     */
    private CodeActionResolveSupportCapabilities createCodeActionResolveSupport() {
        CodeActionResolveSupportCapabilities c = new CodeActionResolveSupportCapabilities();
        c.setProperties(Collections.emptyList());
        return c;
    }

}
