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
 *
 */

package org.apache.camel.kafkaconnector.sink.aws.sns;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.apache.camel.component.aws.sns.SnsConfiguration;
import org.apache.camel.kafkaconnector.AWSConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSNSConfiguration extends SnsConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(TestSNSConfiguration.class);
    private final String amazonHost;
    private final String region;

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

    public TestSNSConfiguration() {
        amazonHost = System.getProperty(AWSConfigs.AMAZON_AWS_HOST);
        region = Regions.valueOf(System.getProperty(AWSConfigs.REGION)).getName();
    }

    @Override
    public AmazonSNS getAmazonSNSClient() {
        LOG.debug("Creating a custom SNS client for running a AWS SNS test");
        AmazonSNSClientBuilder clientBuilder = AmazonSNSClientBuilder
                .standard();

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTP);

        clientBuilder
                .withClientConfiguration(clientConfiguration)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonHost, region))
                .withCredentials(new TestAWSCredentialsProvider());

        return clientBuilder.build();
    }

    @Override
    public AmazonSQS getAmazonSQSClient() {
        LOG.debug("Creating a custom SQS client for running a AWS SNS test");
        AmazonSQSClientBuilder clientBuilder = AmazonSQSClientBuilder
                .standard();

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTP);

        clientBuilder
                .withClientConfiguration(clientConfiguration)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonHost, region))
                .withCredentials(new TestAWSCredentialsProvider());

        return clientBuilder.build();
    }
}
