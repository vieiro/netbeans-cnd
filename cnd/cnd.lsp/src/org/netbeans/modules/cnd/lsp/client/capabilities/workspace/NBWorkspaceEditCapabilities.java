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
package org.netbeans.modules.cnd.lsp.client.capabilities.workspace;

import java.util.Arrays;
import org.eclipse.lsp4j.FailureHandlingKind;
import org.eclipse.lsp4j.ResourceOperationKind;
import org.eclipse.lsp4j.WorkspaceEditCapabilities;
import org.eclipse.lsp4j.WorkspaceEditChangeAnnotationSupportCapabilities;

/**
 * @see
 * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#workspaceEditClientCapabilities">WorkspaceEditCapabilities</a>
 *
 * @author antonio
 */
public final class NBWorkspaceEditCapabilities extends WorkspaceEditCapabilities {

    public NBWorkspaceEditCapabilities() {
        setChangeAnnotationSupport(createChangeAnnotationSupport());
        // If we support versioned document changes
        setDocumentChanges(true);
        // 
        setFailureHandling(FailureHandlingKind.Undo);
        // If set to `true` the client will normalize line ending characters
	    // in a workspace edit to the client specific new line character(s).
        setNormalizesLineEndings(false);
        // https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#resourceOperationKind
        setResourceOperations(Arrays.asList(
                ResourceOperationKind.Create,
                ResourceOperationKind.Delete,
                ResourceOperationKind.Rename
        ));
    }

    private WorkspaceEditChangeAnnotationSupportCapabilities createChangeAnnotationSupport() {
        WorkspaceEditChangeAnnotationSupportCapabilities c = new WorkspaceEditChangeAnnotationSupportCapabilities();
        // If we want to group edit change annotations by label
        c.setGroupsOnLabel(false);
        return c;
    }

}
