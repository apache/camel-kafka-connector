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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.kafkaconnector.CamelConnectorConfig;
import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.camel.kafkaconnector.CamelSourceTask;
import org.apache.kafka.connect.errors.ConnectException;
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
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The connector must not write configuration secrets to the worker log at default levels, whichever property key
 * they reach the log under. Endpoint options are folded into a single composed URI stored under a key that carries
 * no sensitive token, so masking by key name alone is not enough.
 */
public class StartupPropertiesLoggingTest {

    private static final String SECRET_KEY_VALUE = "sUp3rS3cr3tAccessValue";
    private static final String USERINFO_PASSWORD = "hunter2PlainPassword";
    private static final String IDEMPOTENCY_CLIENT_PREFIX = CamelConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_CLIENT_PREFIX;
    private static final String JAAS_PASSWORD = "jaasPl41nPassword";
    private static final String TRUSTSTORE_PASSWORD = "trustSt0reSecret";
    private static final String PRIVATE_KEY_BODY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7";
    private static final String CUSTOM_OPTION_VALUE = "cust0mL0ginSecret";

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
    public void testIdempotentRepositoryKafkaClientSecretsAreNotLoggedInClearText() {
        // Kafka client property names: the secret is not always in the last segment of the key (sasl.jaas.config,
        // ssl.keystore.key), and options of a custom login module or callback handler can use any name at all.
        Map<String, String> props = new HashMap<>();
        props.put(IDEMPOTENCY_CLIENT_PREFIX + "security.protocol", "SASL_SSL");
        props.put(IDEMPOTENCY_CLIENT_PREFIX + "sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"ckc\" password=\"" + JAAS_PASSWORD + "\";");
        props.put(IDEMPOTENCY_CLIENT_PREFIX + "ssl.truststore.password", TRUSTSTORE_PASSWORD);
        props.put(IDEMPOTENCY_CLIENT_PREFIX + "ssl.keystore.key",
                "-----BEGIN PRIVATE KEY-----\n" + PRIVATE_KEY_BODY + "\n-----END PRIVATE KEY-----");
        props.put(IDEMPOTENCY_CLIENT_PREFIX + "custom.login.option", CUSTOM_OPTION_VALUE);

        String logged = startupLogLine(props);

        assertFalse(logged.contains(JAAS_PASSWORD), "the JAAS configuration must not reach the log: " + logged);
        assertFalse(logged.contains(TRUSTSTORE_PASSWORD), "the truststore password must not reach the log: " + logged);
        assertFalse(logged.contains(PRIVATE_KEY_BODY), "the private key must not reach the log: " + logged);
        assertFalse(logged.contains(CUSTOM_OPTION_VALUE),
                "a client property is masked whatever its name, it may be a secret: " + logged);
        assertTrue(logged.contains(IDEMPOTENCY_CLIENT_PREFIX + "sasl.jaas.config"),
                "the key should stay visible, to show the property was picked up: " + logged);
    }

    @Test
    public void testIdempotentRepositoryKafkaClientSecretsDoNotLeakThroughAnyLogLine(@TempDir Path tempDir) {
        Map<String, String> props = new HashMap<>();
        props.put(CamelSourceConnectorConfig.TOPIC_CONF, "my-topic");
        props.put(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF, "direct:start");
        props.put(CamelSourceConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_CONF, "true");
        props.put(CamelSourceConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_CONF, "kafka");
        props.put(IDEMPOTENCY_CLIENT_PREFIX + "security.protocol", "SASL_SSL");
        props.put(IDEMPOTENCY_CLIENT_PREFIX + "sasl.mechanism", "PLAIN");
        props.put(IDEMPOTENCY_CLIENT_PREFIX + "sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"ckc\" password=\"" + JAAS_PASSWORD + "\";");
        props.put(IDEMPOTENCY_CLIENT_PREFIX + "ssl.truststore.location", tempDir.resolve("missing-truststore.jks").toString());
        props.put(IDEMPOTENCY_CLIENT_PREFIX + "ssl.truststore.password", TRUSTSTORE_PASSWORD);

        CamelSourceTask task = new CamelSourceTask();
        ConnectException failure;
        try {
            // the missing truststore stops the repository's Kafka consumer right after it has logged its configuration
            failure = assertThrows(ConnectException.class, () -> task.start(props));
        } finally {
            task.stop();
        }

        List<String> logged = new ArrayList<>(appender.messages);
        // the repository's Kafka consumer got the client properties, and Kafka hides the secrets in its own log line
        String consumerConfig = logged.stream()
            .filter(m -> m.startsWith("ConsumerConfig values"))
            .findFirst()
            .orElseThrow(() -> new AssertionError("the repository's Kafka consumer did not log its configuration: " + logged));
        assertTrue(consumerConfig.contains("security.protocol = SASL_SSL"), consumerConfig);
        assertTrue(consumerConfig.contains("sasl.jaas.config = [hidden]"), consumerConfig);
        assertTrue(consumerConfig.contains("ssl.truststore.password = [hidden]"), consumerConfig);
        // the Kafka Connect runtime logs the failure of a task with its stack trace
        StringWriter failureTrace = new StringWriter();
        failure.printStackTrace(new PrintWriter(failureTrace));
        logged.add(failureTrace.toString());
        for (String message : logged) {
            assertFalse(message.contains(JAAS_PASSWORD), "the JAAS configuration must not reach the log: " + message);
            assertFalse(message.contains(TRUSTSTORE_PASSWORD), "the truststore password must not reach the log: " + message);
        }
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
            if (event.getThrown() != null) {
                StringWriter trace = new StringWriter();
                event.getThrown().printStackTrace(new PrintWriter(trace));
                messages.add(trace.toString());
            }
        }
    }
}
