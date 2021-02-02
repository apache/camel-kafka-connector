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

import java.util.Properties;

public class IdempotencyConfigBuilder<T extends ConnectorPropertyFactory> {
    private final T handle;
    private final Properties properties;

    public IdempotencyConfigBuilder(T handle, Properties properties) {
        this.handle = handle;
        this.properties = properties;

        withEnabled(true);
    }

    private IdempotencyConfigBuilder<T> withEntry(String key, Object value) {
        properties.put("camel.idempotency." + key, value);

        return this;
    }

    public IdempotencyConfigBuilder<T> withEnabled(boolean value) {
        return withEntry("enabled", value);
    }

    public IdempotencyConfigBuilder<T> withRepositoryType(String value) {
        return withEntry("repository.type", value);
    }

    public IdempotencyConfigBuilder<T> withExpressionType(String value) {
        return withEntry("expression.type", value);
    }

    public IdempotencyConfigBuilder<T> withExpressionHeader(String value) {
        return withEntry("expression.header", value);
    }

    public IdempotencyConfigBuilder<T> withMemoryDimension(String value) {
        return withEntry("memory.dimension", value);
    }

    public IdempotencyConfigBuilder<T> withKafkaTopic(String value) {
        return withEntry("kafka.topic", value);
    }

    public IdempotencyConfigBuilder<T> withKafkaBootstrapServers(String value) {
        return withEntry("kafka.bootstrap.servers", value);
    }

    public IdempotencyConfigBuilder<T> withKafkaMaxCacheSize(String value) {
        return withEntry("kafka.max.cache.size", value);
    }

    public IdempotencyConfigBuilder<T> withKafkaPollDurationMs(String value) {
        return withEntry("kafka.poll.duration.ms", value);
    }

    public T end() {
        return handle;
    }
}
