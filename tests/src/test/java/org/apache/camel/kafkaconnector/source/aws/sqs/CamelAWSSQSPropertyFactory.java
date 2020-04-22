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

package org.apache.camel.kafkaconnector.source.aws.sqs;

import java.util.Properties;

import com.amazonaws.regions.Regions;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.clients.aws.AWSConfigs;
import org.apache.kafka.connect.runtime.ConnectorConfig;


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
        connectorProps.put(ConnectorConfig.NAME_CONFIG, "CamelAwssqsSourceConnector");
        connectorProps.put("tasks.max", String.valueOf(tasksMax));

        connectorProps.put(ConnectorConfig.CONNECTOR_CLASS_CONFIG, "org.apache.camel.kafkaconnector.awssqs.CamelAwssqsSourceConnector");
        connectorProps.put(ConnectorConfig.KEY_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.storage.StringConverter");
        connectorProps.put(ConnectorConfig.VALUE_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.storage.StringConverter");

        connectorProps.put("camel.source.kafka.topic", topic);

        String accessKey = amazonConfigs.getProperty(AWSConfigs.ACCESS_KEY, "");
        String secretKey = amazonConfigs.getProperty(AWSConfigs.SECRET_KEY, "");

        String region = amazonConfigs.getProperty(AWSConfigs.REGION, Regions.US_EAST_1.name());

        String queueUrl = String.format("aws-sqs://%s?autoCreateQueue=true&accessKey=%s&secretKey=%s&region=%s",
                queue, accessKey, secretKey, region);

        String protocol = amazonConfigs.getProperty(AWSConfigs.PROTOCOL);
        if (protocol != null && !protocol.isEmpty()) {
            queueUrl = String.format("%s&protocol=%s", queueUrl, protocol);
        }

        String amazonAWSHost = amazonConfigs.getProperty(AWSConfigs.AMAZON_AWS_HOST);
        if (amazonAWSHost != null && !amazonAWSHost.isEmpty()) {
            queueUrl = String.format("%s&amazonAWSHost=%s", queueUrl, amazonAWSHost);
        }

        connectorProps.put("camel.source.url", queueUrl);

        connectorProps.put("camel.component.aws-sqs.accessKey",
                amazonConfigs.getProperty(AWSConfigs.ACCESS_KEY, ""));
        connectorProps.put("camel.component.aws-sqs.secretKey",
                amazonConfigs.getProperty(AWSConfigs.SECRET_KEY, ""));
        connectorProps.put("camel.component.aws-sqs.region",
                amazonConfigs.getProperty(AWSConfigs.REGION, ""));

        return connectorProps;
    }
}
