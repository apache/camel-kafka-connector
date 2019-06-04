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
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.PollingConsumer;
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
    private static final String LOCAL_URL = "direct:end";

    private CamelSourceConnectorConfig config;
    private CamelContext camel;
    private PollingConsumer consumer;
    private String topic;

    @Override
    public String version() {
        return new CamelSourceConnector().version();
    }

    @Override
    public void start(Map<String, String> props) {
        try {
            log.info("Starting CamelSourceTask connector task");
            config = new CamelSourceConnectorConfig(props);

            final String remoteUrl = config.getString(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF);
            topic = config.getString(CamelSourceConnectorConfig.TOPIC_CONF);

            camel = new DefaultCamelContext();

            log.info("Creating Camel route from({}).to({})", remoteUrl, LOCAL_URL);
            camel.addRoutes(new RouteBuilder() {
                public void configure() {
                    from(remoteUrl).to(LOCAL_URL);
                }
            });

            //TODO: Add option to configure pollingConsumerQueueSize, pollingConsumerBlockWhenFull and pollingConsumerBlockTimeout in LOCAL_URL

            Endpoint endpoint = camel.getEndpoint(LOCAL_URL);
            consumer = endpoint.createPollingConsumer();
            consumer.start();

            log.info("Starting CamelContext");
            camel.start();
            log.info("CamelContext started");
            log.info("CamelSourceTask connector task started");
        } catch (Exception e) {
            throw new ConnectException("Failed to create and start Camel context", e);
        }
    }

    @Override
    public List<SourceRecord> poll() {
        List<SourceRecord> records = new ArrayList<>();

        Exchange exchange = consumer.receiveNoWait();

        if(exchange==null){
            return null;
        }

        log.info("Received exchange with");
        log.info("\t from endpoint: {}", exchange.getFromEndpoint());
        log.info("\t exchange id: {}", exchange.getExchangeId());
        log.info("\t message id: {}", exchange.getMessage().getMessageId());
        log.info("\t message body: {}", exchange.getMessage().getBody());

        //TODO: see if there is a better way to use sourcePartition and sourceOffset
        Map<String, String> sourcePartition = Collections.singletonMap("filename", exchange.getFromEndpoint().toString());
        Map<String, String> sourceOffset = Collections.singletonMap("position", exchange.getExchangeId());

        SourceRecord record = new SourceRecord(sourcePartition, sourceOffset, topic, Schema.BYTES_SCHEMA, exchange.getMessage().getBody());
        records.add(record);

        return records;
    }

    @Override
    public void stop() {
        try {
            consumer.stop();
        } catch (Exception e) {
            throw new ConnectException("Failed to stop Polling Consumer", e);
        }
        try {
            camel.stop();
        } catch (Exception e) {
            throw new ConnectException("Failed to stop Camel context", e);
        }
    }
}

