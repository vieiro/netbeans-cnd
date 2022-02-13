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

import org.netbeans.modules.cnd.lsp.client.api.LSPServerStatus;
import java.awt.Dialog;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.eclipse.lsp4j.ClientCapabilities;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.InitializedParams;
import org.eclipse.lsp4j.MessageActionItem;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.ShowMessageRequestParams;
import org.eclipse.lsp4j.TraceValue;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.jsonrpc.RemoteEndpoint;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageServer;
import org.netbeans.modules.cnd.lsp.client.api.LSPFeatures;
import org.netbeans.modules.cnd.lsp.client.capabilities.NBClientCapabilities;
import org.netbeans.modules.cnd.lsp.client.impl.diagnostics.ApplyDiagnosticsOnEDT;
import org.netbeans.modules.cnd.lsp.client.impl.mappers.MessageTypeMapper;
import org.netbeans.modules.cnd.lsp.client.impl.textdocument.ClientTextDocumentService;
import org.netbeans.modules.cnd.lsp.client.impl.ui.ShowMessageRequestPanel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 * A NetBeans specific LSP Client. Subclasses are responsible for LSP-Server
 * specific details (such as the LSP server command to execute, for instance).
 * NOTE: This class does depend on LSP4J.
 *
 * @author antonio
 */
public abstract class AbstractLanguageClient
        extends AbstractLSPClient
        implements LanguageClient {

    private static final Logger LOG = Logger.getLogger(AbstractLanguageClient.class.getName());

    protected Process process;
    protected File stderr;
    private Launcher<LanguageServer> server;
    private LanguageServer languageServer;
    private RemoteEndpoint remoteEndpoint;
    private InputStream input;
    private OutputStream output;
    private InitializeResult initializeResult;
    private ClientTextDocumentService clientTextDocumentService;

    /**
     * Creates an instance that logs to NetBeans log.
     */
    public AbstractLanguageClient() {
        this(null);
    }

    /**
     * Creates an instance with an optional log file.
     *
     * @param stderrLogFile The log file where the LSP stderr is to be stored,
     * or null to redirect to NetBeans log.
     */
    public AbstractLanguageClient(File stderrLogFile) {
        this.stderr = stderrLogFile;
    }

    @Override
    public CompletableFuture<LSPServerStatus> start() throws InterruptedException, ExecutionException {
        return submit(this::initialize);
    }

    @Override
    public CompletableFuture<LSPServerStatus> stop() throws InterruptedException, ExecutionException {
        return submit(this::stopProcess);
    }

    /**
     * Starts the process and sends "initialize" (with capabilities) and
     * "initialized" LSP messages.
     *
     * @return The final status
     * @throws Exception on error.
     */
    protected LSPServerStatus initialize() throws Exception {
        if (status == LSPServerStatus.STARTED) {
            LOG.log(Level.INFO, "Stopping previously started process");
            stopProcess();
        }
        if (status == LSPServerStatus.STARTING) {
            LOG.log(Level.INFO, "LSP client already starting.");
            return LSPServerStatus.STARTING;
        }
        LOG.info("LSP client starting");
        setStatus(LSPServerStatus.STARTING);
        try {
            String[] commands = getLSPServerStartCommands();
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            if (stderr != null) {
                LOG.log(Level.INFO, "LSP client process redirecting to {0}", stderr.getAbsolutePath());
                processBuilder.redirectError(stderr);
            } else {
                LOG.info("LSP client starting redirecting stderr to IDE log");
                processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
            }
            this.process = processBuilder.start();
            this.input = process.getInputStream();
            this.output = process.getOutputStream();

            server = LSPLauncher.createClientLauncher(this, this.input, this.output);
            server.startListening();
            LOG.log(Level.INFO, "Server listening");

            languageServer = server.getRemoteProxy();
            remoteEndpoint = server.getRemoteEndpoint();

            InitializeParams initializeServerParams = new InitializeParams();
            // PID Only in Java 9 initializeServerParams.setProcessId((int) ProcessHandle.current().pid());
            initializeServerParams.setTrace(TraceValue.Verbose);

            EnumSet<LSPFeatures> clientFeatures = EnumSet.allOf(LSPFeatures.class);
            ClientCapabilities clientCapabilities = new NBClientCapabilities(clientFeatures);
            initializeServerParams.setCapabilities(clientCapabilities);
            LOG.log(Level.INFO, "Initialize");
            initializeResult = languageServer.initialize(initializeServerParams).get();
            LOG.log(Level.INFO, String.format(
                    "(T: %s) Server responded to initalize: %s%n", Thread.currentThread().toString(), initializeResult));

            InitializedParams clientInitializedParams = new InitializedParams();
            languageServer.initialized(clientInitializedParams);

            clientTextDocumentService = new ClientTextDocumentService(this);

            LOG.info("LSP client started");
            setStatus(LSPServerStatus.STARTED);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "LSP client start failed", e);
            setStatus(LSPServerStatus.STOPPED);
            throw e;
        }
        return status;
    }

    /**
     * Shutdowns the LSP server and destroys the underlying process.
     *
     * @return The final status
     * @throws Exception if an error happens.
     */
    protected LSPServerStatus stopProcess() throws Exception {
        if (process.isAlive()) {
            try {
                clientTextDocumentService.stop();
                clientTextDocumentService = null;
                languageServer.shutdown().get();
                languageServer.exit();
            } finally {
                if (process.isAlive()) {
                    try {
                        process.destroy();
                    } catch (Exception e) {
                        // Empty
                    }
                }
                setStatus(LSPServerStatus.STOPPED);
                process = null;
            }
        }
        return status;
    }

    /**
     * Log a message sent by the LSP server.
     *
     * @param message The message.
     */
    @Override
    public void logMessage(MessageParams messageParams) {
        Level level = MessageTypeMapper.getInstance().messageType2Level(messageParams.getType());
        String logMessage = String.format("LSP SERVER: %s", messageParams.getMessage());
        LOG.log(level, logMessage);
    }

    /**
     * The show message notification is sent from a server to a client to ask
     * the client to display a particular message in the user interface.
     *
     * @param messageParams The message
     */
    @Override
    public void showMessage(MessageParams messageParams) {
        String message = "LSP Server: " + messageParams.getMessage(); // NOI18N
        int nbMessageType = MessageTypeMapper.getInstance().messageType2NotifyDescriptorType(messageParams.getType());
        NotifyDescriptor nd = new NotifyDescriptor.Message(message, nbMessageType);
        DialogDisplayer.getDefault().notifyLater(nd);
    }

    /**
     * Sent by the server to show a list of actions to the user, and wait for an
     * answer from the user.
     *
     * @param requestParams The parameters
     * @return The chosen MessageActionItem
     */
    @Override
    public CompletableFuture<MessageActionItem> showMessageRequest(ShowMessageRequestParams requestParams) {
        final String message = "LSP Server: " + requestParams.getMessage(); // NOI18N
        final List<MessageActionItem> actions = requestParams.getActions();
        final ArrayList<MessageActionItem> selectedAction = new ArrayList<>();

        Callable<MessageActionItem> task = new Callable<MessageActionItem>() {
            @Override
            public MessageActionItem call() throws Exception {
                SwingUtilities.invokeAndWait(() -> {
                    ShowMessageRequestPanel panel = new ShowMessageRequestPanel(message, actions);
                    DialogDescriptor descriptor = new DialogDescriptor(panel, message, true, null);
                    Dialog dialog = DialogDisplayer.getDefault().createDialog(descriptor);
                    dialog.setVisible(true);
                    Object dialogButton = descriptor.getValue();
                    if (DialogDescriptor.OK_OPTION.equals(dialogButton)) {
                        MessageActionItem item = panel.getSelectedMessageActionItem();
                        if (item != null) {
                            selectedAction.add(item);
                        }
                    }
                });
                return selectedAction.isEmpty() ? null : selectedAction.get(0);
            }
        };

        return submit(task);
    }

    /**
     * This event is sent from the server to log a telemetry event. The exact
     * type of the argument is not specified. Most clients even don’t handle the
     * event directly but forward them to the extensions owing the corresponding
     * server issuing the event.
     *
     * @param object The data received from the server.
     */
    @Override
    public void telemetryEvent(Object object) {
        // TODO: See if we want to forward to extensions in the future
        LOG.log(Level.INFO, "LSP Server Telemetry: {0}", object);
    }

    /**
     * Sent by the server with information about validations. Diagnostics are
     * “owned” by the server so it is the server’s responsibility to clear them
     * if necessary.
     *
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#publishDiagnosticsParams">PublishDiagnosticsParams</a>
     * @see
     * <a href="https://microsoft.github.io/language-server-protocol/specification.html#diagnostic">Diagnostic
     * in the LSP specification</a>
     *
     * @param diagnostics
     */
    @Override
    public void publishDiagnostics(PublishDiagnosticsParams diagnostics) {
        if (! getClientFeatures().contains(LSPFeatures.DIAGNOSTICS)) {
            ApplyDiagnosticsOnEDT applyDiagnosticsTask = new ApplyDiagnosticsOnEDT(this, diagnostics);
            SwingUtilities.invokeLater(applyDiagnosticsTask);
        }
    }

    public LanguageServer getLanguageServer() {
        return languageServer;
    }

    public ServerCapabilities getServerCapabilities() {
        return initializeResult.getCapabilities();
    }

}
