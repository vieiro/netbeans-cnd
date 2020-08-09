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

package org.netbeans.modules.remotefs.versioning.api;

import java.util.logging.Logger;

/**
 * Encrypts user's passwords.
 * Previous versions of NetBeans used the "Scrambler" class to obfuscate 
 * user passwords in connection strings, for instance in Mercurial repository
 * connection strings.
 * 
 */
public final class Scrambler {

    private static Scrambler instance = null;

    private Scrambler() {
    }

    /**
     * Returns the unique instance of this class.
     * @return The unique instance of Scrambler, for all threads.
     */
    public static Scrambler getInstance() {
        if (instance == null) {
            instance = new Scrambler();
        }
        return instance;
    }

    /**
     * Descrambles a scrambled password.
     * Current implementation in Apache NetBeans just throws an exception.
     * NOTE: This method must be thread safe.
     * @param scrambledPassword The scrambled password.
     * @return Currently, this method always throws an exception.
     */
    public String descramble(String scrambledPassword) {
        // See https://issues.apache.org/jira/browse/NETBEANS-4662
        String message = org.openide.util.NbBundle.getMessage(
                        Scrambler.class, "MSG_NETBEANS_4662"); // NOI18N
        throw new UnsupportedOperationException(message);
    }

}
