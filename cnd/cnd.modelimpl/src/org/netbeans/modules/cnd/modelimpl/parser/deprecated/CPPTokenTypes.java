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
package org.netbeans.modules.cnd.modelimpl.parser.deprecated;

/**
 * These are randomnly assignated values.
 * (grep -r CPPTokenTypes | cleanup | sort | uniq)
 */
public interface CPPTokenTypes {


    int AMPERSAND = 1;
    int AND = 2;
    int ASSIGNEQUAL = 3;
    int COLON = 4;
    int COMMA = 5;
    int CSM_ARRAY_DECLARATION = 6;
    int CSM_ASM_BLOCK = 7;
    int CSM_BASE_SPECIFIER = 8;
    int CSM_BREAK_STATEMENT = 9;
    int CSM_CASE_STATEMENT = 10;
    int CSM_CATCH_CLAUSE = 11;
    int CSM_CLASS_DECLARATION = 12;
    int CSM_COMPOUND_STATEMENT = 13;
    int CSM_COMPOUND_STATEMENT_LAZY = 14;
    int CSM_CONDITION = 15;
    int CSM_CONTINUE_STATEMENT = 16;
    int CSM_CTOR_DECLARATION = 17;
    int CSM_CTOR_DEFINITION = 18;
    int CSM_CTOR_INITIALIZER = 19;
    int CSM_CTOR_INITIALIZER_LIST = 20;
    int CSM_CTOR_TEMPLATE_DECLARATION = 21;
    int CSM_CTOR_TEMPLATE_DEFINITION = 22;
    int CSM_DECLARATION_STATEMENT = 23;
    int CSM_DEFAULT_STATEMENT = 24;
    int CSM_DO_WHILE_STATEMENT = 25;
    int CSM_DTOR_DECLARATION = 26;
    int CSM_DTOR_DEFINITION = 27;
    int CSM_DTOR_TEMPLATE_DEFINITION = 28;
    int CSM_END = 29;
    int CSM_ENUM_DECLARATION = 30;
    int CSM_ENUMERATOR_LIST = 31;
    int CSM_ENUM_FWD_DECLARATION = 32;
    int CSM_EXPRESSION = 33;
    int CSM_EXPRESSIONS_END = 34;
    int CSM_EXPRESSIONS_START = 35;
    int CSM_EXPRESSION_STATEMENT = 36;
    int CSM_FIELD = 37;
    int CSM_FOR_INIT_STATEMENT = 38;
    int CSM_FOR_STATEMENT = 39;
    int CSM_FUNCTION_DECLARATION = 40;
    int CSM_FUNCTION_DEFINITION = 41;
    int CSM_FUNCTION_LIKE_VARIABLE_DECLARATION = 42;
    int CSM_FUNCTION_LIKE_VARIABLE_TEMPLATE_DECLARATION = 43;
    int CSM_FUNCTION_RET_FUN_DECLARATION = 44;
    int CSM_FUNCTION_RET_FUN_DEFINITION = 45;
    int CSM_FUNCTION_TEMPLATE_DECLARATION = 46;
    int CSM_FUNCTION_TEMPLATE_DEFINITION = 47;
    int CSM_FWD_TEMPLATE_EXPLICIT_SPECIALIZATION = 48;
    int CSM_GENERIC_DECLARATION = 49;
    int CSM_GOTO_STATEMENT = 50;
    int CSM_IF_STATEMENT = 51;
    int CSM_KR_PARMLIST = 52;
    int CSM_LABELED_STATEMENT = 53;
    int CSM_LINKAGE_SPECIFICATION = 54;
    int CSM_NAMESPACE_ALIAS = 55;
    int CSM_NAMESPACE_DECLARATION = 56;
    int CSM_PARAMETER_DECLARATION = 57;
    int CSM_PARMLIST = 58;
    int CSM_PTR_OPERATOR = 59;
    int CSM_QUALIFIED_ID = 60;
    int CSM_RETURN_STATEMENT = 61;
    int CSM_STATEMENTS_END = 62;
    int CSM_STATEMENTS_START = 63;
    int CSM_SWITCH_STATEMENT = 64;
    int CSM_TEMPLATE_CLASS_DECLARATION = 65;
    int CSM_TEMPLATE_CTOR_DEFINITION_EXPLICIT_SPECIALIZATION = 66;
    int CSM_TEMPLATE_DTOR_DEFINITION_EXPLICIT_SPECIALIZATION = 67;
    int CSM_TEMPLATE_EXPLICIT_INSTANTIATION = 68;
    int CSM_TEMPLATE_EXPLICIT_SPECIALIZATION = 69;
    int CSM_TEMPLATE_FUNCTION_DEFINITION_EXPLICIT_SPECIALIZATION = 70;
    int CSM_TEMPLATE_PARMLIST = 71;
    int CSM_TEMPLATE_TEMPLATE_PARAMETER = 72;
    int CSM_TEMPL_FWD_CL_OR_STAT_MEM = 73;
    int CSM_THROW_STATEMENT = 74;
    int CSM_TRY_CATCH_STATEMENT_LAZY = 75;
    int CSM_TRY_STATEMENT = 76;
    int CSM_TYPE_ALIAS = 77;
    int CSM_TYPE_ATOMIC = 78;
    int CSM_TYPE_BUILTIN = 79;
    int CSM_TYPE_COMPOUND = 80;
    int CSM_TYPE_DECLTYPE = 81;
    int CSM_USER_TYPE_CAST_DECLARATION = 82;
    int CSM_USER_TYPE_CAST_DEFINITION = 83;
    int CSM_USER_TYPE_CAST_DEFINITION_EXPLICIT_SPECIALIZATION = 84;
    int CSM_USER_TYPE_CAST_TEMPLATE_DECLARATION = 85;
    int CSM_USER_TYPE_CAST_TEMPLATE_DEFINITION = 86;
    int CSM_USING_DECLARATION = 87;
    int CSM_USING_DIRECTIVE = 88;
    int CSM_VARIABLE_DECLARATION = 89;
    int CSM_VARIABLE_LIKE_FUNCTION_DECLARATION = 90;
    int CSM_VARIABLE_TEMPLATE_DECLARATION = 91;
    int CSM_VISIBILITY_REDEF = 92;
    int CSM_WHILE_STATEMENT = 93;
    int ELLIPSIS = 94;
    int EOF = 95;
    int GREATERTHAN = 96;
    int ID = 97;
    int IDENT = 98;
    int LCURLY = 99;
    int LESSTHAN = 100;
    int LITERAL__Atomic = 101;
    int LITERAL_auto = 102;
    int LITERAL_class = 103;
    int LITERAL___const = 104;
    int LITERAL___const__ = 105;
    int LITERAL_const = 106;
    int LITERAL_constexpr = 107;
    int LITERAL_default = 108;
    int LITERAL_delete = 109;
    int LITERAL_else = 110;
    int LITERAL_enum = 111;
    int LITERAL_explicit = 112;
    int LITERAL_extern = 113;
    int LITERAL_final = 114;
    int LITERAL_friend = 115;
    int LITERAL___global = 116;
    int LITERAL___hidden = 117;
    int LITERAL___inline = 118;
    int LITERAL___inline__ = 119;
    int LITERAL__inline = 120;
    int LITERAL_inline = 121;
    int LITERAL_mutable = 122;
    int LITERAL_noexcept = 123;
    int LITERAL_OPERATOR = 124;
    int LITERAL_override = 125;
    int LITERAL_private = 126;
    int LITERAL_protected = 127;
    int LITERAL_public = 128;
    int LITERAL_register = 129;
    int LITERAL_static = 130;
    int LITERAL_struct = 131;
    int LITERAL___symbolic = 132;
    int LITERAL_template = 133;
    int LITERAL___thread = 134;
    int LITERAL_thread_local = 135;
    int LITERAL__Thread_local = 136;
    int LITERAL_throw = 137;
    int LITERAL_try = 138;
    int LITERAL_typedef = 139;
    int LITERAL_typename = 140;
    int LITERAL___typeof = 141;
    int LITERAL___typeof__ = 142;
    int LITERAL_typeof = 143;
    int LITERAL_union = 144;
    int LITERAL_using = 145;
    int LITERAL_virtual = 146;
    int LITERAL_void = 147;
    int LITERAL___volatile = 148;
    int LITERAL___volatile__ = 149;
    int LITERAL_volatile = 150;
    int LPAREN = 151;
    int LSQUARE = 152;
    int POINTERTO = 153;
    int RCURLY = 154;
    int RPAREN = 155;
    int RSQUARE = 156;
    int SCOPE = 157;
    int SEMICOLON = 158;
    int STAR = 159;
    int STRING_LITERAL = 160;
    int TILDE = 161;
    int CSM_START = 162;

}
