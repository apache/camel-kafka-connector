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

import static org.apache.camel.kafkaconnector.CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF;
import static org.apache.camel.kafkaconnector.CamelSourceConnectorConfig.CAMEL_SOURCE_CONTENT_LOG_LEVEL_CONF;

public abstract class SinkConnectorPropertyFactory<T extends SinkConnectorPropertyFactory<T>> extends BasicConnectorPropertyFactory<T> {

    public T withTopics(String topics) {
        return setProperty("topics", topics);
    }

    public T withSinkUrl(String sinkUrl) {
        return setProperty(CAMEL_SINK_URL_CONF, sinkUrl);
    }

    public T withSinkContentLogginglevel(LoggingLevel level) {
        return setProperty(CAMEL_SOURCE_CONTENT_LOG_LEVEL_CONF, level.toString());
    }
}
