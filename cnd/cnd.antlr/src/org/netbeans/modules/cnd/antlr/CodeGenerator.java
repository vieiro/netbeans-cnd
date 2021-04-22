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

// This class automatically generated with introspection from "CodeGenerator.java"
package org.netbeans.modules.cnd.antlr;
public abstract class CodeGenerator {
  protected org.netbeans.modules.cnd.antlr.Tool antlrTool;
  protected int tabs;
  protected transient java.io.PrintWriter currentOutput;
  protected org.netbeans.modules.cnd.antlr.Grammar grammar;
  protected org.netbeans.modules.cnd.antlr.collections.impl.Vector bitsetsUsed;
  protected org.netbeans.modules.cnd.antlr.DefineGrammarSymbols behavior;
  protected org.netbeans.modules.cnd.antlr.LLkGrammarAnalyzer analyzer;
  protected org.netbeans.modules.cnd.antlr.CharFormatter charFormatter;
  protected boolean DEBUG_CODE_GENERATOR;
  protected static final int DEFAULT_MAKE_SWITCH_THRESHOLD = 2;
  protected static final int DEFAULT_BITSET_TEST_THRESHOLD = 4;
  protected static final int BITSET_OPTIMIZE_INIT_THRESHOLD = 9;
  protected int makeSwitchThreshold;
  protected int bitsetTestThreshold;
  public static java.lang.String TokenTypesFileSuffix;
  public static java.lang.String TokenTypesFileExt;
  public CodeGenerator() {}
  protected void _print(java.lang.String a) {}
  protected void _printAction(java.lang.String a) {}
  protected void _println(java.lang.String a) {}
  public static boolean elementsAreRange(int[] a) { return false; }
  protected java.lang.String extractIdOfAction(org.netbeans.modules.cnd.antlr.Token a) { return null; }
  protected java.lang.String extractIdOfAction(java.lang.String a, int b, int c) { return null; }
  protected java.lang.String extractTypeOfAction(org.netbeans.modules.cnd.antlr.Token a) { return null; }
  protected java.lang.String extractTypeOfAction(java.lang.String a, int b, int c) { return null; }
  public abstract void gen();
  public abstract void gen(org.netbeans.modules.cnd.antlr.ActionElement a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.AlternativeBlock a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.BlockEndElement a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.CharLiteralElement a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.CharRangeElement a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.LexerGrammar a) throws java.io.IOException;
  public abstract void gen(org.netbeans.modules.cnd.antlr.OneOrMoreBlock a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.ParserGrammar a) throws java.io.IOException;
  public abstract void gen(org.netbeans.modules.cnd.antlr.RuleRefElement a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.StringLiteralElement a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.TokenRangeElement a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.TokenRefElement a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.TreeElement a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.TreeWalkerGrammar a) throws java.io.IOException;
  public abstract void gen(org.netbeans.modules.cnd.antlr.WildcardElement a, org.netbeans.modules.cnd.antlr.Context b);
  public abstract void gen(org.netbeans.modules.cnd.antlr.ZeroOrMoreBlock a, org.netbeans.modules.cnd.antlr.Context b);
  protected void genTokenInterchange(org.netbeans.modules.cnd.antlr.TokenManager a) throws java.io.IOException {}
  public java.lang.String processStringForASTConstructor(java.lang.String a) { return null; }
  public abstract java.lang.String getASTCreateString(org.netbeans.modules.cnd.antlr.collections.impl.Vector a);
  public abstract java.lang.String getASTCreateString(org.netbeans.modules.cnd.antlr.GrammarAtom a, java.lang.String b);
  protected java.lang.String getBitsetName(int a) { return null; }
  public static java.lang.String encodeLexerRuleName(java.lang.String a) { return null; }
  public static java.lang.String decodeLexerRuleName(java.lang.String a) { return null; }
  public abstract java.lang.String mapTreeId(java.lang.String a, org.netbeans.modules.cnd.antlr.ActionTransInfo b);
  protected int markBitsetForGen(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) { return 0; }
  protected void print(java.lang.String a) {}
  protected void printAction(java.lang.String a) {}
  protected void println(java.lang.String a) {}
  protected void printTabs() {}
  protected abstract java.lang.String processActionForSpecialSymbols(java.lang.String a, int b, org.netbeans.modules.cnd.antlr.RuleBlock c, org.netbeans.modules.cnd.antlr.ActionTransInfo d);
  public java.lang.String getFOLLOWBitSet(java.lang.String a, int b) { return null; }
  public java.lang.String getFIRSTBitSet(java.lang.String a, int b) { return null; }
  protected java.lang.String removeAssignmentFromDeclaration(java.lang.String a) { return null; }
  public static java.lang.String reverseLexerRuleName(java.lang.String a) { return null; }
  public void setAnalyzer(org.netbeans.modules.cnd.antlr.LLkGrammarAnalyzer a) {}
  public void setBehavior(org.netbeans.modules.cnd.antlr.DefineGrammarSymbols a) {}
  protected void setGrammar(org.netbeans.modules.cnd.antlr.Grammar a) {}
  public void setTool(org.netbeans.modules.cnd.antlr.Tool a) {}
}
