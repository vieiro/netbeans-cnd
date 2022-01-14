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
package org.netbeans.modules.cnd.makeproject.compilationdb;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import org.json.simple.JSONObject;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;

/**
 * CommandObjectBuilder builds a JSON string representing a CommandObject as
 * defined in https://clang.llvm.org/docs/JSONCompilationDatabase.html
 * 
 * NOTES:
 * 
 * 1. It seems clangd wants paths separated by forward slashes, even in Windows.
 * 2. It seems clangd does not want quoted forward slashes.
 * 
 * See:
 * - https://github.com/15knots/cmake4eclipse/issues/44
 * - https://stackoverflow.com/questions/1580647/json-why-are-forward-slashes-escaped
 *
 * @author antonio
 */
final class CommandObjectBuilder {

    private static final Logger LOGGER = Logger.getLogger(CommandObjectBuilder.class.getName());

    private final Project project;
    private final StringBuilder command;
    private String file;
    private String output;

    CommandObjectBuilder(@Nonnull Project project) {
        this.project = project;
        this.command = new StringBuilder();
    }

    CommandObjectBuilder addCommandItem(String part) {
        command.append(' ').append(part).append(' ');
        return this;
    }

    CommandObjectBuilder setFile(String file) {
        this.file = Paths.get(file).toAbsolutePath().toString();
        return this;
    }

    CommandObjectBuilder setOutput(String output) {
        this.output = Paths.get(output).toAbsolutePath().toString();
        return this;
    }

    String getFile() {
        return file;
    }

    JSONObject build() throws IllegalStateException, InvalidPathException {
        if (file == null) {
            throw new IllegalStateException("Missing file"); // NOI18N
        }
        if (command.length() == 0) {
            throw new IllegalStateException("No command");
        }

        JSONObject object = new JSONObject();

        // directory
        FileObject projectDirectory = project.getProjectDirectory();
        Path directory = Paths.get(projectDirectory.toURI());
        directory = directory.toAbsolutePath();
        LOGGER.log(Level.SEVERE, "Directory {0}", directory.toString());
        object.put("directory", directory.toString());

        // file
        LOGGER.log(Level.SEVERE, "File {0}", file);
        object.put("file", file);

        // command
        object.put("command", command.toString().trim());
        LOGGER.log(Level.SEVERE, "Command {0}", command.toString());

        // output (this one is optional)
        if (output != null) {
            object.put("output", output);
        }
        return object;
    }

}
