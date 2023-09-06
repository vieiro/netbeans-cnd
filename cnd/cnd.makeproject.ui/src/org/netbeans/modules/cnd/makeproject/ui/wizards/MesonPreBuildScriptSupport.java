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
package org.netbeans.modules.cnd.makeproject.ui.wizards;

import javax.swing.filechooser.FileFilter;
import org.netbeans.modules.cnd.api.toolchain.CompilerSet;
import org.netbeans.modules.cnd.api.toolchain.PredefinedToolKind;
import org.netbeans.modules.cnd.api.toolchain.Tool;
import org.netbeans.modules.cnd.makeproject.api.configurations.MakeConfiguration;
import org.netbeans.modules.cnd.makeproject.api.wizards.PreBuildSupport;
import org.netbeans.modules.cnd.makeproject.api.wizards.PreBuildSupport.PreBuildArtifact;
import org.netbeans.modules.cnd.makeproject.api.wizards.PreBuildSupport.PreBuildArtifactProvider;
import org.netbeans.modules.cnd.utils.FileFilterFactory;
import org.netbeans.modules.nativeexecution.api.ExecutionEnvironment;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 */
@ServiceProvider(service = PreBuildArtifactProvider.class, position = 3000)
public class MesonPreBuildScriptSupport implements PreBuildArtifactProvider {
    @Override
    public PreBuildArtifact findScriptInFolder(FileObject folder, ExecutionEnvironment ee, CompilerSet compilerSet) {
        if (folder == null) {
            return null;
        }
        FileObject script = folder.getFileObject("meson.build"); // NOI18N
        if (script != null && script.isValid()) {
            MesonScriptArtifact res = new MesonScriptArtifact(script);
            if (res.validate(ee, compilerSet) == null) {
                return res;
            }
        }
        return null;
    }

    @Override
    public PreBuildArtifact scriptToArtifact(FileObject script) {
        if ("meson.build".equals(script.getNameExt())){ // NOI18N
            return new MesonScriptArtifact(script);
        }
        return null;
    }

    @Override
    public String getToolName() {
        return "meson"; //NOI18N
    }

    @Override
    public boolean isSupported(PreBuildArtifact script) {
        return script instanceof MesonScriptArtifact;
    }

    @Override
    public String getDisplayName() {
         return NbBundle.getMessage(MesonScriptArtifact.class, "ScriptTypeDisplayName_meson"); //NOI18N
    }

    @Override
    public String getHint() {
        return NbBundle.getMessage(MesonScriptArtifact.class, "SelectModeSimpleInstructionExtraText_Meson"); // NOI18N
    }

    @Override
    public String getFileChooserTitle() {
        return NbBundle.getMessage(MesonScriptArtifact.class, "ScriptTypeFileChooser_meson"); //NOI18N
    }

    @Override
    public FileFilter[] getFileFilter() {
        return new FileFilter[]{FileFilterFactory.getMesonFileFilter()};
    }

    private static final class MesonScriptArtifact implements PreBuildArtifact {
        private final FileObject script;

        MesonScriptArtifact(FileObject script) {
            this.script = script;
        }

        @Override
        public FileObject getScript() {
            return script;
        }

        @Override
        public String getArguments(ExecutionEnvironment ee, CompilerSet def, String flags) {
            StringBuilder buf = new StringBuilder(flags);
            ConfigureScriptSupport.appendIfNeed(MakeConfiguration.CND_BUILDDIR_MACRO, flags, buf, "");
            return buf.toString();
        }

        @Override
        public String getCommandLine(String arguments, String workingDir) {
            StringBuilder buf = new StringBuilder();
            buf.append(PreBuildSupport.MESON_MACRO)
               .append(" setup"); //NOI18N
            if ((arguments != null) && !arguments.isEmpty()) {
               buf.append(' ').append(arguments); //NOI18N
            }
            return buf.toString();
        }

        @Override
        public String validate(ExecutionEnvironment ee, CompilerSet compilerSet) {
            if (script.isValid() && script.isData() && script.canRead()) {
                DataObject dObj;
                try {
                    dObj = DataObject.find(script);
                } catch (DataObjectNotFoundException ex) {
                    return NbBundle.getMessage(MesonPreBuildScriptSupport.class, "MESON_BUILD_SCRIPT_DOES_NOT_EXIST"); //NOI18N
                }
                if (dObj == null) {
                    return NbBundle.getMessage(MesonPreBuildScriptSupport.class, "MESON_BUILD_SCRIPT_DOES_NOT_EXIST"); //NOI18N
                }
                Node node = dObj.getNodeDelegate();
                if (node == null) {
                    return NbBundle.getMessage(MesonPreBuildScriptSupport.class, "MESON_BUILD_SCRIPT_DOES_NOT_EXIST"); //NOI18N
                }
                if (compilerSet != null) {
                    Tool tool = compilerSet.findTool(PredefinedToolKind.MesonTool);
                    if (tool != null && !tool.getPath().isEmpty()) {
                        return null;
                    }
                    return NbBundle.getMessage(MesonPreBuildScriptSupport.class, "NotFoundMesonTool", compilerSet.getName()); //NOI18N
                }
                return NbBundle.getMessage(MesonPreBuildScriptSupport.class, "NotFoundMesonTool", ""); //NOI18N
            }
            return NbBundle.getMessage(MesonPreBuildScriptSupport.class, "MESON_BUILD_SCRIPT_IS_NOT_VALID"); //NOI18N
        }
    }
}
