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
package org.apache.camel.kafkaconnector.guavaeventbus;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelGuavaeventbusSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_GUAVAEVENTBUS_PATH_EVENT_BUS_REF_CONF = "camel.sink.path.eventBusRef";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_PATH_EVENT_BUS_REF_DOC = "To lookup the Guava EventBus from the registry with the given name";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_PATH_EVENT_BUS_REF_DEFAULT = null;
    public static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_EVENT_CLASS_CONF = "camel.sink.endpoint.eventClass";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_EVENT_CLASS_DOC = "If used on the consumer side of the route, will filter events received from the EventBus to the instances of the class and superclasses of eventClass. Null value of this option is equal to setting it to the java.lang.Object i.e. the consumer will capture all messages incoming to the event bus. This option cannot be used together with listenerInterface option.";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_EVENT_CLASS_DEFAULT = null;
    public static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LISTENER_INTERFACE_CONF = "camel.sink.endpoint.listenerInterface";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LISTENER_INTERFACE_DOC = "The interface with method(s) marked with the Subscribe annotation. Dynamic proxy will be created over the interface so it could be registered as the EventBus listener. Particularly useful when creating multi-event listeners and for handling DeadEvent properly. This option cannot be used together with eventClass option.";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LISTENER_INTERFACE_DEFAULT = null;
    public static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_EVENT_BUS_CONF = "camel.component.guava-eventbus.eventBus";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_EVENT_BUS_DOC = "To use the given Guava EventBus instance";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_EVENT_BUS_DEFAULT = null;
    public static final String CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LISTENER_INTERFACE_CONF = "camel.component.guava-eventbus.listenerInterface";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LISTENER_INTERFACE_DOC = "The interface with method(s) marked with the Subscribe annotation. Dynamic proxy will be created over the interface so it could be registered as the EventBus listener. Particularly useful when creating multi-event listeners and for handling DeadEvent properly. This option cannot be used together with eventClass option.";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LISTENER_INTERFACE_DEFAULT = null;
    public static final String CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.guava-eventbus.lazyStartProducer";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.guava-eventbus.basicPropertyBinding";
    private static final String CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelGuavaeventbusSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelGuavaeventbusSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_GUAVAEVENTBUS_PATH_EVENT_BUS_REF_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GUAVAEVENTBUS_PATH_EVENT_BUS_REF_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GUAVAEVENTBUS_PATH_EVENT_BUS_REF_DOC);
        conf.define(CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_EVENT_CLASS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_EVENT_CLASS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_EVENT_CLASS_DOC);
        conf.define(CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LISTENER_INTERFACE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LISTENER_INTERFACE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LISTENER_INTERFACE_DOC);
        conf.define(CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GUAVAEVENTBUS_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_EVENT_BUS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_EVENT_BUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_EVENT_BUS_DOC);
        conf.define(CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LISTENER_INTERFACE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LISTENER_INTERFACE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LISTENER_INTERFACE_DOC);
        conf.define(CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GUAVAEVENTBUS_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}