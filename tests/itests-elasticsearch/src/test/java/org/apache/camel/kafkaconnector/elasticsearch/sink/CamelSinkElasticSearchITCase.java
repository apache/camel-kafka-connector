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

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.elasticsearch.clients.ElasticSearchClient;
import org.apache.camel.kafkaconnector.elasticsearch.common.ElasticSearchCommon;
import org.apache.camel.kafkaconnector.elasticsearch.common.ElasticSearchIndexMessageProducer;
import org.apache.camel.kafkaconnector.elasticsearch.common.ElasticSearchLocalContainerTestService;
import org.apache.camel.test.infra.elasticsearch.services.ElasticSearchService;
import org.apache.camel.test.infra.elasticsearch.services.ElasticSearchServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import static org.apache.camel.test.infra.elasticsearch.services.ElasticSearchServiceFactory.builder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class CamelSinkElasticSearchITCase extends CamelSinkTestSupport {

    @RegisterExtension
    public static ElasticSearchService elasticSearch = builder()
            .addLocalMapping(new Supplier<ElasticSearchService>() {
                    @Override
                    public ElasticSearchService get() {
                        ElasticsearchContainer container =
                                new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:8.5.2");
                        container.addEnv("xpack.security.enabled", "true");
                        return new ElasticSearchLocalContainerTestService(container);
                    }
                }
            ).addRemoteMapping(ElasticSearchServiceFactory.RemoteElasticSearchTestService::new).build();

    private static final Logger LOG = LoggerFactory.getLogger(CamelElasticSearchPropertyFactory.class);

    private ElasticSearchClient client;
    private String topicName;

    private final int expect = 10;
    private int received;

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
            List<Hit<ObjectNode>> hits = client.getData();
            assertNotNull(hits);

            hits.forEach(this::verifyHit);
            assertEquals(expect, received,
                    "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail("Failed to receive the messages within the specified time");
        }
    }

    private void verifyHit(Hit<ObjectNode> searchHit) {
        ObjectNode source = searchHit.source();
        LOG.debug("Search hit: {} ", source);

        assertNotNull(source);
        assertFalse(source.isEmpty());

        assertEquals(String.valueOf(received), source.at("/counter").asText());

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
