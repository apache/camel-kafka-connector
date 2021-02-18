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

package org.apache.camel.kafkaconnector.cxf.sink;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

final class CamelSinkCXFPropertyFactory extends SinkConnectorPropertyFactory<CamelSinkCXFPropertyFactory> {
    private CamelSinkCXFPropertyFactory() {

    }

    public EndpointUrlBuilder<CamelSinkCXFPropertyFactory> withUrl(String serviceUrl) {
        String url = String.format("cxf://%s", serviceUrl);

        return new EndpointUrlBuilder<>(this::withSinkUrl, url);
    }

    public CamelSinkCXFPropertyFactory withDataFormat(String dataFormat) {
        return setProperty("camel.sink.endpoint.dataFormat", dataFormat);
    }

    public CamelSinkCXFPropertyFactory withAddress(String address) {
        return setProperty("camel.sink.path.address", address);
    }

    public CamelSinkCXFPropertyFactory withServiceClass(String serviceClass) {
        return setProperty("camel.sink.endpoint.serviceClass", serviceClass);
    }

    public static CamelSinkCXFPropertyFactory basic() {
        return new CamelSinkCXFPropertyFactory()
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.cxf.CamelCxfSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
