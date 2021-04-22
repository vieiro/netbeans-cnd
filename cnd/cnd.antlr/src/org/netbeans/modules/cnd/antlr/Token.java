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

// This class automatically generated with introspection from "Token.java"
package org.netbeans.modules.cnd.antlr;
public interface Token extends java.lang.Cloneable {
  public static final int MIN_USER_TYPE = 4;
  public static final int NULL_TREE_LOOKAHEAD = 3;
  public static final int INVALID_TYPE = 0;
  public static final int EOF_TYPE = 1;
  public static final int SKIP = -1;
  public abstract int getColumn();
  public abstract void setColumn(int a);
  public abstract int getLine();
  public abstract void setLine(int a);
  public abstract java.lang.String getFilename();
  public abstract void setFilename(java.lang.String a);
  public abstract java.lang.String getText();
  public abstract void setText(java.lang.String a);
  public abstract int getType();
  public abstract void setType(int a);
}
