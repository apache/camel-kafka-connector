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
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.DeleteQueueRequest;
import software.amazon.awssdk.services.sqs.model.DeleteQueueResponse;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.QueueDoesNotExistException;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;


public class AWSSQSClient {
    private static final Logger LOG = LoggerFactory.getLogger(AWSSQSClient.class);

    private final SqsClient sqs;
    private int maxWaitTime = 10;
    private int maxNumberOfMessages = 1;

    public AWSSQSClient(SqsClient sqs) {
        this.sqs = sqs;
    }

    public String getQueue(String queue) {
        GetQueueUrlRequest getQueueUrlRequest = GetQueueUrlRequest.builder()
                .queueName(queue)
                .build();

        GetQueueUrlResponse getQueueUrlResult = sqs.getQueueUrl(getQueueUrlRequest);

        return getQueueUrlResult.queueUrl();
    }

    public String createQueue(String queue) {
        final CreateQueueRequest createFifoQueueRequest = CreateQueueRequest.builder()
                .queueName(queue)
                .build();

        CreateQueueResponse response = sqs.createQueue(createFifoQueueRequest);

        if (response.sdkHttpResponse().isSuccessful()) {
            return response.queueUrl();
        }

        LOG.warn("Unable to create the queue: {}", response.sdkHttpResponse().statusCode());
        return null;
    }


    public void receive(String queue, Predicate<List<Message>> predicate) {
        String queueUrl;

        try {
            queueUrl = getQueue(queue);
        } catch (QueueDoesNotExistException e) {
            queueUrl = createQueue(queue);
        }

        LOG.debug("Consuming messages from {}", queueUrl);

        final ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .waitTimeSeconds(maxWaitTime)
                .maxNumberOfMessages(maxNumberOfMessages)
                .build();

        while (true) {
            ReceiveMessageResponse response = sqs.receiveMessage(request);

            if (!response.sdkHttpResponse().isSuccessful()) {
                LOG.warn("Did not receive a success response from SQS: status code {}",
                        response.sdkHttpResponse().statusCode());
            }

            List<Message> messages = response.messages();

            if (!predicate.test(messages)) {
                return;
            }
        }
    }


    public void send(String queue, String body) {
        String queueUrl;

        try {
            queueUrl = getQueue(queue);
        } catch (QueueDoesNotExistException e) {
            queueUrl = createQueue(queue);
        }

        LOG.debug("Sending messages to {}", queueUrl);

        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(body)
                .build();

        sqs.sendMessage(request);
    }

    public boolean deleteQueue(String queue) {
        String queueUrl;

        try {
            queueUrl = getQueue(queue);
            DeleteQueueRequest deleteQueueRequest = DeleteQueueRequest.builder()
                    .queueUrl(queueUrl)
                    .build();

            DeleteQueueResponse result = sqs.deleteQueue(deleteQueueRequest);

            if (!result.sdkHttpResponse().isSuccessful()) {
                LOG.warn("Unable to delete queue {}", queue);
                return false;
            }

            return true;
        } catch (QueueDoesNotExistException e) {
            return true;
        }
    }
}
