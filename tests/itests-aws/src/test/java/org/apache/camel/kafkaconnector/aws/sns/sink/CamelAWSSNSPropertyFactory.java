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

package org.apache.camel.kafkaconnector.aws.sns.sink;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.amazonaws.regions.Regions;
import org.apache.camel.kafkaconnector.aws.common.AWSConfigs;
import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

/**
 * Creates the set of properties used by a Camel JMS Sink Connector
 */
final class CamelAWSSNSPropertyFactory extends SinkConnectorPropertyFactory<CamelAWSSNSPropertyFactory> {
    public static final Map<String, String> SPRING_STYLE = new HashMap<>();
    public static final Map<String, String> KAFKA_STYLE = new HashMap<>();

    static {
        SPRING_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.component.aws-sns.accessKey");
        SPRING_STYLE.put(AWSConfigs.SECRET_KEY, "camel.component.aws-sns.secretKey");
        SPRING_STYLE.put(AWSConfigs.REGION, "camel.component.aws-sns.region");

        KAFKA_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.component.aws-sns.access-key");
        KAFKA_STYLE.put(AWSConfigs.SECRET_KEY, "camel.component.aws-sns.secret-key");
        KAFKA_STYLE.put(AWSConfigs.REGION, "camel.component.aws-sns.region");
    }

    private CamelAWSSNSPropertyFactory() {
    }

    public EndpointUrlBuilder<CamelAWSSNSPropertyFactory> withUrl(String topicOrArn) {
        String queueUrl = String.format("aws-sns://%s", topicOrArn);

        return new EndpointUrlBuilder<>(this::withSinkUrl, queueUrl);
    }

    public CamelAWSSNSPropertyFactory withTopicOrArn(String topicOrArn) {
        return setProperty("camel.sink.path.topicNameOrArn", topicOrArn);
    }

    public CamelAWSSNSPropertyFactory withSubscribeSNStoSQS(String queue) {
        return setProperty("camel.sink.endpoint.subscribeSNStoSQS", "true")
                .setProperty("camel.sink.endpoint.queueUrl", queue);
    }

    public CamelAWSSNSPropertyFactory withAmazonConfig(Properties amazonConfigs) {
        return withAmazonConfig(amazonConfigs, this.SPRING_STYLE);
    }

    public CamelAWSSNSPropertyFactory withAmazonConfig(Properties amazonConfigs, Map<String, String> style) {
        String accessKeyKey = style.get(AWSConfigs.ACCESS_KEY);
        String secretKeyKey = style.get(AWSConfigs.SECRET_KEY);
        String regionKey = style.get(AWSConfigs.REGION);

        setProperty(accessKeyKey,
                amazonConfigs.getProperty(AWSConfigs.ACCESS_KEY, ""));
        setProperty(secretKeyKey,
                amazonConfigs.getProperty(AWSConfigs.SECRET_KEY, ""));
        return setProperty(regionKey,
                amazonConfigs.getProperty(AWSConfigs.REGION, Regions.US_EAST_1.name()));
    }

    public CamelAWSSNSPropertyFactory withConfiguration(String configurationClass) {
        return setProperty("camel.component.aws-sns.configuration",
                classRef(configurationClass));
    }


    public static CamelAWSSNSPropertyFactory basic() {
        return new CamelAWSSNSPropertyFactory()
                .withName("CamelAWSSNSSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.awssns.CamelAwssnsSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
