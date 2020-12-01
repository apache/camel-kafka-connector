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
    public static final String CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF = "camel.aggregation.size";
    public static final String CAMEL_CONNECTOR_AGGREGATE_SIZE_DOC = "The size of the aggregation, to be used in combination with camel.beans.aggregate";

    public static final Long CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_DEFAULT = 500L;
    public static final String CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_CONF = "camel.aggregation.timeout";
    public static final String CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_DOC = "The timeout of the aggregation, to be used in combination with camel.beans.aggregate";

    public static final String CAMEL_CONNECTOR_ERROR_HANDLER_DEFAULT = "default";
    public static final String CAMEL_CONNECTOR_ERROR_HANDLER_CONF = "camel.error.handler";
    public static final String CAMEL_CONNECTOR_ERROR_HANDLER_DOC = "The error handler to use: possible value are 'no' or 'default'";
    
    public static final int CAMEL_CONNECTOR_ERROR_HANDLER_MAXIMUM_REDELIVERIES_DEFAULT = 0;
    public static final String CAMEL_CONNECTOR_ERROR_HANDLER_MAXIMUM_REDELIVERIES_CONF = "camel.error.handler.max.redeliveries";
    public static final String CAMEL_CONNECTOR_ERROR_HANDLER_MAXIMUM_REDELIVERIES_DOC = "The maximum redeliveries to be use in case of Default Error Handler";
    
    public static final Long CAMEL_CONNECTOR_ERROR_HANDLER_REDELIVERY_DELAY_DEFAULT = 1000L;
    public static final String CAMEL_CONNECTOR_ERROR_HANDLER_REDELIVERY_DELAY_CONF = "camel.error.handler.redelivery.delay";
    public static final String CAMEL_CONNECTOR_ERROR_HANDLER_REDELIVERY_DELAY_DOC = "The initial redelivery delay in milliseconds in case of Default Error Handler";
    
    public static final Boolean CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_DEFAULT = false;
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_CONF = "camel.idempotency.enabled";
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_DOC = "If idempotency must be enabled or not";
    
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_DEFAULT = "memory";
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_CONF = "camel.idempotency.repository.type";
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_DOC = "The idempotent repository type to use, possible values are memory and kafka";
    
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_DEFAULT = "body";
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF = "camel.idempotency.expression.type";
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_DOC = "How the idempotency will be evaluated: possible values are body and header";
    
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_DEFAULT = null;
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_CONF = "camel.idempotency.expression.header";
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_DOC = "The header name that will be evaluated in case of camel.idempotency.expression.type equals to header";    
    
    public static final int CAMEL_CONNECTOR_IDEMPOTENCY_MEMORY_DIMENSION_DEFAULT = 100;
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_MEMORY_DIMENSION_CONF = "camel.idempotency.memory.dimension";
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_MEMORY_DIMENSION_DOC = "The Memory dimension of the in memory idempotent Repository";
    
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_TOPIC_DEFAULT = "kafka_idempotent_repository";
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_TOPIC_CONF = "camel.idempotency.kafka.topic";
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_TOPIC_DOC = "The Kafka topic name to use for the idempotent repository";    
    
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_BOOTSTRAP_SERVERS_DEFAULT = "localhost:9092";
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_BOOTSTRAP_SERVERS_CONF = "camel.idempotency.kafka.bootstrap.servers";
    public static final String  CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_BOOTSTRAP_SERVERS_DOC = "A comma-separated list of host and port pairs that are the addresses of the Kafka brokers where the idempotent repository should live"; 
    
    public static final int CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_MAX_CACHE_SIZE_DEFAULT = 1000;
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_MAX_CACHE_SIZE_CONF = "camel.idempotency.kafka.max.cache.size";
    public static final String  CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_MAX_CACHE_SIZE_DOC = "Sets the maximum size of the local key cache";
    
    public static final int CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_POLL_DURATION_DEFAULT = 100;
    public static final String CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_POLL_DURATION_CONF = "camel.idempotency.kafka.poll.duration.ms";
    public static final String  CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_POLL_DURATION_DOC = "Sets the poll duration (in milliseconds) of the Kafka consumer";
    
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
