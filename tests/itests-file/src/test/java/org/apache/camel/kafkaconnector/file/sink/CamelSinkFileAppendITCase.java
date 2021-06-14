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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.file.sink.util.CustomProducer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
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
public class CamelSinkFileAppendITCase extends CamelSinkTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkFileAppendITCase.class);

    private static final String SINK_DIR = CamelSinkFileAppendITCase.class.getResource(".").getPath();
    private static final String FILENAME = "test-append.txt";

    private String topicName;
    private final int numMessages = 10;
    private final int expectedLines = 1;

    private CustomProducer producer;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-file-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
        cleanup();
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

    private String verifier(int i) {
        if (i == 0) {
            return "test0test1test2test3test4test5test6test7test8test9";
        }

        return "NO MATCH";
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(30, TimeUnit.SECONDS)) {
            File sinkFile = new File(SINK_DIR, FILENAME);

            assertTrue(sinkFile.exists(), String.format("The file %s does not exist", sinkFile.getPath()));

            try {
                int lines = checkFileContents(sinkFile, this::verifier);
                assertEquals(expectedLines, lines, "Did not receive the same amount of messages that were sent");
            } catch (IOException e) {
                fail(e.getMessage());
            }
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }


    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelFilePropertyFactory.basic()
                .withTopics(topicName)
                .withDirectoryName(SINK_DIR)
                .withFileName(FILENAME)
                .withFileExist("Append")
                .withDoneFileName(FILENAME + ".done");

        producer = new CustomProducer(getKafkaService().getBootstrapServers(), topicName, numMessages);
        runTest(connectorPropertyFactory, producer);
    }
}
