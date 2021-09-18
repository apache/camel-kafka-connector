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
package org.apache.camel.kafkaconnector.elasticsearch.common;

import java.util.Map;

import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.test.AbstractTestMessageProducer;

public class ElasticSearchIndexMessageProducer extends AbstractTestMessageProducer<String> {

    public ElasticSearchIndexMessageProducer(String bootStrapServer, String topicName, int count) {
        super(bootStrapServer, topicName, count);
    }

    public ElasticSearchIndexMessageProducer(KafkaClient<String, String> kafkaClient, String topicName, int count) {
        super(kafkaClient, topicName, count);
    }

    @Override
    public Map<String, String> messageHeaders(String text, int current) {
        return null;
    }

    @Override
    public String testMessageContent(int current) {
        return "{\n"
                + "  \"tags\": [\n"
                + "    \"opster\",\n"
                + "    \"elasticsearch\"\n"
                + "  ],\n"
                + "  \"date\": \"01-01-2020\",\n"
                + "  \"counter\": \"" + current + "\"\n"
                + "}";
    }
}