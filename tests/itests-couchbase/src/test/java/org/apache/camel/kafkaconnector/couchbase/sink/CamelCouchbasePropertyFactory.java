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

import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

public class CamelCouchbasePropertyFactory extends SinkConnectorPropertyFactory<CamelCouchbasePropertyFactory> {

    public CamelCouchbasePropertyFactory withProtocol(String value) {
        return setProperty("camel.kamelet.couchbase-sink.protocol", value);
    }

    public CamelCouchbasePropertyFactory withHostname(String value) {
        return setProperty("camel.kamelet.couchbase-sink.couchbaseHostname", value);
    }

    public CamelCouchbasePropertyFactory withPort(int value) {
        return setProperty("camel.kamelet.couchbase-sink.couchbasePort", value);
    }

    public CamelCouchbasePropertyFactory withBucket(String value) {
        return setProperty("camel.kamelet.couchbase-sink.bucket", value);
    }

    public CamelCouchbasePropertyFactory withUsername(String value) {
        return setProperty("camel.kamelet.couchbase-sink.username", value);
    }

    public CamelCouchbasePropertyFactory withPassword(String value) {
        return setProperty("camel.kamelet.couchbase-sink.password", value);
    }

    public static CamelCouchbasePropertyFactory basic() {
        return new CamelCouchbasePropertyFactory()
                    .withTasksMax(1)
                    .withName("CamelCouchbasesinkSinkConnector")
                    .withConnectorClass("org.apache.camel.kafkaconnector.couchbasesink.CamelCouchbasesinkSinkConnector")
                    .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .setProperty("camel.component.kamelet.location", "kamelets");
    }
}
