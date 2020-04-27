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

package org.apache.camel.kafkaconnector.clients.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Predicate;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * A very simple test message consumer that can consume messages of different types
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class KafkaClient<K, V> {
    private final ConsumerPropertyFactory propertyProducer;
    private final ProducerPropertyFactory producerPropertyFactory;


    /**
     * Constructs the properties using the given bootstrap server
     *
     * @param bootstrapServer the address of the server in the format
     *                        PLAINTEXT://${address}:${port}
     */
    public KafkaClient(String bootstrapServer) {
        this.propertyProducer = new DefaultConsumerPropertyFactory(bootstrapServer);
        this.producerPropertyFactory = new DefaultProducerPropertyFactory(bootstrapServer);
    }


    /**
     * Consumes message from the given topic until the predicate returns false
     *
     * @param topic     the topic to consume the messages from
     * @param predicate the predicate to test when the messages arrive
     */
    public void consume(String topic, Predicate<ConsumerRecord<K, V>> predicate) {
        Properties props = propertyProducer.getProperties();

        KafkaConsumer<K, V> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));

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
        Properties props = producerPropertyFactory.getProperties();

        KafkaProducer<K, V> producer = new KafkaProducer<>(props);
        ProducerRecord<K, V> record = new ProducerRecord<>(topic, message);

        Future<RecordMetadata> future = producer.send(record);

        future.get();
    }


}
