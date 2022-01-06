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

package org.netbeans.modules.cnd.debugger.common2.debugger.options;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.table.*;

import org.openide.util.HelpCtx;
import java.beans.PropertyEditorSupport;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import org.openide.explorer.propertysheet.PropertyEnv;

import org.netbeans.modules.cnd.debugger.common2.utils.ListUI;

class ExceptionsPanel extends JPanel implements PropertyChangeListener, HelpCtx.Provider {
    private JCheckBox allCheckbox;
    private JCheckBox interceptUnexpectedCheckbox;
    private JCheckBox interceptUnhandledCheckbox;
    private ListUI interceptList;
    private ListUI exceptList;
    private Exceptions exceptions;
    private PropertyEditorSupport propertyEditor;
    
    /** Creates new form CustomizerCompile */
    public ExceptionsPanel(PropertyEditorSupport propertyEditor,
			   PropertyEnv env,
			   Exceptions exceptions) {
        this.propertyEditor = propertyEditor;
        initComponents();                

	adjustButtons();
	setPreferredSize(new java.awt.Dimension(500, 500));

	// Arrange for propertyChange to get called when OK is pressed.
        env.setState(PropertyEnv.STATE_NEEDS_VALIDATION);
        env.addPropertyChangeListener(this);

	this.exceptions = exceptions;

	initValues(exceptions);
    }
    
    /**
     * was: part of RunConfigDialog.configToGui()
     */

    private void initValues(Exceptions exceptions) {
        // Exceptions Intercepts
        String[] intercepts = exceptions.getInterceptList();
        if (intercepts != null && intercepts.length > 0) {
            int n = intercepts.length;
            ArrayList<String> col0 = new ArrayList<String>(intercepts.length+3); // Leave slop for inserts
            for (int i = 0; i < intercepts.length; i++) {
                col0.add(intercepts[i]);
            }
            interceptList.model.setData(intercepts.length, col0, null);
        } else {
            // TMP interceptExcepModel.setData(0, null, null);
	}

        // Do-Not-Intercepts
        String[] nointercepts = exceptions.getInterceptExceptList();
        if (nointercepts != null && nointercepts.length > 0) {
            ArrayList<String> col0 = new ArrayList<String>(nointercepts.length+3); // Leave slop for inserts
            for (int i = 0; i < nointercepts.length; i++) {
                col0.add(nointercepts[i]);
            }
            exceptList.model.setData(nointercepts.length, col0, null);
        } else {
            // TMP exceptList.model.setData(0, null, null);
	}

	allCheckbox.setSelected(exceptions.isAll());
        interceptUnhandledCheckbox.setSelected(exceptions.isInterceptUnhandled());
        interceptUnexpectedCheckbox.setSelected(exceptions.isInterceptUnexpected());
	adjustButtons();
    } 

    private Object getPropertyValue() throws IllegalStateException {
	int numRows;

	interceptList.model.finishEditing();
	exceptList.model.finishEditing();

	// Intercepts
	String [] intercepts = null;
	numRows = interceptList.model.getRowCount();
	if (numRows > 0) {
	    // Do real count (ignore empty rows)
	    int num = 0;
	    for (int j = 0; j < numRows; j++) {
		String name = (String)interceptList.model.getValueAt(j, 0);
		if (name.length() == 0)
		    continue;
		// Empty right hand side is legal
		num++;
	    }
	    
	    intercepts = new String[num];
	    num = 0;
	    for (int j = 0; j < numRows; j++) {
		String name = (String)interceptList.model.getValueAt(j, 0);
		if (name.length() == 0)
		    continue;
		intercepts[num++] = name;
	    }
	}
	
	// Don't-Intercepts
	String [] nointercepts = null;
	numRows = exceptList.model.getRowCount();
	if (numRows > 0) {
	    // Do real count (ignore empty rows)
	    int num = 0;
	    for (int j = 0; j < numRows; j++) {
		String name = (String)exceptList.model.getValueAt(j, 0);
		if (name.length() == 0)
		    continue;
		// Empty right hand side is legal
		num++;
	    }
	    
	    nointercepts = new String[num];
	    num = 0;
	    for (int j = 0; j < numRows; j++) {
		String name = (String)exceptList.model.getValueAt(j, 0);
		if (name.length() == 0)
		    continue;
		nointercepts[num++] = name;
	    }
	}

	exceptions.setInterceptList(intercepts, nointercepts,
				allCheckbox.isSelected(),
				interceptUnhandledCheckbox.isSelected(),
				interceptUnexpectedCheckbox.isSelected()
				);
	return exceptions;
    }
        
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (PropertyEnv.PROP_STATE.equals(evt.getPropertyName()) &&
	    evt.getNewValue() == PropertyEnv.STATE_VALID) {

	    // OK was pressed
            propertyEditor.setValue(getPropertyValue());
        }
    }
    
    @Override
    public HelpCtx getHelpCtx() {
        return new HelpCtx("DebuggerOptions" );
    }
    

    private void initComponents() {
        GridBagConstraints gridBagConstraints;
        setLayout(new GridBagLayout());

	Insets insets = new Insets(12, 12, 12, 12);
	Border margin = new EmptyBorder(insets);
	Border border = new CompoundBorder(new EtchedBorder(), margin);
	setBorder(border);

	// create buttons first so adjustButtons doesn't croak
	interceptList = new ListUI();
	exceptList = new ListUI();

	    //
	    // [ ] Intecept All Exceptions
	    // 
	    allCheckbox = new JCheckBox();
	    allCheckbox.setText(Catalog.get("InterceptAll"));	// NOI18N
	    Catalog.setAccessibleDescription(allCheckbox,
		"ACSD_InterceptAll");				// NOI18N
	    allCheckbox.setMnemonic(Catalog.
		getMnemonic("MNEM_InterceptAll"));		// NOI18N
	    allCheckbox.addItemListener(new ItemListener() {
                @Override
		public void itemStateChanged(ItemEvent e) {
		    adjustButtons();
		}
	    } );


	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
	    this.add(allCheckbox, gridBagConstraints);




	    interceptList.labelText = Catalog.get("ExceptIntercept");// NOI18N
	    interceptList.labelMnemonic =
		Catalog.getMnemonic("MNEM_ExceptIntercept");	// NOI18N
	    interceptList.accessibleDescription =
		Catalog.get("ACSD_ExceptIntercept");		// NOI18N
	    interceptList.column0Text = Catalog.get("ExceptInterceptTab");// NOI18N

	    interceptList.addText = Catalog.get("AddIExc");	// NOI18N
	    interceptList.addMnemonic =
		Catalog.getMnemonic("MNEM_AddN");		// NOI18N
	    interceptList.addActionListener = new ActionListener() {
                @Override
		public void actionPerformed(ActionEvent evt) {
		    addInterceptExceptionRow(evt);
		}
	    };
	    interceptList.remText = Catalog.get("RemoveIExc");	// NOI18N
	    interceptList.remMnemonic =
		Catalog.getMnemonic("MNEM_RemN");		// NOI18N
	    interceptList.remActionListener = new ActionListener() {
                @Override
		public void actionPerformed(ActionEvent evt) {
		    deleteInterceptExceptionRow(evt);
		}
	    };

	    JPanel interceptPanel = interceptList.make(true);

	    GridBagConstraints gbc;

	    gbc = new GridBagConstraints();
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = new Insets(11, 0, 0, 0);
	    this.add(interceptPanel, gbc);


	    exceptList.labelText = Catalog.get("ExceptIgnore");	// NOI18N
	    exceptList.labelMnemonic =
		Catalog.getMnemonic("MNEM_ExceptIgnore");	// NOI18N
	    exceptList.accessibleDescription =
		Catalog.get("ACSD_ExceptIgnore");		// NOI18N
	    exceptList.column0Text = Catalog.get("ExceptIgnoreTab");// NOI18N

	    exceptList.addText = Catalog.get("AddXExc");	// NOI18N
	    exceptList.addMnemonic =
		Catalog.getMnemonic("MNEM_AddG");		// NOI18N
	    exceptList.addActionListener = new ActionListener() {
                @Override
		public void actionPerformed(ActionEvent evt) {
		    addIgnoreExceptionRow(evt);
		}
	    };
	    exceptList.remText = Catalog.get("RemoveXExc");	// NOI18N
	    exceptList.remMnemonic =
		Catalog.getMnemonic("MNEM_RemG");		// NOI18N
	    exceptList.remActionListener = new ActionListener() {
                @Override
		public void actionPerformed(ActionEvent evt) {
		    deleteIgnoreExceptionRow(evt);
		}
	    };

	    JPanel exceptPanel = exceptList.make(true);

	    gbc = new GridBagConstraints();
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = new Insets(11, 0, 0, 0);
	    this.add(exceptPanel, gbc);



	    //
	    // [ ] Intecept Unexpected Exceptions
	    // [ ] Intecept UNhandled Exceptions
	    // 
	    interceptUnexpectedCheckbox = new JCheckBox();
	    interceptUnexpectedCheckbox.setText(Catalog.
		get("InterceptUnexpected"));		//NOI18N
	    Catalog.setAccessibleDescription(interceptUnexpectedCheckbox,
		"ACSD_Unexpected");			// NOI18N
	    interceptUnexpectedCheckbox.setMnemonic(
		Catalog.getMnemonic("MNEM_Unexpected"));	// NOI18N
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
	    gridBagConstraints.insets = new Insets(11, 0, 0, 0);
	    this.add(interceptUnexpectedCheckbox, gridBagConstraints);


	    interceptUnhandledCheckbox = new JCheckBox();
	    interceptUnhandledCheckbox.setText(Catalog.
		get("InterceptUnhandled"));		//NOI18N
	    Catalog.setAccessibleDescription(interceptUnhandledCheckbox,
		"ACSD_Unhandled");			// NOI18N
	    interceptUnhandledCheckbox.setMnemonic(
		Catalog.getMnemonic("MNEM_Unhandled"));	// NOI18N
	    gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
	    this.add(interceptUnhandledCheckbox, gridBagConstraints);
    }

    private void adjustButtons() {
	boolean all = allCheckbox.isSelected();
	interceptList.adjustButtons(all);
	exceptList.adjustButtons(false);
    }

    private JTable makeJTable(TableModel model) {
	JTable table = new JTable(model);
	table.getSelectionModel().
	    addListSelectionListener( new ListSelectionListener() {
                @Override
		public void valueChanged(ListSelectionEvent e) {
		    // DEBUG System.out.println("JTable Selection changed");
		    adjustButtons();
		}
	    });
	// single column, skip the header
	table.setTableHeader(null);
	return table;
    }

    private void deleteInterceptExceptionRow(ActionEvent evt) {
	int[] selRows = interceptList.table.getSelectedRows();
	if (selRows != null && selRows.length > 0) {
	    interceptList.model.removeRows(selRows);
	    adjustButtons();
	}
    } 

    private void addInterceptExceptionRow(ActionEvent evt) {
	interceptList.model.addRow();
	adjustButtons();
    }

    private void deleteIgnoreExceptionRow(ActionEvent evt) {
	int[] selRows = exceptList.table.getSelectedRows();
	if (selRows != null && selRows.length > 0) {
	    exceptList.model.removeRows(selRows);
	    adjustButtons();
	}
    }

    private void addIgnoreExceptionRow(ActionEvent evt) {
	exceptList.model.addRow();
	adjustButtons();
    }
}
