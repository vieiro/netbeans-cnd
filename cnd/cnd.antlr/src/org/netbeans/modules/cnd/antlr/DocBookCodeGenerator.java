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

// This class automatically generated with introspection from "DocBookCodeGenerator.java"
package org.netbeans.modules.cnd.antlr;
public class DocBookCodeGenerator extends org.netbeans.modules.cnd.antlr.CodeGenerator {
  protected int syntacticPredLevel;
  protected boolean doingLexRules;
  protected boolean firstElementInAlt;
  protected org.netbeans.modules.cnd.antlr.AlternativeElement prevAltElem;
  public DocBookCodeGenerator() { super(); }
  public void gen() {}
  public void gen(org.netbeans.modules.cnd.antlr.ActionElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.AlternativeBlock a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.BlockEndElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.CharLiteralElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.CharRangeElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.LexerGrammar a) throws java.io.IOException {}
  public void gen(org.netbeans.modules.cnd.antlr.OneOrMoreBlock a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.ParserGrammar a) throws java.io.IOException {}
  public void gen(org.netbeans.modules.cnd.antlr.RuleRefElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.StringLiteralElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.TokenRangeElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.TokenRefElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.TreeElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.TreeWalkerGrammar a) throws java.io.IOException {}
  public void gen(org.netbeans.modules.cnd.antlr.WildcardElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.ZeroOrMoreBlock a, org.netbeans.modules.cnd.antlr.Context b) {}
  protected void genAlt(org.netbeans.modules.cnd.antlr.Alternative a) {}
  public void genCommonBlock(org.netbeans.modules.cnd.antlr.AlternativeBlock a) {}
  public void genFollowSetForRuleBlock(org.netbeans.modules.cnd.antlr.RuleBlock a) {}
  protected void genGenericBlock(org.netbeans.modules.cnd.antlr.AlternativeBlock a, java.lang.String b) {}
  protected void genHeader() {}
  protected void genLookaheadSetForAlt(org.netbeans.modules.cnd.antlr.Alternative a) {}
  public void genLookaheadSetForBlock(org.netbeans.modules.cnd.antlr.AlternativeBlock a) {}
  public void genNextToken() {}
  public void genRule(org.netbeans.modules.cnd.antlr.RuleSymbol a) {}
  protected void genSynPred(org.netbeans.modules.cnd.antlr.SynPredBlock a) {}
  public void genTail() {}
  protected void genTokenTypes(org.netbeans.modules.cnd.antlr.TokenManager a) throws java.io.IOException {}
  protected java.lang.String processActionForSpecialSymbols(java.lang.String a, int b, org.netbeans.modules.cnd.antlr.RuleBlock c, org.netbeans.modules.cnd.antlr.ActionTransInfo d) { return null; }
  public java.lang.String getASTCreateString(org.netbeans.modules.cnd.antlr.collections.impl.Vector a) { return null; }
  public java.lang.String getASTCreateString(org.netbeans.modules.cnd.antlr.GrammarAtom a, java.lang.String b) { return null; }
  public java.lang.String mapTreeId(java.lang.String a, org.netbeans.modules.cnd.antlr.ActionTransInfo b) { return null; }
  public void printSet(int a, int b, org.netbeans.modules.cnd.antlr.Lookahead c) {}
}
