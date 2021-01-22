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

package org.apache.camel.kafkaconnector.aws.v2.sqs.sink;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.kafkaconnector.aws.v2.common.AWSPropertiesUtils;
import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;
import org.apache.camel.test.infra.aws.common.AWSConfigs;

/**
 * Creates the set of properties used by a Camel JMS Sink Connector
 */
final class CamelAWSSQSPropertyFactory extends SinkConnectorPropertyFactory<CamelAWSSQSPropertyFactory> {
    public static final Map<String, String> SPRING_STYLE = new HashMap<>();
    public static final Map<String, String> KAFKA_STYLE = new HashMap<>();

    static {
        SPRING_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.component.aws2-sqs.accessKey");
        SPRING_STYLE.put(AWSConfigs.SECRET_KEY, "camel.component.aws2-sqs.secretKey");
        SPRING_STYLE.put(AWSConfigs.REGION, "camel.component.aws2-sqs.region");
        SPRING_STYLE.put(AWSConfigs.PROTOCOL, "camel.sink.endpoint.protocol");
        SPRING_STYLE.put(AWSConfigs.AMAZON_AWS_HOST, "camel.sink.endpoint.amazonAWSHost");


        KAFKA_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.component.aws2-sqs.access-key");
        KAFKA_STYLE.put(AWSConfigs.SECRET_KEY, "camel.component.aws2-sqs.secret-key");
        KAFKA_STYLE.put(AWSConfigs.REGION, "camel.component.aws2-sqs.region");
        KAFKA_STYLE.put(AWSConfigs.PROTOCOL, "camel.sink.endpoint.protocol");
        KAFKA_STYLE.put(AWSConfigs.AMAZON_AWS_HOST, "camel.sink.endpoint.amazonAWSHost");
    }

    private CamelAWSSQSPropertyFactory() {
    }


    public CamelAWSSQSPropertyFactory withAmazonConfig(Properties amazonConfigs) {
        return withAmazonConfig(amazonConfigs, this.SPRING_STYLE);
    }

    public CamelAWSSQSPropertyFactory withAmazonConfig(Properties amazonConfigs, Map<String, String> style) {
        AWSPropertiesUtils.setCommonProperties(amazonConfigs, style, this);

        return this;
    }

    public EndpointUrlBuilder<CamelAWSSQSPropertyFactory> withUrl(String queueNameOrArn) {
        String queueUrl = String.format("aws2-sqs://%s", queueNameOrArn);

        return new EndpointUrlBuilder<>(this::withSinkUrl, queueUrl);
    }

    public CamelAWSSQSPropertyFactory withQueueNameOrArn(String queueNameOrArn) {
        return setProperty("camel.sink.path.queueNameOrArn", queueNameOrArn);
    }

    public static CamelAWSSQSPropertyFactory basic() {
        return new CamelAWSSQSPropertyFactory()
                .withName("CamelAws2sqsSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.aws2sqs.CamelAws2sqsSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }

}
