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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.netbeans.modules.cnd.clang.api.Index;

/**
 * Represents a CXTranslationUnit.
 * We want CXTranslationUnit to be lazily initialized.
 * CXTranslationUnits have many different states, represented with CXTranslationUnitState.
 */
public final class CXTranslationUnit {

    /**
     * The state of a CXTranslationUnit.
     * This is hard to find in the libclang documentation. For instance, when one
     * reparses a CXTranslationUnit and the reparsing fails, then the CXTranslationUnit
     * must be destroyed:
     * <pre>
     * "Returns: 0 if the sources could be reparsed. A non-zero error code will be 
     * returned if reparsing was impossible, such that the translation unit is invalid. 
     * In such cases, the only valid call for TU is clang_disposeTranslationUnit(TU). 
     * The error codes returned by this routine are described by the CXErrorCode enum."
     * </pre>
     * @see <a href="https://clang.llvm.org/doxygen/group__CINDEX__TRANSLATION__UNIT.html#details">CXTranslationUnit details</a>
     */
    public static enum CXTranslationUnitState {
        /**
         * The CXTranslationUnit does not exist yet (i.e., it's NULL)
         * and as a consequence it consumes very little resources.
         */
        NOT_INITIALIZED,
        /**
         * The CXTranslationUnit is invalid because it was reparsed before and
         * it did return a non-zero return code. It must be recreated.
         */
        INVALID,
        /**
         * The CXTranslationUnit uses significantly less memory but on the other 
         * side does not support any other calls than clang_reparseTranslationUnit 
         * to resume it or clang_disposeTranslationUnit to dispose it completely. 
         */
        SUSPENDED,

    }

    public static native void deallocate(long pointer);

    public static long allocate(Index index, String absolutePath, ArrayList<String> compilationFlags) throws IOException {
        long indexPointer = index.getPointer();

        List<String> flags = new ArrayList<>(index.getIncludeDirectories().size() * 2);
        index.getIncludeDirectories().stream()
                .forEach((includeDirectory) -> {
                    flags.add("-I");
                    flags.add(includeDirectory.getAbsolutePath());
                });

        flags.addAll(compilationFlags);

        byte[][] flagCStrings = flags.stream()
                .map(NativeUtils::toConstCharZ)
                .collect(Collectors.toList())
                .toArray(new byte[flags.size()][]);

        try (CXUnsavedFileList unsavedFiles = new CXUnsavedFileList(index)) {
            return allocate(index.getPointer(), 
                    NativeUtils.toConstCharZ(absolutePath),
                    unsavedFiles.getPointer(), 
                    unsavedFiles.getNumberOfUnsavedBuffers(),
                    flagCStrings
            );
        }

    }

    private static native long allocate(
            long indexPointer,
            byte [] absolutePath,
            long cxUnsavedFileListPointer,
            int numberOfUnsavedBuffers,
            byte[][] cFlags);

}
