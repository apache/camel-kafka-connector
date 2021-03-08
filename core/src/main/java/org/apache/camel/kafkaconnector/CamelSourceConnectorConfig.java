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
package org.apache.camel.kafkaconnector;

import java.util.Map;

import org.apache.camel.LoggingLevel;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

public class CamelSourceConnectorConfig extends CamelConnectorConfig {
    public static final String CAMEL_SOURCE_UNMARSHAL_DEFAULT = null;
    public static final String CAMEL_SOURCE_UNMARSHAL_CONF = "camel.source.unmarshal";
    public static final String CAMEL_SOURCE_UNMARSHAL_DOC = "The camel dataformat name to use to unmarshal data from the source";

    public static final String CAMEL_SOURCE_MARSHAL_DEFAULT = null;
    public static final String CAMEL_SOURCE_MARSHAL_CONF = "camel.source.marshal";
    public static final String CAMEL_SOURCE_MARSHAL_DOC = "The camel dataformat name to use to unmarshal data to the topic";

    public static final String CAMEL_SOURCE_URL_DEFAULT = null;
    public static final String CAMEL_SOURCE_URL_CONF = "camel.source.url";

    public static final String CAMEL_SOURCE_COMPONENT_DEFAULT = null;
    public static final String CAMEL_SOURCE_COMPONENT_CONF = "camel.source.component";

    public static final String CAMEL_SOURCE_COMPONENT_DOC = "The camel component to use. This is normally set by default for you. It is ignored if " + CAMEL_SOURCE_URL_CONF + " is set.";
    public static final String CAMEL_SOURCE_URL_DOC = "The camel url to configure the source. If this is set " + CAMEL_SOURCE_COMPONENT_CONF
            + " and all the properties starting with " + CamelSourceTask.getCamelSourceEndpointConfigPrefix() + ".<" + CAMEL_SOURCE_COMPONENT_CONF + " value> are ignored.";

    public static final String TOPIC_DEFAULT = "test";
    public static final String TOPIC_CONF = "topics";
    public static final String TOPIC_DOC = "A list of topics to use as output for this connector";

    public static final Long CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_DEFAULT = 1000L;
    public static final String CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_CONF = "camel.source.maxBatchPollSize";
    public static final String CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_DOC = "The max number of messages retrieved in a single poll()";

    public static final Long CAMEL_SOURCE_MAX_POLL_DURATION_DEFAULT = 1000L;
    public static final String CAMEL_SOURCE_MAX_POLL_DURATION_CONF = "camel.source.maxPollDuration";
    public static final String CAMEL_SOURCE_MAX_POLL_DURATION_DOC = "The maximum time in milliseconds spent in a single call to poll()";

    public static final Integer CAMEL_SOURCE_MAX_NOT_COMMITTED_RECORDS_DEFAULT = 1024;
    public static final String CAMEL_SOURCE_MAX_NOT_COMMITTED_RECORDS_CONF = "camel.source.maxNotCommittedRecords";
    public static final String CAMEL_SOURCE_MAX_NOT_COMMITTED_RECORDS_DOC = "The maximum number of non committed kafka connect records that can be tolerated before stop polling new records (rounded to the next power of 2) with a minimum of 4.";

    public static final Long CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_DEFAULT = 1000L;
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_CONF = "camel.source.pollingConsumerQueueSize";
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_DOC = "The queue size for the internal hand-off queue between the polling consumer, and producers sending data into the queue.";

    public static final Long CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_DEFAULT = 0L;
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_CONF = "camel.source.pollingConsumerBlockTimeout";
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_DOC = "To use a timeout (in milliseconds) when the producer is blocked if the internal queue is full. If the value is 0 or negative then no timeout is in use.";

    public static final Boolean CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_DEFAULT = true;
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_CONF = "camel.source.pollingConsumerBlockWhenFull";
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_DOC = "Whether to block any producer if the internal queue is full.";

    public static final String CAMEL_SOURCE_MESSAGE_HEADER_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_MESSAGE_HEADER_KEY_CONF = "camel.source.camelMessageHeaderKey";
    public static final String CAMEL_SOURCE_MESSAGE_HEADER_KEY_DOC = "The name of a camel message header containing an unique key that can be used as a Kafka message key."
          +  " If this is not specified, then the Kafka message will not have a key.";

    public static final String CAMEL_SOURCE_CONTENT_LOG_LEVEL_DEFAULT = LoggingLevel.OFF.toString();
    public static final String CAMEL_SOURCE_CONTENT_LOG_LEVEL_CONF = "camel.source.contentLogLevel";
    public static final String CAMEL_SOURCE_CONTENT_LOG_LEVEL_DOC = "Log level for the record's content (default: " + CAMEL_SOURCE_CONTENT_LOG_LEVEL_DEFAULT + "). Valid values: TRACE, DEBUG, INFO, WARN, ERROR, OFF.";

    private static final ConfigDef CONFIG_DEF = new ConfigDef()
        .define(CAMEL_SOURCE_URL_CONF, Type.STRING, CAMEL_SOURCE_URL_DEFAULT, Importance.HIGH, CAMEL_SOURCE_URL_DOC)
        .define(CAMEL_SOURCE_UNMARSHAL_CONF, Type.STRING, CAMEL_SOURCE_UNMARSHAL_DEFAULT, Importance.HIGH, CAMEL_SOURCE_UNMARSHAL_DOC)
        .define(CAMEL_SOURCE_MARSHAL_CONF, Type.STRING, CAMEL_SOURCE_MARSHAL_DEFAULT, Importance.HIGH, CAMEL_SOURCE_MARSHAL_DOC)
        .define(TOPIC_CONF, ConfigDef.Type.STRING, TOPIC_DEFAULT, ConfigDef.Importance.HIGH, TOPIC_DOC)
        .define(CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_CONF, Type.LONG, CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_DOC)
        .define(CAMEL_SOURCE_MAX_POLL_DURATION_CONF, Type.LONG, CAMEL_SOURCE_MAX_POLL_DURATION_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_MAX_POLL_DURATION_DOC)
        .define(CAMEL_SOURCE_MAX_NOT_COMMITTED_RECORDS_CONF, Type.INT, CAMEL_SOURCE_MAX_NOT_COMMITTED_RECORDS_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_MAX_NOT_COMMITTED_RECORDS_DOC)
        .define(CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_CONF, Type.LONG, CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_DOC)
        .define(CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_CONF, Type.LONG, CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_DOC)
        .define(CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_CONF, Type.BOOLEAN, CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_DOC)
        .define(CAMEL_SOURCE_MESSAGE_HEADER_KEY_CONF, Type.STRING, CAMEL_SOURCE_MESSAGE_HEADER_KEY_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_MESSAGE_HEADER_KEY_DOC)
        .define(CAMEL_SOURCE_COMPONENT_CONF, Type.STRING, CAMEL_SOURCE_COMPONENT_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_COMPONENT_DOC)
        .define(CAMEL_SOURCE_CONTENT_LOG_LEVEL_CONF, Type.STRING, CAMEL_SOURCE_CONTENT_LOG_LEVEL_DEFAULT, Importance.HIGH, CAMEL_SOURCE_CONTENT_LOG_LEVEL_DOC)
        .define(CAMEL_CONNECTOR_AGGREGATE_CONF, Type.STRING, CAMEL_CONNECTOR_AGGREGATE_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_AGGREGATE_DOC)
        .define(CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF, Type.INT, CAMEL_CONNECTOR_AGGREGATE_SIZE_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_AGGREGATE_SIZE_DOC)
        .define(CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_CONF, Type.LONG, CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_DOC)
        .define(CAMEL_CONNECTOR_ERROR_HANDLER_CONF, Type.STRING, CAMEL_CONNECTOR_ERROR_HANDLER_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_ERROR_HANDLER_DOC)
        .define(CAMEL_CONNECTOR_ERROR_HANDLER_MAXIMUM_REDELIVERIES_CONF, Type.INT, CAMEL_CONNECTOR_ERROR_HANDLER_MAXIMUM_REDELIVERIES_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_ERROR_HANDLER_MAXIMUM_REDELIVERIES_DOC)
        .define(CAMEL_CONNECTOR_ERROR_HANDLER_REDELIVERY_DELAY_CONF, Type.LONG, CAMEL_CONNECTOR_ERROR_HANDLER_REDELIVERY_DELAY_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_ERROR_HANDLER_REDELIVERY_DELAY_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_CONF, Type.BOOLEAN, CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF, Type.STRING, CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_CONF, Type.STRING, CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_MEMORY_DIMENSION_CONF, Type.INT, CAMEL_CONNECTOR_IDEMPOTENCY_MEMORY_DIMENSION_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_MEMORY_DIMENSION_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_CONF, Type.STRING, CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_TOPIC_CONF, Type.STRING, CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_TOPIC_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_TOPIC_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_BOOTSTRAP_SERVERS_CONF, Type.STRING, CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_BOOTSTRAP_SERVERS_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_BOOTSTRAP_SERVERS_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_MAX_CACHE_SIZE_CONF, Type.INT, CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_MAX_CACHE_SIZE_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_MAX_CACHE_SIZE_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_POLL_DURATION_CONF, Type.INT, CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_POLL_DURATION_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_POLL_DURATION_DOC)
        .define(CAMEL_CONNECTOR_REMOVE_HEADERS_PATTERN_CONF, Type.STRING, CAMEL_CONNECTOR_REMOVE_HEADERS_PATTERN_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_REMOVE_HEADERS_PATTERN_DOC)
        .define(CAMEL_CONNECTOR_MAP_PROPERTIES_CONF, Type.BOOLEAN, CAMEL_CONNECTOR_MAP_PROPERTIES_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_MAP_PROPERTIES_DOC)
        .define(CAMEL_CONNECTOR_MAP_HEADERS_CONF, Type.BOOLEAN, CAMEL_CONNECTOR_MAP_HEADERS_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_MAP_HEADERS_DOC);
    
    public CamelSourceConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(CONFIG_DEF, parsedConfig);
    }

    public static ConfigDef conf() {
        return CONFIG_DEF;
    }
}
