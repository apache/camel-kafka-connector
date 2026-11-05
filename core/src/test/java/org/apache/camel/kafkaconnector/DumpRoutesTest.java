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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.impl.DefaultDumpRoutesStrategy;
import org.apache.camel.kafkaconnector.utils.CamelKafkaConnectMain;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.Property;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DumpRoutesTest {

    private CapturingAppender appender;
    private LoggerConfig loggerConfig;

    @BeforeEach
    public void attachAppender() {
        appender = new CapturingAppender();
        appender.start();

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration configuration = context.getConfiguration();
        configuration.addAppender(appender);
        loggerConfig = new LoggerConfig(DefaultDumpRoutesStrategy.class.getName(), Level.INFO, true);
        loggerConfig.addAppender(appender, Level.INFO, null);
        configuration.addLogger(DefaultDumpRoutesStrategy.class.getName(), loggerConfig);
        context.updateLoggers();
    }

    @AfterEach
    public void detachAppender() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.getConfiguration().removeLogger(DefaultDumpRoutesStrategy.class.getName());
        appender.stop();
        context.updateLoggers();
    }

    private CamelSourceTask startSourceTask(Map<String, String> extraProps) {
        Map<String, String> props = new HashMap<>(extraProps);
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, "my-topic");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, "direct:start");

        CamelSourceTask sourceTask = new CamelSourceTask();
        sourceTask.start(props);
        return sourceTask;
    }

    @Test
    public void testRoutesAreDumpedAsXmlWhenEnabled() {
        CamelSourceTask sourceTask = startSourceTask(
                Collections.singletonMap(CamelConnectorConfig.CAMEL_CONNECTOR_DUMP_ROUTES_CONF, "true"));
        try {
            assertEquals(CamelKafkaConnectMain.DUMP_ROUTES_FORMAT, sourceTask.getCms().getCamelContext().getDumpRoutes());
            assertTrue(appender.messages.stream().anyMatch(m -> m.startsWith("Dumping") && m.endsWith("routes as XML")),
                    "the routes should be dumped as XML: " + appender.messages);
            assertTrue(appender.messages.stream().anyMatch(m -> m.contains("<route")),
                    "the dump should contain the routes: " + appender.messages);
        } finally {
            sourceTask.stop();
        }
    }

    @Test
    public void testRoutesAreNotDumpedByDefault() {
        CamelSourceTask sourceTask = startSourceTask(Collections.emptyMap());
        try {
            assertNull(sourceTask.getCms().getCamelContext().getDumpRoutes());
            assertFalse(appender.messages.stream().anyMatch(m -> m.startsWith("Dumping")),
                    "no route dump expected: " + appender.messages);
        } finally {
            sourceTask.stop();
        }
    }

    private static final class CapturingAppender extends AbstractAppender {
        private final List<String> messages = Collections.synchronizedList(new ArrayList<>());

        CapturingAppender() {
            super("dump-routes-capture", null, null, true, Property.EMPTY_ARRAY);
        }

        @Override
        public void append(LogEvent event) {
            messages.add(event.getMessage().getFormattedMessage());
        }
    }
}
