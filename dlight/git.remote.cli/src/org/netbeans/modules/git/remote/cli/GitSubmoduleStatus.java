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
package org.netbeans.modules.git.remote.cli;

import org.netbeans.modules.versioning.core.api.VCSFileProxy;

/**
 * Describes current status of a repository's submodule
 *
 * @since 1.16
 */
public final class GitSubmoduleStatus {

    //private final SubmoduleStatus delegate;
    private final StatusType statusType;
    private final VCSFileProxy folder;

    /**
     * Submodule's status
     */
    public enum StatusType {

        /**
         * Submodule's configuration is missing
         */
        MISSING,
        /**
         * Submodule's Git repository is not initialized
         */
        UNINITIALIZED,
        /**
         * Submodule's Git repository is initialized
         */
        INITIALIZED,
        /**
         * Submodule checked out commit is different than the commit referenced
         * in the index tree
         */
        REV_CHECKED_OUT;
    }

    GitSubmoduleStatus (/*SubmoduleStatus delegate,*/ VCSFileProxy folder) {
        //this.delegate = delegate;
        this.folder = folder;
        this.statusType = StatusType.UNINITIALIZED; //parseStatus(delegate.getType());
    }

    GitSubmoduleStatus (StatusType status, VCSFileProxy folder) {
        //this.delegate = delegate;
        this.folder = folder;
        this.statusType = status;
    }

    /**
     * Returns status of the submodule
     *
     * @return submodule's status
     */
    public StatusType getStatus () {
        return statusType;
    }

    /**
     * Returns the submodule's root folder.
     *
     * @return submodule's root folder.
     */
    public VCSFileProxy getSubmoduleFolder () {
        return folder;
    }

    /**
     * Returns the commit id of the currently checked-out commit.
     *
     * @return submodule's commit id.
     */
    public String getHeadId () {
        return GitConstants.HEAD;
        //return delegate.getHeadId().getName();
    }

    /**
     * Returns the commit id of the submodule entry, in other words the
     * referenced commit.
     *
     * @return submodule's referenced commit id.
     */
    public String getReferencedCommitId () {
        return GitConstants.HEAD;
        //return delegate.getIndexId().getName();
    }

//    private StatusType parseStatus (SubmoduleStatusType status) {
//        return StatusType.valueOf(status.name());
//    }
}
