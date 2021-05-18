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

import java.util.ArrayList;
import java.util.Collection;
import org.netbeans.modules.cnd.clang.api.TranslationUnit;
import org.netbeans.modules.cnd.clang.api.Index;
import org.netbeans.modules.cnd.clang.api.NativeObject;

/**
 *
 */
public class CXUnsavedFileList extends NativeObject {

    private final int nUnsavedBuffers;

    CXUnsavedFileList(Index index) {
        Collection<TranslationUnit> translationUnits = index.getTranslationUnits();
        ArrayList<byte[]> unsavedFileNames = new ArrayList<>(translationUnits.size());
        ArrayList<byte[]> unsavedFileContents = new ArrayList<>(translationUnits.size());
        for (TranslationUnit unit : translationUnits) {
            String text = unit.getUnsavedBuffer();
            if (text != null) {
                String path = unit.getFileObject().getPath();
                if (path != null) {
                    unsavedFileNames.add(NativeUtils.toConstCharZ(path));
                    unsavedFileNames.add(NativeUtils.toBuffer(text));
                }
            }
        }
        nUnsavedBuffers = unsavedFileNames.size();
        byte[][] filenames = unsavedFileNames.toArray(new byte[nUnsavedBuffers][]);
        byte[][] buffers = unsavedFileNames.toArray(new byte[nUnsavedBuffers][]);
        setPointer(allocate(filenames, buffers));
    }

    public int getNumberOfUnsavedBuffers() {
        return nUnsavedBuffers;
    }

    @Override
    protected void deallocate() {
        deallocate(getPointer());
    }

    private static native long allocate(byte[][] filenames, byte[][] buffers);

    private static native void deallocate(long pointer);

    public static native long getCXUnsavedFilePointer(long pointer);

}
