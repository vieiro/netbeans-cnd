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

import java.io.File;
import org.junit.Assert;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.LocalFileSystem;

/**
 *
 */
public final class TestUtil {

    private static LocalFileSystem fs = null;

    /**
     * As specified in nbproject/project.properties
     * @return A properly initialized LocalFilesystem in the test directory.
     */
    public static FileSystem getTestDirectory() throws Exception {

        if (fs != null) {
            return fs;
        }

        String baseDirectory = System.getProperty("tests.directory");
        if (baseDirectory == null) {
            System.err.format("Please set -Dtests.directory=test/testfiles before running tests.\n");
            Assert.fail("System property tests.directory not found");
        }
        File file = new File(baseDirectory);
        if (!file.exists() || !file.isDirectory() || !file.canRead()) {
            String message = String.format("tests.directory = %s is not a directory or does not exist.", baseDirectory);
            Assert.fail(message);
        }
        fs = new LocalFileSystem();
        fs.setRootDirectory(file);
        return fs;
    }

    public static FileObject getTestFile(String name) throws Exception {
        FileSystem fs = getTestDirectory();

        FileObject fo = fs.getRoot().getFileObject(name);

        Assert.assertNotNull(fo);

        return fo;

    }

}
