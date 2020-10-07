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

import org.apache.camel.Exchange;
import org.apache.camel.component.seda.BlockingQueueFactory;
import org.apache.camel.component.seda.SedaComponent;
import org.apache.camel.kafkaconnector.test.TestBlockingQueueFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertiesNameFormatsTest {

    @Test
    public void testCamelCaseFormat() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "seda://test");
        props.put("topics", "mytopic");
        props.put("camel.component.seda.defaultQueueFactory", "#class:org.apache.camel.kafkaconnector.test.TestBlockingQueueFactory");
        props.put("camel.component.seda.defaultQueueFactory.counter", "1");

        CamelSourceTask camelsourceTask = new CamelSourceTask();
        camelsourceTask.start(props);
        BlockingQueueFactory<Exchange> sedaTestQueue = ((SedaComponent) camelsourceTask.getCms().getCamelContext().getEndpoint("seda://test").getCamelContext().getComponent("seda")).getDefaultQueueFactory();
        assertEquals("org.apache.camel.kafkaconnector.test.TestBlockingQueueFactory", sedaTestQueue.getClass().getName());
        assertEquals(1, ((TestBlockingQueueFactory)sedaTestQueue).getCounter());
        camelsourceTask.stop();
    }

    @Test
    public void testDashSeparatedFormat() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "seda://test");
        props.put("topics", "mytopic");
        props.put("camel.component.seda.default-queue-factory", "#class:org.apache.camel.kafkaconnector.test.TestBlockingQueueFactory");
        props.put("camel.component.seda.default-queue-factory.counter", "1");

        CamelSourceTask camelsourceTask = new CamelSourceTask();
        camelsourceTask.start(props);
        BlockingQueueFactory<Exchange> sedaTestQueue = ((SedaComponent) camelsourceTask.getCms().getCamelContext().getEndpoint("seda://test").getCamelContext().getComponent("seda")).getDefaultQueueFactory();
        assertEquals("org.apache.camel.kafkaconnector.test.TestBlockingQueueFactory", sedaTestQueue.getClass().getName());
        assertEquals(1, ((TestBlockingQueueFactory)sedaTestQueue).getCounter());
        camelsourceTask.stop();
    }
}

