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

package org.apache.camel.kafkaconnector.clients.elasticsearch;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticSearchClient {
    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchClient.class);

    private final RestHighLevelClient client;
    private final String index;

    public ElasticSearchClient(int port, String index) {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", port, "http")));

        this.index = index;
    }

    public boolean indexExists() {
        try {
            GetIndexRequest indexRequest = new GetIndexRequest(index);

            return client.indices().exists(indexRequest, RequestOptions.DEFAULT);
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

    public SearchHits getData() {
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            searchSourceBuilder.query(QueryBuilders.matchAllQuery());

            searchRequest.source(searchSourceBuilder);

            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

            return response.getHits();

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
        SearchHits searchHits = getData();

        if (searchHits == null) {
            LOG.debug("There are not search hit to return");

            return false;
        }

        SearchHit[] hits = searchHits.getHits();
        if (hits == null) {
            LOG.debug("Empty data set");

            return false;
        }

        int count = hits.length;

        if (count != expect) {
            LOG.debug("Not enough records: {} available, but {} expected", count, expect);

            return false;
        }

        return true;
    }

    private <T> void waitFor(Predicate<T> resourceCheck, T payload) {
        boolean state;
        int retries = 30;
        int waitTime = 1000;
        do {
            try {
                state = resourceCheck.test(payload);

                if (!state) {
                    LOG.debug("The resource is not yet available. Waiting {} seconds before retrying",
                            TimeUnit.MILLISECONDS.toSeconds(waitTime));
                    retries--;
                    Thread.sleep(waitTime);
                }
            } catch (InterruptedException e) {
                break;
            }

        } while (!state && retries > 0);
    }

    private void waitFor(BooleanSupplier resourceCheck) {
        boolean state;
        int retries = 30;
        int waitTime = 1000;
        do {
            try {
                state = resourceCheck.getAsBoolean();

                if (!state) {
                    LOG.debug("The resource is not yet available. Waiting {} seconds before retrying",
                            TimeUnit.MILLISECONDS.toSeconds(waitTime));
                    retries--;
                    Thread.sleep(waitTime);
                }
            } catch (InterruptedException e) {
                break;
            }

        } while (!state && retries > 0);
    }

    public void waitForIndex() {
        waitFor(this::indexExists);
    }

    public void waitForData(int expect) {
        waitFor(this::hasData, expect);
    }
}
