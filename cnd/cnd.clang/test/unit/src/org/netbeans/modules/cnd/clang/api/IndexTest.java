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

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.netbeans.junit.NbTestCase;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.LocalFileSystem;

/**
 *
 * @author antonio
 */
public class IndexTest extends NbTestCase {

    static {
        System.loadLibrary("nblibclang");
    }

    public IndexTest(String name) {
        super(name);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addTranslationUnit method, of class Index.
     */
    @Test
    public void testAddTranslationUnit() throws Exception {
        System.out.println("addTranslationUnit");
        try (Index index = new Index()) {
            System.out.format("Index = %s%n", index);
            FileObject test1 = TestUtil.getTestFile("testfiles/test1.c");
            TranslationUnit unit = index.addTranslationUnit(test1);
            Assert.assertEquals(1, index.getTranslationUnits().size());
            // If we close the unit then it should also be removed from the list of index compilation units.
            unit.close();
            Assert.assertEquals(0, index.getTranslationUnits().size());
            System.out.format("Done.%n");
        }
    }

    @Test
    public void testTranslationUnitWithUnsavedBuffer() throws Exception {
        System.out.println("addTranslationUnit");
        try (Index index = new Index()) {
            System.out.format("Index = %s%n", index);
            FileObject test1 = TestUtil.getTestFile("testfiles/empty.c");
            TranslationUnit unit = index.addTranslationUnit(test1);
            unit.setUnsavedBuffer("#include <stdio.h>\n"
                    + " int main(int argc, const char * argv) {\n"
                    + "   return (0);\n"
                    + " }");
            Assert.assertEquals(1, index.getTranslationUnits().size());

            // If we close the unit then it should also be removed from the list of index compilation units.
            unit.close();
            Assert.assertEquals(0, index.getTranslationUnits().size());
            System.out.format("Done.%n");
        }
    }

    /**
     * Test of deallocate method, of class Index.
     */
    @Test
    public void testDeallocate() throws IOException {
        System.out.println("deallocate");
        try (Index instance = new Index()) {
            Assert.assertNotEquals(0L, instance.getPointer());
        }
    }

    /**
     * Test of getTranslationUnits method, of class Index.
     */
    @Test
    public void testGetTranslationUnits() throws IOException {
        System.out.println("getTranslationUnits");
        try (Index instance = new Index()) {
            Collection<TranslationUnit> result = instance.getTranslationUnits();
            Assert.assertEquals(0, result.size());
        }
    }

}
