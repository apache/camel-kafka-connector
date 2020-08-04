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

package org.apache.camel.kafkaconnector.aws.v1.kinesis.source;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.amazonaws.regions.Regions;
import org.apache.camel.kafkaconnector.aws.common.AWSConfigs;
import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;


/**
 * Creates the set of properties used by a Camel Kinesis Source Connector
 */
final class CamelAWSKinesisPropertyFactory extends SourceConnectorPropertyFactory<CamelAWSKinesisPropertyFactory> {
    public static final Map<String, String> SPRING_STYLE = new HashMap<>();
    public static final Map<String, String> KAFKA_STYLE = new HashMap<>();

    static {
        SPRING_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.component.aws-kinesis.accessKey");
        SPRING_STYLE.put(AWSConfigs.SECRET_KEY, "camel.component.aws-kinesis.secretKey");
        SPRING_STYLE.put(AWSConfigs.REGION, "camel.component.aws-kinesis.region");

        KAFKA_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.component.aws-kinesis.access-key");
        KAFKA_STYLE.put(AWSConfigs.SECRET_KEY, "camel.component.aws-kinesis.secret-key");
        KAFKA_STYLE.put(AWSConfigs.REGION, "camel.component.aws-kinesis.region");
    }

    private CamelAWSKinesisPropertyFactory() {

    }

    public CamelAWSKinesisPropertyFactory withAmazonConfig(Properties amazonConfigs) {
        return withAmazonConfig(amazonConfigs, this.SPRING_STYLE);
    }

    public CamelAWSKinesisPropertyFactory withAmazonConfig(Properties amazonConfigs, Map<String, String> style) {
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

    public CamelAWSKinesisPropertyFactory withStreamName(String streamName) {
        return setProperty("camel.source.path.streamName", streamName);
    }

    public EndpointUrlBuilder<CamelAWSKinesisPropertyFactory> withUrl(String streamName) {
        String sourceUrl = String.format("aws-kinesis://%s", streamName);

        return new EndpointUrlBuilder<>(this::withSourceUrl, sourceUrl);
    }

    public CamelAWSKinesisPropertyFactory withConfiguration(String configurationClass) {
        return setProperty("camel.component.aws-kinesis.configuration",
                classRef(configurationClass));
    }

    public static CamelAWSKinesisPropertyFactory basic() {
        return new CamelAWSKinesisPropertyFactory()
                .withName("CamelAwskinesisSourceConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.awskinesis.CamelAwskinesisSourceConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
