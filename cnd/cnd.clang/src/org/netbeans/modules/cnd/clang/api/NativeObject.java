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
package org.netbeans.modules.cnd.clang.api;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract base class for handling native object's lifecycle.
 */
public abstract class NativeObject implements AutoCloseable {

    /**
     * Logger for native objects.
     */
    public static final Logger LOG = Logger.getLogger(NativeObject.class.getName());

    /**
     * The native pointer this class points to. If this is 0L then the instance
     * has not been natively allocated yet, or it has been freed.
     */
    private long pointer = 0L;

    /**
     * Sets a new pointer for this native object. If the native has been
     * previously allocated, then it is deallocated first.
     *
     * @param newPointer The new pointer.
     */
    protected void setPointer(long newPointer) {
        if (pointer != newPointer) {
            if (pointer != 0L) {
                try {
                    close();
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, "Error deallocating " + getClass().getName(), ex);
                }
            }
            this.pointer = newPointer;
        }
    }

    /**
     * Returns the native pointer this object points to.
     *
     * @return The native pointer.
     */
    public long getPointer() {
        if (pointer == 0L) {
            // LOG.log(Level.INFO, "Instance of type {0} is NULL.", getClass().getName());
        }
        return pointer;
    }

    /**
     * Deallocates native resources when garbage collected.
     *
     * @throws Throwable if an error happens while deallocating.
     */
    @Override
    protected void finalize() throws Throwable {
        try {
            if (pointer != 0) {
                close();
            }
        } finally {
            super.finalize();
        }
    }

    @Override
    public final void close() throws IOException {
        try {
            if (pointer != 0) {
                // LOG.log(Level.SEVERE, "Deallocating instance of type {0}", getClass().getName());
                deallocate();
            }
        } finally {
            pointer = 0;
        }
    }

    /**
     * Responsible for deallocating native objects. This method is automatically
     * invoked by the close() call.
     */
    protected abstract void deallocate();

    @Override
    public String toString() {
        return String.format("[NativeObject:%s:%X]", getClass().getName(), getPointer());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.pointer ^ (this.pointer >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NativeObject other = (NativeObject) obj;
        if (this.pointer != other.pointer) {
            return false;
        }
        return true;
    }

}
