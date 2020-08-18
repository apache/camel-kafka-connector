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

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.camel.component.hl7.HL7DataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.kafkaconnector.utils.CamelKafkaConnectDataformat;
import org.apache.camel.kafkaconnector.utils.CamelMainSupport;
import org.apache.camel.model.dataformat.SyslogDataFormat;
import org.apache.kafka.connect.errors.ConnectException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataFormatTest {

    @Test
    public void testDataFormatSource() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct://test");
        props.put("topics", "mytopic");
        props.put("camel.source.marshal", "syslog");
        props.put("camel.source.unmarshal", "hl7");

        CamelSourceTask camelsourceTask = new CamelSourceTask();
        camelsourceTask.start(props);
        camelsourceTask.stop();
    }

    @Test
    public void testDataFormatSink() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "direct://test");
        props.put("camel.sink.kafka.topic", "mytopic");
        props.put("camel.sink.unmarshal", "syslog");
        props.put("camel.source.marshal", "hl7");

        CamelSinkTask camelsinkTask = new CamelSinkTask();
        camelsinkTask.start(props);
        camelsinkTask.stop();
    }

    @Test
    public void testDataFormatNotFound() {
        Map<String, String> props = new HashMap<>();
        props.put("camel.sink.url", "direct://test");
        props.put("camel.sink.kafka.topic", "mytopic");
        props.put("camel.sink.marshal", "missingDataformat");

        CamelSinkTask camelsinkTask = new CamelSinkTask();
        assertThrows(ConnectException.class, () -> camelsinkTask.start(props));
        // No need to check the stop method. The error is already thrown/caught during startup.
    }

    @Test
    public void testMultipleDataFormatConfigured() throws Exception {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct://test");
        props.put("topics", "mytopic");
        props.put("camel.source.marshal", "hl7");
        props.put("camel.source.unmarshal", "syslog");

        List<CamelKafkaConnectDataformat> dataformats = new LinkedList<>();
        dataformats.add(new CamelKafkaConnectDataformat("hl7", CamelKafkaConnectDataformat.CamelKafkaConnectDataformatKind.MARSHALL));
        dataformats.add(new CamelKafkaConnectDataformat("syslog", CamelKafkaConnectDataformat.CamelKafkaConnectDataformatKind.UNMARSHALL));
        DefaultCamelContext dcc = new DefaultCamelContext();
        CamelMainSupport cms = new CamelMainSupport(props, "direct://start", "log://test", dataformats, 10, 500, dcc);

        HL7DataFormat hl7Df = new HL7DataFormat();
        hl7Df.setValidate(false);
        dcc.getRegistry().bind("hl7", hl7Df);

        SyslogDataFormat syslogDf = new SyslogDataFormat();
        dcc.getRegistry().bind("syslog", syslogDf);

        cms.start();
        HL7DataFormat hl7dfLoaded = dcc.getRegistry().lookupByNameAndType("hl7", HL7DataFormat.class);
        assertNotNull(hl7dfLoaded);
        SyslogDataFormat syslogDfLoaded = dcc.getRegistry().lookupByNameAndType("syslog", SyslogDataFormat.class);
        assertNotNull(syslogDfLoaded);
        cms.stop();
    }

    @Test
    public void testDataFormatLookUpInRegistry() throws Exception {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct://test");
        props.put("topics", "mytopic");
        props.put("camel.source.marshal", "hl7");

        List<CamelKafkaConnectDataformat> dataformats = Collections.singletonList(new CamelKafkaConnectDataformat("hl7", CamelKafkaConnectDataformat.CamelKafkaConnectDataformatKind.MARSHALL));
        DefaultCamelContext dcc = new DefaultCamelContext();
        CamelMainSupport cms = new CamelMainSupport(props, "direct://start", "log://test", dataformats, 10, 500, dcc);

        HL7DataFormat hl7df = new HL7DataFormat();
        hl7df.setValidate(false);
        dcc.getRegistry().bind("hl7", hl7df);

        cms.start();
        HL7DataFormat hl7dfLoaded = dcc.getRegistry().lookupByNameAndType("hl7", HL7DataFormat.class);
        assertFalse(hl7dfLoaded.isValidate());
        cms.stop();
    }

    @Test
    public void testDataFormatConfiguration() throws Exception {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct://test");
        props.put("topics", "mytopic");
        props.put("camel.source.marshal", "hl7");
        props.put("camel.dataformat.hl7.validate", "false");

        List<CamelKafkaConnectDataformat> dataformats = Collections.singletonList(new CamelKafkaConnectDataformat("hl7", CamelKafkaConnectDataformat.CamelKafkaConnectDataformatKind.MARSHALL));
        DefaultCamelContext dcc = new DefaultCamelContext();
        CamelMainSupport cms = new CamelMainSupport(props, "direct://start", "log://test", dataformats, 10, 500, dcc);

        cms.start();
        HL7DataFormat hl7dfLoaded = dcc.getRegistry().lookupByNameAndType("hl7", HL7DataFormat.class);
        assertTrue(hl7dfLoaded.isValidate());
        cms.stop();
    }
}
