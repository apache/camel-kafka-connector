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

package org.apache.camel.kafkaconnector.services.elasticsearch;

import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.elasticsearch.ElasticSearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

public class ElasticSearchLocalContainerService implements ElasticSearchService {
    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchLocalContainerService.class);
    private static final String DEFAULT_ELASTIC_SEARCH_CONTAINER = "docker.elastic.co/elasticsearch/elasticsearch-oss:7.3.2";
    private static final int ELASTIC_SEARCH_PORT = 9200;

    public ElasticsearchContainer container;

    public ElasticSearchLocalContainerService() {
        String containerName = System.getProperty("elasticsearch.container");

        if (containerName == null || containerName.isEmpty()) {
            containerName = DEFAULT_ELASTIC_SEARCH_CONTAINER;
        }

        container = new ElasticsearchContainer(containerName);
        container.start();
    }


    @Override
    public String getHttpHostAddress() {
        return container.getHttpHostAddress();
    }

    @Override
    public void initialize() {
        LOG.info("ElasticSearch instance running at {}", getHttpHostAddress());
    }

    @Override
    public void shutdown() {
        LOG.info("Stopping the ElasticSearch container");
        container.stop();
    }

    @Override
    public ElasticSearchClient getClient() {
        return new ElasticSearchClient(container.getContainerIpAddress(), container.getMappedPort(ELASTIC_SEARCH_PORT),
                TestCommon.DEFAULT_ELASTICSEARCH_INDEX);
    }
}
