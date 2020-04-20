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

package org.apache.camel.kafkaconnector.sink.aws.sns;

import java.util.Properties;

import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.clients.aws.AWSConfigs;
import org.apache.kafka.connect.runtime.ConnectorConfig;

/**
 * Creates the set of properties used by a Camel JMS Sink Connector
 */
class CamelAWSSNSPropertyFactory implements ConnectorPropertyFactory {
    private final int tasksMax;
    private final String topic;
    private final String queue;
    private final Properties amazonConfigs;


    CamelAWSSNSPropertyFactory(int tasksMax, String topic, String queue, Properties amazonConfigs) {
        this.tasksMax = tasksMax;
        this.topic = topic;
        this.queue = queue;
        this.amazonConfigs = amazonConfigs;
    }

    @Override
    public Properties getProperties() {
        Properties connectorProps = new Properties();
        connectorProps.put(ConnectorConfig.NAME_CONFIG, "CamelAwssnsSinkConnector");
        connectorProps.put("tasks.max", String.valueOf(tasksMax));

        connectorProps.put(ConnectorConfig.CONNECTOR_CLASS_CONFIG, "org.apache.camel.kafkaconnector.awssns.CamelAwssnsSinkConnector");
        connectorProps.put(ConnectorConfig.KEY_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.storage.StringConverter");
        connectorProps.put(ConnectorConfig.VALUE_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.storage.StringConverter");

        String queueUrl = "aws-sns://" + queue + "?subscribeSNStoSQS=true"
                + "&queueUrl=" + amazonConfigs.getProperty(AWSConfigs.AMAZON_AWS_SNS_2_SQS_QUEUE_URL);

        connectorProps.put("camel.sink.url", queueUrl);
        connectorProps.put("topics", topic);

        connectorProps.put("camel.component.aws-sns.configuration.access-key",
                amazonConfigs.getProperty(AWSConfigs.ACCESS_KEY, ""));
        connectorProps.put("camel.component.aws-sns.configuration.secret-key",
                amazonConfigs.getProperty(AWSConfigs.SECRET_KEY, ""));

        connectorProps.put("camel.component.aws-sns.configuration.region",
            amazonConfigs.getProperty(AWSConfigs.REGION, ""));

        connectorProps.put("camel.component.aws-sns.configuration", "#class:"
                + TestSNSConfiguration.class.getName());


        return connectorProps;
    }
}
