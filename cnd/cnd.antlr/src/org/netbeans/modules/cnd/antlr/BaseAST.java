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

// This class automatically generated with introspection from "BaseAST.java"
package org.netbeans.modules.cnd.antlr;
public abstract class BaseAST implements org.netbeans.modules.cnd.antlr.collections.AST,java.io.Serializable {
  protected transient org.netbeans.modules.cnd.antlr.BaseAST down;
  protected transient org.netbeans.modules.cnd.antlr.BaseAST right;
  public BaseAST() {}
  public void addChild(org.netbeans.modules.cnd.antlr.collections.AST a) {}
  public int getNumberOfChildren() { return 0; }
  public boolean equals(org.netbeans.modules.cnd.antlr.collections.AST a) { return false; }
  public boolean equalsList(org.netbeans.modules.cnd.antlr.collections.AST a) { return false; }
  public boolean equalsListPartial(org.netbeans.modules.cnd.antlr.collections.AST a) { return false; }
  public boolean equalsTree(org.netbeans.modules.cnd.antlr.collections.AST a) { return false; }
  public boolean equalsTreePartial(org.netbeans.modules.cnd.antlr.collections.AST a) { return false; }
  public org.netbeans.modules.cnd.antlr.collections.ASTEnumeration findAll(org.netbeans.modules.cnd.antlr.collections.AST a) { return null; }
  public org.netbeans.modules.cnd.antlr.collections.ASTEnumeration findAllPartial(org.netbeans.modules.cnd.antlr.collections.AST a) { return null; }
  public org.netbeans.modules.cnd.antlr.collections.AST getFirstChild() { return null; }
  public org.netbeans.modules.cnd.antlr.collections.AST getNextSibling() { return null; }
  public java.lang.String getText() { return null; }
  public int getType() { return 0; }
  public int getLine() { return 0; }
  public int getColumn() { return 0; }
  public abstract void initialize(int a, java.lang.String b);
  public abstract void initialize(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract void initialize(org.netbeans.modules.cnd.antlr.Token a);
  public void removeChildren() {}
  public void setFirstChild(org.netbeans.modules.cnd.antlr.collections.AST a) {}
  public void setNextSibling(org.netbeans.modules.cnd.antlr.collections.AST a) {}
  public void setText(java.lang.String a) {}
  public void setType(int a) {}
  public static void setVerboseStringConversion(boolean a, java.lang.String[] b) {}
  public static java.lang.String[] getTokenNames() { return null; }
  public java.lang.String toString() { return null; }
  public java.lang.String toStringList() { return null; }
  public java.lang.String toStringTree() { return null; }
  public static java.lang.String decode(java.lang.String a) { return null; }
  public static java.lang.String encode(java.lang.String a) { return null; }
  public void xmlSerializeNode(java.io.Writer a) throws java.io.IOException {}
  public void xmlSerializeRootOpen(java.io.Writer a) throws java.io.IOException {}
  public void xmlSerializeRootClose(java.io.Writer a) throws java.io.IOException {}
  public void xmlSerialize(java.io.Writer a) throws java.io.IOException {}
}
