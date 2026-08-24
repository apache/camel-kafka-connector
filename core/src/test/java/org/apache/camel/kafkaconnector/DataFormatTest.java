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

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.component.hl7.HL7DataFormat;
import org.apache.camel.component.syslog.SyslogDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.kafkaconnector.utils.CamelKafkaConnectMain;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.support.service.ServiceSupport;
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
        camelsinkTask.stop();
    }

    @Test
    public void testMultipleDataFormatConfigured() throws Exception {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct://test");
        props.put("topics", "mytopic");
        props.put("camel.source.marshal", "hl7");
        props.put("camel.source.unmarshal", "syslog");
        DefaultCamelContext dcc = new DefaultCamelContext();

        CamelKafkaConnectMain cms = CamelKafkaConnectMain.builder("direct://start", "log://test")
            .withProperties(props)
            .withUnmarshallDataFormat("syslog")
            .withMarshallDataFormat("hl7")
            .build(dcc);

        HL7DataFormat hl7Df = new HL7DataFormat();
        hl7Df.setValidate(false);
        dcc.getRegistry().bind("hl7", hl7Df);

        SyslogDataFormat syslogDf = new SyslogDataFormat();
        dcc.getRegistry().bind("syslog", syslogDf);

        cms.start();
        HL7DataFormat hl7dfLoaded = (HL7DataFormat)dcc.resolveDataFormat("hl7");
        assertNotNull(hl7dfLoaded);
        SyslogDataFormat syslogDfLoaded = (SyslogDataFormat)dcc.resolveDataFormat("syslog");
        assertNotNull(syslogDfLoaded);
        cms.stop();
    }

    @Test
    public void testDataFormatLookUpInRegistry() throws Exception {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "direct://test");
        props.put("topics", "mytopic");
        props.put("camel.source.marshal", "hl7");

        DefaultCamelContext dcc = new DefaultCamelContext();
        CamelKafkaConnectMain cms = CamelKafkaConnectMain.builder("direct://start", "log://test")
            .withProperties(props)
            .withMarshallDataFormat("hl7")
            .build(dcc);

        HL7DataFormat hl7df = new HL7DataFormat();
        hl7df.setValidate(false);
        dcc.getRegistry().bind("hl7", hl7df);

        cms.start();
        HL7DataFormat hl7dfLoaded = (HL7DataFormat)dcc.resolveDataFormat("hl7");
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

        DefaultCamelContext dcc = new DefaultCamelContext();

        CamelKafkaConnectMain cms = CamelKafkaConnectMain.builder("direct://start", "log://test")
            .withProperties(props)
            .withMarshallDataFormat("hl7")
            .build(dcc);

        cms.start();
        HL7DataFormat hl7dfLoaded = (HL7DataFormat)dcc.resolveDataFormat("hl7");
        assertFalse(hl7dfLoaded.isValidate());
        cms.stop();
    }

    @Test
    public void testUnmarshalDataFormatIsAppliedInTheUnmarshalDirection() throws Exception {
        DirectionRecordingDataFormat dataFormat = new DirectionRecordingDataFormat();
        DefaultCamelContext dcc = new DefaultCamelContext();
        dcc.getRegistry().bind("directionRecording", dataFormat);

        CamelKafkaConnectMain cms = CamelKafkaConnectMain.builder("direct://start", "log://test")
            .withProperties(new HashMap<String, String>())
            .withUnmarshallDataFormat("directionRecording")
            .build(dcc);

        cms.start();
        cms.getProducerTemplate().sendBody("direct://start", "payload");
        cms.stop();

        assertTrue(dataFormat.isUnmarshalled(), "camel.*.unmarshal must apply the data format in the unmarshal direction");
        assertFalse(dataFormat.isMarshalled(), "camel.*.unmarshal must not apply the data format in the marshal direction");
    }

    @Test
    public void testMarshalDataFormatIsAppliedInTheMarshalDirection() throws Exception {
        DirectionRecordingDataFormat dataFormat = new DirectionRecordingDataFormat();
        DefaultCamelContext dcc = new DefaultCamelContext();
        dcc.getRegistry().bind("directionRecording", dataFormat);

        CamelKafkaConnectMain cms = CamelKafkaConnectMain.builder("direct://start", "log://test")
            .withProperties(new HashMap<String, String>())
            .withMarshallDataFormat("directionRecording")
            .build(dcc);

        cms.start();
        cms.getProducerTemplate().sendBody("direct://start", "payload");
        cms.stop();

        assertTrue(dataFormat.isMarshalled(), "camel.*.marshal must apply the data format in the marshal direction");
        assertFalse(dataFormat.isUnmarshalled(), "camel.*.marshal must not apply the data format in the unmarshal direction");
    }

    /**
     * A data format that only records which direction it was invoked in, so that a test can assert that the route
     * template applies the operation the configuration actually names.
     */
    private static final class DirectionRecordingDataFormat extends ServiceSupport implements DataFormat {

        private boolean marshalled;
        private boolean unmarshalled;

        @Override
        public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
            marshalled = true;
            stream.write(exchange.getContext().getTypeConverter().mandatoryConvertTo(byte[].class, exchange, graph));
        }

        @Override
        public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
            unmarshalled = true;
            return exchange.getContext().getTypeConverter().mandatoryConvertTo(String.class, exchange, stream);
        }

        @Override
        public Object unmarshal(Exchange exchange, Object body) throws Exception {
            unmarshalled = true;
            return exchange.getContext().getTypeConverter().mandatoryConvertTo(String.class, exchange, body);
        }

        boolean isMarshalled() {
            return marshalled;
        }

        boolean isUnmarshalled() {
            return unmarshalled;
        }
    }
}
