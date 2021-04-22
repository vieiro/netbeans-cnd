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

// This class automatically generated with introspection from "ParserEventSupport.java"
package org.netbeans.modules.cnd.antlr.debug;
public class ParserEventSupport {
  protected static final int CONSUME = 0;
  protected static final int ENTER_RULE = 1;
  protected static final int EXIT_RULE = 2;
  protected static final int LA = 3;
  protected static final int MATCH = 4;
  protected static final int MATCH_NOT = 5;
  protected static final int MISMATCH = 6;
  protected static final int MISMATCH_NOT = 7;
  protected static final int REPORT_ERROR = 8;
  protected static final int REPORT_WARNING = 9;
  protected static final int SEMPRED = 10;
  protected static final int SYNPRED_FAILED = 11;
  protected static final int SYNPRED_STARTED = 12;
  protected static final int SYNPRED_SUCCEEDED = 13;
  protected static final int NEW_LINE = 14;
  protected static final int DONE_PARSING = 15;
  public ParserEventSupport(java.lang.Object a) {}
  public void addDoneListener(org.netbeans.modules.cnd.antlr.debug.ListenerBase a) {}
  public void addMessageListener(org.netbeans.modules.cnd.antlr.debug.MessageListener a) {}
  public void addNewLineListener(org.netbeans.modules.cnd.antlr.debug.NewLineListener a) {}
  public void addParserListener(org.netbeans.modules.cnd.antlr.debug.ParserListener a) {}
  public void addParserMatchListener(org.netbeans.modules.cnd.antlr.debug.ParserMatchListener a) {}
  public void addParserTokenListener(org.netbeans.modules.cnd.antlr.debug.ParserTokenListener a) {}
  public void addSemanticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SemanticPredicateListener a) {}
  public void addSyntacticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SyntacticPredicateListener a) {}
  public void addTraceListener(org.netbeans.modules.cnd.antlr.debug.TraceListener a) {}
  public void fireConsume(int a) {}
  public void fireDoneParsing() {}
  public void fireEnterRule(int a, int b, int c) {}
  public void fireEvent(int a, org.netbeans.modules.cnd.antlr.debug.ListenerBase b) {}
  public void fireEvents(int a, java.util.Vector b) {}
  public void fireExitRule(int a, int b, int c) {}
  public void fireLA(int a, int b) {}
  public void fireMatch(char a, int b) {}
  public void fireMatch(char a, org.netbeans.modules.cnd.antlr.collections.impl.BitSet b, int c) {}
  public void fireMatch(char a, java.lang.String b, int c) {}
  public void fireMatch(int a, org.netbeans.modules.cnd.antlr.collections.impl.BitSet b, java.lang.String c, int d) {}
  public void fireMatch(int a, java.lang.String b, int c) {}
  public void fireMatch(java.lang.String a, int b) {}
  public void fireMatchNot(char a, char b, int c) {}
  public void fireMatchNot(int a, int b, java.lang.String c, int d) {}
  public void fireMismatch(char a, char b, int c) {}
  public void fireMismatch(char a, org.netbeans.modules.cnd.antlr.collections.impl.BitSet b, int c) {}
  public void fireMismatch(char a, java.lang.String b, int c) {}
  public void fireMismatch(int a, int b, java.lang.String c, int d) {}
  public void fireMismatch(int a, org.netbeans.modules.cnd.antlr.collections.impl.BitSet b, java.lang.String c, int d) {}
  public void fireMismatch(java.lang.String a, java.lang.String b, int c) {}
  public void fireMismatchNot(char a, char b, int c) {}
  public void fireMismatchNot(int a, int b, java.lang.String c, int d) {}
  public void fireNewLine(int a) {}
  public void fireReportError(java.lang.Exception a) {}
  public void fireReportError(java.lang.String a) {}
  public void fireReportWarning(java.lang.String a) {}
  public boolean fireSemanticPredicateEvaluated(int a, int b, boolean c, int d) { return false; }
  public void fireSyntacticPredicateFailed(int a) {}
  public void fireSyntacticPredicateStarted(int a) {}
  public void fireSyntacticPredicateSucceeded(int a) {}
  protected void refresh(java.util.Vector a) {}
  public void refreshListeners() {}
  public void removeDoneListener(org.netbeans.modules.cnd.antlr.debug.ListenerBase a) {}
  public void removeMessageListener(org.netbeans.modules.cnd.antlr.debug.MessageListener a) {}
  public void removeNewLineListener(org.netbeans.modules.cnd.antlr.debug.NewLineListener a) {}
  public void removeParserListener(org.netbeans.modules.cnd.antlr.debug.ParserListener a) {}
  public void removeParserMatchListener(org.netbeans.modules.cnd.antlr.debug.ParserMatchListener a) {}
  public void removeParserTokenListener(org.netbeans.modules.cnd.antlr.debug.ParserTokenListener a) {}
  public void removeSemanticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SemanticPredicateListener a) {}
  public void removeSyntacticPredicateListener(org.netbeans.modules.cnd.antlr.debug.SyntacticPredicateListener a) {}
  public void removeTraceListener(org.netbeans.modules.cnd.antlr.debug.TraceListener a) {}
}
