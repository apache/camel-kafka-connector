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

package org.apache.camel.kafkaconnector.nettyhttp.sink;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

final class CamelNettyhttpPropertyFactory extends SinkConnectorPropertyFactory<CamelNettyhttpPropertyFactory> {

    private CamelNettyhttpPropertyFactory() {
    }

    public CamelNettyhttpPropertyFactory withProtocol(String value) {
        return setProperty("camel.sink.path.protocol", value);
    }

    public CamelNettyhttpPropertyFactory withHost(String value) {
        return setProperty("camel.sink.path.host", value);
    }

    public CamelNettyhttpPropertyFactory withPort(int value) {
        return setProperty("camel.sink.path.port", value);
    }

    public CamelNettyhttpPropertyFactory withPath(String value) {
        return setProperty("camel.sink.path.path", value);
    }

    public CamelNettyhttpPropertyFactory withDisconnect(boolean value) {
        return setProperty("camel.sink.endpoint.disconnect", value);
    }

    public CamelNettyhttpPropertyFactory withSync(boolean value) {
        return setProperty("camel.sink.endpoint.sync", value);
    }

    public EndpointUrlBuilder<CamelNettyhttpPropertyFactory> withUrl(String protocol, String host, int port, String path) {
        String url = String.format("netty-http:%s://%s:%s/%s", protocol, host, port, path);
        return new EndpointUrlBuilder<>(this::withSinkUrl, url);
    }

    public static CamelNettyhttpPropertyFactory basic() {
        return new CamelNettyhttpPropertyFactory()
                .withName("CamelNettyhttpSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.nettyhttp.CamelNettyhttpSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
