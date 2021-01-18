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

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.hdfs.utils.HDFSEasy;
import org.apache.camel.test.infra.hdfs.v2.services.HDFSService;
import org.apache.camel.test.infra.hdfs.v2.services.HDFSServiceFactory;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CamelSinkHDFSITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static HDFSService hdfsService = HDFSServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkHDFSITCase.class);

    private HDFSEasy hdfsEasy;
    private Path currentBasePath;

    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-hdfs-kafka-connector"};
    }


    @BeforeEach
    public void setUp() throws IOException, URISyntaxException {
        hdfsEasy = new HDFSEasy(hdfsService.getHDFSHost(), hdfsService.getPort());

        String currentPath = "/test" + TestUtils.randomWithRange(0, 256) + "/";
        currentBasePath = new Path(currentPath);

        if (!hdfsEasy.delete(currentBasePath)) {
            // This is OK: directory may not exist on the path
            LOG.debug("The directory at {} was not removed ", currentBasePath.getName());
        }
    }

    @AfterEach
    public void tearDown() {
        if (!hdfsEasy.delete(currentBasePath)) {
            LOG.warn("The directory at {} was not removed", currentBasePath.getName());
        }
    }

    private boolean filesCreated() {
        return hdfsEasy.filesCreated(currentBasePath, expect);
    }


    private String sendKafkaMessages(String baseMessage, int count) throws java.util.concurrent.ExecutionException, InterruptedException {
        LOG.info("Sending data to Kafka");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        for (int i = 0; i < count; i++) {
            kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), baseMessage + i);
        }
        return baseMessage;
    }

    @Test
    @Timeout(90)
    public void testBasicSendReceive() {
        try {
            ConnectorPropertyFactory connectorPropertyFactory = CamelHDFSPropertyFactory
                    .basic()
                    .withTopics(TestUtils.getDefaultTestTopic(this.getClass()))
                    .withHostname(hdfsService.getHDFSHost())
                    .withPort(hdfsService.getPort())
                    .withPath(currentBasePath.getName())
                    .withSplitStrategy("MESSAGES:1,IDLE:1000");

            connectorPropertyFactory.log();
            getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

            final String baseMessage = "Sink test message: ";
            sendKafkaMessages(baseMessage, expect);

            boolean filesCreated = TestUtils.waitFor(this::filesCreated);
            assertTrue(filesCreated, "The files were not created on the remote host");
            assertEquals(hdfsEasy.countFiles(currentBasePath), expect, "The number of files created vs expected do not match");
            hdfsEasy.listFiles(currentBasePath)
                    .stream()
                    .filter(f -> !f.getPath().getName().contains(".opened"))
                    .forEach(f -> printFile(f, baseMessage));

        } catch (Exception e) {
            LOG.error("HDFS test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }



    private void printFile(LocatedFileStatus f, String matchString) {
        try {
            String contents = hdfsEasy.readFile(f.getPath());

            LOG.debug("Retrieved file {} with contents: {}", f.getPath(), contents);
            boolean contains = contents.contains(matchString);
            assertTrue(contains, "Unexpected content for the remote file " + f.getPath().getName());
        } catch (IOException e) {
            LOG.debug("Reading returned file {} failed: {}", f.getPath(), e.getMessage());
            fail("I/O error: " + e.getMessage());
        }
    }
}
