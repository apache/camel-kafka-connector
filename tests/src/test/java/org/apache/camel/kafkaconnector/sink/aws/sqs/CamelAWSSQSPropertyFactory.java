/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.camel.kafkaconnector.sink.aws.sqs;

import org.apache.camel.kafkaconnector.AWSConfigs;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.kafka.connect.runtime.ConnectorConfig;

import java.util.Properties;


/**
 * Creates the set of properties used by a Camel JMS Sink Connector
 */
class CamelAWSSQSPropertyFactory implements ConnectorPropertyFactory {
    private final int tasksMax;
    private final String topic;
    private final String queue;
    private final Properties amazonConfigs;


    CamelAWSSQSPropertyFactory(int tasksMax, String topic, String queue, Properties amazonConfigs) {
        this.tasksMax = tasksMax;
        this.topic = topic;
        this.queue = queue;
        this.amazonConfigs = amazonConfigs;
    }

    @Override
    public Properties getProperties() {
        Properties connectorProps = new Properties();
        connectorProps.put(ConnectorConfig.NAME_CONFIG, "CamelAWSSQSSinkConnector");
        connectorProps.put("tasks.max", String.valueOf(tasksMax));

        connectorProps.put(ConnectorConfig.CONNECTOR_CLASS_CONFIG, "org.apache.camel.kafkaconnector.CamelSinkConnector");
        connectorProps.put(ConnectorConfig.KEY_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.storage.StringConverter");
        connectorProps.put(ConnectorConfig.VALUE_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.storage.StringConverter");

        String queueUrl = "aws-sqs://" + queue + "?autoCreateQueue=false&accessKey=accesskey&protocol=http&amazonAWSHost=" +
                amazonConfigs.getProperty(AWSConfigs.AMAZON_AWS_HOST, "localhost");
        System.out.println("Queue URL => " + queueUrl);
        connectorProps.put("camel.sink.url", queueUrl);
        connectorProps.put("topics", topic);

        connectorProps.put("camel.component.aws-sqs.configuration.access-key",
                amazonConfigs.getProperty(AWSConfigs.ACCESS_KEY, ""));
        connectorProps.put("camel.component.aws-sqs.configuration.secret-key",
                amazonConfigs.getProperty(AWSConfigs.SECRET_KEY, ""));

        connectorProps.put("camel.component.aws-sqs.configuration.region",
            amazonConfigs.getProperty(AWSConfigs.REGION, ""));

        return connectorProps;
    }
}
