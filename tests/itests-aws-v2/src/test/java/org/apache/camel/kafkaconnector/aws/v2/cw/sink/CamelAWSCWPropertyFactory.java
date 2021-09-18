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

package org.apache.camel.kafkaconnector.aws.v2.cw.sink;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.kafkaconnector.aws.v2.common.AWSPropertiesUtils;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;
import org.apache.camel.test.infra.aws.common.AWSConfigs;

public class CamelAWSCWPropertyFactory extends SinkConnectorPropertyFactory<CamelAWSCWPropertyFactory> {
    public static final Map<String, String> SPRING_STYLE = new HashMap<>();

    static {
        SPRING_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.kamelet.aws-cloudwatch-sink.accessKey");
        SPRING_STYLE.put(AWSConfigs.SECRET_KEY, "camel.kamelet.aws-cloudwatch-sink.secretKey");
        SPRING_STYLE.put(AWSConfigs.REGION, "camel.kamelet.aws-cloudwatch-sink.region");
    }

    public CamelAWSCWPropertyFactory withNamespace(String value) {
        return setProperty("camel.kamelet.aws-cloudwatch-sink.cw_namespace", value);
    }

    public CamelAWSCWPropertyFactory withConfiguration(String value) {
        return setProperty("camel.component.aws2-cw.configuration", classRef(value));
    }

    public CamelAWSCWPropertyFactory withAmazonConfig(Properties amazonConfigs) {
        return withAmazonConfig(amazonConfigs, this.SPRING_STYLE);
    }

    public CamelAWSCWPropertyFactory withAmazonConfig(Properties amazonConfigs, Map<String, String> style) {
        AWSPropertiesUtils.setCommonProperties(amazonConfigs, style, this);

        return this;
    }

    public static CamelAWSCWPropertyFactory basic() {
        return new CamelAWSCWPropertyFactory()
                    .withTasksMax(1)
                    .withName("CamelAWSCWConnector")
                    .withConnectorClass("org.apache.camel.kafkaconnector.awscloudwatchsink.CamelAwscloudwatchsinkSinkConnector")
                    .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .setProperty("camel.sink.contentLogLevel", "INFO")
                    .setProperty("camel.component.kamelet.location", "kamelets");

    }
}
