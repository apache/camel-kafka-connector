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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.connect.sink.SinkConnector;
import org.apache.kafka.connect.source.SourceConnector;
import org.apache.kafka.connect.transforms.Transformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifies the Kafka Connect ServiceLoader manifests (KIP-898 plugin discovery) shipped by the core
 * module reference real, usable plugin implementations.
 */
public class ServiceLoaderManifestTest {

    @Test
    public void sinkConnectorManifestIsValid() throws Exception {
        assertManifest("org.apache.kafka.connect.sink.SinkConnector", SinkConnector.class);
    }

    @Test
    public void sourceConnectorManifestIsValid() throws Exception {
        assertManifest("org.apache.kafka.connect.source.SourceConnector", SourceConnector.class);
    }

    @Test
    public void transformationManifestIsValid() throws Exception {
        assertManifest("org.apache.kafka.connect.transforms.Transformation", Transformation.class);
    }

    private void assertManifest(String serviceName, Class<?> spi) throws Exception {
        List<String> classNames = readManifest(serviceName);
        assertFalse(classNames.isEmpty(), serviceName + " manifest must list at least one implementation");
        for (String className : classNames) {
            Class<?> impl = Class.forName(className);
            assertTrue(spi.isAssignableFrom(impl), className + " must implement " + spi.getName());
            assertFalse(Modifier.isAbstract(impl.getModifiers()), className + " must not be abstract");
            assertTrue(Modifier.isPublic(impl.getModifiers()), className + " must be public");
        }
    }

    private List<String> readManifest(String serviceName) throws IOException {
        List<String> classNames = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream("/META-INF/services/" + serviceName)) {
            assertNotNull(is, "Missing ServiceLoader manifest: META-INF/services/" + serviceName);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty() && !line.startsWith("#")) {
                        classNames.add(line);
                    }
                }
            }
        }
        return classNames;
    }
}
