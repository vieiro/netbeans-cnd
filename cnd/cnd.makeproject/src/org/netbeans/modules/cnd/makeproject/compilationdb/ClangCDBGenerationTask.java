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
package org.netbeans.modules.cnd.makeproject.compilationdb;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;
import org.netbeans.modules.cnd.api.project.NativeFileItem.Language;
import static org.netbeans.modules.cnd.api.project.NativeFileItem.Language.CPP;
import static org.netbeans.modules.cnd.api.project.NativeFileItem.Language.C_HEADER;
import static org.netbeans.modules.cnd.api.project.NativeFileItem.Language.FORTRAN;
import static org.netbeans.modules.cnd.api.project.NativeFileItem.Language.OTHER;
import org.netbeans.modules.cnd.api.project.NativeProject;
import org.netbeans.modules.cnd.api.toolchain.AbstractCompiler;
import org.netbeans.modules.cnd.api.toolchain.CompilerSet;
import org.netbeans.modules.cnd.api.toolchain.PredefinedToolKind;
import org.netbeans.modules.cnd.api.toolchain.Tool;
import org.netbeans.modules.cnd.makeproject.api.MakeProject;
import org.netbeans.modules.cnd.makeproject.api.configurations.BasicCompilerConfiguration;
import org.netbeans.modules.cnd.makeproject.api.configurations.CCCompilerConfiguration;
import org.netbeans.modules.cnd.makeproject.api.configurations.CCompilerConfiguration;
import org.netbeans.modules.cnd.makeproject.api.configurations.ConfigurationDescriptorProvider;
import org.netbeans.modules.cnd.makeproject.api.configurations.Item;
import org.netbeans.modules.cnd.makeproject.api.configurations.ItemConfiguration;
import org.netbeans.modules.cnd.makeproject.api.configurations.MakeConfiguration;
import org.netbeans.modules.cnd.makeproject.api.configurations.MakeConfigurationDescriptor;
import org.openide.filesystems.FileAlreadyLockedException;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.util.Cancellable;

/**
 * ClangCDBGenerationTask creates/updates a Clang compilation database as
 * defined https://clang.llvm.org/docs/JSONCompilationDatabase.html
 *
 * @author antonio
 */
final class ClangCDBGenerationTask implements Callable<Void>, Cancellable {

    private static final String CDB_NAME = "compile_commands.json"; // NOI18N
    private static final Logger LOG
            = Logger.getLogger(ClangCDBGenerationTask.class.getName());
    private static final Level DEBUG_LEVEL = Level.INFO;

    protected boolean cancelled;
    protected final MakeProject makeProject;
    protected final ClangCDBGenerationCause cause;

    ClangCDBGenerationTask(MakeProject makeProject, ClangCDBGenerationCause cause) {
        this.makeProject = makeProject;
        this.cause = cause;
        this.cancelled = false;
    }

    @Override
    public Void call() throws Exception {
        if (cancelled) {
            return null;
        }

        FileObject compilationDatabase = makeProject.getProjectDirectory().getFileObject(CDB_NAME);

        LOG.log(DEBUG_LEVEL, "Updating compilation database {0} because {1}", // NOI18N
                new Object[]{compilationDatabase.getPath(), cause.name()});

        // Prepare some required objects
        NativeProject nativeProject = makeProject.getLookup().lookup(NativeProject.class);
        if (nativeProject == null) {
            LOG.log(DEBUG_LEVEL, "No native project found"); // NOI18N
            return null;
        }

        if (makeProject.getDevelopmentHost() != null && !makeProject.getDevelopmentHost().isLocal()) {
            LOG.log(DEBUG_LEVEL, "Not a local project"); // NOI18N
            return null;
        }

        MakeConfiguration activeMakeConfiguration = makeProject.getActiveConfiguration();
        if (activeMakeConfiguration == null) {
            LOG.log(DEBUG_LEVEL, "No active make configuration"); // NOI18N
            return null;
        }

        CompilerSet compilerSet = activeMakeConfiguration.getCompilerSet().getCompilerSet();
        if (compilerSet == null) {
            LOG.log(DEBUG_LEVEL, "No compiler set defined"); // NOI18N
            return null;
        }

        ConfigurationDescriptorProvider configurationDescriptorProvider
                = makeProject.getLookup().lookup(ConfigurationDescriptorProvider.class);
        if (configurationDescriptorProvider == null) {
            LOG.log(DEBUG_LEVEL, "No configuration descriptor found"); // NOI18N
            return null;
        }

        MakeConfigurationDescriptor makeConfigurationDescriptor = configurationDescriptorProvider.getConfigurationDescriptor();
        if (makeConfigurationDescriptor == null) {
            LOG.log(DEBUG_LEVEL, "No MakeConfigurationDescriptor"); // NOI18N
            return null;
        }

        // Get the project items,
        Item[] items = makeConfigurationDescriptor.getProjectItems();
        if (items == null || items.length == 0) {
            LOG.log(DEBUG_LEVEL, "No items in project"); // NOI18N
            return null;
        }

        if (cancelled) {
            LOG.log(DEBUG_LEVEL, "Task cancelled"); // NOI18N
            return null;

        }

        try ( FileLock lock = compilationDatabase.lock()) {
            updateCompilationDatabase(compilationDatabase, lock, items,
                    activeMakeConfiguration, compilerSet, makeConfigurationDescriptor);
        } catch (FileAlreadyLockedException fale) {
            LOG.log(DEBUG_LEVEL, "Somebody is already updating the file...");
        } catch (Throwable e) {
            LOG.log(DEBUG_LEVEL,
                    String.format("Error creating database %s:%s:%s", // NOI18N
                            compilationDatabase.getPath(),
                            e.getMessage(),
                            e.getClass().getName()),
                    e);
        }
        return null;
    }

    private void updateCompilationDatabase(
            FileObject compilationDatabase, FileLock fileLock, Item[] items, MakeConfiguration activeMakeConfiguration, CompilerSet compilerSet, MakeConfigurationDescriptor makeConfigurationDescriptor)
            throws Exception {

        // We're streaming the JSON output
        try ( PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(
                        compilationDatabase.getOutputStream(fileLock),
                        StandardCharsets.UTF_8))) {

            writer.println("[");
            boolean firstItem = true;
            for (Item item : items) {
                LOG.log(DEBUG_LEVEL, "Updating compilatino db for item {0}", item.getName());
                Language language = item.getLanguage();
                ItemConfiguration itemConfiguration = item.getItemConfiguration(activeMakeConfiguration);
                JSONObject commandObject = null;
                switch (language) {
                    case C_HEADER:
                        // C headers are not included in compilation databases
                        continue;
                    case C:
                    case CPP:
                    case OTHER: // Assembler?
                        // C and C++ files (and possibly assembler) are included in the compilation database
                        commandObject = generateObjectCommandForItem(
                                makeProject, activeMakeConfiguration,
                                compilerSet,
                                language, item, itemConfiguration);
                        break;
                    case FORTRAN:
                        // Fortran is not supported in clang-style compilation databases, AFAIK
                        break;
                }
                if (commandObject != null) {
                    if (firstItem) {
                        firstItem = false;
                    } else {
                        writer.print(",");
                    }
                    commandObject.writeJSONString(writer);
                    writer.println();
                }
            }
            writer.println("]");
        }
    }

    /**
     * Returns a Command Object required to compile an item.
     *
     * @param makeProject The project
     * @param activeMakeConfiguration The active make configuration.
     * @param compilerSet The compiler set.
     * @param item The item being compiled.
     * @param itemConfiguration The item configuration of the item.
     * @return A JSON String with this command object.
     * @throws Exception If an I/O error happens.
     */
    private JSONObject generateObjectCommandForItem(
            MakeProject makeProject,
            MakeConfiguration activeMakeConfiguration,
            CompilerSet compilerSet,
            Language language,
            Item item,
            ItemConfiguration itemConfiguration
    )
            throws Exception {

        if (itemConfiguration.getExcluded().getValue()) {
            LOG.log(DEBUG_LEVEL, "Skipping excluded item {0}", // NOI18N
                    item.getName());
            return null;
        }

        CommandObjectBuilder builder = new CommandObjectBuilder(makeProject);

        // 0. 'directory' property (already present in constructor)

        // 1. file property
        LOG.log(DEBUG_LEVEL, "ITEM: {0} FILE: {1}", new Object[] { item.getName(), item.getAbsolutePath()});
        builder.setFile(item.getAbsolutePath());

        // 2. command property
        PredefinedToolKind toolKind = itemConfiguration.getTool();
        Tool compilerTool = compilerSet.getTool(toolKind);
        if (!(compilerTool instanceof AbstractCompiler)) {
            LOG.log(DEBUG_LEVEL, "Cannot find a compiler for item {0}", // NOI18N
                    item.getName());
            return null;
        }
        AbstractCompiler compiler = (AbstractCompiler) compilerTool;

        // 2.1 compiler path
        builder.addCommandItem(compiler.getPath());

        // 2.2 "-c" flag. This is currently hardwired in NetBeans CND... :-(
        builder.addCommandItem("-c");

        // 2.3 The full path of the file to compile
        builder.addCommandItem(builder.getFile());

        // 2.3 language specific flags
        CCompilerConfiguration cConfiguration = itemConfiguration.getCCompilerConfiguration();
        if (cConfiguration != null) {
            LOG.log(DEBUG_LEVEL, "CConfiguration {0}", cConfiguration);
        }

        CCCompilerConfiguration cppConfiguration = itemConfiguration.getCCCompilerConfiguration();
        if (cppConfiguration != null) {
            LOG.log(DEBUG_LEVEL, "CPPConfiguration {0}", cppConfiguration);
        }

        // 2.4 all options
        BasicCompilerConfiguration basicCompilerConfiguration = itemConfiguration.getCompilerConfiguration();
        String allOptions = basicCompilerConfiguration.getAllOptions(compilerTool);
        LOG.log(DEBUG_LEVEL, "allOptions {0}", allOptions);
        builder.addCommandItem(allOptions);

        // CCompilerConfiguration cCompilerConfiguration = itemConfiguration.getCCompilerConfiguration();
        // cCompilerConfiguration.get
        return builder.build();
    }

    @Override
    public boolean cancel() {
        this.cancelled = true;
        return true;
    }

}
