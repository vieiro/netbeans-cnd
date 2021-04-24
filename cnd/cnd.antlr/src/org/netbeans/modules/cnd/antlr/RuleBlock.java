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

// This class automatically generated with introspection from "RuleBlock.java"
package org.netbeans.modules.cnd.antlr;
public class RuleBlock extends org.netbeans.modules.cnd.antlr.AlternativeBlock {
  protected java.lang.String ruleName;
  protected java.lang.String argAction;
  protected java.lang.String throwsSpec;
  protected java.lang.String returnAction;
  protected org.netbeans.modules.cnd.antlr.RuleEndElement endNode;
  protected boolean testLiterals;
  protected boolean[] lock;
  protected org.netbeans.modules.cnd.antlr.Lookahead[] cache;
  protected boolean defaultErrorHandler;
  protected boolean constText;
  protected boolean checkSkip;
  protected java.lang.String ignoreRule;
  public RuleBlock(org.netbeans.modules.cnd.antlr.Grammar a, java.lang.String b) { super(a); }
  public RuleBlock(org.netbeans.modules.cnd.antlr.Grammar a, java.lang.String b, int c, boolean d) { super(a); }
  public void addExceptionSpec(org.netbeans.modules.cnd.antlr.ExceptionSpec a) {}
  public org.netbeans.modules.cnd.antlr.ExceptionSpec findExceptionSpec(org.netbeans.modules.cnd.antlr.Token a) { return null; }
  public org.netbeans.modules.cnd.antlr.ExceptionSpec findExceptionSpec(java.lang.String a) { return null; }
  public void generate(org.netbeans.modules.cnd.antlr.Context a) {}
  public boolean getDefaultErrorHandler() { return false; }
  public boolean isCheckSkip() { return false; }
  public boolean isConstText() { return false; }
  public org.netbeans.modules.cnd.antlr.RuleEndElement getEndElement() { return null; }
  public java.lang.String getIgnoreRule() { return null; }
  public java.lang.String getRuleName() { return null; }
  public boolean getTestLiterals() { return false; }
  public boolean isLexerAutoGenRule() { return false; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a) { return null; }
  public void prepareForAnalysis() {}
  public void setDefaultErrorHandler(boolean a) {}
  public void setEndElement(org.netbeans.modules.cnd.antlr.RuleEndElement a) {}
  public void setOption(org.netbeans.modules.cnd.antlr.Token a, org.netbeans.modules.cnd.antlr.Token b) {}
  public java.lang.String toString() { return null; }
  public void setLabel(java.lang.String a) {}
  public void setInitAction(java.lang.String a) {}
  public void setAutoGen(boolean a) {}
  public void setAlternatives(org.netbeans.modules.cnd.antlr.collections.impl.Vector a) {}
  public void removeTrackingOfRuleRefs(org.netbeans.modules.cnd.antlr.Grammar a) {}
  public java.lang.String getLabel() { return null; }
  public java.lang.String getInitAction() { return null; }
  public boolean getAutoGen() { return false; }
  public org.netbeans.modules.cnd.antlr.collections.impl.Vector getAlternatives() { return null; }
  public org.netbeans.modules.cnd.antlr.Alternative getAlternativeAt(int a) { return null; }
  public void addAlternative(org.netbeans.modules.cnd.antlr.Alternative a) {}
  public boolean isCombineChars() { return false; }
  public void setAutoGenType(int a) {}
  public int getAutoGenType() { return 0; }
  public int getColumn() { return 0; }
  public int getLine() { return 0; }
}
