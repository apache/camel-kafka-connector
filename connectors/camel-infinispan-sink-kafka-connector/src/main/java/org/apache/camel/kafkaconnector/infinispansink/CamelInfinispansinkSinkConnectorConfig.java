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
 */package org.apache.camel.kafkaconnector.infinispansink;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelInfinispansinkSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_CACHE_NAME_CONF = "camel.kamelet.infinispan-sink.cacheName";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_CACHE_NAME_DOC = "The name of the Infinispan cache to use";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_CACHE_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_HOSTS_CONF = "camel.kamelet.infinispan-sink.hosts";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_HOSTS_DOC = "Specifies the host of the cache on Infinispan instance";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_HOSTS_DEFAULT = null;
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_SECURE_CONF = "camel.kamelet.infinispan-sink.secure";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_SECURE_DOC = "If the Infinispan instance is secured or not";
    public static final Boolean CAMEL_SINK_INFINISPANSINK_KAMELET_SECURE_DEFAULT = true;
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_USERNAME_CONF = "camel.kamelet.infinispan-sink.username";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_USERNAME_DOC = "Username to connect to Infinispan.";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_USERNAME_DEFAULT = null;
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_PASSWORD_CONF = "camel.kamelet.infinispan-sink.password";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_PASSWORD_DOC = "Password to connect to Infinispan.";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_PASSWORD_DEFAULT = null;
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_SASL_MECHANISM_CONF = "camel.kamelet.infinispan-sink.saslMechanism";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_SASL_MECHANISM_DOC = "The SASL Mechanism to use";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_SASL_MECHANISM_DEFAULT = "DIGEST-MD5";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_REALM_CONF = "camel.kamelet.infinispan-sink.securityRealm";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_REALM_DOC = "Define the security realm to access the infinispan instance";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_REALM_DEFAULT = "default";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_SERVER_NAME_CONF = "camel.kamelet.infinispan-sink.securityServerName";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_SERVER_NAME_DOC = "Define the security server name to access the infinispan instance";
    public static final String CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_SERVER_NAME_DEFAULT = "infinispan";

    public CamelInfinispansinkSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelInfinispansinkSinkConnectorConfig(
            Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_INFINISPANSINK_KAMELET_CACHE_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_INFINISPANSINK_KAMELET_CACHE_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_INFINISPANSINK_KAMELET_CACHE_NAME_DOC);
        conf.define(CAMEL_SINK_INFINISPANSINK_KAMELET_HOSTS_CONF, ConfigDef.Type.STRING, CAMEL_SINK_INFINISPANSINK_KAMELET_HOSTS_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_INFINISPANSINK_KAMELET_HOSTS_DOC);
        conf.define(CAMEL_SINK_INFINISPANSINK_KAMELET_SECURE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_INFINISPANSINK_KAMELET_SECURE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_INFINISPANSINK_KAMELET_SECURE_DOC);
        conf.define(CAMEL_SINK_INFINISPANSINK_KAMELET_USERNAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_INFINISPANSINK_KAMELET_USERNAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_INFINISPANSINK_KAMELET_USERNAME_DOC);
        conf.define(CAMEL_SINK_INFINISPANSINK_KAMELET_PASSWORD_CONF, ConfigDef.Type.PASSWORD, CAMEL_SINK_INFINISPANSINK_KAMELET_PASSWORD_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_INFINISPANSINK_KAMELET_PASSWORD_DOC);
        conf.define(CAMEL_SINK_INFINISPANSINK_KAMELET_SASL_MECHANISM_CONF, ConfigDef.Type.STRING, CAMEL_SINK_INFINISPANSINK_KAMELET_SASL_MECHANISM_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_INFINISPANSINK_KAMELET_SASL_MECHANISM_DOC);
        conf.define(CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_REALM_CONF, ConfigDef.Type.STRING, CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_REALM_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_REALM_DOC);
        conf.define(CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_SERVER_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_SERVER_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_INFINISPANSINK_KAMELET_SECURITY_SERVER_NAME_DOC);
        return conf;
    }
}