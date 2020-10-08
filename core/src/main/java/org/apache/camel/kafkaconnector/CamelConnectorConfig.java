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

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

public abstract class CamelConnectorConfig extends AbstractConfig {
    public static final String CAMEL_CONNECTOR_AGGREGATE_DEFAULT = null;
    public static final String CAMEL_CONNECTOR_AGGREGATE_NAME = "aggregate";
    public static final String CAMEL_CONNECTOR_AGGREGATE_CONF = "camel.beans." + CAMEL_CONNECTOR_AGGREGATE_NAME;
    public static final String CAMEL_CONNECTOR_AGGREGATE_DOC = "A reference to an aggregate bean, in the form of #class:";

    public static final Integer CAMEL_CONNECTOR_AGGREGATE_SIZE_DEFAULT = 10;
    public static final String CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF = "camel.beans.aggregation.size";
    public static final String CAMEL_CONNECTOR_AGGREGATE_SIZE_DOC = "The size of the aggregation, to be used in combination with camel.beans.aggregate";

    public static final Long CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_DEFAULT = 500L;
    public static final String CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_CONF = "camel.beans.aggregation.timeout";
    public static final String CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_DOC = "The timeout of the aggregation, to be used in combination with camel.beans.aggregate";

    protected CamelConnectorConfig(ConfigDef definition, Map<?, ?> originals, Map<String, ?> configProviderProps, boolean doLog) {
        super(definition, originals, configProviderProps, doLog);
    }

    protected CamelConnectorConfig(ConfigDef definition, Map<?, ?> originals) {
        super(definition, originals);
    }

    protected CamelConnectorConfig(ConfigDef definition, Map<?, ?> originals, boolean doLog) {
        super(definition, originals, doLog);
    }
}
