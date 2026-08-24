/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.kafkaconnector.file.transformers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.camel.component.file.GenericFile;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileTransformsTest {

    @TempDir
    private Path tempDir;

    private SourceRecord recordOf(Object value) {
        return new SourceRecord(null, null, "mytopic", 0, null, null, null, value, null);
    }

    private SourceRecord recordFor(File file) {
        GenericFile<File> genericFile = new GenericFile<>();
        genericFile.setFile(file);
        return recordOf(genericFile);
    }

    @Test
    public void testReadsFileContentAsString() throws IOException {
        File file = tempDir.resolve("content.txt").toFile();
        Files.write(file.toPath(), "hello".getBytes(StandardCharsets.UTF_8));

        SourceRecord transformed = new FileTransforms<SourceRecord>().apply(recordFor(file));

        assertEquals("hello", transformed.value());
        assertNotNull(transformed.valueSchema());
    }

    @Test
    public void testUnreadableFileFailsTheRecordInsteadOfEmittingANullValue() {
        File missing = tempDir.resolve("missing.txt").toFile();

        FileTransforms<SourceRecord> transform = new FileTransforms<>();
        SourceRecord record = recordFor(missing);

        ConnectException e = assertThrows(ConnectException.class, () -> transform.apply(record));

        assertInstanceOf(IOException.class, e.getCause());
    }

    @Test
    public void testRecordWithANullValueIsPassedThrough() {
        SourceRecord record = recordOf(null);

        assertSame(record, new FileTransforms<SourceRecord>().apply(record));
    }

    @Test
    public void testRecordOfAnUnexpectedTypeIsPassedThrough() {
        SourceRecord record = recordOf("not a file");

        assertSame(record, new FileTransforms<SourceRecord>().apply(record));
    }
}
