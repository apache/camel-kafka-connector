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
package org.apache.camel.kafkaconnector.timer;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelTimerSourceConnectorConfig
        extends
            CamelSourceConnectorConfig {

    public static final String CAMEL_SOURCE_TIMER_PATH_TIMER_NAME_CONF = "camel.source.path.timerName";
    public static final String CAMEL_SOURCE_TIMER_PATH_TIMER_NAME_DOC = "The name of the timer";
    public static final String CAMEL_SOURCE_TIMER_PATH_TIMER_NAME_DEFAULT = null;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF = "camel.source.endpoint.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_TIMER_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_DELAY_CONF = "camel.source.endpoint.delay";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_DELAY_DOC = "Delay before first event is triggered.";
    public static final Long CAMEL_SOURCE_TIMER_ENDPOINT_DELAY_DEFAULT = 1000L;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_FIXED_RATE_CONF = "camel.source.endpoint.fixedRate";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_FIXED_RATE_DOC = "Events take place at approximately regular intervals, separated by the specified period.";
    public static final Boolean CAMEL_SOURCE_TIMER_ENDPOINT_FIXED_RATE_DEFAULT = false;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_INCLUDE_METADATA_CONF = "camel.source.endpoint.includeMetadata";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_INCLUDE_METADATA_DOC = "Whether to include metadata in the exchange such as fired time, timer name, timer count etc. This information is default included.";
    public static final Boolean CAMEL_SOURCE_TIMER_ENDPOINT_INCLUDE_METADATA_DEFAULT = true;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_PERIOD_CONF = "camel.source.endpoint.period";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_PERIOD_DOC = "If greater than 0, generate periodic events every period.";
    public static final Long CAMEL_SOURCE_TIMER_ENDPOINT_PERIOD_DEFAULT = 1000L;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_REPEAT_COUNT_CONF = "camel.source.endpoint.repeatCount";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_REPEAT_COUNT_DOC = "Specifies a maximum limit of number of fires. So if you set it to 1, the timer will only fire once. If you set it to 5, it will only fire five times. A value of zero or negative means fire forever.";
    public static final Long CAMEL_SOURCE_TIMER_ENDPOINT_REPEAT_COUNT_DEFAULT = 0L;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_EXCEPTION_HANDLER_CONF = "camel.source.endpoint.exceptionHandler";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_EXCEPTION_HANDLER_DOC = "To let the consumer use a custom ExceptionHandler. Notice if the option bridgeErrorHandler is enabled then this option is not in use. By default the consumer will deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_EXCEPTION_HANDLER_DEFAULT = null;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_EXCHANGE_PATTERN_CONF = "camel.source.endpoint.exchangePattern";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_EXCHANGE_PATTERN_DOC = "Sets the exchange pattern when the consumer creates an exchange. One of: [InOnly] [InOut] [InOptionalOut]";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_EXCHANGE_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.source.endpoint.basicPropertyBinding";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SOURCE_TIMER_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_DAEMON_CONF = "camel.source.endpoint.daemon";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_DAEMON_DOC = "Specifies whether or not the thread associated with the timer endpoint runs as a daemon. The default value is true.";
    public static final Boolean CAMEL_SOURCE_TIMER_ENDPOINT_DAEMON_DEFAULT = true;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_PATTERN_CONF = "camel.source.endpoint.pattern";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_PATTERN_DOC = "Allows you to specify a custom Date pattern to use for setting the time option using URI syntax.";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_PATTERN_DEFAULT = null;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_SYNCHRONOUS_CONF = "camel.source.endpoint.synchronous";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    public static final Boolean CAMEL_SOURCE_TIMER_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_TIME_CONF = "camel.source.endpoint.time";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_TIME_DOC = "A java.util.Date the first event should be generated. If using the URI, the pattern expected is: yyyy-MM-dd HH:mm:ss or yyyy-MM-dd'T'HH:mm:ss.";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_TIME_DEFAULT = null;
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_TIMER_CONF = "camel.source.endpoint.timer";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_TIMER_DOC = "To use a custom Timer";
    public static final String CAMEL_SOURCE_TIMER_ENDPOINT_TIMER_DEFAULT = null;
    public static final String CAMEL_SOURCE_TIMER_COMPONENT_BRIDGE_ERROR_HANDLER_CONF = "camel.component.timer.bridgeErrorHandler";
    public static final String CAMEL_SOURCE_TIMER_COMPONENT_BRIDGE_ERROR_HANDLER_DOC = "Allows for bridging the consumer to the Camel routing Error Handler, which mean any exceptions occurred while the consumer is trying to pickup incoming messages, or the likes, will now be processed as a message and handled by the routing Error Handler. By default the consumer will use the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that will be logged at WARN or ERROR level and ignored.";
    public static final Boolean CAMEL_SOURCE_TIMER_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT = false;
    public static final String CAMEL_SOURCE_TIMER_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.timer.basicPropertyBinding";
    public static final String CAMEL_SOURCE_TIMER_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    public static final Boolean CAMEL_SOURCE_TIMER_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelTimerSourceConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelTimerSourceConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSourceConnectorConfig.conf());
        conf.define(CAMEL_SOURCE_TIMER_PATH_TIMER_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_TIMER_PATH_TIMER_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SOURCE_TIMER_PATH_TIMER_NAME_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_TIMER_ENDPOINT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_TIMER_ENDPOINT_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_DELAY_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_FIXED_RATE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_TIMER_ENDPOINT_FIXED_RATE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_FIXED_RATE_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_INCLUDE_METADATA_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_TIMER_ENDPOINT_INCLUDE_METADATA_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_INCLUDE_METADATA_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_PERIOD_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_TIMER_ENDPOINT_PERIOD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_PERIOD_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_REPEAT_COUNT_CONF, ConfigDef.Type.LONG, CAMEL_SOURCE_TIMER_ENDPOINT_REPEAT_COUNT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_REPEAT_COUNT_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_EXCEPTION_HANDLER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_TIMER_ENDPOINT_EXCEPTION_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_EXCEPTION_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_EXCHANGE_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_TIMER_ENDPOINT_EXCHANGE_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_EXCHANGE_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_TIMER_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_DAEMON_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_TIMER_ENDPOINT_DAEMON_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_DAEMON_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_PATTERN_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_TIMER_ENDPOINT_PATTERN_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_PATTERN_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_TIMER_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_TIME_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_TIMER_ENDPOINT_TIME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_TIME_DOC);
        conf.define(CAMEL_SOURCE_TIMER_ENDPOINT_TIMER_CONF, ConfigDef.Type.STRING, CAMEL_SOURCE_TIMER_ENDPOINT_TIMER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_ENDPOINT_TIMER_DOC);
        conf.define(CAMEL_SOURCE_TIMER_COMPONENT_BRIDGE_ERROR_HANDLER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_TIMER_COMPONENT_BRIDGE_ERROR_HANDLER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SOURCE_TIMER_COMPONENT_BRIDGE_ERROR_HANDLER_DOC);
        conf.define(CAMEL_SOURCE_TIMER_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SOURCE_TIMER_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.LOW, CAMEL_SOURCE_TIMER_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}