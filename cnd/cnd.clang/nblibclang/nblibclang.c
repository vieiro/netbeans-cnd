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

#include "nblibclang.h"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <stdarg.h>

/*----------------------------------------------------------------------
 * Loading/unloading library
 *----------------------------------------------------------------------*/

static JavaVM * nblibclang_jvm = NULL;

jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env = NULL;
    if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_8) != JNI_OK) {
        return JNI_ERR;
    }
    nblibclang_jvm = vm;
    LOG("NBLIBCLANG Initialized.");
    return JNI_VERSION_1_8;
}

JNIEXPORT void JNI_OnUnload(JavaVM* vm, void* reserved) {
    LOG("NBLIBCLANG Closed.");
    nblibclang_jvm = NULL;
}

void NBLCL_LOG(const char * format, ...) {
    time_t timer;
    char buffer[26];
    struct tm* tm_info;
    va_list arg_list;

    va_start(arg_list, format);
    {
        timer = time(NULL);
        tm_info = localtime(&timer);
        strftime(buffer, 26, "%Y-%m-%d %H:%M:%S", tm_info);
        fprintf(stderr, "NBCLANG:%s:", buffer);
        vfprintf(stderr, format, arg_list);
    }
    va_end(arg_list);


}

/*----------------------------------------------------------------------
 * Conversion from byte[][] to const char * []
 *----------------------------------------------------------------------*/

const char * const * NBLCL_allocateStringArray(JNIEnv * env, int * count, jobjectArray strings) {
    *count = (*env)->GetArrayLength(env, strings);
    if (count == 0) {
        return NULL;
    }
    const char ** cStrings = (const char **) malloc(*count * sizeof (const char *));
    if (cStrings == NULL) {
        LOG("Cannot allocate an array with %d string pointers.", *count);
        *count = 0;
        return NULL;
    }
    for (int i = 0; i<*count; i++) {
        const char * cStringZ = GET_C_STRING_REF(env, strings);
        cStrings[i] = cStringZ;
    }
    return cStrings;
}

void NBLCL_deallocateStringArray(JNIEnv * env, jobjectArray strings, const char * const * cStrings) {
    int count = (*env)->GetArrayLength(env, strings);
    if (cStrings != NULL) {
        for (int i = 0; i<count; i++) {
            jbyteArray bytes = (jbyteArray) (*env)->GetObjectArrayElement(env, strings, i);
            UNGET_C_STRING_REF(env, bytes, cStrings[i]);
        }
        free((void *)cStrings);
    }
}

/*----------------------------------------------------------------------
 * CXUnsavedFile * handling.
 *----------------------------------------------------------------------*/

struct NBLCL_CXUnsavedFileList {
    jobjectArray filenames;
    jobjectArray contents;
    struct CXUnsavedFile * unsavedFiles;
    jint unsavedFileCount;
};

NBLCL_CXUnsavedFileList_t NBLCL_CXUnsavedFileList_alloc(JNIEnv* env, jobjectArray filenames, jobjectArray contents) {
    NBLCL_CXUnsavedFileList_t o = (NBLCL_CXUnsavedFileList_t) malloc(sizeof (struct NBLCL_CXUnsavedFileList));
    if (o == NULL) {
        return NULL;
    }
    o->contents = NULL;
    o->filenames = NULL;
    o->unsavedFiles = NULL;
    o->unsavedFileCount = (*env)->GetArrayLength(env, filenames);
    if (o->unsavedFileCount != 0) {
        o->filenames = (*env)->NewGlobalRef(env, filenames);
        o->contents = (*env)->NewGlobalRef(env, contents);
        o->unsavedFiles = (struct CXUnsavedFile *) malloc(o->unsavedFileCount * sizeof (struct CXUnsavedFile));
        for (int i = 0; i < o->unsavedFileCount; i++) {
            struct CXUnsavedFile file = o->unsavedFiles[i];

            jbyteArray filenameBytes = (*env)->GetObjectArrayElement(env, o->filenames, i);
            file.Filename = GET_C_STRING_REF(env, filenameBytes);

            jbyteArray contentBytes = (*env)->GetObjectArrayElement(env, o->contents, i);
            jint contentSize = (*env)->GetArrayLength(env, contentBytes);

            file.Contents = GET_C_STRING_REF(env, contentBytes);
            file.Length = contentSize;

        }
    }
    return o;
}

void NBLCL_CXUnsavedFileList_deallocate(JNIEnv * env, NBLCL_CXUnsavedFileList_t o) {
    if (o->unsavedFileCount != 0) {
        for (int i = 0; i < o->unsavedFileCount; i++) {
            struct CXUnsavedFile file = o->unsavedFiles[i];

            jbyteArray filenameBytes = (*env)->GetObjectArrayElement(env, o->filenames, i);
            UNGET_C_STRING_REF(env, filenameBytes, file.Filename);

            jbyteArray contentBytes = (*env)->GetObjectArrayElement(env, o->contents, i);
            UNGET_C_STRING_REF(env, contentBytes, file.Contents);
        }
        (*env)->DeleteGlobalRef(env, o->filenames);
        (*env)->DeleteGlobalRef(env, o->contents);
        free(o->unsavedFiles);
    }
    free(o);
}

struct CXUnsavedFile * NBLCL_CXUnsavedFileList_files(NBLCL_CXUnsavedFileList_t unsavedFiles) {
    return unsavedFiles == NULL ? NULL : unsavedFiles->unsavedFiles;
}

jint NBLCL_CXUnsavedFileList_count(NBLCL_CXUnsavedFileList_t unsavedFiles) {
    return unsavedFiles == NULL ? 0 : unsavedFiles->unsavedFileCount;
}



