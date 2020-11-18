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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.kafkaconnector.CamelConnectorConfig;
import org.apache.camel.main.SimpleMain;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.support.processor.idempotent.MemoryIdempotentRepository;
import org.apache.camel.support.service.ServiceHelper;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelKafkaConnectMain extends SimpleMain {
    public static final String CAMEL_DATAFORMAT_PROPERTIES_PREFIX = "camel.dataformat.";
    private static final Logger LOG = LoggerFactory.getLogger(CamelKafkaConnectMain.class);

    protected volatile ConsumerTemplate consumerTemplate;
    protected volatile ProducerTemplate producerTemplate;

    public CamelKafkaConnectMain(CamelContext context) {
        super(context);
    }

    @Override
    protected void doStop() throws Exception {
        ServiceHelper.stopService(consumerTemplate);
        consumerTemplate = null;

        ServiceHelper.stopService(producerTemplate);
        producerTemplate = null;

        super.doStop();
    }

    public ProducerTemplate getProducerTemplate() {
        if (this.producerTemplate == null) {
            synchronized (this) {
                if (this.producerTemplate == null) {
                    this.producerTemplate = getCamelContext().createProducerTemplate();
                }
            }
        }

        return this.producerTemplate;
    }

    public ConsumerTemplate getConsumerTemplate() {
        if (this.consumerTemplate == null) {
            synchronized (this) {
                if (this.consumerTemplate == null) {
                    this.consumerTemplate = getCamelContext().createConsumerTemplate();
                }
            }
        }

        return this.consumerTemplate;
    }

    public static Builder builder(String from, String to) {
        return new Builder(from, to);
    }

    public static final class Builder {
        private final String from;
        private final String to;
        private Map<String, String> props;
        private String marshallDataFormat;
        private String unmarshallDataFormat;
        private int aggregationSize;
        private long aggregationTimeout;
        private String errorHandler;
        private int maxRedeliveries;
        private long redeliveryDelay;
        private boolean idempotencyEnabled;
        private String expressionType;
        private String expressionHeader;
        private int memoryDimension;

        public Builder(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public Builder withProperties(Map<String, String> props) {
            this.props = new HashMap<>(props);
            return this;
        }

        public Builder withMarshallDataFormat(String dataformatId) {
            this.marshallDataFormat = dataformatId;
            return this;
        }

        public Builder withUnmarshallDataFormat(String dataformatId) {
            this.unmarshallDataFormat = dataformatId;
            return this;
        }

        public Builder withAggregationSize(int aggregationSize) {
            this.aggregationSize = aggregationSize;
            return this;
        }

        public Builder withAggregationTimeout(long aggregationTimeout) {
            this.aggregationTimeout = aggregationTimeout;
            return this;
        }
        
        public Builder withErrorHandler(String errorHandler) {
            this.errorHandler = errorHandler;
            return this;
        }
        
        public Builder withMaxRedeliveries(int maxRedeliveries) {
            this.maxRedeliveries = maxRedeliveries;
            return this;
        }
        
        public Builder withRedeliveryDelay(long redeliveryDelay) {
            this.redeliveryDelay = redeliveryDelay;
            return this;
        }
        
        public Builder withIdempotencyEnabled(boolean idempotencyEnabled) {
            this.idempotencyEnabled = idempotencyEnabled;
            return this;
        }
        
        public Builder withExpressionType(String expressionType) {
            this.expressionType = expressionType;
            return this;
        }
        
        public Builder withExpressionHeader(String expressionHeader) {
            this.expressionHeader = expressionHeader;
            return this;
        }
        
        public Builder withMemoryDimension(int memoryDimension) {
            this.memoryDimension = memoryDimension;
            return this;
        }

        public CamelKafkaConnectMain build(CamelContext camelContext) {
            CamelKafkaConnectMain camelMain = new CamelKafkaConnectMain(camelContext);
            camelMain.configure().setAutoConfigurationLogSummary(false);

            Properties camelProperties = new Properties();
            camelProperties.putAll(props);

            LOG.info("Setting initial properties in Camel context: [{}]", camelProperties);
            camelMain.setInitialProperties(camelProperties);

            //creating the actual route
            camelMain.configure().addRoutesBuilder(new RouteBuilder() {
                public void configure() {
                    //from
                    RouteDefinition rd = from(from);
                    LOG.info("Creating Camel route from({})", from);
                    
                    if (!ObjectHelper.isEmpty(errorHandler)) {
                        switch (errorHandler) {
                            case "no":
                                rd.errorHandler(noErrorHandler());
                                break;
                            case "default":
                                rd.errorHandler(defaultErrorHandler().maximumRedeliveries(maxRedeliveries).redeliveryDelay(redeliveryDelay));
                                break;
                            default:
                                break;
                        }
                    }

                    //dataformats
                    if (!ObjectHelper.isEmpty(marshallDataFormat)) {
                        LOG.info(".marshal({})", marshallDataFormat);
                        rd.marshal(marshallDataFormat);
                    }
                    if (!ObjectHelper.isEmpty(unmarshallDataFormat)) {
                        LOG.info(".unmarshal({})", unmarshallDataFormat);
                        rd.unmarshal(unmarshallDataFormat);
                    }
                    if (getContext().getRegistry().lookupByName("aggregate") != null) {
                        //aggregation
                        AggregationStrategy s = getContext().getRegistry().lookupByNameAndType(CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NAME, AggregationStrategy.class);
                        if (idempotencyEnabled) {
                            switch (expressionType) {
                                case "body":
                                    LOG.info(".aggregate({}).constant(true).completionSize({}).completionTimeout({}).idempotentConsumer(body(), + "
                                           + "MemoryIdempotentRepository.memoryIdempotentRepository({}))", s, aggregationSize, aggregationTimeout, memoryDimension);
                                    LOG.info(".to({})", to);
                                    rd.aggregate(s).constant(true).completionSize(aggregationSize).completionTimeout(aggregationTimeout).idempotentConsumer(body(), MemoryIdempotentRepository.memoryIdempotentRepository(memoryDimension)).toD(to);
                                    break;
                                case "header":
                                    LOG.info(".aggregate({}).constant(true).completionSize({}).completionTimeout({}).idempotentConsumer(header(expressionHeader), + "
                                           + "MemoryIdempotentRepository.memoryIdempotentRepository({}))", s, aggregationSize, aggregationTimeout, memoryDimension);
                                    LOG.info(".to({})", to);
                                    rd.aggregate(s).constant(true).completionSize(aggregationSize).completionTimeout(aggregationTimeout)
                                        .idempotentConsumer(header(expressionHeader), MemoryIdempotentRepository.memoryIdempotentRepository(memoryDimension)).toD(to);
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            LOG.info(".aggregate({}).constant(true).completionSize({}).completionTimeout({})", s, aggregationSize, aggregationTimeout);
                            LOG.info(".to({})", to);
                            rd.aggregate(s).constant(true).completionSize(aggregationSize).completionTimeout(aggregationTimeout).toD(to);
                        }
                    } else {
                        if (idempotencyEnabled) {
                            switch (expressionType) {
                                case "body":
                                    LOG.info("idempotentConsumer(body(), MemoryIdempotentRepository.memoryIdempotentRepository({})).to({})", memoryDimension, to);
                                    rd.idempotentConsumer(body(), MemoryIdempotentRepository.memoryIdempotentRepository(memoryDimension)).toD(to);
                                    break;
                                case "header":
                                    LOG.info("idempotentConsumer(header(expressionHeader), MemoryIdempotentRepository.memoryIdempotentRepository({})).to({})", memoryDimension, to);
                                    rd.idempotentConsumer(header(expressionHeader), MemoryIdempotentRepository.memoryIdempotentRepository(memoryDimension)).toD(to);
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            //to
                            LOG.info(".to({})", to);
                            rd.toD(to);
                        }
                    }
                }
            });

            return camelMain;
        }
    }
}
