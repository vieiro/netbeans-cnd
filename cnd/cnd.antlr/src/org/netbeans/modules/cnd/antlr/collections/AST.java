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

// This class automatically generated with introspection from "AST.java"
package org.netbeans.modules.cnd.antlr.collections;
public interface AST {
  public abstract void addChild(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract boolean equals(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract boolean equalsList(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract boolean equalsListPartial(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract boolean equalsTree(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract boolean equalsTreePartial(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract org.netbeans.modules.cnd.antlr.collections.ASTEnumeration findAll(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract org.netbeans.modules.cnd.antlr.collections.ASTEnumeration findAllPartial(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract org.netbeans.modules.cnd.antlr.collections.AST getFirstChild();
  public abstract org.netbeans.modules.cnd.antlr.collections.AST getNextSibling();
  public abstract java.lang.String getText();
  public abstract int getType();
  public abstract int getLine();
  public abstract int getColumn();
  public abstract int getNumberOfChildren();
  public abstract void initialize(int a, java.lang.String b);
  public abstract void initialize(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract void initialize(org.netbeans.modules.cnd.antlr.Token a);
  public abstract void setFirstChild(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract void setNextSibling(org.netbeans.modules.cnd.antlr.collections.AST a);
  public abstract void setText(java.lang.String a);
  public abstract void setType(int a);
  public abstract java.lang.String toString();
  public abstract java.lang.String toStringList();
  public abstract java.lang.String toStringTree();
}
