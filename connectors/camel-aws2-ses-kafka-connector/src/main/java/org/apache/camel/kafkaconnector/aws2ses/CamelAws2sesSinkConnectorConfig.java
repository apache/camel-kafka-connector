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
package org.apache.camel.kafkaconnector.aws2ses;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelAws2sesSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_AWS2SES_PATH_FROM_CONF = "camel.sink.path.from";
    public static final String CAMEL_SINK_AWS2SES_PATH_FROM_DOC = "The sender's email address.";
    public static final String CAMEL_SINK_AWS2SES_PATH_FROM_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_AMAZON_SESCLIENT_CONF = "camel.sink.endpoint.amazonSESClient";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_AMAZON_SESCLIENT_DOC = "To use the AmazonSimpleEmailService as the client";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_AMAZON_SESCLIENT_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_AWS2SES_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_HOST_CONF = "camel.sink.endpoint.proxyHost";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_HOST_DOC = "To define a proxy host when instantiating the SES client";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PORT_CONF = "camel.sink.endpoint.proxyPort";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PORT_DOC = "To define a proxy port when instantiating the SES client";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PROTOCOL_CONF = "camel.sink.endpoint.proxyProtocol";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PROTOCOL_DOC = "To define a proxy protocol when instantiating the SES client One of: [HTTP] [HTTPS]";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PROTOCOL_DEFAULT = "HTTPS";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_REGION_CONF = "camel.sink.endpoint.region";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_REGION_DOC = "The region in which SES client needs to work. When using this parameter, the configuration will expect the lowercase name of the region (for example ap-east-1) You'll need to use the name Region.EU_WEST_1.id()";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_REGION_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_REPLY_TO_ADDRESSES_CONF = "camel.sink.endpoint.replyToAddresses";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_REPLY_TO_ADDRESSES_DOC = "List of reply-to email address(es) for the message, override it using 'CamelAwsSesReplyToAddresses' header.";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_REPLY_TO_ADDRESSES_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_RETURN_PATH_CONF = "camel.sink.endpoint.returnPath";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_RETURN_PATH_DOC = "The email address to which bounce notifications are to be forwarded, override it using 'CamelAwsSesReturnPath' header.";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_RETURN_PATH_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_SUBJECT_CONF = "camel.sink.endpoint.subject";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_SUBJECT_DOC = "The subject which is used if the message header 'CamelAwsSesSubject' is not present.";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_SUBJECT_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_TO_CONF = "camel.sink.endpoint.to";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_TO_DOC = "List of destination email address. Can be overriden with 'CamelAwsSesTo' header.";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_TO_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_TRUST_ALL_CERTIFICATES_CONF = "camel.sink.endpoint.trustAllCertificates";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_TRUST_ALL_CERTIFICATES_DOC = "If we want to trust all certificates in case of overriding the endpoint";
    public static final Boolean CAMEL_SINK_AWS2SES_ENDPOINT_TRUST_ALL_CERTIFICATES_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_ACCESS_KEY_CONF = "camel.sink.endpoint.accessKey";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_ACCESS_KEY_DOC = "Amazon AWS Access Key";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_SECRET_KEY_CONF = "camel.sink.endpoint.secretKey";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_SECRET_KEY_DOC = "Amazon AWS Secret Key";
    public static final String CAMEL_SINK_AWS2SES_ENDPOINT_SECRET_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_AMAZON_SESCLIENT_CONF = "camel.component.aws2-ses.amazonSESClient";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_AMAZON_SESCLIENT_DOC = "To use the AmazonSimpleEmailService as the client";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_AMAZON_SESCLIENT_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_CONFIGURATION_CONF = "camel.component.aws2-ses.configuration";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_CONFIGURATION_DOC = "component configuration";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_CONFIGURATION_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.aws2-ses.lazyStartProducer";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_AWS2SES_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_PROXY_HOST_CONF = "camel.component.aws2-ses.proxyHost";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_PROXY_HOST_DOC = "To define a proxy host when instantiating the SES client";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_PROXY_HOST_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PORT_CONF = "camel.component.aws2-ses.proxyPort";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PORT_DOC = "To define a proxy port when instantiating the SES client";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PORT_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PROTOCOL_CONF = "camel.component.aws2-ses.proxyProtocol";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PROTOCOL_DOC = "To define a proxy protocol when instantiating the SES client One of: [HTTP] [HTTPS]";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PROTOCOL_DEFAULT = "HTTPS";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_REGION_CONF = "camel.component.aws2-ses.region";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_REGION_DOC = "The region in which SES client needs to work. When using this parameter, the configuration will expect the lowercase name of the region (for example ap-east-1) You'll need to use the name Region.EU_WEST_1.id()";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_REGION_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_REPLY_TO_ADDRESSES_CONF = "camel.component.aws2-ses.replyToAddresses";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_REPLY_TO_ADDRESSES_DOC = "List of reply-to email address(es) for the message, override it using 'CamelAwsSesReplyToAddresses' header.";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_REPLY_TO_ADDRESSES_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_RETURN_PATH_CONF = "camel.component.aws2-ses.returnPath";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_RETURN_PATH_DOC = "The email address to which bounce notifications are to be forwarded, override it using 'CamelAwsSesReturnPath' header.";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_RETURN_PATH_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_SUBJECT_CONF = "camel.component.aws2-ses.subject";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_SUBJECT_DOC = "The subject which is used if the message header 'CamelAwsSesSubject' is not present.";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_SUBJECT_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_TO_CONF = "camel.component.aws2-ses.to";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_TO_DOC = "List of destination email address. Can be overriden with 'CamelAwsSesTo' header.";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_TO_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_TRUST_ALL_CERTIFICATES_CONF = "camel.component.aws2-ses.trustAllCertificates";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_TRUST_ALL_CERTIFICATES_DOC = "If we want to trust all certificates in case of overriding the endpoint";
    public static final Boolean CAMEL_SINK_AWS2SES_COMPONENT_TRUST_ALL_CERTIFICATES_DEFAULT = false;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_AUTOWIRED_ENABLED_CONF = "camel.component.aws2-ses.autowiredEnabled";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_AUTOWIRED_ENABLED_DOC = "Whether autowiring is enabled. This is used for automatic autowiring options (the option must be marked as autowired) by looking up in the registry to find if there is a single instance of matching type, which then gets configured on the component. This can be used for automatic configuring JDBC data sources, JMS connection factories, AWS Clients, etc.";
    public static final Boolean CAMEL_SINK_AWS2SES_COMPONENT_AUTOWIRED_ENABLED_DEFAULT = true;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_ACCESS_KEY_CONF = "camel.component.aws2-ses.accessKey";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_ACCESS_KEY_DOC = "Amazon AWS Access Key";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_ACCESS_KEY_DEFAULT = null;
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_SECRET_KEY_CONF = "camel.component.aws2-ses.secretKey";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_SECRET_KEY_DOC = "Amazon AWS Secret Key";
    public static final String CAMEL_SINK_AWS2SES_COMPONENT_SECRET_KEY_DEFAULT = null;

    public CamelAws2sesSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelAws2sesSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_AWS2SES_PATH_FROM_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_PATH_FROM_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_AWS2SES_PATH_FROM_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_AMAZON_SESCLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_ENDPOINT_AMAZON_SESCLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_AMAZON_SESCLIENT_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SES_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_HOST_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PORT_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_PROXY_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_ENDPOINT_REGION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_REGION_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_REPLY_TO_ADDRESSES_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_ENDPOINT_REPLY_TO_ADDRESSES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_REPLY_TO_ADDRESSES_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_RETURN_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_ENDPOINT_RETURN_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_RETURN_PATH_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_SUBJECT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_ENDPOINT_SUBJECT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_SUBJECT_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_TO_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_ENDPOINT_TO_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_TO_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_TRUST_ALL_CERTIFICATES_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SES_ENDPOINT_TRUST_ALL_CERTIFICATES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_TRUST_ALL_CERTIFICATES_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_ACCESS_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_ENDPOINT_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_ACCESS_KEY_DOC);
        conf.define(CAMEL_SINK_AWS2SES_ENDPOINT_SECRET_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_ENDPOINT_SECRET_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_ENDPOINT_SECRET_KEY_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_AMAZON_SESCLIENT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_AMAZON_SESCLIENT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_AMAZON_SESCLIENT_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_CONFIGURATION_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SES_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_PROXY_HOST_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_PROXY_HOST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_PROXY_HOST_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PORT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PORT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PORT_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PROTOCOL_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PROTOCOL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_PROXY_PROTOCOL_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_REGION_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_REGION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_REGION_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_REPLY_TO_ADDRESSES_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_REPLY_TO_ADDRESSES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_REPLY_TO_ADDRESSES_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_RETURN_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_RETURN_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_RETURN_PATH_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_SUBJECT_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_SUBJECT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_SUBJECT_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_TO_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_TO_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_TO_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_TRUST_ALL_CERTIFICATES_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SES_COMPONENT_TRUST_ALL_CERTIFICATES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_TRUST_ALL_CERTIFICATES_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_AUTOWIRED_ENABLED_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_AWS2SES_COMPONENT_AUTOWIRED_ENABLED_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_AUTOWIRED_ENABLED_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_ACCESS_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_ACCESS_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_ACCESS_KEY_DOC);
        conf.define(CAMEL_SINK_AWS2SES_COMPONENT_SECRET_KEY_CONF, ConfigDef.Type.STRING, CAMEL_SINK_AWS2SES_COMPONENT_SECRET_KEY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_AWS2SES_COMPONENT_SECRET_KEY_DOC);
        return conf;
    }
}