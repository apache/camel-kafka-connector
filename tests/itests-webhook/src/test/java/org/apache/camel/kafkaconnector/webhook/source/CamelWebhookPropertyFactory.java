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
package org.apache.camel.kafkaconnector.webhook.source;

import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;

final class CamelWebhookPropertyFactory extends SourceConnectorPropertyFactory<CamelWebhookPropertyFactory> {
    private CamelWebhookPropertyFactory() {

    }

    public CamelWebhookPropertyFactory withSubpath(String subpath) {
        return setProperty("camel.kamelet.webhook-source.subpath", subpath);
    }

    public CamelWebhookPropertyFactory withServerEnabled(boolean enabled) {
        return setProperty("camel.server.enabled", String.valueOf(enabled));
    }

    public CamelWebhookPropertyFactory withServerHost(String host) {
        return setProperty("camel.server.host", host);
    }

    public CamelWebhookPropertyFactory withServerPort(int port) {
        return setProperty("camel.server.port", String.valueOf(port));
    }

    public CamelWebhookPropertyFactory withCamelTypeConverterTransformTo(String targetClass) {
        setProperty("transforms", "cameltypeconverter");
        setProperty("transforms.cameltypeconverter.type", "org.apache.camel.kafkaconnector.transforms.CamelTypeConverterTransform$Value");
        return setProperty("transforms.cameltypeconverter.target.type", targetClass);
    }

    public static CamelWebhookPropertyFactory basic() {
        return new CamelWebhookPropertyFactory()
                .withTasksMax(1)
                .withName("CamelWebhookSourceConnector")
                .withConnectorClass("org.apache.camel.kafkaconnector.webhooksource.CamelWebhooksourceSourceConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
