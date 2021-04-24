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

// This class automatically generated with introspection from "InputBufferEventSupport.java"
package org.netbeans.modules.cnd.antlr.debug;
public class InputBufferEventSupport {
  protected static final int CONSUME = 0;
  protected static final int LA = 1;
  protected static final int MARK = 2;
  protected static final int REWIND = 3;
  public InputBufferEventSupport(java.lang.Object a) {}
  public void addInputBufferListener(org.netbeans.modules.cnd.antlr.debug.InputBufferListener a) {}
  public void fireConsume(char a) {}
  public void fireEvent(int a, org.netbeans.modules.cnd.antlr.debug.ListenerBase b) {}
  public void fireEvents(int a, java.util.Vector b) {}
  public void fireLA(char a, int b) {}
  public void fireMark(int a) {}
  public void fireRewind(int a) {}
  public java.util.Vector getInputBufferListeners() { return null; }
  protected void refresh(java.util.Vector a) {}
  public void refreshListeners() {}
  public void removeInputBufferListener(org.netbeans.modules.cnd.antlr.debug.InputBufferListener a) {}
}
