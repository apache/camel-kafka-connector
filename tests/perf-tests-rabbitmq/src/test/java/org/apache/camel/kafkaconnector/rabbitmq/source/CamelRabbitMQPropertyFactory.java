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

package org.apache.camel.kafkaconnector.rabbitmq.source;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;

public class CamelRabbitMQPropertyFactory extends SourceConnectorPropertyFactory<CamelRabbitMQPropertyFactory> {
    public CamelRabbitMQPropertyFactory withHostname(String value) {
        return setProperty("camel.component.rabbitmq.hostname", value);
    }

    public CamelRabbitMQPropertyFactory withPortNumber(int value) {
        return setProperty("camel.component.rabbitmq.portNumber", value);
    }

    public CamelRabbitMQPropertyFactory withUsername(String value) {
        return setProperty("camel.component.rabbitmq.username", value);
    }

    public CamelRabbitMQPropertyFactory withPassword(String value) {
        return setProperty("camel.component.rabbitmq.password", value);
    }

    public CamelRabbitMQPropertyFactory withExchangeName(String value) {
        return setProperty("camel.source.path.exchangeName", value);
    }

    public CamelRabbitMQPropertyFactory withExchangeType(String value) {
        return setProperty("camel.source.endpoint.exchangeType", value);
    }

    public CamelRabbitMQPropertyFactory withAutoDelete(boolean value) {
        return setProperty("camel.source.endpoint.autoDelete", value);
    }

    public CamelRabbitMQPropertyFactory withQueue(String value) {
        return setProperty("camel.source.endpoint.queue", value);
    }

    public CamelRabbitMQPropertyFactory withRoutingKey(String value) {
        return setProperty("camel.source.endpoint.routingKey", value);
    }

    public EndpointUrlBuilder<CamelRabbitMQPropertyFactory> withUrl(String hostname, int port, String exchangeName) {
        String sourceUrl = String.format("rabbitmq://%s:%d/%s", hostname, port, exchangeName);

        return new EndpointUrlBuilder<>(this::withSourceUrl, sourceUrl);
    }

    public static CamelRabbitMQPropertyFactory basic() {
        return new CamelRabbitMQPropertyFactory()
                    .withTasksMax(1)
                    .withName("CamelRabbitmqSourceConnector")
                    .withConnectorClass("org.apache.camel.kafkaconnector.rabbitmq.CamelRabbitmqSourceConnector")
                    .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .withValueConverterClass("org.apache.kafka.connect.converters.ByteArrayConverter");
    
    }

}
