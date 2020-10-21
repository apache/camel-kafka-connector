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

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.camel.LoggingLevel;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.ext.LoggerWrapper;

import static org.apache.camel.util.CollectionHelper.mapOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskHelperTest {

    @Test
    public void testMergePropertiesNull() {
        Map<String, String> result = TaskHelper.combineDefaultAndLoadedProperties(null, null);

        assertEquals(Collections.EMPTY_MAP, result);
    }

    @Test
    public void testMergePropertiesDefaultAreAdded() {
        Map<String, String> defaults = mapOf("property", "defaultValue");
        Map<String, String> loaded = mapOf("anotherProperty", "loadedValue");
        Map<String, String> result = TaskHelper.combineDefaultAndLoadedProperties(defaults, loaded);

        assertTrue(result.containsKey("property"));
        assertTrue(result.containsKey("anotherProperty"));
        assertEquals("defaultValue", result.get("property"));
        assertEquals("loadedValue", result.get("anotherProperty"));
    }

    @Test
    public void testMergePropertiesLoadedHavePrecedence() {
        Map<String, String> defaults = mapOf("property", "defaultValue");
        Map<String, String> loaded = mapOf("property", "loadedValue");
        Map<String, String> result = TaskHelper.combineDefaultAndLoadedProperties(defaults, loaded);

        assertTrue(result.containsKey("property"));
        assertEquals("loadedValue", result.get("property"));
    }

    @Test
    public void testMergePropertiesLoadedHavePrecedenceWithPrefixFiltering() {
        Map<String, String> defaults = mapOf(
            "property", "defaultValue",
            "camel.component.x.objectProperty", "#class:my.package.MyClass",
            "camel.component.x.objectProperty.field", "defaultValue"
        );

        Map<String, String> loaded = mapOf(
            "camel.component.x.objectProperty", "#class:my.package.MyOtherClass",
            "camel.component.x.objectProperty.anotherField", "loadedValue",
            "camel.component.x.normalProperty", "loadedValue"
        );

        Map<String, String> result = TaskHelper.combineDefaultAndLoadedProperties(defaults, loaded);

        assertEquals("defaultValue", result.get("property"));
        assertEquals("#class:my.package.MyOtherClass", result.get("camel.component.x.objectProperty"));
        assertEquals("loadedValue", result.get("camel.component.x.objectProperty.anotherField"));
        assertEquals("loadedValue", result.get("camel.component.x.normalProperty"));
        assertFalse(result.containsKey("camel.component.x.objectProperty.field"));
    }

    @Test
    public void testMergePropertiesLoadedAndDefaultMergesIfNoPrefixFiltering() {
        Map<String, String> defaults = mapOf(
                "camel.component.x.objectProperty", "#class:my.package.MyClass",
                "camel.component.x.objectProperty.field", "defaultValue"
        );

        Map<String, String> loaded = mapOf(
                "camel.component.x.objectProperty.anotherField", "loadedValue"
        );

        Map<String, String> result = TaskHelper.combineDefaultAndLoadedProperties(defaults, loaded);

        assertEquals("#class:my.package.MyClass", result.get("camel.component.x.objectProperty"));
        assertEquals("defaultValue", result.get("camel.component.x.objectProperty.field"));
        assertEquals("loadedValue", result.get("camel.component.x.objectProperty.anotherField"));
    }

    @Test
    public void testBuildUrlWithRuntimeCatalog() throws URISyntaxException {
        DefaultCamelContext dcc = new DefaultCamelContext();
        Map<String, String> props = mapOf(
            "camel.source.path.name", "test",
            "camel.source.endpoint.synchronous", "true"
        );

        String result = TaskHelper.buildUrl(dcc, props, "direct", "camel.source.endpoint.", "camel.source.path.");

        assertEquals("direct:test?synchronous=true", result);

        props = mapOf(
            "camel.source.path.port", "8080",
            "camel.source.path.keyspace", "test",
            "camel.source.path.hosts", "localhost"
        );

        result = TaskHelper.buildUrl(dcc, props, "cql", "camel.source.endpoint.", "camel.source.path.");

        assertEquals("cql:localhost:8080/test", result);
    }

    @Test
    public void testlogRecordContent() {
        final String partName = "abc123";
        final MyLogger logger = new MyLogger(LoggerFactory.getLogger(TaskHelperTest.class), null);
        final SourceRecord record = new SourceRecord(
            Collections.singletonMap("partition", partName),
            Collections.singletonMap("offset", "0"), null, null, null, null);

        TaskHelper.logRecordContent(logger, LoggingLevel.OFF, record);
        assertNull(logger.getEvents().poll());

        TaskHelper.logRecordContent(logger, LoggingLevel.TRACE, record);
        assertThat(logger.getEvents().peek()).isNotNull().contains(LoggingLevel.TRACE.toString());
        assertThat(logger.getEvents().poll()).isNotNull().contains(partName);

        TaskHelper.logRecordContent(logger,  LoggingLevel.DEBUG, record);
        assertThat(logger.getEvents().peek()).isNotNull().contains(LoggingLevel.DEBUG.toString());
        assertThat(logger.getEvents().poll()).isNotNull().contains(partName);

        TaskHelper.logRecordContent(logger,  LoggingLevel.INFO, record);
        assertThat(logger.getEvents().peek()).isNotNull().contains(LoggingLevel.INFO.toString());
        assertThat(logger.getEvents().poll()).isNotNull().contains(partName);

        TaskHelper.logRecordContent(logger,  LoggingLevel.WARN, record);
        assertThat(logger.getEvents().peek()).isNotNull().contains(LoggingLevel.WARN.toString());
        assertThat(logger.getEvents().poll()).isNotNull().contains(partName);

        TaskHelper.logRecordContent(logger,  LoggingLevel.ERROR, record);
        assertThat(logger.getEvents().peek()).isNotNull().contains(LoggingLevel.ERROR.toString());
        assertThat(logger.getEvents().poll()).isNotNull().contains(partName);
    }

    static class MyLogger extends LoggerWrapper {
        private final Queue<String> events = new ConcurrentLinkedQueue<>();

        public MyLogger(Logger logger, String fqcn) {
            super(logger, fqcn);
        }

        public Queue<String> getEvents() {
            return events;
        }

        private void log(LoggingLevel level, String msg) {
            events.add(level + " " + msg);
        }

        @Override
        public void trace(String msg) {
            log(LoggingLevel.TRACE, msg);
        }

        @Override
        public void debug(String msg) {
            log(LoggingLevel.DEBUG, msg);
        }

        @Override
        public void info(String msg) {
            log(LoggingLevel.INFO, msg);
        }

        @Override
        public void warn(String msg) {
            log(LoggingLevel.WARN, msg);
        }

        @Override
        public void error(String msg) {
            log(LoggingLevel.ERROR, msg);
        }
    }

}
