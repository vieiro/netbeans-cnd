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

// This class automatically generated with introspection from "DebuggingCharScanner.java"
package org.netbeans.modules.cnd.antlr.debug;
public abstract class DebuggingCharScanner extends org.netbeans.modules.cnd.antlr.CharScanner implements org.netbeans.modules.cnd.antlr.debug.DebuggingParser {
  protected java.lang.String[] ruleNames;
  protected java.lang.String[] semPredNames;
  public DebuggingCharScanner(org.netbeans.modules.cnd.antlr.InputBuffer a) { super(a); }
  public void addMessageListener(org.netbeans.modules.cnd.antlr.debug.MessageListener a) {}
  public void addNewLineListener(org.netbeans.modules.cnd.antlr.debug.NewLineListener a) {}
  public void addParserListener(org.netbeans.modules.cnd.antlr.debug.ParserListener a) {}
  public void addParserMatchListener(org.netbeans.modules.cnd.antlr.debug.ParserMatchListener a) {}
  public void addParserTokenListener(org.netbeans.modules.cnd.antlr.debug.ParserTokenListener a) {}
  public void addSemanticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SemanticPredicateListener a) {}
  public void addSyntacticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SyntacticPredicateListener a) {}
  public void addTraceListener(org.netbeans.modules.cnd.antlr.debug.TraceListener a) {}
  public void consume() {}
  protected void fireEnterRule(int a, int b) {}
  protected void fireExitRule(int a, int b) {}
  protected boolean fireSemanticPredicateEvaluated(int a, int b, boolean c) { return false; }
  protected void fireSyntacticPredicateFailed() {}
  protected void fireSyntacticPredicateStarted() {}
  protected void fireSyntacticPredicateSucceeded() {}
  public java.lang.String getRuleName(int a) { return null; }
  public java.lang.String getSemPredName(int a) { return null; }
  public synchronized void goToSleep() {}
  public boolean isDebugMode() { return false; }
  public char LA(int a) { return 0; }
  protected org.netbeans.modules.cnd.antlr.Token makeToken(int a) { return null; }
  public void match(char a) throws org.netbeans.modules.cnd.antlr.MismatchedCharException {}
  public void match(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) throws org.netbeans.modules.cnd.antlr.MismatchedCharException {}
  public void match(java.lang.String a) throws org.netbeans.modules.cnd.antlr.MismatchedCharException {}
  public void matchNot(char a) throws org.netbeans.modules.cnd.antlr.MismatchedCharException {}
  public void matchRange(char a, char b) throws org.netbeans.modules.cnd.antlr.MismatchedCharException {}
  public void newline() {}
  public void removeMessageListener(org.netbeans.modules.cnd.antlr.debug.MessageListener a) {}
  public void removeNewLineListener(org.netbeans.modules.cnd.antlr.debug.NewLineListener a) {}
  public void removeParserListener(org.netbeans.modules.cnd.antlr.debug.ParserListener a) {}
  public void removeParserMatchListener(org.netbeans.modules.cnd.antlr.debug.ParserMatchListener a) {}
  public void removeParserTokenListener(org.netbeans.modules.cnd.antlr.debug.ParserTokenListener a) {}
  public void removeSemanticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SemanticPredicateListener a) {}
  public void removeSyntacticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SyntacticPredicateListener a) {}
  public void removeTraceListener(org.netbeans.modules.cnd.antlr.debug.TraceListener a) {}
  public void reportError(org.netbeans.modules.cnd.antlr.MismatchedCharException a) {}
  public void reportError(java.lang.String a) {}
  public void reportWarning(java.lang.String a) {}
  public void setDebugMode(boolean a) {}
  public void setupDebugging() {}
  public synchronized void wakeUp() {}
}
