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

// This class automatically generated with introspection from "BitSet.java"
package org.netbeans.modules.cnd.antlr.collections.impl;
public class BitSet implements java.lang.Cloneable {
  protected static final int BITS = 64;
  protected static final int NIBBLE = 4;
  protected static final int LOG_BITS = 6;
  protected static final int MOD_MASK = 63;
  protected long[] bits;
  public BitSet() {}
  public BitSet(long... a) {}
  public BitSet(int a) {}
  public void add(int a) {}
  public org.netbeans.modules.cnd.antlr.collections.impl.BitSet and(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) { return null; }
  public void andInPlace(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) {}
  public void clear() {}
  public void clear(int a) {}
  public java.lang.Object clone() { return null; }
  public int degree() { return 0; }
  public boolean equals(java.lang.Object a) { return false; }
  public static org.netbeans.modules.cnd.antlr.collections.impl.Vector getRanges(int[] a) { return null; }
  public void growToInclude(int a) {}
  public boolean member(int a) { return false; }
  public boolean nil() { return false; }
  public org.netbeans.modules.cnd.antlr.collections.impl.BitSet not() { return null; }
  public void notInPlace() {}
  public void notInPlace(int a) {}
  public void notInPlace(int a, int b) {}
  public static org.netbeans.modules.cnd.antlr.collections.impl.BitSet of(int a) { return null; }
  public org.netbeans.modules.cnd.antlr.collections.impl.BitSet or(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) { return null; }
  public void orInPlace(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) {}
  public void remove(int a) {}
  public int size() { return 0; }
  public int lengthInLongWords() { return 0; }
  public boolean subset(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) { return false; }
  public void subtractInPlace(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) {}
  public int[] toArray() { return null; }
  public long[] toPackedArray() { return null; }
  public java.lang.String toString() { return null; }
  public java.lang.String toString(java.lang.String a) { return null; }
  public java.lang.String toString(java.lang.String a, org.netbeans.modules.cnd.antlr.CharFormatter b) { return null; }
  public java.lang.String toString(java.lang.String a, org.netbeans.modules.cnd.antlr.collections.impl.Vector b) { return null; }
  public java.lang.String toStringOfHalfWords() { return null; }
  public java.lang.String toStringOfWords() { return null; }
  public java.lang.String toStringWithRanges(java.lang.String a, org.netbeans.modules.cnd.antlr.CharFormatter b) { return null; }
}
