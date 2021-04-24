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
package org.netbeans.modules.cnd.antlr;
public abstract class Grammar {
  protected org.netbeans.modules.cnd.antlr.Tool antlrTool;
  protected org.netbeans.modules.cnd.antlr.CodeGenerator generator;
  protected org.netbeans.modules.cnd.antlr.LLkGrammarAnalyzer theLLkAnalyzer;
  protected java.util.Hashtable symbols;
  protected boolean buildAST;
  protected boolean analyzerDebug;
  protected boolean interactive;
  protected boolean genASTClassMap;
  protected java.lang.String superClass;
  protected org.netbeans.modules.cnd.antlr.TokenManager tokenManager;
  protected java.lang.String exportVocab;
  protected java.lang.String importVocab;
  protected java.util.Hashtable options;
  protected org.netbeans.modules.cnd.antlr.collections.impl.Vector rules;
  protected org.netbeans.modules.cnd.antlr.Token preambleAction;
  protected java.lang.String className;
  protected java.lang.String fileName;
  protected org.netbeans.modules.cnd.antlr.Token classMemberAction;
  protected boolean hasSyntacticPredicate;
  protected boolean hasUserErrorHandling;
  protected int maxk;
  protected boolean traceRules;
  protected boolean debuggingOutput;
  protected boolean traceSyntacticPredicates;
  protected boolean defaultErrorHandler;
  protected java.lang.String comment;
  public Grammar(java.lang.String a, org.netbeans.modules.cnd.antlr.Tool b, java.lang.String c) {}
  public void define(org.netbeans.modules.cnd.antlr.RuleSymbol a) {}
  public abstract void generate() throws java.io.IOException;
  protected java.lang.String getClassName() { return null; }
  public boolean getDefaultErrorHandler() { return false; }
  public java.lang.String getFilename() { return null; }
  public int getIntegerOption(java.lang.String a) throws java.lang.NumberFormatException { return 0; }
  public org.netbeans.modules.cnd.antlr.Token getOption(java.lang.String a) { return null; }
  protected abstract java.lang.String getSuperClass();
  public org.netbeans.modules.cnd.antlr.GrammarSymbol getSymbol(java.lang.String a) { return null; }
  public java.util.Enumeration getSymbols() { return null; }
  public boolean hasOption(java.lang.String a) { return false; }
  public boolean isDefined(java.lang.String a) { return false; }
  public abstract void processArguments(java.lang.String[] a);
  public void setCodeGenerator(org.netbeans.modules.cnd.antlr.CodeGenerator a) {}
  public void setFilename(java.lang.String a) {}
  public void setGrammarAnalyzer(org.netbeans.modules.cnd.antlr.LLkGrammarAnalyzer a) {}
  public boolean setOption(java.lang.String a, org.netbeans.modules.cnd.antlr.Token b) { return false; }
  public void setTokenManager(org.netbeans.modules.cnd.antlr.TokenManager a) {}
  public java.lang.String toString() { return null; }
}
