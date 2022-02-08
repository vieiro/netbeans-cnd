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
package org.netbeans.modules.cnd.lsp.client.capabilities.workspace;

import java.util.Arrays;
import org.eclipse.lsp4j.CodeLensWorkspaceCapabilities;
import org.eclipse.lsp4j.DidChangeConfigurationCapabilities;
import org.eclipse.lsp4j.DidChangeWatchedFilesCapabilities;
import org.eclipse.lsp4j.ExecuteCommandCapabilities;
import org.eclipse.lsp4j.FileOperationsWorkspaceCapabilities;
import org.eclipse.lsp4j.SemanticTokensWorkspaceCapabilities;
import org.eclipse.lsp4j.SymbolCapabilities;
import org.eclipse.lsp4j.SymbolKind;
import org.eclipse.lsp4j.SymbolKindCapabilities;
import org.eclipse.lsp4j.SymbolTag;
import org.eclipse.lsp4j.SymbolTagSupportCapabilities;
import org.eclipse.lsp4j.WorkspaceClientCapabilities;

/**
 * @see
 * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#clientCapabilities">WorkspaceClientCapabilities</a>
 * @author antonio
 */
public class NBWorkspaceClientCapabilities extends WorkspaceClientCapabilities {

    public NBWorkspaceClientCapabilities() {
        // If we support the "workspace/applyEdit" applying batch edits
        setApplyEdit(true);
        setCodeLens(createCodeLensWorkspaceCapabilities());
        // If we support the "workspace/configuration" requests
        setConfiguration(true);
        setDidChangeConfiguration(createDidChangeConfigurationCapabilities());
        setDidChangeWatchedFiles(createDidChangedFilesCapabilities());
        setExecuteCommand(createExecuteCommandCapabilities());
        setFileOperations(createFileOperationsWorkspaceCapabilities());
        setSemanticTokens(createSemanticTokensWorkspaceCapabilities());
        setSymbol(createSymbolCapabilities());
        setWorkspaceEdit(new NBWorkspaceEditCapabilities());
        // If we support "workspace folders"
        // This is similar to projects, probably.
        // See https://github.com/emacs-lsp/lsp-java/issues/201
        setWorkspaceFolders(true);
    }

    /**
     * If the LSP Server can send us a refresh request. Quoting: "It should be
     * used with absolute care and is useful for situation where a server for
     * example detect a project wide change that requires such a calculation."
     *
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#codeLensWorkspaceClientCapabilities">CodeLensWorkspaceCapabilities</a>
     * @return CodeLensWorkspaceCapabilities
     */
    private CodeLensWorkspaceCapabilities createCodeLensWorkspaceCapabilities() {
        boolean refreshSupport = false;
        CodeLensWorkspaceCapabilities c = new CodeLensWorkspaceCapabilities(refreshSupport);
        return c;
    }

    /**
     * Notification from client to server indicating that workspace
     * configuration has changed.
     *
     * @return
     */
    private DidChangeConfigurationCapabilities createDidChangeConfigurationCapabilities() {
        DidChangeConfigurationCapabilities c = new DidChangeConfigurationCapabilities();
        c.setDynamicRegistration(true);
        return c;
    }

    /**
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#didChangeWatchedFilesClientCapabilities">DidChangeWatchedFilesCapabilities</a>
     * @return
     */
    private DidChangeWatchedFilesCapabilities createDidChangedFilesCapabilities() {
        DidChangeWatchedFilesCapabilities c = new DidChangeWatchedFilesCapabilities();
        c.setDynamicRegistration(true);
        return c;
    }

    /**
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#executeCommandClientCapabilities">ExecuteCommandCapabilities</a>
     * @return
     */
    private ExecuteCommandCapabilities createExecuteCommandCapabilities() {
        ExecuteCommandCapabilities c = new ExecuteCommandCapabilities();
        c.setDynamicRegistration(true);
        return c;
    }

    /**
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#clientCapabilities">ClientCapabilities/workspace/fileOperations</a>
     * @return
     */
    private FileOperationsWorkspaceCapabilities createFileOperationsWorkspaceCapabilities() {
        FileOperationsWorkspaceCapabilities c = new FileOperationsWorkspaceCapabilities();
        // The client has support for sending didCreateFile/willCreateFile and related notifications.
        c.setDidCreate(true);
        c.setWillCreate(false);
        c.setDidDelete(true);
        c.setWillDelete(false);
        c.setDidRename(true);
        c.setDidCreate(false);
        return c;
    }

    /**
     * If we support a refresh sent from the server. Quoting: "Note that this
     * event is global and will force the client to refresh all semantic tokens
     * currently shown. It should be used with absolute care and is useful for
     * situation where a server for example detect a project wide change that
     * requires such a calculation."
     *
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#semanticTokensWorkspaceClientCapabilities">SemanticTokensWorkspaceCapabilities</a>
     * @return
     */
    private SemanticTokensWorkspaceCapabilities createSemanticTokensWorkspaceCapabilities() {
        SemanticTokensWorkspaceCapabilities c = new SemanticTokensWorkspaceCapabilities();
        c.setRefreshSupport(false);
        return c;
    }

    /**
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#workspace_symbol">SymbolCapabilities</a>
     * @return
     */
    private SymbolCapabilities createSymbolCapabilities() {
        SymbolCapabilities c = new SymbolCapabilities();
        c.setDynamicRegistration(true);
        c.setSymbolKind(createSymbolKindCapabilities());
        c.setTagSupport(createSymbolTagSupportCapabilities());
        return c;
    }

    /**
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#workspace_symbol">symbolKind?</a>
     * @return
     */
    private SymbolKindCapabilities createSymbolKindCapabilities() {
        SymbolKindCapabilities c = new SymbolKindCapabilities();
        c.setValueSet(Arrays.asList(
                SymbolKind.values()
        ));
        return c;
    }

    /**
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#workspace_symbol">tagSupport?</a>
     * @return
     */
    private SymbolTagSupportCapabilities createSymbolTagSupportCapabilities() {
        SymbolTagSupportCapabilities c = new SymbolTagSupportCapabilities();
        c.setValueSet(Arrays.asList(
                SymbolTag.values()
        ));
        return c;
    }

}
