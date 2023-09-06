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

package org.netbeans.modules.cnd.builds;

import java.io.IOException;
import org.netbeans.modules.cnd.execution.ExecutionSupport;
import org.openide.filesystems.FileObject;
import org.openide.loaders.MultiDataObject;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.NbBundle;

public class MesonExecSupport extends ExecutionSupport {

    // the property sheet where properties are shown
    private Sheet.Set sheetSet;
    private static final String PROP_MESON_COMMAND = "mesonCommand"; // NOI18N
    private static final String PROP_RUN_DIRECTORY = "rundirectory"; // NOI18N
    private static final String PROP_ENVIRONMENT = "environment"; // NOI18N
    // The list of our properties
    private PropertySupport<String> mesonRunDirectory;
    private PropertySupport<String> mesonCommandProperty;
    private PropertySupport<String> mesonEnvironmentProperty;

    public MesonExecSupport(MultiDataObject.Entry entry) {
        super(entry);
    }

    public FileObject getFileObject() {
        return getEntry().getFile();
    }

    private void createProperties() {
        if (mesonCommandProperty == null) {
            mesonCommandProperty = createMesonCommandProperty();
            mesonRunDirectory = createRunDirectoryProperty();
            mesonEnvironmentProperty = createEnvironmentProperty(PROP_ENVIRONMENT, getString("PROP_MESON_ENVIRONMENT"), getString("HINT_MESON_ENVIRONMENT")); // NOI18N
        }
    }

    @Override
    public void addProperties(Sheet.Set set) {
        createProperties();
        this.sheetSet = set;
        set.put(createParamsProperty(PROP_FILE_PARAMS, getString("PROP_MESON_PARAMS"), getString("HINT_MESON_PARAMS")));
        set.put(mesonRunDirectory);
        set.put(mesonCommandProperty);
        set.put(mesonEnvironmentProperty);
    }

    private PropertySupport<String> createMesonCommandProperty() {
         PropertySupport<String> result = new PropertySupport.ReadWrite<String>(PROP_MESON_COMMAND, String.class,
                getString("PROP_MESON_COMMAND"), getString("HINT_MESON_COMMAND")) { // NOI18N
            @Override
            public String getValue() {
                return getMesonCommand();
            }
            @Override
            public void setValue(String val) {
                setMesonCommand(val);
            }
            @Override public boolean supportsDefaultValue() {
                return true;
            }
            @Override public void restoreDefaultValue() {
                setValue(null);
            }
            @Override public boolean canWrite() {
                return getEntry().getFile().getParent().canWrite();
            }
        };
        //String editor hint to use a JTextField, not a JTextArea for the
        //custom editor.  Arguments can't be multiline anyway.
        result.setValue("oneline", Boolean.TRUE);// NOI18N
        return result;
    }

    public String getMesonCommand() {
        String meson = (String) getEntry().getFile().getAttribute(PROP_MESON_COMMAND);
        if (meson == null || meson.equals("")) { // NOI18N
            meson = "meson";// NOI18N
            setMesonCommand(meson);
        }
        return meson;
    }

    public void setMesonCommand(String meson) {
        try {
            getEntry().getFile().setAttribute(PROP_MESON_COMMAND, meson);
        } catch (IOException ex) {
            if (Boolean.getBoolean("netbeans.debug.exceptions")) { // NOI18N
                ex.printStackTrace();
            }
        }
    }

    private PropertySupport<String> createRunDirectoryProperty() {
        PropertySupport<String> result = new PropertySupport.ReadWrite<String>(PROP_RUN_DIRECTORY, String.class,
                getString("PROP_RUN_MESON_DIRECTORY"), getString("HINT_RUN_MESON_DIRECTORY")) { // NOI18N
            @Override
            public String getValue() {
                return getRunDirectory();
            }
            @Override
            public void setValue(String val) {
                setRunDirectory(val);
            }
            @Override public boolean supportsDefaultValue() {
                return true;
            }
            @Override public void restoreDefaultValue() {
                setValue(null);
            }
            @Override public boolean canWrite() {
                return getEntry().getFile().getParent().canWrite();
            }
        };
        //String editor hint to use a JTextField, not a JTextArea for the
        //custom editor.  Arguments can't be multiline anyway.
        result.setValue("oneline", Boolean.TRUE);// NOI18N
        return result;
    }

    public String getRunDirectory() {
        String dir = (String) getEntry().getFile().getAttribute(PROP_RUN_DIRECTORY);
        if (dir == null) {
            dir = "."; // NOI18N
            setRunDirectory(dir);
        }
        return dir;
    }

    public void setRunDirectory(String dir) {
        FileObject fo = getEntry().getFile();
        try {
            fo.setAttribute(PROP_RUN_DIRECTORY, dir);
        } catch (IOException ex) {
            if (Boolean.getBoolean("netbeans.debug.exceptions")) { // NOI18N
                ex.printStackTrace();
            }
        }
    }

    private static String getString(String key){
        return NbBundle.getBundle(MesonExecSupport.class).getString(key);
    }
}
