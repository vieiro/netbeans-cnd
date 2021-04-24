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
// This class automatically generated with introspection from "CharScanner.java"
package org.netbeans.modules.cnd.antlr;

public abstract class CharScanner extends org.netbeans.modules.cnd.antlr.MatchExceptionState implements org.netbeans.modules.cnd.antlr.TokenStream {

    public static final char EOF_CHAR = '\uffff';
    protected final org.netbeans.modules.cnd.antlr.ANTLRStringBuffer text = null;
    protected boolean saveConsumedInput;
    protected java.lang.Class tokenObjectClass;
    protected boolean caseSensitive;
    protected boolean caseSensitiveLiterals;
    protected java.util.Map<org.netbeans.modules.cnd.antlr.ANTLRHashString, java.lang.Integer> literals;
    protected int tabsize;
    protected org.netbeans.modules.cnd.antlr.Token _returnToken;
    protected final org.netbeans.modules.cnd.antlr.ANTLRHashString hashString = null;
    protected final org.netbeans.modules.cnd.antlr.LexerSharedInputState inputState = null;
    protected final org.netbeans.modules.cnd.antlr.InputBuffer input = null;
    public int guessing;
    protected boolean commitToPath;
    protected int traceDepth;

    protected CharScanner(org.netbeans.modules.cnd.antlr.LexerSharedInputState a, org.netbeans.modules.cnd.antlr.InputBuffer b) {
        super();
    }

    public CharScanner(org.netbeans.modules.cnd.antlr.InputBuffer a) {
        super();
    }

    protected CharScanner() {

    }

    public void append(char a) {
    }

    public void append(java.lang.String a) {
    }

    public void consume() {
    }

    public void consumeUntil(int a) {
    }

    public void consumeUntil(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) {
    }

    public boolean getCaseSensitive() {
        return false;
    }

    public final boolean getCaseSensitiveLiterals() {
        return false;
    }

    public int getColumn() {
        return 0;
    }

    public void setColumn(int a) {
    }

    public boolean getCommitToPath() {
        return false;
    }

    public java.lang.String getFilename() {
        return null;
    }

    public org.netbeans.modules.cnd.antlr.InputBuffer getInputBuffer() {
        return null;
    }

    public org.netbeans.modules.cnd.antlr.LexerSharedInputState getInputState() {
        return null;
    }

    public int getLine() {
        return 0;
    }

    public java.lang.String getText() {
        return null;
    }

    public org.netbeans.modules.cnd.antlr.Token getTokenObject() {
        return null;
    }

    public char LA(int a) {
        return 0;
    }

    protected org.netbeans.modules.cnd.antlr.Token createToken(int a) throws java.lang.InstantiationException, java.lang.IllegalAccessException {
        return null;
    }

    protected final int getTokenStartColumn() {
        return 0;
    }

    protected final int getTokenStartLine() {
        return 0;
    }

    protected org.netbeans.modules.cnd.antlr.Token makeToken(int a) {
        return null;
    }

    protected void setTokenText(org.netbeans.modules.cnd.antlr.Token a, char[] b, int c, int d) {
    }

    public int mark() {
        return 0;
    }

    public void match(char a) throws org.netbeans.modules.cnd.antlr.MismatchedCharException {
    }

    public void match(org.netbeans.modules.cnd.antlr.collections.impl.BitSet a) throws org.netbeans.modules.cnd.antlr.MismatchedCharException {
    }

    public void match(java.lang.String a) throws org.netbeans.modules.cnd.antlr.MismatchedCharException {
    }

    public void matchNot(char a) throws org.netbeans.modules.cnd.antlr.MismatchedCharException {
    }

    public void matchRange(char a, char b) throws org.netbeans.modules.cnd.antlr.MismatchedCharException {
    }

    public void newline() {
    }

    public void tab() {
    }

    public void setTabSize(int a) {
    }

    public int getTabSize() {
        return 0;
    }

    public void panic() {
    }

    public void panic(java.lang.String a) {
    }

    public void reportError(org.netbeans.modules.cnd.antlr.RecognitionException a) {
    }

    public void reportError(java.lang.String a) {
    }

    public void reportWarning(java.lang.String a) {
    }

    public void resetText() {
    }

    public void rewind(int a) {
    }

    public void setCaseSensitive(boolean a) {
    }

    public void setCommitToPath(boolean a) {
    }

    public void setFilename(java.lang.String a) {
    }

    public void setLine(int a) {
    }

    public void setText(java.lang.String a) {
    }

    public void setTokenObjectClass(java.lang.String a) {
    }

    public int testLiteralsTable(int a) {
        return 0;
    }

    public int testLiteralsTable(java.lang.String a, int b) {
        return 0;
    }

    public char toLower(char a) {
        return 0;
    }

    public void traceIndent() {
    }

    public void traceIn(java.lang.String a) {
    }

    public void traceOut(java.lang.String a) {
    }

    public void uponEOF() throws org.netbeans.modules.cnd.antlr.TokenStreamException {
    }
}
