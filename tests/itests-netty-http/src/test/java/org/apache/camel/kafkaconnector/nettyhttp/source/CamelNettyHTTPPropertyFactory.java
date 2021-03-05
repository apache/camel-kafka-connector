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

import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;

final class CamelNettyHTTPPropertyFactory extends SourceConnectorPropertyFactory<CamelNettyHTTPPropertyFactory> {
    private CamelNettyHTTPPropertyFactory() {

    }

    public CamelNettyHTTPPropertyFactory withHost(String host) {
        return setProperty("camel.source.path.host", host);
    }

    public CamelNettyHTTPPropertyFactory withProtocol(String protocol) {
        return setProperty("camel.source.path.protocol", protocol);
    }

    public CamelNettyHTTPPropertyFactory withPort(int port) {
        return setProperty("camel.source.path.port", String.valueOf(port));
    }

    public CamelNettyHTTPPropertyFactory withSync(boolean sync) {
        return setProperty("camel.source.endpoint.sync", String.valueOf(sync));
    }

    public CamelNettyHTTPPropertyFactory withReceiveBufferSize(int size) {
        return setProperty("camel.source.endpoint.receiveBufferSize", String.valueOf(size));
    }

    public CamelNettyHTTPPropertyFactory withCamelTypeConverterTransformTo(String targetClass) {
        setProperty("transforms", "cameltypeconverter");
        setProperty("transforms.cameltypeconverter.type", "org.apache.camel.kafkaconnector.transforms.CamelTypeConverterTransform$Value");
        return setProperty("transforms.cameltypeconverter.target.type", targetClass);
    }

    public static CamelNettyHTTPPropertyFactory basic() {
        return new CamelNettyHTTPPropertyFactory()
                .withTasksMax(1)
                .withName("CamelNettyHttpSourceConnector")
                .withConnectorClass("org.apache.camel.kafkaconnector.nettyhttp.CamelNettyhttpSourceConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
