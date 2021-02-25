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

package org.apache.camel.kafkaconnector.netty.source;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;

final class CamelNettyPropertyFactory extends SourceConnectorPropertyFactory<CamelNettyPropertyFactory> {

    private CamelNettyPropertyFactory() {
    }

    public CamelNettyPropertyFactory withProtocol(String value) {
        return setProperty("camel.source.path.protocol", value);
    }

    public CamelNettyPropertyFactory withHost(String value) {
        return setProperty("camel.source.path.host", value);
    }

    public CamelNettyPropertyFactory withPort(int value) {
        return setProperty("camel.source.path.port", value);
    }

    public CamelNettyPropertyFactory withSync(boolean value) {
        return setProperty("camel.source.endpoint.sync", value);
    }

    public EndpointUrlBuilder<CamelNettyPropertyFactory> withUrl(String protocol, String host, int port) {
        String url = String.format("netty:%s://%s:%s", protocol, host, port);
        return new EndpointUrlBuilder<>(this::withSourceUrl, url);
    }

    public static CamelNettyPropertyFactory basic() {
        return new CamelNettyPropertyFactory()
                .withName("CamelNettySourceConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.netty.CamelNettySourceConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
