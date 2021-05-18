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
#include "org_netbeans_modules_cnd_clang_jni_CXTranslationUnit.h"
#include "nblibclang.h"

JNIEXPORT jlong JNICALL Java_org_netbeans_modules_cnd_clang_jni_CXTranslationUnit_allocate
(JNIEnv *env, jclass CXTranslationUnitClass,
        jlong indexPointer,
        jbyteArray absoluteFileName,
        jlong CXUnsavedFilePointer,
        jint unsavedFileCount,
        jobjectArray commandLineArgsArray) {
    CXIndex index = (CXIndex) indexPointer;
    NBLCL_CXUnsavedFileList_t unsavedFileLists = (NBLCL_CXUnsavedFileList_t) CXUnsavedFilePointer;
    int numberOfUnsavedViles = NBLCL_CXUnsavedFileList_count(unsavedFileLists);
    struct CXUnsavedFile * unsavedFiles = NBLCL_CXUnsavedFileList_files(unsavedFileLists);
    int nArgs = 0;
    const char * const * args= NBLCL_allocateStringArray(env, &nArgs, commandLineArgsArray);
    const char * filenameZ = GET_C_STRING_REF(env, absoluteFileName);

    for(int i=0; i<nArgs; i++) {
        LOG("ARG %d: %s\n", i, args[i]);
    }
    LOG("FILENAME: %s\n", filenameZ);

    LOG("Creating translationunit...\n");

    CXTranslationUnit unit = clang_createTranslationUnitFromSourceFile(index,filenameZ, nArgs, args, numberOfUnsavedViles, unsavedFiles);

    LOG("ALLOC CXTranslationUnit %p", unit);

    UNGET_C_STRING_REF(env, absoluteFileName, filenameZ);
    NBLCL_deallocateStringArray(env, commandLineArgsArray, args);

    return (jlong) unit;
}

JNIEXPORT void JNICALL Java_org_netbeans_modules_cnd_clang_jni_CXTranslationUnit_deallocate
(JNIEnv *env, jclass CXTranslationUnitClass, jlong pointer) {
    CXTranslationUnit unit = (CXTranslationUnit) pointer;
    LOG("FREE  CXTranslationUnit %p", unit);
    clang_disposeTranslationUnit(unit);
}


