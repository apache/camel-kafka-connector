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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.connect.runtime.isolation.PluginClassLoader;

/**
 * Verifies the deserializer in a packaged connector using Kafka Connect's plugin classloader.
 *
 * <pre>
 * java --class-path "$KAFKA_HOME/libs/*" tests/ApicurioPluginSmokeTest.java avro path/to/unpacked-connector
 * </pre>
 * Use {@code jsonschema} for kafka-apicurio-registry-not-secured-source.
 */
public final class ApicurioPluginSmokeTest {
    private static final long SCHEMA_ID = 42;
    private static final String SCHEMA_PATH = "/apis/registry/v2/ids/globalIds/" + SCHEMA_ID;

    public static void main(String[] args) throws Exception {
        if (args.length != 2 || !("avro".equals(args[0]) || "jsonschema".equals(args[0]))) {
            throw new IllegalArgumentException("Usage: ApicurioPluginSmokeTest [avro|jsonschema] /path/to/unpacked-connector");
        }

        Path connector = Path.of(args[1]);
        boolean jsonSchema = "jsonschema".equals(args[0]);
        String serde = jsonSchema ? "jsonschema.JsonSchema" : "avro.Avro";
        var urls = new ArrayList<URL>();
        try (var files = Files.list(connector)) {
            for (Path jar : files.filter(file -> file.toString().endsWith(".jar")).sorted().toList()) {
                urls.add(jar.toUri().toURL());
            }
        }

        var fetchedSchema = new AtomicBoolean();
        var registry = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        registry.createContext(SCHEMA_PATH + "/references", request -> reply(request, "[]"));
        registry.createContext(SCHEMA_PATH, request -> {
            fetchedSchema.set(true);
            reply(request, jsonSchema ? "{\"type\":\"string\"}" : "\"string\"");
        });
        registry.start();

        Thread thread = Thread.currentThread();
        ClassLoader originalLoader = thread.getContextClassLoader();
        try (var loader = new PluginClassLoader(
                connector.toUri().toURL(), urls.toArray(URL[]::new), originalLoader)) {
            thread.setContextClassLoader(loader);
            Class<?> deserializerClass = loader.loadClass("io.apicurio.registry.serde." + serde + "KafkaDeserializer");
            try (var deserializer = (Deserializer<?>) deserializerClass.getConstructor().newInstance()) {
                deserializer.configure(Map.of(
                        "apicurio.registry.url", "http://127.0.0.1:" + registry.getAddress().getPort() + "/apis/registry/v2",
                        "apicurio.registry.headers.enabled", false), false);

                byte[] data = (jsonSchema ? "\"hello\"" : "\nhello").getBytes(StandardCharsets.UTF_8);
                byte[] payload = ByteBuffer.allocate(1 + Long.BYTES + data.length)
                        .put((byte) 0).putLong(SCHEMA_ID).put(data).array();
                Object result = deserializer.deserialize("test-topic", payload);
                String expected = jsonSchema ? "\"hello\"" : "hello";
                if (!fetchedSchema.get() || !expected.equals(String.valueOf(result))) {
                    throw new AssertionError("Expected a schema fetch and " + expected
                            + ", got fetchedSchema=" + fetchedSchema.get() + " and value=" + result);
                }
            }
        } finally {
            thread.setContextClassLoader(originalLoader);
            registry.stop(0);
        }

        System.out.println("Deserialized from " + connector.getFileName());
    }

    private static void reply(HttpExchange request, String content) throws IOException {
        try (request) {
            byte[] response = content.getBytes(StandardCharsets.UTF_8);
            request.getResponseHeaders().set("Content-Type", "application/json");
            request.sendResponseHeaders(200, response.length);
            request.getResponseBody().write(response);
        }
    }
}
