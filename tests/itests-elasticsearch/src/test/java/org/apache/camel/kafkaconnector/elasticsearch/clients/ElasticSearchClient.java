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

package org.apache.camel.kafkaconnector.elasticsearch.clients;

import java.io.IOException;
import java.util.List;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.camel.kafkaconnector.elasticsearch.common.ElasticSearchCommon;
import org.apache.camel.test.infra.common.TestUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ElasticSearchClient {
    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchClient.class);

    private final ElasticsearchClient client;
    private final String index;

    public ElasticSearchClient(String host, int port, String index) {

        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(ElasticSearchCommon.USERNAME, ElasticSearchCommon.PASSWORD));

        RestClientBuilder builder = RestClient.builder(
                        new HttpHost(host, port, "http"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(
                            HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder
                                .setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        RestClient httpClient = builder.build();
        ElasticsearchTransport transport = new RestClientTransport(
                httpClient,
                new JacksonJsonpMapper()
        );

        client = new ElasticsearchClient(transport);
        this.index = index;
    }

    public boolean indexExists() {
        try {
            ExistsRequest indexRequest = new ExistsRequest.Builder().index(index).build();

            return client.indices().exists(indexRequest).value();
        } catch (IOException e) {
            /*
                  It may return if failed to parse the response, on timeout or no response from the ES instance.
                  Assuming it is more likely to timeout or provide no reply either the during the start up or
                  on overloaded CI environments, we log the I/O error and try again
                 */
            LOG.error("I/O error trying to query for index existence: {}", e.getMessage(), e);
        }

        return false;
    }

    public List<Hit<ObjectNode>> getData() {
        try {
            SearchResponse<ObjectNode> response = client.search(s ->
                            s.index(index)
                            .query(QueryBuilders.matchAll().build()._toQuery()),
                    ObjectNode.class);

            return response.hits().hits();
        } catch (IOException e) {
            /*
              It may return if failed to parse the response, on timeout or no response from the ES instance.
              Assuming it is more likely to timeout or provide no reply either the during the start up or
              on overloaded CI environments, we log the I/O error and try again
             */
            LOG.error("I/O error trying to query for index existence: {}", e.getMessage(), e);
        } catch (Throwable e) {
            LOG.error("Unhandled error trying to query for index existence: {}", e.getMessage(), e);
        }

        return null;
    }

    private boolean hasData(int expect) {
        List<Hit<ObjectNode>> searchHits = getData();

        if (searchHits == null) {
            LOG.debug("There are not search hit to return");

            return false;
        }

        int count = searchHits.size();

        if (count != expect) {
            LOG.debug("Not enough records: {} available, but {} expected", count, expect);

            return false;
        }

        return true;
    }

    public void waitForIndex() {
        TestUtils.waitFor(this::indexExists);
    }

    public void waitForData(int expect) {
        TestUtils.waitFor(this::hasData, expect);
    }
}
