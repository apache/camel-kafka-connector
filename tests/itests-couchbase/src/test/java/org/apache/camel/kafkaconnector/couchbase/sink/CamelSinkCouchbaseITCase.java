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

package org.apache.camel.kafkaconnector.couchbase.sink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.couchbase.client.core.diagnostics.EndpointPingReport;
import com.couchbase.client.core.diagnostics.PingResult;
import com.couchbase.client.core.diagnostics.PingState;
import com.couchbase.client.core.service.ServiceType;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.manager.bucket.BucketSettings;
import com.couchbase.client.java.query.QueryResult;
import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.test.infra.couchbase.services.CouchbaseService;
import org.apache.camel.test.infra.couchbase.services.CouchbaseServiceFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/*
 This test is slow and flaky. It tends to fail on systems with limited resources and slow I/O. Therefore, it is
 disabled by default. Also, suffers from bugs in the couchbase test container:
 - https://github.com/testcontainers/testcontainers-java/issues/2993

 Therefore, this test is marked as flaky and only runs if specifically enabled.
 */
@EnabledIfSystemProperty(named = "enable.flaky.tests", matches = "true")
public class CamelSinkCouchbaseITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static CouchbaseService service = CouchbaseServiceFactory.getService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkCouchbaseITCase.class);

    private String bucketName;
    private String topic;

    private Cluster cluster;

    private final int expect = 10;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-couchbase-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        bucketName = "testBucket" + TestUtils.randomWithRange(0, 100);
        cluster = Cluster.connect(service.getConnectionString(), service.getUsername(), service.getPassword());

        cluster.ping().endpoints().entrySet().forEach(this::checkEndpoints);

        LOG.debug("Creating a new bucket named {}", bucketName);

        cluster.buckets().createBucket(BucketSettings.create(bucketName));
        PingResult pingResult = cluster.bucket(bucketName).ping();
        pingResult.endpoints().entrySet().forEach(this::checkEndpoints);

        LOG.debug("Bucket created");

        topic = TestUtils.getDefaultTestTopic(this.getClass()) + TestUtils.randomWithRange(0, 100);

        try {
            String startDelay = System.getProperty("couchbase.test.start.delay", "1000");

            int delay = Integer.parseInt(startDelay);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupted();
        }
    }

    private void checkEndpoints(Map.Entry<ServiceType, List<EndpointPingReport>> entries) {
        entries.getValue().forEach(this::checkStatus);
    }

    private void checkStatus(EndpointPingReport endpointPingReport) {
        if (endpointPingReport.state() == PingState.OK) {
            LOG.debug("Endpoint {} is ok", endpointPingReport.id());
        } else {
            LOG.warn("Endpoint {} is not OK", endpointPingReport.id());
        }
    }

    @AfterEach
    public void tearDown() {
        LOG.debug("Dropping the test bucket named {}", bucketName);
        cluster.buckets().dropBucket(bucketName);
        LOG.debug("Bucket dropped");

        cluster.disconnect();
    }

    private void produceMessages(CountDownLatch latch) {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        try {
            for (int i = 0; i < expect; i++) {
                Map<String, String> parameters = new HashMap<>();

                parameters.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CCB_ID", String.valueOf(i));

                JsonObject jsonObject = JsonObject.create().put("data", String.format("test-%d", i));

                try {
                    kafkaClient.produce(topic, jsonObject.toString(), parameters);
                } catch (ExecutionException e) {
                    LOG.error("Unable to produce messages: {}", e.getMessage(), e);
                } catch (InterruptedException e) {
                    break;
                }
            }
        } finally {
            latch.countDown();
        }
    }

    private boolean waitForMinimumRecordCount() {
        try {
            String query = String.format("select count(*) as count from `%s`", bucketName);
            QueryResult queryResult = cluster.query(query);
            List<JsonObject> results = queryResult.rowsAsObject();

            if (results.isEmpty()) {
                return false;
            }

            int size = results.get(0).getInt("count");
            if (size < expect) {
                LOG.info("There are only {} records at the moment", size);

                return false;
            }

            return size == expect;
        } catch (Exception e) {
            LOG.warn("Exception while waiting for the records to arrive: {}", e.getMessage(), e);
        }

        return false;
    }

    private void verifyRecords() {
        String query = String.format("select * from `%s` USE KEYS \"1\"", bucketName);
        QueryResult queryResult = cluster.query(query);

        List<JsonObject> results = queryResult.rowsAsObject();

        assertFalse(results.isEmpty(), "There should be at least 1 record on the result");
        LOG.debug("Received record: {}", results.get(0));
    }

    public void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws Exception {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

        LOG.debug("Creating the producer and sending messages ...");
        ExecutorService service = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(1);
        service.submit(() -> produceMessages(latch));

        assertTrue(TestUtils.waitFor(this::waitForMinimumRecordCount));

        LOG.debug("Waiting for the test to complete");
        if (latch.await(110, TimeUnit.SECONDS)) {
            verifyRecords();
        } else {
            fail("Failed to receive the records within the specified time");
        }
    }

    @Disabled("Not formatting the URL correctly - issue #629")
    @Test
    @Timeout(90)
    public void testBasicSendReceive() throws Exception {
        ConnectorPropertyFactory factory = CamelCouchbasePropertyFactory.basic()
                .withTopics(topic)
                .withBucket(bucketName)
                .withProtocol("http")
                .withHostname(service.getHostname())
                .withPort(service.getPort())
                .withUsername(service.getUsername())
                .withPassword(service.getPassword());

        runTest(factory);
    }

    @RepeatedTest(10)
    @Timeout(90)
    public void testBasicSendReceiveUsingUrl() throws Exception {
        ConnectorPropertyFactory factory = CamelCouchbasePropertyFactory.basic()
                .withTopics(topic)
                .withUrl("http", service.getHostname(), service.getPort())
                    .append("bucket", bucketName)
                    .append("username", service.getUsername())
                    .append("password", service.getPassword())
                    .append("connectTimeout", 5000)
                    .append("queryTimeout", 5000)
                    .append("producerRetryAttempts", 10)
                    .append("producerRetryPause", 7500)
                    .buildUrl();


        runTest(factory);
    }
}
