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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
public class CamelSinkFileITCase extends CamelSinkTestSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkFileITCase.class);

    private static final String SINK_DIR = CamelSinkFileITCase.class.getResource(".").getPath();
    private static final String FILENAME = "test.txt";

    private String topicName;
    private final int expect = 1;

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
    }

    @Override
    protected String testMessageContent(int current) {
        return "test";
    }

    @Override
    protected Map<String, String> messageHeaders(String text, int current) {
        return null;
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            File sinkFile = new File(SINK_DIR, FILENAME);
            File doneFile = new File(SINK_DIR, FILENAME + ".done");

            waitForFile(sinkFile, doneFile);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(30, TimeUnit.SECONDS)) {
            File sinkFile = new File(SINK_DIR, FILENAME);

            assertTrue(sinkFile.exists(), String.format("The file %s does not exist", sinkFile.getPath()));

            try {
                checkFileContents(sinkFile);
            } catch (IOException e) {
                fail(e.getMessage());
            }
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    private void checkFileContents(File sinkFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(sinkFile));

        int i = 0;
        String line;
        do {
            line = reader.readLine();
            if (line != null) {
                assertEquals("test", line, String.format("Unexpected data: %s", line));
                i++;
            }
        } while (line != null);

        assertEquals(expect, i, "Did not receive the same amount of messages that were sent");
    }

    private void waitForFile(File sinkFile, File doneFile) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = sinkFile.getParentFile().toPath();

        if (doneFile.exists()) {
            return;
        }

        // We watch for both the file creation and truncation
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

        int retries = 30;
        do {
            WatchKey watchKey = watchService.poll(1, TimeUnit.SECONDS);

            if (watchKey == null) {
                continue;
            }

            for (WatchEvent<?> event : watchKey.pollEvents()) {

                /*
                  It should return a Path object for ENTRY_CREATE and ENTRY_MODIFY events
                 */
                Object context = event.context();
                if (!(context instanceof Path)) {
                    LOG.warn("Received an unexpected event of kind {} for context {}", event.kind(), event.context());
                    continue;
                }

                Path contextPath = (Path) context;

                if (contextPath.toString().equals(doneFile.getName())) {
                    LOG.info("Sink file at the build path {} had a matching event of type: {}", sinkFile.getPath(),
                            event.kind());

                    return;
                } else {
                    LOG.debug("Ignoring a watch event at build path {} of type {} for file: {}", sinkFile.getPath(),
                            event.kind(), contextPath.getFileName());
                }
            }
            watchKey.reset();
            retries--;
        } while (!doneFile.exists() && retries > 0);
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelFilePropertyFactory.basic()
                .withTopics(topicName)
                .withDirectoryName(SINK_DIR)
                .withFileName(FILENAME)
                .withDoneFileName(FILENAME + ".done");

        runTest(connectorPropertyFactory, topicName, expect);
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceiveUsingUrl() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelFilePropertyFactory.basic()
                .withTopics(topicName)
                .withUrl(SINK_DIR)
                .append("fileName", FILENAME)
                .append("doneFileName", FILENAME + ".done")
                .buildUrl();

        runTest(connectorPropertyFactory, topicName, expect);
    }
}
