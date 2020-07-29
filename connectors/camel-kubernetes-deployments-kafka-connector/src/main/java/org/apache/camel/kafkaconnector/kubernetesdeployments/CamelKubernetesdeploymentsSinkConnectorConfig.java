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
package org.apache.camel.kafkaconnector.kubernetesdeployments;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelKubernetesdeploymentsSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_PATH_MASTER_URL_CONF = "camel.sink.path.masterUrl";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_PATH_MASTER_URL_DOC = "Kubernetes Master url";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_PATH_MASTER_URL_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_API_VERSION_CONF = "camel.sink.endpoint.apiVersion";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_API_VERSION_DOC = "The Kubernetes API Version to use";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_API_VERSION_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_DNS_DOMAIN_CONF = "camel.sink.endpoint.dnsDomain";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_DNS_DOMAIN_DOC = "The dns domain, used for ServiceCall EIP";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_DNS_DOMAIN_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_KUBERNETES_CLIENT_CONF = "camel.sink.endpoint.kubernetesClient";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_KUBERNETES_CLIENT_DOC = "Default KubernetesClient to use if provided";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_KUBERNETES_CLIENT_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_NAME_CONF = "camel.sink.endpoint.portName";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_NAME_DOC = "The port name, used for ServiceCall EIP";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_PROTOCOL_CONF = "camel.sink.endpoint.portProtocol";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_PROTOCOL_DOC = "The port protocol, used for ServiceCall EIP";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_PROTOCOL_DEFAULT = "tcp";
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OPERATION_CONF = "camel.sink.endpoint.operation";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OPERATION_DOC = "Producer operation to do on Kubernetes";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OPERATION_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CONNECTION_TIMEOUT_CONF = "camel.sink.endpoint.connectionTimeout";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CONNECTION_TIMEOUT_DOC = "Connection timeout in milliseconds to use when making requests to the Kubernetes API server.";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CONNECTION_TIMEOUT_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_DATA_CONF = "camel.sink.endpoint.caCertData";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_DATA_DOC = "The CA Cert Data";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_DATA_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_FILE_CONF = "camel.sink.endpoint.caCertFile";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_FILE_DOC = "The CA Cert File";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_FILE_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_DATA_CONF = "camel.sink.endpoint.clientCertData";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_DATA_DOC = "The Client Cert Data";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_DATA_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_FILE_CONF = "camel.sink.endpoint.clientCertFile";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_FILE_DOC = "The Client Cert File";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_FILE_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_ALGO_CONF = "camel.sink.endpoint.clientKeyAlgo";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_ALGO_DOC = "The Key Algorithm used by the client";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_ALGO_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_DATA_CONF = "camel.sink.endpoint.clientKeyData";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_DATA_DOC = "The Client Key data";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_DATA_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_FILE_CONF = "camel.sink.endpoint.clientKeyFile";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_FILE_DOC = "The Client Key file";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_FILE_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_PASSPHRASE_CONF = "camel.sink.endpoint.clientKeyPassphrase";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_PASSPHRASE_DOC = "The Client Key Passphrase";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_PASSPHRASE_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OAUTH_TOKEN_CONF = "camel.sink.endpoint.oauthToken";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OAUTH_TOKEN_DOC = "The Auth Token";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OAUTH_TOKEN_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PASSWORD_CONF = "camel.sink.endpoint.password";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PASSWORD_DOC = "Password to connect to Kubernetes";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_TRUST_CERTS_CONF = "camel.sink.endpoint.trustCerts";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_TRUST_CERTS_DOC = "Define if the certs we used are trusted anyway or not";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_TRUST_CERTS_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_USERNAME_CONF = "camel.sink.endpoint.username";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_USERNAME_DOC = "Username to connect to Kubernetes";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.kubernetes-deployments.lazyStartProducer";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.kubernetes-deployments.basicPropertyBinding";
    private static final String CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelKubernetesdeploymentsSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelKubernetesdeploymentsSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_PATH_MASTER_URL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_PATH_MASTER_URL_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_KUBERNETESDEPLOYMENTS_PATH_MASTER_URL_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_API_VERSION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_API_VERSION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_API_VERSION_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_DNS_DOMAIN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_DNS_DOMAIN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_DNS_DOMAIN_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_KUBERNETES_CLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_KUBERNETES_CLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_KUBERNETES_CLIENT_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_NAME_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PORT_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OPERATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OPERATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OPERATION_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CONNECTION_TIMEOUT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CONNECTION_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CONNECTION_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_DATA_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_DATA_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_DATA_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_FILE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_FILE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CA_CERT_FILE_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_DATA_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_DATA_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_DATA_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_FILE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_FILE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_CERT_FILE_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_ALGO_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_ALGO_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_ALGO_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_DATA_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_DATA_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_DATA_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_FILE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_FILE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_FILE_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_PASSPHRASE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_PASSPHRASE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_CLIENT_KEY_PASSPHRASE_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OAUTH_TOKEN_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OAUTH_TOKEN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_OAUTH_TOKEN_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PASSWORD_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PASSWORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_PASSWORD_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_TRUST_CERTS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_TRUST_CERTS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_TRUST_CERTS_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_USERNAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_ENDPOINT_USERNAME_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_KUBERNETESDEPLOYMENTS_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}