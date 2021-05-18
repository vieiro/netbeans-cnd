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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import org.netbeans.modules.cnd.clang.jni.CXTranslationUnit;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 * A TranslationUnit represents a file in an Index. Files can be in "unsaved"
 * state, when the user is editing its content in an IDE and has not yet saved
 * it to disk.
 */
public final class TranslationUnit extends NativeObject {

    private static Level DEBUG_LEVEL = Level.SEVERE;

    private final FileObject file;
    private final Index index;
    private final ArrayList<String> compilationFlags;
    private String unsavedBuffer;

    TranslationUnit(Index index, FileObject file) throws IOException {
        this.index = index;
        this.file = file;
        this.compilationFlags = new ArrayList<>();
        if (file == null) {
            throw new NullPointerException("Cannot create a TranslationUnit for a null fileobject");
        }
        String absolutePath = FileUtil.toFile(file).getAbsolutePath();
        if (absolutePath == null) {
            throw new NullPointerException("Cannot create a TranslationUnit for a fileobject without a path");
        }
        setPointer(CXTranslationUnit.allocate(index, absolutePath, compilationFlags));
    }

    /**
     * Returns the unsaved text for this compilation unit, if any, or null if
     * this compilation unit has already been saved.
     *
     * @return The unsaved text for this compilation unit, or null.
     */
    public String getUnsavedBuffer() {
        return unsavedBuffer;
    }

    public void setUnsavedBuffer(String unsavedBuffer) {
        this.unsavedBuffer = unsavedBuffer;
    }

    @Override
    protected void deallocate() {
        index.removeTranslationUnit(this);
        CXTranslationUnit.deallocate(getPointer());
    }

    /**
     * Returns the file for this compilation unit.
     * @return The (main) file for this compilation unit.
     */
    public FileObject getFileObject() {
        return file;
    }

}
