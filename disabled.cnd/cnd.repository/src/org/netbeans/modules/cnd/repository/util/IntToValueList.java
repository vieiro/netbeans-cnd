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
package org.netbeans.modules.cnd.repository.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.modules.cnd.repository.disk.RepositoryImplUtil;
import org.netbeans.modules.cnd.repository.testbench.Stats;
import org.netbeans.modules.cnd.utils.CndUtils;
import org.netbeans.modules.cnd.utils.cache.FilePathCache;

/**
 * Maps strings to integers and vice versa. Used to make persistence storage
 * more compact
 */
public final class IntToValueList<T> {

    public static final CharSequenceFactory CHAR_SEQUENCE_FACTORY = new CharSequenceFactory();
    private final Object cacheLock = new Object();
    private final List<T> cache;
    private final Exception creationStack;
    private final int version;
    private final long timestamp;
    private transient final CharSequence traceName;
    private transient final boolean dummy;

    private IntToValueList(long timestamp, CharSequence traceName, boolean dummy) {
        //this.factory = factory;
        this.traceName = traceName;
        creationStack = Stats.TRACE_IZ_215449 ? new Exception("CREATED " + (dummy ? "INVALID for " : "for ") + traceName + " from Thread=" + Thread.currentThread().getName()) : null; // NOI18N
        this.cache = new ArrayList<T>();
        this.version = RepositoryImplUtil.getVersion();
        this.timestamp = timestamp;
        this.dummy = dummy;
    }

    public static <T> IntToValueList<T> createEmpty(CharSequence unitName) {
        return new IntToValueList<T>(System.currentTimeMillis(), unitName, false);
    }

    public static <T> IntToValueList<T> createDummy(long ts, CharSequence unitName) {
        return new IntToValueList<T>(ts, unitName, true);
    }

    public static <T> IntToValueList<T> createFromStream(DataInput stream, CharSequence unitName, Factory<T> factory) throws IOException {
        return new IntToValueList<T>(stream, unitName, factory);
    }

    private void assertNotDummy() {
        if (isDummy() && CndUtils.isDebugMode() && !CndUtils.isUnitTestMode()) {
            new IllegalStateException("ACCESS INVALID cache for " + traceName + " Thread=" + Thread.currentThread().getName()).printStackTrace(System.err); // NOI18N
            if (creationStack != null) {
                creationStack.printStackTrace(System.err);
            }
        }
    }

    private IntToValueList(DataInput stream, CharSequence traceName, Factory<T> factory) throws IOException {
        //this.factory = factory;
        this.dummy = false;
        this.traceName = traceName;
        creationStack = Stats.TRACE_IZ_215449 ? new Exception("DESERIALIZED for " + traceName + " Thread=" + Thread.currentThread().getName()) : null; // NOI18N
        assert stream != null;

        cache = new ArrayList<T>();
        version = stream.readInt();

        timestamp = stream.readLong();

        int size = stream.readInt();

        for (int i = 0; i < size; i++) {
            T value = factory.read(stream);
            T v = factory.intern(value);
            cache.add(v);
        }
    }

    public List<T> getTable() {
        return new ArrayList<T>(cache);
    }

    public void set(int id, T value) {
        if (cache.size() > id) {
            assert value.equals(cache.get(id));
        }
        if (cache.size() < id) {
            // TODO:FIXME this is a workaround for a race
            synchronized (cacheLock) {
                if (cache.size() < id) {
                    if (CndUtils.isDebugMode() && !CndUtils.isUnitTestMode()) {
                        CndUtils.assertTrue(false, "Trying yo add id " + id + " while cache size is " + cache.size()); //NOI18N
                    }
                    int delta = id - cache.size();
                    for (int i = 0; i < delta; i++) {
                        cache.add(null);
                    }
                }
            }
        }
        cache.add(null);
        cache.set(id, value);
    }

    @Override
    public String toString() {
        return (dummy ? "INVALID " : "") + "IntToValueCache{" + "version=" + version + ", timestamp=" + timestamp + ", unitName=" + traceName + '}'; // NOI18N
    }
    /*
     * Persists the master index: unit name <-> integer index
     *
     */

    public void write(DataOutput stream) throws IOException {
        assertNotDummy();
        assert cache != null;
        assert stream != null;

        stream.writeInt(version);
        stream.writeLong(timestamp);

        int size = cache.size();
        stream.writeInt(size);

        for (int i = 0; i < size; i++) {
            T value = cache.get(i);
            if (value == null) {
                stream.writeUTF("");
            } else {
                stream.writeUTF(value.toString());
            }
        }
    }

    public boolean isDummy() {
        return dummy;
    }

    public T getValueById(int id) {
        assertNotDummy();
        return cache.get(id);
    }

    public boolean containsId(int id) {
        assertNotDummy();
        return 0 <= id && id < cache.size();
    }

    public int size() {
        assertNotDummy();
        return cache.size();
    }

    public int getVersion() {
        assertNotDummy();
        return version;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Exception getCreationStack() {
        return creationStack;
    }

    public static interface Factory<T> {

        T intern(T value); // FilePathCache.getManager().getString(value);

        T read(DataInput stream) throws IOException; // stream.readUTF();
    }

    public static class CharSequenceFactory implements Factory<CharSequence> {

        @Override
        public CharSequence intern(CharSequence value) {
            return FilePathCache.getManager().getString(value);
        }

        @Override
        public CharSequence read(DataInput stream) throws IOException {
            return stream.readUTF();
        }
    };
}
