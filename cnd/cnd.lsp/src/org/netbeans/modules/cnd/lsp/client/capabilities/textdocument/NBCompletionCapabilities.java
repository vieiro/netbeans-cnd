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

import java.util.Arrays;
import org.eclipse.lsp4j.CompletionCapabilities;
import org.eclipse.lsp4j.CompletionItemCapabilities;
import org.eclipse.lsp4j.CompletionItemInsertTextModeSupportCapabilities;
import org.eclipse.lsp4j.CompletionItemKind;
import org.eclipse.lsp4j.CompletionItemKindCapabilities;
import org.eclipse.lsp4j.InsertTextMode;
import org.eclipse.lsp4j.MarkupKind;

/**
 * @see
 * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-16/#completionClientCapabilities">Completion
 * Client Capabilities</a>
 * @author antonio
 */
public final class NBCompletionCapabilities extends CompletionCapabilities {

    public NBCompletionCapabilities() {
        setCompletionItem(createCompletionItemCapabilities());
        setCompletionItemKind(createCompletionItemKindCapabilities());
        // The client supports to send additional context information for a `textDocument/completion` request.
        setContextSupport(true);
        // Whether completion supports dynamic registration.
        // No idea what "dynamic registration" means. Let's say yes.
        setDynamicRegistration(true);
    }

    /**
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-16/#completionClientCapabilities>Copmletion
     * Client Capabilities</a>
     *
     * @return A CompletionItemKindCapabilities object.
     */
    private CompletionItemCapabilities createCompletionItemCapabilities() {
        CompletionItemCapabilities c = new CompletionItemCapabilities();
        //  Client supports commit characters on a completion item.
        // See https://giters.com/microsoft/language-server-protocol/issues/1291 for details
        c.setCommitCharactersSupport(true);
        // Client supports the deprecated property on a completion item.
        c.setDeprecatedSupport(true);
        // Client supports the following content formats for the documentation property. The order describes the preferred format of the client.
        c.setDocumentationFormat(Arrays.asList(MarkupKind.PLAINTEXT));
        // Client supports insert replace edit to control different behavior if
        // a completion item is inserted in the text or should replace text.
        c.setInsertReplaceSupport(true);
        c.setInsertTextModeSupport(createCompletionItemInsertTextModeSupportCapabilities());
        return c;
    }

    /**
     * The client supports the `insertTextMode` property on a completion item to
     * override the whitespace handling mode as defined by the client.
     *
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#insertTextMode">Insert
     * Text mode</a>
     *
     * @return A CompletionItemInsertTextModeSupportCapabilities object.
     */
    private CompletionItemInsertTextModeSupportCapabilities createCompletionItemInsertTextModeSupportCapabilities() {
        CompletionItemInsertTextModeSupportCapabilities c = new CompletionItemInsertTextModeSupportCapabilities();
        c.setValueSet(Arrays.asList(InsertTextMode.AsIs));
        return c;
    }

    /**
     * @link https://microsoft.github.io/language-server-protocol/specifications/specification-3-16/#completionClientCapabilities
     *
     * @return A CompletionItemKindCapabilities object.
     */
    private CompletionItemKindCapabilities createCompletionItemKindCapabilities() {
        CompletionItemKindCapabilities c = new CompletionItemKindCapabilities();
        c.setValueSet(Arrays.asList(CompletionItemKind.values()));
        return c;
    }

}
