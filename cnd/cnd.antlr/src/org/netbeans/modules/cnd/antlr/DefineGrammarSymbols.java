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

// This class automatically generated with introspection from "DefineGrammarSymbols.java"
package org.netbeans.modules.cnd.antlr;
public class DefineGrammarSymbols implements org.netbeans.modules.cnd.antlr.ANTLRGrammarParseBehavior {
  protected java.util.Hashtable grammars;
  protected java.util.Hashtable tokenManagers;
  protected org.netbeans.modules.cnd.antlr.Grammar grammar;
  protected org.netbeans.modules.cnd.antlr.Tool tool;
  protected java.util.Hashtable headerActions;
  protected int numLexers;
  protected int numParsers;
  protected int numTreeParsers;
  public DefineGrammarSymbols(org.netbeans.modules.cnd.antlr.Tool a, java.lang.String[] b, org.netbeans.modules.cnd.antlr.LLkAnalyzer c) {}
  public void _refStringLiteral(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, int c, boolean d) {}
  public void _refToken(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c, org.netbeans.modules.cnd.antlr.Token d, boolean e, int f, boolean g) {}
  public void abortGrammar() {}
  public void beginAlt(boolean a) {}
  public void beginChildList() {}
  public void beginExceptionGroup() {}
  public void beginExceptionSpec(org.netbeans.modules.cnd.antlr.Token a) {}
  public void beginSubRule(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, boolean c) {}
  public void beginTree(org.netbeans.modules.cnd.antlr.Token a) throws org.netbeans.modules.cnd.antlr.SemanticException {}
  public void defineRuleName(org.netbeans.modules.cnd.antlr.Token a, java.lang.String b, boolean c, java.lang.String d) throws org.netbeans.modules.cnd.antlr.SemanticException {}
  public void defineToken(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b) {}
  public void endAlt() {}
  public void endChildList() {}
  public void endExceptionGroup() {}
  public void endExceptionSpec() {}
  public void endGrammar() {}
  public void endOptions() {}
  public void endRule(java.lang.String a) {}
  public void endSubRule() {}
  public void endTree() {}
  public void hasError() {}
  public void noASTSubRule() {}
  public void oneOrMoreSubRule() {}
  public void optionalSubRule() {}
  public void setUserExceptions(java.lang.String a) {}
  public void refAction(org.netbeans.modules.cnd.antlr.Token a) {}
  public void refArgAction(org.netbeans.modules.cnd.antlr.Token a) {}
  public void refCharLiteral(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, boolean c, int d, boolean e) {}
  public void refCharRange(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c, int d, boolean e) {}
  public void refElementOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b) {}
  public void refTokensSpecElementOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c) {}
  public void refExceptionHandler(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b) {}
  public void refHeaderAction(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b) {}
  public java.lang.String getHeaderAction(java.lang.String a) { return null; }
  public int getHeaderActionLine(java.lang.String a) { return 0; }
  public void refInitAction(org.netbeans.modules.cnd.antlr.Token a) {}
  public void refMemberAction(org.netbeans.modules.cnd.antlr.Token a) {}
  public void refPreambleAction(org.netbeans.modules.cnd.antlr.Token a) {}
  public void refReturnAction(org.netbeans.modules.cnd.antlr.Token a) {}
  public void refRule(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c, org.netbeans.modules.cnd.antlr.Token d, int e) {}
  public void refSemPred(org.netbeans.modules.cnd.antlr.Token a) {}
  public void refStringLiteral(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, int c, boolean d) {}
  public void refToken(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c, org.netbeans.modules.cnd.antlr.Token d, boolean e, int f, boolean g) {}
  public void refTokenRange(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c, int d, boolean e) {}
  public void refTreeSpecifier(org.netbeans.modules.cnd.antlr.Token a) {}
  public void refWildcard(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, int c) {}
  public void reset() {}
  public void setArgOfRuleRef(org.netbeans.modules.cnd.antlr.Token a) {}
  public void setCharVocabulary(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) {}
  public void setFileOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, java.lang.String c) {}
  public void setGrammarOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b) {}
  public void setRuleOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b) {}
  public void setSubruleOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b) {}
  public void startLexer(java.lang.String a, org.netbeans.modules.cnd.antlr.Token b, java.lang.String c, java.lang.String d) {}
  public void startParser(java.lang.String a, org.netbeans.modules.cnd.antlr.Token b, java.lang.String c, java.lang.String d) {}
  public void startTreeWalker(java.lang.String a, org.netbeans.modules.cnd.antlr.Token b, java.lang.String c, java.lang.String d) {}
  public void synPred() {}
  public void zeroOrMoreSubRule() {}
}
