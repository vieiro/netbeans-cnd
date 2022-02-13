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
package org.netbeans.modules.cnd.lsp.client.capabilities;

import java.util.EnumSet;
import org.eclipse.lsp4j.ClientCapabilities;
import org.netbeans.modules.cnd.lsp.client.api.LSPFeatures;
import org.netbeans.modules.cnd.lsp.client.capabilities.textdocument.NBTextDocumentClientCapabilities;
import org.netbeans.modules.cnd.lsp.client.capabilities.workspace.NBWorkspaceClientCapabilities;

/**
 * NetBeans specific client capabilities for the LSP protocol. Version 3.16.
 *
 * @see
 * <a href="https://github.com/microsoft/language-server-protocol/blob/gh-pages/_specifications/specification-3-16.md">LSP
 * Specification 3.16 in markdown format</a>
 * @@see
 * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#clientCapabilities">LSP
 * Client Capabilities in 3.16</a>
 * @see
 * <a href="https://docs.microsoft.com/en-us/dotnet/api/microsoft.visualstudio.languageserver.protocol?view=visualstudiosdk-2022">Microsoft.VisualStudio.LanguageServer.Protocol
 * Namespace</a>
 *
 * @author antonio
 */
public final class NBClientCapabilities extends ClientCapabilities {

    private final EnumSet<LSPFeatures> requiredFeatures;

    /**
     * Builds a NetBeans ClientCapabilities object with the desired client
     * capabilities.
     *
     * @param requiredFeatures The required capabilities in the client, note
     * that LSP servers may or may not have these capabilities.
     */
    public NBClientCapabilities(EnumSet<LSPFeatures> requiredFeatures) {
        this.requiredFeatures = requiredFeatures;
        setTextDocument(new NBTextDocumentClientCapabilities());
        setWorkspace(new NBWorkspaceClientCapabilities());
    }

}
