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

package org.apache.camel.kafkaconnector.common.test;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTestMessageProducer<T> implements TestMessageProducer<T> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractTestMessageProducer.class);

    private final KafkaClient<String, T> kafkaClient;
    private final String topicName;
    private final int count;

    public AbstractTestMessageProducer(KafkaClient<String, T> kafkaClient, String topicName, int count) {
        this.kafkaClient = kafkaClient;
        this.topicName = topicName;
        this.count = count;
    }

    public AbstractTestMessageProducer(String bootstrapServer, String topicName, int count) {
        this.kafkaClient = createKafkaClient(bootstrapServer);
        this.topicName = topicName;
        this.count = count;
    }

    protected KafkaClient<String, T> createKafkaClient(String bootstrapServer) {
        return new KafkaClient<>(bootstrapServer);
    }

    public void produceMessages() throws ExecutionException, InterruptedException {
        LOG.trace("Producing messages ...");
        for (int i = 0; i < count; i++) {
            T message = testMessageContent(i);
            Map<String, String> headers = messageHeaders(message, i);

            if (headers == null) {
                kafkaClient.produce(topicName, message);
            } else {
                kafkaClient.produce(topicName, message, headers);
            }
        }
    }
}
