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
package org.netbeans.modules.subversion.remote.client.cli.commands;

import java.io.IOException;
import org.netbeans.modules.subversion.remote.api.ISVNNotifyListener;
import org.netbeans.modules.subversion.remote.client.cli.SvnCommand;
import org.netbeans.modules.subversion.remote.util.Context;
import org.netbeans.modules.remotefs.versioning.api.VCSFileProxySupport;
import org.openide.filesystems.FileSystem;

/**
 *
 * 
 */
public class RelocateCommand extends SvnCommand {

    private final String from;
    private final String to;
    private final String path;
    private final boolean rec;
    private final Context context;

    public RelocateCommand(FileSystem fileSystem, Context context, String from, String to, String path, boolean rec) {
        super(fileSystem);
        this.context = context;
        this.from = from;
        this.to = to;
        this.path = path;
        this.rec = rec;
    }

    @Override
    protected boolean notifyOutput() {
        return false;
    }    
    
    @Override
    protected ISVNNotifyListener.Command getCommand() {
        return ISVNNotifyListener.Command.RELOCATE;
    }
    
    @Override
    public void prepareCommand(Arguments arguments) throws IOException {                     
        arguments.add("switch"); //NOI18N
        arguments.add("--relocate"); //NOI18N
        if (!rec) {
            arguments.add("-N"); //NOI18N
        }            
        arguments.add(from);
        arguments.add(to);
        arguments.add(path);    
        setCommandWorkingDirectory(VCSFileProxySupport.getResource(context.getFileSystem(), path));                
    }
    
}
