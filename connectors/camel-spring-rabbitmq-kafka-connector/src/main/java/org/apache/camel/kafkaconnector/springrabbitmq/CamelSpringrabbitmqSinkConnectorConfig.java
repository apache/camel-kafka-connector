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
package org.apache.camel.kafkaconnector.springrabbitmq;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelSpringrabbitmqSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_SPRINGRABBITMQ_PATH_EXCHANGE_NAME_CONF = "camel.sink.path.exchangeName";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_PATH_EXCHANGE_NAME_DOC = "The exchange name determines the exchange to which the produced messages will be sent to. In the case of consumers, the exchange name determines the exchange the queue will be bound to. Note: to use default exchange then do not use empty name, but use default instead.";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_PATH_EXCHANGE_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_CONNECTION_FACTORY_CONF = "camel.sink.endpoint.connectionFactory";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_CONNECTION_FACTORY_DOC = "The connection factory to be use. A connection factory must be configured either on the component or endpoint.";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_CONNECTION_FACTORY_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_DISABLE_REPLY_TO_CONF = "camel.sink.endpoint.disableReplyTo";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_DISABLE_REPLY_TO_DOC = "Specifies whether Camel ignores the ReplyTo header in messages. If true, Camel does not send a reply back to the destination specified in the ReplyTo header. You can use this option if you want Camel to consume from a route and you do not want Camel to automatically send back a reply message because another component in your code handles the reply message. You can also use this option if you want to use Camel as a proxy between different message brokers and you want to route message from one system to another.";
    public static final Boolean CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_DISABLE_REPLY_TO_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ROUTING_KEY_CONF = "camel.sink.endpoint.routingKey";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ROUTING_KEY_DOC = "The value of a routing key to use. Default is empty which is not helpful when using the default (or any direct) exchange, but fine if the exchange is a headers exchange for instance.";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ROUTING_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_TEST_CONNECTION_ON_STARTUP_CONF = "camel.sink.endpoint.testConnectionOnStartup";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_TEST_CONNECTION_ON_STARTUP_DOC = "Specifies whether to test the connection on startup. This ensures that when Camel starts that all the JMS consumers have a valid connection to the JMS broker. If a connection cannot be granted then Camel throws an exception on startup. This ensures that Camel is not started with failed connections. The JMS producers is tested as well.";
    public static final Boolean CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_TEST_CONNECTION_ON_STARTUP_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_REPLY_TIMEOUT_CONF = "camel.sink.endpoint.replyTimeout";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_REPLY_TIMEOUT_DOC = "Specify the timeout in milliseconds to be used when waiting for a reply message when doing request/reply messaging. The default value is 5 seconds. A negative value indicates an indefinite timeout.";
    public static final Long CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_REPLY_TIMEOUT_DEFAULT = 5000L;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_USE_PUBLISHER_CONNECTION_CONF = "camel.sink.endpoint.usePublisherConnection";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_USE_PUBLISHER_CONNECTION_DOC = "Use a separate connection for publishers and consumers";
    public static final Boolean CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_USE_PUBLISHER_CONNECTION_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ARGS_CONF = "camel.sink.endpoint.args";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ARGS_DOC = "Specify arguments for configuring the different RabbitMQ concepts, a different prefix is required for each element: arg.consumer. arg.exchange. arg.queue. arg.binding. arg.dlq.exchange. arg.dlq.queue. arg.dlq.binding. For example to declare a queue with message ttl argument: args=arg.queue.x-message-ttl=60000";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ARGS_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_CONVERTER_CONF = "camel.sink.endpoint.messageConverter";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_CONVERTER_DOC = "To use a custom MessageConverter so you can be in control how to map to/from a org.springframework.amqp.core.Message.";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_CONVERTER_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_PROPERTIES_CONVERTER_CONF = "camel.sink.endpoint.messagePropertiesConverter";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_PROPERTIES_CONVERTER_DOC = "To use a custom MessagePropertiesConverter so you can be in control how to map to/from a org.springframework.amqp.core.MessageProperties.";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_PROPERTIES_CONVERTER_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used";
    public static final Boolean CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AMQP_ADMIN_CONF = "camel.component.spring-rabbitmq.amqpAdmin";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AMQP_ADMIN_DOC = "Optional AMQP Admin service to use for auto declaring elements (queues, exchanges, bindings)";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AMQP_ADMIN_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_CONNECTION_FACTORY_CONF = "camel.component.spring-rabbitmq.connectionFactory";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_CONNECTION_FACTORY_DOC = "The connection factory to be use. A connection factory must be configured either on the component or endpoint.";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_CONNECTION_FACTORY_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_TEST_CONNECTION_ON_STARTUP_CONF = "camel.component.spring-rabbitmq.testConnectionOnStartup";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_TEST_CONNECTION_ON_STARTUP_DOC = "Specifies whether to test the connection on startup. This ensures that when Camel starts that all the JMS consumers have a valid connection to the JMS broker. If a connection cannot be granted then Camel throws an exception on startup. This ensures that Camel is not started with failed connections. The JMS producers is tested as well.";
    public static final Boolean CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_TEST_CONNECTION_ON_STARTUP_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.spring-rabbitmq.lazyStartProducer";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_REPLY_TIMEOUT_CONF = "camel.component.spring-rabbitmq.replyTimeout";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_REPLY_TIMEOUT_DOC = "Specify the timeout in milliseconds to be used when waiting for a reply message when doing request/reply messaging. The default value is 5 seconds. A negative value indicates an indefinite timeout.";
    public static final Long CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_REPLY_TIMEOUT_DEFAULT = 5000L;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.spring-rabbitmq.autowiredEnabled";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_IGNORE_DECLARATION_EXCEPTIONS_CONF = "camel.component.spring-rabbitmq.ignoreDeclarationExceptions";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_IGNORE_DECLARATION_EXCEPTIONS_DOC = "Switch on ignore exceptions such as mismatched properties when declaring";
    public static final Boolean CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_IGNORE_DECLARATION_EXCEPTIONS_DEFAULT = false;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_CONVERTER_CONF = "camel.component.spring-rabbitmq.messageConverter";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_CONVERTER_DOC = "To use a custom MessageConverter so you can be in control how to map to/from a org.springframework.amqp.core.Message.";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_CONVERTER_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_PROPERTIES_CONVERTER_CONF = "camel.component.spring-rabbitmq.messagePropertiesConverter";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_PROPERTIES_CONVERTER_DOC = "To use a custom MessagePropertiesConverter so you can be in control how to map to/from a org.springframework.amqp.core.MessageProperties.";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_PROPERTIES_CONVERTER_DEFAULT = null;
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_HEADER_FILTER_STRATEGY_CONF = "camel.component.spring-rabbitmq.headerFilterStrategy";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_HEADER_FILTER_STRATEGY_DOC = "To use a custom org.apache.camel.spi.HeaderFilterStrategy to filter header to and from Camel message.";
    public static final String CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_HEADER_FILTER_STRATEGY_DEFAULT = null;

    public CamelSpringrabbitmqSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSpringrabbitmqSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_PATH_EXCHANGE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGRABBITMQ_PATH_EXCHANGE_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_SPRINGRABBITMQ_PATH_EXCHANGE_NAME_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_CONNECTION_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_CONNECTION_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_CONNECTION_FACTORY_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_DISABLE_REPLY_TO_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_DISABLE_REPLY_TO_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_DISABLE_REPLY_TO_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ROUTING_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ROUTING_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ROUTING_KEY_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_TEST_CONNECTION_ON_STARTUP_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_TEST_CONNECTION_ON_STARTUP_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_TEST_CONNECTION_ON_STARTUP_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_REPLY_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_REPLY_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_REPLY_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_USE_PUBLISHER_CONNECTION_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_USE_PUBLISHER_CONNECTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_USE_PUBLISHER_CONNECTION_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ARGS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ARGS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_ARGS_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_CONVERTER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_CONVERTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_CONVERTER_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_PROPERTIES_CONVERTER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_PROPERTIES_CONVERTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_MESSAGE_PROPERTIES_CONVERTER_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AMQP_ADMIN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AMQP_ADMIN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AMQP_ADMIN_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_CONNECTION_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_CONNECTION_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_CONNECTION_FACTORY_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_TEST_CONNECTION_ON_STARTUP_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_TEST_CONNECTION_ON_STARTUP_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_TEST_CONNECTION_ON_STARTUP_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_REPLY_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_REPLY_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_REPLY_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_IGNORE_DECLARATION_EXCEPTIONS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_IGNORE_DECLARATION_EXCEPTIONS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_IGNORE_DECLARATION_EXCEPTIONS_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_CONVERTER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_CONVERTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_CONVERTER_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_PROPERTIES_CONVERTER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_PROPERTIES_CONVERTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_MESSAGE_PROPERTIES_CONVERTER_DOC);
        conf.define(CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_HEADER_FILTER_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_HEADER_FILTER_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SPRINGRABBITMQ_COMPONENT_HEADER_FILTER_STRATEGY_DOC);
        return conf;
    }
}