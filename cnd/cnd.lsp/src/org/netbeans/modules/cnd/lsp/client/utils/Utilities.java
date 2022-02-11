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
package org.netbeans.modules.cnd.lsp.client.utils;

import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.StyledDocument;
import org.eclipse.lsp4j.Position;
import org.netbeans.api.editor.document.LineDocument;
import org.netbeans.api.editor.document.LineDocumentUtils;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.URLMapper;

/**
 *
 * @author antvie
 */
public final class Utilities {

    private static final Logger LOG = Logger.getLogger(Utilities.class.getName());

    /**
     * Returns the StyledDocument responsible for editing the given document URI.
     * @param documentURI A DocumentURI
     * @return The StyledDocument that is editing the given documentURI, or null.
     */
    public static StyledDocument getDocument(String documentURI) {
        URL url;
        try {
            url = new URI(documentURI).toURL();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Invalid documentURI " + documentURI, ex);
            return null;
        }
        FileObject fileObject = URLMapper.findFileObject(url);
        if (fileObject == null) {
            LOG.log(Level.SEVERE, "Inexistent documentURI {0}", documentURI);
            return null;
        }
        // Is the fileObject being edited?
        EditorCookie editorCookie = fileObject.getLookup().lookup(EditorCookie.class);
        if (editorCookie == null) {
            return null;
        }
        return editorCookie.getDocument();
    }

    /**
     * Returns an offset in a Document given a Position.
     * @param document The document.
     * @param position The position.
     * @return An offset.
     */
    public static int offset(LineDocument document, Position position) {
        // TODO: This is dependent on the encoding used by the LSP Server.
        // Note that the LSP specification uses UTF-16 by default, but many
        // LSP servers can be configured to support other ones.
        int line = position.getLine();
        int column = position.getCharacter();
        int lineStartOffset = LineDocumentUtils.getLineStartFromIndex(document, line);
        int offset = lineStartOffset + column;
        return offset;
    }

}
