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
package org.apache.camel.kafkaconnector.solrs;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelSolrsSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_SOLRS_PATH_URL_CONF = "camel.sink.path.url";
    private static final String CAMEL_SINK_SOLRS_PATH_URL_DOC = "Hostname and port for the solr server";
    private static final String CAMEL_SINK_SOLRS_PATH_URL_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_ALLOW_COMPRESSION_CONF = "camel.sink.endpoint.allowCompression";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_ALLOW_COMPRESSION_DOC = "Server side must support gzip or deflate for this to have any effect";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_ALLOW_COMPRESSION_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_CONNECTION_TIMEOUT_CONF = "camel.sink.endpoint.connectionTimeout";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_CONNECTION_TIMEOUT_DOC = "connectionTimeout on the underlying HttpConnectionManager";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_CONNECTION_TIMEOUT_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_DEFAULT_MAX_CONNECTIONS_PER_HOST_CONF = "camel.sink.endpoint.defaultMaxConnectionsPerHost";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_DEFAULT_MAX_CONNECTIONS_PER_HOST_DOC = "maxConnectionsPerHost on the underlying HttpConnectionManager";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_DEFAULT_MAX_CONNECTIONS_PER_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_FOLLOW_REDIRECTS_CONF = "camel.sink.endpoint.followRedirects";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_FOLLOW_REDIRECTS_DOC = "indicates whether redirects are used to get to the Solr server";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_FOLLOW_REDIRECTS_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_SOLRS_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_MAX_RETRIES_CONF = "camel.sink.endpoint.maxRetries";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_MAX_RETRIES_DOC = "Maximum number of retries to attempt in the event of transient errors";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_MAX_RETRIES_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_MAX_TOTAL_CONNECTIONS_CONF = "camel.sink.endpoint.maxTotalConnections";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_MAX_TOTAL_CONNECTIONS_DOC = "maxTotalConnection on the underlying HttpConnectionManager";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_MAX_TOTAL_CONNECTIONS_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_REQUEST_HANDLER_CONF = "camel.sink.endpoint.requestHandler";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_REQUEST_HANDLER_DOC = "Set the request handler to be used";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_REQUEST_HANDLER_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_SO_TIMEOUT_CONF = "camel.sink.endpoint.soTimeout";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_SO_TIMEOUT_DOC = "Read timeout on the underlying HttpConnectionManager. This is desirable for queries, but probably not for indexing";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_SO_TIMEOUT_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_QUEUE_SIZE_CONF = "camel.sink.endpoint.streamingQueueSize";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_QUEUE_SIZE_DOC = "Set the queue size for the StreamingUpdateSolrServer";
    private static final Integer CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_QUEUE_SIZE_DEFAULT = 10;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_THREAD_COUNT_CONF = "camel.sink.endpoint.streamingThreadCount";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_THREAD_COUNT_DOC = "Set the number of threads for the StreamingUpdateSolrServer";
    private static final Integer CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_THREAD_COUNT_DEFAULT = 2;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_SOLRS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_SOLRS_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_PASSWORD_CONF = "camel.sink.endpoint.password";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_PASSWORD_DOC = "Sets password for basic auth plugin enabled servers";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_USERNAME_CONF = "camel.sink.endpoint.username";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_USERNAME_DOC = "Sets username for basic auth plugin enabled servers";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_COLLECTION_CONF = "camel.sink.endpoint.collection";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_COLLECTION_DOC = "Set the collection name which the solrCloud server could use";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_COLLECTION_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_ENDPOINT_ZK_HOST_CONF = "camel.sink.endpoint.zkHost";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_ZK_HOST_DOC = "Set the ZooKeeper host information which the solrCloud could use, such as zkhost=localhost:8123.";
    private static final String CAMEL_SINK_SOLRS_ENDPOINT_ZK_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_SOLRS_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.solrs.lazyStartProducer";
    private static final String CAMEL_SINK_SOLRS_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_SOLRS_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SOLRS_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.solrs.basicPropertyBinding";
    private static final String CAMEL_SINK_SOLRS_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_SOLRS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelSolrsSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSolrsSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_SOLRS_PATH_URL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_PATH_URL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_SOLRS_PATH_URL_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_ALLOW_COMPRESSION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_ALLOW_COMPRESSION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_ALLOW_COMPRESSION_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_CONNECTION_TIMEOUT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_CONNECTION_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_CONNECTION_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_DEFAULT_MAX_CONNECTIONS_PER_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_DEFAULT_MAX_CONNECTIONS_PER_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_DEFAULT_MAX_CONNECTIONS_PER_HOST_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_FOLLOW_REDIRECTS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_FOLLOW_REDIRECTS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_FOLLOW_REDIRECTS_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SOLRS_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_MAX_RETRIES_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_MAX_RETRIES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_MAX_RETRIES_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_MAX_TOTAL_CONNECTIONS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_MAX_TOTAL_CONNECTIONS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_MAX_TOTAL_CONNECTIONS_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_REQUEST_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_REQUEST_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_REQUEST_HANDLER_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_SO_TIMEOUT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_SO_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_SO_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_QUEUE_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_QUEUE_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_QUEUE_SIZE_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_THREAD_COUNT_CONF, ConfigDef.Type.INT, CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_THREAD_COUNT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_STREAMING_THREAD_COUNT_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SOLRS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SOLRS_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_PASSWORD_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_USERNAME_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_COLLECTION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_COLLECTION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_COLLECTION_DOC);
        conf.define(CAMEL_SINK_SOLRS_ENDPOINT_ZK_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SOLRS_ENDPOINT_ZK_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_ENDPOINT_ZK_HOST_DOC);
        conf.define(CAMEL_SINK_SOLRS_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SOLRS_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SOLRS_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SOLRS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SOLRS_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}