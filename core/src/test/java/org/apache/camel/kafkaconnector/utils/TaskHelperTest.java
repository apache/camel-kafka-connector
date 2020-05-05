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
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ExtendedCamelContext;
import org.apache.camel.catalog.RuntimeCamelCatalog;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TaskHelperTest {

    @Test
    public void testMergePropertiesNull() {
        Map result = TaskHelper.mergeProperties(null, null);

        assertEquals(Collections.EMPTY_MAP, result);
    }

    @Test
    public void testMergePropertiesDefaultAreAdded() {
        Map<String, String> defaults = new HashMap<String, String>() {{
                put("property", "defaultValue");
            }};

        Map<String, String> loaded = new HashMap<String, String>() {{
                put("anotherProperty", "loadedValue");
            }};

        Map result = TaskHelper.mergeProperties(defaults, loaded);

        assertTrue(result.containsKey("property"));
        assertTrue(result.containsKey("anotherProperty"));
        assertEquals("defaultValue", result.get("property"));
        assertEquals("loadedValue", result.get("anotherProperty"));
    }

    @Test
    public void testMergePropertiesLoadedHavePrecedence() {
        Map<String, String> defaults = new HashMap<String, String>() {{
                put("property", "defaultValue");
            }};

        Map<String, String> loaded = new HashMap<String, String>() {{
                put("property", "loadedValue");
            }};

        Map result = TaskHelper.mergeProperties(defaults, loaded);

        assertTrue(result.containsKey("property"));
        assertEquals("loadedValue", result.get("property"));
    }

    @Test
    public void testMergePropertiesLoadedHavePrecedenceWithPrefixFiltering() {
        Map<String, String> defaults = new HashMap<String, String>() {{
                put("property", "defaultValue");
                put("camel.component.x.objectProperty", "#class:my.package.MyClass");
                put("camel.component.x.objectProperty.field", "defaultValue");
            }};

        Map<String, String> loaded = new HashMap<String, String>() {{
                put("camel.component.x.objectProperty", "#class:my.package.MyOtherClass");
                put("camel.component.x.objectProperty.anotherField", "loadedValue");
                put("camel.component.x.normalProperty", "loadedValue");
            }};

        Map result = TaskHelper.mergeProperties(defaults, loaded);

        assertEquals("defaultValue", result.get("property"));
        assertEquals("#class:my.package.MyOtherClass", result.get("camel.component.x.objectProperty"));
        assertEquals("loadedValue", result.get("camel.component.x.objectProperty.anotherField"));
        assertEquals("loadedValue", result.get("camel.component.x.normalProperty"));
        assertTrue(!result.containsKey("camel.component.x.objectProperty.field"));
    }

    @Test
    public void testCreateEndpointOptionsFromProperties() {
        Map<String, String> props = new HashMap<String, String>() {{
                put("prefix.key1", "value1");
                put("notprefix.key2", "value2");
            }};

        String result = TaskHelper.createEndpointOptionsFromProperties(props, "prefix.");

        assertEquals("?key1=value1", result);
    }

    @Test
    public void testCreateEndpointOptionsFromPropertiesConcatenation() {
        Map<String, String> props = new HashMap<String, String>() {{
                put("prefix.key1", "value1");
                put("prefix.key2", "value2");
            }};

        String result = TaskHelper.createEndpointOptionsFromProperties(props, "prefix.");

        assertEquals("?key1=value1&key2=value2", result);
    }

    @Test
    public void testCreateEndpointOptionsFromPropertiesEmpty() {
        Map<String, String> props = new HashMap<String, String>() {{
                put("prefix.key1", "value1");
                put("notprefix.key2", "value2");
            }};

        String result = TaskHelper.createEndpointOptionsFromProperties(props, "anotherprefix");

        assertEquals("", result);
    }

    @Test
    public void testCreateUrlPathFromProperties() {
        Map<String, String> props = new HashMap<String, String>() {{
                put("prefix.key1", "value1");
                put("notprefix.key2", "value2");
            }};

        String result = TaskHelper.createUrlPathFromProperties(props, "prefix.");

        assertEquals("value1", result);
    }

    @Test
    public void testCreateUrlPathFromPropertiesConcatenation() {
        Map<String, String> props = new HashMap<String, String>() {{
                put("prefix.key1", "value1");
                put("prefix.key2", "value2");
            }};

        String result = TaskHelper.createUrlPathFromProperties(props, "prefix.");

        assertEquals("value1:value2", result);
    }

    @Test
    public void testCreateUrlPathFromPropertiesEmpty() {
        Map<String, String> props = new HashMap<String, String>() {{
                put("prefix.key1", "value1");
                put("notprefix.key2", "value2");
            }};

        String result = TaskHelper.createUrlPathFromProperties(props, "anotherprefix");

        assertEquals("", result);
    }

    @Test
    public void testBuildUrl() {
        Map<String, String> props = new HashMap<String, String>() {{
                put("prefix.key1", "value1");
                put("anotherPrefix.key2", "value2");
            }};

        String result = TaskHelper.buildUrl(props, "test", "prefix.", "anotherPrefix.");

        assertEquals("test:value2?key1=value1", result);
    }

    @Test
    public void testBuildUrlWithRuntimeCatalog() throws URISyntaxException {
        DefaultCamelContext dcc = new DefaultCamelContext();
        RuntimeCamelCatalog rcc = dcc.adapt(ExtendedCamelContext.class).getRuntimeCamelCatalog();
        Map<String, String> props = new HashMap<String, String>() {{
                put("prefix.name", "test");
                put("anotherPrefix.synchronous", "true");
            }};

        String result = TaskHelper.buildUrl(rcc, props, "direct", "prefix.", "anotherPrefix.");

        assertEquals("direct:test?synchronous=true", result);

        props = new HashMap<String, String>() {{
                put("prefix.port", "8080");
                put("anotherPrefix.keyspace", "test");
                put("anotherPrefix.hosts", "localhost");
            }};

        result = TaskHelper.buildUrl(rcc, props, "cql", "prefix.", "anotherPrefix.");

        assertEquals("cql:localhost:8080/test", result);
    }
}