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

package org.apache.camel.kafkaconnector.aws.v1.clients;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AWSSQSClient {
    private static final Logger LOG = LoggerFactory.getLogger(AWSSQSClient.class);

    private final AmazonSQS sqs;
    private int maxWaitTime = 10;
    private int maxNumberOfMessages = 1;

    public AWSSQSClient(AmazonSQS sqs) {
        this.sqs = sqs;
    }

    public String getQueue(String queue) {
        final Map<String, String> queueAttributes = new HashMap<>();

        final CreateQueueRequest createFifoQueueRequest = new CreateQueueRequest(queue)
                .withAttributes(queueAttributes);

        return sqs.createQueue(createFifoQueueRequest)
                .getQueueUrl();
    }


    public void receive(String queue, Predicate<List<Message>> predicate) {
        final String queueUrl = getQueue(queue);

        LOG.debug("Consuming messages from {}", queueUrl);

        final ReceiveMessageRequest request = new ReceiveMessageRequest(queueUrl)
                .withWaitTimeSeconds(maxWaitTime)
                .withMaxNumberOfMessages(maxNumberOfMessages);

        while (true) {
            ReceiveMessageResult result = sqs.receiveMessage(request);

            List<Message> messages = result.getMessages();

            if (!predicate.test(messages)) {
                return;
            }
        }
    }


    public void send(String queue, String body) {
        final String queueUrl = getQueue(queue);

        LOG.debug("Sending messages to {}", queueUrl);

        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(body);

        sqs.sendMessage(request);
    }

    public boolean deleteQueue(String queue) {
        GetQueueUrlResult getQueueUrlResult = sqs.getQueueUrl(queue);

        if (getQueueUrlResult.getSdkHttpMetadata().getHttpStatusCode() == 404) {
            return true;
        } else {
            if (getQueueUrlResult.getSdkHttpMetadata().getHttpStatusCode() != 200) {
                LOG.warn("Unable to get queue {} for deletion", queue);

                return false;
            }
        }

        DeleteQueueResult result = sqs.deleteQueue(getQueueUrlResult.getQueueUrl());

        if (result.getSdkHttpMetadata().getHttpStatusCode() != 200) {
            LOG.warn("Unable to delete queue {}", queue);
            return false;
        }

        return true;
    }
}
