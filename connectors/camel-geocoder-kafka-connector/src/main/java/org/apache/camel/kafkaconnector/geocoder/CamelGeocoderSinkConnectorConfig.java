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
package org.apache.camel.kafkaconnector.geocoder;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelGeocoderSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_GEOCODER_PATH_ADDRESS_CONF = "camel.sink.path.address";
    public static final String CAMEL_SINK_GEOCODER_PATH_ADDRESS_DOC = "The geo address which should be prefixed with address:";
    public static final String CAMEL_SINK_GEOCODER_PATH_ADDRESS_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_PATH_LATLNG_CONF = "camel.sink.path.latlng";
    public static final String CAMEL_SINK_GEOCODER_PATH_LATLNG_DOC = "The geo latitude and longitude which should be prefixed with latlng:";
    public static final String CAMEL_SINK_GEOCODER_PATH_LATLNG_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_HEADERS_ONLY_CONF = "camel.sink.endpoint.headersOnly";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_HEADERS_ONLY_DOC = "Whether to only enrich the Exchange with headers, and leave the body as-is.";
    public static final Boolean CAMEL_SINK_GEOCODER_ENDPOINT_HEADERS_ONLY_DEFAULT = false;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_LANGUAGE_CONF = "camel.sink.endpoint.language";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_LANGUAGE_DOC = "The language to use.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_LANGUAGE_DEFAULT = "en";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_GEOCODER_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_SERVER_URL_CONF = "camel.sink.endpoint.serverUrl";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_SERVER_URL_DOC = "URL to the geocoder server. Mandatory for Nominatim server.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_SERVER_URL_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_TYPE_CONF = "camel.sink.endpoint.type";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_TYPE_DOC = "Type of GeoCoding server. Supported Nominatim and Google. One of: [NOMINATIM] [GOOGLE]";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_TYPE_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_DOMAIN_CONF = "camel.sink.endpoint.proxyAuthDomain";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_DOMAIN_DOC = "Proxy Authentication Domain to access Google GeoCoding server.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_DOMAIN_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_HOST_CONF = "camel.sink.endpoint.proxyAuthHost";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_HOST_DOC = "Proxy Authentication Host to access Google GeoCoding server.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_METHOD_CONF = "camel.sink.endpoint.proxyAuthMethod";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_METHOD_DOC = "Authentication Method to Google GeoCoding server.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_METHOD_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_PASSWORD_CONF = "camel.sink.endpoint.proxyAuthPassword";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_PASSWORD_DOC = "Proxy Password to access GeoCoding server.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_USERNAME_CONF = "camel.sink.endpoint.proxyAuthUsername";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_USERNAME_DOC = "Proxy Username to access GeoCoding server.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_HOST_CONF = "camel.sink.endpoint.proxyHost";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_HOST_DOC = "Proxy Host to access GeoCoding server.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_PORT_CONF = "camel.sink.endpoint.proxyPort";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_PORT_DOC = "Proxy Port to access GeoCoding server.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_API_KEY_CONF = "camel.sink.endpoint.apiKey";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_API_KEY_DOC = "API Key to access Google. Mandatory for Google GeoCoding server.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_API_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_ID_CONF = "camel.sink.endpoint.clientId";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_ID_DOC = "Client ID to access Google GeoCoding server.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_ID_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_KEY_CONF = "camel.sink.endpoint.clientKey";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_KEY_DOC = "Client Key to access Google GeoCoding server.";
    public static final String CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_GEOCODER_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.geocoder.lazyStartProducer";
    public static final String CAMEL_SINK_GEOCODER_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_GEOCODER_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_GEOCODER_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.geocoder.autowiredEnabled";
    public static final String CAMEL_SINK_GEOCODER_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_GEOCODER_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SINK_GEOCODER_COMPONENT_GEO_API_CONTEXT_CONF = "camel.component.geocoder.geoApiContext";
    public static final String CAMEL_SINK_GEOCODER_COMPONENT_GEO_API_CONTEXT_DOC = "Configuration for Google maps API";
    public static final String CAMEL_SINK_GEOCODER_COMPONENT_GEO_API_CONTEXT_DEFAULT = null;

    public CamelGeocoderSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelGeocoderSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_GEOCODER_PATH_ADDRESS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_PATH_ADDRESS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_PATH_ADDRESS_DOC);
        conf.define(CAMEL_SINK_GEOCODER_PATH_LATLNG_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_PATH_LATLNG_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_PATH_LATLNG_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_HEADERS_ONLY_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_GEOCODER_ENDPOINT_HEADERS_ONLY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_HEADERS_ONLY_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_LANGUAGE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_LANGUAGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_LANGUAGE_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_GEOCODER_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_SERVER_URL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_SERVER_URL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_SERVER_URL_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_TYPE_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_DOMAIN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_DOMAIN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_DOMAIN_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_HOST_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_METHOD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_METHOD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_METHOD_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_PASSWORD_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_AUTH_USERNAME_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_HOST_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_PROXY_PORT_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_API_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_API_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_API_KEY_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_ID_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_ID_DOC);
        conf.define(CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_ENDPOINT_CLIENT_KEY_DOC);
        conf.define(CAMEL_SINK_GEOCODER_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_GEOCODER_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_GEOCODER_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_GEOCODER_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SINK_GEOCODER_COMPONENT_GEO_API_CONTEXT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_GEOCODER_COMPONENT_GEO_API_CONTEXT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_GEOCODER_COMPONENT_GEO_API_CONTEXT_DOC);
        return conf;
    }
}