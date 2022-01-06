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

package org.netbeans.modules.cnd.cmake.editor;

import javax.swing.Action;
import javax.swing.text.Document;
import javax.swing.text.TextAction;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.cnd.utils.MIMENames;
import org.netbeans.modules.editor.NbEditorKit;

/**
 */
public class CMakeKit extends NbEditorKit {
    private static final String COMMENT_LINE = "#"; //NOI18N

    @Override
    public Document createDefaultDocument() {
        Document doc = super.createDefaultDocument();
        doc.putProperty(BaseDocument.WRITE_LINE_SEPARATOR_PROP, BaseDocument.LS_LF);
        return doc;
    }

    @Override
    public String getContentType() {
        return MIMENames.CMAKE_MIME_TYPE;
    }
    
    protected 
    @Override
    Action[] createActions() {
        Action[] superActions = super.createActions();
        Action[] ccActions = new Action[]{
            new CommentAction(COMMENT_LINE),
            new UncommentAction(COMMENT_LINE),
            new ToggleCommentAction(COMMENT_LINE)};
        ccActions = TextAction.augmentList(superActions, ccActions);

        return ccActions;
    }
}
