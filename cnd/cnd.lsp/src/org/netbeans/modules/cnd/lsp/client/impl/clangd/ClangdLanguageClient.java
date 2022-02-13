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
package org.netbeans.modules.cnd.lsp.client.impl.clangd;

import java.util.EnumSet;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.modules.cnd.lsp.client.api.LSPFeatures;
import org.netbeans.modules.cnd.lsp.client.impl.AbstractLanguageClient;
import org.openide.filesystems.FileObject;

/**
 *
 * @author antvie
 */
public class ClangdLanguageClient extends AbstractLanguageClient {

    // TODO: Parametrize this
    private static final String CLANGD_PATH = "/usr/bin/clangd";
    private static final String[] CLANGD_COMMAND = {
        CLANGD_PATH,
        "--clang-tidy",
        "--completion-style=detailed",
        "--offset-encoding=utf-8",
        "--pch-storage=disk",
        "--log=verbose",
        "--background-index",};

    private EnumSet<LSPFeatures> features = EnumSet.of(LSPFeatures.COMPLETION);

    @Override
    public String[] getLSPServerStartCommands() {
        return CLANGD_COMMAND;
    }

    @Override
    public Project isResponsibleFor(FileObject fileObject) {
        if (fileObject == null) {
            return null;
        }
        return FileOwnerQuery.getOwner(fileObject);
    }

    @Override
    public EnumSet<LSPFeatures> getClientFeatures() {
        return features;
    }
   
}
