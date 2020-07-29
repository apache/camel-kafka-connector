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
package org.apache.camel.kafkaconnector.kubernetesjob;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelKubernetesjobSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_KUBERNETESJOB_PATH_MASTER_URL_CONF = "camel.source.path.masterUrl";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_PATH_MASTER_URL_DOC = "Kubernetes Master url";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_PATH_MASTER_URL_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_API_VERSION_CONF = "camel.source.endpoint.apiVersion";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_API_VERSION_DOC = "The Kubernetes API Version to use";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_API_VERSION_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_DNS_DOMAIN_CONF = "camel.source.endpoint.dnsDomain";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_DNS_DOMAIN_DOC = "The dns domain, used for ServiceCall EIP";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_DNS_DOMAIN_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_KUBERNETES_CLIENT_CONF = "camel.source.endpoint.kubernetesClient";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_KUBERNETES_CLIENT_DOC = "Default KubernetesClient to use if provided";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_KUBERNETES_CLIENT_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_NAME_CONF = "camel.source.endpoint.portName";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_NAME_DOC = "The port name, used for ServiceCall EIP";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_PROTOCOL_CONF = "camel.source.endpoint.portProtocol";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_PROTOCOL_DOC = "The port protocol, used for ServiceCall EIP";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_PROTOCOL_DEFAULT = "tcp";
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_KEY_CONF = "camel.source.endpoint.labelKey";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_KEY_DOC = "The Consumer Label key when watching at some resources";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_KEY_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_VALUE_CONF = "camel.source.endpoint.labelValue";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_VALUE_DOC = "The Consumer Label value when watching at some resources";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_VALUE_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_NAMESPACE_CONF = "camel.source.endpoint.namespace";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_NAMESPACE_DOC = "The namespace";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_NAMESPACE_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_POOL_SIZE_CONF = "camel.source.endpoint.poolSize";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_POOL_SIZE_DOC = "The Consumer pool size";
    private static final Integer CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_POOL_SIZE_DEFAULT = 1;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_RESOURCE_NAME_CONF = "camel.source.endpoint.resourceName";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_RESOURCE_NAME_DOC = "The Consumer Resource Name we would like to watch";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_RESOURCE_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CONNECTION_TIMEOUT_CONF = "camel.source.endpoint.connectionTimeout";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CONNECTION_TIMEOUT_DOC = "Connection timeout in milliseconds to use when making requests to the Kubernetes API server.";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CONNECTION_TIMEOUT_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_DATA_CONF = "camel.source.endpoint.caCertData";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_DATA_DOC = "The CA Cert Data";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_DATA_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_FILE_CONF = "camel.source.endpoint.caCertFile";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_FILE_DOC = "The CA Cert File";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_FILE_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_DATA_CONF = "camel.source.endpoint.clientCertData";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_DATA_DOC = "The Client Cert Data";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_DATA_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_FILE_CONF = "camel.source.endpoint.clientCertFile";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_FILE_DOC = "The Client Cert File";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_FILE_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_ALGO_CONF = "camel.source.endpoint.clientKeyAlgo";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_ALGO_DOC = "The Key Algorithm used by the client";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_ALGO_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_DATA_CONF = "camel.source.endpoint.clientKeyData";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_DATA_DOC = "The Client Key data";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_DATA_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_FILE_CONF = "camel.source.endpoint.clientKeyFile";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_FILE_DOC = "The Client Key file";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_FILE_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_PASSPHRASE_CONF = "camel.source.endpoint.clientKeyPassphrase";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_PASSPHRASE_DOC = "The Client Key Passphrase";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_PASSPHRASE_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_OAUTH_TOKEN_CONF = "camel.source.endpoint.oauthToken";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_OAUTH_TOKEN_DOC = "The Auth Token";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_OAUTH_TOKEN_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PASSWORD_CONF = "camel.source.endpoint.password";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PASSWORD_DOC = "Password to connect to Kubernetes";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_TRUST_CERTS_CONF = "camel.source.endpoint.trustCerts";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_TRUST_CERTS_DOC = "Define if the certs we used are trusted anyway or not";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_TRUST_CERTS_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_USERNAME_CONF = "camel.source.endpoint.username";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_USERNAME_DOC = "Username to connect to Kubernetes";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_USERNAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.kubernetes-job.bridgeErrorHandler";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    private static final Boolean CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.kubernetes-job.basicPropertyBinding";
    private static final String CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelKubernetesjobSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelKubernetesjobSourceConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_PATH_MASTER_URL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_PATH_MASTER_URL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_KUBERNETESJOB_PATH_MASTER_URL_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_API_VERSION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_API_VERSION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_API_VERSION_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_DNS_DOMAIN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_DNS_DOMAIN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_DNS_DOMAIN_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_KUBERNETES_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_KUBERNETES_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_KUBERNETES_CLIENT_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_NAME_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PORT_PROTOCOL_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_KEY_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_VALUE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_VALUE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_LABEL_VALUE_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_NAMESPACE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_NAMESPACE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_NAMESPACE_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_POOL_SIZE_CONF, ConfigDef.Type.INT, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_POOL_SIZE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_POOL_SIZE_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_RESOURCE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_RESOURCE_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_RESOURCE_NAME_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CONNECTION_TIMEOUT_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CONNECTION_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CONNECTION_TIMEOUT_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_DATA_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_DATA_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_DATA_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_FILE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_FILE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CA_CERT_FILE_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_DATA_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_DATA_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_DATA_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_FILE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_FILE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_CERT_FILE_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_ALGO_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_ALGO_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_ALGO_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_DATA_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_DATA_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_DATA_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_FILE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_FILE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_FILE_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_PASSPHRASE_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_PASSPHRASE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_CLIENT_KEY_PASSPHRASE_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_OAUTH_TOKEN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_OAUTH_TOKEN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_OAUTH_TOKEN_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_PASSWORD_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_TRUST_CERTS_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_TRUST_CERTS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_TRUST_CERTS_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_ENDPOINT_USERNAME_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_KUBERNETESJOB_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}