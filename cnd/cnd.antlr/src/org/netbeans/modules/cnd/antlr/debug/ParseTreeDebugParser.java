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

// This class automatically generated with introspection from "ParseTreeDebugParser.java"
package org.netbeans.modules.cnd.antlr.debug;
public class ParseTreeDebugParser extends org.netbeans.modules.cnd.antlr.LLkParser {
  protected java.util.Stack currentParseTreeRoot;
  protected org.netbeans.modules.cnd.antlr.ParseTreeRule mostRecentParseTreeRoot;
  protected int numberOfDerivationSteps;
  public ParseTreeDebugParser(int a) { super(a); }
  public ParseTreeDebugParser(org.netbeans.modules.cnd.antlr.TokenBuffer a, int b) { super(a, b); }
  public ParseTreeDebugParser(org.netbeans.modules.cnd.antlr.TokenStream a, int b) { super(a, b); }
  public org.netbeans.modules.cnd.antlr.ParseTree getParseTree() { return null; }
  public int getNumberOfDerivationSteps() { return 0; }
  public void match(int a) throws org.netbeans.modules.cnd.antlr.MismatchedTokenException {}
  public void match(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) throws org.netbeans.modules.cnd.antlr.MismatchedTokenException {}
  public void matchNot(int a) throws org.netbeans.modules.cnd.antlr.MismatchedTokenException {}
  protected void addCurrentTokenToParseTree() {}
  public void traceIn(java.lang.String a) {}
  public void traceOut(java.lang.String a) {}
}
