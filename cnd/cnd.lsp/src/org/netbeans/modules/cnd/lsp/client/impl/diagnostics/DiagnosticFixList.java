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
package org.netbeans.modules.cnd.lsp.client.impl.diagnostics;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import org.eclipse.lsp4j.CodeAction;
import org.eclipse.lsp4j.Command;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.netbeans.modules.cnd.lsp.client.impl.AbstractLanguageClient;
import org.netbeans.spi.editor.hints.ChangeInfo;
import org.netbeans.spi.editor.hints.Fix;
import org.netbeans.spi.editor.hints.LazyFixList;
import org.openide.filesystems.FileObject;

/**
 *
 * @author antonio
 */
public class DiagnosticFixList implements LazyFixList {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final FileObject fileObject;
    private final AbstractLanguageClient client;
    private final Diagnostic diagnostic;
    private List<Fix> fixes;
    private boolean computing;
    private boolean computed;

    public DiagnosticFixList(AbstractLanguageClient client, FileObject fileObject, Diagnostic diagnostic) {
        this.client = client;
        this.fileObject = fileObject;
        this.diagnostic = diagnostic;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    @Override
    public boolean probablyContainsFixes() {
        return true;
    }

    @Override
    public synchronized List<Fix> getFixes() {
        if (!computing && !computed) {
            computing = true;
//            bindings.runOnBackground(() -> {
//                try {
//                    List<Either<Command, CodeAction>> commands
//                            = bindings.getTextDocumentService().codeAction(new CodeActionParams(new TextDocumentIdentifier(fileUri),
//                                    diagnostic.getRange(),
//                                    new CodeActionContext(Collections.singletonList(diagnostic)))).get();
//                    List<Fix> fixes = commands.stream()
//                            .map(cmd -> new CommandBasedFix(cmd))
//                            .collect(Collectors.toList());
//                    synchronized (this) {
//                        this.fixes = Collections.unmodifiableList(fixes);
//                        this.computed = true;
//                        this.computing = false;
//                    }
//                    pcs.firePropertyChange(PROP_COMPUTED, null, null);
//                    pcs.firePropertyChange(PROP_FIXES, null, null);
//                } catch (InterruptedException | ExecutionException ex) {
//                    Exceptions.printStackTrace(ex);
//                }
//            });
        }
        return fixes;
    }

    @Override
    public synchronized boolean isComputed() {
        return computed;
    }

    private class CommandBasedFix implements Fix {

        private final Either<Command, CodeAction> cmd;

        public CommandBasedFix(Either<Command, CodeAction> cmd) {
            this.cmd = cmd;
        }

        @Override
        public String getText() {
            return cmd.isLeft() ? cmd.getLeft().getTitle() : cmd.getRight().getTitle();
        }

        @Override
        public ChangeInfo implement() throws Exception {
//            Utils.applyCodeAction(bindings, cmd);
            return null;
        }
    }

}
