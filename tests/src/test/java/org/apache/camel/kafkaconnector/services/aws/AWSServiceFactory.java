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

package org.apache.camel.kafkaconnector.services.aws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AWSServiceFactory {
    private static final Logger LOG = LoggerFactory.getLogger(AWSServiceFactory.class);

    private AWSServiceFactory() {
    }

    public static AWSService createSQSService() {
        String awsInstanceType = System.getProperty("aws-service.instance.type");
        LOG.info("Creating a {} AWS SQS instance", awsInstanceType);

        if (awsInstanceType == null || awsInstanceType.equals("local-aws-container")) {
            return new AWSSQSLocalContainerService();
        }

        if (awsInstanceType.equals("remote")) {
            return new AWSRemoteService(AWSRemoteService::newSQSClient);
        }

        LOG.error("Invalid AWS instance type: {}. Must be either 'remote' or 'local-aws-container'",
                awsInstanceType);
        throw new UnsupportedOperationException("Invalid AWS instance type");
    }

    public static AWSService createSNSService() {
        String awsInstanceType = System.getProperty("aws-service.instance.type");
        LOG.info("Creating a {} AWS SNS instance", awsInstanceType);

        if (awsInstanceType == null || awsInstanceType.equals("local-aws-container")) {
            return new AWSSNSLocalContainerService();
        }

        if (awsInstanceType.equals("remote")) {
            return new AWSRemoteService(AWSRemoteService::newSQSClient);
        }

        LOG.error("Invalid AWS instance type: {}. Must be either 'remote' or 'local-aws-container'",
                awsInstanceType);
        throw new UnsupportedOperationException("Invalid AWS instance type");

    }

    public static AWSService createKinesisService() {
        String awsInstanceType = System.getProperty("aws-service.kinesis.instance.type");
        LOG.info("Creating a {} AWS kinesis instance", awsInstanceType);

        if (awsInstanceType == null || awsInstanceType.equals("local-aws-container")) {
            return new AWSKinesisLocalContainerService();
        }

        if (awsInstanceType.equals("remote")) {
            return new AWSRemoteService(AWSClientUtils::newKinesisClient);
        }

        LOG.error("Invalid AWS instance type: {}. Must be either 'remote' or 'local-aws-container'",
                awsInstanceType);
        throw new UnsupportedOperationException("Invalid AWS instance type");
    }

    public static AWSService createS3Service() {
        String awsInstanceType = System.getProperty("aws-service.instance.type");
        LOG.info("Creating a {} AWS S3 instance", awsInstanceType);

        if (awsInstanceType == null || awsInstanceType.equals("local-aws-container")) {
            return new AWSS3LocalContainerService();
        }

        if (awsInstanceType.equals("remote")) {
            return new AWSRemoteService(AWSClientUtils::newS3Client);
        }

        LOG.error("Invalid AWS instance type: {}. Must be either 'remote' or 'local-aws-container'",
                awsInstanceType);
        throw new UnsupportedOperationException("Invalid AWS instance type");
    }
}
