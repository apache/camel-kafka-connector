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
package org.apache.camel.kafkaconnector.pulsar;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelPulsarSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_PULSAR_PATH_PERSISTENCE_CONF = "camel.source.path.persistence";
    public static final String CAMEL_SOURCE_PULSAR_PATH_PERSISTENCE_DOC = "Whether the topic is persistent or non-persistent One of: [persistent] [non-persistent]";
    public static final String CAMEL_SOURCE_PULSAR_PATH_PERSISTENCE_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_PATH_TENANT_CONF = "camel.source.path.tenant";
    public static final String CAMEL_SOURCE_PULSAR_PATH_TENANT_DOC = "The tenant";
    public static final String CAMEL_SOURCE_PULSAR_PATH_TENANT_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_PATH_NAMESPACE_CONF = "camel.source.path.namespace";
    public static final String CAMEL_SOURCE_PULSAR_PATH_NAMESPACE_DOC = "The namespace";
    public static final String CAMEL_SOURCE_PULSAR_PATH_NAMESPACE_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_PATH_TOPIC_CONF = "camel.source.path.topic";
    public static final String CAMEL_SOURCE_PULSAR_PATH_TOPIC_DOC = "The topic";
    public static final String CAMEL_SOURCE_PULSAR_PATH_TOPIC_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_CLASS_CONF = "camel.source.endpoint.authenticationClass";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_CLASS_DOC = "The Authentication FQCN to be used while creating the client from URI";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_CLASS_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_PARAMS_CONF = "camel.source.endpoint.authenticationParams";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_PARAMS_DOC = "The Authentication Parameters to be used while creating the client from URI";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_PARAMS_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SERVICE_URL_CONF = "camel.source.endpoint.serviceUrl";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SERVICE_URL_DOC = "The Pulsar Service URL to point while creating the client from URI";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SERVICE_URL_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_GROUP_TIME_MILLIS_CONF = "camel.source.endpoint.ackGroupTimeMillis";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_GROUP_TIME_MILLIS_DOC = "Group the consumer acknowledgments for the specified time in milliseconds - defaults to 100";
    public static final Long CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_GROUP_TIME_MILLIS_DEFAULT = 100L;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_TIMEOUT_MILLIS_CONF = "camel.source.endpoint.ackTimeoutMillis";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_TIMEOUT_MILLIS_DOC = "Timeout for unacknowledged messages in milliseconds - defaults to 10000";
    public static final Long CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_TIMEOUT_MILLIS_DEFAULT = 10000L;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_ALLOW_MANUAL_ACKNOWLEDGEMENT_CONF = "camel.source.endpoint.allowManualAcknowledgement";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_ALLOW_MANUAL_ACKNOWLEDGEMENT_DOC = "Whether to allow manual message acknowledgements. If this option is enabled, then messages are not acknowledged automatically after successful route completion. Instead, an instance of PulsarMessageReceipt is stored as a header on the org.apache.camel.Exchange. Messages can then be acknowledged using PulsarMessageReceipt at any time before the ackTimeout occurs.";
    public static final Boolean CAMEL_SOURCE_PULSAR_ENDPOINT_ALLOW_MANUAL_ACKNOWLEDGEMENT_DEFAULT = false;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_PULSAR_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_CONF = "camel.source.endpoint.consumerName";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_DOC = "Name of the consumer when subscription is EXCLUSIVE";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_DEFAULT = "sole-consumer";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_PREFIX_CONF = "camel.source.endpoint.consumerNamePrefix";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_PREFIX_DOC = "Prefix to add to consumer names when a SHARED or FAILOVER subscription is used";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_PREFIX_DEFAULT = "cons";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_QUEUE_SIZE_CONF = "camel.source.endpoint.consumerQueueSize";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_QUEUE_SIZE_DOC = "Size of the consumer queue - defaults to 10";
    public static final Integer CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_QUEUE_SIZE_DEFAULT = 10;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_DEAD_LETTER_TOPIC_CONF = "camel.source.endpoint.deadLetterTopic";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_DEAD_LETTER_TOPIC_DOC = "Name of the topic where the messages which fail maxRedeliverCount times will be sent. Note: if not set, default topic name will be topicName-subscriptionName-DLQ";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_DEAD_LETTER_TOPIC_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_MAX_REDELIVER_COUNT_CONF = "camel.source.endpoint.maxRedeliverCount";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_MAX_REDELIVER_COUNT_DOC = "Maximum number of times that a message will be redelivered before being sent to the dead letter queue. If this value is not set, no Dead Letter Policy will be created";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_MAX_REDELIVER_COUNT_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_MESSAGE_LISTENER_CONF = "camel.source.endpoint.messageListener";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_MESSAGE_LISTENER_DOC = "Whether to use the messageListener interface, or to receive messages using a separate thread pool";
    public static final Boolean CAMEL_SOURCE_PULSAR_ENDPOINT_MESSAGE_LISTENER_DEFAULT = true;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_CONF = "camel.source.endpoint.negativeAckRedeliveryDelayMicros";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DOC = "Set the negative acknowledgement delay";
    public static final Long CAMEL_SOURCE_PULSAR_ENDPOINT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DEFAULT = 60000000L;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMERS_CONF = "camel.source.endpoint.numberOfConsumers";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMERS_DOC = "Number of consumers - defaults to 1";
    public static final Integer CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMERS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMER_THREADS_CONF = "camel.source.endpoint.numberOfConsumerThreads";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMER_THREADS_DOC = "Number of threads to receive and handle messages when using a separate thread pool";
    public static final Integer CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMER_THREADS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_READ_COMPACTED_CONF = "camel.source.endpoint.readCompacted";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_READ_COMPACTED_DOC = "Enable compacted topic reading.";
    public static final Boolean CAMEL_SOURCE_PULSAR_ENDPOINT_READ_COMPACTED_DEFAULT = false;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_INITIAL_POSITION_CONF = "camel.source.endpoint.subscriptionInitialPosition";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_INITIAL_POSITION_DOC = "Control the initial position in the topic of a newly created subscription. Default is latest message. One of: [EARLIEST] [LATEST]";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_INITIAL_POSITION_DEFAULT = "LATEST";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_NAME_CONF = "camel.source.endpoint.subscriptionName";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_NAME_DOC = "Name of the subscription to use";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_NAME_DEFAULT = "subs";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TOPICS_MODE_CONF = "camel.source.endpoint.subscriptionTopicsMode";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TOPICS_MODE_DOC = "Determines to which topics this consumer should be subscribed to - Persistent, Non-Persistent, or both. Only used with pattern subscriptions. One of: [PersistentOnly] [NonPersistentOnly] [AllTopics]";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TOPICS_MODE_DEFAULT = "PersistentOnly";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TYPE_CONF = "camel.source.endpoint.subscriptionType";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TYPE_DOC = "Type of the subscription EXCLUSIVESHAREDFAILOVERKEY_SHARED, defaults to EXCLUSIVE One of: [EXCLUSIVE] [SHARED] [FAILOVER] [KEY_SHARED]";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TYPE_DEFAULT = "EXCLUSIVE";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_TOPICS_PATTERN_CONF = "camel.source.endpoint.topicsPattern";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_TOPICS_PATTERN_DOC = "Whether the topic is a pattern (regular expression) that allows the consumer to subscribe to all matching topics in the namespace";
    public static final Boolean CAMEL_SOURCE_PULSAR_ENDPOINT_TOPICS_PATTERN_DEFAULT = false;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    public static final String CAMEL_SOURCE_PULSAR_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_CLASS_CONF = "camel.component.pulsar.authenticationClass";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_CLASS_DOC = "The Authentication FQCN to be used while creating the client from URI";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_CLASS_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_PARAMS_CONF = "camel.component.pulsar.authenticationParams";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_PARAMS_DOC = "The Authentication Parameters to be used while creating the client from URI";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_PARAMS_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_CONFIGURATION_CONF = "camel.component.pulsar.configuration";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_CONFIGURATION_DOC = "Allows to pre-configure the Pulsar component with common options that the endpoints will reuse.";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SERVICE_URL_CONF = "camel.component.pulsar.serviceUrl";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SERVICE_URL_DOC = "The Pulsar Service URL to point while creating the client from URI";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SERVICE_URL_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_ACK_GROUP_TIME_MILLIS_CONF = "camel.component.pulsar.ackGroupTimeMillis";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_ACK_GROUP_TIME_MILLIS_DOC = "Group the consumer acknowledgments for the specified time in milliseconds - defaults to 100";
    public static final Long CAMEL_SOURCE_PULSAR_COMPONENT_ACK_GROUP_TIME_MILLIS_DEFAULT = 100L;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_ACK_TIMEOUT_MILLIS_CONF = "camel.component.pulsar.ackTimeoutMillis";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_ACK_TIMEOUT_MILLIS_DOC = "Timeout for unacknowledged messages in milliseconds - defaults to 10000";
    public static final Long CAMEL_SOURCE_PULSAR_COMPONENT_ACK_TIMEOUT_MILLIS_DEFAULT = 10000L;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_ALLOW_MANUAL_ACKNOWLEDGEMENT_CONF = "camel.component.pulsar.allowManualAcknowledgement";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_ALLOW_MANUAL_ACKNOWLEDGEMENT_DOC = "Whether to allow manual message acknowledgements. If this option is enabled, then messages are not acknowledged automatically after successful route completion. Instead, an instance of PulsarMessageReceipt is stored as a header on the org.apache.camel.Exchange. Messages can then be acknowledged using PulsarMessageReceipt at any time before the ackTimeout occurs.";
    public static final Boolean CAMEL_SOURCE_PULSAR_COMPONENT_ALLOW_MANUAL_ACKNOWLEDGEMENT_DEFAULT = false;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.pulsar.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_PULSAR_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_CONF = "camel.component.pulsar.consumerName";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_DOC = "Name of the consumer when subscription is EXCLUSIVE";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_DEFAULT = "sole-consumer";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_PREFIX_CONF = "camel.component.pulsar.consumerNamePrefix";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_PREFIX_DOC = "Prefix to add to consumer names when a SHARED or FAILOVER subscription is used";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_PREFIX_DEFAULT = "cons";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_QUEUE_SIZE_CONF = "camel.component.pulsar.consumerQueueSize";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_QUEUE_SIZE_DOC = "Size of the consumer queue - defaults to 10";
    public static final Integer CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_QUEUE_SIZE_DEFAULT = 10;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_DEAD_LETTER_TOPIC_CONF = "camel.component.pulsar.deadLetterTopic";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_DEAD_LETTER_TOPIC_DOC = "Name of the topic where the messages which fail maxRedeliverCount times will be sent. Note: if not set, default topic name will be topicName-subscriptionName-DLQ";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_DEAD_LETTER_TOPIC_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_MAX_REDELIVER_COUNT_CONF = "camel.component.pulsar.maxRedeliverCount";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_MAX_REDELIVER_COUNT_DOC = "Maximum number of times that a message will be redelivered before being sent to the dead letter queue. If this value is not set, no Dead Letter Policy will be created";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_MAX_REDELIVER_COUNT_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_MESSAGE_LISTENER_CONF = "camel.component.pulsar.messageListener";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_MESSAGE_LISTENER_DOC = "Whether to use the messageListener interface, or to receive messages using a separate thread pool";
    public static final Boolean CAMEL_SOURCE_PULSAR_COMPONENT_MESSAGE_LISTENER_DEFAULT = true;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_CONF = "camel.component.pulsar.negativeAckRedeliveryDelayMicros";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DOC = "Set the negative acknowledgement delay";
    public static final Long CAMEL_SOURCE_PULSAR_COMPONENT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DEFAULT = 60000000L;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMERS_CONF = "camel.component.pulsar.numberOfConsumers";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMERS_DOC = "Number of consumers - defaults to 1";
    public static final Integer CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMERS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMER_THREADS_CONF = "camel.component.pulsar.numberOfConsumerThreads";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMER_THREADS_DOC = "Number of threads to receive and handle messages when using a separate thread pool";
    public static final Integer CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMER_THREADS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_READ_COMPACTED_CONF = "camel.component.pulsar.readCompacted";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_READ_COMPACTED_DOC = "Enable compacted topic reading.";
    public static final Boolean CAMEL_SOURCE_PULSAR_COMPONENT_READ_COMPACTED_DEFAULT = false;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_INITIAL_POSITION_CONF = "camel.component.pulsar.subscriptionInitialPosition";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_INITIAL_POSITION_DOC = "Control the initial position in the topic of a newly created subscription. Default is latest message. One of: [EARLIEST] [LATEST]";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_INITIAL_POSITION_DEFAULT = "LATEST";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_NAME_CONF = "camel.component.pulsar.subscriptionName";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_NAME_DOC = "Name of the subscription to use";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_NAME_DEFAULT = "subs";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TOPICS_MODE_CONF = "camel.component.pulsar.subscriptionTopicsMode";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TOPICS_MODE_DOC = "Determines to which topics this consumer should be subscribed to - Persistent, Non-Persistent, or both. Only used with pattern subscriptions. One of: [PersistentOnly] [NonPersistentOnly] [AllTopics]";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TOPICS_MODE_DEFAULT = "PersistentOnly";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TYPE_CONF = "camel.component.pulsar.subscriptionType";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TYPE_DOC = "Type of the subscription EXCLUSIVESHAREDFAILOVERKEY_SHARED, defaults to EXCLUSIVE One of: [EXCLUSIVE] [SHARED] [FAILOVER] [KEY_SHARED]";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TYPE_DEFAULT = "EXCLUSIVE";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_TOPICS_PATTERN_CONF = "camel.component.pulsar.topicsPattern";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_TOPICS_PATTERN_DOC = "Whether the topic is a pattern (regular expression) that allows the consumer to subscribe to all matching topics in the namespace";
    public static final Boolean CAMEL_SOURCE_PULSAR_COMPONENT_TOPICS_PATTERN_DEFAULT = false;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_MESSAGE_RECEIPT_FACTORY_CONF = "camel.component.pulsar.pulsarMessageReceiptFactory";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_MESSAGE_RECEIPT_FACTORY_DOC = "Provide a factory to create an alternate implementation of PulsarMessageReceipt.";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_MESSAGE_RECEIPT_FACTORY_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_AUTO_CONFIGURATION_CONF = "camel.component.pulsar.autoConfiguration";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_AUTO_CONFIGURATION_DOC = "The pulsar auto configuration";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_AUTO_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.pulsar.autowiredEnabled";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SOURCE_PULSAR_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_CLIENT_CONF = "camel.component.pulsar.pulsarClient";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_CLIENT_DOC = "The pulsar client";
    public static final String CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_CLIENT_DEFAULT = null;

    public CamelPulsarSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelPulsarSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_PULSAR_PATH_PERSISTENCE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_PATH_PERSISTENCE_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_PULSAR_PATH_PERSISTENCE_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_PATH_TENANT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_PATH_TENANT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_PULSAR_PATH_TENANT_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_PATH_NAMESPACE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_PATH_NAMESPACE_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_PULSAR_PATH_NAMESPACE_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_PATH_TOPIC_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_PATH_TOPIC_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_PULSAR_PATH_TOPIC_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_CLASS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_CLASS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_CLASS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_PARAMS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_PARAMS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_AUTHENTICATION_PARAMS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_SERVICE_URL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_SERVICE_URL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_SERVICE_URL_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_GROUP_TIME_MILLIS_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_GROUP_TIME_MILLIS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_GROUP_TIME_MILLIS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_TIMEOUT_MILLIS_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_TIMEOUT_MILLIS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_ACK_TIMEOUT_MILLIS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_ALLOW_MANUAL_ACKNOWLEDGEMENT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSAR_ENDPOINT_ALLOW_MANUAL_ACKNOWLEDGEMENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_ALLOW_MANUAL_ACKNOWLEDGEMENT_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSAR_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_PREFIX_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_NAME_PREFIX_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_QUEUE_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_QUEUE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_CONSUMER_QUEUE_SIZE_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_DEAD_LETTER_TOPIC_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_DEAD_LETTER_TOPIC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_DEAD_LETTER_TOPIC_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_MAX_REDELIVER_COUNT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_MAX_REDELIVER_COUNT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_MAX_REDELIVER_COUNT_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_MESSAGE_LISTENER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSAR_ENDPOINT_MESSAGE_LISTENER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_MESSAGE_LISTENER_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_PULSAR_ENDPOINT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMERS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMERS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMER_THREADS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMER_THREADS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_NUMBER_OF_CONSUMER_THREADS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_READ_COMPACTED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSAR_ENDPOINT_READ_COMPACTED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_READ_COMPACTED_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_INITIAL_POSITION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_INITIAL_POSITION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_INITIAL_POSITION_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_NAME_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TOPICS_MODE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TOPICS_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TOPICS_MODE_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_SUBSCRIPTION_TYPE_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_TOPICS_PATTERN_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSAR_ENDPOINT_TOPICS_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_TOPICS_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_CLASS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_CLASS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_CLASS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_PARAMS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_PARAMS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_AUTHENTICATION_PARAMS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_SERVICE_URL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_SERVICE_URL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_SERVICE_URL_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_ACK_GROUP_TIME_MILLIS_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_PULSAR_COMPONENT_ACK_GROUP_TIME_MILLIS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_ACK_GROUP_TIME_MILLIS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_ACK_TIMEOUT_MILLIS_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_PULSAR_COMPONENT_ACK_TIMEOUT_MILLIS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_ACK_TIMEOUT_MILLIS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_ALLOW_MANUAL_ACKNOWLEDGEMENT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSAR_COMPONENT_ALLOW_MANUAL_ACKNOWLEDGEMENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_ALLOW_MANUAL_ACKNOWLEDGEMENT_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSAR_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_PREFIX_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_NAME_PREFIX_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_QUEUE_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_QUEUE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_CONSUMER_QUEUE_SIZE_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_DEAD_LETTER_TOPIC_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_DEAD_LETTER_TOPIC_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_DEAD_LETTER_TOPIC_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_MAX_REDELIVER_COUNT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_MAX_REDELIVER_COUNT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_MAX_REDELIVER_COUNT_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_MESSAGE_LISTENER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSAR_COMPONENT_MESSAGE_LISTENER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_MESSAGE_LISTENER_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_PULSAR_COMPONENT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_NEGATIVE_ACK_REDELIVERY_DELAY_MICROS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMERS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMERS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMER_THREADS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMER_THREADS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_NUMBER_OF_CONSUMER_THREADS_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_READ_COMPACTED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSAR_COMPONENT_READ_COMPACTED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_READ_COMPACTED_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_INITIAL_POSITION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_INITIAL_POSITION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_INITIAL_POSITION_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_NAME_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TOPICS_MODE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TOPICS_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TOPICS_MODE_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_SUBSCRIPTION_TYPE_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_TOPICS_PATTERN_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSAR_COMPONENT_TOPICS_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_TOPICS_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_MESSAGE_RECEIPT_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_MESSAGE_RECEIPT_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_MESSAGE_RECEIPT_FACTORY_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_AUTO_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_AUTO_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_AUTO_CONFIGURATION_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_PULSAR_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_PULSAR_COMPONENT_PULSAR_CLIENT_DOC);
        return conf;
    }
}