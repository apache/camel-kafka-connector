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

package org.apache.camel.kafkaconnector.source.aws.kinesis;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import org.apache.camel.component.aws.kinesis.KinesisConfiguration;
import org.apache.camel.kafkaconnector.AWSConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestKinesisConfiguration extends KinesisConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(TestKinesisConfiguration.class);
    private final String amazonHost;
    private final String region;

    private AmazonKinesis amazonKinesis;

    private class TestAWSCredentialsProvider implements AWSCredentialsProvider {
        @Override
        public AWSCredentials getCredentials() {
            return new AWSCredentials() {
                @Override
                public String getAWSAccessKeyId() {
                    return System.getProperty(AWSConfigs.ACCESS_KEY);
                }

                @Override
                public String getAWSSecretKey() {
                    return System.getProperty(AWSConfigs.SECRET_KEY);
                }
            };
        }

        @Override
        public void refresh() {

        }
    }

    public TestKinesisConfiguration() {
        amazonHost = System.getProperty(AWSConfigs.AMAZON_AWS_HOST);
        region = Regions.valueOf(System.getProperty(AWSConfigs.REGION)).getName();
    }

    private AmazonKinesis buildClient() {
        LOG.debug("Creating a new AWS Kinesis client");
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTP);

        return AmazonKinesisClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonHost, region))
                .withCredentials(new TestAWSCredentialsProvider())
                .withClientConfiguration(clientConfiguration)
                .build();
    }

    @Override
    public AmazonKinesis getAmazonKinesisClient() {
        if (amazonKinesis == null) {
            amazonKinesis = buildClient();
        }

        return amazonKinesis;
    }
}
