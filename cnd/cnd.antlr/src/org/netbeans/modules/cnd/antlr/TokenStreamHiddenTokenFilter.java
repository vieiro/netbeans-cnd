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

// This class automatically generated with introspection from "TokenStreamHiddenTokenFilter.java"
package org.netbeans.modules.cnd.antlr;
public class TokenStreamHiddenTokenFilter extends org.netbeans.modules.cnd.antlr.TokenStreamBasicFilter implements org.netbeans.modules.cnd.antlr.TokenStream {
  protected org.netbeans.modules.cnd.antlr.collections.impl.BitSet hideMask;
  protected org.netbeans.modules.cnd.antlr.CommonHiddenStreamToken nextMonitoredToken;
  protected org.netbeans.modules.cnd.antlr.CommonHiddenStreamToken lastHiddenToken;
  protected org.netbeans.modules.cnd.antlr.CommonHiddenStreamToken firstHidden;
  public TokenStreamHiddenTokenFilter(org.netbeans.modules.cnd.antlr.TokenStream a) { super(a); }
  protected void consume() throws org.netbeans.modules.cnd.antlr.TokenStreamException {}
  public org.netbeans.modules.cnd.antlr.collections.impl.BitSet getDiscardMask() { return null; }
  public org.netbeans.modules.cnd.antlr.CommonHiddenStreamToken getHiddenAfter(org.netbeans.modules.cnd.antlr.CommonHiddenStreamToken a) { return null; }
  public org.netbeans.modules.cnd.antlr.CommonHiddenStreamToken getHiddenBefore(org.netbeans.modules.cnd.antlr.CommonHiddenStreamToken a) { return null; }
  public org.netbeans.modules.cnd.antlr.collections.impl.BitSet getHideMask() { return null; }
  public org.netbeans.modules.cnd.antlr.CommonHiddenStreamToken getInitialHiddenToken() { return null; }
  public void hide(int a) {}
  public void hide(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) {}
  protected org.netbeans.modules.cnd.antlr.CommonHiddenStreamToken LA(int a) { return null; }
  public org.netbeans.modules.cnd.antlr.Token nextToken() throws org.netbeans.modules.cnd.antlr.TokenStreamException { return null; }
}
