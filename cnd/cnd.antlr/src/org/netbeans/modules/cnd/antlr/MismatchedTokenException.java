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

// This class automatically generated with introspection from "MismatchedTokenException.java"
package org.netbeans.modules.cnd.antlr;
public class MismatchedTokenException extends org.netbeans.modules.cnd.antlr.RecognitionException {
  public org.netbeans.modules.cnd.antlr.Token token;
  public org.netbeans.modules.cnd.antlr.collections.AST node;
  public static final int TOKEN = 1;
  public static final int NOT_TOKEN = 2;
  public static final int RANGE = 3;
  public static final int NOT_RANGE = 4;
  public static final int SET = 5;
  public static final int NOT_SET = 6;
  public int mismatchType;
  public int expecting;
  public int upper;
  public org.netbeans.modules.cnd.antlr.collections.impl.BitSet set;
  public MismatchedTokenException() { super(); }
  public MismatchedTokenException(java.lang.String[] a, org.netbeans.modules.cnd.antlr.collections.AST b, int c, int d, boolean e) { super(); }
  public MismatchedTokenException(java.lang.String[] a, org.netbeans.modules.cnd.antlr.collections.AST b, int c, boolean d) { super(); }
  public MismatchedTokenException(java.lang.String[] a, org.netbeans.modules.cnd.antlr.collections.AST b, org.netbeans.modules.cnd.antlr.collections.impl.BitSet c, boolean d) { super(); }
  public MismatchedTokenException(java.lang.String[] a, org.netbeans.modules.cnd.antlr.Token b, int c, int d, boolean e, java.lang.String f) { super(); }
  public MismatchedTokenException(java.lang.String[] a, org.netbeans.modules.cnd.antlr.Token b, int c, boolean d, java.lang.String e) { super(); }
  public MismatchedTokenException(java.lang.String[] a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.collections.impl.BitSet c, boolean d, java.lang.String e) { super(); }
  public java.lang.String getMessage() { return null; }
  public static java.lang.String tokenName(java.lang.String[] a, int b) { return null; }
  public java.lang.String getTokenText() { return null; }
}
