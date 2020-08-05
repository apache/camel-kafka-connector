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

package org.apache.camel.kafkaconnector.aws.v2.clients;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.camel.kafkaconnector.aws.common.AWSConfigs;
import org.apache.camel.kafkaconnector.aws.v2.common.TestAWSCredentialsProvider;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.KinesisClientBuilder;

public final class AWSSDKClientUtils {
    private static final Logger LOG = LoggerFactory.getLogger(AWSSDKClientUtils.class);

    private AWSSDKClientUtils() {

    }

    @NotNull
    private static URI getEndpoint() {
        String amazonHost = System.getProperty(AWSConfigs.AMAZON_AWS_HOST);

        if (amazonHost == null || amazonHost.isEmpty()) {
            return null;
        }

        try {
            return new URI(String.format("http://%s", amazonHost));
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid endpoint");
        }
    }

    public static KinesisClient newKinesisClient() {
        LOG.debug("Creating a new AWS v2 Kinesis client");

        String awsInstanceType = System.getProperty("aws-service.kinesis.instance.type");

        KinesisClientBuilder clientBuilder = KinesisClient.builder();

        clientBuilder.region(Region.US_EAST_1);

        URI endpoint = getEndpoint();

        if (isLocalContainer(awsInstanceType) || endpoint != null) {
            clientBuilder.endpointOverride(endpoint);
        }

        if (isLocalContainer(awsInstanceType)) {
            clientBuilder.credentialsProvider(TestAWSCredentialsProvider.CONTAINER_LOCAL_DEFAULT_PROVIDER);

        } else {
            clientBuilder.credentialsProvider(TestAWSCredentialsProvider.SYSTEM_PROPERTY_PROVIDER);
        }

        return clientBuilder.build();
    }

    private static boolean isLocalContainer(String awsInstanceType) {
        return awsInstanceType == null || awsInstanceType.equals("local-aws-container");
    }


}
