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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
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
import org.openide.filesystems.FileUtil;
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
    private final ClangCDBSupport support;
    private final BlockingQueue<ClangCDBGenerationCause> pendingCauses;
    private final MakeProject makeProject;
    private final NativeProject nativeProject;
    private final MakeConfiguration activeMakeConfiguration;
    private final CompilerSet compilerSet;
    private final ConfigurationDescriptorProvider configurationDescriptorProvider;
    private final MakeConfigurationDescriptor makeConfigurationDescriptor;

    ClangCDBGenerationTask(ClangCDBSupport support, BlockingQueue<ClangCDBGenerationCause> pendingCauses)
            throws IllegalArgumentException {
        this.support = support;
        this.makeProject = support.getMakeProject();
        this.pendingCauses = pendingCauses;
        this.cancelled = false;

        // Prepare some required objects
        nativeProject = makeProject.getLookup().lookup(NativeProject.class);
        if (nativeProject == null) {
            throw new IllegalArgumentException("No native project found"); // NOI18N
        }

        if (makeProject.getDevelopmentHost() != null && !makeProject.getDevelopmentHost().isLocal()) {
            throw new IllegalArgumentException("Not a local project"); // NOI18N
        }

        activeMakeConfiguration = makeProject.getActiveConfiguration();
        if (activeMakeConfiguration == null) {
            throw new IllegalArgumentException("No active make configuration"); // NOI18N
        }

        compilerSet = activeMakeConfiguration.getCompilerSet().getCompilerSet();
        if (compilerSet == null) {
            throw new IllegalArgumentException("No compilerset defined"); // NOI18N
        }

        configurationDescriptorProvider
                = makeProject.getLookup().lookup(ConfigurationDescriptorProvider.class);
        if (configurationDescriptorProvider == null) {
            throw new IllegalArgumentException("No configuration descriptor found"); // NOI18N
        }

        makeConfigurationDescriptor = configurationDescriptorProvider.getConfigurationDescriptor();
        if (makeConfigurationDescriptor == null) {
            throw new IllegalArgumentException("No MakeConfigurationDescriptor"); // NOI18N
        }

    }

    @Override
    public Void call() throws Exception {

        try {
            updateCompilationDatabase();
        } catch (Throwable e) {
            LOG.log(Level.SEVERE, String.format("Error updating compilation database:%s:%s", e.getMessage(), e.getClass().getName()), e);
        }
        return null;
    }

    public void updateCompilationDatabase() throws Exception {
        LOG.log(DEBUG_LEVEL, "Task.call()");

        if (cancelled) {
            LOG.log(DEBUG_LEVEL, "Task cancelled.");
            return;
        }

        if (!support.isOpen()) {
            LOG.log(DEBUG_LEVEL, "Project is not open");
            return;
        }

        if (pendingCauses.isEmpty()) {
            LOG.log(DEBUG_LEVEL, "No pending causes");
            return;
        }

        ArrayList<ClangCDBGenerationCause> coalescedCauses = new ArrayList<>(pendingCauses.size());
        pendingCauses.drainTo(coalescedCauses);

        File projectDirectory = FileUtil.toFile(makeProject.getProjectDirectory());
        File compilationDatabase = new File(projectDirectory, CDB_NAME);
        LOG.log(DEBUG_LEVEL, "Updating compilation database {0} because {1}", // NOI18N
                new Object[]{compilationDatabase.getPath(), coalescedCauses.toString()});

        // Get the project items,
        Item[] items = makeConfigurationDescriptor.getProjectItems();
        if (items == null || items.length == 0) {
            LOG.log(DEBUG_LEVEL, "No items in project"); // NOI18N
            return;
        }

        if (cancelled) {
            LOG.log(DEBUG_LEVEL, "Task cancelled"); // NOI18N
            return;
        }

        long startTime = System.currentTimeMillis();

        File tempFile = Files.createTempFile("CDB", ".json").toFile();

        try ( FileOutputStream outputStream = new FileOutputStream(tempFile); //
                  PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            FileChannel channel = outputStream.getChannel();
            FileLock lock = channel.lock();
            updateCompilationDatabase(nativeProject, writer, lock, items);
        }

        if (!cancelled) {
            try {
                Files.move(tempFile.toPath(), compilationDatabase.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } finally {
                tempFile.delete();
            }
        }

        long endTime = System.currentTimeMillis();

        LOG.log(Level.INFO, "Compilation database regenerated in {0} ms.", endTime-startTime);

    }

    private void updateCompilationDatabase(NativeProject nativeProject, PrintWriter writer, FileLock lock, Item[] items)
            throws Exception {

        // We're streaming the JSON output
        writer.println("[");
        boolean firstItem = true;
        for (Item item : items) {
            if (cancelled) {
                return;
            }
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
                            nativeProject,
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
            NativeProject nativeProject, MakeProject makeProject, MakeConfiguration activeMakeConfiguration, CompilerSet compilerSet, Language language, Item item, ItemConfiguration itemConfiguration)
            throws Exception {

        if (itemConfiguration.getExcluded().getValue()) {
            LOG.log(DEBUG_LEVEL, "Skipping excluded item {0}", // NOI18N
                    item.getName());
            return null;
        }

        CommandObjectBuilder builder = new CommandObjectBuilder(makeProject);

        // 0. 'directory' entry in the command object is automatically added.
        // 1. 'file' entry in the command object.
        LOG.log(DEBUG_LEVEL, "ITEM: {0} FILE: {1}", new Object[]{item.getName(), item.getAbsolutePath()});
        builder.setFile(item.getAbsolutePath());

        // 2. 'command' entry in the command object
        PredefinedToolKind toolKind = itemConfiguration.getTool();
        Tool compilerTool = compilerSet.getTool(toolKind);
        if (!(compilerTool instanceof AbstractCompiler)) {
            LOG.log(DEBUG_LEVEL, "Cannot find a compiler for item {0}", // NOI18N
                    item.getName());
            return null;
        }
        AbstractCompiler compiler = (AbstractCompiler) compilerTool;
        if (compiler == null) {
            LOG.log(DEBUG_LEVEL, "Cannot find a compiler for item {0}", // NOI18N
                    item.getName());
            return null;
        }
        // 2.1 compiler path
        builder.addCommandItem(compiler.getPath());

        // 2.2 "-c" flag. This is currently hardcoded in NetBeans CND... :-(
        builder.addCommandItem("-c");

        // 2.3 The full path of the file to compile
        builder.addCommandItem(builder.getFile());

        // 2.3 language specific flags
        // C Options
        {
            CCompilerConfiguration cConfiguration = itemConfiguration.getCCompilerConfiguration();
            if (cConfiguration != null) {
                String cFlags = cConfiguration.getCFlags(compiler);
                builder.addCommandItem(cFlags);
                String allOptions = cConfiguration.getAllOptions2(compiler);
                builder.addCommandItem(allOptions);
                LOG.log(DEBUG_LEVEL, "CFLAGS {0} ALLOPTIONS {1}", new Object[]{cFlags, allOptions});
            }
        }

        // C++ options
        {
            CCCompilerConfiguration cppConfiguration = itemConfiguration.getCCCompilerConfiguration();
            if (cppConfiguration != null) {
                String cppFlags = cppConfiguration.getCCFlags(compiler);
                builder.addCommandItem(cppFlags);
                String allOptions = cppConfiguration.getAllOptions2(compiler);
                builder.addCommandItem(allOptions);
                LOG.log(DEBUG_LEVEL, "CPPFLAGS {0} ALLOPTIONS {1}", new Object[]{cppFlags, allOptions});
            }
        }

        // 3. output file
        BasicCompilerConfiguration basicCompilerConfiguration = itemConfiguration.getCompilerConfiguration();
        String outputFile = basicCompilerConfiguration.getOutputFile(item, activeMakeConfiguration, true);

        if (outputFile != null) {
            // outputFile is constructed with Makefile macros, like these:
            // "/home/antonio/tmp/${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}/main.o
            // We want to expand these
            outputFile = activeMakeConfiguration.expandMacros(outputFile);
            builder.setOutput(outputFile);

            // We also include a '-o outputfile' to the command
            builder.addCommandItem("-o");
            builder.addCommandItem(outputFile);
        }

        return builder.build();
    }

    @Override
    public boolean cancel() {
        this.cancelled = true;
        return true;
    }

}
