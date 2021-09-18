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
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.elasticsearch.clients.ElasticSearchClient;
import org.apache.camel.kafkaconnector.elasticsearch.common.ElasticSearchCommon;
import org.apache.camel.kafkaconnector.elasticsearch.common.ElasticSearchIndexMessageProducer;
import org.apache.camel.test.infra.elasticsearch.services.ElasticSearchService;
import org.apache.camel.test.infra.elasticsearch.services.ElasticSearchServiceFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@DisabledIfSystemProperty(named = "kafka.instance.type", matches = "local-(kafka|strimzi)-container",
        disabledReason = "Hangs when running with the embedded Kafka Connect instance")
public class CamelSinkElasticSearchITCase extends CamelSinkTestSupport {
    @RegisterExtension
    public static ElasticSearchService elasticSearch = ElasticSearchServiceFactory.createService();

    private static final Logger LOG = LoggerFactory.getLogger(CamelElasticSearchPropertyFactory.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ElasticSearchClient client;
    private String topicName;

    private final int expect = 10;
    private int received;
    private final String transformKey = "index-test";


    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-elasticsearch-index-sink-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        topicName = getTopicForTest(this);
        client = new ElasticSearchClient(elasticSearch.getElasticSearchHost(), elasticSearch.getPort(),
                ElasticSearchCommon.DEFAULT_ELASTICSEARCH_INDEX);

        received = 0;
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            client.waitForIndex();

            LOG.debug("Waiting for data");
            client.waitForData(expect);
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(30, TimeUnit.SECONDS)) {
            SearchHits hits = client.getData();
            assertNotNull(hits);

            hits.forEach(this::verifyHit);
            assertEquals(expect, received,
                    "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    private void verifyHit(SearchHit searchHit) {
        String source = searchHit.getSourceAsString();
        LOG.debug("Search hit: {} ", source);

        assertNotNull(source);
        assertFalse(source.isEmpty());

        try {
            JsonNode rootNode = MAPPER.readTree(source);
            assertEquals(String.valueOf(received), rootNode.at("/counter").asText());
        } catch (JsonProcessingException e) {
            LOG.error("Error in parsing json elasticsearch search hit answer: " + e.getMessage() + e.getCause());
            fail();
        }

        received++;
    }

    @Test
    @Timeout(90)
    public void testIndexOperation() throws Exception {
        ConnectorPropertyFactory propertyFactory = CamelElasticSearchPropertyFactory
                .basic()
                .withTopics(topicName)
                .withClusterName(ElasticSearchCommon.DEFAULT_ELASTICSEARCH_CLUSTER)
                .withHostAddress(elasticSearch.getHttpHostAddress())
                .withIndexName(ElasticSearchCommon.DEFAULT_ELASTICSEARCH_INDEX);

        runTest(propertyFactory, new ElasticSearchIndexMessageProducer(getKafkaService().getBootstrapServers(), topicName, expect));
    }
}
