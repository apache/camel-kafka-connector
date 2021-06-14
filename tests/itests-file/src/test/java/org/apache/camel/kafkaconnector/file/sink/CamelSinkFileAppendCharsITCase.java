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

package org.apache.camel.kafkaconnector.file.sink;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.file.sink.util.CustomProducer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.apache.camel.kafkaconnector.file.sink.util.FileTestUtil.checkFileContents;
import static org.apache.camel.kafkaconnector.file.sink.util.FileTestUtil.waitForFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
public class CamelSinkFileAppendCharsITCase extends CamelSinkTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkFileAppendCharsITCase.class);

    private static final String SINK_DIR = CamelSinkFileAppendCharsITCase.class.getResource(".").getPath();
    private static final String FILENAME = "test-append-with-chars.txt";
    private Map<String, Function<Integer, String>> verifierTable;

    private String topicName;
    private final int numMessages = 10;
    private int expectedLines;
    private String currentChar;

    private CustomProducer producer;


    static {

    }


    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-file-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
        cleanup();

        verifierTable = new HashMap<>();

        verifierTable.put("ddd", this::verifierRegularChar);
        verifierTable.put("n", this::verifierRegularChar);
        verifierTable.put("%0A", this::verifierNewLine);
    }

    @AfterEach
    public void tearDown() {
        cleanup();
    }

    private void cleanup() {
        File doneFile = new File(SINK_DIR, FILENAME + ".done");
        if (doneFile.exists()) {
            doneFile.delete();
        }

        File testFile = new File(SINK_DIR, FILENAME);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            File sinkFile = new File(SINK_DIR, FILENAME);
            File doneFile = new File(SINK_DIR, FILENAME + ".done");

            waitForFile(sinkFile, doneFile);

            // We need to give some time for all the messages to be read and appended
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        } finally {
            latch.countDown();
        }
    }

    private String verifierRegularChar(int currentLine) {
        String expected = "";

        for (int i = 0; i < numMessages; i++) {
            expected += producer.testMessageContent(i) + currentChar;
        }

        return expected;
    }

    private String verifierNewLine(int currentLine) {
        return producer.testMessageContent(currentLine);
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(30, TimeUnit.SECONDS)) {
            File sinkFile = new File(SINK_DIR, FILENAME);

            assertTrue(sinkFile.exists(), String.format("The file %s does not exist", sinkFile.getPath()));

            try {
                Function<Integer, String> verifier = verifierTable.get(currentChar);

                int lines = checkFileContents(sinkFile, verifier);
                assertEquals(expectedLines, lines, "Did not receive the same amount of messages that were sent");
            } catch (IOException e) {
                fail(e.getMessage());
            }
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"ddd", "n"})
    @Timeout(90)
    public void testBasicSendReceiveWithAppendChar(String appendedChars) throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelFilePropertyFactory.basic()
                .withTopics(topicName)
                .withDirectoryName(SINK_DIR)
                .withFileName(FILENAME)
                .withAppendChars(appendedChars)
                .withFileExist("Append")
                .withDoneFileName(FILENAME + ".done");

        producer = new CustomProducer(getKafkaService().getBootstrapServers(), topicName, numMessages);
        expectedLines = 1;
        currentChar = appendedChars;

        runTest(connectorPropertyFactory, producer);
    }

    @ParameterizedTest
    @ValueSource(strings = {"%0A"})
    @Timeout(90)
    public void testBasicSendReceiveWithAppendSpecialChars(String appendedChars) throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelFilePropertyFactory.basic()
                .withTopics(topicName)
                .withDirectoryName(SINK_DIR)
                .withFileName(FILENAME)
                .withAppendChars(appendedChars)
                .withFileExist("Append")
                .withDoneFileName(FILENAME + ".done");

        producer = new CustomProducer(getKafkaService().getBootstrapServers(), topicName, numMessages);
        expectedLines = numMessages;
        currentChar = appendedChars;

        runTest(connectorPropertyFactory, producer);
    }
}
