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
package org.apache.camel.kafkaconnector.sjms2;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelSjms2SinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_SJMS2_PATH_DESTINATION_TYPE_CONF = "camel.sink.path.destinationType";
    public static final String CAMEL_SINK_SJMS2_PATH_DESTINATION_TYPE_DOC = "The kind of destination to use One of: [queue] [topic]";
    public static final String CAMEL_SINK_SJMS2_PATH_DESTINATION_TYPE_DEFAULT = "queue";
    public static final String CAMEL_SINK_SJMS2_PATH_DESTINATION_NAME_CONF = "camel.sink.path.destinationName";
    public static final String CAMEL_SINK_SJMS2_PATH_DESTINATION_NAME_DOC = "DestinationName is a JMS queue or topic name. By default, the destinationName is interpreted as a queue name.";
    public static final String CAMEL_SINK_SJMS2_PATH_DESTINATION_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_ACKNOWLEDGEMENT_MODE_CONF = "camel.sink.endpoint.acknowledgementMode";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_ACKNOWLEDGEMENT_MODE_DOC = "The JMS acknowledgement name, which is one of: SESSION_TRANSACTED, CLIENT_ACKNOWLEDGE, AUTO_ACKNOWLEDGE, DUPS_OK_ACKNOWLEDGE One of: [SESSION_TRANSACTED] [CLIENT_ACKNOWLEDGE] [AUTO_ACKNOWLEDGE] [DUPS_OK_ACKNOWLEDGE]";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_ACKNOWLEDGEMENT_MODE_DEFAULT = "AUTO_ACKNOWLEDGE";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_CONNECTION_FACTORY_CONF = "camel.sink.endpoint.connectionFactory";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_CONNECTION_FACTORY_DOC = "The connection factory to be use. A connection factory must be configured either on the component or endpoint.";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_CONNECTION_FACTORY_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_REPLY_TO_CONF = "camel.sink.endpoint.disableReplyTo";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_REPLY_TO_DOC = "Specifies whether Camel ignores the JMSReplyTo header in messages. If true, Camel does not send a reply back to the destination specified in the JMSReplyTo header. You can use this option if you want Camel to consume from a route and you do not want Camel to automatically send back a reply message because another component in your code handles the reply message. You can also use this option if you want to use Camel as a proxy between different message brokers and you want to route message from one system to another.";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_REPLY_TO_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_CONF = "camel.sink.endpoint.replyTo";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_DOC = "Provides an explicit ReplyTo destination (overrides any incoming value of Message.getJMSReplyTo() in consumer).";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_TEST_CONNECTION_ON_STARTUP_CONF = "camel.sink.endpoint.testConnectionOnStartup";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_TEST_CONNECTION_ON_STARTUP_DOC = "Specifies whether to test the connection on startup. This ensures that when Camel starts that all the JMS consumers have a valid connection to the JMS broker. If a connection cannot be granted then Camel throws an exception on startup. This ensures that Camel is not started with failed connections. The JMS producers is tested as well.";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_TEST_CONNECTION_ON_STARTUP_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_MODE_CONF = "camel.sink.endpoint.deliveryMode";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_MODE_DOC = "Specifies the delivery mode to be used. Possible values are those defined by javax.jms.DeliveryMode. NON_PERSISTENT = 1 and PERSISTENT = 2. One of: [1] [2]";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_MODE_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_PERSISTENT_CONF = "camel.sink.endpoint.deliveryPersistent";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_PERSISTENT_DOC = "Specifies whether persistent delivery is used by default.";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_PERSISTENT_DEFAULT = true;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_PRIORITY_CONF = "camel.sink.endpoint.priority";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_PRIORITY_DOC = "Values greater than 1 specify the message priority when sending (where 1 is the lowest priority and 9 is the highest). The explicitQosEnabled option must also be enabled in order for this option to have any effect. One of: [1] [2] [3] [4] [5] [6] [7] [8] [9]";
    public static final Integer CAMEL_SINK_SJMS2_ENDPOINT_PRIORITY_DEFAULT = 4;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_CONCURRENT_CONSUMERS_CONF = "camel.sink.endpoint.replyToConcurrentConsumers";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_CONCURRENT_CONSUMERS_DOC = "Specifies the default number of concurrent consumers when doing request/reply over JMS. See also the maxMessagesPerTask option to control dynamic scaling up/down of threads.";
    public static final Integer CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_CONCURRENT_CONSUMERS_DEFAULT = 1;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_OVERRIDE_CONF = "camel.sink.endpoint.replyToOverride";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_OVERRIDE_DOC = "Provides an explicit ReplyTo destination in the JMS message, which overrides the setting of replyTo. It is useful if you want to forward the message to a remote Queue and receive the reply message from the ReplyTo destination.";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_OVERRIDE_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_TYPE_CONF = "camel.sink.endpoint.replyToType";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_TYPE_DOC = "Allows for explicitly specifying which kind of strategy to use for replyTo queues when doing request/reply over JMS. Possible values are: Temporary or Exclusive. By default Camel will use temporary queues. However if replyTo has been configured, then Exclusive is used. One of: [Temporary] [Exclusive]";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_TYPE_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REQUEST_TIMEOUT_CONF = "camel.sink.endpoint.requestTimeout";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_REQUEST_TIMEOUT_DOC = "The timeout for waiting for a reply when using the InOut Exchange Pattern (in milliseconds). The default is 20 seconds. You can include the header CamelJmsRequestTimeout to override this endpoint configured timeout value, and thus have per message individual timeout values. See also the requestTimeoutCheckerInterval option.";
    public static final Long CAMEL_SINK_SJMS2_ENDPOINT_REQUEST_TIMEOUT_DEFAULT = 20000L;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_TIME_TO_LIVE_CONF = "camel.sink.endpoint.timeToLive";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_TIME_TO_LIVE_DOC = "When sending messages, specifies the time-to-live of the message (in milliseconds).";
    public static final Long CAMEL_SINK_SJMS2_ENDPOINT_TIME_TO_LIVE_DEFAULT = -1L;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_ALLOW_NULL_BODY_CONF = "camel.sink.endpoint.allowNullBody";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_ALLOW_NULL_BODY_DOC = "Whether to allow sending messages with no body. If this option is false and the message body is null, then an JMSException is thrown.";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_ALLOW_NULL_BODY_DEFAULT = true;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_TIME_TO_LIVE_CONF = "camel.sink.endpoint.disableTimeToLive";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_TIME_TO_LIVE_DOC = "Use this option to force disabling time to live. For example when you do request/reply over JMS, then Camel will by default use the requestTimeout value as time to live on the message being sent. The problem is that the sender and receiver systems have to have their clocks synchronized, so they are in sync. This is not always so easy to archive. So you can use disableTimeToLive=true to not set a time to live value on the sent message. Then the message will not expire on the receiver system. See below in section About time to live for more details.";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_TIME_TO_LIVE_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_EXPLICIT_QOS_ENABLED_CONF = "camel.sink.endpoint.explicitQosEnabled";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_EXPLICIT_QOS_ENABLED_DOC = "Set if the deliveryMode, priority or timeToLive qualities of service should be used when sending messages. This option is based on Spring's JmsTemplate. The deliveryMode, priority and timeToLive options are applied to the current endpoint. This contrasts with the preserveMessageQos option, which operates at message granularity, reading QoS properties exclusively from the Camel In message headers.";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_EXPLICIT_QOS_ENABLED_DEFAULT = "false";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_PRESERVE_MESSAGE_QOS_CONF = "camel.sink.endpoint.preserveMessageQos";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_PRESERVE_MESSAGE_QOS_DOC = "Set to true, if you want to send message using the QoS settings specified on the message, instead of the QoS settings on the JMS endpoint. The following three headers are considered JMSPriority, JMSDeliveryMode, and JMSExpiration. You can provide all or only some of them. If not provided, Camel will fall back to use the values from the endpoint instead. So, when using this option, the headers override the values from the endpoint. The explicitQosEnabled option, by contrast, will only use options set on the endpoint, and not values from the message header.";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_PRESERVE_MESSAGE_QOS_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_START_LISTENER_CONF = "camel.sink.endpoint.asyncStartListener";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_START_LISTENER_DOC = "Whether to startup the consumer message listener asynchronously, when starting a route. For example if a JmsConsumer cannot get a connection to a remote JMS broker, then it may block while retrying and/or failover. This will cause Camel to block while starting routes. By setting this option to true, you will let routes startup, while the JmsConsumer connects to the JMS broker using a dedicated thread in asynchronous mode. If this option is used, then beware that if the connection could not be established, then an exception is logged at WARN level, and the consumer will not be able to receive messages; You can then restart the route to retry.";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_START_LISTENER_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_STOP_LISTENER_CONF = "camel.sink.endpoint.asyncStopListener";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_STOP_LISTENER_DOC = "Whether to stop the consumer message listener asynchronously, when stopping a route.";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_STOP_LISTENER_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DESTINATION_CREATION_STRATEGY_CONF = "camel.sink.endpoint.destinationCreationStrategy";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DESTINATION_CREATION_STRATEGY_DOC = "To use a custom DestinationCreationStrategy.";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_DESTINATION_CREATION_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_EXCEPTION_LISTENER_CONF = "camel.sink.endpoint.exceptionListener";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_EXCEPTION_LISTENER_DOC = "Specifies the JMS Exception Listener that is to be notified of any underlying JMS exceptions.";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_EXCEPTION_LISTENER_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_HEADER_FILTER_STRATEGY_CONF = "camel.sink.endpoint.headerFilterStrategy";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_HEADER_FILTER_STRATEGY_DOC = "To use a custom HeaderFilterStrategy to filter header to and from Camel message.";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_HEADER_FILTER_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_INCLUDE_ALL_JMSXPROPERTIES_CONF = "camel.sink.endpoint.includeAllJMSXProperties";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_INCLUDE_ALL_JMSXPROPERTIES_DOC = "Whether to include all JMSXxxx properties when mapping from JMS to Camel Message. Setting this to true will include properties such as JMSXAppID, and JMSXUserID etc. Note: If you are using a custom headerFilterStrategy then this option does not apply.";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_INCLUDE_ALL_JMSXPROPERTIES_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_JMS_KEY_FORMAT_STRATEGY_CONF = "camel.sink.endpoint.jmsKeyFormatStrategy";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_JMS_KEY_FORMAT_STRATEGY_DOC = "Pluggable strategy for encoding and decoding JMS keys so they can be compliant with the JMS specification. Camel provides two implementations out of the box: default and passthrough. The default strategy will safely marshal dots and hyphens (. and -). The passthrough strategy leaves the key as is. Can be used for JMS brokers which do not care whether JMS header keys contain illegal characters. You can provide your own implementation of the org.apache.camel.component.jms.JmsKeyFormatStrategy and refer to it using the # notation.";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_JMS_KEY_FORMAT_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_MAP_JMS_MESSAGE_CONF = "camel.sink.endpoint.mapJmsMessage";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_MAP_JMS_MESSAGE_DOC = "Specifies whether Camel should auto map the received JMS message to a suited payload type, such as javax.jms.TextMessage to a String etc. See section about how mapping works below for more details.";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_MAP_JMS_MESSAGE_DEFAULT = true;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_MESSAGE_CREATED_STRATEGY_CONF = "camel.sink.endpoint.messageCreatedStrategy";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_MESSAGE_CREATED_STRATEGY_DOC = "To use the given MessageCreatedStrategy which are invoked when Camel creates new instances of javax.jms.Message objects when Camel is sending a JMS message.";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_MESSAGE_CREATED_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_RECOVERY_INTERVAL_CONF = "camel.sink.endpoint.recoveryInterval";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_RECOVERY_INTERVAL_DOC = "Specifies the interval between recovery attempts, i.e. when a connection is being refreshed, in milliseconds. The default is 5000 ms, that is, 5 seconds.";
    public static final Long CAMEL_SINK_SJMS2_ENDPOINT_RECOVERY_INTERVAL_DEFAULT = 5000L;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_TRANSFER_EXCEPTION_CONF = "camel.sink.endpoint.transferException";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_TRANSFER_EXCEPTION_DOC = "If enabled and you are using Request Reply messaging (InOut) and an Exchange failed on the consumer side, then the caused Exception will be send back in response as a javax.jms.ObjectMessage. If the client is Camel, the returned Exception is rethrown. This allows you to use Camel JMS as a bridge in your routing - for example, using persistent queues to enable robust routing. Notice that if you also have transferExchange enabled, this option takes precedence. The caught exception is required to be serializable. The original Exception on the consumer side can be wrapped in an outer exception such as org.apache.camel.RuntimeCamelException when returned to the producer. Use this with caution as the data is using Java Object serialization and requires the received to be able to deserialize the data at Class level, which forces a strong coupling between the producers and consumer!";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_TRANSFER_EXCEPTION_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_TRANSACTED_CONF = "camel.sink.endpoint.transacted";
    public static final String CAMEL_SINK_SJMS2_ENDPOINT_TRANSACTED_DOC = "Specifies whether to use transacted mode";
    public static final Boolean CAMEL_SINK_SJMS2_ENDPOINT_TRANSACTED_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_COMPONENT_CONNECTION_FACTORY_CONF = "camel.component.sjms2.connectionFactory";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_CONNECTION_FACTORY_DOC = "The connection factory to be use. A connection factory must be configured either on the component or endpoint.";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_CONNECTION_FACTORY_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.sjms2.lazyStartProducer";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_SJMS2_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SJMS2_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.sjms2.autowiredEnabled";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_SJMS2_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SINK_SJMS2_COMPONENT_DESTINATION_CREATION_STRATEGY_CONF = "camel.component.sjms2.destinationCreationStrategy";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_DESTINATION_CREATION_STRATEGY_DOC = "To use a custom DestinationCreationStrategy.";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_DESTINATION_CREATION_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_COMPONENT_JMS_KEY_FORMAT_STRATEGY_CONF = "camel.component.sjms2.jmsKeyFormatStrategy";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_JMS_KEY_FORMAT_STRATEGY_DOC = "Pluggable strategy for encoding and decoding JMS keys so they can be compliant with the JMS specification. Camel provides one implementation out of the box: default. The default strategy will safely marshal dots and hyphens (. and -). Can be used for JMS brokers which do not care whether JMS header keys contain illegal characters. You can provide your own implementation of the org.apache.camel.component.jms.JmsKeyFormatStrategy and refer to it using the # notation.";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_JMS_KEY_FORMAT_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_COMPONENT_MESSAGE_CREATED_STRATEGY_CONF = "camel.component.sjms2.messageCreatedStrategy";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_MESSAGE_CREATED_STRATEGY_DOC = "To use the given MessageCreatedStrategy which are invoked when Camel creates new instances of javax.jms.Message objects when Camel is sending a JMS message.";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_MESSAGE_CREATED_STRATEGY_DEFAULT = null;
    public static final String CAMEL_SINK_SJMS2_COMPONENT_RECOVERY_INTERVAL_CONF = "camel.component.sjms2.recoveryInterval";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_RECOVERY_INTERVAL_DOC = "Specifies the interval between recovery attempts, i.e. when a connection is being refreshed, in milliseconds. The default is 5000 ms, that is, 5 seconds.";
    public static final Long CAMEL_SINK_SJMS2_COMPONENT_RECOVERY_INTERVAL_DEFAULT = 5000L;
    public static final String CAMEL_SINK_SJMS2_COMPONENT_REPLY_TO_ON_TIMEOUT_MAX_CONCURRENT_CONSUMERS_CONF = "camel.component.sjms2.replyToOnTimeoutMaxConcurrentConsumers";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_REPLY_TO_ON_TIMEOUT_MAX_CONCURRENT_CONSUMERS_DOC = "Specifies the maximum number of concurrent consumers for continue routing when timeout occurred when using request/reply over JMS.";
    public static final Integer CAMEL_SINK_SJMS2_COMPONENT_REPLY_TO_ON_TIMEOUT_MAX_CONCURRENT_CONSUMERS_DEFAULT = 1;
    public static final String CAMEL_SINK_SJMS2_COMPONENT_REQUEST_TIMEOUT_CHECKER_INTERVAL_CONF = "camel.component.sjms2.requestTimeoutCheckerInterval";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_REQUEST_TIMEOUT_CHECKER_INTERVAL_DOC = "Configures how often Camel should check for timed out Exchanges when doing request/reply over JMS. By default Camel checks once per second. But if you must react faster when a timeout occurs, then you can lower this interval, to check more frequently. The timeout is determined by the option requestTimeout.";
    public static final Long CAMEL_SINK_SJMS2_COMPONENT_REQUEST_TIMEOUT_CHECKER_INTERVAL_DEFAULT = 1000L;
    public static final String CAMEL_SINK_SJMS2_COMPONENT_HEADER_FILTER_STRATEGY_CONF = "camel.component.sjms2.headerFilterStrategy";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_HEADER_FILTER_STRATEGY_DOC = "To use a custom org.apache.camel.spi.HeaderFilterStrategy to filter header to and from Camel message.";
    public static final String CAMEL_SINK_SJMS2_COMPONENT_HEADER_FILTER_STRATEGY_DEFAULT = null;

    public CamelSjms2SinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSjms2SinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_SJMS2_PATH_DESTINATION_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_PATH_DESTINATION_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_PATH_DESTINATION_TYPE_DOC);
        conf.define(CAMEL_SINK_SJMS2_PATH_DESTINATION_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_PATH_DESTINATION_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_SJMS2_PATH_DESTINATION_NAME_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_ACKNOWLEDGEMENT_MODE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_ACKNOWLEDGEMENT_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_ACKNOWLEDGEMENT_MODE_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_CONNECTION_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_CONNECTION_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_CONNECTION_FACTORY_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_REPLY_TO_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_REPLY_TO_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_REPLY_TO_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_TEST_CONNECTION_ON_STARTUP_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_TEST_CONNECTION_ON_STARTUP_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_TEST_CONNECTION_ON_STARTUP_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_MODE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_MODE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_MODE_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_PERSISTENT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_PERSISTENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_DELIVERY_PERSISTENT_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_PRIORITY_CONF, ConfigDef.Type.INT, CAMEL_SINK_SJMS2_ENDPOINT_PRIORITY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_PRIORITY_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_CONCURRENT_CONSUMERS_CONF, ConfigDef.Type.INT, CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_CONCURRENT_CONSUMERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_CONCURRENT_CONSUMERS_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_OVERRIDE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_OVERRIDE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_OVERRIDE_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_REPLY_TO_TYPE_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_REQUEST_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_SJMS2_ENDPOINT_REQUEST_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_REQUEST_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_TIME_TO_LIVE_CONF, ConfigDef.Type.LONG, CAMEL_SINK_SJMS2_ENDPOINT_TIME_TO_LIVE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_TIME_TO_LIVE_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_ALLOW_NULL_BODY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_ALLOW_NULL_BODY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_ALLOW_NULL_BODY_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_TIME_TO_LIVE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_TIME_TO_LIVE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_DISABLE_TIME_TO_LIVE_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_EXPLICIT_QOS_ENABLED_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_EXPLICIT_QOS_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_EXPLICIT_QOS_ENABLED_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_PRESERVE_MESSAGE_QOS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_PRESERVE_MESSAGE_QOS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_PRESERVE_MESSAGE_QOS_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_START_LISTENER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_START_LISTENER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_START_LISTENER_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_STOP_LISTENER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_STOP_LISTENER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_ASYNC_STOP_LISTENER_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_DESTINATION_CREATION_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_DESTINATION_CREATION_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_DESTINATION_CREATION_STRATEGY_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_EXCEPTION_LISTENER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_EXCEPTION_LISTENER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_EXCEPTION_LISTENER_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_HEADER_FILTER_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_HEADER_FILTER_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_HEADER_FILTER_STRATEGY_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_INCLUDE_ALL_JMSXPROPERTIES_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_INCLUDE_ALL_JMSXPROPERTIES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_INCLUDE_ALL_JMSXPROPERTIES_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_JMS_KEY_FORMAT_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_JMS_KEY_FORMAT_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_JMS_KEY_FORMAT_STRATEGY_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_MAP_JMS_MESSAGE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_MAP_JMS_MESSAGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_MAP_JMS_MESSAGE_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_MESSAGE_CREATED_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_ENDPOINT_MESSAGE_CREATED_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_MESSAGE_CREATED_STRATEGY_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_RECOVERY_INTERVAL_CONF, ConfigDef.Type.LONG, CAMEL_SINK_SJMS2_ENDPOINT_RECOVERY_INTERVAL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_RECOVERY_INTERVAL_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_TRANSFER_EXCEPTION_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_TRANSFER_EXCEPTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_TRANSFER_EXCEPTION_DOC);
        conf.define(CAMEL_SINK_SJMS2_ENDPOINT_TRANSACTED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_ENDPOINT_TRANSACTED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_ENDPOINT_TRANSACTED_DOC);
        conf.define(CAMEL_SINK_SJMS2_COMPONENT_CONNECTION_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_COMPONENT_CONNECTION_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_COMPONENT_CONNECTION_FACTORY_DOC);
        conf.define(CAMEL_SINK_SJMS2_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SJMS2_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SJMS2_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SINK_SJMS2_COMPONENT_DESTINATION_CREATION_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_COMPONENT_DESTINATION_CREATION_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_COMPONENT_DESTINATION_CREATION_STRATEGY_DOC);
        conf.define(CAMEL_SINK_SJMS2_COMPONENT_JMS_KEY_FORMAT_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_COMPONENT_JMS_KEY_FORMAT_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_COMPONENT_JMS_KEY_FORMAT_STRATEGY_DOC);
        conf.define(CAMEL_SINK_SJMS2_COMPONENT_MESSAGE_CREATED_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_COMPONENT_MESSAGE_CREATED_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_COMPONENT_MESSAGE_CREATED_STRATEGY_DOC);
        conf.define(CAMEL_SINK_SJMS2_COMPONENT_RECOVERY_INTERVAL_CONF, ConfigDef.Type.LONG, CAMEL_SINK_SJMS2_COMPONENT_RECOVERY_INTERVAL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_COMPONENT_RECOVERY_INTERVAL_DOC);
        conf.define(CAMEL_SINK_SJMS2_COMPONENT_REPLY_TO_ON_TIMEOUT_MAX_CONCURRENT_CONSUMERS_CONF, ConfigDef.Type.INT, CAMEL_SINK_SJMS2_COMPONENT_REPLY_TO_ON_TIMEOUT_MAX_CONCURRENT_CONSUMERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_COMPONENT_REPLY_TO_ON_TIMEOUT_MAX_CONCURRENT_CONSUMERS_DOC);
        conf.define(CAMEL_SINK_SJMS2_COMPONENT_REQUEST_TIMEOUT_CHECKER_INTERVAL_CONF, ConfigDef.Type.LONG, CAMEL_SINK_SJMS2_COMPONENT_REQUEST_TIMEOUT_CHECKER_INTERVAL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_COMPONENT_REQUEST_TIMEOUT_CHECKER_INTERVAL_DOC);
        conf.define(CAMEL_SINK_SJMS2_COMPONENT_HEADER_FILTER_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SJMS2_COMPONENT_HEADER_FILTER_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SJMS2_COMPONENT_HEADER_FILTER_STRATEGY_DOC);
        return conf;
    }
}