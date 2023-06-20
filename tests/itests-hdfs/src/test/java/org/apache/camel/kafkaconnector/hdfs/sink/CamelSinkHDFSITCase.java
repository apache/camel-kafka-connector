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
package org.apache.camel.kafkaconnector.hdfs.sink;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageProducer;
import org.apache.camel.kafkaconnector.hdfs.utils.HDFSEasy;
import org.apache.camel.test.infra.common.TestUtils;
import org.apache.camel.test.infra.hdfs.v2.services.HDFSService;
import org.apache.camel.test.infra.hdfs.v2.services.HDFSServiceFactory;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled("Not working with Camel 4")
public class CamelSinkHDFSITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static HDFSService hdfsService = HDFSServiceFactory.createSingletonService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkHDFSITCase.class);

    private HDFSEasy hdfsEasy;
    private Path currentBasePath;
    private String topicName;

    private final int expect = 10;

    private static class CustomProducer extends StringMessageProducer {
        public CustomProducer(String bootstrapServer, String topicName, int count) {
            super(bootstrapServer, topicName, count);
        }

        @Override
        public String testMessageContent(int current) {
            return "Sink test message: " + current;
        }
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-hdfs-kafka-connector"};
    }

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, InitializationError {
        topicName = getTopicForTest(this);
        hdfsEasy = new HDFSEasy(hdfsService.getHDFSHost(), hdfsService.getPort());

        String currentPath = "/test" + TestUtils.randomWithRange(0, 256) + "/";
        currentBasePath = new Path(currentPath);

        boolean hdfsServiceCorrectlyStarted = TestUtils.waitFor(() -> hdfsEasy.createFile(new Path(currentBasePath, "initTest"), "test")
                                                                        &&  hdfsEasy.delete(new Path(currentBasePath, "initTest")));

        if (hdfsServiceCorrectlyStarted) {
            if (!hdfsEasy.delete(currentBasePath)) {
                // This is OK: directory may not exist on the path
                LOG.debug("The directory at {} was not removed", currentBasePath.getName());
            }
        } else {
            throw new InitializationError("HDFS Service didn't start properly.");
        }
    }

    @AfterEach
    public void tearDown() {
        if (!hdfsEasy.delete(currentBasePath)) {
            LOG.warn("The directory at {} was not removed", currentBasePath.getName());
        }
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            TestUtils.waitFor(this::filesCreated);
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(30, TimeUnit.SECONDS)) {
            boolean filesCreated = filesCreated();
            assertTrue(filesCreated, "The files were not created on the remote host");

            try {
                assertEquals(hdfsEasy.countFiles(currentBasePath), expect, "The number of files created vs expected do not match");

                final String baseMessage = "Sink test message: ";
                hdfsEasy.listFiles(currentBasePath)
                        .stream()
                        .filter(f -> !f.getPath().getName().contains(".opened"))
                        .forEach(f -> printFile(f, baseMessage));
            } catch (IOException e) {
                fail(e.getMessage());
            }

        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    private boolean filesCreated() {
        return hdfsEasy.filesCreated(currentBasePath, expect);
    }

    private void printFile(LocatedFileStatus f, String matchString) {
        try {
            String contents = hdfsEasy.readFile(f.getPath());

            LOG.debug("Retrieved file {} with contents: {}", f.getPath(), contents);
            boolean contains = contents.contains(matchString);
            assertTrue(contains, "Unexpected content for the remote file " + f.getPath().getName() + " content: [" + contents + "] should contain [" + matchString + "]");
        } catch (IOException e) {
            LOG.debug("Reading returned file {} failed: {}", f.getPath(), e.getMessage());
            fail("I/O error: " + e.getMessage());
        }
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws Exception {
        ConnectorPropertyFactory connectorPropertyFactory = CamelHDFSPropertyFactory
                .basic()
                .withTopics(topicName)
                .withHostname(hdfsService.getHDFSHost())
                .withPort(hdfsService.getPort())
                .withPath(currentBasePath.getName())
                .withSplitStrategy("MESSAGES:1,IDLE:1000");

        runTest(connectorPropertyFactory, new CustomProducer(getKafkaService().getBootstrapServers(), topicName, expect));
    }
}
