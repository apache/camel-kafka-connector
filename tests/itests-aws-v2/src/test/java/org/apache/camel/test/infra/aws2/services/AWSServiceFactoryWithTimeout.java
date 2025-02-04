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
package org.apache.camel.test.infra.aws2.services;

import java.util.function.Supplier;

import org.apache.camel.test.infra.aws.common.services.AWSInfraService;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.common.services.SimpleTestServiceBuilder;


public final class AWSServiceFactoryWithTimeout {
    private AWSServiceFactoryWithTimeout() {
    }

    public static <T extends AWSInfraService> SimpleTestServiceBuilder<T> builder() {
        return new SimpleTestServiceBuilder("aws");
    }

    private static AWSService createService(Supplier<AWSInfraService> supplier) {
        return (AWSService)builder().addRemoteMapping(AWSRemoteInfraService::new).addLocalMapping(supplier).withPropertyNameFormat("%s-service.instance.type").build();
    }

    public static AWSService createKinesisService() {
        return (AWSService)builder().addRemoteMapping(AWSRemoteInfraService::new).addLocalMapping(AWSKinesisLocalContainerServiceWithTimeout::new).withPropertyNameFormat("%s-service.kinesis.instance.type").build();
    }

    public static AWSService createSQSService() {
        return createService(AWSSQSLocalContainerServiceWithTimeout::new);
    }

    public static AWSService createS3Service() {
        return createService(AWSS3LocalContainerServiceWithTimeout::new);
    }

    public static AWSService createSNSService() {
        return createService(AWSSNSLocalContainerServiceWithTimeout::new);
    }

    public static AWSService createCloudWatchService() {
        return createService(AWSCloudWatchLocalContainerServiceWithTimeout::new);
    }

    public static AWSService createEC2Service() {
        return createService(AWSEC2LocalContainerServiceWithTimeout::new);
    }

    public static AWSService createEventBridgeService() {
        return createService(AWSEventBridgeLocalContainerServiceWithTimeout::new);
    }

    public static AWSService createIAMService() {
        return createService(AWSIAMLocalContainerServiceWithTimeout::new);
    }

    public static AWSService createKMSService() {
        return createService(AWSKMSLocalContainerServiceWithTimeout::new);
    }

    public static AWSService createLambdaService() {
        return createService(AWSLambdaLocalContainerServiceWithTimeout::new);
    }

    public static AWSService createSTSService() {
        return createService(AWSSTSLocalContainerServiceWithTimeout::new);
    }

    public static AWSService createDynamodbService() {
        return createService(AWSDynamodbLocalContainerServiceWithTimeout::new);
    }

}
