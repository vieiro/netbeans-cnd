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
package org.netbeans.modules.cnd.refactoring.actions;

import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import org.netbeans.editor.BaseAction;

/**
 * action for in-place rename
 * (copy of  org.netbeans.modules.java.editor.rename.InstantRenameAction)
 * 
 */
public class InstantRenameAction extends BaseAction {

    /** Creates a new instance of InstantRenameAction */
    public InstantRenameAction() {
        super("in-place-refactoring", MAGIC_POSITION_RESET | UNDO_MERGE_RESET); // NOI18N
    }

    @Override
    public void actionPerformed(ActionEvent evt, final JTextComponent target) {
        InstantRenamePerformer.invokeInstantRename(target);
    }

    @Override
    protected Class getShortDescriptionBundleClass() {
        return InstantRenameAction.class;
    }
}
