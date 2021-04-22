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

// This class automatically generated with introspection from "GrammarElement.java"
package org.netbeans.modules.cnd.antlr;
abstract class GrammarElement {
  public static final int AUTO_GEN_NONE = 1;
  public static final int AUTO_GEN_CARET = 2;
  public static final int AUTO_GEN_BANG = 3;
  protected org.netbeans.modules.cnd.antlr.Grammar grammar;
  protected int line;
  protected int column;
  public GrammarElement(org.netbeans.modules.cnd.antlr.Grammar a) {}
  public GrammarElement(org.netbeans.modules.cnd.antlr.Grammar a, org.netbeans.modules.cnd.antlr.Token b) {}
  public void generate(org.netbeans.modules.cnd.antlr.Context a) {}
  public int getLine() { return 0; }
  public int getColumn() { return 0; }
  public org.netbeans.modules.cnd.antlr.Lookahead look(int a) { return null; }
  public abstract java.lang.String toString();
}
