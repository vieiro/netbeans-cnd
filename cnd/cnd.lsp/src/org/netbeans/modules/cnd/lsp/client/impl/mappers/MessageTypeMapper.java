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
package org.netbeans.modules.cnd.lsp.client.impl.mappers;

import java.util.logging.Level;
import org.eclipse.lsp4j.MessageType;
import org.openide.NotifyDescriptor;

/**
 * Maps MessageType to NetBeans
 *
 * @author antvie
 */
public final class MessageTypeMapper {

    private static MessageTypeMapper INSTANCE = new MessageTypeMapper();

    public static MessageTypeMapper getInstance() {
        return INSTANCE;
    }

    private MessageTypeMapper() {
    }

    public int messageType2NotifyDescriptorType(MessageType messageType) {
        if (messageType == null) {
            return NotifyDescriptor.ERROR_MESSAGE;
        }
        switch (messageType) {
            case Info:
                return NotifyDescriptor.INFORMATION_MESSAGE;
            case Log:
                return NotifyDescriptor.PLAIN_MESSAGE;
            case Warning:
                return NotifyDescriptor.WARNING_MESSAGE;
            default:
            case Error:
                return NotifyDescriptor.ERROR_MESSAGE;
        }
    }

    public Level messageType2Level(MessageType messageType) {
        if (messageType == null) {
            return Level.SEVERE;
        }
        switch (messageType) {
            case Warning:
                return Level.WARNING;
            case Info:
                return Level.INFO;
            case Log:
                return Level.INFO;
            case Error:
            default:
                return Level.SEVERE;
        }

    }

}
