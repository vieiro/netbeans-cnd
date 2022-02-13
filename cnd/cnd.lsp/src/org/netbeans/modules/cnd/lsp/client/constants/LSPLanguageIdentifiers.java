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
package org.netbeans.modules.cnd.lsp.client.constants;

/**
 * LSP language identifiers, as defined in
 * https://microsoft.github.io/language-server-protocol/specifications/specification-3-17/#textDocumentItem
 *
 * @author antonio
 */
public enum LSPLanguageIdentifiers {
    /**
     * ABAP
     */
    abap("abap"),
    /**
     * Windows Bat
     */
    bat("bat"),
    /**
     * BibTeX
     */
    bibtex("bibtex"),
    /**
     * Clojure
     */
    clojure("clojure"),
    /**
     * Coffeescript
     */
    coffeescript("coffeescript"),
    /**
     * C
     */
    c("c"),
    /**
     * C++
     */
    cpp("cpp"),
    /**
     * C#
     */
    csharp("csharp"),
    /**
     * CSS
     */
    css("css"),
    /**
     * Diff
     */
    diff("diff"),
    /**
     * Dart
     */
    dart("dart"),
    /**
     * Dockerfile
     */
    dockerfile("dockerfile"),
    /**
     * Elixir
     */
    elixir("elixir"),
    /**
     * Erlang
     */
    erlang("erlang"),
    /**
     * F#
     */
    fsharp("fsharp"),
    /**
     * Git
     */
    git_commit("git-commit"),
    /**
     * Git
     */
    git_rebase("git-rebase"),
    /**
     * Go
     */
    go("go"),
    /**
     * Groovy
     */
    groovy("groovy"),
    /**
     * Handlebars
     */
    handlebars("handlebars"),
    /**
     * HTML
     */
    html("html"),
    /**
     * Ini
     */
    ini("ini"),
    /**
     * Java
     */
    java("java"),
    /**
     * JavaScript
     */
    javascript("javascript"),
    /**
     * JavaScript React
     */
    javascriptreact("javascriptreact"),
    /**
     * JSON
     */
    json("json"),
    /**
     * LaTeX
     */
    latex("latex"),
    /**
     * Less
     */
    less("less"),
    /**
     * Lua
     */
    lua("lua"),
    /**
     * Makefile
     */
    makefile("makefile"),
    /**
     * Markdown
     */
    markdown("markdown"),
    /**
     * Objective-C
     */
    objective_c("objective-c"),
    /**
     * Objective-C++
     */
    objective_cpp("objective-cpp"),
    /**
     * Perl
     */
    perl("perl"),
    /**
     * Perl 6
     */
    perl6("perl6"),
    /**
     * PHP
     */
    php("php"),
    /**
     * Powershell
     */
    powershell("powershell"),
    /**
     * Pug
     */
    jade("jade"),
    /**
     * Python
     */
    python("python"),
    /**
     * R
     */
    r("r"),
    /**
     * Razor (cshtml)
     */
    razor("razor"),
    /**
     * Ruby
     */
    ruby("ruby"),
    /**
     * Rust
     */
    rust("rust"),
    /**
     * SCSS (syntax using curly braces)
     */
    scss("scss"),
    /**
     * SCSS (indented syntax)
     */
    sass("sass"),
    /**
     * Scala
     */
    scala("scala"),
    /**
     * ShaderLab
     */
    shaderlab("shaderlab"),
    /**
     * Shell Script (Bash)
     */
    shellscript("shellscript"),
    /**
     * SQL
     */
    sql("sql"),
    /**
     * Swift
     */
    swift("swift"),
    /**
     * TypeScript
     */
    typescript("typescript"),
    /**
     * TypeScript React
     */
    typescriptreact("typescriptreact"),
    /**
     * TeX
     */
    tex("tex"),
    /**
     * Visual Basic
     */
    vb("vb"),
    /**
     * XML
     */
    xml("xml"),
    /**
     * XSL
     */
    xsl("xsl"),
    /**
     * YAML
     */
    yaml("yaml");

    public final String id;
    LSPLanguageIdentifiers(String id) {
        this.id = id;
    }
}
