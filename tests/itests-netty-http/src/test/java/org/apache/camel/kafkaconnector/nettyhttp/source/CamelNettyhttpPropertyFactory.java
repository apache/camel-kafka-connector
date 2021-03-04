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

package org.apache.camel.kafkaconnector.nettyhttp.source;

import org.apache.camel.LoggingLevel;
import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;

final class CamelNettyhttpPropertyFactory extends SourceConnectorPropertyFactory<CamelNettyhttpPropertyFactory> {

    private CamelNettyhttpPropertyFactory() {
    }

    public CamelNettyhttpPropertyFactory withProtocol(String value) {
        return setProperty("camel.source.path.protocol", value);
    }

    public CamelNettyhttpPropertyFactory withHost(String value) {
        return setProperty("camel.source.path.host", value);
    }

    public CamelNettyhttpPropertyFactory withPort(int value) {
        return setProperty("camel.source.path.port", value);
    }

    public CamelNettyhttpPropertyFactory withPath(String value) {
        return setProperty("camel.source.path.path", value);
    }

    public EndpointUrlBuilder<CamelNettyhttpPropertyFactory> withUrl(String protocol, String host, int port, String path) {
        String url = String.format("netty-http:%s://%s:%s/%s", protocol, host, port, path);
        return new EndpointUrlBuilder<>(this::withSourceUrl, url);
    }

    public static CamelNettyhttpPropertyFactory basic() {
        return new CamelNettyhttpPropertyFactory()
                .withName("CamelNettyhttpSourceConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.nettyhttp.CamelNettyhttpSourceConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withTransformsConfig("tostring")
                .withEntry("type", "org.apache.camel.kafkaconnector.transforms.CamelTypeConverterTransform$Value")
                .withEntry("target.type", "java.lang.String")
                .end()
                .withSourceContentLogginglevel(LoggingLevel.DEBUG);
    }
}
