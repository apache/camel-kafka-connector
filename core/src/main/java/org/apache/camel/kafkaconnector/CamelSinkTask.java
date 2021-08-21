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
import java.util.Map;
import java.util.Objects;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.kafkaconnector.utils.CamelKafkaConnectMain;
import org.apache.camel.kafkaconnector.utils.TaskHelper;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.util.StringHelper;
import org.apache.kafka.connect.data.Decimal;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.sink.ErrantRecordReporter;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelSinkTask extends SinkTask {
    public static final String KAMELET_SINK_TEMPLATE_PARAMETERS_PREFIX = "camel.kamelet.ckcSink.";

    public static final String KAFKA_RECORD_KEY_HEADER = "camel.kafka.connector.record.key";
    public static final String HEADER_CAMEL_PREFIX = "CamelHeader.";
    public static final String PROPERTY_CAMEL_PREFIX = "CamelProperty.";

    private static final String CAMEL_SINK_ENDPOINT_PROPERTIES_PREFIX = "camel.sink.endpoint.";
    private static final String CAMEL_SINK_PATH_PROPERTIES_PREFIX = "camel.sink.path.";

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkTask.class);

    private static final String LOCAL_URL = "direct:start";
    private static final String DEFAULT_KAMELET_CKC_SINK = "kamelet:ckcSink";
    private ErrantRecordReporter reporter;

    private CamelKafkaConnectMain cms;
    private ProducerTemplate producer;
    private Endpoint localEndpoint;

    private LoggingLevel loggingLevel = LoggingLevel.OFF;
    private boolean mapProperties;
    private boolean mapHeaders;

    @Override
    public String version() {
        return VersionUtil.getVersion();
    }

    @Override
    public void start(Map<String, String> props) {
        try {
            LOG.info("Starting CamelSinkTask connector task");
            Map<String, String> actualProps = TaskHelper.combineDefaultAndLoadedProperties(getDefaultConfig(), props);
            CamelSinkConnectorConfig config = getCamelSinkConnectorConfig(actualProps);

            if (context != null) {
                try {
                    reporter = context.errantRecordReporter();
                } catch (NoSuchMethodError | NoClassDefFoundError e) {
                    LOG.warn("Unable to instantiate ErrantRecordReporter.  Method 'SinkTaskContext.errantRecordReporter' does not exist.");
                    reporter = null;
                }
            }

            String levelStr = config.getString(CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF);
            try {
                loggingLevel = LoggingLevel.valueOf(levelStr.toUpperCase());
            } catch (Exception e) {
                LOG.debug("Invalid value {} for {} property", levelStr.toUpperCase(), CamelSinkConnectorConfig.CAMEL_SINK_CONTENT_LOG_LEVEL_CONF);
            }

            String remoteUrl = config.getString(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF);
            final String marshaller = config.getString(CamelSinkConnectorConfig.CAMEL_SINK_MARSHAL_CONF);
            final String unmarshaller = config.getString(CamelSinkConnectorConfig.CAMEL_SINK_UNMARSHAL_CONF);
            final int size = config.getInt(CamelSinkConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF);
            final long timeout = config.getLong(CamelSinkConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_CONF);
            final int maxRedeliveries = config.getInt(CamelSinkConnectorConfig.CAMEL_CONNECTOR_ERROR_HANDLER_MAXIMUM_REDELIVERIES_CONF);
            final long redeliveryDelay = config.getLong(CamelSinkConnectorConfig.CAMEL_CONNECTOR_ERROR_HANDLER_REDELIVERY_DELAY_CONF);
            final String errorHandler = config.getString(CamelSinkConnectorConfig.CAMEL_CONNECTOR_ERROR_HANDLER_CONF);
            final Boolean idempotencyEnabled = config.getBoolean(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_CONF);
            final String expressionType = config.getString(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF);
            final String expressionHeader = config.getString(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_CONF);
            final int memoryDimension = config.getInt(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_MEMORY_DIMENSION_CONF);
            final String idempotentRepositoryType = config.getString(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_CONF);
            final String idempotentRepositoryKafkaTopic = config.getString(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_TOPIC_CONF);
            final String idempotentRepositoryBootstrapServers = config.getString(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_BOOTSTRAP_SERVERS_CONF);
            final int idempotentRepositoryKafkaMaxCacheSize = config.getInt(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_MAX_CACHE_SIZE_CONF);
            final int idempotentRepositoryKafkaPollDuration = config.getInt(CamelSinkConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_POLL_DURATION_CONF);
            final String headersRemovePattern = config.getString(CamelSinkConnectorConfig.CAMEL_CONNECTOR_REMOVE_HEADERS_PATTERN_CONF);
            mapProperties = config.getBoolean(CamelSinkConnectorConfig.CAMEL_CONNECTOR_MAP_PROPERTIES_CONF);
            mapHeaders = config.getBoolean(CamelSinkConnectorConfig.CAMEL_CONNECTOR_MAP_HEADERS_CONF);
            
            CamelContext camelContext = new DefaultCamelContext();
            if (remoteUrl == null) {
                remoteUrl = TaskHelper.buildUrl(camelContext,
                                                actualProps,
                                                config.getString(CamelSinkConnectorConfig.CAMEL_SINK_COMPONENT_CONF),
                                                CAMEL_SINK_ENDPOINT_PROPERTIES_PREFIX,
                                                CAMEL_SINK_PATH_PROPERTIES_PREFIX);
            }
            actualProps.put(KAMELET_SINK_TEMPLATE_PARAMETERS_PREFIX + "toUrl", remoteUrl);

            cms = CamelKafkaConnectMain.builder(LOCAL_URL, getSinkKamelet())
                .withProperties(actualProps)
                .withUnmarshallDataFormat(unmarshaller)
                .withMarshallDataFormat(marshaller)
                .withAggregationSize(size)
                .withAggregationTimeout(timeout)
                .withErrorHandler(errorHandler)
                .withMaxRedeliveries(maxRedeliveries)
                .withRedeliveryDelay(redeliveryDelay)
                .withIdempotencyEnabled(idempotencyEnabled)
                .withExpressionType(expressionType)
                .withExpressionHeader(expressionHeader)
                .withMemoryDimension(memoryDimension)
                .withIdempotentRepositoryType(idempotentRepositoryType)
                .withIdempotentRepositoryTopicName(idempotentRepositoryKafkaTopic)
                .withIdempotentRepositoryKafkaServers(idempotentRepositoryBootstrapServers)
                .withIdempotentRepositoryKafkaMaxCacheSize(idempotentRepositoryKafkaMaxCacheSize)
                .withIdempotentRepositoryKafkaPollDuration(idempotentRepositoryKafkaPollDuration)
                .withHeadersExcludePattern(headersRemovePattern)
                .build(camelContext);

            cms.start();

            producer = cms.getProducerTemplate();
            localEndpoint = cms.getCamelContext().getEndpoint(LOCAL_URL);

            LOG.info("CamelSinkTask connector task started");
        } catch (Exception e) {
            throw new ConnectException("Failed to create and start Camel context", e);
        }
    }

    @NotNull
    protected String getSinkKamelet() {
        return DEFAULT_KAMELET_CKC_SINK;
    }

    protected CamelSinkConnectorConfig getCamelSinkConnectorConfig(Map<String, String> props) {
        return new CamelSinkConnectorConfig(props);
    }

    protected Map<String, String> getDefaultConfig() {
        return Collections.emptyMap();
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
            TaskHelper.logRecordContent(LOG, loggingLevel, record);

            Exchange exchange = new DefaultExchange(producer.getCamelContext());
            exchange.getMessage().setBody(record.value());
            exchange.getMessage().setHeader(KAFKA_RECORD_KEY_HEADER, record.key());

            for (Header header : record.headers()) {
                if (header.key().startsWith(HEADER_CAMEL_PREFIX)) {
                    if (mapHeaders) {
                        mapHeader(header, HEADER_CAMEL_PREFIX, exchange.getMessage().getHeaders());
                    }
                } else if (header.key().startsWith(PROPERTY_CAMEL_PREFIX)) {
                    if (mapProperties) {
                        mapHeader(header, PROPERTY_CAMEL_PREFIX, exchange.getProperties());
                    }
                }
            }

            LOG.debug("Sending exchange {} to {}", exchange.getExchangeId(), LOCAL_URL);
            producer.send(localEndpoint, exchange);

            if (exchange.isFailed()) {
                if (reporter == null) {
                    LOG.warn("A delivery has failed and the error reporting is NOT enabled. Records may be lost or ignored");
                    throw new ConnectException("Exchange delivery has failed!", exchange.getException());
                }

                LOG.warn("A delivery has failed and the error reporting is enabled. Sending record to the DLQ");
                reporter.report(record, exchange.getException());
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

    private static void mapHeader(Header header, String prefix, Map<String, Object> destination) {
        final String key = StringHelper.after(header.key(), prefix, header.key());
        final Schema schema = header.schema();

        if (schema.type().equals(Schema.BYTES_SCHEMA.type()) && Objects.equals(schema.name(), Decimal.LOGICAL_NAME)) {
            destination.put(key, Decimal.toLogical(schema, (byte[]) header.value()));
        } else {
            destination.put(key, header.value());
        }
    }

    CamelKafkaConnectMain getCms() {
        return cms;
    }

    public LoggingLevel getLoggingLevel() {
        return loggingLevel;
    }

    public void setLoggingLevel(LoggingLevel loggingLevel) {
        this.loggingLevel = loggingLevel;
    }
}
