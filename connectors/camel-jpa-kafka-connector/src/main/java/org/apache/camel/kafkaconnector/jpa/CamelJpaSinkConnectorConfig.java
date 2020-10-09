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
package org.apache.camel.kafkaconnector.jpa;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelJpaSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_JPA_PATH_ENTITY_TYPE_CONF = "camel.sink.path.entityType";
    public static final String CAMEL_SINK_JPA_PATH_ENTITY_TYPE_DOC = "Entity class name";
    public static final String CAMEL_SINK_JPA_PATH_ENTITY_TYPE_DEFAULT = null;
    public static final String CAMEL_SINK_JPA_ENDPOINT_JOIN_TRANSACTION_CONF = "camel.sink.endpoint.joinTransaction";
    public static final String CAMEL_SINK_JPA_ENDPOINT_JOIN_TRANSACTION_DOC = "The camel-jpa component will join transaction by default. You can use this option to turn this off, for example if you use LOCAL_RESOURCE and join transaction doesn't work with your JPA provider. This option can also be set globally on the JpaComponent, instead of having to set it on all endpoints.";
    public static final Boolean CAMEL_SINK_JPA_ENDPOINT_JOIN_TRANSACTION_DEFAULT = true;
    public static final String CAMEL_SINK_JPA_ENDPOINT_MAXIMUM_RESULTS_CONF = "camel.sink.endpoint.maximumResults";
    public static final String CAMEL_SINK_JPA_ENDPOINT_MAXIMUM_RESULTS_DOC = "Set the maximum number of results to retrieve on the Query.";
    public static final Integer CAMEL_SINK_JPA_ENDPOINT_MAXIMUM_RESULTS_DEFAULT = -1;
    public static final String CAMEL_SINK_JPA_ENDPOINT_NAMED_QUERY_CONF = "camel.sink.endpoint.namedQuery";
    public static final String CAMEL_SINK_JPA_ENDPOINT_NAMED_QUERY_DOC = "To use a named query.";
    public static final String CAMEL_SINK_JPA_ENDPOINT_NAMED_QUERY_DEFAULT = null;
    public static final String CAMEL_SINK_JPA_ENDPOINT_NATIVE_QUERY_CONF = "camel.sink.endpoint.nativeQuery";
    public static final String CAMEL_SINK_JPA_ENDPOINT_NATIVE_QUERY_DOC = "To use a custom native query. You may want to use the option resultClass also when using native queries.";
    public static final String CAMEL_SINK_JPA_ENDPOINT_NATIVE_QUERY_DEFAULT = null;
    public static final String CAMEL_SINK_JPA_ENDPOINT_PERSISTENCE_UNIT_CONF = "camel.sink.endpoint.persistenceUnit";
    public static final String CAMEL_SINK_JPA_ENDPOINT_PERSISTENCE_UNIT_DOC = "The JPA persistence unit used by default.";
    public static final String CAMEL_SINK_JPA_ENDPOINT_PERSISTENCE_UNIT_DEFAULT = "camel";
    public static final String CAMEL_SINK_JPA_ENDPOINT_QUERY_CONF = "camel.sink.endpoint.query";
    public static final String CAMEL_SINK_JPA_ENDPOINT_QUERY_DOC = "To use a custom query.";
    public static final String CAMEL_SINK_JPA_ENDPOINT_QUERY_DEFAULT = null;
    public static final String CAMEL_SINK_JPA_ENDPOINT_RESULT_CLASS_CONF = "camel.sink.endpoint.resultClass";
    public static final String CAMEL_SINK_JPA_ENDPOINT_RESULT_CLASS_DOC = "Defines the type of the returned payload (we will call entityManager.createNativeQuery(nativeQuery, resultClass) instead of entityManager.createNativeQuery(nativeQuery)). Without this option, we will return an object array. Only has an affect when using in conjunction with native query when consuming data.";
    public static final String CAMEL_SINK_JPA_ENDPOINT_RESULT_CLASS_DEFAULT = null;
    public static final String CAMEL_SINK_JPA_ENDPOINT_FIND_ENTITY_CONF = "camel.sink.endpoint.findEntity";
    public static final String CAMEL_SINK_JPA_ENDPOINT_FIND_ENTITY_DOC = "If enabled then the producer will find a single entity by using the message body as key and entityType as the class type. This can be used instead of a query to find a single entity.";
    public static final Boolean CAMEL_SINK_JPA_ENDPOINT_FIND_ENTITY_DEFAULT = false;
    public static final String CAMEL_SINK_JPA_ENDPOINT_FLUSH_ON_SEND_CONF = "camel.sink.endpoint.flushOnSend";
    public static final String CAMEL_SINK_JPA_ENDPOINT_FLUSH_ON_SEND_DOC = "Flushes the EntityManager after the entity bean has been persisted.";
    public static final Boolean CAMEL_SINK_JPA_ENDPOINT_FLUSH_ON_SEND_DEFAULT = true;
    public static final String CAMEL_SINK_JPA_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_JPA_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_JPA_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_JPA_ENDPOINT_REMOVE_CONF = "camel.sink.endpoint.remove";
    public static final String CAMEL_SINK_JPA_ENDPOINT_REMOVE_DOC = "Indicates to use entityManager.remove(entity).";
    public static final Boolean CAMEL_SINK_JPA_ENDPOINT_REMOVE_DEFAULT = false;
    public static final String CAMEL_SINK_JPA_ENDPOINT_USE_EXECUTE_UPDATE_CONF = "camel.sink.endpoint.useExecuteUpdate";
    public static final String CAMEL_SINK_JPA_ENDPOINT_USE_EXECUTE_UPDATE_DOC = "To configure whether to use executeUpdate() when producer executes a query. When you use INSERT, UPDATE or DELETE statement as a named query, you need to specify this option to 'true'.";
    public static final String CAMEL_SINK_JPA_ENDPOINT_USE_EXECUTE_UPDATE_DEFAULT = null;
    public static final String CAMEL_SINK_JPA_ENDPOINT_USE_PERSIST_CONF = "camel.sink.endpoint.usePersist";
    public static final String CAMEL_SINK_JPA_ENDPOINT_USE_PERSIST_DOC = "Indicates to use entityManager.persist(entity) instead of entityManager.merge(entity). Note: entityManager.persist(entity) doesn't work for detached entities (where the EntityManager has to execute an UPDATE instead of an INSERT query)!";
    public static final Boolean CAMEL_SINK_JPA_ENDPOINT_USE_PERSIST_DEFAULT = false;
    public static final String CAMEL_SINK_JPA_ENDPOINT_USE_PASSED_IN_ENTITY_MANAGER_CONF = "camel.sink.endpoint.usePassedInEntityManager";
    public static final String CAMEL_SINK_JPA_ENDPOINT_USE_PASSED_IN_ENTITY_MANAGER_DOC = "If set to true, then Camel will use the EntityManager from the header JpaConstants.ENTITY_MANAGER instead of the configured entity manager on the component/endpoint. This allows end users to control which entity manager will be in use.";
    public static final Boolean CAMEL_SINK_JPA_ENDPOINT_USE_PASSED_IN_ENTITY_MANAGER_DEFAULT = false;
    public static final String CAMEL_SINK_JPA_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    public static final String CAMEL_SINK_JPA_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_JPA_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_JPA_ENDPOINT_ENTITY_MANAGER_PROPERTIES_CONF = "camel.sink.endpoint.entityManagerProperties";
    public static final String CAMEL_SINK_JPA_ENDPOINT_ENTITY_MANAGER_PROPERTIES_DOC = "Additional properties for the entity manager to use.";
    public static final String CAMEL_SINK_JPA_ENDPOINT_ENTITY_MANAGER_PROPERTIES_DEFAULT = null;
    public static final String CAMEL_SINK_JPA_ENDPOINT_SHARED_ENTITY_MANAGER_CONF = "camel.sink.endpoint.sharedEntityManager";
    public static final String CAMEL_SINK_JPA_ENDPOINT_SHARED_ENTITY_MANAGER_DOC = "Whether to use Spring's SharedEntityManager for the consumer/producer. Note in most cases joinTransaction should be set to false as this is not an EXTENDED EntityManager.";
    public static final Boolean CAMEL_SINK_JPA_ENDPOINT_SHARED_ENTITY_MANAGER_DEFAULT = false;
    public static final String CAMEL_SINK_JPA_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_JPA_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_JPA_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_JPA_COMPONENT_ENTITY_MANAGER_FACTORY_CONF = "camel.component.jpa.entityManagerFactory";
    public static final String CAMEL_SINK_JPA_COMPONENT_ENTITY_MANAGER_FACTORY_DOC = "To use the EntityManagerFactory. This is strongly recommended to configure.";
    public static final String CAMEL_SINK_JPA_COMPONENT_ENTITY_MANAGER_FACTORY_DEFAULT = null;
    public static final String CAMEL_SINK_JPA_COMPONENT_JOIN_TRANSACTION_CONF = "camel.component.jpa.joinTransaction";
    public static final String CAMEL_SINK_JPA_COMPONENT_JOIN_TRANSACTION_DOC = "The camel-jpa component will join transaction by default. You can use this option to turn this off, for example if you use LOCAL_RESOURCE and join transaction doesn't work with your JPA provider. This option can also be set globally on the JpaComponent, instead of having to set it on all endpoints.";
    public static final Boolean CAMEL_SINK_JPA_COMPONENT_JOIN_TRANSACTION_DEFAULT = true;
    public static final String CAMEL_SINK_JPA_COMPONENT_SHARED_ENTITY_MANAGER_CONF = "camel.component.jpa.sharedEntityManager";
    public static final String CAMEL_SINK_JPA_COMPONENT_SHARED_ENTITY_MANAGER_DOC = "Whether to use Spring's SharedEntityManager for the consumer/producer. Note in most cases joinTransaction should be set to false as this is not an EXTENDED EntityManager.";
    public static final Boolean CAMEL_SINK_JPA_COMPONENT_SHARED_ENTITY_MANAGER_DEFAULT = false;
    public static final String CAMEL_SINK_JPA_COMPONENT_TRANSACTION_MANAGER_CONF = "camel.component.jpa.transactionManager";
    public static final String CAMEL_SINK_JPA_COMPONENT_TRANSACTION_MANAGER_DOC = "To use the PlatformTransactionManager for managing transactions.";
    public static final String CAMEL_SINK_JPA_COMPONENT_TRANSACTION_MANAGER_DEFAULT = null;
    public static final String CAMEL_SINK_JPA_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.jpa.lazyStartProducer";
    public static final String CAMEL_SINK_JPA_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_JPA_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_JPA_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.jpa.basicPropertyBinding";
    public static final String CAMEL_SINK_JPA_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_JPA_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelJpaSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelJpaSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_JPA_PATH_ENTITY_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JPA_PATH_ENTITY_TYPE_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_JPA_PATH_ENTITY_TYPE_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_JOIN_TRANSACTION_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_ENDPOINT_JOIN_TRANSACTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_JOIN_TRANSACTION_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_MAXIMUM_RESULTS_CONF, ConfigDef.Type.INT, CAMEL_SINK_JPA_ENDPOINT_MAXIMUM_RESULTS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_MAXIMUM_RESULTS_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_NAMED_QUERY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JPA_ENDPOINT_NAMED_QUERY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_NAMED_QUERY_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_NATIVE_QUERY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JPA_ENDPOINT_NATIVE_QUERY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_NATIVE_QUERY_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_PERSISTENCE_UNIT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JPA_ENDPOINT_PERSISTENCE_UNIT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_JPA_ENDPOINT_PERSISTENCE_UNIT_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_QUERY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JPA_ENDPOINT_QUERY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_QUERY_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_RESULT_CLASS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JPA_ENDPOINT_RESULT_CLASS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_RESULT_CLASS_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_FIND_ENTITY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_ENDPOINT_FIND_ENTITY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_FIND_ENTITY_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_FLUSH_ON_SEND_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_ENDPOINT_FLUSH_ON_SEND_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_FLUSH_ON_SEND_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_REMOVE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_ENDPOINT_REMOVE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_REMOVE_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_USE_EXECUTE_UPDATE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JPA_ENDPOINT_USE_EXECUTE_UPDATE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_USE_EXECUTE_UPDATE_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_USE_PERSIST_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_ENDPOINT_USE_PERSIST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_USE_PERSIST_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_USE_PASSED_IN_ENTITY_MANAGER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_ENDPOINT_USE_PASSED_IN_ENTITY_MANAGER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_USE_PASSED_IN_ENTITY_MANAGER_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_ENTITY_MANAGER_PROPERTIES_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JPA_ENDPOINT_ENTITY_MANAGER_PROPERTIES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_ENTITY_MANAGER_PROPERTIES_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_SHARED_ENTITY_MANAGER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_ENDPOINT_SHARED_ENTITY_MANAGER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_SHARED_ENTITY_MANAGER_DOC);
        conf.define(CAMEL_SINK_JPA_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_JPA_COMPONENT_ENTITY_MANAGER_FACTORY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JPA_COMPONENT_ENTITY_MANAGER_FACTORY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_COMPONENT_ENTITY_MANAGER_FACTORY_DOC);
        conf.define(CAMEL_SINK_JPA_COMPONENT_JOIN_TRANSACTION_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_COMPONENT_JOIN_TRANSACTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_COMPONENT_JOIN_TRANSACTION_DOC);
        conf.define(CAMEL_SINK_JPA_COMPONENT_SHARED_ENTITY_MANAGER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_COMPONENT_SHARED_ENTITY_MANAGER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_COMPONENT_SHARED_ENTITY_MANAGER_DOC);
        conf.define(CAMEL_SINK_JPA_COMPONENT_TRANSACTION_MANAGER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_JPA_COMPONENT_TRANSACTION_MANAGER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_COMPONENT_TRANSACTION_MANAGER_DOC);
        conf.define(CAMEL_SINK_JPA_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_JPA_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_JPA_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_JPA_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SINK_JPA_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}