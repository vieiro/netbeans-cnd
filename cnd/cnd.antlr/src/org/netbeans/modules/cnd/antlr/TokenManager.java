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

// This class automatically generated with introspection from "TokenManager.java"
package org.netbeans.modules.cnd.antlr;
interface TokenManager {
  public abstract java.lang.Object clone();
  public abstract void define(org.netbeans.modules.cnd.antlr.TokenSymbol a);
  public abstract java.lang.String getName();
  public abstract java.lang.String getTokenStringAt(int a);
  public abstract org.netbeans.modules.cnd.antlr.TokenSymbol getTokenSymbol(java.lang.String a);
  public abstract org.netbeans.modules.cnd.antlr.TokenSymbol getTokenSymbolAt(int a);
  public abstract java.util.Enumeration getTokenSymbolElements();
  public abstract java.util.Enumeration getTokenSymbolKeys();
  public abstract org.netbeans.modules.cnd.antlr.collections.impl.Vector getVocabulary();
  public abstract boolean isReadOnly();
  public abstract void mapToTokenSymbol(java.lang.String a, org.netbeans.modules.cnd.antlr.TokenSymbol b);
  public abstract int maxTokenType();
  public abstract int nextTokenType();
  public abstract void setName(java.lang.String a);
  public abstract void setReadOnly(boolean a);
  public abstract boolean tokenDefined(java.lang.String a);
}
