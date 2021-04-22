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

// This class automatically generated with introspection from "ANTLRGrammarParseBehavior.java"
package org.netbeans.modules.cnd.antlr;
public interface ANTLRGrammarParseBehavior {
  public abstract void abortGrammar();
  public abstract void beginAlt(boolean a);
  public abstract void beginChildList();
  public abstract void beginExceptionGroup();
  public abstract void beginExceptionSpec(org.netbeans.modules.cnd.antlr.Token a);
  public abstract void beginSubRule(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, boolean c);
  public abstract void beginTree(org.netbeans.modules.cnd.antlr.Token a) throws org.netbeans.modules.cnd.antlr.SemanticException;
  public abstract void defineRuleName(org.netbeans.modules.cnd.antlr.Token a, java.lang.String b, boolean c, java.lang.String d) throws org.netbeans.modules.cnd.antlr.SemanticException;
  public abstract void defineToken(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b);
  public abstract void endAlt();
  public abstract void endChildList();
  public abstract void endExceptionGroup();
  public abstract void endExceptionSpec();
  public abstract void endGrammar();
  public abstract void endOptions();
  public abstract void endRule(java.lang.String a);
  public abstract void endSubRule();
  public abstract void endTree();
  public abstract void hasError();
  public abstract void noASTSubRule();
  public abstract void oneOrMoreSubRule();
  public abstract void optionalSubRule();
  public abstract void refAction(org.netbeans.modules.cnd.antlr.Token a);
  public abstract void refArgAction(org.netbeans.modules.cnd.antlr.Token a);
  public abstract void setUserExceptions(java.lang.String a);
  public abstract void refCharLiteral(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, boolean c, int d, boolean e);
  public abstract void refCharRange(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c, int d, boolean e);
  public abstract void refElementOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b);
  public abstract void refTokensSpecElementOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c);
  public abstract void refExceptionHandler(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b);
  public abstract void refHeaderAction(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b);
  public abstract void refInitAction(org.netbeans.modules.cnd.antlr.Token a);
  public abstract void refMemberAction(org.netbeans.modules.cnd.antlr.Token a);
  public abstract void refPreambleAction(org.netbeans.modules.cnd.antlr.Token a);
  public abstract void refReturnAction(org.netbeans.modules.cnd.antlr.Token a);
  public abstract void refRule(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c, org.netbeans.modules.cnd.antlr.Token d, int e);
  public abstract void refSemPred(org.netbeans.modules.cnd.antlr.Token a);
  public abstract void refStringLiteral(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, int c, boolean d);
  public abstract void refToken(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c, org.netbeans.modules.cnd.antlr.Token d, boolean e, int f, boolean g);
  public abstract void refTokenRange(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, org.netbeans.modules.cnd.antlr.Token c, int d, boolean e);
  public abstract void refTreeSpecifier(org.netbeans.modules.cnd.antlr.Token a);
  public abstract void refWildcard(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, int c);
  public abstract void setArgOfRuleRef(org.netbeans.modules.cnd.antlr.Token a);
  public abstract void setCharVocabulary(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a);
  public abstract void setFileOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b, java.lang.String c);
  public abstract void setGrammarOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b);
  public abstract void setRuleOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b);
  public abstract void setSubruleOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b);
  public abstract void startLexer(java.lang.String a, org.netbeans.modules.cnd.antlr.Token b, java.lang.String c, java.lang.String d);
  public abstract void startParser(java.lang.String a, org.netbeans.modules.cnd.antlr.Token b, java.lang.String c, java.lang.String d);
  public abstract void startTreeWalker(java.lang.String a, org.netbeans.modules.cnd.antlr.Token b, java.lang.String c, java.lang.String d);
  public abstract void synPred();
  public abstract void zeroOrMoreSubRule();
}
