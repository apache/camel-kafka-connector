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

package org.apache.camel.kafkaconnector.sink.file;

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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.KafkaConnectRunner;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.fail;

public class CamelSinkFileITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkFileITCase.class);

    private static final String SINK_DIR = CamelSinkFileITCase.class.getResource(".").getPath();
    private static final String FILENAME = "test.txt";

    private KafkaConnectRunner kafkaConnectRunner;

    private final int expect = 1;


    @Before
    public void setUp() {
        String url = "file://" + SINK_DIR + "?fileName=" + FILENAME + "&doneFileName=${file:name}.done";
        LOG.debug("Saving files to {}", url);

        File doneFile = new File(SINK_DIR, FILENAME + ".done");
        if (doneFile.exists()) {
            doneFile.delete();
        }

        ConnectorPropertyFactory testProperties = new CamelFilePropertyFactory(1,
                TestCommon.getDefaultTestTopic(this.getClass()), url);

        kafkaConnectRunner = getKafkaConnectRunner();
        kafkaConnectRunner.getConnectorPropertyProducers().add(testProperties);
    }

    private void putRecords() {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        for (int i = 0; i < expect; i++) {
            try {
                kafkaClient.produce(TestCommon.getDefaultTestTopic(this.getClass()), "test");
            } catch (ExecutionException e) {
                LOG.error("Unable to produce messages: {}", e.getMessage(), e);
            } catch (InterruptedException e) {
                break;
            }
        }

    }

    @Test(timeout = 90000)
    public void testBasicSendReceive() {
        try {
            ExecutorService service = Executors.newCachedThreadPool();
            service.submit(() -> kafkaConnectRunner.run());

            putRecords();

            LOG.debug("Created the consumer ... About to receive messages");

            File sinkFile = new File(SINK_DIR, FILENAME);
            File doneFile = new File(SINK_DIR, FILENAME + ".done");

            waitForFile(sinkFile, doneFile);

            Assert.assertTrue(String.format("The file %s does not exist", sinkFile.getPath()), sinkFile.exists());

            checkFileContents(sinkFile);

        } catch (Exception e) {
            LOG.error("HTTP test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        } finally {
            kafkaConnectRunner.stop();
        }
    }

    private void checkFileContents(File sinkFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(sinkFile));

        int i = 0;
        String line;
        do {
            line = reader.readLine();
            if (line != null) {
                Assert.assertEquals(String.format("Unexpected data: %s", line), "test", line);
                i++;
            }
        } while (line != null);

        Assert.assertEquals("Did not receive the same amount of messages that were sent", expect, i);
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
}
