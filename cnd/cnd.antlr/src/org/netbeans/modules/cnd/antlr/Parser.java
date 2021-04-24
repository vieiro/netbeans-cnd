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

// This class automatically generated with introspection from "Parser.java"
package org.netbeans.modules.cnd.antlr;
public abstract class Parser extends org.netbeans.modules.cnd.antlr.MatchExceptionState {
  public static final int MEMO_RULE_FAILED = -2;
  public static final int MEMO_RULE_UNKNOWN = -1;
  public static final int INITIAL_FOLLOW_STACK_SIZE = 100;
  protected int guessing;
  protected java.lang.String filename;
  protected org.netbeans.modules.cnd.antlr.TokenBuffer input;
  protected java.lang.String[] tokenNames;
  protected org.netbeans.modules.cnd.antlr.collections.AST returnAST;
  protected org.netbeans.modules.cnd.antlr.ASTFactory astFactory;
  protected java.util.Hashtable tokenTypeToASTClassMap;
  protected int traceDepth;
  protected java.util.Map[] ruleMemo;
  public Parser() { super(); }
  public java.util.Hashtable getTokenTypeToASTClassMap() { return null; }
  public void addMessageListener(org.netbeans.modules.cnd.antlr.debug.MessageListener a) {}
  public void addParserListener(org.netbeans.modules.cnd.antlr.debug.ParserListener a) {}
  public void addParserMatchListener(org.netbeans.modules.cnd.antlr.debug.ParserMatchListener a) {}
  public void addParserTokenListener(org.netbeans.modules.cnd.antlr.debug.ParserTokenListener a) {}
  public void addSemanticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SemanticPredicateListener a) {}
  public void addSyntacticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SyntacticPredicateListener a) {}
  public void addTraceListener(org.netbeans.modules.cnd.antlr.debug.TraceListener a) {}
  public abstract void consume();
  public void consumeUntil(int a) {}
  public void consumeUntil(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) {}
  protected void defaultDebuggingSetup(org.netbeans.modules.cnd.antlr.TokenStream a, org.netbeans.modules.cnd.antlr.TokenBuffer b) {}
  public org.netbeans.modules.cnd.antlr.collections.AST getAST() { return null; }
  public org.netbeans.modules.cnd.antlr.ASTFactory getASTFactory() { return null; }
  public java.lang.String getFilename() { return null; }
  public java.lang.String getTokenName(int a) { return null; }
  public java.lang.String[] getTokenNames() { return null; }
  public boolean isDebugMode() { return false; }
  public abstract int LA(int a);
  public abstract org.netbeans.modules.cnd.antlr.Token LT(int a);
  public int mark() { return 0; }
  public void match(int a) throws org.netbeans.modules.cnd.antlr.MismatchedTokenException {}
  public void match(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) throws org.netbeans.modules.cnd.antlr.MismatchedTokenException {}
  public void matchNot(int a) throws org.netbeans.modules.cnd.antlr.MismatchedTokenException {}
  public static void panic() {}
  public void removeMessageListener(org.netbeans.modules.cnd.antlr.debug.MessageListener a) {}
  public void removeParserListener(org.netbeans.modules.cnd.antlr.debug.ParserListener a) {}
  public void removeParserMatchListener(org.netbeans.modules.cnd.antlr.debug.ParserMatchListener a) {}
  public void removeParserTokenListener(org.netbeans.modules.cnd.antlr.debug.ParserTokenListener a) {}
  public void removeSemanticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SemanticPredicateListener a) {}
  public void removeSyntacticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SyntacticPredicateListener a) {}
  public void removeTraceListener(org.netbeans.modules.cnd.antlr.debug.TraceListener a) {}
  public void reportError(org.netbeans.modules.cnd.antlr.RecognitionException a) {}
  public void reportError(java.lang.String a) {}
  public void reportWarning(java.lang.String a) {}
  public void recover(org.netbeans.modules.cnd.antlr.RecognitionException a, org.netbeans.modules.cnd.antlr.collections.impl.BitSet b) {}
  public void rewind(int a) {}
  public void setASTFactory(org.netbeans.modules.cnd.antlr.ASTFactory a) {}
  public void setASTNodeClass(java.lang.String a) {}
  public void setASTNodeType(java.lang.String a) {}
  public void setDebugMode(boolean a) {}
  public void setFilename(java.lang.String a) {}
  public void setIgnoreInvalidDebugCalls(boolean a) {}
  public void setTokenBuffer(org.netbeans.modules.cnd.antlr.TokenBuffer a) {}
  public void traceIndent() {}
  public void traceIn(java.lang.String a) {}
  public void traceOut(java.lang.String a) {}
  public int getRuleMemoization(int a, int b) { return 0; }
  public boolean alreadyParsedRule(int a) { return false; }
  public void memoize(int a, int b) {}
}
