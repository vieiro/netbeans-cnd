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

// This class automatically generated with introspection from "Hierarchy.java"
package org.netbeans.modules.cnd.antlr.preprocessor;
public class Hierarchy {
  protected org.netbeans.modules.cnd.antlr.preprocessor.Grammar LexerRoot;
  protected org.netbeans.modules.cnd.antlr.preprocessor.Grammar ParserRoot;
  protected org.netbeans.modules.cnd.antlr.preprocessor.Grammar TreeParserRoot;
  protected java.util.Hashtable symbols;
  protected java.util.Hashtable files;
  protected org.netbeans.modules.cnd.antlr.Tool antlrTool;
  public Hierarchy(org.netbeans.modules.cnd.antlr.Tool a) {}
  public void addGrammar(org.netbeans.modules.cnd.antlr.preprocessor.Grammar a) {}
  public void addGrammarFile(org.netbeans.modules.cnd.antlr.preprocessor.GrammarFile a) {}
  public void expandGrammarsInFile(java.lang.String a) {}
  public org.netbeans.modules.cnd.antlr.preprocessor.Grammar findRoot(org.netbeans.modules.cnd.antlr.preprocessor.Grammar a) { return null; }
  public org.netbeans.modules.cnd.antlr.preprocessor.GrammarFile getFile(java.lang.String a) { return null; }
  public org.netbeans.modules.cnd.antlr.preprocessor.Grammar getGrammar(java.lang.String a) { return null; }
  public static java.lang.String optionsToString(org.netbeans.modules.cnd.antlr.collections.impl.IndexedVector a) { return null; }
  public void readGrammarFile(java.lang.String a) throws java.io.FileNotFoundException {}
  public boolean verifyThatHierarchyIsComplete() { return false; }
  public org.netbeans.modules.cnd.antlr.Tool getTool() { return null; }
  public void setTool(org.netbeans.modules.cnd.antlr.Tool a) {}
}
