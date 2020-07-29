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
package org.apache.camel.kafkaconnector.elasticsearchrest;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelElasticsearchrestSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_ELASTICSEARCHREST_PATH_CLUSTER_NAME_CONF = "camel.sink.path.clusterName";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_PATH_CLUSTER_NAME_DOC = "Name of the cluster";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_PATH_CLUSTER_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_CONNECTION_TIMEOUT_CONF = "camel.sink.endpoint.connectionTimeout";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_CONNECTION_TIMEOUT_DOC = "The time in ms to wait before connection will timeout.";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_CONNECTION_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_DISCONNECT_CONF = "camel.sink.endpoint.disconnect";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_DISCONNECT_DOC = "Disconnect after it finish calling the producer";
    private static final Boolean CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_DISCONNECT_DEFAULT = false;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SNIFFER_CONF = "camel.sink.endpoint.enableSniffer";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SNIFFER_DOC = "Enable automatically discover nodes from a running Elasticsearch cluster";
    private static final Boolean CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SNIFFER_DEFAULT = false;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SSLCONF = "camel.sink.endpoint.enableSSL";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SSLDOC = "Enable SSL";
    private static final Boolean CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SSLDEFAULT = false;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_FROM_CONF = "camel.sink.endpoint.from";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_FROM_DOC = "Starting index of the response.";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_FROM_DEFAULT = null;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_HOST_ADDRESSES_CONF = "camel.sink.endpoint.hostAddresses";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_HOST_ADDRESSES_DOC = "Comma separated list with ip:port formatted remote transport addresses to use.";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_HOST_ADDRESSES_DEFAULT = null;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_INDEX_NAME_CONF = "camel.sink.endpoint.indexName";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_INDEX_NAME_DOC = "The name of the index to act against";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_INDEX_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_MAX_RETRY_TIMEOUT_CONF = "camel.sink.endpoint.maxRetryTimeout";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_MAX_RETRY_TIMEOUT_DOC = "The time in ms before retry";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_MAX_RETRY_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_OPERATION_CONF = "camel.sink.endpoint.operation";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_OPERATION_DOC = "What operation to perform One of: [Index] [Update] [Bulk] [BulkIndex] [GetById] [MultiGet] [MultiSearch] [Delete] [DeleteIndex] [Search] [Exists] [Ping]";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_OPERATION_DEFAULT = null;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SCROLL_KEEP_ALIVE_MS_CONF = "camel.sink.endpoint.scrollKeepAliveMs";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SCROLL_KEEP_ALIVE_MS_DOC = "Time in ms during which elasticsearch will keep search context alive";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SCROLL_KEEP_ALIVE_MS_DEFAULT = 60000;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SIZE_CONF = "camel.sink.endpoint.size";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SIZE_DOC = "Size of the response.";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SIZE_DEFAULT = null;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFF_AFTER_FAILURE_DELAY_CONF = "camel.sink.endpoint.sniffAfterFailureDelay";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFF_AFTER_FAILURE_DELAY_DOC = "The delay of a sniff execution scheduled after a failure (in milliseconds)";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFF_AFTER_FAILURE_DELAY_DEFAULT = 60000;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFFER_INTERVAL_CONF = "camel.sink.endpoint.snifferInterval";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFFER_INTERVAL_DOC = "The interval between consecutive ordinary sniff executions in milliseconds. Will be honoured when sniffOnFailure is disabled or when there are no failures between consecutive sniff executions";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFFER_INTERVAL_DEFAULT = 300000;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SOCKET_TIMEOUT_CONF = "camel.sink.endpoint.socketTimeout";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SOCKET_TIMEOUT_DOC = "The timeout in ms to wait before the socket will timeout.";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SOCKET_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_USE_SCROLL_CONF = "camel.sink.endpoint.useScroll";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_USE_SCROLL_DOC = "Enable scroll usage";
    private static final Boolean CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_USE_SCROLL_DEFAULT = false;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_WAIT_FOR_ACTIVE_SHARDS_CONF = "camel.sink.endpoint.waitForActiveShards";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_WAIT_FOR_ACTIVE_SHARDS_DOC = "Index creation waits for the write consistency number of shards to be available";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_WAIT_FOR_ACTIVE_SHARDS_DEFAULT = 1;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.elasticsearch-rest.lazyStartProducer";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.elasticsearch-rest.basicPropertyBinding";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CLIENT_CONF = "camel.component.elasticsearch-rest.client";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CLIENT_DOC = "To use an existing configured Elasticsearch client, instead of creating a client per endpoint. This allow to customize the client with specific settings.";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CLIENT_DEFAULT = null;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CONNECTION_TIMEOUT_CONF = "camel.component.elasticsearch-rest.connectionTimeout";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CONNECTION_TIMEOUT_DOC = "The time in ms to wait before connection will timeout.";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CONNECTION_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SNIFFER_CONF = "camel.component.elasticsearch-rest.enableSniffer";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SNIFFER_DOC = "Enable automatically discover nodes from a running Elasticsearch cluster";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SNIFFER_DEFAULT = "false";
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_HOST_ADDRESSES_CONF = "camel.component.elasticsearch-rest.hostAddresses";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_HOST_ADDRESSES_DOC = "Comma separated list with ip:port formatted remote transport addresses to use. The ip and port options must be left blank for hostAddresses to be considered instead.";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_HOST_ADDRESSES_DEFAULT = null;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_MAX_RETRY_TIMEOUT_CONF = "camel.component.elasticsearch-rest.maxRetryTimeout";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_MAX_RETRY_TIMEOUT_DOC = "The time in ms before retry";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_MAX_RETRY_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFF_AFTER_FAILURE_DELAY_CONF = "camel.component.elasticsearch-rest.sniffAfterFailureDelay";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFF_AFTER_FAILURE_DELAY_DOC = "The delay of a sniff execution scheduled after a failure (in milliseconds)";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFF_AFTER_FAILURE_DELAY_DEFAULT = 60000;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFFER_INTERVAL_CONF = "camel.component.elasticsearch-rest.snifferInterval";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFFER_INTERVAL_DOC = "The interval between consecutive ordinary sniff executions in milliseconds. Will be honoured when sniffOnFailure is disabled or when there are no failures between consecutive sniff executions";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFFER_INTERVAL_DEFAULT = 300000;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SOCKET_TIMEOUT_CONF = "camel.component.elasticsearch-rest.socketTimeout";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SOCKET_TIMEOUT_DOC = "The timeout in ms to wait before the socket will timeout.";
    private static final Integer CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SOCKET_TIMEOUT_DEFAULT = 30000;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SSLCONF = "camel.component.elasticsearch-rest.enableSSL";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SSLDOC = "Enable SSL";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SSLDEFAULT = "false";
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_PASSWORD_CONF = "camel.component.elasticsearch-rest.password";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_PASSWORD_DOC = "Password for authenticate";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_USER_CONF = "camel.component.elasticsearch-rest.user";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_USER_DOC = "Basic authenticate user";
    private static final String CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_USER_DEFAULT = null;

    public CamelElasticsearchrestSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelElasticsearchrestSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_PATH_CLUSTER_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_PATH_CLUSTER_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_ELASTICSEARCHREST_PATH_CLUSTER_NAME_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_CONNECTION_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_CONNECTION_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_CONNECTION_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_DISCONNECT_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_DISCONNECT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_DISCONNECT_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SNIFFER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SNIFFER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SNIFFER_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SSLCONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SSLDEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_ENABLE_SSLDOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_FROM_CONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_FROM_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_FROM_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_HOST_ADDRESSES_CONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_HOST_ADDRESSES_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_HOST_ADDRESSES_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_INDEX_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_INDEX_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_INDEX_NAME_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_MAX_RETRY_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_MAX_RETRY_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_MAX_RETRY_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_OPERATION_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SCROLL_KEEP_ALIVE_MS_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SCROLL_KEEP_ALIVE_MS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SCROLL_KEEP_ALIVE_MS_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SIZE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SIZE_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFF_AFTER_FAILURE_DELAY_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFF_AFTER_FAILURE_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFF_AFTER_FAILURE_DELAY_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFFER_INTERVAL_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFFER_INTERVAL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SNIFFER_INTERVAL_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SOCKET_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SOCKET_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SOCKET_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_USE_SCROLL_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_USE_SCROLL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_USE_SCROLL_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_WAIT_FOR_ACTIVE_SHARDS_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_WAIT_FOR_ACTIVE_SHARDS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_WAIT_FOR_ACTIVE_SHARDS_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CLIENT_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CONNECTION_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CONNECTION_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_CONNECTION_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SNIFFER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SNIFFER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SNIFFER_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_HOST_ADDRESSES_CONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_HOST_ADDRESSES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_HOST_ADDRESSES_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_MAX_RETRY_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_MAX_RETRY_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_MAX_RETRY_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFF_AFTER_FAILURE_DELAY_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFF_AFTER_FAILURE_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFF_AFTER_FAILURE_DELAY_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFFER_INTERVAL_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFFER_INTERVAL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SNIFFER_INTERVAL_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SOCKET_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SOCKET_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_SOCKET_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SSLCONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SSLDEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_ENABLE_SSLDOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_PASSWORD_DOC);
        conf.define(CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_USER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_USER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_ELASTICSEARCHREST_COMPONENT_USER_DOC);
        return conf;
    }
}