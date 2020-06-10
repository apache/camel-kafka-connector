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

package org.apache.camel.kafkaconnector.cassandra.sink;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

final class CamelCassandraPropertyFactory extends SinkConnectorPropertyFactory<CamelCassandraPropertyFactory> {
    private CamelCassandraPropertyFactory() {

    }

    public CamelCassandraPropertyFactory withKeySpace(String keySpace) {
        return setProperty("camel.sink.path.keyspace", keySpace);
    }

    public CamelCassandraPropertyFactory withCql(String cql) {
        return setProperty("camel.sink.endpoint.cql", cql);
    }

    public CamelCassandraPropertyFactory withHosts(String hosts) {
        return setProperty("camel.sink.path.hosts", hosts);
    }

    public CamelCassandraPropertyFactory withPort(int port) {
        return withPort(Integer.toString(port));
    }

    public CamelCassandraPropertyFactory withPort(String port) {
        return setProperty("camel.sink.path.port", port);
    }

    public CamelCassandraPropertyFactory withCluster(String cluster) {
        return setProperty("camel.sink.endpoint.cluster", cluster);
    }

    public EndpointUrlBuilder<CamelCassandraPropertyFactory> withUrl(String host, String keySpace) {
        String queueUrl = String.format("cql://%s/%s", host, keySpace);

        return new EndpointUrlBuilder<>(this::withSinkUrl, queueUrl);
    }


    public static CamelCassandraPropertyFactory basic() {
        return new CamelCassandraPropertyFactory()
                .withName("CamelCqlSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.cql.CamelCqlSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
