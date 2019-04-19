/**
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
package org.apache.camel.kafkaconnector;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CamelSourceTask extends SourceTask {
    private static final Logger log = LoggerFactory.getLogger(CamelSourceTask.class);

    private String taskName;
    private String topic;
    private CamelContext camel;
    private String localUrl;
    private ConsumerTemplate consumer;

    @Override
    public String version() {
        return new CamelSourceConnector().version();
    }

    @Override
    public void start(Map<String, String> props) {
        try {
            taskName = props.get(CamelSourceConnector.NAME_CONFIG);
            topic = props.get(CamelSourceConnector.TOPIC_CONFIG);
            log.info("Starting connector task {}", taskName);

            camel = new DefaultCamelContext();

            localUrl = "direct:" + taskName;
            final String remoteUrl = props.get(CamelSourceConnector.COMPONENT_CONFIG) + "://" + props.get(CamelSourceConnector.ADDRESS_CONFIG) + "?" + props.get(CamelSourceConnector.OPTIONS_CONFIG);

            log.info("Creating Camel route from({}).to({})", remoteUrl, localUrl);
            camel.addRoutes(new RouteBuilder() {
                public void configure() {
                    from(remoteUrl).to(localUrl);
                }
            });

            consumer = camel.createConsumerTemplate();

            camel.start();
        } catch (Exception e) {
            throw new ConnectException("Failed to create and start Camel context", e);
        }
    }

    @Override
    public List<SourceRecord> poll() {
        List<SourceRecord> records = new ArrayList<>();

        while (true)    {
            Exchange ex = consumer.receiveNoWait(localUrl);

            if (ex != null) {
                log.info("Received exchange with");
                log.info("\t from endpoint: {}", ex.getFromEndpoint());
                log.info("\t exchange id: {}", ex.getExchangeId());
                log.info("\t message id: {}", ex.getMessage().getMessageId());
                log.info("\t message body: {}", ex.getMessage().getBody());
                Map<String, String> sourcePartition = Collections.singletonMap("filename", ex.getFromEndpoint().toString());
                Map<String, String> sourceOffset = Collections.singletonMap("position", ex.getExchangeId());
                SourceRecord record = new SourceRecord(sourcePartition, sourceOffset, topic, Schema.BYTES_SCHEMA, ex.getMessage().getBody());
                records.add(record);
                consumer.doneUoW(ex);
            } else {
                break;
            }
        }

        return records;
    }

    @Override
    public void stop() {
        try {
            camel.stop();
        } catch (Exception e) {
            throw new ConnectException("Failed to stop Camel context", e);
        }
    }
}

