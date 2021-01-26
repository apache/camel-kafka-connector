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

package org.apache.camel.kafkaconnector.aws.v2.kinesis.sink;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.kafkaconnector.aws.v2.common.AWSPropertiesUtils;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;
import org.apache.camel.test.infra.aws.common.AWSConfigs;


/**
 * Creates the set of properties used by a Camel Kinesis Source Connector
 */
final class CamelAWSKinesisPropertyFactory extends SinkConnectorPropertyFactory<CamelAWSKinesisPropertyFactory> {
    public static final Map<String, String> SPRING_STYLE = new HashMap<>();
    public static final Map<String, String> KAFKA_STYLE = new HashMap<>();

    static {
        SPRING_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.component.aws2-kinesis.accessKey");
        SPRING_STYLE.put(AWSConfigs.SECRET_KEY, "camel.component.aws2-kinesis.secretKey");
        SPRING_STYLE.put(AWSConfigs.REGION, "camel.component.aws2-kinesis.region");

        KAFKA_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.component.aws2-kinesis.access-key");
        KAFKA_STYLE.put(AWSConfigs.SECRET_KEY, "camel.component.aws2-kinesis.secret-key");
        KAFKA_STYLE.put(AWSConfigs.REGION, "camel.component.aws2-kinesis.region");
    }

    private CamelAWSKinesisPropertyFactory() {

    }

    public CamelAWSKinesisPropertyFactory withAmazonConfig(Properties amazonConfigs) {
        return withAmazonConfig(amazonConfigs, this.SPRING_STYLE);
    }

    public CamelAWSKinesisPropertyFactory withAmazonConfig(Properties amazonConfigs, Map<String, String> style) {
        AWSPropertiesUtils.setCommonProperties(amazonConfigs, style, this);

        return this;
    }

    public CamelAWSKinesisPropertyFactory withStreamName(String streamName) {
        return setProperty("camel.sink.path.streamName", streamName);
    }

    public CamelAWSKinesisPropertyFactory withConfiguration(String configurationClass) {
        return setProperty("camel.component.aws2-kinesis.configuration",
                classRef(configurationClass));
    }

    public static CamelAWSKinesisPropertyFactory basic() {
        return new CamelAWSKinesisPropertyFactory()
                .withName("CamelAwsKinesisSinkConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.aws2kinesis.CamelAws2kinesisSinkConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
