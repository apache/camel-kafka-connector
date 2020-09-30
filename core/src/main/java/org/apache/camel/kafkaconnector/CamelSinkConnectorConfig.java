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
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

public class CamelSinkConnectorConfig extends AbstractConfig {
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

    public static final String CAMEL_SINK_AGGREGATE_DEFAULT = null;
    public static final String CAMEL_SINK_AGGREGATE_CONF = "camel.beans.aggregate";
    public static final String CAMEL_SINK_AGGREGATE_DOC = "A reference to an aggregate bean, in the form of #class:";    

    public static final Integer CAMEL_SINK_AGGREGATE_SIZE_DEFAULT = 10;
    public static final String CAMEL_SINK_AGGREGATE_SIZE_CONF = "camel.aggregation.size";
    public static final String CAMEL_SINK_AGGREGATE_SIZE_DOC = "The size of the aggregation, to be used in combination with camel.beans.aggregate";
    
    public static final Long CAMEL_SINK_AGGREGATE_TIMEOUT_DEFAULT = 500L;
    public static final String CAMEL_SINK_AGGREGATE_TIMEOUT_CONF = "camel.aggregation.timeout";
    public static final String CAMEL_SINK_AGGREGATE_TIMEOUT_DOC = "The timeout of the aggregation, to be used in combination with camel.beans.aggregate";    

    private static final ConfigDef CONFIG_DEF = new ConfigDef()
        .define(CAMEL_SINK_URL_CONF, Type.STRING, CAMEL_SINK_URL_DEFAULT, Importance.HIGH, CAMEL_SINK_URL_DOC)
        .define(CAMEL_SINK_MARSHAL_CONF, Type.STRING, CAMEL_SINK_MARSHAL_DEFAULT, Importance.HIGH, CAMEL_SINK_MARSHAL_DOC)
        .define(CAMEL_SINK_UNMARSHAL_CONF, Type.STRING, CAMEL_SINK_UNMARSHAL_DEFAULT, Importance.HIGH, CAMEL_SINK_UNMARSHAL_DOC)
        .define(CAMEL_SINK_COMPONENT_CONF, Type.STRING, CAMEL_SINK_COMPONENT_DEFAULT, Importance.HIGH, CAMEL_SINK_COMPONENT_DOC)
        .define(CAMEL_SINK_CONTENT_LOG_LEVEL_CONF, Type.STRING, CAMEL_SINK_CONTENT_LOG_LEVEL_DEFAULT, Importance.HIGH, CAMEL_SINK_CONTENT_LOG_LEVEL_DOC)
        .define(CAMEL_SINK_AGGREGATE_CONF, Type.STRING, CAMEL_SINK_AGGREGATE_DEFAULT, Importance.MEDIUM, CAMEL_SINK_AGGREGATE_DOC)
        .define(CAMEL_SINK_AGGREGATE_SIZE_CONF, Type.INT, CAMEL_SINK_AGGREGATE_SIZE_DEFAULT, Importance.MEDIUM, CAMEL_SINK_AGGREGATE_SIZE_DOC)
        .define(CAMEL_SINK_AGGREGATE_TIMEOUT_CONF, Type.LONG, CAMEL_SINK_AGGREGATE_TIMEOUT_DEFAULT, Importance.MEDIUM, CAMEL_SINK_AGGREGATE_TIMEOUT_DOC);

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
