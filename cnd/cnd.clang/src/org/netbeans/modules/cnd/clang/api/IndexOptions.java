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
package org.netbeans.modules.cnd.clang.api;

/**
 * Options used to create an Index.
 *
 * @see
 * <a href="https://clang.llvm.org/doxygen/Index_8h_source.html#l00277">CXGlobalOptFlags</a>
 */
public enum IndexOptions {
    /**
     * No special options are required.
     */
    NONE(0x00), 
    /**
     * Threads used for indexing should have background priority. (Affects
     * parseTranslationUnit, indexSourceFile, ...)
     */
    THREAD_BACKGROUND_PRIORITY_FOR_INDEXING(0x01), /**
     * Threads used for editing should have background priority. (Affects
     * reparseTranslationUnit, codeCompleteAt, etc.).
     */
    THREAD_BACKGROUND_PRIORITY_FOR_EDITING(0x02),
    /**
     * Both THREAD_BACKGROUND_PRIORITY_FOR_INDEXING and THREAD_BACKGROUND_PRIORITY_FOR_EDITING.
     */
    THREAD_BACKGROUND_PRIORITY_FOR_ALL(0x01 | 0x02);

    /**
     * The native value of mask (see CXGlobalOptFlags)
     */
    public final int mask;

    private IndexOptions(int mask) {
        this.mask = mask;
    }

}
