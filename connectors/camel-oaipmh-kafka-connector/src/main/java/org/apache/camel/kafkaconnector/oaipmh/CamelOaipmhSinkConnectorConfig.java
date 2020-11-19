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
package org.apache.camel.kafkaconnector.oaipmh;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelOaipmhSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_OAIPMH_PATH_BASE_URL_CONF = "camel.sink.path.baseUrl";
    public static final String CAMEL_SINK_OAIPMH_PATH_BASE_URL_DOC = "Base URL of the repository to which the request is made through the OAI-PMH protocol";
    public static final String CAMEL_SINK_OAIPMH_PATH_BASE_URL_DEFAULT = null;
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_FROM_CONF = "camel.sink.endpoint.from";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_FROM_DOC = "Specifies a lower bound for datestamp-based selective harvesting. UTC DateTime value";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_FROM_DEFAULT = null;
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_IDENTIFIER_CONF = "camel.sink.endpoint.identifier";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_IDENTIFIER_DOC = "Identifier of the requested resources. Applicable only with certain verbs";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_IDENTIFIER_DEFAULT = null;
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_METADATA_PREFIX_CONF = "camel.sink.endpoint.metadataPrefix";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_METADATA_PREFIX_DOC = "Specifies the metadataPrefix of the format that should be included in the metadata part of the returned records.";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_METADATA_PREFIX_DEFAULT = "oai_dc";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_SET_CONF = "camel.sink.endpoint.set";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_SET_DOC = "Specifies membership as a criteria for set-based selective harvesting";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_SET_DEFAULT = null;
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_UNTIL_CONF = "camel.sink.endpoint.until";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_UNTIL_DOC = "Specifies an upper bound for datestamp-based selective harvesting. UTC DateTime value.";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_UNTIL_DEFAULT = null;
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_VERB_CONF = "camel.sink.endpoint.verb";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_VERB_DOC = "Request name supported by OAI-PMh protocol";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_VERB_DEFAULT = "ListRecords";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_OAIPMH_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_ONLY_FIRST_CONF = "camel.sink.endpoint.onlyFirst";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_ONLY_FIRST_DOC = "Returns the response of a single request. Otherwise it will make requests until there is no more data to return.";
    public static final Boolean CAMEL_SINK_OAIPMH_ENDPOINT_ONLY_FIRST_DEFAULT = false;
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_OAIPMH_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_IGNORE_SSLWARNINGS_CONF = "camel.sink.endpoint.ignoreSSLWarnings";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_IGNORE_SSLWARNINGS_DOC = "Ignore SSL certificate warnings";
    public static final Boolean CAMEL_SINK_OAIPMH_ENDPOINT_IGNORE_SSLWARNINGS_DEFAULT = false;
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_SSL_CONF = "camel.sink.endpoint.ssl";
    public static final String CAMEL_SINK_OAIPMH_ENDPOINT_SSL_DOC = "Causes the defined url to make an https request";
    public static final Boolean CAMEL_SINK_OAIPMH_ENDPOINT_SSL_DEFAULT = false;
    public static final String CAMEL_SINK_OAIPMH_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.oaipmh.lazyStartProducer";
    public static final String CAMEL_SINK_OAIPMH_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_OAIPMH_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_OAIPMH_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.oaipmh.autowiredEnabled";
    public static final String CAMEL_SINK_OAIPMH_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_OAIPMH_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;

    public CamelOaipmhSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelOaipmhSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_OAIPMH_PATH_BASE_URL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OAIPMH_PATH_BASE_URL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_OAIPMH_PATH_BASE_URL_DOC);
        conf.define(CAMEL_SINK_OAIPMH_ENDPOINT_FROM_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OAIPMH_ENDPOINT_FROM_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_ENDPOINT_FROM_DOC);
        conf.define(CAMEL_SINK_OAIPMH_ENDPOINT_IDENTIFIER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OAIPMH_ENDPOINT_IDENTIFIER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_ENDPOINT_IDENTIFIER_DOC);
        conf.define(CAMEL_SINK_OAIPMH_ENDPOINT_METADATA_PREFIX_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OAIPMH_ENDPOINT_METADATA_PREFIX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_ENDPOINT_METADATA_PREFIX_DOC);
        conf.define(CAMEL_SINK_OAIPMH_ENDPOINT_SET_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OAIPMH_ENDPOINT_SET_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_ENDPOINT_SET_DOC);
        conf.define(CAMEL_SINK_OAIPMH_ENDPOINT_UNTIL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OAIPMH_ENDPOINT_UNTIL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_ENDPOINT_UNTIL_DOC);
        conf.define(CAMEL_SINK_OAIPMH_ENDPOINT_VERB_CONF, ConfigDef.Type.STRING, CAMEL_SINK_OAIPMH_ENDPOINT_VERB_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_ENDPOINT_VERB_DOC);
        conf.define(CAMEL_SINK_OAIPMH_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OAIPMH_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_OAIPMH_ENDPOINT_ONLY_FIRST_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OAIPMH_ENDPOINT_ONLY_FIRST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_ENDPOINT_ONLY_FIRST_DOC);
        conf.define(CAMEL_SINK_OAIPMH_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OAIPMH_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_OAIPMH_ENDPOINT_IGNORE_SSLWARNINGS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OAIPMH_ENDPOINT_IGNORE_SSLWARNINGS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_ENDPOINT_IGNORE_SSLWARNINGS_DOC);
        conf.define(CAMEL_SINK_OAIPMH_ENDPOINT_SSL_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OAIPMH_ENDPOINT_SSL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_ENDPOINT_SSL_DOC);
        conf.define(CAMEL_SINK_OAIPMH_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OAIPMH_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_OAIPMH_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_OAIPMH_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_OAIPMH_COMPONENT_AUTOWIRED_ENABLED_DOC);
        return conf;
    }
}