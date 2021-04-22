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

// This class automatically generated with introspection from "Grammar.java"
package org.netbeans.modules.cnd.antlr.preprocessor;
class Grammar {
  protected java.lang.String name;
  protected java.lang.String fileName;
  protected java.lang.String superGrammar;
  protected java.lang.String type;
  protected org.netbeans.modules.cnd.antlr.collections.impl.IndexedVector rules;
  protected org.netbeans.modules.cnd.antlr.collections.impl.IndexedVector options;
  protected java.lang.String tokenSection;
  protected java.lang.String preambleAction;
  protected java.lang.String memberAction;
  protected org.netbeans.modules.cnd.antlr.preprocessor.Hierarchy hier;
  protected boolean predefined;
  protected boolean alreadyExpanded;
  protected boolean specifiedVocabulary;
  protected java.lang.String superClass;
  protected java.lang.String importVocab;
  protected java.lang.String exportVocab;
  protected org.netbeans.modules.cnd.antlr.Tool antlrTool;
  public Grammar(org.netbeans.modules.cnd.antlr.Tool a, java.lang.String b, java.lang.String c, org.netbeans.modules.cnd.antlr.collections.impl.IndexedVector d) {}
  public void addOption(org.netbeans.modules.cnd.antlr.preprocessor.Option a) {}
  public void addRule(org.netbeans.modules.cnd.antlr.preprocessor.Rule a) {}
  public void expandInPlace() {}
  public java.lang.String getFileName() { return null; }
  public java.lang.String getName() { return null; }
  public org.netbeans.modules.cnd.antlr.collections.impl.IndexedVector getOptions() { return null; }
  public org.netbeans.modules.cnd.antlr.collections.impl.IndexedVector getRules() { return null; }
  public org.netbeans.modules.cnd.antlr.preprocessor.Grammar getSuperGrammar() { return null; }
  public java.lang.String getSuperGrammarName() { return null; }
  public java.lang.String getType() { return null; }
  public void inherit(org.netbeans.modules.cnd.antlr.preprocessor.Option a, org.netbeans.modules.cnd.antlr.preprocessor.Grammar b) {}
  public void inherit(org.netbeans.modules.cnd.antlr.preprocessor.Rule a, org.netbeans.modules.cnd.antlr.preprocessor.Grammar b) {}
  public void inherit(java.lang.String a, org.netbeans.modules.cnd.antlr.preprocessor.Grammar b) {}
  public boolean isPredefined() { return false; }
  public void setFileName(java.lang.String a) {}
  public void setHierarchy(org.netbeans.modules.cnd.antlr.preprocessor.Hierarchy a) {}
  public void setMemberAction(java.lang.String a) {}
  public void setOptions(org.netbeans.modules.cnd.antlr.collections.impl.IndexedVector a) {}
  public void setPreambleAction(java.lang.String a) {}
  public void setPredefined(boolean a) {}
  public void setTokenSection(java.lang.String a) {}
  public void setType(java.lang.String a) {}
  public java.lang.String toString() { return null; }
}
