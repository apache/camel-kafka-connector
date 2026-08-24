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
package org.apache.camel.kafkaconnector.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.CamelSourceTask;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The connector must not write configuration secrets to the worker log at default levels, whichever property key
 * they reach the log under. Endpoint options are folded into a single composed URI stored under a key that carries
 * no sensitive token, so masking by key name alone is not enough.
 */
public class StartupPropertiesLoggingTest {

    private static final String SECRET_KEY_VALUE = "sUp3rS3cr3tAccessValue";
    private static final String USERINFO_PASSWORD = "hunter2PlainPassword";

    private CapturingAppender appender;
    private LoggerConfig loggerConfig;

    @BeforeEach
    public void attachAppender() {
        appender = new CapturingAppender();
        appender.start();

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration configuration = context.getConfiguration();
        configuration.addAppender(appender);
        loggerConfig = configuration.getLoggerConfig(CamelKafkaConnectMain.class.getName());
        loggerConfig.addAppender(appender, Level.INFO, null);
        context.updateLoggers();
    }

    @AfterEach
    public void detachAppender() {
        loggerConfig.removeAppender(appender.getName());
        appender.stop();
        ((LoggerContext) LogManager.getContext(false)).updateLoggers();
    }

    private String startupLogLine(Map<String, String> props) {
        CamelKafkaConnectMain.builder("direct://start", "log://test")
            .withProperties(props)
            .build(new DefaultCamelContext());

        return appender.messages.stream()
            .filter(m -> m.startsWith("Setting initial properties"))
            .findFirst()
            .orElseThrow(() -> new AssertionError("the startup properties line was not logged: " + appender.messages));
    }

    @Test
    public void testComposedSinkEndpointUriIsNotLoggedInClearText() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSinkTask.KAMELET_SINK_TEMPLATE_PARAMETERS_PREFIX + "toUrl",
                "aws2-kms://label?accessKey=AKIAEXAMPLEKEY&secretKey=" + SECRET_KEY_VALUE + "&region=eu-west-1");

        String logged = startupLogLine(props);

        assertFalse(logged.contains(SECRET_KEY_VALUE),
                "the composed endpoint URI must not carry the secret into the log: " + logged);
        assertTrue(logged.contains("aws2-kms"), "the endpoint itself should stay readable: " + logged);
    }

    @Test
    public void testComposedSourceEndpointUriIsNotLoggedInClearText() {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceTask.KAMELET_SOURCE_TEMPLATE_PARAMETERS_PREFIX + "fromUrl",
                "aws2-kms://label?accessKey=AKIAEXAMPLEKEY&secretKey=" + SECRET_KEY_VALUE + "&region=eu-west-1");

        String logged = startupLogLine(props);

        assertFalse(logged.contains(SECRET_KEY_VALUE),
                "the composed endpoint URI must not carry the secret into the log: " + logged);
    }

    @Test
    public void testCredentialsEmbeddedInTheConfiguredUrlAreNotLoggedInClearText() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "netty:tcp://someuser:" + USERINFO_PASSWORD + "@somehost:5555");

        String logged = startupLogLine(props);

        assertFalse(logged.contains(USERINFO_PASSWORD),
                "userinfo credentials must not reach the log: " + logged);
    }

    @Test
    public void testKeyBasedMaskingStillApplies() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.endpoint.secretKey", SECRET_KEY_VALUE);

        String logged = startupLogLine(props);

        assertFalse(logged.contains(SECRET_KEY_VALUE), "a sensitively named key must stay masked: " + logged);
    }

    @Test
    public void testNonSensitiveValuesAreStillLogged() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.endpoint.region", "eu-west-1");

        String logged = startupLogLine(props);

        assertTrue(logged.contains("eu-west-1"), "ordinary configuration should remain visible: " + logged);
    }

    private static final class CapturingAppender extends AbstractAppender {

        private final List<String> messages = Collections.synchronizedList(new ArrayList<>());

        CapturingAppender() {
            super("ckcCapture", null, null, true, Property.EMPTY_ARRAY);
        }

        @Override
        public void append(LogEvent event) {
            messages.add(event.getMessage().getFormattedMessage());
        }
    }
}
