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

package org.netbeans.modules.subversion.remote.ui.history;

import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import javax.swing.*;
import java.util.*;
import org.netbeans.modules.remotefs.versioning.api.VCSFileProxySupport;
import org.netbeans.modules.subversion.remote.FileInformation;
import org.netbeans.modules.subversion.remote.Subversion;
import org.netbeans.modules.subversion.remote.api.SVNUrl;
import org.netbeans.modules.subversion.remote.ui.actions.ContextAction;
import org.netbeans.modules.subversion.remote.util.Context;
import org.netbeans.modules.subversion.remote.util.SvnUtils;
import org.netbeans.modules.versioning.core.api.VCSFileProxy;

/**
 * Opens Search History Component.
 * 
 * 
 */
public class SearchHistoryAction extends ContextAction {

    static final int DIRECTORY_ENABLED_STATUS = FileInformation.STATUS_MANAGED & ~FileInformation.STATUS_NOTVERSIONED_EXCLUDED & ~FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY;
    static final int FILE_ENABLED_STATUS = FileInformation.STATUS_MANAGED & ~FileInformation.STATUS_NOTVERSIONED_EXCLUDED & ~FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY;
    private static final String ICON_RESOURCE = "org/netbeans/modules/subversion/remote/resources/icons/search_history.png"; //NOI18N

    public SearchHistoryAction () {
        super(ICON_RESOURCE);
    }

    @Override
    protected String iconResource () {
        return ICON_RESOURCE;
    }
    
    @Override
    protected String getBaseName(Node [] activatedNodes) {
        return "CTL_MenuItem_SearchHistory"; // NOI18N
    }

    @Override
    protected int getFileEnabledStatus() {
        return FILE_ENABLED_STATUS;
    }

    @Override
    protected int getDirectoryEnabledStatus() {
        return DIRECTORY_ENABLED_STATUS;
    }
    
    @Override
    protected boolean asynchronous() {
        return false;
    }

    @Override
    protected void performContextAction(Node[] nodes) {
        final Context context = getContext(nodes);
        if(!Subversion.getInstance().checkClientAvailable(context)) {            
            return;
        }        
        openHistory(context.getFiles(), NbBundle.getMessage(SearchHistoryAction.class, "CTL_SearchHistory_Title", getContextDisplayName(nodes)));
    }

    public static void openHistory(VCSFileProxy [] files) {
        openHistory(files, NbBundle.getMessage(SearchHistoryAction.class, "CTL_SearchHistory_Title", files[0].getName()));
    }
    
    private static void openHistory(final VCSFileProxy [] files, final String title) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SearchHistoryTopComponent tc = new SearchHistoryTopComponent(files);
                tc.setDisplayName(title); 
                tc.open();
                tc.requestActive();
                if (files.length == 1 && files[0].isFile() || files.length > 1 && VCSFileProxySupport.shareCommonDataObject(files)) {
                    tc.search(false);
                }
            }
        });
    }

    /**
     * Opens search panel with a diff view fixed on a line
     * @param file file to search history for
     * @param lineNumber number of a line to fix on
     */
    public static void openSearch(final VCSFileProxy file, final int lineNumber) {
        SearchHistoryTopComponent tc = new SearchHistoryTopComponent(file, new SearchHistoryTopComponent.DiffResultsViewFactory() {
            @Override
            DiffResultsView createDiffResultsView(SearchHistoryPanel panel, List<RepositoryRevision> results) {
                return new DiffResultsViewForLine(panel, results, lineNumber);
            }
        });
        String tcTitle = NbBundle.getMessage(SearchHistoryAction.class, "CTL_SearchHistory_Title", file.getName()); // NOI18N
        tc.setDisplayName(tcTitle);
        tc.open();
        tc.requestActive();
        tc.search(true);
        tc.activateDiffView(true);
    }

    private static Context getDefaultContext() {
        Project [] projects = OpenProjects.getDefault().getOpenProjects();
        return SvnUtils.getProjectsContext(projects);
    }

    /**
     * Opens search panel in the context of the given repository URL.
     * 
     * @param repositoryUrl URL to search
     * @param localRoot local working copy root that corresponds to the repository URL 
     * @param revision revision to search for
     */ 
    public static void openSearch(SVNUrl repositoryUrl, VCSFileProxy localRoot, long revision) {
        SearchHistoryTopComponent tc = new SearchHistoryTopComponent(repositoryUrl, localRoot, revision);
        String tcTitle = NbBundle.getMessage(SearchHistoryAction.class, "CTL_SearchHistory_Title", repositoryUrl); // NOI18N
        tc.setDisplayName(tcTitle);
        tc.open();
        tc.requestActive();
        tc.search(false);
    }
}
