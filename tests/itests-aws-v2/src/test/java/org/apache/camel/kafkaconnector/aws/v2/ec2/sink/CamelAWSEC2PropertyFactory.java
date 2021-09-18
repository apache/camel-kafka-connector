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

package org.apache.camel.kafkaconnector.aws.v2.ec2.sink;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.kafkaconnector.aws.v2.common.AWSPropertiesUtils;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;
import org.apache.camel.test.infra.aws.common.AWSConfigs;

public class CamelAWSEC2PropertyFactory extends SinkConnectorPropertyFactory<CamelAWSEC2PropertyFactory> {
    public static final Map<String, String> SPRING_STYLE = new HashMap<>();

    static {
        SPRING_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.kamelet.aws-ec2-sink.accessKey");
        SPRING_STYLE.put(AWSConfigs.SECRET_KEY, "camel.kamelet.aws-ec2-sink.secretKey");
        SPRING_STYLE.put(AWSConfigs.REGION, "camel.kamelet.aws-ec2-sink.region");
    }

//    public CamelAWSEC2PropertyFactory withSinkPathLabel(String value) {
//        return setProperty("camel.kamelet.aws-ec2-sink.label", value);
//    }

    public CamelAWSEC2PropertyFactory withSinkEndpointOperation(String value) {
        return setProperty("camel.component.aws2-ec2.operation", value);
    }

    public CamelAWSEC2PropertyFactory withConfiguration(String value) {
        return setProperty("camel.component.aws2-ec2.configuration", classRef(value));
    }

    public CamelAWSEC2PropertyFactory withAmazonConfig(Properties amazonConfigs) {
        return withAmazonConfig(amazonConfigs, this.SPRING_STYLE);
    }

    public CamelAWSEC2PropertyFactory withAmazonConfig(Properties amazonConfigs, Map<String, String> style) {
        AWSPropertiesUtils.setCommonProperties(amazonConfigs, style, this);

        return this;
    }

    public static CamelAWSEC2PropertyFactory basic() {
        return new CamelAWSEC2PropertyFactory()
                    .withTasksMax(1)
                    .withName("CamelAws2ec2SinkConnector")
                    .withConnectorClass("org.apache.camel.kafkaconnector.aws2ec2.CamelAws2ec2SinkConnector")
                    .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .setProperty("camel.component.kamelet.location", "kamelets");

    }
}
