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

package org.apache.camel.kafkaconnector.sink.elasticsearch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.ContainerUtil;
import org.apache.camel.kafkaconnector.KafkaConnectRunner;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.elasticsearch.ElasticSearchClient;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import static org.junit.Assert.fail;

public class CamelSinkElasticSearchITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelElasticSearchPropertyFactory.class);
    // This is required in order to use the Open Source one by default
    private static final String ELASTIC_SEARCH_CONTAINER = "docker.elastic.co/elasticsearch/elasticsearch-oss:7.3.2";

    private static final int ELASTIC_SEARCH_PORT = 9200;

    @Rule
    public ElasticsearchContainer elasticsearch = new ElasticsearchContainer(ELASTIC_SEARCH_CONTAINER);

    private KafkaConnectRunner kafkaConnectRunner;
    private ElasticSearchClient client;

    private final int expect = 10;
    private int received;
    private final String transformKey = "index-test";

    @Before
    public void setUp() {
        ContainerUtil.waitForHttpInitialization(elasticsearch, elasticsearch.getMappedPort(ELASTIC_SEARCH_PORT));

        final String elasticSearchInstance = elasticsearch
                .getHttpHostAddress();

        LOG.info("ElasticSearch instance running at {}", elasticSearchInstance);

        String topic = TestCommon.getDefaultTestTopic(this.getClass());
        CamelElasticSearchPropertyFactory testProperties = new CamelElasticSearchIndexPropertyFactory(1, topic,
                TestCommon.DEFAULT_ELASTICSEARCH_CLUSTER,
                elasticSearchInstance, TestCommon.DEFAULT_ELASTICSEARCH_INDEX, transformKey);

        kafkaConnectRunner = getKafkaConnectRunner();
        kafkaConnectRunner.getConnectorPropertyProducers().add(testProperties);

        client = new ElasticSearchClient(elasticsearch.getMappedPort(ELASTIC_SEARCH_PORT),
                TestCommon.DEFAULT_ELASTICSEARCH_INDEX);
    }

    private void putRecords(CountDownLatch latch) {
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        try {
            for (int i = 0; i < expect; i++) {
                try {
                    kafkaClient.produce(TestCommon.getDefaultTestTopic(this.getClass()), "test");
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

        Assert.assertTrue(source != null);
        Assert.assertFalse(source.isEmpty());

        // TODO: this is not enough, we need to parse the json and check the key itself
        Assert.assertTrue(source.contains(transformKey));

        LOG.debug("Search hit: {} ", searchHit.getSourceAsString());
        received++;
    }



    @Test(timeout = 90000)
    public void testIndexOperation() {
        try {
            CountDownLatch latch = new CountDownLatch(2);
            ExecutorService service = Executors.newCachedThreadPool();
            service.submit(() -> kafkaConnectRunner.run(latch));
            service.submit(() -> putRecords(latch));

            latch.await(30, TimeUnit.SECONDS);

            LOG.debug("Waiting for indices");

            client.waitForIndex();

            LOG.debug("Waiting for data");
            client.waitForData(expect);

            SearchHits hits = client.getData();

            hits.forEach(this::verifyHit);
            Assert.assertEquals("Did not receive the same amount of messages sent", expect, received);

            LOG.debug("Created the consumer ... About to receive messages");
        } catch (Exception e) {
            LOG.error("ElasticSearch test failed: {}", e.getMessage(), e);
            fail(e.getMessage());
        } finally {
            kafkaConnectRunner.stop();
        }
    }
}
