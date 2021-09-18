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

import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;

public class CamelRabbitMQPropertyFactory extends SourceConnectorPropertyFactory<CamelRabbitMQPropertyFactory> {
    public CamelRabbitMQPropertyFactory withAddresses(String value) {
        return setProperty("camel.kamelet.rabbitmq-source.addresses", value);
    }

    public CamelRabbitMQPropertyFactory withPortNumber(int value) {
        return setProperty("camel.kamelet.rabbitmq-source.portNumber", value);
    }

    public CamelRabbitMQPropertyFactory withUsername(String value) {
        return setProperty("camel.kamelet.rabbitmq-source.username", value);
    }

    public CamelRabbitMQPropertyFactory withPassword(String value) {
        return setProperty("camel.kamelet.rabbitmq-source.password", value);
    }

    public CamelRabbitMQPropertyFactory withExchangeName(String value) {
        return setProperty("camel.kamelet.rabbitmq-source.exchangeName", value);
    }

    public static CamelRabbitMQPropertyFactory basic() {
        return new CamelRabbitMQPropertyFactory()
                    .withTasksMax(1)
                    .withName("CamelRabbitmqSourceConnector")
                    .withConnectorClass("org.apache.camel.kafkaconnector.rabbitmqsource.CamelRabbitmqsourceSourceConnector")
                    .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .withValueConverterClass("org.apache.kafka.connect.converters.ByteArrayConverter")
                    .setProperty("camel.component.kamelet.location", "kamelets")
                    .setProperty("camel.component.properties.environment-variable-mode", "1");
    
    }

}
