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

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

public class CamelSourceConnectorConfig extends AbstractConfig {
    public static final String CAMEL_SOURCE_URL_DEFAULT = null;
    public static final String CAMEL_SOURCE_URL_CONF = "camel.source.url";
    public static final String CAMEL_SOURCE_URL_DOC = "The camel url to configure the source";

    public static final String CAMEL_SOURCE_UNMARSHAL_DEFAULT = null;
    public static final String CAMEL_SOURCE_UNMARSHAL_CONF = "camel.source.unmarshal";
    public static final String CAMEL_SOURCE_UNMARSHAL_DOC = "The camel dataformat name to use to unmarshal data from the source";

    public static final String TOPIC_DEFAULT = "test";
    public static final String TOPIC_CONF = "camel.source.kafka.topic";
    public static final String TOPIC_DOC = "The topic to publish data to";

    public static final Long CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_DEFAULT = 1000L;
    public static final String CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_CONF = "camel.source.maxBatchPollSize";
    public static final String CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_DOC = "The max number of messages retrieved in a single poll()";

    public static final Long CAMEL_SOURCE_MAX_POLL_DURATION_DEFAULT = 1000L;
    public static final String CAMEL_SOURCE_MAX_POLL_DURATION_CONF = "camel.source.maxPollDuration";
    public static final String CAMEL_SOURCE_MAX_POLL_DURATION_DOC = "The maximum time in milliseconds spent in a single call to poll()";

    public static final Long CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_DEFAULT = 1000L;
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_CONF = "camel.source.pollingConsumerQueueSize";
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_DOC = "The queue size for the internal hand-off queue between the polling consumer, and producers sending data into the queue.";

    public static final Long CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_DEFAULT = 0L;
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_CONF = "camel.source.pollingConsumerBlockTimeout";
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_DOC = "To use a timeout (in milliseconds) when the producer is blocked if the internal queue is full. If the value is 0 or negative then no timeout is in use.";

    public static final Boolean CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_DEFAULT = true;
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_CONF = "camel.source.pollingConsumerBlockWhenFull";
    public static final String CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_DOC = " Whether to block any producer if the internal queue is full.";

    public static final String CAMEL_SOURCE_MESSAGE_HEADER_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_MESSAGE_HEADER_KEY_CONF = "camel.source.camelMessageHeaderKey";
    public static final String CAMEL_SOURCE_MESSAGE_HEADER_KEY_DOC = "The name of a camel message header containing an unique key that can be used as a Kafka message key."
          +  " If this is not specified, then the Kafka message will not have a key.";

    public CamelSourceConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        return new ConfigDef()
                .define(CAMEL_SOURCE_URL_CONF, Type.STRING, CAMEL_SOURCE_URL_DEFAULT, Importance.HIGH, CAMEL_SOURCE_URL_DOC)
                .define(CAMEL_SOURCE_UNMARSHAL_CONF, Type.STRING, CAMEL_SOURCE_UNMARSHAL_DEFAULT, Importance.HIGH, CAMEL_SOURCE_UNMARSHAL_DOC)
                .define(TOPIC_CONF, ConfigDef.Type.STRING, TOPIC_DEFAULT, ConfigDef.Importance.HIGH, TOPIC_DOC)
                .define(CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_CONF, Type.LONG, CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_DOC)
                .define(CAMEL_SOURCE_MAX_POLL_DURATION_CONF, Type.LONG, CAMEL_SOURCE_MAX_POLL_DURATION_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_MAX_POLL_DURATION_DOC)
                .define(CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_CONF, Type.LONG, CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_DOC)
                .define(CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_CONF, Type.LONG, CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_DOC)
                .define(CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_CONF, Type.BOOLEAN, CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_DOC)
                .define(CAMEL_SOURCE_MESSAGE_HEADER_KEY_CONF, Type.STRING, CAMEL_SOURCE_MESSAGE_HEADER_KEY_DEFAULT, Importance.MEDIUM, CAMEL_SOURCE_MESSAGE_HEADER_KEY_DOC);
    }
}
