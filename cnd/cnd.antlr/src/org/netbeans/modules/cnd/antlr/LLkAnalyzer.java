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

// This class automatically generated with introspection from "LLkAnalyzer.java"
package org.netbeans.modules.cnd.antlr;
public class LLkAnalyzer implements org.netbeans.modules.cnd.antlr.LLkGrammarAnalyzer {
  public boolean DEBUG_ANALYZER;
  protected org.netbeans.modules.cnd.antlr.Tool tool;
  protected org.netbeans.modules.cnd.antlr.Grammar grammar;
  protected boolean lexicalAnalysis;
  public LLkAnalyzer(org.netbeans.modules.cnd.antlr.Tool a) {}
  protected boolean altUsesWildcardDefault(org.netbeans.modules.cnd.antlr.Alternative a) { return false; }
  public boolean deterministic(org.netbeans.modules.cnd.antlr.AlternativeBlock a) { return false; }
  public boolean deterministic(org.netbeans.modules.cnd.antlr.OneOrMoreBlock a) { return false; }
  public boolean deterministic(org.netbeans.modules.cnd.antlr.ZeroOrMoreBlock a) { return false; }
  public boolean deterministicImpliedPath(org.netbeans.modules.cnd.antlr.BlockWithImpliedExitPath a) { return false; }
  public org.netbeans.modules.cnd.antlr.Lookahead FOLLOW(int a, org.netbeans.modules.cnd.antlr.RuleEndElement b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.ActionElement b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.AlternativeBlock b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.BlockEndElement b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.CharLiteralElement b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.CharRangeElement b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.GrammarAtom b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.OneOrMoreBlock b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.RuleBlock b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.RuleEndElement b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.RuleRefElement b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.StringLiteralElement b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.SynPredBlock b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.TokenRangeElement b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.TreeElement b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.WildcardElement b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.ZeroOrMoreBlock b) { return null; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a, java.lang.String b) { return null; }
  public static boolean lookaheadEquivForApproxAndFullAnalysis(org.netbeans.modules.cnd.antlr.Lookahead[] a, int b) { return false; }
  public void setGrammar(org.netbeans.modules.cnd.antlr.Grammar a) {}
  public boolean subruleCanBeInverted(org.netbeans.modules.cnd.antlr.AlternativeBlock a, boolean b) { return false; }
}
