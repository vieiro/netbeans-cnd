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
#include "org_netbeans_modules_cnd_clang_jni_CXIndex.h"
#include "nblibclang.h"

JNIEXPORT jlong JNICALL Java_org_netbeans_modules_cnd_clang_jni_CXIndex_allocate
(JNIEnv *env,
        jclass CXIndexClass,
        jboolean excludeDeclarationsFromPCH,
        jboolean displayDiagnostics,
        jint options) {
    CXIndex index = clang_createIndex(excludeDeclarationsFromPCH, displayDiagnostics);
    if (index != NULL) {
        clang_CXIndex_setGlobalOptions(index, options);
    }
    LOG("ALLOC CXIndex %p", index);
    return (jlong)index;
}

JNIEXPORT void Java_org_netbeans_modules_cnd_clang_jni_CXIndex_deallocate(JNIEnv* env,
        jclass CXIndexClass, jlong pointer) {
    CXIndex index = (CXIndex) pointer;
    if (index != NULL) {
        LOG("FREE  CXIndex %p", index);
        clang_disposeIndex(index);
    }
}


