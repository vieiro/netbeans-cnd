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
package org.netbeans.modules.cnd.lsp.client.api;

import java.beans.PropertyChangeListener;
import java.util.EnumSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;

/**
 * A LSP client. NOTE: This interface does not depend on LSP4J on purpose.
 *
 * @author antonio
 */
public interface LSPClient {

    /**
     * Returns the array of strings that are used to start the Process.
     * Subclasses are responsible for setting this.
     * 
     * @return The array of strings used to conform the command to start the
     * process, the first one must, of course, point to the LSP Server
     * executable
     */
    String[] getLSPServerStartCommands();

    /**
     * Returns the set of features this __client__ is interested in. A Client
     * may be interested in the completion feature, but not in the breadcrumbs one,
     * for instance.
     * @return The set of _client_ features we are interested in.
     */
    EnumSet<LSPFeatures> getClientFeatures();

    /**
     * Checks if the given FileObject is to be synchronized with the LSP Server,
     * if so the Project that holds the FileObject is returned.
     * @param fileObject The fileObject, may be null.
     * @return The Project that owns this FileObject, iff this FileObject is
     * to be synchronized with the LSP Server.
     */
    Project isResponsibleFor(FileObject fileObject);


    /**
     * The property name used for "status" PropertyChangeEvents
     */
    static final String PROPERTY_STATUS = "status";

    /**
     * Starts the LSP server and connects to it..
     *
     * @param process The LSP server process.
     * @return a CompletableFuture associated with the initialization task.
     * @throws InterruptedException If the initialization task was interrupted.
     * @throws ExecutionException If the task failed.
     */
    CompletableFuture<LSPServerStatus> start() throws InterruptedException, ExecutionException;

    /**
     * Stops the server.
     *
     * @return a CompletableFuture associated with the stop task.
     * @throws InterruptedException If the initialization task was interrupted.
     * @throws ExecutionException If the task failed.
     */
    CompletableFuture<LSPServerStatus> stop() throws InterruptedException, ExecutionException;

    /**
     * Returns the status of the client
     *
     * @return The status of the client
     */
    LSPServerStatus getStatus();

    /**
     * Adds a PropertyChangeListener to receive PropertyChangedEvents. Events
     * are always sent in the event dispatch thread.
     *
     * @param propertyChangeListener a PropertyChangeListener
     */
    void addPropertyChangeListener(PropertyChangeListener propertyChangeListener);

    /**
     * Removes a PropertyChangeListener that won't receive PropertyChangedEvents
     *
     * @param propertyChangeListener a PropertyChangeListener
     */
    void removePropertyChangeListener(PropertyChangeListener propertyChangeListener);

}
