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

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.PollingConsumer;
import org.apache.camel.kafkaconnector.utils.CamelMainSupport;
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

    private CamelMainSupport cms;
    private CamelSourceConnectorConfig config;
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
            final String unmarshaller = config.getString(CamelSourceConnectorConfig.CAMEL_SOURCE_UNMARSHAL_CONF);
            topic = config.getString(CamelSourceConnectorConfig.TOPIC_CONF);

            String localUrl = getLocalUrlWithPollingOptions(config);

            cms = new CamelMainSupport(props, remoteUrl, localUrl, null, unmarshaller);

            Endpoint endpoint = cms.getEndpoint(localUrl);
            consumer = endpoint.createPollingConsumer();
            consumer.start();
            
            cms.start();
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
                log.debug("Received exchange with");
                log.debug("\t from endpoint: {}", exchange.getFromEndpoint());
                log.debug("\t exchange id: {}", exchange.getExchangeId());
                log.debug("\t message id: {}", exchange.getMessage().getMessageId());
                log.debug("\t message body: {}", exchange.getMessage().getBody());
                log.debug("\t message headers: {}", exchange.getMessage().getHeaders());
                log.debug("\t message properties: {}", exchange.getProperties());

                // TODO: see if there is a better way to use sourcePartition an sourceOffset
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
        log.info("Stopping CamelSourceTask connector task");
        try {
            consumer.stop();
        } catch (Exception e) {
            log.error("Error stopping camel consumer: {}", e.getMessage());
        }
        try {
            cms.stop();
        } catch (Exception e) {
            throw new ConnectException("Failed to stop Camel context", e);
        } finally {
            log.info("CamelSourceTask connector task stopped");
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

    private String getLocalUrlWithPollingOptions(CamelSourceConnectorConfig config){
        long pollingConsumerQueueSize = config.getLong(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_CONF);
        long pollingConsumerBlockTimeout = config.getLong(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_CONF);
        boolean pollingConsumerBlockWhenFull = config.getBoolean(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_CONF);
        return LOCAL_URL + "?pollingConsumerQueueSize=" +pollingConsumerQueueSize+"&pollingConsumerBlockTimeout="+pollingConsumerBlockTimeout+"&pollingConsumerBlockWhenFull="+pollingConsumerBlockWhenFull;
    }

    public CamelMainSupport getCms() {
        return cms;
    }
}
