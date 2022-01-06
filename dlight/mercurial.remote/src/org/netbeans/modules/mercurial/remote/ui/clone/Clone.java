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
package org.netbeans.modules.mercurial.remote.ui.clone;

import java.awt.Dialog;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.modules.versioning.core.api.VCSFileProxy;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.HelpCtx;

/**
 *
 * 
 */
public class Clone implements PropertyChangeListener {

    private final ClonePanel panel;
    private final JButton okButton;
    private final JButton cancelButton;
    private final DocumentListener          listener;

    
    /** Creates a new instance of Clone */
    public Clone(VCSFileProxy repository, VCSFileProxy to) {
        panel = new ClonePanel(repository, to);
        okButton = new JButton();
        org.openide.awt.Mnemonics.setLocalizedText(okButton, org.openide.util.NbBundle.getMessage(CloneAction.class, "CTL_CloneForm_Action_Clone"));
        okButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(Clone.class, "ACSN_CloneForm_Action_Clone")); // NOI18N
        okButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(Clone.class, "ACSD_CloneForm_Action_Clone")); // NOI18N
        cancelButton = new JButton();
        org.openide.awt.Mnemonics.setLocalizedText(cancelButton, org.openide.util.NbBundle.getMessage(CloneAction.class, "CTL_CloneForm_Action_Cancel"));
        cancelButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(Clone.class, "ACSD_CloneForm_Action_Cancel")); // NOI18N
        cancelButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(Clone.class, "ACSN_CloneForm_Action_Cancel")); // NOI18N
        this.listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { nameChange(); }
            @Override
            public void removeUpdate(DocumentEvent e) { nameChange(); }
            @Override
            public void changedUpdate(DocumentEvent e) { nameChange(); }
        };
        panel.toTextField.getDocument().addDocumentListener(listener);
    } 
    
    public boolean showDialog() {
        DialogDescriptor dialogDescriptor = new DialogDescriptor(panel, org.openide.util.NbBundle.getMessage(Clone.class, "CTL_CloneDialog"),  // NOI18N
                true, new Object[] {okButton, cancelButton}, okButton, DialogDescriptor.DEFAULT_ALIGN, new HelpCtx(this.getClass()), null);
        dialogDescriptor.setValid(false);
        
        Dialog dialog = DialogDisplayer.getDefault().createDialog(dialogDescriptor);     
        dialog.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(Clone.class, "ACSD_CloneDialog")); // NOI18N
        dialog.setVisible(true);
        dialog.setResizable(false);
        boolean ret = dialogDescriptor.getValue() == okButton;
        return ret;       
    }

    private void nameChange() {
        if (panel.toTextField.getText().trim().length() > 0) { 
            okButton.setEnabled(true);
        } else {
            okButton.setEnabled(false);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(okButton != null) {
            boolean valid = ((Boolean)evt.getNewValue()).booleanValue();
            okButton.setEnabled(valid);
        }       
    }

    public VCSFileProxy getTargetDir() {
        if (panel == null) {
            return null;
        }
        return panel.getTargetDir();
    }

}
