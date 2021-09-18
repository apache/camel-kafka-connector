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

package org.apache.camel.kafkaconnector.aws.v2.sns.sink;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;
import org.apache.camel.test.infra.aws.common.AWSConfigs;
import software.amazon.awssdk.regions.Region;

/**
 * Creates the set of properties used by a Camel JMS Sink Connector
 */
final class CamelAWSSNSPropertyFactory extends SinkConnectorPropertyFactory<CamelAWSSNSPropertyFactory> {
    public static final Map<String, String> SPRING_STYLE = new HashMap<>();

    static {
        SPRING_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.kamelet.aws-sns-sink.accessKey");
        SPRING_STYLE.put(AWSConfigs.SECRET_KEY, "camel.kamelet.aws-sns-sink.secretKey");
        SPRING_STYLE.put(AWSConfigs.REGION, "camel.kamelet.aws-sns-sink.region");
    }

    private CamelAWSSNSPropertyFactory() {
    }

    public CamelAWSSNSPropertyFactory withTopicOrArn(String topicOrArn) {
        return setProperty("camel.kamelet.aws-sns-sink.topicNameOrArn", topicOrArn);
    }

    public CamelAWSSNSPropertyFactory withAmazonConfig(Properties amazonConfigs) {
        return withAmazonConfig(amazonConfigs, this.SPRING_STYLE);
    }

    public CamelAWSSNSPropertyFactory withAutoCreateTopic(boolean value) {
        return setProperty("camel.kamelet.aws-sns-sink.autoCreateTopic", value);
    }

    public CamelAWSSNSPropertyFactory withSubscribeSNStoSQS(String queue) {
        return setProperty("camel.component.aws2-sns.subscribeSNStoSQS", "true")
                .setProperty("camel.component.aws2-sns.queueUrl", queue);
    }

    public CamelAWSSNSPropertyFactory withAmazonConfig(Properties amazonConfigs, Map<String, String> style) {
        String accessKeyKey = style.get(AWSConfigs.ACCESS_KEY);
        String secretKeyKey = style.get(AWSConfigs.SECRET_KEY);
        String regionKey = style.get(AWSConfigs.REGION);

        setProperty(accessKeyKey, amazonConfigs.getProperty(AWSConfigs.ACCESS_KEY, ""));
        setProperty(secretKeyKey, amazonConfigs.getProperty(AWSConfigs.SECRET_KEY, ""));
        return setProperty(regionKey, amazonConfigs.getProperty(AWSConfigs.REGION, Region.US_EAST_1.id()));
    }

    public CamelAWSSNSPropertyFactory withConfiguration(String configurationClass) {
        return setProperty("camel.component.aws2-sns.configuration", classRef(configurationClass));
    }

    public static CamelAWSSNSPropertyFactory basic() {
        return new CamelAWSSNSPropertyFactory().withName("CamelAWS2SNSSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.awssnssink.CamelAwssnssinkSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .setProperty("camel.component.kamelet.location", "kamelets");
    }
}
