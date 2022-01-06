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

package org.netbeans.modules.subversion.remote.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.netbeans.modules.subversion.remote.Subversion;
import org.netbeans.modules.subversion.remote.client.SvnProgressSupport;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;

/**
 *
 * 
 */
public class ClientCheckSupport {

    private static ClientCheckSupport instance;
    private final RequestProcessor rp;
    private static final Logger LOG = Logger.getLogger(ClientCheckSupport.class.getName());

    public static synchronized ClientCheckSupport getInstance() {
        if (instance == null) {
            instance = new ClientCheckSupport();
        }
        return instance;
    }

    private ClientCheckSupport() {
        this.rp = new RequestProcessor("SvnClientCheckRP", 1, true); //NOI18N
    }

    /**
     * Asynchronously checks client's availability and then starts a given runnable in AWT,
     * @param progressName name of a progress displayed in the progress bar, may be null
     * @param runnable runnable started if a client is available
     */
    public void runInAWTIfAvailable (final Node[] nodes, final String progressName, final Runnable runnable) {
        final Context ctx = SvnUtils.getCurrentContext(nodes);
        SvnProgressSupport supp = new SvnProgressSupport(ctx.getFileSystem()) {
            @Override
            protected void perform() {
                setDisplayName(NbBundle.getMessage(ClientCheckSupport.class, "MSG_ClientCheckSupport.progressDescription")); //NOI18N
                if (!Subversion.getInstance().checkClientAvailable(ctx)) {
                    LOG.log(Level.FINE, "Client is unavailable, cannot perform {0}", progressName); //NOI18N
                    return;
                }
                if (!isCanceled()) {
                    SwingUtilities.invokeLater(runnable);
                }
            }
        };
        supp.start(rp, null, progressName == null ? NbBundle.getMessage(ClientCheckSupport.class, "MSG_ClientCheckSupport.progressDescription") : progressName); //NOI18N
    }

}
