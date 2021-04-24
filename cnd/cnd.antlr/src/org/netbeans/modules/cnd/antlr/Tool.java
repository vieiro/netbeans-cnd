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

// This class automatically generated with introspection from "Tool.java"
package org.netbeans.modules.cnd.antlr;
public class Tool {
  public static java.lang.String version;
  protected boolean hasError;
  protected java.lang.String outputDir;
  protected java.lang.String grammarFile;
  protected java.lang.String literalsPrefix;
  protected boolean upperCaseMangledLiterals;
  protected org.netbeans.modules.cnd.antlr.NameSpace nameSpace;
  protected java.lang.String namespaceAntlr;
  protected java.lang.String namespaceStd;
  protected boolean genHashLines;
  protected boolean noConstructors;
  public static boolean memoization;
  public static boolean localLACache;
  public static boolean avoidLAMatch;
  public static boolean cloneGuessing;
  public static boolean extendedErrors;
  public static boolean agressive;
  public Tool() {}
  public java.lang.String getGrammarFile() { return null; }
  public boolean hasError() { return false; }
  public org.netbeans.modules.cnd.antlr.NameSpace getNameSpace() { return null; }
  public java.lang.String getNamespaceStd() { return null; }
  public java.lang.String getNamespaceAntlr() { return null; }
  public boolean getGenHashLines() { return false; }
  public java.lang.String getLiteralsPrefix() { return null; }
  public boolean getUpperCaseMangledLiterals() { return false; }
  public void setFileLineFormatter(org.netbeans.modules.cnd.antlr.FileLineFormatter a) {}
  protected void checkForInvalidArguments(java.lang.String[] a, org.netbeans.modules.cnd.antlr.collections.impl.BitSet b) {}
  public void copyFile(java.lang.String a, java.lang.String b) throws java.io.IOException {}
  public void doEverythingWrapper(java.lang.String[] a) {}
  public int doEverything(java.lang.String[] a) { return 0; }
  public void error(java.lang.String a) {}
  public void error(java.lang.String a, java.lang.String b, int c, int d) {}
  public java.lang.String fileMinusPath(java.lang.String a) { return null; }
  public java.lang.String getLanguage(org.netbeans.modules.cnd.antlr.MakeGrammar a) { return null; }
  public java.lang.String getOutputDirectory() { return null; }
  public static void main(java.lang.String[] a) {}
  public java.io.PrintWriter openOutputFile(java.lang.String a) throws java.io.IOException { return null; }
  public java.io.Reader getGrammarReader() { return null; }
  public void reportException(java.lang.Exception a, java.lang.String b) {}
  public void reportProgress(java.lang.String a) {}
  public void fatalError(java.lang.String a) {}
  public void panic() {}
  public void panic(java.lang.String a) {}
  public java.io.File parent(java.io.File a) { return null; }
  public static org.netbeans.modules.cnd.antlr.collections.impl.Vector parseSeparatedList(java.lang.String a, char b) { return null; }
  public java.lang.String pathToFile(java.lang.String a) { return null; }
  protected void processArguments(java.lang.String[] a) {}
  public void setArgOK(int a) {}
  public void setOutputDirectory(java.lang.String a) {}
  public void toolError(java.lang.String a) {}
  public void warning(java.lang.String a) {}
  public void warning(java.lang.String a, java.lang.String b, int c, int d) {}
  public void warning(java.lang.String[] a, java.lang.String b, int c, int d) {}
  public void setNameSpace(java.lang.String a) {}
}
