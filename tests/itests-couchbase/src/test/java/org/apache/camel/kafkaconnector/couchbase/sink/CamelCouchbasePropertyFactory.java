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

package org.apache.camel.kafkaconnector.couchbase.sink;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

public class CamelCouchbasePropertyFactory extends SinkConnectorPropertyFactory<CamelCouchbasePropertyFactory> {

    public CamelCouchbasePropertyFactory withProtocol(String value) {
        return setProperty("camel.sink.path.protocol", value);
    }

    public CamelCouchbasePropertyFactory withHostname(String value) {
        return setProperty("camel.sink.path.hostname", value);
    }

    public CamelCouchbasePropertyFactory withPort(int value) {
        return setProperty("camel.sink.path.port", value);
    }

    public CamelCouchbasePropertyFactory withBucket(String value) {
        return setProperty("camel.sink.endpoint.bucket", value);
    }

    public CamelCouchbasePropertyFactory withCollection(String value) {
        return setProperty("camel.sink.endpoint.collection", value);
    }

    public CamelCouchbasePropertyFactory withOperation(String value) {
        return setProperty("camel.sink.endpoint.operation", value);
    }

    public CamelCouchbasePropertyFactory withUsername(String value) {
        return setProperty("camel.sink.endpoint.username", value);
    }

    public CamelCouchbasePropertyFactory withPassword(String value) {
        return setProperty("camel.sink.endpoint.password", value);
    }

    public EndpointUrlBuilder<CamelCouchbasePropertyFactory> withUrl(String protocol, String hostname, int port, String bucket) {
        String sinkUrl = String.format("couchbase:%s://%s:%d/%s", protocol, hostname, port, bucket);

        return new EndpointUrlBuilder<>(this::withSinkUrl, sinkUrl);
    }

    public static CamelCouchbasePropertyFactory basic() {
        return new CamelCouchbasePropertyFactory()
                    .withTasksMax(1)
                    .withName("CamelCouchbaseSinkConnector")
                    .withConnectorClass("org.apache.camel.kafkaconnector.couchbase.CamelCouchbaseSinkConnector")
                    .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
