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

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExtendedExchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.PollingConsumer;
import org.apache.camel.StreamCache;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.kafkaconnector.utils.CamelKafkaConnectMain;
import org.apache.camel.kafkaconnector.utils.SchemaHelper;
import org.apache.camel.kafkaconnector.utils.TaskHelper;
import org.apache.camel.support.UnitOfWorkHelper;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.connect.data.Decimal;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.jctools.queues.MessagePassingQueue;
import org.jctools.queues.SpscArrayQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CamelSourceTask extends SourceTask {
    public static final String KAMELET_SOURCE_TEMPLATE_PARAMETERS_PREFIX = "camel.kamelet.ckcSource.";
    public static final String HEADER_CAMEL_PREFIX = "CamelHeader.";
    public static final String PROPERTY_CAMEL_PREFIX = "CamelProperty.";

    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceTask.class);

    private static final String CAMEL_SOURCE_ENDPOINT_PROPERTIES_PREFIX = "camel.source.endpoint.";
    private static final String CAMEL_SOURCE_PATH_PROPERTIES_PREFIX = "camel.source.path.";

    private static final String LOCAL_URL = "seda:end";
    private static final String DEFAULT_KAMELET_CKC_SOURCE = "kamelet:ckcSource";

    private CamelKafkaConnectMain cms;
    private PollingConsumer consumer;
    private String[] topics;
    private Long maxBatchPollSize;
    private Long maxPollDuration;
    private Integer maxNotCommittedRecords;
    private String camelMessageHeaderKey;
    private LoggingLevel loggingLevel = LoggingLevel.OFF;
    private Exchange[] exchangesWaitingForAck;
    //the assumption is that at most 1 thread is running poll() method and at most 1 thread is running commitRecord()
    private SpscArrayQueue<Integer> freeSlots;
    private boolean mapProperties;
    private boolean mapHeaders;


    @Override
    public String version() {
        return VersionUtil.getVersion();
    }

    @Override
    public void start(Map<String, String> props) {
        try {
            LOG.info("Starting CamelSourceTask connector task");
            Map<String, String> actualProps = TaskHelper.combineDefaultAndLoadedProperties(getDefaultConfig(), props);
            CamelSourceConnectorConfig config = getCamelSourceConnectorConfig(actualProps);

            String levelStr = config.getString(CamelSourceConnectorConfig.CAMEL_SOURCE_CONTENT_LOG_LEVEL_CONF);
            try {
                loggingLevel = LoggingLevel.valueOf(levelStr.toUpperCase());
            } catch (Exception e) {
                LOG.error("Invalid value {} for {} property", levelStr.toUpperCase(), CamelSourceConnectorConfig.CAMEL_SOURCE_CONTENT_LOG_LEVEL_CONF);
            }

            maxBatchPollSize = config.getLong(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_BATCH_POLL_SIZE_CONF);
            maxPollDuration = config.getLong(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_POLL_DURATION_CONF);
            maxNotCommittedRecords = config.getInt(CamelSourceConnectorConfig.CAMEL_SOURCE_MAX_NOT_COMMITTED_RECORDS_CONF);

            camelMessageHeaderKey = config.getString(CamelSourceConnectorConfig.CAMEL_SOURCE_MESSAGE_HEADER_KEY_CONF);

            String remoteUrl = config.getString(CamelSourceConnectorConfig.CAMEL_SOURCE_URL_CONF);
            final String unmarshaller = config.getString(CamelSourceConnectorConfig.CAMEL_SOURCE_UNMARSHAL_CONF);
            final String marshaller = config.getString(CamelSourceConnectorConfig.CAMEL_SOURCE_MARSHAL_CONF);
            final int size = config.getInt(CamelSourceConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_SIZE_CONF);
            final long timeout = config.getLong(CamelSourceConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_TIMEOUT_CONF);
            final int maxRedeliveries = config.getInt(CamelSourceConnectorConfig.CAMEL_CONNECTOR_ERROR_HANDLER_MAXIMUM_REDELIVERIES_CONF);
            final long redeliveryDelay = config.getLong(CamelSourceConnectorConfig.CAMEL_CONNECTOR_ERROR_HANDLER_REDELIVERY_DELAY_CONF);
            final String errorHandler = config.getString(CamelSourceConnectorConfig.CAMEL_CONNECTOR_ERROR_HANDLER_CONF);
            final Boolean idempotencyEnabled = config.getBoolean(CamelSourceConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_ENABLED_CONF);
            final String expressionType = config.getString(CamelSourceConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_TYPE_CONF);
            final String expressionHeader = config.getString(CamelSourceConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_EXPRESSION_HEADER_CONF);
            final int memoryDimension = config.getInt(CamelSourceConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_MEMORY_DIMENSION_CONF);
            final String idempotentRepositoryType = config.getString(CamelSourceConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_REPOSITORY_TYPE_CONF);
            final String idempotentRepositoryKafkaTopic = config.getString(CamelSourceConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_TOPIC_CONF);
            final String idempotentRepositoryBootstrapServers = config.getString(CamelSourceConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_BOOTSTRAP_SERVERS_CONF);
            final int idempotentRepositoryKafkaMaxCacheSize = config.getInt(CamelSourceConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_MAX_CACHE_SIZE_CONF);
            final int idempotentRepositoryKafkaPollDuration = config.getInt(CamelSourceConnectorConfig.CAMEL_CONNECTOR_IDEMPOTENCY_KAFKA_POLL_DURATION_CONF);
            final String headersRemovePattern = config.getString(CamelSourceConnectorConfig.CAMEL_CONNECTOR_REMOVE_HEADERS_PATTERN_CONF);
            mapProperties = config.getBoolean(CamelSourceConnectorConfig.CAMEL_CONNECTOR_MAP_PROPERTIES_CONF);
            mapHeaders = config.getBoolean(CamelSinkConnectorConfig.CAMEL_CONNECTOR_MAP_HEADERS_CONF);

            topics = config.getString(CamelSourceConnectorConfig.TOPIC_CONF).split(",");

            long pollingConsumerQueueSize = config.getLong(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_QUEUE_SIZE_CONF);
            long pollingConsumerBlockTimeout = config.getLong(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_TIMEOUT_CONF);
            boolean pollingConsumerBlockWhenFull = config.getBoolean(CamelSourceConnectorConfig.CAMEL_SOURCE_POLLING_CONSUMER_BLOCK_WHEN_FULL_CONF);
            String localUrl = getLocalUrlWithPollingOptions(pollingConsumerQueueSize, pollingConsumerBlockTimeout, pollingConsumerBlockWhenFull);

            freeSlots = new SpscArrayQueue<>(maxNotCommittedRecords);
            freeSlots.fill(new MessagePassingQueue.Supplier<Integer>() {
                int i;
                @Override
                public Integer get() {
                    return i++;
                }
            });
            //needs to be done like this because freeSlots capacity is rounded to the next power of 2 of maxNotCommittedRecords
            exchangesWaitingForAck = new Exchange[freeSlots.capacity()];

            CamelContext camelContext = new DefaultCamelContext();
            if (remoteUrl == null) {
                remoteUrl = TaskHelper.buildUrl(camelContext,
                                                actualProps,
                                                config.getString(CamelSourceConnectorConfig.CAMEL_SOURCE_COMPONENT_CONF), CAMEL_SOURCE_ENDPOINT_PROPERTIES_PREFIX,
                                                CAMEL_SOURCE_PATH_PROPERTIES_PREFIX);
            }
            actualProps.put(KAMELET_SOURCE_TEMPLATE_PARAMETERS_PREFIX + "fromUrl", remoteUrl);

            cms = CamelKafkaConnectMain.builder(getSourceKamelet(), localUrl)
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

            consumer = cms.getCamelContext().getEndpoint(localUrl).createPollingConsumer();
            consumer.start();

            cms.start();

            LOG.info("CamelSourceTask connector task started");
        } catch (Exception e) {
            throw new ConnectException("Failed to create and start Camel context", e);
        }
    }

    protected String getSourceKamelet() {
        return DEFAULT_KAMELET_CKC_SOURCE;
    }

    private long remaining(long startPollEpochMilli, long maxPollDuration)  {
        return maxPollDuration - (Instant.now().toEpochMilli() - startPollEpochMilli);
    }

    @Override
    public synchronized List<SourceRecord> poll() {
        LOG.debug("Number of records waiting an ack: {}", freeSlots.capacity() - freeSlots.size());
        final long startPollEpochMilli = Instant.now().toEpochMilli();

        long remaining = remaining(startPollEpochMilli, maxPollDuration);
        long collectedRecords = 0L;

        List<SourceRecord> records = new ArrayList<>();
        while (collectedRecords < maxBatchPollSize && freeSlots.size() >= topics.length && remaining > 0) {
            Exchange exchange = consumer.receive(remaining);
            if (exchange == null) {
                // Nothing received, abort and return what we received so far
                break;
            }

            LOG.debug("Received Exchange {} with Message {} from Endpoint {}", exchange.getExchangeId(),
                    exchange.getMessage().getMessageId(), exchange.getFromEndpoint());

            // TODO: see if there is a better way to use sourcePartition
            // an sourceOffset
            Map<String, String> sourcePartition = Collections.singletonMap("filename", exchange.getFromEndpoint().toString());
            Map<String, String> sourceOffset = Collections.singletonMap("position", exchange.getExchangeId());

            final Object messageHeaderKey = camelMessageHeaderKey != null ? exchange.getMessage().getHeader(camelMessageHeaderKey) : null;
            Object messageBodyValue = exchange.getMessage().getBody();

            final Schema messageKeySchema = messageHeaderKey != null ? SchemaHelper.buildSchemaBuilderForType(messageHeaderKey) : null;
            final Schema messageBodySchema = messageBodyValue != null ? SchemaHelper.buildSchemaBuilderForType(messageBodyValue) : null;

            final long timestamp = calculateTimestamp(exchange);

            // take in account Cached camel streams
            if (messageBodyValue instanceof StreamCache) {
                StreamCache sc = (StreamCache) messageBodyValue;
                // reset to be sure that the cache is ready to be used before sending it in the record (could be useful for SMTs)
                sc.reset();
            }
            for (String singleTopic : topics) {
                CamelSourceRecord camelRecord = new CamelSourceRecord(sourcePartition, sourceOffset, singleTopic, null, messageKeySchema,
                        messageHeaderKey, messageBodySchema, messageBodyValue, timestamp);

                if (mapHeaders) {
                    if (exchange.getMessage().hasHeaders()) {
                        setAdditionalHeaders(camelRecord, exchange.getMessage().getHeaders(), HEADER_CAMEL_PREFIX);
                    }
                }
                
                if (mapProperties) {
                    if (exchange.hasProperties()) {
                        setAdditionalHeaders(camelRecord, exchange.getProperties(), PROPERTY_CAMEL_PREFIX);
                    }
                }

                TaskHelper.logRecordContent(LOG, loggingLevel, camelRecord);
                Integer claimCheck = freeSlots.remove();
                camelRecord.setClaimCheck(claimCheck);
                exchangesWaitingForAck[claimCheck] = exchange;
                LOG.debug("Record: {}, containing data from exchange: {}, is associated with claim check number: {}", camelRecord, exchange, claimCheck);
                records.add(camelRecord);
            }
            collectedRecords++;
            remaining = remaining(startPollEpochMilli, maxPollDuration);
        }

        return records.isEmpty() ? null : records;
    }

    @Override
    public void commitRecord(SourceRecord record, RecordMetadata metadata) {
        LOG.debug("Committing record: {} with metadata: {}", record, metadata);
        ///XXX: this should be a safe cast please see: https://issues.apache.org/jira/browse/KAFKA-12391
        Integer claimCheck = ((CamelSourceRecord)record).getClaimCheck();
        LOG.debug("Committing record with claim check number: {}", claimCheck);
        Exchange correlatedExchange = exchangesWaitingForAck[claimCheck];
        try {
            UnitOfWorkHelper.doneSynchronizations(correlatedExchange, correlatedExchange.adapt(ExtendedExchange.class).handoverCompletions(), LOG);
            LOG.debug("Record with claim check number: {} committed.", claimCheck);
        } catch (Throwable t) {
            LOG.error("Exception during Unit Of Work completion: {} caused by: {}", t.getMessage(), t.getCause());
            throw new RuntimeException(t);
        } finally {
            exchangesWaitingForAck[claimCheck] = null;
            freeSlots.add(claimCheck);
            LOG.debug("Claim check number: {} freed.", claimCheck);
        }
    }

    @Override
    public void stop() {
        LOG.info("Stopping CamelSourceTask connector task");
        try {
            if (consumer != null) {
                consumer.stop();
            } else {
                LOG.warn("A critical error may have occurred and there is no consumer to stop");
            }
        } catch (Exception e) {
            LOG.error("Error stopping camel consumer: {}", e.getMessage());
        }
        try {
            /*
             * If the CamelMainSupport instance fails to be instantiated (ie.:
             * due to missing classes or similar issues) then it won't be
             * assigned and de-referencing it could cause an NPE.
             */
            if (cms != null) {
                cms.stop();
            } else {
                LOG.warn("A fatal exception may have occurred and the Camel main was not created");
            }
        } catch (Exception e) {
            throw new ConnectException("Failed to stop Camel context", e);
        } finally {
            LOG.info("CamelSourceTask connector task stopped");
        }
    }

    protected CamelSourceConnectorConfig getCamelSourceConnectorConfig(Map<String, String> props) {
        return new CamelSourceConnectorConfig(props);
    }

    protected Map<String, String> getDefaultConfig() {
        return Collections.emptyMap();
    }

    protected static String getCamelSourceEndpointConfigPrefix() {
        return CAMEL_SOURCE_ENDPOINT_PROPERTIES_PREFIX;
    }

    protected static String getCamelSourcePathConfigPrefix() {
        return CAMEL_SOURCE_PATH_PROPERTIES_PREFIX;
    }

    protected long calculateTimestamp(Exchange exchange) {
        return System.currentTimeMillis();
    }

    private void setAdditionalHeaders(SourceRecord record, Map<String, Object> map, String prefix) {

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String keyCamelHeader = prefix + key;

            if (value instanceof String) {
                record.headers().addString(keyCamelHeader, (String)value);
            } else if (value instanceof Boolean) {
                record.headers().addBoolean(keyCamelHeader, (boolean)value);
            } else if (value instanceof Byte) {
                record.headers().addByte(keyCamelHeader, (byte)value);
            } else if (value instanceof Byte[]) {
                final Byte[] array = (Byte[])value;
                final byte[] bytes = new byte[array.length];

                for (int i = 0; i < array.length; i++) {
                    bytes[i] = array[i];
                }

                record.headers().addBytes(keyCamelHeader, bytes);
            } else if (value instanceof Date) {
                record.headers().addTimestamp(keyCamelHeader, (Date)value);
            } else if (value instanceof BigDecimal) {
                Schema schema = Decimal.schema(((BigDecimal)value).scale());
                record.headers().add(keyCamelHeader, Decimal.fromLogical(schema, (BigDecimal)value), schema);
            } else if (value instanceof Double) {
                record.headers().addDouble(keyCamelHeader, (double)value);
            } else if (value instanceof Float) {
                record.headers().addFloat(keyCamelHeader, (float)value);
            } else if (value instanceof Integer) {
                record.headers().addInt(keyCamelHeader, (int)value);
            } else if (value instanceof Long) {
                record.headers().addLong(keyCamelHeader, (long)value);
            } else if (value instanceof Short) {
                record.headers().addShort(keyCamelHeader, (short)value);
            }
        }
    }

    private String getLocalUrlWithPollingOptions(long pollingConsumerQueueSize, long pollingConsumerBlockTimeout, boolean pollingConsumerBlockWhenFull) {
        return LOCAL_URL + "?pollingConsumerQueueSize=" + pollingConsumerQueueSize + "&pollingConsumerBlockTimeout=" + pollingConsumerBlockTimeout
               + "&pollingConsumerBlockWhenFull=" + pollingConsumerBlockWhenFull;
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
