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
package org.apache.camel.kafkaconnector.elasticsearch.common;

import org.testcontainers.elasticsearch.ElasticsearchContainer;

public final class ElasticSearchCommon {
    /**
     * The default ElasticSearch cluster name for usage during the tests
     */
    public static final String DEFAULT_ELASTICSEARCH_CLUSTER = "docker-cluster";

    /**
     * The default ElasticSearch index for usage during the tests
     */
    public static final String DEFAULT_ELASTICSEARCH_INDEX = "ckc-index";

    /**
     * The default ElasticSearch container username
     */
    public static final String USERNAME = "elastic";

    /**
     * The default ElasticSearch container password
     */
    public static final String PASSWORD = ElasticsearchContainer.ELASTICSEARCH_DEFAULT_PASSWORD;

    private ElasticSearchCommon() {

    }
}
