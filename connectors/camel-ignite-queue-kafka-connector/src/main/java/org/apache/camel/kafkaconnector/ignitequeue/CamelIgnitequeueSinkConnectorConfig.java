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
package org.apache.camel.kafkaconnector.ignitequeue;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelIgnitequeueSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_IGNITEQUEUE_PATH_NAME_CONF = "camel.sink.path.name";
    private static final String CAMEL_SINK_IGNITEQUEUE_PATH_NAME_DOC = "The queue name.";
    private static final String CAMEL_SINK_IGNITEQUEUE_PATH_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CAPACITY_CONF = "camel.sink.endpoint.capacity";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CAPACITY_DOC = "The queue capacity. Default: non-bounded.";
    private static final Integer CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CAPACITY_DEFAULT = null;
    public static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CONFIGURATION_CONF = "camel.sink.endpoint.configuration";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CONFIGURATION_DOC = "The collection configuration. Default: empty configuration. You can also conveniently set inner properties by using configuration.xyz=123 options.";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_IGNITEQUEUE_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_OPERATION_CONF = "camel.sink.endpoint.operation";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_OPERATION_DOC = "The operation to invoke on the Ignite Queue. Superseded by the IgniteConstants.IGNITE_QUEUE_OPERATION header in the IN message. Possible values: CONTAINS, ADD, SIZE, REMOVE, ITERATOR, CLEAR, RETAIN_ALL, ARRAY, DRAIN, ELEMENT, PEEK, OFFER, POLL, TAKE, PUT. One of: [CONTAINS] [ADD] [SIZE] [REMOVE] [ITERATOR] [CLEAR] [RETAIN_ALL] [ARRAY] [DRAIN] [ELEMENT] [PEEK] [OFFER] [POLL] [TAKE] [PUT]";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_OPERATION_DEFAULT = null;
    public static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_PROPAGATE_INCOMING_BODY_IF_NO_RETURN_VALUE_CONF = "camel.sink.endpoint.propagateIncomingBodyIfNoReturnValue";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_PROPAGATE_INCOMING_BODY_IF_NO_RETURN_VALUE_DOC = "Sets whether to propagate the incoming body if the return type of the underlying Ignite operation is void.";
    private static final Boolean CAMEL_SINK_IGNITEQUEUE_ENDPOINT_PROPAGATE_INCOMING_BODY_IF_NO_RETURN_VALUE_DEFAULT = true;
    public static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TIMEOUT_MILLIS_CONF = "camel.sink.endpoint.timeoutMillis";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TIMEOUT_MILLIS_DOC = "The queue timeout in milliseconds. Default: no timeout.";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TIMEOUT_MILLIS_DEFAULT = null;
    public static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TREAT_COLLECTIONS_AS_CACHE_OBJECTS_CONF = "camel.sink.endpoint.treatCollectionsAsCacheObjects";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TREAT_COLLECTIONS_AS_CACHE_OBJECTS_DOC = "Sets whether to treat Collections as cache objects or as Collections of items to insert/update/compute, etc.";
    private static final Boolean CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TREAT_COLLECTIONS_AS_CACHE_OBJECTS_DEFAULT = false;
    public static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_IGNITEQUEUE_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_IGNITEQUEUE_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_IGNITEQUEUE_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_CONFIGURATION_RESOURCE_CONF = "camel.component.ignite-queue.configurationResource";
    private static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_CONFIGURATION_RESOURCE_DOC = "Resource from where to load configuration.";
    private static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_CONFIGURATION_RESOURCE_DEFAULT = null;
    public static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_CONF = "camel.component.ignite-queue.ignite";
    private static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_DOC = "Ignite instance.";
    private static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_DEFAULT = null;
    public static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_CONFIGURATION_CONF = "camel.component.ignite-queue.igniteConfiguration";
    private static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_CONFIGURATION_DOC = "Ignite configuration.";
    private static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.ignite-queue.lazyStartProducer";
    private static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_IGNITEQUEUE_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.ignite-queue.basicPropertyBinding";
    private static final String CAMEL_SINK_IGNITEQUEUE_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_IGNITEQUEUE_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelIgnitequeueSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelIgnitequeueSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_IGNITEQUEUE_PATH_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IGNITEQUEUE_PATH_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_IGNITEQUEUE_PATH_NAME_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CAPACITY_CONF, ConfigDef.Type.INT, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CAPACITY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CAPACITY_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_ENDPOINT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_OPERATION_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_ENDPOINT_PROPAGATE_INCOMING_BODY_IF_NO_RETURN_VALUE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_PROPAGATE_INCOMING_BODY_IF_NO_RETURN_VALUE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_PROPAGATE_INCOMING_BODY_IF_NO_RETURN_VALUE_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TIMEOUT_MILLIS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TIMEOUT_MILLIS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TIMEOUT_MILLIS_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TREAT_COLLECTIONS_AS_CACHE_OBJECTS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TREAT_COLLECTIONS_AS_CACHE_OBJECTS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_TREAT_COLLECTIONS_AS_CACHE_OBJECTS_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_COMPONENT_CONFIGURATION_RESOURCE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IGNITEQUEUE_COMPONENT_CONFIGURATION_RESOURCE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_COMPONENT_CONFIGURATION_RESOURCE_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_COMPONENT_IGNITE_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IGNITEQUEUE_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_IGNITEQUEUE_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_IGNITEQUEUE_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_IGNITEQUEUE_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}