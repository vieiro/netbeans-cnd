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

// This class automatically generated with introspection from "TokenStreamRewriteEngine.java"
package org.netbeans.modules.cnd.antlr;
public class TokenStreamRewriteEngine implements org.netbeans.modules.cnd.antlr.TokenStream,org.netbeans.modules.cnd.antlr.ASdebug.IASDebugStream {
  public static final int MIN_TOKEN_INDEX = 0;
  public static final java.lang.String DEFAULT_PROGRAM_NAME = "default";
  public static final int PROGRAM_INIT_SIZE = 100;
  protected java.util.List tokens;
  protected java.util.Map programs;
  protected java.util.Map lastRewriteTokenIndexes;
  protected int index;
  protected org.netbeans.modules.cnd.antlr.TokenStream stream;
  protected org.netbeans.modules.cnd.antlr.collections.impl.BitSet discardMask;
  public TokenStreamRewriteEngine(org.netbeans.modules.cnd.antlr.TokenStream a) {}
  public TokenStreamRewriteEngine(org.netbeans.modules.cnd.antlr.TokenStream a, int b) {}
  public org.netbeans.modules.cnd.antlr.Token nextToken() throws org.netbeans.modules.cnd.antlr.TokenStreamException { return null; }
  public void rollback(int a) {}
  public void rollback(java.lang.String a, int b) {}
  public void deleteProgram() {}
  public void deleteProgram(java.lang.String a) {}
  protected void addToSortedRewriteList(Object a) {}
  protected void addToSortedRewriteList(java.lang.String a, Object b) {}
  public void insertAfter(org.netbeans.modules.cnd.antlr.Token a, java.lang.String b) {}
  public void insertAfter(int a, java.lang.String b) {}
  public void insertAfter(java.lang.String a, org.netbeans.modules.cnd.antlr.Token b, java.lang.String c) {}
  public void insertAfter(java.lang.String a, int b, java.lang.String c) {}
  public void insertBefore(org.netbeans.modules.cnd.antlr.Token a, java.lang.String b) {}
  public void insertBefore(int a, java.lang.String b) {}
  public void insertBefore(java.lang.String a, org.netbeans.modules.cnd.antlr.Token b, java.lang.String c) {}
  public void insertBefore(java.lang.String a, int b, java.lang.String c) {}
  public void replace(int a, java.lang.String b) {}
  public void replace(int a, int b, java.lang.String c) {}
  public void replace(org.netbeans.modules.cnd.antlr.Token a, java.lang.String b) {}
  public void replace(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, java.lang.String c) {}
  public void replace(java.lang.String a, int b, int c, java.lang.String d) {}
  public void replace(java.lang.String a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c, java.lang.String d) {}
  public void delete(int a) {}
  public void delete(int a, int b) {}
  public void delete(org.netbeans.modules.cnd.antlr.Token a) {}
  public void delete(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b) {}
  public void delete(java.lang.String a, int b, int c) {}
  public void delete(java.lang.String a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c) {}
  public void discard(int a) {}
  public org.netbeans.modules.cnd.antlr.TokenWithIndex getToken(int a) { return null; }
  public int getTokenStreamSize() { return 0; }
  public java.lang.String toOriginalString() { return null; }
  public java.lang.String toOriginalString(int a, int b) { return null; }
  public java.lang.String toString() { return null; }
  public java.lang.String toString(java.lang.String a) { return null; }
  public java.lang.String toString(int a, int b) { return null; }
  public java.lang.String toString(java.lang.String a, int b, int c) { return null; }
  public java.lang.String toDebugString() { return null; }
  public java.lang.String toDebugString(int a, int b) { return null; }
  public int getLastRewriteTokenIndex() { return 0; }
  protected int getLastRewriteTokenIndex(java.lang.String a) { return 0; }
  protected void setLastRewriteTokenIndex(java.lang.String a, int b) {}
  protected java.util.List getProgram(java.lang.String a) { return null; }
  public int size() { return 0; }
  public int index() { return 0; }
  public java.lang.String getEntireText() { return null; }
  public org.netbeans.modules.cnd.antlr.ASdebug.TokenOffsetInfo getOffsetInfo(org.netbeans.modules.cnd.antlr.Token a) { return null; }
}
