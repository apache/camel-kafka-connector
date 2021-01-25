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

package org.apache.camel.kafkaconnector.aws.v2.iam.sink;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.kafkaconnector.aws.v2.common.AWSPropertiesUtils;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;
import org.apache.camel.test.infra.aws.common.AWSConfigs;

public class CamelAWSIAMPropertyFactory extends SinkConnectorPropertyFactory<CamelAWSIAMPropertyFactory> {
    public static final Map<String, String> SPRING_STYLE = new HashMap<>();
    public static final Map<String, String> KAFKA_STYLE = new HashMap<>();

    static {
        SPRING_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.component.aws2-iam.accessKey");
        SPRING_STYLE.put(AWSConfigs.SECRET_KEY, "camel.component.aws2-iam.secretKey");
        SPRING_STYLE.put(AWSConfigs.REGION, "camel.component.aws2-iam.region");

        KAFKA_STYLE.put(AWSConfigs.ACCESS_KEY, "camel.component.aws2-iam.access-key");
        KAFKA_STYLE.put(AWSConfigs.SECRET_KEY, "camel.component.aws2-iam.secret-key");
        KAFKA_STYLE.put(AWSConfigs.REGION, "camel.component.aws2-iam.region");
    }

    public CamelAWSIAMPropertyFactory withSinkPathLabel(String value) {
        return setProperty("camel.sink.path.label", value);
    }

    public CamelAWSIAMPropertyFactory withSinkEndpointOperation(String value) {
        return setProperty("camel.sink.endpoint.operation", value);
    }

    public CamelAWSIAMPropertyFactory withConfiguration(String value) {
        return setProperty("camel.component.aws2-iam.configuration", classRef(value));
    }

    public CamelAWSIAMPropertyFactory withAmazonConfig(Properties amazonConfigs) {
        return withAmazonConfig(amazonConfigs, this.SPRING_STYLE);
    }

    public CamelAWSIAMPropertyFactory withAmazonConfig(Properties amazonConfigs, Map<String, String> style) {
        AWSPropertiesUtils.setCommonProperties(amazonConfigs, style, this);

        return this;
    }
    
    public static CamelAWSIAMPropertyFactory basic() {
        return new CamelAWSIAMPropertyFactory()
                    .withTasksMax(1)
                    .withName("CamelAws2iamSinkConnector")
                    .withConnectorClass("org.apache.camel.kafkaconnector.aws2iam.CamelAws2iamSinkConnector")
                    .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    
    }
}
