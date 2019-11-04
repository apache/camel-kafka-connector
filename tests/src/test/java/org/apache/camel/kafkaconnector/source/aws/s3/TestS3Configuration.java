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

package org.apache.camel.kafkaconnector.source.aws.s3;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.camel.component.aws.s3.S3Configuration;
import org.apache.camel.kafkaconnector.AWSConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestS3Configuration extends S3Configuration {
    private static final Logger LOG = LoggerFactory.getLogger(TestS3Configuration.class);
    private final String amazonHost;
    private final String region;

    private AmazonS3 amazonS3;

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

    public TestS3Configuration() {
        amazonHost = System.getProperty(AWSConfigs.AMAZON_AWS_HOST);
        region = Regions.valueOf(System.getProperty(AWSConfigs.REGION)).getName();
    }

    private AmazonS3 buildClient() {
        LOG.debug("Creating a new S3 client");
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTP);

        return AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonHost, region))
                .withCredentials(new TestAWSCredentialsProvider())
                .withClientConfiguration(clientConfiguration)
                .withPathStyleAccessEnabled(true)
                .build();
    }

    @Override
    public AmazonS3 getAmazonS3Client() {
        if (amazonS3 == null) {
            amazonS3 = buildClient();
        }

        return amazonS3;
    }
}
