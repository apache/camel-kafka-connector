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
package org.apache.camel.kafkaconnector.disruptorvm;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelDisruptorvmSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_DISRUPTORVM_PATH_NAME_CONF = "camel.source.path.name";
    public static final String CAMEL_SOURCE_DISRUPTORVM_PATH_NAME_DOC = "Name of queue";
    public static final String CAMEL_SOURCE_DISRUPTORVM_PATH_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SIZE_CONF = "camel.source.endpoint.size";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SIZE_DOC = "The maximum capacity of the Disruptors ringbuffer Will be effectively increased to the nearest power of two. Notice: Mind if you use this option, then its the first endpoint being created with the queue name, that determines the size. To make sure all endpoints use same size, then configure the size option on all of them, or the first endpoint being created.";
    public static final Integer CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SIZE_DEFAULT = 1024;
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_CONCURRENT_CONSUMERS_CONF = "camel.source.endpoint.concurrentConsumers";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_CONCURRENT_CONSUMERS_DOC = "Number of concurrent threads processing exchanges.";
    public static final Integer CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_CONCURRENT_CONSUMERS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_MULTIPLE_CONSUMERS_CONF = "camel.source.endpoint.multipleConsumers";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_MULTIPLE_CONSUMERS_DOC = "Specifies whether multiple consumers are allowed. If enabled, you can use Disruptor for Publish-Subscribe messaging. That is, you can send a message to the queue and have each consumer receive a copy of the message. When enabled, this option should be specified on every consumer endpoint.";
    public static final Boolean CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_MULTIPLE_CONSUMERS_DEFAULT = false;
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_WAIT_STRATEGY_CONF = "camel.source.endpoint.waitStrategy";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_WAIT_STRATEGY_DOC = "Defines the strategy used by consumer threads to wait on new exchanges to be published. The options allowed are:Blocking, Sleeping, BusySpin and Yielding. One of: [Blocking] [Sleeping] [BusySpin] [Yielding]";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_WAIT_STRATEGY_DEFAULT = "Blocking";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    public static final String CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BUFFER_SIZE_CONF = "camel.component.disruptor-vm.bufferSize";
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BUFFER_SIZE_DOC = "To configure the ring buffer size";
    public static final Integer CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BUFFER_SIZE_DEFAULT = 1024;
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.disruptor-vm.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_CONCURRENT_CONSUMERS_CONF = "camel.component.disruptor-vm.defaultConcurrentConsumers";
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_CONCURRENT_CONSUMERS_DOC = "To configure the default number of concurrent consumers";
    public static final Integer CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_CONCURRENT_CONSUMERS_DEFAULT = 1;
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_MULTIPLE_CONSUMERS_CONF = "camel.component.disruptor-vm.defaultMultipleConsumers";
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_MULTIPLE_CONSUMERS_DOC = "To configure the default value for multiple consumers";
    public static final Boolean CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_MULTIPLE_CONSUMERS_DEFAULT = false;
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_WAIT_STRATEGY_CONF = "camel.component.disruptor-vm.defaultWaitStrategy";
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_WAIT_STRATEGY_DOC = "To configure the default value for DisruptorWaitStrategy The default value is Blocking. One of: [Blocking] [Sleeping] [BusySpin] [Yielding]";
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_WAIT_STRATEGY_DEFAULT = "Blocking";
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.disruptor-vm.autowiredEnabled";
    public static final String CAMEL_SOURCE_DISRUPTORVM_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SOURCE_DISRUPTORVM_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;

    public CamelDisruptorvmSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelDisruptorvmSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_DISRUPTORVM_PATH_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_DISRUPTORVM_PATH_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_DISRUPTORVM_PATH_NAME_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SIZE_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_CONCURRENT_CONSUMERS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_CONCURRENT_CONSUMERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_CONCURRENT_CONSUMERS_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_MULTIPLE_CONSUMERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_MULTIPLE_CONSUMERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_MULTIPLE_CONSUMERS_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_WAIT_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_WAIT_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_WAIT_STRATEGY_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BUFFER_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BUFFER_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BUFFER_SIZE_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_CONCURRENT_CONSUMERS_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_CONCURRENT_CONSUMERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_CONCURRENT_CONSUMERS_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_MULTIPLE_CONSUMERS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_MULTIPLE_CONSUMERS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_MULTIPLE_CONSUMERS_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_WAIT_STRATEGY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_WAIT_STRATEGY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_DEFAULT_WAIT_STRATEGY_DOC);
        conf.define(CAMEL_SOURCE_DISRUPTORVM_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_DISRUPTORVM_COMPONENT_AUTOWIRED_ENABLED_DOC);
        return conf;
    }
}