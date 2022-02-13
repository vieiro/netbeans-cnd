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

import org.netbeans.modules.cnd.lsp.client.constants.LSPLanguageIdentifiers;
import org.netbeans.modules.cnd.utils.MIMENames;
import org.openide.filesystems.FileObject;

/**
 * Maps between mimetypes (or fileobjects) and a LSPLanguageIdentifier
 * @author antonio
 */
public final class LSPLanguageIdentifiersMapper {

    private static final LSPLanguageIdentifiersMapper INSTANCE = new LSPLanguageIdentifiersMapper();

    public static LSPLanguageIdentifiersMapper getInstance() {
        return INSTANCE;
    }

    public LSPLanguageIdentifiers toLSPLanguageIdentifier(FileObject fileObject) {
        return toLSPLanguageIdentifier(fileObject == null ? null : fileObject.getMIMEType());
    }

    public LSPLanguageIdentifiers toLSPLanguageIdentifier(String mimetype) {
        if (mimetype == null) {
            return LSPLanguageIdentifiers.c;
        }
        switch(mimetype) {
            case MIMENames.C_MIME_TYPE:
                return LSPLanguageIdentifiers.c;
            case MIMENames.HEADER_MIME_TYPE:
                return LSPLanguageIdentifiers.c;
            case MIMENames.CPLUSPLUS_MIME_TYPE:
                return LSPLanguageIdentifiers.cpp;
            case MIMENames.BAT_MIME_TYPE:
                return LSPLanguageIdentifiers.bat;
            default:
                return LSPLanguageIdentifiers.c;
        }
    }
    
}
