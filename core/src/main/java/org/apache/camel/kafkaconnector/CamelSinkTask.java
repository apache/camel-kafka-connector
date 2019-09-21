/**
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

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.kafkaconnector.utils.CamelMainSupport;
import org.apache.camel.support.DefaultExchange;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CamelSinkTask extends SinkTask {
    private static Logger log = LoggerFactory.getLogger(CamelSinkTask.class);

    public static final String KAFKA_RECORD_KEY_HEADER = "camel.kafka.connector.record.key";
    private static final String LOCAL_URL = "direct:start";
    private static final String HEADER_CAMEL_PREFIX = "CamelHeader";
    private static final String PROPERTY_CAMEL_PREFIX = "CamelProperty";

    private CamelMainSupport cms;
    private ProducerTemplate producer;
    private CamelSinkConnectorConfig config;

    @Override
    public String version() {
        return new CamelSinkConnector().version();
    }

    @Override
    public void start(Map<String, String> props) {
        try {
            log.info("Starting CamelSinkTask connector task");
            config = new CamelSinkConnectorConfig(props);
            final String remoteUrl = config.getString(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF);
            final String marshaller = config.getString(CamelSinkConnectorConfig.CAMEL_SINK_MARSHAL_CONF);

            cms = new CamelMainSupport(props, LOCAL_URL, remoteUrl, marshaller, null);

            producer = cms.createProducerTemplate();

            cms.start();
            log.info("CamelSinkTask connector task started");
        } catch (Exception e) {
            throw new ConnectException("Failed to create and start Camel context", e);
        }
    }

    @Override
    public void put(Collection<SinkRecord> sinkRecords) {
        Map<String, Object> headers = new HashMap<String, Object>();
        Exchange exchange = new DefaultExchange(producer.getCamelContext());
        for (SinkRecord record : sinkRecords) {
            headers.put(KAFKA_RECORD_KEY_HEADER, record.key());
            for (Iterator iterator = record.headers().iterator(); iterator.hasNext();) {
                Header header = (Header)iterator.next();
                if (header.key().startsWith(HEADER_CAMEL_PREFIX)) {
                    addHeader(headers, header);
                } else if (header.key().startsWith(PROPERTY_CAMEL_PREFIX)) {
                    addProperty(exchange, header);
                }
            }
            exchange.getMessage().setHeaders(headers);
            exchange.getMessage().setBody(record.value());
            log.debug("Sending {} to {}", exchange, LOCAL_URL);
            producer.send(LOCAL_URL, exchange);
        }
    }

    @Override
    public void stop() {
        try {
            log.info("Stopping CamelSinkTask connector task");
            cms.stop();
        } catch (Exception e) {
            throw new ConnectException("Failed to stop Camel context", e);
        } finally {
            log.info("CamelSinkTask connector task stopped");
        }
    }

    private void addHeader(Map<String, Object> map, Header singleHeader) {
        Schema schema = singleHeader.schema();
        if (schema.type().getName().equals(Schema.STRING_SCHEMA.type().getName())) {
            map.put(singleHeader.key(), (String)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.BOOLEAN_SCHEMA.type().getName())) {
            map.put(singleHeader.key(), (Boolean)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT32_SCHEMA.type().getName())) {
            map.put(singleHeader.key(), singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.BYTES_SCHEMA.type().getName())) {
            map.put(singleHeader.key(), ((byte[])singleHeader.value()));
        } else if (schema.type().getName().equalsIgnoreCase(Schema.FLOAT32_SCHEMA.type().getName())) {
            map.put(singleHeader.key(), ((float)singleHeader.value()));
        } else if (schema.type().getName().equalsIgnoreCase(Schema.FLOAT64_SCHEMA.type().getName())) {
            map.put(singleHeader.key(), ((double)singleHeader.value()));
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT16_SCHEMA.type().getName())) {
            map.put(singleHeader.key(), ((short)singleHeader.value()));
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT64_SCHEMA.type().getName())) {
            map.put(singleHeader.key(), ((long)singleHeader.value()));
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT8_SCHEMA.type().getName())) {
            map.put(singleHeader.key(), ((byte)singleHeader.value()));
        }
    }

    private void addProperty(Exchange exchange, Header singleHeader) {
        Schema schema = singleHeader.schema();
        if (schema.type().getName().equals(Schema.STRING_SCHEMA.type().getName())) {
            exchange.getProperties().put(singleHeader.key(), (String)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.BOOLEAN_SCHEMA.type().getName())) {
            exchange.getProperties().put(singleHeader.key(), (Boolean)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT32_SCHEMA.type().getName())) {
            exchange.getProperties().put(singleHeader.key(), singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.BYTES_SCHEMA.type().getName())) {
            exchange.getProperties().put(singleHeader.key(), ((byte[])singleHeader.value()));
        } else if (schema.type().getName().equalsIgnoreCase(Schema.FLOAT32_SCHEMA.type().getName())) {
            exchange.getProperties().put(singleHeader.key(), ((float)singleHeader.value()));
        } else if (schema.type().getName().equalsIgnoreCase(Schema.FLOAT64_SCHEMA.type().getName())) {
            exchange.getProperties().put(singleHeader.key(), ((double)singleHeader.value()));
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT16_SCHEMA.type().getName())) {
            exchange.getProperties().put(singleHeader.key(), ((short)singleHeader.value()));
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT64_SCHEMA.type().getName())) {
            exchange.getProperties().put(singleHeader.key(), ((long)singleHeader.value()));
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT8_SCHEMA.type().getName())) {
            exchange.getProperties().put(singleHeader.key(), ((byte)singleHeader.value()));
        }
    }

    public CamelMainSupport getCms() {
        return cms;
    }
}
