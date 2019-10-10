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

package org.apache.camel.kafkaconnector.source.jms;

import java.util.Properties;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.kafka.connect.runtime.ConnectorConfig;


/**
 * Creates the set of properties used by a Camel JMS Sink Connector
 */
class CamelJMSPropertyFactory implements ConnectorPropertyFactory {
    private final int tasksMax;
    private final String topic;
    private final String queue;

    private final String brokerURL;

    CamelJMSPropertyFactory(int tasksMax, String topic, String queue, String brokerURL) {
        this.tasksMax = tasksMax;
        this.topic = topic;
        this.queue = queue;
        this.brokerURL = brokerURL;
    }

    @Override
    public Properties getProperties() {
        Properties connectorProps = new Properties();
        connectorProps.put(ConnectorConfig.NAME_CONFIG, "CamelJMSSourceConnector");
        connectorProps.put("tasks.max", String.valueOf(tasksMax));

        connectorProps.put(ConnectorConfig.CONNECTOR_CLASS_CONFIG, "org.apache.camel.kafkaconnector.CamelSourceConnector");
        connectorProps.put(ConnectorConfig.KEY_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.storage.StringConverter");
        connectorProps.put(ConnectorConfig.VALUE_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.storage.StringConverter");
        connectorProps.put("camel.source.url", "sjms2://queue:" + queue);
        connectorProps.put("camel.source.kafka.topic", topic);
        connectorProps.put("camel.component.sjms2.connection-factory", "#class:org.apache.activemq.ActiveMQConnectionFactory");
        connectorProps.put("camel.component.sjms2.connection-factory.brokerURL", brokerURL);

        return connectorProps;
    }
}
