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
package org.apache.camel.kafkaconnector.flatpack;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelFlatpackSinkConnectorConfig
        extends
            CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_FLATPACK_PATH_TYPE_CONF = "camel.sink.path.type";
    public static final String CAMEL_SINK_FLATPACK_PATH_TYPE_DOC = "Whether to use fixed or delimiter One of: [fixed] [delim]";
    public static final String CAMEL_SINK_FLATPACK_PATH_TYPE_DEFAULT = "delim";
    public static final String CAMEL_SINK_FLATPACK_PATH_RESOURCE_URI_CONF = "camel.sink.path.resourceUri";
    public static final String CAMEL_SINK_FLATPACK_PATH_RESOURCE_URI_DOC = "URL for loading the flatpack mapping file from classpath or file system";
    public static final String CAMEL_SINK_FLATPACK_PATH_RESOURCE_URI_DEFAULT = null;
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_ALLOW_SHORT_LINES_CONF = "camel.sink.endpoint.allowShortLines";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_ALLOW_SHORT_LINES_DOC = "Allows for lines to be shorter than expected and ignores the extra characters";
    public static final Boolean CAMEL_SINK_FLATPACK_ENDPOINT_ALLOW_SHORT_LINES_DEFAULT = false;
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_DELIMITER_CONF = "camel.sink.endpoint.delimiter";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_DELIMITER_DOC = "The default character delimiter for delimited files.";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_DELIMITER_DEFAULT = ",";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_EXTRA_COLUMNS_CONF = "camel.sink.endpoint.ignoreExtraColumns";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_EXTRA_COLUMNS_DOC = "Allows for lines to be longer than expected and ignores the extra characters";
    public static final Boolean CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_EXTRA_COLUMNS_DEFAULT = false;
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_FIRST_RECORD_CONF = "camel.sink.endpoint.ignoreFirstRecord";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_FIRST_RECORD_DOC = "Whether the first line is ignored for delimited files (for the column headers).";
    public static final Boolean CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_FIRST_RECORD_DEFAULT = true;
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_SPLIT_ROWS_CONF = "camel.sink.endpoint.splitRows";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_SPLIT_ROWS_DOC = "Sets the Component to send each row as a separate exchange once parsed";
    public static final Boolean CAMEL_SINK_FLATPACK_ENDPOINT_SPLIT_ROWS_DEFAULT = true;
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_TEXT_QUALIFIER_CONF = "camel.sink.endpoint.textQualifier";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_TEXT_QUALIFIER_DOC = "The text qualifier for delimited files.";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_TEXT_QUALIFIER_DEFAULT = null;
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_FLATPACK_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_FLATPACK_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    public static final String CAMEL_SINK_FLATPACK_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SINK_FLATPACK_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_FLATPACK_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.flatpack.lazyStartProducer";
    public static final String CAMEL_SINK_FLATPACK_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    public static final Boolean CAMEL_SINK_FLATPACK_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_FLATPACK_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.flatpack.basicPropertyBinding";
    public static final String CAMEL_SINK_FLATPACK_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SINK_FLATPACK_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelFlatpackSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelFlatpackSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_FLATPACK_PATH_TYPE_CONF, ConfigDef.Type.STRING, CAMEL_SINK_FLATPACK_PATH_TYPE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FLATPACK_PATH_TYPE_DOC);
        conf.define(CAMEL_SINK_FLATPACK_PATH_RESOURCE_URI_CONF, ConfigDef.Type.STRING, CAMEL_SINK_FLATPACK_PATH_RESOURCE_URI_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_FLATPACK_PATH_RESOURCE_URI_DOC);
        conf.define(CAMEL_SINK_FLATPACK_ENDPOINT_ALLOW_SHORT_LINES_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_FLATPACK_ENDPOINT_ALLOW_SHORT_LINES_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FLATPACK_ENDPOINT_ALLOW_SHORT_LINES_DOC);
        conf.define(CAMEL_SINK_FLATPACK_ENDPOINT_DELIMITER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_FLATPACK_ENDPOINT_DELIMITER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FLATPACK_ENDPOINT_DELIMITER_DOC);
        conf.define(CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_EXTRA_COLUMNS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_EXTRA_COLUMNS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_EXTRA_COLUMNS_DOC);
        conf.define(CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_FIRST_RECORD_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_FIRST_RECORD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FLATPACK_ENDPOINT_IGNORE_FIRST_RECORD_DOC);
        conf.define(CAMEL_SINK_FLATPACK_ENDPOINT_SPLIT_ROWS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_FLATPACK_ENDPOINT_SPLIT_ROWS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FLATPACK_ENDPOINT_SPLIT_ROWS_DOC);
        conf.define(CAMEL_SINK_FLATPACK_ENDPOINT_TEXT_QUALIFIER_CONF, ConfigDef.Type.STRING, CAMEL_SINK_FLATPACK_ENDPOINT_TEXT_QUALIFIER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FLATPACK_ENDPOINT_TEXT_QUALIFIER_DOC);
        conf.define(CAMEL_SINK_FLATPACK_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_FLATPACK_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FLATPACK_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_FLATPACK_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_FLATPACK_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FLATPACK_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_FLATPACK_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_FLATPACK_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FLATPACK_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_FLATPACK_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_FLATPACK_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_FLATPACK_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_FLATPACK_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_FLATPACK_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SINK_FLATPACK_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}