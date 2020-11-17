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
package org.apache.camel.kafkaconnector;

import java.util.Map;

import org.apache.camel.LoggingLevel;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

public class CamelSinkConnectorConfig extends CamelConnectorConfig {
    public static final String CAMEL_SINK_MARSHAL_DEFAULT = null;
    public static final String CAMEL_SINK_MARSHAL_CONF = "camel.sink.marshal";
    public static final String CAMEL_SINK_MARSHAL_DOC = "The camel dataformat name to use to marshal data to the destination";

    public static final String CAMEL_SINK_UNMARSHAL_DEFAULT = null;
    public static final String CAMEL_SINK_UNMARSHAL_CONF = "camel.sink.unmarshal";
    public static final String CAMEL_SINK_UNMARSHAL_DOC = "The camel dataformat name to use to unmarshal data from the topic";

    public static final String CAMEL_SINK_COMPONENT_DEFAULT = null;
    public static final String CAMEL_SINK_COMPONENT_CONF = "camel.sink.component";

    public static final String CAMEL_SINK_URL_DEFAULT = null;
    public static final String CAMEL_SINK_URL_CONF = "camel.sink.url";

    public static final String CAMEL_SINK_COMPONENT_DOC = "The camel component to use. This is normally set by default for you. It is ignored if " + CAMEL_SINK_URL_CONF + " is set.";
    public static final String CAMEL_SINK_URL_DOC = "The camel url to configure the destination. If this is set " + CAMEL_SINK_COMPONENT_CONF
            + " and all the properties starting with " + CamelSinkTask.getCamelSinkEndpointConfigPrefix() + ".<" + CAMEL_SINK_COMPONENT_CONF + " value> are ignored.";

    public static final String CAMEL_SINK_CONTENT_LOG_LEVEL_DEFAULT = LoggingLevel.OFF.toString();
    public static final String CAMEL_SINK_CONTENT_LOG_LEVEL_CONF = "camel.sink.contentLogLevel";
    public static final String CAMEL_SINK_CONTENT_LOG_LEVEL_DOC = "Log level for the record's content (default: " + CAMEL_SINK_CONTENT_LOG_LEVEL_DEFAULT + "). Valid values: TRACE, DEBUG, INFO, WARN, ERROR, OFF.";

    private static final ConfigDef CONFIG_DEF = new ConfigDef()
        .define(CAMEL_SINK_URL_CONF, Type.STRING, CAMEL_SINK_URL_DEFAULT, Importance.HIGH, CAMEL_SINK_URL_DOC)
        .define(CAMEL_SINK_MARSHAL_CONF, Type.STRING, CAMEL_SINK_MARSHAL_DEFAULT, Importance.HIGH, CAMEL_SINK_MARSHAL_DOC)
        .define(CAMEL_SINK_UNMARSHAL_CONF, Type.STRING, CAMEL_SINK_UNMARSHAL_DEFAULT, Importance.HIGH, CAMEL_SINK_UNMARSHAL_DOC)
        .define(CAMEL_SINK_COMPONENT_CONF, Type.STRING, CAMEL_SINK_COMPONENT_DEFAULT, Importance.HIGH, CAMEL_SINK_COMPONENT_DOC)
        .define(CAMEL_SINK_CONTENT_LOG_LEVEL_CONF, Type.STRING, CAMEL_SINK_CONTENT_LOG_LEVEL_DEFAULT, Importance.HIGH, CAMEL_SINK_CONTENT_LOG_LEVEL_DOC)
        .define(CAMEL_CONNECTOR_AGGREGATE_CONF, Type.STRING, CAMEL_CONNECTOR_AGGREGATE_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_AGGREGATE_DOC)
        .define(CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF, Type.INT, CAMEL_CONNECTOR_AGGREGATE_SIZE_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_AGGREGATE_SIZE_DOC)
        .define(CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_CONF, Type.LONG, CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_DOC)
        .define(CAMEL_CONNECTOR_ERROR_HANDLER_CONF, Type.STRING, CAMEL_CONNECTOR_ERROR_HANDLER_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_ERROR_HANDLER_DOC)
        .define(CAMEL_CONNECTOR_ERROR_HANDLER_MAXIMUM_REDELIVERIES_CONF, Type.INT, CAMEL_CONNECTOR_ERROR_HANDLER_MAXIMUM_REDELIVERIES_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_ERROR_HANDLER_MAXIMUM_REDELIVERIES_DOC)
        .define(CAMEL_CONNECTOR_ERROR_HANDLER_REDELIVERY_DELAY_CONF, Type.LONG, CAMEL_CONNECTOR_ERROR_HANDLER_REDELIVERY_DELAY_DEFAULT, Importance.MEDIUM, CAMEL_CONNECTOR_ERROR_HANDLER_REDELIVERY_DELAY_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_CONF, Type.BOOLEAN, CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF, Type.STRING, CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_CONF, Type.STRING, CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_DOC)
        .define(CAMEL_CONNECTOR_IDEMPOTENCY_MEMORY_DIMENSION_CONF, Type.INT, CAMEL_CONNECTOR_IDEMPOTENCY_MEMORY_DIMENSION_DEFAULT, Importance.LOW, CAMEL_CONNECTOR_IDEMPOTENCY_MEMORY_DIMENSION_DOC);
    
    public CamelSinkConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(CONFIG_DEF, parsedConfig);
    }

    public static ConfigDef conf() {
        return CONFIG_DEF;
    }

}
