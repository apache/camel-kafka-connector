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

package org.apache.camel.kafkaconnector.netty.sink;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

final class CamelNettyPropertyFactory extends SinkConnectorPropertyFactory<CamelNettyPropertyFactory> {

    private CamelNettyPropertyFactory() {
    }

    public CamelNettyPropertyFactory withProtocol(String value) {
        return setProperty("camel.sink.path.protocol", value);
    }

    public CamelNettyPropertyFactory withHost(String value) {
        return setProperty("camel.sink.path.host", value);
    }

    public CamelNettyPropertyFactory withPort(int value) {
        return setProperty("camel.sink.path.port", value);
    }

    public CamelNettyPropertyFactory withDisconnect(boolean value) {
        return setProperty("camel.sink.endpoint.disconnect", value);
    }

    public CamelNettyPropertyFactory withSync(boolean value) {
        return setProperty("camel.sink.endpoint.sync", value);
    }

    public EndpointUrlBuilder<CamelNettyPropertyFactory> withUrl(String protocol, String host, int port) {
        String url = String.format("netty:%s://%s:%s", protocol, host, port);
        return new EndpointUrlBuilder<>(this::withSinkUrl, url);
    }

    public static CamelNettyPropertyFactory basic() {
        return new CamelNettyPropertyFactory()
                .withName("CamelNettySinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.netty.CamelNettySinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
