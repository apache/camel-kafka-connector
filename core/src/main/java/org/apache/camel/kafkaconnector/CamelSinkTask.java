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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExtendedCamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.kafkaconnector.utils.CamelKafkaConnectDataformat;
import org.apache.camel.kafkaconnector.utils.CamelMainSupport;
import org.apache.camel.kafkaconnector.utils.TaskHelper;
import org.apache.camel.support.DefaultExchange;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.connect.data.Decimal;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelSinkTask extends SinkTask {
    public static final String KAFKA_RECORD_KEY_HEADER = "camel.kafka.connector.record.key";
    public static final String HEADER_CAMEL_PREFIX = "CamelHeader.";
    public static final String PROPERTY_CAMEL_PREFIX = "CamelProperty.";

    private static final String CAMEL_SINK_ENDPOINT_PROPERTIES_PREFIX = "camel.sink.endpoint.";
    private static final String CAMEL_SINK_PATH_PROPERTIES_PREFIX = "camel.sink.path.";

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkTask.class);

    private static final String LOCAL_URL = "direct:start";


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
            LOG.info("Starting CamelSinkTask connector task");
            Map<String, String> actualProps = TaskHelper.mergeProperties(getDefaultConfig(), props);
            config = getCamelSinkConnectorConfig(actualProps);

            String remoteUrl = config.getString(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF);
            final String marshaller = config.getString(CamelSinkConnectorConfig.CAMEL_SINK_MARSHAL_CONF);
            final String unmarshaller = config.getString(CamelSinkConnectorConfig.CAMEL_SINK_UNMARSHAL_CONF);
            List<CamelKafkaConnectDataformat> dataformats = new LinkedList<>();
            if (unmarshaller != null) {
                dataformats.add(new CamelKafkaConnectDataformat(unmarshaller, CamelKafkaConnectDataformat.CamelKafkaConnectDataformatKind.UNMARSHALL));
            }
            if (marshaller != null) {
                dataformats.add(new CamelKafkaConnectDataformat(marshaller, CamelKafkaConnectDataformat.CamelKafkaConnectDataformatKind.MARSHALL));
            }
            final int size = config.getInt(CamelSinkConnectorConfig.CAMEL_SINK_AGGREGATE_SIZE_CONF);
            final long timeout = config.getLong(CamelSinkConnectorConfig.CAMEL_SINK_AGGREGATE_TIMEOUT_CONF);

            CamelContext camelContext = new DefaultCamelContext();
            if (remoteUrl == null) {
                remoteUrl = TaskHelper.buildUrl(camelContext.adapt(ExtendedCamelContext.class).getRuntimeCamelCatalog(),
                                                actualProps,
                                                config.getString(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF),
                                                CAMEL_SINK_ENDPOINT_PROPERTIES_PREFIX,
                                                CAMEL_SINK_PATH_PROPERTIES_PREFIX);
            }

            cms = new CamelMainSupport(actualProps, LOCAL_URL, remoteUrl, dataformats, size, timeout, camelContext);

            producer = cms.createProducerTemplate();

            cms.start();
            LOG.info("CamelSinkTask connector task started");
        } catch (Exception e) {
            throw new ConnectException("Failed to create and start Camel context", e);
        }
    }

    protected CamelSinkConnectorConfig getCamelSinkConnectorConfig(Map<String, String> props) {
        return new CamelSinkConnectorConfig(props);
    }

    protected Map<String, String> getDefaultConfig() {
        return Collections.EMPTY_MAP;
    }

    protected static String getCamelSinkEndpointConfigPrefix() {
        return CAMEL_SINK_ENDPOINT_PROPERTIES_PREFIX;
    }

    protected static String getCamelSinkPathConfigPrefix() {
        return CAMEL_SINK_PATH_PROPERTIES_PREFIX;
    }

    @Override
    public void put(Collection<SinkRecord> sinkRecords) {
        for (SinkRecord record : sinkRecords) {
            TaskHelper.logRecordContent(LOG, record, config);
            Map<String, Object> headers = new HashMap<String, Object>();
            Exchange exchange = new DefaultExchange(producer.getCamelContext());
            headers.put(KAFKA_RECORD_KEY_HEADER, record.key());
            for (Iterator<Header> iterator = record.headers().iterator(); iterator.hasNext();) {
                Header header = (Header)iterator.next();
                if (header.key().startsWith(HEADER_CAMEL_PREFIX)) {
                    addHeader(headers, header);
                } else if (header.key().startsWith(PROPERTY_CAMEL_PREFIX)) {
                    addProperty(exchange, header);
                }
            }
            exchange.getMessage().setHeaders(headers);
            exchange.getMessage().setBody(record.value());

            LOG.debug("Sending exchange {} to {}", exchange.getExchangeId(), LOCAL_URL);
            producer.send(LOCAL_URL, exchange);

            if (exchange.isFailed()) {
                throw new ConnectException("Exchange delivery has failed!", exchange.getException());
            }
        }
    }

    @Override
    public void stop() {
        LOG.info("Stopping CamelSinkTask connector task");
        try {
            if (cms != null) {
                /*
                  If the CamelMainSupport instance fails to be instantiated (ie.: due to missing classes or similar
                  issues) then it won't be assigned and de-referencing it could cause an NPE.
                 */
                cms.stop();
            } else {
                LOG.warn("A fatal exception may have occurred and the Camel main was not created");
            }
        } catch (Exception e) {
            throw new ConnectException("Failed to stop Camel context", e);
        } finally {
            LOG.info("CamelSinkTask connector task stopped");
        }
    }

    private void addHeader(Map<String, Object> map, Header singleHeader) {
        String camelHeaderKey = StringUtils.removeStart(singleHeader.key(), HEADER_CAMEL_PREFIX);
        Schema schema = singleHeader.schema();
        if (schema.type().getName().equals(Schema.STRING_SCHEMA.type().getName())) {
            map.put(camelHeaderKey, (String)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.BOOLEAN_SCHEMA.type().getName())) {
            map.put(camelHeaderKey, (Boolean)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT32_SCHEMA.type().getName())) {
            map.put(camelHeaderKey, singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.BYTES_SCHEMA.type().getName())) {
            if (Decimal.class.getCanonicalName().equals(schema.name())) {
                map.put(camelHeaderKey, Decimal.toLogical(schema, (byte[])singleHeader.value()));
            } else {
                map.put(camelHeaderKey, (byte[])singleHeader.value());
            }
        } else if (schema.type().getName().equalsIgnoreCase(Schema.FLOAT32_SCHEMA.type().getName())) {
            map.put(camelHeaderKey, (float)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.FLOAT64_SCHEMA.type().getName())) {
            map.put(camelHeaderKey, (double)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT16_SCHEMA.type().getName())) {
            map.put(camelHeaderKey, (short)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT64_SCHEMA.type().getName())) {
            map.put(camelHeaderKey, (long)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT8_SCHEMA.type().getName())) {
            map.put(camelHeaderKey, (byte)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.STRING_SCHEMA).type().getName())) {
            map.put(camelHeaderKey, (Map<?, ?>)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(SchemaBuilder.array(Schema.STRING_SCHEMA).type().getName())) {
            map.put(camelHeaderKey, (List<?>)singleHeader.value());
        }
    }

    private void addProperty(Exchange exchange, Header singleHeader) {
        String camelPropertyKey = StringUtils.removeStart(singleHeader.key(), PROPERTY_CAMEL_PREFIX);
        Schema schema = singleHeader.schema();
        if (schema.type().getName().equals(Schema.STRING_SCHEMA.type().getName())) {
            exchange.getProperties().put(camelPropertyKey, (String)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.BOOLEAN_SCHEMA.type().getName())) {
            exchange.getProperties().put(camelPropertyKey, (Boolean)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT32_SCHEMA.type().getName())) {
            exchange.getProperties().put(camelPropertyKey, singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.BYTES_SCHEMA.type().getName())) {
            exchange.getProperties().put(camelPropertyKey, (byte[])singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.FLOAT32_SCHEMA.type().getName())) {
            exchange.getProperties().put(camelPropertyKey, (float)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.FLOAT64_SCHEMA.type().getName())) {
            exchange.getProperties().put(camelPropertyKey, (double)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT16_SCHEMA.type().getName())) {
            exchange.getProperties().put(camelPropertyKey, (short)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT64_SCHEMA.type().getName())) {
            exchange.getProperties().put(camelPropertyKey, (long)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(Schema.INT8_SCHEMA.type().getName())) {
            exchange.getProperties().put(camelPropertyKey, (byte)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(SchemaBuilder.map(Schema.STRING_SCHEMA, Schema.STRING_SCHEMA).type().getName())) {
            exchange.getProperties().put(camelPropertyKey, (Map<?, ?>)singleHeader.value());
        } else if (schema.type().getName().equalsIgnoreCase(SchemaBuilder.array(Schema.STRING_SCHEMA).type().getName())) {
            exchange.getProperties().put(camelPropertyKey, (List<?>)singleHeader.value());
        }
    }

    public CamelMainSupport getCms() {
        return cms;
    }
}
