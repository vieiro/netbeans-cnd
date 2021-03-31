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

import java.nio.charset.StandardCharsets;

/**
 * Scrambles/descrambles user passwords with the following algorithm:
 * http://www.cvsnt.org/cvsclient/Password-scrambling.html
 * NOTE: The algorithm is not clear at all, so we scrambled US-ASCII[32,126]
 * and generated a one-to-one relationship with scrambled counterparts. We don't
 * contemplate user specific encodings.
 */
public final class Scrambler {

    /**
     * Characters in range [32,126].
     */
    static final byte[] CLEAR = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".getBytes(StandardCharsets.UTF_8);
    /**
     * Scrambled counterparts of CLEAR.
     */
    static final byte[] SCRAMBLED = "rx5O`mHlF@LCtJDWo4Kw1\"RQ_ApVvnzi)9S+.f(Y&g-2*{[#}76B|~;/\\GsNXkj8$yuhedEIc?^]'%=0:q Z,b<3!a>MTPU".getBytes(StandardCharsets.US_ASCII);

    private static final byte descramble(byte b) {
        for (int i = 0; i < SCRAMBLED.length; i++) {
            if (b == SCRAMBLED[i]) {
                return CLEAR[i];
            }
        }
        throw new IllegalArgumentException("Invalid scrambled byte '" + b + "'");
    }

    private static final byte scramble(byte b) {
        for (int i = 0; i < CLEAR.length; i++) {
            if (b == CLEAR[i]) {
                return SCRAMBLED[i];
            }
        }
        throw new IllegalArgumentException("Invalid descrambled byte '" + b + "' (" + ((char)b) + ")");
    }


    /**
     * Returns a _new_ instance of a Scrambler.
     *
     * @return a thread safe instance of a Scrambler.
     */
    public static Scrambler getInstance() {
        return new Scrambler();
    }

    /**
     * Given a scrambled password, this method descrambles it.
     *
     * @param scrambled the scrambled password.
     * @return The descrambled password.
     * @throws IllegalArgumentException if the input string contains invalid
     * characters.
     */
    public String descramble(String scrambled) {
        byte[] bytes = scrambled.getBytes(StandardCharsets.US_ASCII);
        if (bytes[0] != 'A') {
            throw new IllegalArgumentException("scrambled passwords must start with 'A'");
        }
        byte[] descrambled = new byte[bytes.length - 1];
        for (int i = 0; i < descrambled.length; i++) {
            descrambled[i] = descramble(bytes[i+1]);
        }
        return new String(descrambled, 0, descrambled.length, StandardCharsets.US_ASCII);
    }

    /**
     * Givem a descrambled password (with US-ASCII charset) this method
     * scrambles it.
     *
     * @param clear The descrambled password.
     * @return The scrambled password.
     */
    public String scramble(String clear) {
        byte[] bytes = clear.getBytes(StandardCharsets.US_ASCII);
        byte[] scrambled = new byte[bytes.length + 1];
        scrambled[0] = 'A';
        for (int i = 0; i < bytes.length; i++) {
            scrambled[i+1] = scramble(bytes[i]);
        }
        return new String(scrambled, 0, scrambled.length, StandardCharsets.US_ASCII);
    }

}
