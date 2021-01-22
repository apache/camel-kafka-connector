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

package org.apache.camel.kafkaconnector.aws.v2.common;

import java.util.Map;
import java.util.Properties;

import org.apache.camel.kafkaconnector.common.BasicConnectorPropertyFactory;
import org.apache.camel.test.infra.aws.common.AWSConfigs;
import software.amazon.awssdk.regions.Region;

public final class AWSPropertiesUtils {

    private AWSPropertiesUtils() {

    }

    public static void setCommonProperties(Properties amazonConfigs, Map<String, String> style,
                                           BasicConnectorPropertyFactory<?> propertyFactory) {
        String accessKeyKey = style.get(AWSConfigs.ACCESS_KEY);
        String secretKeyKey = style.get(AWSConfigs.SECRET_KEY);
        String regionKey = style.get(AWSConfigs.REGION);
        String protocolKey = style.get(AWSConfigs.PROTOCOL);
        String hostKey = style.get(AWSConfigs.AMAZON_AWS_HOST);

        propertyFactory.setProperty(accessKeyKey,
                amazonConfigs.getProperty(AWSConfigs.ACCESS_KEY, ""));
        propertyFactory.setProperty(secretKeyKey,
                amazonConfigs.getProperty(AWSConfigs.SECRET_KEY, ""));
        propertyFactory.setProperty(regionKey,
                amazonConfigs.getProperty(AWSConfigs.REGION, Region.US_EAST_1.toString()));

        String protocol = amazonConfigs.getProperty(AWSConfigs.PROTOCOL, "");

        if (protocolKey != null && !protocolKey.isEmpty()) {
            if (protocol != null && !protocol.isEmpty()) {
                propertyFactory.setProperty(protocolKey, protocol);
            }
        }

        if (hostKey != null && !hostKey.isEmpty()) {
            String amazonAwsHost = amazonConfigs.getProperty(AWSConfigs.AMAZON_AWS_HOST, "");
            if (amazonAwsHost != null && !amazonAwsHost.isEmpty()) {
                propertyFactory.setProperty(hostKey, amazonAwsHost);
            }
        }
    }
}
