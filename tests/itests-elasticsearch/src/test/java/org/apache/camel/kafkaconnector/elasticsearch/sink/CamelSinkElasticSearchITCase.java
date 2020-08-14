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

package org.apache.camel.kafkaconnector.elasticsearch.sink;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.elasticsearch.clients.ElasticSearchClient;
import org.apache.camel.kafkaconnector.elasticsearch.common.ElasticSearchCommon;
import org.apache.camel.kafkaconnector.elasticsearch.services.ElasticSearchService;
import org.apache.camel.kafkaconnector.elasticsearch.services.ElasticSearchServiceFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Testcontainers
public class CamelSinkElasticSearchITCase extends AbstractKafkaTest {
    @RegisterExtension
    public static ElasticSearchService elasticSearch = ElasticSearchServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelElasticSearchPropertyFactory.class);

    private ElasticSearchClient client;

    private final int expect = 10;
    private int received;
    private final String transformKey = "index-test";

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-elasticsearch-rest-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        client = elasticSearch.getClient();
        received = 0;
    }

    private void putRecords(CountDownLatch latch) {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        try {
            for (int i = 0; i < expect; i++) {
                try {
                    kafkaClient.produce(TestUtils.getDefaultTestTopic(this.getClass()), "test");
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

    private void verifyHit(SearchHit searchHit) {
        String source = searchHit.getSourceAsString();

        assertTrue(source != null);
        assertFalse(source.isEmpty());

        // TODO: this is not enough, we need to parse the json and check the key itself
        assertTrue(source.contains(transformKey));

        LOG.debug("Search hit: {} ", searchHit.getSourceAsString());
        received++;
    }

    public void runTest(ConnectorPropertyFactory propertyFactory) throws ExecutionException, InterruptedException {
        propertyFactory.log();
        getKafkaConnectService().initializeConnector(propertyFactory);

        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> putRecords(latch));

        if (!latch.await(30, TimeUnit.SECONDS)) {
            fail("Timed out wait for data to be added to the Kafka cluster");
        }

        LOG.debug("Waiting for indices");

        client.waitForIndex();

        LOG.debug("Waiting for data");
        client.waitForData(expect);

        SearchHits hits = client.getData();

        assertNotNull(hits);

        hits.forEach(this::verifyHit);
        assertEquals(expect, received, "Did not receive the same amount of messages sent");

        LOG.debug("Created the consumer ... About to receive messages");
    }


    @Test
    @Timeout(90)
    public void testIndexOperation() {
        try {
            String topic = TestUtils.getDefaultTestTopic(this.getClass());

            ConnectorPropertyFactory propertyFactory = CamelElasticSearchPropertyFactory
                    .basic()
                    .withTopics(topic)
                    .withOperation("Index")
                    .withClusterName(ElasticSearchCommon.DEFAULT_ELASTICSEARCH_CLUSTER)
                    .withHostAddress(elasticSearch.getHttpHostAddress())
                    .withIndexName(ElasticSearchCommon.DEFAULT_ELASTICSEARCH_INDEX)
                    .withTransformsConfig("ElasticSearchTransformer")
                        .withEntry("type", "org.apache.camel.kafkaconnector.elasticsearch.sink.transform.ConnectRecordValueToMapTransformer")
                        .withEntry("key", transformKey)
                        .end();

            runTest(propertyFactory);

            LOG.debug("Created the consumer ... About to receive messages");
        } catch (Exception e) {
            LOG.error("ElasticSearch test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Test
    @Timeout(90)
    public void testIndexOperationUsingUrl() {
        try {
            String topic = TestUtils.getDefaultTestTopic(this.getClass());

            ConnectorPropertyFactory propertyFactory = CamelElasticSearchPropertyFactory
                    .basic()
                    .withTopics(topic)
                    .withUrl(ElasticSearchCommon.DEFAULT_ELASTICSEARCH_CLUSTER)
                        .append("hostAddresses", elasticSearch.getHttpHostAddress())
                        .append("operation", "Index")
                        .append("indexName", ElasticSearchCommon.DEFAULT_ELASTICSEARCH_INDEX)
                        .buildUrl()
                    .withTransformsConfig("ElasticSearchTransformer")
                        .withEntry("type", "org.apache.camel.kafkaconnector.elasticsearch.sink.transform.ConnectRecordValueToMapTransformer")
                        .withEntry("key", transformKey)
                        .end();

            runTest(propertyFactory);

            LOG.debug("Created the consumer ... About to receive messages");
        } catch (Exception e) {
            LOG.error("ElasticSearch test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }
}
