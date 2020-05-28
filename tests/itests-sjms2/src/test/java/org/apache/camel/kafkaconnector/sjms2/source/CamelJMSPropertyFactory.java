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

package org.apache.camel.kafkaconnector.sjms2.source;

import java.util.Properties;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;


/**
 * Creates the set of properties used by a Camel JMS Sink Connector
 */
final class CamelJMSPropertyFactory extends SourceConnectorPropertyFactory<CamelJMSPropertyFactory> {
    private CamelJMSPropertyFactory() {

    }

    public CamelJMSPropertyFactory withDestinationName(String destinationName) {
        return setProperty("camel.source.path.destinationName", destinationName);
    }

    public static CamelJMSPropertyFactory basic() {
        return new CamelJMSPropertyFactory()
                .withName("CamelJMSSourceConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.sjms2.CamelSjms2SourceConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }

    public EndpointUrlBuilder<CamelJMSPropertyFactory> withUrl(String destinationName) {
        String url = String.format("sjms2://%s", destinationName);

        return new EndpointUrlBuilder<>(this::withSourceUrl, url);
    }

    public CamelJMSPropertyFactory withConnectionProperties(Properties connectionProperties) {
        return merge(connectionProperties);
    }
}
