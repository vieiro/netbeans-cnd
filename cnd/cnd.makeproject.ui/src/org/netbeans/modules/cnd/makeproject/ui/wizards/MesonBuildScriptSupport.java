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
import org.netbeans.modules.cnd.makeproject.api.wizards.BuildSupport.BuildFile;
import org.netbeans.modules.cnd.makeproject.api.wizards.BuildSupport.BuildFileProvider;
import org.netbeans.modules.cnd.makeproject.api.wizards.PreBuildSupport;
import org.netbeans.modules.cnd.utils.CndPathUtilities;
import org.netbeans.modules.cnd.utils.FileFilterFactory;
import org.netbeans.modules.nativeexecution.api.ExecutionEnvironment;
import org.openide.filesystems.FileObject;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 */
@ServiceProvider(service = BuildFileProvider.class, position = 3000)
public class MesonBuildScriptSupport implements BuildFileProvider {
    @Override
    public BuildFile findBuildFileInFolder(FileObject folder, ExecutionEnvironment ee, CompilerSet compilerSet) {
        if ((folder != null) && folder.isFolder() && folder.canRead()) {
            FileObject script = folder.getFileObject("meson.build"); // NOI18N
            if ((script != null) && script.isValid()) {
                return new MesonScriptArtifact(script.getPath());
            }
        }
        return null;
    }

    @Override
    public BuildFile scriptToBuildFile(String script) {
        if ("meson.build".equals(CndPathUtilities.getBaseName(script))) { // NOI18N
            return new MesonScriptArtifact(script);
        }
        return null;
    }

    @Override
    public boolean isSupported(BuildFile script) {
        return script instanceof MesonScriptArtifact;
    }

    @Override
    public FileFilter[] getFileFilter() {
        return new FileFilter[]{FileFilterFactory.getMesonFileFilter()};
    }

    @Override
    public String getHint() {
        return NbBundle.getMessage(SelectModePanel.class, "SelectModeSimpleInstructionExtraText_Meson"); // NOI18N
    }

    private static final class MesonScriptArtifact implements BuildFile {
        private final String script;

        MesonScriptArtifact(String script) {
            this.script = script;
        }

        @Override
        public String getFile() {
            return script;
        }

        @Override
        public String getCleanCommandLine(String arguments, String workingDir) {
            StringBuilder buf = new StringBuilder();
            buf.append(PreBuildSupport.MESON_MACRO).append(" compile --clean"); // NOI18N
            if (arguments != null && !arguments.isEmpty()) {
                buf.append(' ').append(arguments); // NOI18N
            }
            return buf.toString();
        }

        @Override
        public String getBuildCommandLine(String arguments, String workingDir) {
            StringBuilder buf = new StringBuilder();
            buf.append(PreBuildSupport.MESON_MACRO).append(" compile"); // NOI18N
            if ((arguments != null) && !arguments.isEmpty()) {
                buf.append(' ').append(arguments); // NOI18N
            }
            return buf.toString();
        }

        @Override
        public String validate(ExecutionEnvironment ee, CompilerSet compilerSet) {
            return null;
        }
    }
}
