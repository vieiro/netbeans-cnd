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

package org.netbeans.modules.cnd.editor.cplusplus;

import java.awt.event.ActionEvent;
import java.util.MissingResourceException;
import java.util.prefs.Preferences;
import javax.swing.Action;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.netbeans.editor.BaseAction;
import org.netbeans.editor.BaseDocument;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 *
 */
/* package */ abstract class AbstractCamelCasePosition extends BaseAction {

    private Action originalAction;
    
    public AbstractCamelCasePosition(String name, Action originalAction) {
        super(name, MAGIC_POSITION_RESET);
        
        if (originalAction != null) {
            Object nameObj = originalAction.getValue(Action.NAME);
            if (nameObj instanceof String) {
                // We will be wrapping around the original action, use its name
                putValue(NAME, nameObj);
                this.originalAction = originalAction;
            }
        }
        
        String desc = getShortDescription();
        if (desc != null) {
            putValue(SHORT_DESCRIPTION, desc);
        }
    }

    @Override
    public final void actionPerformed(ActionEvent evt, final JTextComponent target) {
        if (target != null) {
            if (originalAction != null && !isUsingCamelCase()) {
                if (originalAction instanceof BaseAction) {
                    ((BaseAction) originalAction).actionPerformed(evt, target);
                } else {
                    originalAction.actionPerformed(evt);
                }
            } else {
                final BaseDocument bdoc = org.netbeans.editor.Utilities.getDocument(target);
                if (bdoc != null) {
                    bdoc.runAtomicAsUser (new Runnable () {
                        @Override
                        public void run () {
                            DocumentUtilities.setTypingModification(bdoc, true);
                            try {
                                int offset = newOffset(target);
                                if (offset >= 0 && offset < bdoc.getLength()) {
                                    moveToNewOffset(target, offset);
                                }
                            } catch (BadLocationException ble) {
                                target.getToolkit().beep();
                            } finally {
                                DocumentUtilities.setTypingModification(bdoc, false);
                            }
                        }
                    });
                } else {
                    target.getToolkit().beep();
                }
            }
        }
    }

    protected abstract int newOffset(JTextComponent textComponent) throws BadLocationException;
    protected abstract void moveToNewOffset(JTextComponent textComponent, int offset) throws BadLocationException;

    public String getShortDescription(){
        String name = (String)getValue(Action.NAME);
        if (name == null) return null;
        String shortDesc;
        try {
            shortDesc = NbBundle.getBundle(CCKit.class).getString(name); // NOI18N
        }catch (MissingResourceException mre){
            shortDesc = name;
        }
        return shortDesc;
    }        

    private boolean isUsingCamelCase() {
        return NbPreferences.root ().getBoolean("useCamelCaseStyleNavigation", true); // NOI18N
    }

}
