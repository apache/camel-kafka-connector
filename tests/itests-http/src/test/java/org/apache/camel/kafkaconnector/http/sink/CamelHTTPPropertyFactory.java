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

package org.apache.camel.kafkaconnector.http.sink;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

final class CamelHTTPPropertyFactory extends SinkConnectorPropertyFactory<CamelHTTPPropertyFactory> {
    private CamelHTTPPropertyFactory() {

    }

    public CamelHTTPPropertyFactory withHttpUri(String uri) {
        return setProperty("camel.sink.path.httpUri", uri);
    }

    public EndpointUrlBuilder<CamelHTTPPropertyFactory> withUrl(String hostname) {
        String url = String.format("http://%s", hostname);

        return new EndpointUrlBuilder<>(this::withSinkUrl, url);
    }

    public static CamelHTTPPropertyFactory basic() {
        return new CamelHTTPPropertyFactory()
                .withTasksMax(1)
                .withName("CamelHttpSinkConnector")
                .withConnectorClass("org.apache.camel.kafkaconnector.http.CamelHttpSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
