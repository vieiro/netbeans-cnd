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
package org.netbeans.modules.mercurial.remote.ui.status;

import java.util.Calendar;
import java.util.logging.Level;
import org.netbeans.modules.mercurial.remote.FileStatusCache;
import org.netbeans.modules.mercurial.remote.Mercurial;
import org.netbeans.modules.versioning.core.spi.VCSContext;
import org.netbeans.modules.mercurial.remote.HgProgressSupport;
import org.netbeans.modules.mercurial.remote.util.HgUtils;
import org.netbeans.modules.mercurial.remote.ui.actions.ContextAction;
import org.netbeans.modules.remotefs.versioning.api.VCSFileProxySupport;
import org.netbeans.modules.versioning.core.api.VCSFileProxy;
import org.openide.nodes.Node;

/**
 * Status action for mercurial: 
 * hg status - show changed files in the working directory
 * 
 * 
 */
public class StatusAction extends ContextAction {
    
    private static final String ICON_RESOURCE = "org/netbeans/modules/mercurial/remote/resources/icons/show_changes.png"; //NOI18N

    public StatusAction () {
        super(ICON_RESOURCE);
    }

    @Override
    protected String iconResource () {
        return ICON_RESOURCE;
    }
    
    @Override
    public boolean enable (Node[] nodes) {
        VCSContext context = HgUtils.getCurrentContext(nodes);
        return HgUtils.isFromHgRepository(context);
    }

    @Override
    protected String getBaseName(Node[] nodes) {
        return "CTL_MenuItem_ShowChanges"; // NOI18N
    }

    @Override
    public void performContextAction (Node[] nodes) {
        VCSContext context = HgUtils.getCurrentContext(nodes);

        VCSFileProxy [] files = context.getRootFiles().toArray(new VCSFileProxy[context.getRootFiles().size()]);
        if (files == null || files.length == 0) {
            return;
        }
                
        final HgVersioningTopComponent stc = HgVersioningTopComponent.findInstance();
        stc.setContentTitle(VCSFileProxySupport.getContextDisplayName(context)); 
        stc.setContext(context);
        stc.open(); 
        stc.requestActive();
        stc.performRefreshAction();
    }

    /**
     * Connects to repository and gets recent status.
     */
    public static void executeStatus(final VCSContext context, HgProgressSupport support) {
        if (context == null || context.getRootFiles().isEmpty()) {
            return;
        }

        FileStatusCache cache = Mercurial.getInstance().getFileStatusCache();
        Calendar start = Calendar.getInstance();
        cache.refreshAllRoots(context.getRootFiles());
        Calendar end = Calendar.getInstance();
        Mercurial.STATUS_LOG.log(Level.FINE, "executeStatus: refreshCached took {0} millisecs", end.getTimeInMillis() - start.getTimeInMillis()); // NOI18N
    }
}
