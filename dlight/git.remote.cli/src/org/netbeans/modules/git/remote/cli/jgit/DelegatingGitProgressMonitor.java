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

package org.netbeans.modules.git.remote.cli.jgit;

import org.netbeans.modules.git.remote.cli.progress.ProgressMonitor;
import org.openide.util.Cancellable;

/**
 *
 */
public final class DelegatingGitProgressMonitor extends ProgressMonitor {
    private final ProgressMonitor monitor;
    private Cancellable cancellable;

    public DelegatingGitProgressMonitor (ProgressMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public synchronized final boolean isCanceled () {
        return monitor.isCanceled();
    }
    
    @Override
    public synchronized final void setCancelDelegate(Cancellable c) {
        cancellable = c;
    }

    @Override
    public synchronized final boolean cancel() {
        if (cancellable != null) {
            cancellable.cancel();
        }
        return monitor.cancel();
    }

    @Override
    public void started (String command) {
    }

    @Override
    public void finished () {
    }

    @Override
    public void preparationsFailed (String message) {
    }

    @Override
    public void notifyError (String message) {
    }

    @Override
    public void notifyWarning (String message) {
    }

    @Override
    public void beginTask (String title, int totalWork) {
        monitor.beginTask(title, totalWork);
    }

    @Override
    public void endTask () {
        monitor.endTask();
    }

    @Override
    public void updateTaskState (int completed) {
        monitor.updateTaskState(completed);
    }
}
