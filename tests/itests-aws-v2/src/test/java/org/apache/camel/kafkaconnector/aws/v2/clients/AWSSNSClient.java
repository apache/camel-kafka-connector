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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.ListTopicsRequest;
import software.amazon.awssdk.services.sns.model.ListTopicsResponse;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;
import software.amazon.awssdk.services.sns.model.Topic;

public class AWSSNSClient {
    private static final Logger LOG = LoggerFactory.getLogger(AWSSNSClient.class);

    private final SnsClient sns;
    private final int maxNumberOfMessages = 1;

    public AWSSNSClient(SnsClient sns) {
        this.sns = sns;
    }

    public List<Topic> getTopics() {
        ListTopicsRequest request = ListTopicsRequest.builder()
                .build();

        ListTopicsResponse result = sns.listTopics(request);

        return result.topics();
    }

    public String createTopic(String topic) {
        CreateTopicRequest request = CreateTopicRequest.builder()
                .name(topic)
                .build();

        CreateTopicResponse response = sns.createTopic(request);

        if (response.sdkHttpResponse().isSuccessful()) {
            return response.topicArn();
        }

        LOG.warn("Unable to create the topic: {}", response.sdkHttpResponse().statusCode());
        return null;
    }

    public void subscribeSQS(String topicArn, String sqsArn) {
        SubscribeRequest request = SubscribeRequest.builder()
                .protocol("sqs")
                .endpoint(sqsArn)
                .returnSubscriptionArn(true)
                .topicArn(topicArn)
                .build();

        SubscribeResponse response = sns.subscribe(request);
        if (!response.sdkHttpResponse().isSuccessful()) {
            LOG.warn("Unable to create sqs subscription from sqs queue {} to sns topic: {},  status code: {}", sqsArn, topicArn, response.sdkHttpResponse().statusCode());
        }
    }
}
