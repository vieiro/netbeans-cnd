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
package org.netbeans.modules.mercurial.remote.ui.properties;

/**
 *
 * 
 */
public class HgPropertiesNode {

    private final  String name;
    private String value;
    
    /** Creates a new instance of HgPropertiesNodes */
    public HgPropertiesNode(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }    
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }    
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof HgPropertiesNode) {
            if (name.equals(((HgPropertiesNode) o).getName()) && value.equals(((HgPropertiesNode) o).getValue())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
