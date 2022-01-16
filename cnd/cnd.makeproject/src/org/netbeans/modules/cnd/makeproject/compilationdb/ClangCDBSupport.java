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

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.modules.cnd.api.project.NativeFileItem;
import org.netbeans.modules.cnd.api.project.NativeProject;
import org.netbeans.modules.cnd.api.project.NativeProjectItemsListener;
import org.netbeans.modules.cnd.makeproject.api.MakeProject;
import org.netbeans.modules.cnd.makeproject.api.support.MakeProjectEvent;
import org.netbeans.modules.cnd.makeproject.api.support.MakeProjectListener;
import org.openide.util.RequestProcessor;

/**
 * ClangCDBSupport is responsible for detecting and generating a JSON
 * compilation database, as defined in
 * https://clang.llvm.org/docs/JSONCompilationDatabase.html
 *
 */
public final class ClangCDBSupport
        implements
        NativeProjectItemsListener, MakeProjectListener {

    private static final Logger LOG = Logger.getLogger(ClangCDBSupport.class.getName());
    private static final Level DEBUGLEVEL = Level.INFO;
    private static final RequestProcessor CLANG_CDB_PROCESSOR = new RequestProcessor(ClangCDBSupport.class.getName(), 2);

    private final MakeProject makeProject;
    private final NativeProject nativeProject;
    private Future<Void> runningTask;

    public ClangCDBSupport(final MakeProject makeProject) {
        this.makeProject = makeProject;
        this.nativeProject = makeProject.getLookup().lookup(NativeProject.class);

        // Start listening
        this.makeProject.getHelper().addMakeProjectListener(this);
        this.nativeProject.addProjectItemsListener(this);

        // Fire a "PROJECT_OPENED" task, within a second or so
        ClangCDBGenerationTask projectOpenedTask
                = new ClangCDBGenerationTask(makeProject, ClangCDBGenerationCause.PROJECT_OPENED);
        runningTask = CLANG_CDB_PROCESSOR.schedule(projectOpenedTask, 1500, TimeUnit.MILLISECONDS);
    }

    @Override
    public void filesAdded(List<NativeFileItem> fileItems) {
        updateCompilationDatabase(ClangCDBGenerationCause.FILES_ADDED);
    }

    @Override
    public void filesRemoved(List<NativeFileItem> fileItems) {
        updateCompilationDatabase(ClangCDBGenerationCause.FILES_REMOVED);
    }

    @Override
    public void filesPropertiesChanged(List<NativeFileItem> fileItems) {
        LOG.log(DEBUGLEVEL, "Files properties changed (saved?) {0}", // NOI18N
                fileItems);
    }

    @Override
    public void filesPropertiesChanged(NativeProject nativeProject) {
        LOG.log(DEBUGLEVEL, "Project properties changed (saved?) {0}", // NOI18N
                nativeProject.getProjectDisplayName());
    }

    @Override
    public void fileRenamed(String oldPath, NativeFileItem newFileIetm) {
        updateCompilationDatabase(ClangCDBGenerationCause.FILES_RENAMED);
    }

    @Override
    public void projectDeleted(NativeProject nativeProject) {
        nativeProject.removeProjectItemsListener(this);
    }

    @Override
    public void fileOperationsStarted(NativeProject nativeProject) {
        LOG.log(DEBUGLEVEL, "File operations started."); // NOI18N
    }

    @Override
    public void fileOperationsFinished(NativeProject nativeProject) {
        LOG.log(DEBUGLEVEL, "File operations finished."); // NOI18N
    }

    @Override
    public void configurationXmlChanged(MakeProjectEvent ev) {
        updateCompilationDatabase(ClangCDBGenerationCause.PROJECT_CONFIGURATION_CHANGED);
    }

    @Override
    public void propertiesChanged(MakeProjectEvent ev) {
        LOG.log(DEBUGLEVEL, "Some project properties changed {0}", // NOI18N
                ev.getPath());
    }

    /**
     * Updates the compilation database "[project]/compile_commands.json". If a
     * task is already running then the request is silently ignored.
     *
     * @param cause The cause that originated the need to recreate the
     * compilation database.
     */
    private void updateCompilationDatabase(ClangCDBGenerationCause cause) {
        if (runningTask != null && !runningTask.isDone() && !runningTask.isCancelled()) {
            LOG.log(DEBUGLEVEL, "Wanted to update CDB because of {0} but a task is already running", cause.name());
            return;
        }
        ClangCDBGenerationTask task = new ClangCDBGenerationTask(makeProject, cause);
        runningTask = CLANG_CDB_PROCESSOR.submit(task);
    }
}
