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

package org.apache.camel.kafkaconnector.common.clients.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Header;

/**
 * A very simple test message consumer that can consume messages of different types
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class KafkaClient<K, V> {
    private final ConsumerPropertyFactory consumerPropertyFactory;
    private final ProducerPropertyFactory producerPropertyFactory;
    private KafkaProducer<K, V> producer;
    private KafkaConsumer<K, V> consumer;

    private static class TestHeader implements Header {
        private final String key;
        private final String value;

        public TestHeader(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String key() {
            return this.key;
        }

        @Override
        public byte[] value() {
            return value.getBytes();
        }
    }


    /**
     * Constructs the properties using the given bootstrap server
     *
     * @param bootstrapServer the address of the server in the format
     *                        PLAINTEXT://${address}:${port}
     */
    public KafkaClient(String bootstrapServer) {
        this(new DefaultConsumerPropertyFactory(bootstrapServer), new DefaultProducerPropertyFactory(bootstrapServer));
    }

    /**
     * Constructs the properties using the given bootstrap server
     *
     * @param consumerPropertyFactory a property factory for Kafka client consumers
     * @param producerPropertyFactory a property factory for Kafka client producers
     */
    public KafkaClient(ConsumerPropertyFactory consumerPropertyFactory, ProducerPropertyFactory producerPropertyFactory) {
        this.consumerPropertyFactory = consumerPropertyFactory;
        this.producerPropertyFactory = producerPropertyFactory;

        producer = new KafkaProducer<>(producerPropertyFactory.getProperties());
        consumer = new KafkaConsumer<>(consumerPropertyFactory.getProperties());
    }

    /**
     * Consumes message from the given topic
     *
     * @param topic     the topic to consume the messages from
     * @param recordConsumer the a function to consume the received messages
     */
    public void consumeAvailable(String topic, Consumer<ConsumerRecord<K, V>> recordConsumer) {
        consumer.subscribe(Collections.singletonList(topic));

        ConsumerRecords<K, V> records = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<K, V> record : records) {
            recordConsumer.accept(record);
        }
    }


    /**
     * Consumes message from the given topic until the predicate returns false
     *
     * @param topic     the topic to consume the messages from
     * @param predicate the predicate to test when the messages arrive
     */
    public void consume(String topic, Predicate<ConsumerRecord<K, V>> predicate) {
        consumer.subscribe(Collections.singletonList(topic));

        // TODO: handle failures, timeouts, etc
        while (true) {
            ConsumerRecords<K, V> records = consumer.poll(Duration.ofMillis(10));
            for (ConsumerRecord<K, V> record : records) {
                if (!predicate.test(record)) {
                    return;
                }
            }
        }
    }


    /**
     * Sends data to a topic
     *
     * @param topic   the topic to send data to
     * @param message the message to send
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void produce(String topic, V message) throws ExecutionException, InterruptedException {
        ProducerRecord<K, V> record = new ProducerRecord<>(topic, message);

        Future<RecordMetadata> future = producer.send(record);

        future.get();
    }


    /**
     * Sends data to a topic
     *
     * @param topic   the topic to send data to
     * @param message the message to send
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void produce(String topic, V message, Map<String, String> headers) throws ExecutionException, InterruptedException {
        ProducerRecord<K, V> record = new ProducerRecord<>(topic, message);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            record.headers().add(new TestHeader(entry.getKey(), entry.getValue()));
        }

        Future<RecordMetadata> future = producer.send(record);

        future.get();
    }

    public AdminClient getAdminClient() {
        return AdminClient.create(producerPropertyFactory.getProperties());
    }

    /**
     * Delete a topic
     *
     * @param topic the topic to be deleted
     */
    public void deleteTopic(String topic) {
        AdminClient adminClient = getAdminClient();

        adminClient.deleteTopics(Collections.singleton(topic));
    }
}
