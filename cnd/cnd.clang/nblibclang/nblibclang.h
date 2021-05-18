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

#ifndef NBLIBCLANG_HPP
#define NBLIBCLANG_HPP

#include <clang-c/Index.h>
#include <jni.h>
#include <stdio.h>

#define NBCLANG_DEBUG 1
#if NBCLANG_DEBUG == 1
extern void NBLCL_LOG(const char * format, ...);
#define LOG(fmt, ...) NBLCL_LOG("%s:%d:" fmt "\n", __FILE__, __LINE__, ##__VA_ARGS__);
#else
#define LOG(fmt, ...)
#endif

/*----------------------------------------------------------------------
 * NBLCL_allocateStringArray and NBLCL_deallocateStringArray convert
 * byte[][] to const char * const.
 ----------------------------------------------------------------------*/

#define GET_C_STRING_REF(env, JBA) ((const char *) (*(env))->GetByteArrayElements((env), (JBA), NULL))
#define UNGET_C_STRING_REF(env, JBA, CSTRING) ((*(env))->ReleaseByteArrayElements((env), (JBA), (jbyte *)(CSTRING), JNI_ABORT))

const char * const * NBLCL_allocateStringArray(JNIEnv * env, int * count, jobjectArray strings);
void NBLCL_deallocateStringArray(JNIEnv * env, jobjectArray strings, const char * const * cStrings);

/*----------------------------------------------------------------------
 * NBLCL_CXUnsavedFileList_t
 * Holds a CXUnsavedFile * and some Java objects around.
 ----------------------------------------------------------------------*/

typedef struct NBLCL_CXUnsavedFileList * NBLCL_CXUnsavedFileList_t;

NBLCL_CXUnsavedFileList_t NBLCL_CXUnsavedFileList_alloc(JNIEnv* env, jobjectArray filenames, jobjectArray contents);
struct CXUnsavedFile * NBLCL_CXUnsavedFileList_files(NBLCL_CXUnsavedFileList_t unsavedFiles);
jint NBLCL_CXUnsavedFileList_count(NBLCL_CXUnsavedFileList_t unsavedFiles);
void NBLCL_CXUnsavedFileList_deallocate(JNIEnv * env, NBLCL_CXUnsavedFileList_t unsavedFiles);



#endif /* NBLIBCLANG_HPP */

