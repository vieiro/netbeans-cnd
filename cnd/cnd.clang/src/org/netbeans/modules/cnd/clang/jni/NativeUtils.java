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
package org.netbeans.modules.cnd.clang.jni;

import java.nio.charset.Charset;

/**
 * Utilities to invoke the native layer.
 */
public final class NativeUtils {


    /**
     * Returns a byte[] (not terminated with '\0' with the bytes
     * of the given string in the platform's default encoding.
     * @param text The text.
     * @return The byte[] of the text wth the platform's default encoding.
     */
    public static byte [] toBuffer(String text) {
        byte [] textBytes = text.getBytes(Charset.defaultCharset());
        return textBytes;
    }

    /**
     * Returns a byte[] corresponding to a zero-ended C string.
     * It uses the default platform encoding.
     * @param text The text.
     * @return A '\0' ended byte[] with the bytes of the string in 
     * the system's default encoding.
     */
    public static byte[] toConstCharZ(String text) {
        byte [] textBytes = toBuffer(text);
        byte [] bytes = new byte[textBytes.length + 1];
        System.arraycopy(textBytes, 0, bytes, 0, textBytes.length);
        bytes[textBytes.length] = '\0';
        return bytes;
    }
    
}
