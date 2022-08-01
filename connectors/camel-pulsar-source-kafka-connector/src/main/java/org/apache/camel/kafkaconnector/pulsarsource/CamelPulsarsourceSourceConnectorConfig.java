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
 */package org.apache.camel.kafkaconnector.pulsarsource;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelPulsarsourceSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_CONF = "camel.kamelet.pulsar-source.topic";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_DOC = "The topic name or regexp";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_TENANT_CONF = "camel.kamelet.pulsar-source.tenant";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_TENANT_DOC = "The Tenant Name";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_TENANT_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_TYPE_CONF = "camel.kamelet.pulsar-source.topicType";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_TYPE_DOC = "The topic type. Possible values are: persistent or non-persistent";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_TYPE_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_NAMESPACE_NAME_CONF = "camel.kamelet.pulsar-source.namespaceName";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_NAMESPACE_NAME_DOC = "The Pulsar Namespace Name";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_NAMESPACE_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SERVICE_URL_CONF = "camel.kamelet.pulsar-source.serviceUrl";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SERVICE_URL_DOC = "The Pulsar Service URL to point while creating the client from URI.";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SERVICE_URL_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_CLASS_CONF = "camel.kamelet.pulsar-source.authenticationClass";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_CLASS_DOC = "The Authentication FQCN to be used while creating the client from URI.";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_CLASS_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_PARAMS_CONF = "camel.kamelet.pulsar-source.authenticationParams";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_PARAMS_DOC = "The Authentication Parameters to be used while creating the client from URI.";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_PARAMS_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_NAME_PREFIX_CONF = "camel.kamelet.pulsar-source.consumerNamePrefix";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_NAME_PREFIX_DOC = "Prefix to add to consumer names when a SHARED or FAILOVER subscription is used";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_NAME_PREFIX_DEFAULT = "cons";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_QUEUE_SIZE_CONF = "camel.kamelet.pulsar-source.consumerQueueSize";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_QUEUE_SIZE_DOC = "Size of the consumer queue";
    public static final Integer CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_QUEUE_SIZE_DEFAULT = 10;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_DEAD_LETTER_TOPIC_CONF = "camel.kamelet.pulsar-source.deadLetterTopic";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_DEAD_LETTER_TOPIC_DOC = "Name of the topic where the messages which fail maxRedeliverCount times will be sent. Note: if not set, default topic name will be topicName-subscriptionName-DLQ.";
    public static final Integer CAMEL_SOURCE_PULSARSOURCE_KAMELET_DEAD_LETTER_TOPIC_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_MAX_REDELIVER_COUNT_CONF = "camel.kamelet.pulsar-source.maxRedeliverCount";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_MAX_REDELIVER_COUNT_DOC = "Maximum number of times that a message will be redelivered before being sent to the dead letter queue. If this value is not set, no Dead Letter Policy will be created.";
    public static final Integer CAMEL_SOURCE_PULSARSOURCE_KAMELET_MAX_REDELIVER_COUNT_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_CONF = "camel.kamelet.pulsar-source.negativeAckRedeliveryDelayMicros";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DOC = "Set the negative acknowledgement delay.";
    public static final Long CAMEL_SOURCE_PULSARSOURCE_KAMELET_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DEFAULT = 60000000L;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_MESSAGE_LISTENER_CONF = "camel.kamelet.pulsar-source.messageListener";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_MESSAGE_LISTENER_DOC = "Whether to use the messageListener interface, or to receive messages using a separate thread pool.";
    public static final Boolean CAMEL_SOURCE_PULSARSOURCE_KAMELET_MESSAGE_LISTENER_DEFAULT = true;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMERS_CONF = "camel.kamelet.pulsar-source.numberOfConsumers";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMERS_DOC = "Number of consumers.";
    public static final Integer CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMERS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMER_THREADS_CONF = "camel.kamelet.pulsar-source.numberOfConsumerThreads";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMER_THREADS_DOC = "Number of threads to receive and handle messages when using a separate thread pool.";
    public static final Integer CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMER_THREADS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_READ_COMPACTED_CONF = "camel.kamelet.pulsar-source.readCompacted";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_READ_COMPACTED_DOC = "Enable compacted topic reading.";
    public static final Boolean CAMEL_SOURCE_PULSARSOURCE_KAMELET_READ_COMPACTED_DEFAULT = false;
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_INITIAL_POSITION_CONF = "camel.kamelet.pulsar-source.subscriptionInitialPosition";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_INITIAL_POSITION_DOC = "Control the initial position in the topic of a newly created subscription. Default is latest message.Possible values: EARLIEST or LATEST";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_INITIAL_POSITION_DEFAULT = "LATEST";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_NAME_CONF = "camel.kamelet.pulsar-source.subscriptionName";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_NAME_DOC = "Name of the subscription to use.";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_NAME_DEFAULT = "subs";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TOPICS_MODE_CONF = "camel.kamelet.pulsar-source.subscriptionTopicsMode";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TOPICS_MODE_DOC = "Determines to which topics this consumer should be subscribed to - Persistent, Non-Persistent, or both. Only used with pattern subscriptions.Possible values: PersistentOnly, NonPersistentOnly, AllTopics";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TOPICS_MODE_DEFAULT = "PersistentOnly";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TYPE_CONF = "camel.kamelet.pulsar-source.subscriptionType";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TYPE_DOC = "Type of the subscription. Possible values: EXCLUSIVE, SHARED, FAILOVER, KEY_SHARED";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TYPE_DEFAULT = "EXCLUSIVE";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPICS_PATTERN_CONF = "camel.kamelet.pulsar-source.topicsPattern";
    public static final String CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPICS_PATTERN_DOC = "Whether the topic is a pattern (regular expression) that allows the consumer to subscribe to all matching topics in the namespace.";
    public static final Boolean CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPICS_PATTERN_DEFAULT = false;

    public CamelPulsarsourceSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelPulsarsourceSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_TENANT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_TENANT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_PULSARSOURCE_KAMELET_TENANT_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_TYPE_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPIC_TYPE_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_NAMESPACE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_NAMESPACE_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_PULSARSOURCE_KAMELET_NAMESPACE_NAME_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_SERVICE_URL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_SERVICE_URL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_PULSARSOURCE_KAMELET_SERVICE_URL_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_CLASS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_CLASS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_CLASS_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_PARAMS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_PARAMS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_AUTHENTICATION_PARAMS_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_NAME_PREFIX_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_NAME_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_NAME_PREFIX_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_QUEUE_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_QUEUE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_CONSUMER_QUEUE_SIZE_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_DEAD_LETTER_TOPIC_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_PULSARSOURCE_KAMELET_DEAD_LETTER_TOPIC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_DEAD_LETTER_TOPIC_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_MAX_REDELIVER_COUNT_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_PULSARSOURCE_KAMELET_MAX_REDELIVER_COUNT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_MAX_REDELIVER_COUNT_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_PULSARSOURCE_KAMELET_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_MESSAGE_LISTENER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSARSOURCE_KAMELET_MESSAGE_LISTENER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_MESSAGE_LISTENER_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMERS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMERS_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMER_THREADS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMER_THREADS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_NUMBER_OF_CONSUMER_THREADS_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_READ_COMPACTED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSARSOURCE_KAMELET_READ_COMPACTED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_READ_COMPACTED_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_INITIAL_POSITION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_INITIAL_POSITION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_INITIAL_POSITION_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_NAME_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TOPICS_MODE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TOPICS_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TOPICS_MODE_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_SUBSCRIPTION_TYPE_DOC);
        conf.define(CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPICS_PATTERN_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPICS_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSARSOURCE_KAMELET_TOPICS_PATTERN_DOC);
        return conf;
    }
}