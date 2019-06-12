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

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CamelSourceTask extends SourceTask {
    private static final Logger log = LoggerFactory.getLogger(CamelSourceTask.class);
    private static final String LOCAL_URL = "direct:end";
    private static final String HEADER_CAMEL_PREFIX = "CamelHeader";
    private static final String PROPERTY_CAMEL_PREFIX = "CamelProperty";

    private CamelSourceConnectorConfig config;
    private CamelContext camel;
    private PollingConsumer consumer;
    private String topic;
    private Long maxBatchPollSize;
    private Long maxPollDuration;

    @Override
    public String version() {
        return new CamelSourceConnector().version();
    }

    @Override
    public void start(Map<String, String> props) {
        try {
            log.info("Starting CamelSourceTask connector task");
            config = new CamelSourceConnectorConfig(props);

            maxBatchPollSize = config.getLong(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_CONF);
            maxPollDuration = config.getLong(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_POLL_DURATION_CONF);

            final String remoteUrl = config.getString(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF);
            topic = config.getString(CamelSourceConnectorConfig.TOPIC_CONF);

            camel = new DefaultCamelContext();

            log.info("Creating Camel route from({}).to({})", remoteUrl, LOCAL_URL);
            camel.addRoutes(new RouteBuilder() {
                public void configure() {
                    from(remoteUrl).to(LOCAL_URL);
                }
            });

            // TODO: Add option to configure pollingConsumerQueueSize,
            // pollingConsumerBlockWhenFull and pollingConsumerBlockTimeout in
            // LOCAL_URL

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
    public synchronized List<SourceRecord> poll() {
        Long startPollEpochMilli = Instant.now().toEpochMilli();
        Long collectedRecords = 0L;

        List<SourceRecord> records = new ArrayList<>();

        while (collectedRecords < maxBatchPollSize && (Instant.now().toEpochMilli()-startPollEpochMilli) < maxPollDuration) {
            Exchange exchange = consumer.receiveNoWait();

            if (exchange != null) {
                log.info("Received exchange with");
                log.info("\t from endpoint: {}", exchange.getFromEndpoint());
                log.info("\t exchange id: {}", exchange.getExchangeId());
                log.info("\t message id: {}", exchange.getMessage().getMessageId());
                log.info("\t message body: {}", exchange.getMessage().getBody());
                log.info("\t message headers: {}", exchange.getMessage().getHeaders());
                log.info("\t message properties: {}", exchange.getProperties());

                // TODO: see if there is a better way to use sourcePartition and
                // sourceOffset
                Map<String, String> sourcePartition = Collections.singletonMap("filename", exchange.getFromEndpoint().toString());
                Map<String, String> sourceOffset = Collections.singletonMap("position", exchange.getExchangeId());

                SourceRecord record = new SourceRecord(sourcePartition, sourceOffset, topic, Schema.BYTES_SCHEMA, exchange.getMessage().getBody());
                if (exchange.getMessage().hasHeaders()) {
                    setAdditionalHeaders(record, exchange.getMessage().getHeaders(), HEADER_CAMEL_PREFIX);
                }
                if (exchange.hasProperties()) {
                    setAdditionalHeaders(record, exchange.getProperties(), PROPERTY_CAMEL_PREFIX);
                }
                records.add(record);
                collectedRecords++;
            } else {
                break;
            }
        }

        if (records.isEmpty()) {
            return null;
        } else {
            return records;
        }
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

    private void setAdditionalHeaders(SourceRecord record, Map<String, Object> map, String prefix) {

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String keyCamelHeader = prefix + key;

            if (value instanceof String) {
                record.headers().addString(keyCamelHeader, (String)value);
            } else if (value instanceof Boolean) {
                record.headers().addBoolean(keyCamelHeader, (boolean)value);
            } else if (value instanceof Byte) {
                record.headers().addByte(keyCamelHeader, (byte)value);
            } else if (value instanceof Byte[]) {
                record.headers().addBytes(keyCamelHeader, (byte[])value);
            } else if (value instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String convertedDate = sdf.format(value);
                record.headers().addString(keyCamelHeader, (String)convertedDate);
            } else if (value instanceof BigDecimal) {
                record.headers().addDecimal(keyCamelHeader, (BigDecimal)value);
            } else if (value instanceof Double) {
                record.headers().addDouble(keyCamelHeader, (double)value);
            } else if (value instanceof Float) {
                record.headers().addFloat(keyCamelHeader, (float)value);
            } else if (value instanceof Integer) {
                record.headers().addInt(keyCamelHeader, (int)value);
            } else if (value instanceof Long) {
                record.headers().addLong(keyCamelHeader, (long)value);
            } else if (value instanceof Short) {
                record.headers().addShort(keyCamelHeader, (short)value);
            } else if (value instanceof Time) {
                record.headers().addTime(keyCamelHeader, (Time)value);
            } else if (value instanceof Timestamp) {
                record.headers().addTimestamp(keyCamelHeader, (Timestamp)value);
            }
        }
    }
}
