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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.processor.idempotent.kafka.KafkaIdempotentRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * The Kafka idempotent repository must get the Kafka client properties of the configuration on both its consumer and
 * its producer, so that it can reach a cluster secured with SSL or SASL. The repository is only built here, never
 * started, so no broker is needed.
 */
public class IdempotentRepositoryKafkaClientPropertiesTest {

    private static final String TOPIC = "ckc-idempotency";
    private static final String BOOTSTRAP_SERVERS = "broker-1:9093,broker-2:9093";
    private static final int MAX_CACHE_SIZE = 1500;
    private static final int POLL_DURATION_MS = 150;

    private static KafkaIdempotentRepository buildRepository(Map<String, ?> clientProperties) {
        CamelKafkaConnectMain main = CamelKafkaConnectMain.builder("direct://start", "log://test")
            .withProperties(Collections.emptyMap())
            .withIdempotencyEnabled(true)
            .withExpressionType("body")
            .withIdempotentRepositoryType("kafka")
            .withIdempotentRepositoryTopicName(TOPIC)
            .withIdempotentRepositoryKafkaServers(BOOTSTRAP_SERVERS)
            .withIdempotentRepositoryKafkaMaxCacheSize(MAX_CACHE_SIZE)
            .withIdempotentRepositoryKafkaPollDuration(POLL_DURATION_MS)
            .withIdempotentRepositoryKafkaClientProperties(clientProperties)
            .build(new DefaultCamelContext());

        KafkaIdempotentRepository repository = main.getCamelContext().getRegistry()
            .lookupByNameAndType("ckcIdempotentRepository", KafkaIdempotentRepository.class);
        assertNotNull(repository, "the Kafka idempotent repository should be bound in the registry");
        assertEquals(TOPIC, repository.getTopic());
        assertEquals(MAX_CACHE_SIZE, repository.getMaxCacheSize());
        assertEquals(POLL_DURATION_MS, repository.getPollDurationMs());
        return repository;
    }

    @Test
    public void testClientPropertiesReachTheConsumerAndTheProducer() {
        Map<String, String> clientProperties = new HashMap<>();
        clientProperties.put("security.protocol", "SASL_SSL");
        clientProperties.put("sasl.mechanism", "SCRAM-SHA-512");
        clientProperties.put("sasl.jaas.config",
                "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ckc\" password=\"ckc-secret\";");
        clientProperties.put("ssl.truststore.location", "/opt/kafka/secrets/truststore.p12");
        clientProperties.put("ssl.truststore.password", "truststore-secret");

        KafkaIdempotentRepository repository = buildRepository(clientProperties);

        for (Properties config : List.of(repository.getConsumerConfig(), repository.getProducerConfig())) {
            assertEquals(BOOTSTRAP_SERVERS, config.getProperty("bootstrap.servers"));
            clientProperties.forEach((name, value) -> assertEquals(value, config.getProperty(name), name));
            assertEquals(clientProperties.size() + 1, config.size(), "unexpected properties in " + config.keySet());
        }
        // the repository adds consumer-only and producer-only settings to these when it starts
        assertNotSame(repository.getConsumerConfig(), repository.getProducerConfig());
    }

    @Test
    public void testWithoutClientPropertiesTheRepositoryIsBuiltFromTheBootstrapServersOnly() {
        KafkaIdempotentRepository repository = buildRepository(Collections.emptyMap());

        // unchanged behaviour: the repository builds its own consumer and producer configuration when it starts
        assertEquals(BOOTSTRAP_SERVERS, repository.getBootstrapServers());
        assertNull(repository.getConsumerConfig());
        assertNull(repository.getProducerConfig());
    }

    @Test
    public void testBootstrapServersGivenAsClientPropertyTakePrecedence() {
        KafkaIdempotentRepository repository
                = buildRepository(Collections.singletonMap("bootstrap.servers", "secured-broker:9094"));

        assertEquals("secured-broker:9094", repository.getConsumerConfig().getProperty("bootstrap.servers"));
        assertEquals("secured-broker:9094", repository.getProducerConfig().getProperty("bootstrap.servers"));
    }
}
