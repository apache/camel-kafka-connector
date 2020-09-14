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
package org.apache.camel.kafkaconnector.webhook;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelWebhookSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_WEBHOOK_PATH_ENDPOINT_URI_CONF = "camel.source.path.endpointUri";
    public static final String CAMEL_SOURCE_WEBHOOK_PATH_ENDPOINT_URI_DOC = "The delegate uri. Must belong to a component that supports webhooks.";
    public static final String CAMEL_SOURCE_WEBHOOK_PATH_ENDPOINT_URI_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_WEBHOOK_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_AUTO_REGISTER_CONF = "camel.source.endpoint.webhookAutoRegister";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_AUTO_REGISTER_DOC = "Automatically register the webhook at startup and unregister it on shutdown.";
    public static final Boolean CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_AUTO_REGISTER_DEFAULT = true;
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_BASE_PATH_CONF = "camel.source.endpoint.webhookBasePath";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_BASE_PATH_DOC = "The first (base) path element where the webhook will be exposed. It's a good practice to set it to a random string, so that it cannot be guessed by unauthorized parties.";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_BASE_PATH_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_COMPONENT_NAME_CONF = "camel.source.endpoint.webhookComponentName";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_COMPONENT_NAME_DOC = "The Camel Rest component to use for the REST transport, such as netty-http.";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_COMPONENT_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_EXTERNAL_URL_CONF = "camel.source.endpoint.webhookExternalUrl";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_EXTERNAL_URL_DOC = "The URL of the current service as seen by the webhook provider";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_EXTERNAL_URL_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_PATH_CONF = "camel.source.endpoint.webhookPath";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_PATH_DOC = "The path where the webhook endpoint will be exposed (relative to basePath, if any)";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_PATH_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SOURCE_WEBHOOK_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    public static final String CAMEL_SOURCE_WEBHOOK_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SOURCE_WEBHOOK_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.webhook.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_WEBHOOK_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_AUTO_REGISTER_CONF = "camel.component.webhook.webhookAutoRegister";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_AUTO_REGISTER_DOC = "Automatically register the webhook at startup and unregister it on shutdown.";
    public static final Boolean CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_AUTO_REGISTER_DEFAULT = true;
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_BASE_PATH_CONF = "camel.component.webhook.webhookBasePath";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_BASE_PATH_DOC = "The first (base) path element where the webhook will be exposed. It's a good practice to set it to a random string, so that it cannot be guessed by unauthorized parties.";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_BASE_PATH_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_COMPONENT_NAME_CONF = "camel.component.webhook.webhookComponentName";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_COMPONENT_NAME_DOC = "The Camel Rest component to use for the REST transport, such as netty-http.";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_COMPONENT_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_EXTERNAL_URL_CONF = "camel.component.webhook.webhookExternalUrl";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_EXTERNAL_URL_DOC = "The URL of the current service as seen by the webhook provider";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_EXTERNAL_URL_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_PATH_CONF = "camel.component.webhook.webhookPath";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_PATH_DOC = "The path where the webhook endpoint will be exposed (relative to basePath, if any)";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_PATH_DEFAULT = null;
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.webhook.basicPropertyBinding";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SOURCE_WEBHOOK_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_CONFIGURATION_CONF = "camel.component.webhook.configuration";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_CONFIGURATION_DOC = "Set the default configuration for the webhook meta-component.";
    public static final String CAMEL_SOURCE_WEBHOOK_COMPONENT_CONFIGURATION_DEFAULT = null;

    public CamelWebhookSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelWebhookSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_WEBHOOK_PATH_ENDPOINT_URI_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_PATH_ENDPOINT_URI_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_WEBHOOK_PATH_ENDPOINT_URI_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBHOOK_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_AUTO_REGISTER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_AUTO_REGISTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_AUTO_REGISTER_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_BASE_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_BASE_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_BASE_PATH_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_COMPONENT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_COMPONENT_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_COMPONENT_NAME_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_EXTERNAL_URL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_EXTERNAL_URL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_EXTERNAL_URL_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_ENDPOINT_WEBHOOK_PATH_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBHOOK_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBHOOK_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBHOOK_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_AUTO_REGISTER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_AUTO_REGISTER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_AUTO_REGISTER_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_BASE_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_BASE_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_BASE_PATH_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_COMPONENT_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_COMPONENT_NAME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_COMPONENT_NAME_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_EXTERNAL_URL_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_EXTERNAL_URL_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_EXTERNAL_URL_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_PATH_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_PATH_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_COMPONENT_WEBHOOK_PATH_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_WEBHOOK_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SOURCE_WEBHOOK_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_WEBHOOK_COMPONENT_CONFIGURATION_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_WEBHOOK_COMPONENT_CONFIGURATION_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_WEBHOOK_COMPONENT_CONFIGURATION_DOC);
        return conf;
    }
}