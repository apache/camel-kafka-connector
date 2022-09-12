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

import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

final class CamelCassandraPropertyFactory extends SinkConnectorPropertyFactory<CamelCassandraPropertyFactory> {
    private CamelCassandraPropertyFactory() {

    }

    public CamelCassandraPropertyFactory withKeySpace(String keySpace) {
        return setProperty("camel.kamelet.cassandra-sink.keyspace", keySpace);
    }

    public CamelCassandraPropertyFactory withQuery(String query) {
        return setProperty("camel.kamelet.cassandra-sink.query", query);
    }

    public CamelCassandraPropertyFactory withHosts(String hosts) {
        return setProperty("camel.kamelet.cassandra-sink.connectionHost", hosts);
    }

    public CamelCassandraPropertyFactory withPort(int port) {
        return withPort(Integer.toString(port));
    }

    public CamelCassandraPropertyFactory withPort(String port) {
        return setProperty("camel.kamelet.cassandra-sink.connectionPort", port);
    }

    public static CamelCassandraPropertyFactory basic() {
        return new CamelCassandraPropertyFactory()
                .withName("CamelCassandraSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.cassandrasink.CamelCassandrasinkSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .setProperty("camel.kamelet.cassandra-sink.prepareStatements", "false")
                .setProperty("camel.component.kamelet.location", "kamelets");
    }
}
