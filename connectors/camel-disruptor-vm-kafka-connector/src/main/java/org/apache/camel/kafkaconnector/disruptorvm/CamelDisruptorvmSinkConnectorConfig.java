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
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelDisruptorvmSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_DISRUPTORVM_PATH_NAME_CONF = "camel.sink.path.name";
    public static final String CAMEL_SINK_DISRUPTORVM_PATH_NAME_DOC = "Name of queue";
    public static final String CAMEL_SINK_DISRUPTORVM_PATH_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_SIZE_CONF = "camel.sink.endpoint.size";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_SIZE_DOC = "The maximum capacity of the Disruptors ringbuffer Will be effectively increased to the nearest power of two. Notice: Mind if you use this option, then its the first endpoint being created with the queue name, that determines the size. To make sure all endpoints use same size, then configure the size option on all of them, or the first endpoint being created.";
    public static final Integer CAMEL_SINK_DISRUPTORVM_ENDPOINT_SIZE_DEFAULT = 1024;
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_BLOCK_WHEN_FULL_CONF = "camel.sink.endpoint.blockWhenFull";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_BLOCK_WHEN_FULL_DOC = "Whether a thread that sends messages to a full Disruptor will block until the ringbuffer's capacity is no longer exhausted. By default, the calling thread will block and wait until the message can be accepted. By disabling this option, an exception will be thrown stating that the queue is full.";
    public static final Boolean CAMEL_SINK_DISRUPTORVM_ENDPOINT_BLOCK_WHEN_FULL_DEFAULT = false;
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_DISRUPTORVM_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_PRODUCER_TYPE_CONF = "camel.sink.endpoint.producerType";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_PRODUCER_TYPE_DOC = "Defines the producers allowed on the Disruptor. The options allowed are: Multi to allow multiple producers and Single to enable certain optimizations only allowed when one concurrent producer (on one thread or otherwise synchronized) is active. One of: [Single] [Multi]";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_PRODUCER_TYPE_DEFAULT = "Multi";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_TIMEOUT_CONF = "camel.sink.endpoint.timeout";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_TIMEOUT_DOC = "Timeout (in milliseconds) before a producer will stop waiting for an asynchronous task to complete. You can disable timeout by using 0 or a negative value.";
    public static final Long CAMEL_SINK_DISRUPTORVM_ENDPOINT_TIMEOUT_DEFAULT = 30000L;
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_WAIT_FOR_TASK_TO_COMPLETE_CONF = "camel.sink.endpoint.waitForTaskToComplete";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_WAIT_FOR_TASK_TO_COMPLETE_DOC = "Option to specify whether the caller should wait for the async task to complete or not before continuing. The following three options are supported: Always, Never or IfReplyExpected. The first two values are self-explanatory. The last value, IfReplyExpected, will only wait if the message is Request Reply based. One of: [Never] [IfReplyExpected] [Always]";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_WAIT_FOR_TASK_TO_COMPLETE_DEFAULT = "IfReplyExpected";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_DISRUPTORVM_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_DISRUPTORVM_COMPONENT_BUFFER_SIZE_CONF = "camel.component.disruptor-vm.bufferSize";
    public static final String CAMEL_SINK_DISRUPTORVM_COMPONENT_BUFFER_SIZE_DOC = "To configure the ring buffer size";
    public static final Integer CAMEL_SINK_DISRUPTORVM_COMPONENT_BUFFER_SIZE_DEFAULT = 1024;
    public static final String CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_BLOCK_WHEN_FULL_CONF = "camel.component.disruptor-vm.defaultBlockWhenFull";
    public static final String CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_BLOCK_WHEN_FULL_DOC = "To configure the default value for block when full The default value is true.";
    public static final Boolean CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_BLOCK_WHEN_FULL_DEFAULT = true;
    public static final String CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_PRODUCER_TYPE_CONF = "camel.component.disruptor-vm.defaultProducerType";
    public static final String CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_PRODUCER_TYPE_DOC = "To configure the default value for DisruptorProducerType The default value is Multi. One of: [Single] [Multi]";
    public static final String CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_PRODUCER_TYPE_DEFAULT = "Multi";
    public static final String CAMEL_SINK_DISRUPTORVM_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.disruptor-vm.lazyStartProducer";
    public static final String CAMEL_SINK_DISRUPTORVM_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_DISRUPTORVM_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_DISRUPTORVM_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.disruptor-vm.basicPropertyBinding";
    public static final String CAMEL_SINK_DISRUPTORVM_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_DISRUPTORVM_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelDisruptorvmSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelDisruptorvmSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_DISRUPTORVM_PATH_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DISRUPTORVM_PATH_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_DISRUPTORVM_PATH_NAME_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_ENDPOINT_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SINK_DISRUPTORVM_ENDPOINT_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_ENDPOINT_SIZE_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_ENDPOINT_BLOCK_WHEN_FULL_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DISRUPTORVM_ENDPOINT_BLOCK_WHEN_FULL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_ENDPOINT_BLOCK_WHEN_FULL_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DISRUPTORVM_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_ENDPOINT_PRODUCER_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DISRUPTORVM_ENDPOINT_PRODUCER_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_ENDPOINT_PRODUCER_TYPE_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_ENDPOINT_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_DISRUPTORVM_ENDPOINT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_ENDPOINT_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_ENDPOINT_WAIT_FOR_TASK_TO_COMPLETE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DISRUPTORVM_ENDPOINT_WAIT_FOR_TASK_TO_COMPLETE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_ENDPOINT_WAIT_FOR_TASK_TO_COMPLETE_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DISRUPTORVM_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_COMPONENT_BUFFER_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SINK_DISRUPTORVM_COMPONENT_BUFFER_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_COMPONENT_BUFFER_SIZE_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_BLOCK_WHEN_FULL_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_BLOCK_WHEN_FULL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_BLOCK_WHEN_FULL_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_PRODUCER_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_PRODUCER_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_COMPONENT_DEFAULT_PRODUCER_TYPE_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DISRUPTORVM_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DISRUPTORVM_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_DISRUPTORVM_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DISRUPTORVM_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SINK_DISRUPTORVM_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}