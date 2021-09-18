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

package org.apache.camel.kafkaconnector.aws.v2.s3.source;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.kafkaconnector.aws.v2.common.AWSPropertiesUtils;
import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;
import org.apache.camel.test.infra.aws.common.AWSConfigs;


/**
 * Creates the set of properties used by a Camel JMS Sink Connector
 */
final class CamelAWSS3PropertyFactory extends SourceConnectorPropertyFactory<CamelAWSS3PropertyFactory> {
    public static final Map<String, String> SPRING_STYLE = new HashMap<>();

    static {
        SPRING_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.kamelet.aws-s3-source.accessKey");
        SPRING_STYLE.put(AWSConfigs.SECRET_KEY, "camel.kamelet.aws-s3-source.secretKey");
        SPRING_STYLE.put(AWSConfigs.REGION, "camel.kamelet.aws-s3-source.region");
    }

    private CamelAWSS3PropertyFactory() {

    }

    public CamelAWSS3PropertyFactory withAmazonConfig(Properties amazonConfigs) {
        return withAmazonConfig(amazonConfigs, this.SPRING_STYLE);
    }

    public CamelAWSS3PropertyFactory withAmazonConfig(Properties amazonConfigs, Map<String, String> style) {
        AWSPropertiesUtils.setCommonProperties(amazonConfigs, style, this);

        return this;
    }

    public CamelAWSS3PropertyFactory withMaxMessagesPerPoll(int value) {
        return setProperty("camel.source.endpoint.maxMessagesPerPoll", Integer.toString(value));
    }

    public CamelAWSS3PropertyFactory withBucketNameOrArn(String bucketNameOrArn) {
        return setProperty("camel.kamelet.aws-s3-source.bucketNameOrArn", bucketNameOrArn);
    }

    public CamelAWSS3PropertyFactory withConfiguration(String configurationClass) {
        return setProperty("camel.component.aws2-s3.configuration", classRef(configurationClass));
    }

    public static CamelAWSS3PropertyFactory basic() {
        return new CamelAWSS3PropertyFactory()
                .withName("CamelAwss3SourceConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.awss3source.CamelAwss3sourceSourceConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .setProperty("camel.component.kamelet.location", "kamelets");
    }
}
