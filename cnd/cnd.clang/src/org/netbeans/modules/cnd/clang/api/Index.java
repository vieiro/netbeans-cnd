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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.netbeans.modules.cnd.clang.jni.CXIndex;
import org.openide.filesystems.FileObject;

/**
 * Index represents a set of files that are compiled together in a library or an
 * executable.
 */
public final class Index extends NativeObject {
    /**
     * Default options.
     */
    public static final IndexOptions DEFAULT_INDEX_OPTIONS = IndexOptions.NONE;

    /**
     * A list of include directories
     */
    private final List<File> includeDirectories;

    /**
     * The list of compilation units in this index.
     */
    private final List<TranslationUnit> compilationUnits;

    /**
     * Creates an Index with default sensible options.
     */
    public Index() {
        this(false, true, DEFAULT_INDEX_OPTIONS);
    }

    /**
     * Creates a new Index object.
     *
     * @param excludeDeclarationsFromPCH If false then all declarations (in the
     * local file and in precompiled headers) will be enumerated.
     * @param displayDiagnostics Used to display diagnostics, this prints
     * stuff to stdout when analyzing files (useful for debugging)..
     * @param options The options affecting thread priorities.
     */
    public Index(boolean excludeDeclarationsFromPCH, boolean displayDiagnostics,
            IndexOptions options) {
        setPointer(CXIndex.allocate(excludeDeclarationsFromPCH, displayDiagnostics, options.mask));
        if (getPointer() == 0) {
            throw new IllegalArgumentException("Cannot create an index.");
        }
        compilationUnits = new ArrayList<>();
        includeDirectories = new ArrayList<>();
    }


    /**
     * Adds a compilation unit for the given file in this compilation unit.
     * @param file the file that this compilation unit represents.
     * @return a TranslationUnit.
     * @throws IOException if the compilation unit cannot be constructed.
     */
    public TranslationUnit addTranslationUnit(FileObject fileObject) throws IOException {
        if (fileObject == null) {
            throw new NullPointerException("Cannot create a compilation unit for a null File");
        }
        TranslationUnit unit = new TranslationUnit(this, fileObject);
        compilationUnits.add(unit);
        return unit;
    }

    @Override
    protected void deallocate() {
        CXIndex.deallocate(getPointer());
    }

    /**
     * Returns an unmodifiable collection of the compilation units in this index.
     * @return An unmodifiable collection of the compilation units in this index.
     */
    public Collection<TranslationUnit> getTranslationUnits() {
        return Collections.unmodifiableCollection(compilationUnits);
    }

    /**
     * Returns a modifiable list of include directories.
     * @return A list of include directories.
     */
    public List<File> getIncludeDirectories() {
        return includeDirectories;
    }

    /**
     * Removes a compilation unit from the list of compilation units.
     * @param unit The unit to remove.
     */
    void removeTranslationUnit(TranslationUnit unit) {
        compilationUnits.remove(unit);
    }

}
