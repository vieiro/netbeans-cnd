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
import org.eclipse.lsp4j.SemanticTokensCapabilities;
import org.eclipse.lsp4j.TokenFormat;

/**
 * @see
 * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#semanticTokensClientCapabilities">Semantic Client Capabilities</a>
 * @author antonio
 */
public final class NBSemanticTokensCapabilities extends SemanticTokensCapabilities {

    private static final String[] TOKEN_TYPES = {

    };

    /**
     * Token formats.
     */
    private static final String [] FORMATS = {
        "relative" // NOI18N
    };

    public NBSemanticTokensCapabilities() {
        super(true);
        // https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#tokenFormat
        setFormats(Arrays.asList(TokenFormat.Relative));
        // If we support tokens that span multiple lines
        setMultilineTokenSupport(true);
        // 
        setTokenTypes(Arrays.asList(TOKEN_TYPES));
    }
    
}
