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

// This class automatically generated with introspection from "JavaCodeGenerator.java"
package org.netbeans.modules.cnd.antlr;
public class JavaCodeGenerator extends org.netbeans.modules.cnd.antlr.CodeGenerator {
  public static final int NO_MAPPING = -999;
  public static final int CONTINUE_LAST_MAPPING = -888;
  public static final boolean RECOVER_AST = true;
  protected int syntacticPredLevel;
  protected boolean genAST;
  protected boolean saveText;
  protected static final java.lang.String NONUNIQUE = null;
  public static final int caseSizeThreshold = 127;
  public int loopCount;
  public JavaCodeGenerator() { super(); }
  protected void printAction(java.lang.String a) {}
  protected void printAction(java.lang.String a, int b) {}
  public void println(java.lang.String a) {}
  public void println(java.lang.String a, int b) {}
  protected void print(java.lang.String a) {}
  protected void print(java.lang.String a, int b) {}
  protected void _print(java.lang.String a) {}
  protected void _print(java.lang.String a, int b) {}
  protected void _println(java.lang.String a) {}
  protected void _println(java.lang.String a, int b) {}
  protected int addSemPred(java.lang.String a) { return 0; }
  public void exitIfError() {}
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
  public java.lang.String getCheckString(org.netbeans.modules.cnd.antlr.Context a) { return null; }
  public void printCheck(org.netbeans.modules.cnd.antlr.Context a) {}
  public void gen(org.netbeans.modules.cnd.antlr.TreeElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.TreeWalkerGrammar a) throws java.io.IOException {}
  public void gen(org.netbeans.modules.cnd.antlr.WildcardElement a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void gen(org.netbeans.modules.cnd.antlr.ZeroOrMoreBlock a, org.netbeans.modules.cnd.antlr.Context b) {}
  public void printLoopStart(java.lang.String a) {}
  public void printLoopEnd(java.lang.String a) {}
  protected void genAlt(org.netbeans.modules.cnd.antlr.Alternative a, org.netbeans.modules.cnd.antlr.AlternativeBlock b, org.netbeans.modules.cnd.antlr.Context c) {}
  protected void genBitsets(org.netbeans.modules.cnd.antlr.collections.impl.Vector a, int b) {}
  protected void genBlockInitAction(org.netbeans.modules.cnd.antlr.AlternativeBlock a, org.netbeans.modules.cnd.antlr.Context b) {}
  protected void genBlockPreamble(org.netbeans.modules.cnd.antlr.AlternativeBlock a) {}
  protected void genCases(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a, int b) {}
  public org.netbeans.modules.cnd.antlr.JavaBlockFinishingInfo genCommonBlock(org.netbeans.modules.cnd.antlr.AlternativeBlock a, boolean b, org.netbeans.modules.cnd.antlr.Context c) { return null; }
  protected void genASTDeclaration(org.netbeans.modules.cnd.antlr.AlternativeElement a) {}
  protected void genASTDeclaration(org.netbeans.modules.cnd.antlr.AlternativeElement a, java.lang.String b) {}
  protected void genASTDeclaration(org.netbeans.modules.cnd.antlr.AlternativeElement a, java.lang.String b, java.lang.String c) {}
  protected void genHeader() {}
  protected void genMatch(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) {}
  protected void genMatch(org.netbeans.modules.cnd.antlr.GrammarAtom a) {}
  protected void genMatchUsingAtomText(org.netbeans.modules.cnd.antlr.GrammarAtom a) {}
  protected void genMatchUsingAtomTokenType(org.netbeans.modules.cnd.antlr.GrammarAtom a) {}
  public void genNextToken() {}
  public void genRule(org.netbeans.modules.cnd.antlr.RuleSymbol a, boolean b, int c, org.netbeans.modules.cnd.antlr.Context d) {}
  protected void genSemPred(java.lang.String a, int b, org.netbeans.modules.cnd.antlr.Context c) {}
  protected void genSemPredMap() {}
  protected void genSynPred(org.netbeans.modules.cnd.antlr.SynPredBlock a, java.lang.String b, org.netbeans.modules.cnd.antlr.Context c) {}
  public void genTokenStrings() {}
  protected void genTokenASTNodeMap() {}
  protected void genTokenASTCreateMethod() {}
  protected void genTokenTypes(org.netbeans.modules.cnd.antlr.TokenManager a) throws java.io.IOException {}
  public java.lang.String getASTCreateString(org.netbeans.modules.cnd.antlr.collections.impl.Vector a) { return null; }
  public java.lang.String getASTCreateString(org.netbeans.modules.cnd.antlr.GrammarAtom a, java.lang.String b) { return null; }
  public java.lang.String getASTCreateString(java.lang.String a) { return null; }
  protected java.lang.String getLookaheadTestExpression(org.netbeans.modules.cnd.antlr.Lookahead[] a, int b, int c) { return null; }
  protected java.lang.String getLookaheadTestExpression(org.netbeans.modules.cnd.antlr.Alternative a, int b, int c) { return null; }
  protected java.lang.String getLookaheadTestTerm(int a, org.netbeans.modules.cnd.antlr.collections.impl.BitSet b, int c) { return null; }
  public java.lang.String getRangeExpression(int a, int[] b, int c) { return null; }
  protected boolean lookaheadIsEmpty(org.netbeans.modules.cnd.antlr.Alternative a, int b) { return false; }
  public java.lang.String mapTreeId(java.lang.String a, org.netbeans.modules.cnd.antlr.ActionTransInfo b) { return null; }
  protected java.lang.String processActionForSpecialSymbols(java.lang.String a, int b, org.netbeans.modules.cnd.antlr.RuleBlock c, org.netbeans.modules.cnd.antlr.ActionTransInfo d) { return null; }
  protected java.lang.String getThrowNoViableStr(org.netbeans.modules.cnd.antlr.Context a, org.netbeans.modules.cnd.antlr.AlternativeBlock b) { return null; }
  public org.netbeans.modules.cnd.antlr.JavaCodeGeneratorPrintWriterManager getPrintWriterManager() { return null; }
  public void setPrintWriterManager(org.netbeans.modules.cnd.antlr.JavaCodeGeneratorPrintWriterManager a) {}
  public void setTool(org.netbeans.modules.cnd.antlr.Tool a) {}
}
