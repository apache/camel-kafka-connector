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
package org.apache.camel.kafkaconnector.couchbase;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelCouchbaseSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_COUCHBASE_PATH_PROTOCOL_CONF = "camel.sink.path.protocol";
    public static final String CAMEL_SINK_COUCHBASE_PATH_PROTOCOL_DOC = "The protocol to use";
    public static final String CAMEL_SINK_COUCHBASE_PATH_PROTOCOL_DEFAULT = null;
    public static final String CAMEL_SINK_COUCHBASE_PATH_HOSTNAME_CONF = "camel.sink.path.hostname";
    public static final String CAMEL_SINK_COUCHBASE_PATH_HOSTNAME_DOC = "The hostname to use";
    public static final String CAMEL_SINK_COUCHBASE_PATH_HOSTNAME_DEFAULT = null;
    public static final String CAMEL_SINK_COUCHBASE_PATH_PORT_CONF = "camel.sink.path.port";
    public static final String CAMEL_SINK_COUCHBASE_PATH_PORT_DOC = "The port number to use";
    public static final Integer CAMEL_SINK_COUCHBASE_PATH_PORT_DEFAULT = 8091;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_BUCKET_CONF = "camel.sink.endpoint.bucket";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_BUCKET_DOC = "The bucket to use";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_BUCKET_DEFAULT = null;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_COLLECTION_CONF = "camel.sink.endpoint.collection";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_COLLECTION_DOC = "The collection to use";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_COLLECTION_DEFAULT = null;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_KEY_CONF = "camel.sink.endpoint.key";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_KEY_DOC = "The key to use";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_SCOPE_CONF = "camel.sink.endpoint.scope";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_SCOPE_DOC = "The scope to use";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_SCOPE_DEFAULT = null;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_AUTO_START_ID_FOR_INSERTS_CONF = "camel.sink.endpoint.autoStartIdForInserts";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_AUTO_START_ID_FOR_INSERTS_DOC = "Define if we want an autostart Id when we are doing an insert operation";
    public static final Boolean CAMEL_SINK_COUCHBASE_ENDPOINT_AUTO_START_ID_FOR_INSERTS_DEFAULT = false;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_COUCHBASE_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_OPERATION_CONF = "camel.sink.endpoint.operation";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_OPERATION_DOC = "The operation to do";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_OPERATION_DEFAULT = "CCB_PUT";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_PERSIST_TO_CONF = "camel.sink.endpoint.persistTo";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_PERSIST_TO_DOC = "Where to persist the data";
    public static final Integer CAMEL_SINK_COUCHBASE_ENDPOINT_PERSIST_TO_DEFAULT = 0;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_ATTEMPTS_CONF = "camel.sink.endpoint.producerRetryAttempts";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_ATTEMPTS_DOC = "Define the number of retry attempts";
    public static final Integer CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_ATTEMPTS_DEFAULT = 2;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_PAUSE_CONF = "camel.sink.endpoint.producerRetryPause";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_PAUSE_DOC = "Define the retry pause between different attempts";
    public static final Integer CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_PAUSE_DEFAULT = 5000;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_REPLICATE_TO_CONF = "camel.sink.endpoint.replicateTo";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_REPLICATE_TO_DOC = "Where to replicate the data";
    public static final Integer CAMEL_SINK_COUCHBASE_ENDPOINT_REPLICATE_TO_DEFAULT = 0;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_STARTING_ID_FOR_INSERTS_FROM_CONF = "camel.sink.endpoint.startingIdForInsertsFrom";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_STARTING_ID_FOR_INSERTS_FROM_DOC = "Define the starting Id where we are doing an insert operation";
    public static final Long CAMEL_SINK_COUCHBASE_ENDPOINT_STARTING_ID_FOR_INSERTS_FROM_DEFAULT = null;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_ADDITIONAL_HOSTS_CONF = "camel.sink.endpoint.additionalHosts";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_ADDITIONAL_HOSTS_DOC = "The additional hosts";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_ADDITIONAL_HOSTS_DEFAULT = null;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_CONNECT_TIMEOUT_CONF = "camel.sink.endpoint.connectTimeout";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_CONNECT_TIMEOUT_DOC = "Define the timeoutconnect in milliseconds";
    public static final Long CAMEL_SINK_COUCHBASE_ENDPOINT_CONNECT_TIMEOUT_DEFAULT = 2500L;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_QUERY_TIMEOUT_CONF = "camel.sink.endpoint.queryTimeout";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_QUERY_TIMEOUT_DOC = "Define the operation timeout in milliseconds";
    public static final Long CAMEL_SINK_COUCHBASE_ENDPOINT_QUERY_TIMEOUT_DEFAULT = 2500L;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_PASSWORD_CONF = "camel.sink.endpoint.password";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_PASSWORD_DOC = "The password to use";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_USERNAME_CONF = "camel.sink.endpoint.username";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_USERNAME_DOC = "The username to use";
    public static final String CAMEL_SINK_COUCHBASE_ENDPOINT_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_COUCHBASE_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.couchbase.lazyStartProducer";
    public static final String CAMEL_SINK_COUCHBASE_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_COUCHBASE_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_COUCHBASE_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.couchbase.autowiredEnabled";
    public static final String CAMEL_SINK_COUCHBASE_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_COUCHBASE_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;

    public CamelCouchbaseSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelCouchbaseSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_COUCHBASE_PATH_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COUCHBASE_PATH_PROTOCOL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_COUCHBASE_PATH_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_PATH_HOSTNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COUCHBASE_PATH_HOSTNAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_COUCHBASE_PATH_HOSTNAME_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_PATH_PORT_CONF, ConfigDef.Type.INT, CAMEL_SINK_COUCHBASE_PATH_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_PATH_PORT_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_BUCKET_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COUCHBASE_ENDPOINT_BUCKET_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_COUCHBASE_ENDPOINT_BUCKET_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_COLLECTION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COUCHBASE_ENDPOINT_COLLECTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_COLLECTION_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COUCHBASE_ENDPOINT_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_KEY_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_SCOPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COUCHBASE_ENDPOINT_SCOPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_SCOPE_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_AUTO_START_ID_FOR_INSERTS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COUCHBASE_ENDPOINT_AUTO_START_ID_FOR_INSERTS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_AUTO_START_ID_FOR_INSERTS_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COUCHBASE_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COUCHBASE_ENDPOINT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_OPERATION_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_PERSIST_TO_CONF, ConfigDef.Type.INT, CAMEL_SINK_COUCHBASE_ENDPOINT_PERSIST_TO_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_PERSIST_TO_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_ATTEMPTS_CONF, ConfigDef.Type.INT, CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_ATTEMPTS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_ATTEMPTS_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_PAUSE_CONF, ConfigDef.Type.INT, CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_PAUSE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_PRODUCER_RETRY_PAUSE_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_REPLICATE_TO_CONF, ConfigDef.Type.INT, CAMEL_SINK_COUCHBASE_ENDPOINT_REPLICATE_TO_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_REPLICATE_TO_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_STARTING_ID_FOR_INSERTS_FROM_CONF, ConfigDef.Type.LONG, CAMEL_SINK_COUCHBASE_ENDPOINT_STARTING_ID_FOR_INSERTS_FROM_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_STARTING_ID_FOR_INSERTS_FROM_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_ADDITIONAL_HOSTS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_COUCHBASE_ENDPOINT_ADDITIONAL_HOSTS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_ADDITIONAL_HOSTS_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_CONNECT_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_COUCHBASE_ENDPOINT_CONNECT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_CONNECT_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_QUERY_TIMEOUT_CONF, ConfigDef.Type.LONG, CAMEL_SINK_COUCHBASE_ENDPOINT_QUERY_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_QUERY_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_PASSWORD_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_COUCHBASE_ENDPOINT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_PASSWORD_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_ENDPOINT_USERNAME_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_COUCHBASE_ENDPOINT_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_ENDPOINT_USERNAME_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COUCHBASE_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_COUCHBASE_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_COUCHBASE_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_COUCHBASE_COMPONENT_AUTOWIRED_ENABLED_DOC);
        return conf;
    }
}