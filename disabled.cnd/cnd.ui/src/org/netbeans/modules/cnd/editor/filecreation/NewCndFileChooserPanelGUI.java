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

package org.netbeans.modules.cnd.editor.filecreation;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.netbeans.modules.cnd.utils.MIMEExtensions;
import org.netbeans.modules.remote.spi.FileSystemProvider;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 * NewCndFileChooserPanelGUI is SimpleTargetChooserPanelGUI extended with extension selector and logic
 * 
 */
class NewCndFileChooserPanelGUI extends CndPanelGUI implements ActionListener{

    private final Logger logger;
    private final String defaultExtension;
    private String expectedExtension;
    private final MIMEExtensions es;
    private final boolean fileWithoutExtension;

    /** Creates new form NewCndFileChooserPanelGUI */
    NewCndFileChooserPanelGUI( Project project, SourceGroup[] folders, Component bottomPanel, MIMEExtensions es, String defaultExt) {
        super(project, folders);

        this.logger = Logger.getLogger("cnd.editor.filecreation"); // NOI18N
        this.es = es;
        this.fileWithoutExtension = "".equals(defaultExt);
        
        initComponents();
        
        locationComboBox.setRenderer( CELL_RENDERER );
        
        if ( bottomPanel != null ) {
            bottomPanelContainer.add( bottomPanel, java.awt.BorderLayout.CENTER );
        }
        defaultExtension = defaultExt;
        initValues(null, null, null);
        
        browseButton.addActionListener( this );
        locationComboBox.addActionListener( this );
        documentNameTextField.getDocument().addDocumentListener( this );
        folderTextField.getDocument().addDocumentListener( this );
        
        setName (NbBundle.getMessage(NewCndFileChooserPanelGUI.class, "LBL_SimpleTargetChooserPanel_Name")); // NOI18N
    }
    
    @Override
    public void initValues( FileObject template, FileObject preselectedFolder, String documentName ) {
        assert project != null;
        
        projectTextField.setText(getProjectDisplayName(project));

        Sources sources = ProjectUtils.getSources( project );
        if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "sources is {0}", sources); // NOI18N
        }

        folders = sources.getSourceGroups( Sources.TYPE_GENERIC );
        if (logger.isLoggable(Level.FINE)) {
            for (int i = 0; i < folders.length; ++i) {
                logger.log(Level.FINE, "folders[{0}] = {1}", new Object[]{i, folders[i]}); // NOI18N
            }
        }

        if ( folders.length < 2 ) {
            // one source group i.e. hide Location
            locationLabel.setVisible( false );
            locationComboBox.setVisible( false );
        }
        else {
            // more source groups user needs to select location
            locationLabel.setVisible( true );
            locationComboBox.setVisible( true );
            
        }
        
        locationComboBox.setModel( new DefaultComboBoxModel( folders ) );
        // Guess the group we want to create the file in
        SourceGroup preselectedGroup = getPreselectedGroup( folders, preselectedFolder );        
        locationComboBox.setSelectedItem( preselectedGroup );               
        // Create OS dependent relative name
        folderTextField.setText( getRelativeNativeName( preselectedGroup.getRootFolder(), preselectedFolder ) );
        
        String ext = defaultExtension == null? es.getDefaultExtension() : defaultExtension;
        cbExtension.setSelectedItem(ext);
        cbExtension.enableInputMethods(true);
        expectedExtension = ext;

        Object editorComp = cbExtension.getEditor().getEditorComponent();
        if(editorComp instanceof JTextField)
        {
           ((JTextField)editorComp).getDocument().addDocumentListener(new DocumentListener() {
                private void update(Document doc) {
                    try {
                        expectedExtension = doc.getText(0, doc.getLength() );
                        updateCreatedFile();
                    } catch (BadLocationException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
               
                @Override
                public void insertUpdate(DocumentEvent e) {
                    update(e.getDocument());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    update(e.getDocument());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    update(e.getDocument());
                }
            });
        }
        
        String displayName = null;
        try {
            if (template != null) {
                DataObject templateDo = DataObject.find (template);
                displayName = templateDo.getNodeDelegate ().getDisplayName ();
            }
        } catch (DataObjectNotFoundException ex) {
            displayName = template.getName ();
        }
        putClientProperty ("NewFileWizard_Title", displayName);// NOI18N        
        
        if (template != null) {
            if (documentName == null) {
                final String baseName = NEW_FILE_PREFIX + template.getName ();
                documentName = baseName;
                FileObject currentFolder = preselectedFolder != null ? preselectedFolder : getTargetGroup().getRootFolder();
                if (currentFolder != null) {
                    int index = 0;
                    while (true) {
                        FileObject _tmp = currentFolder.getFileObject(documentName, ext);
                        if (_tmp == null) {
                            break;
                        }
                        documentName = baseName + ++index;
                    }
                }
                
            }
            documentNameTextField.setText (documentName);
            documentNameTextField.selectAll ();
        }

    }

    @Override
    public SourceGroup getTargetGroup() {
        Object selectedItem = locationComboBox.getSelectedItem();
        if (selectedItem == null) {
            // workaround for MacOS, see IZ 175457
            selectedItem = locationComboBox.getItemAt(locationComboBox.getSelectedIndex());
            if (selectedItem == null) {
                selectedItem = locationComboBox.getItemAt(0);
            }
        }
        return (SourceGroup) selectedItem;
    }

    @Override
    public String getTargetFolder() {
        
        String folderName = folderTextField.getText().trim();
        
        if ( folderName.length() == 0 ) {
            return "";
        }
        else {           
            return folderName.replace( fileSeparatorChar, '/' ); // NOI18N
        }
    }
    
    @Override
    public String getTargetName() {
        String documentName = documentNameTextField.getText().trim();
        
        if ( documentName.length() == 0){
            return null;
        }
        else {
            String docExt = FileUtil.getExtension( documentName );
            if (docExt.length() == 0 && expectedExtension.length() > 0) {
                documentName += '.' + expectedExtension;
            } else {
                assert docExt.equals(expectedExtension);
            }
            return documentName;
        }
    }
        
    public String getTargetExtension() {
        return expectedExtension;
    }

    public boolean useTargetExtensionAsDefault() {
        return cbSetAsDefault.isSelected();
    }
        
    @Override
    protected void updateCreatedFile() {
        if (fileWithoutExtension && getTargetExtension().length() == 0 || es.getDefaultExtension().equals(getTargetExtension())) {
            cbSetAsDefault.setEnabled(false);
        } else {
            cbSetAsDefault.setEnabled(true);
        }

        FileObject root = getTargetGroup().getRootFolder();

        String folderName = folderTextField.getText().trim();
        String documentName = documentNameTextField.getText().trim();
        String docExt = FileUtil.getExtension( documentName );
        
        String createdFileName = root.getPath() + 
            ( folderName.startsWith("/") || folderName.startsWith( fileSeparator ) ? "" : "/" ) + // NOI18N
            folderName + 
            ( folderName.endsWith("/") || folderName.endsWith( fileSeparator ) || folderName.length() == 0 ? "" : "/" ) + // NOI18N
            documentName ; // NOI18N
        
//      Use Cases:        
//        1) User wants to use different (but known) extension then the default one (for example "cpp")
//        1.1 He chooses this extension from the list in the combo box.
//           This extension becomes a new default.
//
//        1.2 He types the full file name with the extension.
//           The extension becomes a new default.
//           No extension is added to the file name.
//
//        2) User wants to use different (but unknown) extension then the default one (for example "abc")
//        2.1) He types the extension into the combo box (which is editable)
//           The notification is displayed at the bottom part of the panel.
//
//        2.2) He types the full name with the extension.
//           The "Extension" combo box gets disabled and no extension is added to the file name.
//           The notification is displayed at the bottom part of the panel.           
        
        if (docExt.length() == 0) {
            cbExtension.setEnabled(true);
            createdFileName += "." + expectedExtension; // NOI18N
        } else {
            cbExtension.setEnabled(false);
            cbExtension.setSelectedItem(docExt);
            expectedExtension = docExt;
        }

        char fileSeparatorChar = FileSystemProvider.getFileSeparatorChar(getProject().getProjectDirectory());
        createdFileName = createdFileName.replace( '/', fileSeparatorChar );
        if (!createdFileName.equals(fileTextField.getText())) {
            fileTextField.setText( createdFileName );
            changeSupport.fireChange();
        }
    }

    private DefaultComboBoxModel getExtensionsCBModel() {
        Vector<String> vExt = new Vector<String>(es.getValues());
        return new javax.swing.DefaultComboBoxModel(vExt);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        documentNameTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbExtension = new javax.swing.JComboBox();
        cbSetAsDefault = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        projectTextField = new javax.swing.JTextField();
        locationLabel = new javax.swing.JLabel();
        locationComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        folderTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        fileTextField = new javax.swing.JTextField();
        targetSeparator = new javax.swing.JSeparator();
        bottomPanelContainer = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel3.setLabelFor(documentNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(NewCndFileChooserPanelGUI.class, "LBL_TargetChooser_ClassName_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel1.add(documentNameTextField, gridBagConstraints);
        documentNameTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(NewCndFileChooserPanelGUI.class).getString("AD_documentNameTextField")); // NOI18N

        jLabel5.setLabelFor(cbExtension);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(NewCndFileChooserPanelGUI.class, "LBL_TargetChooser_Extension_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel5, gridBagConstraints);

        cbExtension.setEditable(true);
        cbExtension.setModel(getExtensionsCBModel());
        cbExtension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbExtensionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 5, 0);
        jPanel1.add(cbExtension, gridBagConstraints);
        cbExtension.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(NewCndFileChooserPanelGUI.class, "AD_ExtensionTextField")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(cbSetAsDefault, org.openide.util.NbBundle.getMessage(NewCndFileChooserPanelGUI.class, "ACSD_SetAsDefaultCheckBox")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel1.add(cbSetAsDefault, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 24, 0);
        add(jPanel1, gridBagConstraints);

        jLabel1.setLabelFor(projectTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NewCndFileChooserPanelGUI.class, "LBL_TargetChooser_Project_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        add(jLabel1, gridBagConstraints);

        projectTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 6, 0);
        add(projectTextField, gridBagConstraints);
        projectTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(NewCndFileChooserPanelGUI.class).getString("AD_projectTextField")); // NOI18N

        locationLabel.setLabelFor(locationComboBox);
        org.openide.awt.Mnemonics.setLocalizedText(locationLabel, org.openide.util.NbBundle.getMessage(NewCndFileChooserPanelGUI.class, "LBL_TargetChooser_Location_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(locationLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 0);
        add(locationComboBox, gridBagConstraints);
        locationComboBox.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(NewCndFileChooserPanelGUI.class).getString("AD_locationComboBox")); // NOI18N

        jLabel2.setLabelFor(folderTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(NewCndFileChooserPanelGUI.class, "LBL_TargetChooser_Folder_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 12, 0);
        add(folderTextField, gridBagConstraints);
        folderTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(NewCndFileChooserPanelGUI.class).getString("AD_folderTextField")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(browseButton, org.openide.util.NbBundle.getMessage(NewCndFileChooserPanelGUI.class, "LBL_TargetChooser_Browse_Button")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 12, 0);
        add(browseButton, gridBagConstraints);
        browseButton.getAccessibleContext().setAccessibleName("");
        browseButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(NewCndFileChooserPanelGUI.class).getString("AD_browseButton")); // NOI18N

        jLabel4.setLabelFor(fileTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(NewCndFileChooserPanelGUI.class, "LBL_TargetChooser_CreatedFile_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        add(jLabel4, gridBagConstraints);

        fileTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 12, 0);
        add(fileTextField, gridBagConstraints);
        fileTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(NewCndFileChooserPanelGUI.class).getString("AD_fileTextField")); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        add(targetSeparator, gridBagConstraints);

        bottomPanelContainer.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        add(bottomPanelContainer, gridBagConstraints);

        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(NewCndFileChooserPanelGUI.class).getString("AD_SimpleTargetChooserPanelGUI_1")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void cbExtensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbExtensionActionPerformed
        expectedExtension = (String)cbExtension.getSelectedItem();
        updateCreatedFile();
    }//GEN-LAST:event_cbExtensionActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanelContainer;
    private javax.swing.JButton browseButton;
    private javax.swing.JComboBox cbExtension;
    private javax.swing.JCheckBox cbSetAsDefault;
    private javax.swing.JTextField documentNameTextField;
    private javax.swing.JTextField fileTextField;
    private javax.swing.JTextField folderTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox locationComboBox;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JTextField projectTextField;
    private javax.swing.JSeparator targetSeparator;
    // End of variables declaration//GEN-END:variables

    // ActionListener implementation -------------------------------------------
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if ( browseButton == e.getSource() ) {
            FileObject fo=null;
            // Show the browse dialog             

            SourceGroup group = getTargetGroup();

            fo = BrowseFolders.showDialog( new SourceGroup[] { group }, 
                                           project, 
                                           folderTextField.getText().replace( fileSeparatorChar, '/' ) ); // NOI18N
                        
            if ( fo != null && fo.isFolder() ) {
                String relPath = FileUtil.getRelativePath( group.getRootFolder(), fo );
                folderTextField.setText( relPath.replace( '/', fileSeparatorChar ) ); // NOI18N
            }                        
        }
        else if ( locationComboBox == e.getSource() )  {
            updateCreatedFile();
        } else if ( cbExtension.getEditor() == e.getSource() ) {
            expectedExtension = (String)cbExtension.getEditor().getItem();
            updateCreatedFile();
        }
    }    
    
}
