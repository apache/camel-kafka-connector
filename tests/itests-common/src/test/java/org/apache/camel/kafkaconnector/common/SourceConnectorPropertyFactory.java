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

package org.apache.camel.kafkaconnector.common;

import org.apache.camel.LoggingLevel;

import static org.apache.camel.kafkaconnector.CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NAME;
import static org.apache.camel.kafkaconnector.CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF;
import static org.apache.camel.kafkaconnector.CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_CONF;
import static org.apache.camel.kafkaconnector.CamelSourceConnectorConfig.CAMEL_SOURCE_CONTENT_LOG_LEVEL_CONF;
import static org.apache.camel.kafkaconnector.CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF;
import static org.apache.camel.kafkaconnector.CamelSourceConnectorConfig.TOPIC_CONF;

public abstract class SourceConnectorPropertyFactory<T extends SourceConnectorPropertyFactory<T>> extends BasicConnectorPropertyFactory<T> {

    public T withKafkaTopic(String topic) {
        return setProperty(TOPIC_CONF, topic);
    }

    public T withSourceUrl(String sourceUrl) {
        return setProperty(CAMEL_SOURCE_URL_CONF, sourceUrl);
    }

    public T withSourceContentLogginglevel(LoggingLevel level) {
        return setProperty(CAMEL_SOURCE_CONTENT_LOG_LEVEL_CONF, level.toString());
    }

    public T withAggregate(String aggregate, int size, int timeout) {
        return withBeans(CAMEL_CONNECTOR_AGGREGATE_NAME, classRef(aggregate))
                .setProperty(CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF, size)
                .setProperty(CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_CONF, timeout);
    }
}
