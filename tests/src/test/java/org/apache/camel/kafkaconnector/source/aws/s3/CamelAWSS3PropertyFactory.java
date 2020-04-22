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

package org.apache.camel.kafkaconnector.source.aws.s3;

import java.util.Properties;

import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.clients.aws.AWSConfigs;
import org.apache.kafka.connect.runtime.ConnectorConfig;


/**
 * Creates the set of properties used by a Camel JMS Sink Connector
 */
class CamelAWSS3PropertyFactory implements ConnectorPropertyFactory {
    private final int tasksMax;
    private final String topic;
    private final String bucket;
    private final Properties amazonConfigs;


    CamelAWSS3PropertyFactory(int tasksMax, String topic, String bucket, Properties amazonConfigs) {
        this.tasksMax = tasksMax;
        this.topic = topic;
        this.bucket = bucket;
        this.amazonConfigs = amazonConfigs;
    }

    @Override
    public Properties getProperties() {
        Properties connectorProps = new Properties();
        connectorProps.put(ConnectorConfig.NAME_CONFIG, "CamelAwss3SourceConnector");

        connectorProps.put(ConnectorConfig.CONNECTOR_CLASS_CONFIG, "org.apache.camel.kafkaconnector.awss3.CamelAwss3SourceConnector");
        connectorProps.put(ConnectorConfig.KEY_CONVERTER_CLASS_CONFIG, "org.apache.kafka.connect.storage.StringConverter");
        connectorProps.put(ConnectorConfig.VALUE_CONVERTER_CLASS_CONFIG, "org.apache.camel.kafkaconnector.awss3.converters.S3ObjectConverter");

        connectorProps.put("camel.source.kafka.topic", topic);

        String queueUrl = "aws-s3://" + bucket + "?maxMessagesPerPoll=10";
        connectorProps.put("camel.source.url", queueUrl);


        connectorProps.put("camel.component.aws-s3.accessKey",
                amazonConfigs.getProperty(AWSConfigs.ACCESS_KEY, ""));
        connectorProps.put("camel.component.aws-s3.secretKey",
                amazonConfigs.getProperty(AWSConfigs.SECRET_KEY, ""));
        connectorProps.put("camel.component.aws-s3.region",
                amazonConfigs.getProperty(AWSConfigs.REGION, ""));

        connectorProps.put("camel.component.aws-s3.configuration", "#class:"
                + TestS3Configuration.class.getName());

        return connectorProps;
    }
}
