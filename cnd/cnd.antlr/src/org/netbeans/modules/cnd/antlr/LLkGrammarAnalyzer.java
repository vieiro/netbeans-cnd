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

// This class automatically generated with introspection from "LLkGrammarAnalyzer.java"
package org.netbeans.modules.cnd.antlr;
public interface LLkGrammarAnalyzer extends org.netbeans.modules.cnd.antlr.GrammarAnalyzer {
  public abstract boolean deterministic(org.netbeans.modules.cnd.antlr.AlternativeBlock a);
  public abstract boolean deterministic(org.netbeans.modules.cnd.antlr.OneOrMoreBlock a);
  public abstract boolean deterministic(org.netbeans.modules.cnd.antlr.ZeroOrMoreBlock a);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead FOLLOW(int a, org.netbeans.modules.cnd.antlr.RuleEndElement b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.ActionElement b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.AlternativeBlock b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.BlockEndElement b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.CharLiteralElement b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.CharRangeElement b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.GrammarAtom b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.OneOrMoreBlock b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.RuleBlock b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.RuleEndElement b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.RuleRefElement b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.StringLiteralElement b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.SynPredBlock b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.TokenRangeElement b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.TreeElement b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.WildcardElement b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, org.netbeans.modules.cnd.antlr.ZeroOrMoreBlock b);
  public abstract org.netbeans.modules.cnd.antlr.Lookahead look(int a, java.lang.String b);
  public abstract void setGrammar(org.netbeans.modules.cnd.antlr.Grammar a);
  public abstract boolean subruleCanBeInverted(org.netbeans.modules.cnd.antlr.AlternativeBlock a, boolean b);
}
