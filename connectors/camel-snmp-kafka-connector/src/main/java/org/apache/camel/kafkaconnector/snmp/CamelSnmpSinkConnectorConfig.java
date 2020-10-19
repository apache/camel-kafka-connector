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
package org.apache.camel.kafkaconnector.snmp;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelSnmpSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_SNMP_PATH_HOST_CONF = "camel.sink.path.host";
    public static final String CAMEL_SINK_SNMP_PATH_HOST_DOC = "Hostname of the SNMP enabled device";
    public static final String CAMEL_SINK_SNMP_PATH_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_SNMP_PATH_PORT_CONF = "camel.sink.path.port";
    public static final String CAMEL_SINK_SNMP_PATH_PORT_DOC = "Port number of the SNMP enabled device";
    public static final String CAMEL_SINK_SNMP_PATH_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_OIDS_CONF = "camel.sink.endpoint.oids";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_OIDS_DOC = "Defines which values you are interested in. Please have a look at the Wikipedia to get a better understanding. You may provide a single OID or a coma separated list of OIDs. Example: oids=1.3.6.1.2.1.1.3.0,1.3.6.1.2.1.25.3.2.1.5.1,1.3.6.1.2.1.25.3.5.1.1.1,1.3.6.1.2.1.43.5.1.1.11.1";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_OIDS_DEFAULT = null;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_PROTOCOL_CONF = "camel.sink.endpoint.protocol";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_PROTOCOL_DOC = "Here you can select which protocol to use. You can use either udp or tcp. One of: [tcp] [udp]";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_PROTOCOL_DEFAULT = "udp";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_RETRIES_CONF = "camel.sink.endpoint.retries";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_RETRIES_DOC = "Defines how often a retry is made before canceling the request.";
    public static final Integer CAMEL_SINK_SNMP_ENDPOINT_RETRIES_DEFAULT = 2;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SNMP_COMMUNITY_CONF = "camel.sink.endpoint.snmpCommunity";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SNMP_COMMUNITY_DOC = "Sets the community octet string for the snmp request.";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SNMP_COMMUNITY_DEFAULT = "public";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_ENGINE_ID_CONF = "camel.sink.endpoint.snmpContextEngineId";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_ENGINE_ID_DOC = "Sets the context engine ID field of the scoped PDU.";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_ENGINE_ID_DEFAULT = null;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_NAME_CONF = "camel.sink.endpoint.snmpContextName";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_NAME_DOC = "Sets the context name field of this scoped PDU.";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SNMP_VERSION_CONF = "camel.sink.endpoint.snmpVersion";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SNMP_VERSION_DOC = "Sets the snmp version for the request. The value 0 means SNMPv1, 1 means SNMPv2c, and the value 3 means SNMPv3 One of: [0] [1] [3]";
    public static final Integer CAMEL_SINK_SNMP_ENDPOINT_SNMP_VERSION_DEFAULT = 0;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_TIMEOUT_CONF = "camel.sink.endpoint.timeout";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_TIMEOUT_DOC = "Sets the timeout value for the request in millis.";
    public static final Integer CAMEL_SINK_SNMP_ENDPOINT_TIMEOUT_DEFAULT = 1500;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_TYPE_CONF = "camel.sink.endpoint.type";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_TYPE_DOC = "Which operation to perform such as poll, trap, etc. One of: [TRAP] [POLL] [GET_NEXT]";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_TYPE_DEFAULT = null;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_SNMP_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_SNMP_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_SNMP_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PASSPHRASE_CONF = "camel.sink.endpoint.authenticationPassphrase";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PASSPHRASE_DOC = "The authentication passphrase. If not null, authenticationProtocol must also be not null. RFC3414 11.2 requires passphrases to have a minimum length of 8 bytes. If the length of authenticationPassphrase is less than 8 bytes an IllegalArgumentException is thrown.";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PASSPHRASE_DEFAULT = null;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PROTOCOL_CONF = "camel.sink.endpoint.authenticationProtocol";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PROTOCOL_DOC = "Authentication protocol to use if security level is set to enable authentication The possible values are: MD5, SHA1 One of: [MD5] [SHA1]";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PROTOCOL_DEFAULT = null;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PASSPHRASE_CONF = "camel.sink.endpoint.privacyPassphrase";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PASSPHRASE_DOC = "The privacy passphrase. If not null, privacyProtocol must also be not null. RFC3414 11.2 requires passphrases to have a minimum length of 8 bytes. If the length of authenticationPassphrase is less than 8 bytes an IllegalArgumentException is thrown.";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PASSPHRASE_DEFAULT = null;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PROTOCOL_CONF = "camel.sink.endpoint.privacyProtocol";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PROTOCOL_DOC = "The privacy protocol ID to be associated with this user. If set to null, this user only supports unencrypted messages.";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PROTOCOL_DEFAULT = null;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SECURITY_LEVEL_CONF = "camel.sink.endpoint.securityLevel";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SECURITY_LEVEL_DOC = "Sets the security level for this target. The supplied security level must be supported by the security model dependent information associated with the security name set for this target. The value 1 means: No authentication and no encryption. Anyone can create and read messages with this security level The value 2 means: Authentication and no encryption. Only the one with the right authentication key can create messages with this security level, but anyone can read the contents of the message. The value 3 means: Authentication and encryption. Only the one with the right authentication key can create messages with this security level, and only the one with the right encryption/decryption key can read the contents of the message. One of: [1] [2] [3]";
    public static final Integer CAMEL_SINK_SNMP_ENDPOINT_SECURITY_LEVEL_DEFAULT = 3;
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SECURITY_NAME_CONF = "camel.sink.endpoint.securityName";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SECURITY_NAME_DOC = "Sets the security name to be used with this target.";
    public static final String CAMEL_SINK_SNMP_ENDPOINT_SECURITY_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_SNMP_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.snmp.lazyStartProducer";
    public static final String CAMEL_SINK_SNMP_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_SNMP_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_SNMP_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.snmp.basicPropertyBinding";
    public static final String CAMEL_SINK_SNMP_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_SNMP_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelSnmpSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSnmpSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_SNMP_PATH_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_PATH_HOST_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_SNMP_PATH_HOST_DOC);
        conf.define(CAMEL_SINK_SNMP_PATH_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_PATH_PORT_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_SNMP_PATH_PORT_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_OIDS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_ENDPOINT_OIDS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_OIDS_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_ENDPOINT_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_RETRIES_CONF, ConfigDef.Type.INT, CAMEL_SINK_SNMP_ENDPOINT_RETRIES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_RETRIES_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_SNMP_COMMUNITY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_ENDPOINT_SNMP_COMMUNITY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_SNMP_COMMUNITY_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_ENGINE_ID_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_ENGINE_ID_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_ENGINE_ID_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_SNMP_CONTEXT_NAME_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_SNMP_VERSION_CONF, ConfigDef.Type.INT, CAMEL_SINK_SNMP_ENDPOINT_SNMP_VERSION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_SNMP_VERSION_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_TIMEOUT_CONF, ConfigDef.Type.INT, CAMEL_SINK_SNMP_ENDPOINT_TIMEOUT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_TIMEOUT_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_ENDPOINT_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_TYPE_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SNMP_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SNMP_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SNMP_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PASSPHRASE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PASSPHRASE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PASSPHRASE_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_AUTHENTICATION_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PASSPHRASE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PASSPHRASE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PASSPHRASE_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_PRIVACY_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_SECURITY_LEVEL_CONF, ConfigDef.Type.INT, CAMEL_SINK_SNMP_ENDPOINT_SECURITY_LEVEL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_SECURITY_LEVEL_DOC);
        conf.define(CAMEL_SINK_SNMP_ENDPOINT_SECURITY_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_SNMP_ENDPOINT_SECURITY_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_ENDPOINT_SECURITY_NAME_DOC);
        conf.define(CAMEL_SINK_SNMP_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SNMP_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_SNMP_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_SNMP_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_SNMP_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SINK_SNMP_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}