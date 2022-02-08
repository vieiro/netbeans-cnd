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
package org.netbeans.modules.cnd.lsp.client.impl;

import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.swing.event.SwingPropertyChangeSupport;

/**
 * A LSPClient with property change support and background thread.
 * NOTE: This class does not depend on LSP4J on purpose.
 *
 * @author antonio
 */
public abstract class AbstractLSPClient implements LSPClient {

    private static final Logger LOG = Logger.getLogger(AbstractLSPClient.class.getName());

    protected final SwingPropertyChangeSupport propertyChangeSupport;
    protected final ExecutorService executorService;
    protected LSPServerStatus status;

    public AbstractLSPClient() {
        // All property changes are sent in the Event Dispatch Thread.
        this.propertyChangeSupport = new SwingPropertyChangeSupport(this);
        this.status = LSPServerStatus.STOPPED;
        // All request to the server are performed in a single-thread.
        this.executorService = Executors.newSingleThreadExecutor();
    }

    protected synchronized void setStatus(LSPServerStatus newStatus) {
        if (newStatus != status) {
            LSPServerStatus oldStatus = status;
            status = newStatus;
            propertyChangeSupport.firePropertyChange(PROPERTY_STATUS, oldStatus, status);
        }
    }

    @Override
    public LSPServerStatus getStatus() {
        return status;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
    }

    /**
     * Used to send messages to the LSP server in a background thread.
     * NOTE: All LSP4J request must be done in a task for proper sequencing.
     * @param <T> The type of the response expected.
     * @param task The task to perform in a background thread without blocking
     *   the calling thread.
     * @return A CompletableFuture used to process the response asynchronously.
     */
    protected <T> CompletableFuture<T> submit(Callable<T> task) {

        final Future<T> future = executorService.submit(task);

        final CompletableFuture<T> completableFuture = new CompletableFuture<>();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (!completableFuture.isDone()
                        && !completableFuture.isCancelled()
                        && !completableFuture.isCompletedExceptionally()) {
                    try {
                        T result = future.get();
                        completableFuture.complete(result);
                    } catch (Exception e) {
                        completableFuture.completeExceptionally(e);
                    }
                }
            }
        });

        return completableFuture;
    }

}
