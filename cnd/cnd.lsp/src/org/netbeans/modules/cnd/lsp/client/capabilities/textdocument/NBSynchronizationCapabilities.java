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
package org.netbeans.modules.cnd.lsp.client.capabilities.textdocument;

import org.eclipse.lsp4j.SynchronizationCapabilities;

/**
 * @see
 * <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-16/#textDocument_synchronization">Text
 * Document Synchronization</a>
 * NOTE: These are mandatory (pre 3.0)
 * @author antonio
 */
public final class NBSynchronizationCapabilities extends SynchronizationCapabilities {

    /**
     * We send a 'will save' notification before saving
     */
    private static final boolean WILL_SAVE = true;
    /**
     * We send a 'will save' notification with some pending edits and await for
     * the server to respond. This, of course, may slow things down.
     */
    private static final boolean WILL_SAVE_WAIT_UNTIL = false;
    /**
     * We send a 'did save' notification after saving.
     */
    private static final boolean DID_SAVE = true;
    /**
     * We support dynamic registration.
     * @see <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#client_registerCapability">client/registerCapability</a>
     */
    private static final boolean DYNAMIC_REGISTRATION = true;

    public NBSynchronizationCapabilities() {
        super(WILL_SAVE, WILL_SAVE_WAIT_UNTIL, DID_SAVE, DYNAMIC_REGISTRATION);
    }

}
