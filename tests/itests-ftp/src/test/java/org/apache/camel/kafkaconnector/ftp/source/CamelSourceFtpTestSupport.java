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

package org.apache.camel.kafkaconnector.ftp.source;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.CamelSourceTask;
import org.apache.camel.kafkaconnector.common.test.CamelSourceTestSupport;
import org.apache.camel.kafkaconnector.common.test.TestMessageConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Drops a file in the directory polled by the source connector and checks the record sent to Kafka.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class CamelSourceFtpTestSupport extends CamelSourceTestSupport {
    protected static final String DIRECTORY = "source";

    private static final String FILE_NAME = "source-test.txt";
    private static final String FILE_CONTENT = "Camel Kafka Connector source test";

    private String topic;

    /**
     * @return the local path of the directory polled by the connector
     */
    protected abstract Path localDirectory();

    /**
     * @return the connector configuration for the server under test, without the Kafka topic
     */
    protected abstract CamelFtpPropertyFactory connectorPropertyFactory();

    @BeforeEach
    public void setUp() {
        topic = getTopicForTest(this);
    }

    @Override
    protected void produceTestData() {
        try {
            Path directory = localDirectory();
            Files.createDirectories(directory);

            // write the file aside and move it in, so that the connector cannot pick it up partially written
            Path file = Files.writeString(directory.resolveSibling(FILE_NAME), FILE_CONTENT);
            Files.move(file, directory.resolve(FILE_NAME), StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            fail("Unable to create the test file: " + e.getMessage());
        }
    }

    @Override
    protected void verifyMessages(TestMessageConsumer<?> consumer) {
        List<? extends ConsumerRecord<String, ?>> records = consumer.consumedMessages();
        assertEquals(1, records.size(), "Did not receive the expected number of records");

        ConsumerRecord<String, ?> record = records.get(0);
        assertEquals(FILE_CONTENT, record.value(), "The record value is not the content of the file");

        Header fileName = record.headers().lastHeader(CamelSourceTask.HEADER_CAMEL_PREFIX + "file");
        assertNotNull(fileName, "The record does not carry the file name header");
        assertEquals(FILE_NAME, new String(fileName.value(), StandardCharsets.UTF_8), "Unexpected file name header");
    }

    @Test
    @Timeout(180)
    public void testRetrieveFile() throws ExecutionException, InterruptedException {
        runTest(connectorPropertyFactory().withKafkaTopic(topic), topic, 1);
    }
}
