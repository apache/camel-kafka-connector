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

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTestMessageConsumer<T> implements TestMessageConsumer<T> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractTestMessageConsumer.class);

    private final KafkaClient<String, T> kafkaClient;
    private final String topicName;
    private final int count;
    private final List<ConsumerRecord<String, T>> receivedMessages;
    private volatile int received;

    public AbstractTestMessageConsumer(KafkaClient<String, T> kafkaClient, String topicName, int count) {
        this.kafkaClient = kafkaClient;
        this.topicName = topicName;
        this.count = count;

        receivedMessages = new ArrayList<>(count);
    }

    private boolean checkRecord(ConsumerRecord<String, T> record) {
        LOG.debug("Received: {}", record.value());
        received++;
        receivedMessages.add(record);

        if (received == count) {
            return false;
        }

        return true;
    }

    @Override
    public void consumeMessages() {
        kafkaClient.consume(topicName, this::checkRecord);
    }

    @Override
    public List<ConsumerRecord<String, T>> consumedMessages() {
        return receivedMessages;
    }
}
