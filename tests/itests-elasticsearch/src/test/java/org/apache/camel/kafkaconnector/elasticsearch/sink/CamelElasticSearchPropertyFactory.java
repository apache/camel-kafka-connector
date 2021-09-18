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

import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

final class CamelElasticSearchPropertyFactory extends SinkConnectorPropertyFactory<CamelElasticSearchPropertyFactory> {

    private CamelElasticSearchPropertyFactory() {

    }

    public CamelElasticSearchPropertyFactory withClusterName(String clusterName) {
        return setProperty("camel.kamelet.elasticsearch-index-sink.clusterName", clusterName);
    }

    public CamelElasticSearchPropertyFactory withHostAddress(String hostAddress) {
        return setProperty("camel.kamelet.elasticsearch-index-sink.hostAddresses", hostAddress);
    }

    public CamelElasticSearchPropertyFactory withIndexName(String indexName) {
        return setProperty("camel.kamelet.elasticsearch-index-sink.indexName", indexName);
    }

    public static CamelElasticSearchPropertyFactory basic() {
        return new CamelElasticSearchPropertyFactory()
                .withName("CamelElasticSearchSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.elasticsearchindexsink.CamelElasticsearchindexsinkSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .setProperty("camel.kamelet.elasticsearch-index-sink.username", "dummy")
                .setProperty("camel.kamelet.elasticsearch-index-sink.password", "dummy")
                .setProperty("camel.kamelet.elasticsearch-index-sink.enableSSL", "false")
                .setProperty("camel.component.kamelet.location", "kamelets");
    }

}
