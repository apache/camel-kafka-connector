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

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.config.ConfigException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A behaviour-selecting option that is misspelled must be rejected when the connector configuration is submitted,
 * rather than silently starting a connector that behaves differently from what the configuration asked for.
 */
public class ConfigValidationTest {

    private Map<String, String> sinkProps() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "direct://test");
        props.put("camel.sink.kafka.topic", "mytopic");
        return props;
    }

    private Map<String, String> sourceProps() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct://test");
        props.put("topics", "mytopic");
        return props;
    }

    private void assertRejected(Map<String, String> props, String option, boolean sink) {
        ConfigException e = assertThrows(ConfigException.class,
            () -> {
                if (sink) {
                    new CamelSinkConnectorConfig(props);
                } else {
                    new CamelSourceConnectorConfig(props);
                }
            });
        assertTrue(e.getMessage().contains(option), "the message should name " + option + " but was: " + e.getMessage());
    }

    @Test
    public void testSinkRejectsUnknownIdempotencyExpressionType() {
        Map<String, String> props = sinkProps();
        props.put(CamelConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF, "headers");
        assertRejected(props, CamelConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF, true);
    }

    @Test
    public void testSinkRejectsUnknownIdempotencyRepositoryType() {
        Map<String, String> props = sinkProps();
        props.put(CamelConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_CONF, "in-memory");
        assertRejected(props, CamelConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_CONF, true);
    }

    @Test
    public void testSinkRejectsUnknownErrorHandler() {
        Map<String, String> props = sinkProps();
        props.put(CamelConnectorConfig.CAMEL_CONNECTOR_ERROR_HANDLER_CONF, "none");
        assertRejected(props, CamelConnectorConfig.CAMEL_CONNECTOR_ERROR_HANDLER_CONF, true);
    }

    @Test
    public void testSourceRejectsUnknownIdempotencyExpressionType() {
        Map<String, String> props = sourceProps();
        props.put(CamelConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF, "Body");
        assertRejected(props, CamelConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF, false);
    }

    @Test
    public void testSourceRejectsUnknownErrorHandler() {
        Map<String, String> props = sourceProps();
        props.put(CamelConnectorConfig.CAMEL_CONNECTOR_ERROR_HANDLER_CONF, "retry");
        assertRejected(props, CamelConnectorConfig.CAMEL_CONNECTOR_ERROR_HANDLER_CONF, false);
    }

    @Test
    public void testDocumentedValuesAreAccepted() {
        for (String expressionType : new String[] {"body", "header"}) {
            Map<String, String> props = sinkProps();
            props.put(CamelConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF, expressionType);
            assertDoesNotThrow(() -> new CamelSinkConnectorConfig(props));
        }
        for (String repositoryType : new String[] {"memory", "kafka"}) {
            Map<String, String> props = sinkProps();
            props.put(CamelConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_CONF, repositoryType);
            assertDoesNotThrow(() -> new CamelSinkConnectorConfig(props));
        }
        for (String errorHandler : new String[] {"no", "default"}) {
            Map<String, String> props = sourceProps();
            props.put(CamelConnectorConfig.CAMEL_CONNECTOR_ERROR_HANDLER_CONF, errorHandler);
            assertDoesNotThrow(() -> new CamelSourceConnectorConfig(props));
        }
    }

    @Test
    public void testDefaultsAreAccepted() {
        assertDoesNotThrow(() -> new CamelSinkConnectorConfig(sinkProps()));
        assertDoesNotThrow(() -> new CamelSourceConnectorConfig(sourceProps()));
    }
}
