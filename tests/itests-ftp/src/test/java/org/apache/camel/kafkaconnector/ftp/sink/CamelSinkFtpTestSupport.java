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

package org.apache.camel.kafkaconnector.ftp.sink;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageProducer;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Sends records to the sink connector and checks the files written on the server. The name of each file comes from
 * the CamelHeader.file Kafka header of the record, which the connector maps to the file header of the Kamelet.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class CamelSinkFtpTestSupport extends CamelSinkTestSupport {
    protected static final String DIRECTORY = "sink";

    private static final int EXPECT = 3;

    private String topic;

    private static class FileNameHeaderProducer extends StringMessageProducer {
        FileNameHeaderProducer(String bootstrapServer, String topicName, int count) {
            super(bootstrapServer, topicName, count);
        }

        @Override
        public Map<String, String> messageHeaders(String text, int current) {
            return Collections.singletonMap(CamelSinkTask.HEADER_CAMEL_PREFIX + "file", fileName(current));
        }

        @Override
        public String testMessageContent(int current) {
            return fileContent(current);
        }
    }

    /**
     * @return the local path of the directory the connector writes the files to
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
    protected void consumeMessages(CountDownLatch latch) {
        // nothing to consume from Kafka: verifyMessages checks the files written on the server
        latch.countDown();
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) {
        final Path directory = localDirectory();

        Awaitility.await()
                .atMost(Duration.ofSeconds(60))
                .untilAsserted(() -> {
                    for (int i = 0; i < EXPECT; i++) {
                        Path file = directory.resolve(fileName(i));

                        assertTrue(Files.exists(file), "The file " + file + " was not written");
                        assertEquals(fileContent(i), Files.readString(file), "Unexpected content in " + file);
                    }
                });
    }

    @Test
    @Timeout(180)
    public void testStoreFiles() throws Exception {
        runTest(connectorPropertyFactory().withTopics(topic),
                new FileNameHeaderProducer(getKafkaService().getBootstrapServers(), topic, EXPECT));
    }

    private static String fileName(int current) {
        return "sink-test-" + current + ".txt";
    }

    private static String fileContent(int current) {
        return "Camel Kafka Connector sink test " + current;
    }
}
