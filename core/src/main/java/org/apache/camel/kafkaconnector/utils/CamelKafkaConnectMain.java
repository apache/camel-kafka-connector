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
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.DefaultErrorHandlerBuilder;
import org.apache.camel.builder.ErrorHandlerBuilderRef;
import org.apache.camel.builder.NoErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.kafkaconnector.CamelConnectorConfig;
import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.CamelSourceTask;
import org.apache.camel.main.SimpleMain;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteTemplateDefinition;
import org.apache.camel.processor.idempotent.kafka.KafkaIdempotentRepository;
import org.apache.camel.spi.IdempotentRepository;
import org.apache.camel.support.processor.idempotent.MemoryIdempotentRepository;
import org.apache.camel.support.service.ServiceHelper;
import org.apache.camel.util.ObjectHelper;
import org.apache.camel.util.SensitiveUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelKafkaConnectMain extends SimpleMain {
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
        private String idempotentRepositoryType;
        private String idempotentRepositoryTopicName;
        private String idempotentRepositoryKafkaServers;
        private int idempotentRepositoryKafkaMaxCacheSize;
        private int idempotentRepositoryKafkaPollDuration;
        private String headersExcludePattern;

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

        public Builder withIdempotentRepositoryType(String idempotentRepositoryType) {
            this.idempotentRepositoryType = idempotentRepositoryType;
            return this;
        }

        public Builder withIdempotentRepositoryTopicName(String idempotentRepositoryTopicName) {
            this.idempotentRepositoryTopicName = idempotentRepositoryTopicName;
            return this;
        }

        public Builder withIdempotentRepositoryKafkaServers(String idempotentRepositoryKafkaServers) {
            this.idempotentRepositoryKafkaServers = idempotentRepositoryKafkaServers;
            return this;
        }

        public Builder withIdempotentRepositoryKafkaMaxCacheSize(int idempotentRepositoryKafkaMaxCacheSize) {
            this.idempotentRepositoryKafkaMaxCacheSize = idempotentRepositoryKafkaMaxCacheSize;
            return this;
        }

        public Builder withIdempotentRepositoryKafkaPollDuration(int idempotentRepositoryKafkaPollDuration) {
            this.idempotentRepositoryKafkaPollDuration = idempotentRepositoryKafkaPollDuration;
            return this;
        }

        public Builder withHeadersExcludePattern(String headersExcludePattern) {
            this.headersExcludePattern = headersExcludePattern;
            return this;
        }

        private String filterSensitive(Map.Entry<Object, Object> entry) {

            if (SensitiveUtils.containsSensitive((String) entry.getKey())) {
                return entry.getKey() + "=xxxxxxx";
            }
            return entry.getKey() + "=" + entry.getValue();
        }

        public CamelKafkaConnectMain build(CamelContext camelContext) {
            CamelKafkaConnectMain camelMain = new CamelKafkaConnectMain(camelContext);
            camelMain.configure().setAutoConfigurationLogSummary(false);
            //TODO: make it configurable
            camelMain.configure().setDumpRoutes(true);

            Properties camelProperties = new Properties();
            camelProperties.putAll(props);

            //TODO: enable or delete these parameters once https://issues.apache.org/jira/browse/CAMEL-16551 is resolved
//            //dataformats
//            if (!ObjectHelper.isEmpty(marshallDataFormat)) {
//                camelProperties.put(CamelSourceTask.KAMELET_SOURCE_TEMPLETE_PARAMETERS_PREFIX + "marshall", marshallDataFormat);
//                camelProperties.put(CamelSinkTask.KAMELET_SINK_TEMPLATE_PARAMETERS_PREFIX + "marshall", marshallDataFormat);
//            }
//            if (!ObjectHelper.isEmpty(unmarshallDataFormat)) {
//                camelProperties.put(CamelSourceTask.KAMELET_SOURCE_TEMPLETE_PARAMETERS_PREFIX + "unmarshall", unmarshallDataFormat);
//                camelProperties.put(CamelSinkTask.KAMELET_SINK_TEMPLATE_PARAMETERS_PREFIX + "unmarshall", unmarshallDataFormat);
//            }

            //aggregator
            if (!ObjectHelper.isEmpty(aggregationSize)) {
                camelProperties.put(CamelSourceTask.KAMELET_SOURCE_TEMPLATE_PARAMETERS_PREFIX + "aggregationSize", String.valueOf(aggregationSize));
                camelProperties.put(CamelSinkTask.KAMELET_SINK_TEMPLATE_PARAMETERS_PREFIX + "aggregationSize", String.valueOf(aggregationSize));
            }
            if (!ObjectHelper.isEmpty(aggregationTimeout)) {
                camelProperties.put(CamelSourceTask.KAMELET_SOURCE_TEMPLATE_PARAMETERS_PREFIX + "aggregationTimeout", String.valueOf(aggregationTimeout));
                camelProperties.put(CamelSinkTask.KAMELET_SINK_TEMPLATE_PARAMETERS_PREFIX + "aggregationTimeout", String.valueOf(aggregationTimeout));
            }

            //idempotency
            if (idempotencyEnabled) {
                switch (expressionType) {
                    case "body":
                        camelProperties.put(CamelSourceTask.KAMELET_SOURCE_TEMPLATE_PARAMETERS_PREFIX + "idempotentExpression", "${body}");
                        camelProperties.put(CamelSinkTask.KAMELET_SINK_TEMPLATE_PARAMETERS_PREFIX + "idempotentExpression", "${body}");
                        break;
                    case "header":
                        camelProperties.put(CamelSourceTask.KAMELET_SOURCE_TEMPLATE_PARAMETERS_PREFIX + "idempotentExpression", "${headers." + expressionHeader + "}");
                        camelProperties.put(CamelSinkTask.KAMELET_SINK_TEMPLATE_PARAMETERS_PREFIX + "idempotentExpression", "${headers." + expressionHeader + "}");
                        break;
                    default:
                        break;
                }
                // Instantiating the idempotent Repository here and inject it in registry to be referenced
                IdempotentRepository idempotentRepo = null;
                switch (idempotentRepositoryType) {
                    case "memory":
                        idempotentRepo = MemoryIdempotentRepository.memoryIdempotentRepository(memoryDimension);
                        break;
                    case "kafka":
                        idempotentRepo = new KafkaIdempotentRepository(idempotentRepositoryTopicName, idempotentRepositoryKafkaServers, idempotentRepositoryKafkaMaxCacheSize, idempotentRepositoryKafkaPollDuration);
                        break;
                    default:
                        break;
                }
                camelMain.getCamelContext().getRegistry().bind("ckcIdempotentRepository", idempotentRepo);
            }

            //remove headers
            if (!ObjectHelper.isEmpty(headersExcludePattern)) {
                camelProperties.put(CamelSourceTask.KAMELET_SOURCE_TEMPLATE_PARAMETERS_PREFIX + "headersExcludePattern", headersExcludePattern);
                camelProperties.put(CamelSinkTask.KAMELET_SINK_TEMPLATE_PARAMETERS_PREFIX + "headersExcludePattern", headersExcludePattern);
            }

            List<String> filteredProps = camelProperties.entrySet().stream().map(this::filterSensitive).collect(Collectors.toList());
            LOG.info("Setting initial properties in Camel context: [{}]", filteredProps);
            camelMain.setInitialProperties(camelProperties);

            //error handler
            camelMain.getCamelContext().getRegistry().bind("ckcErrorHandler", new DefaultErrorHandlerBuilder());
            if (errorHandler != null) {
                switch (errorHandler) {
                    case "no":
                        camelMain.getCamelContext().getRegistry().bind("ckcErrorHandler", new NoErrorHandlerBuilder());
                        break;
                    case "default":
                        camelMain.getCamelContext().getRegistry().bind("ckcErrorHandler", new DefaultErrorHandlerBuilder().maximumRedeliveries(maxRedeliveries).redeliveryDelay(redeliveryDelay));
                        break;
                    default:
                        break;
                }
            }

            camelMain.configure().addRoutesBuilder(new RouteBuilder() {
                public void configure() {

                    //creating source template
                    RouteTemplateDefinition rtdSource = routeTemplate("ckcSource")
                            .templateParameter("fromUrl")
                            .templateParameter("errorHandler", "ckcErrorHandler")
                            //TODO: enable or delete these parameters once https://issues.apache.org/jira/browse/CAMEL-16551 is resolved
//                            .templateParameter("marshall", "dummyDataformat")
//                            .templateParameter("unmarshall", "dummyDataformat")

                            //TODO: change CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NA to ckcAggregationStrategy?
                            .templateParameter("aggregationStrategy", CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NAME)
                            .templateParameter("aggregationSize", "1")
                            .templateParameter("aggregationTimeout", String.valueOf(Long.MAX_VALUE))

                            .templateParameter("idempotentExpression", "dummyExpression")
                            .templateParameter("idempotentRepository", "ckcIdempotentRepository")
                            .templateParameter("headersExcludePattern", "(?!)");


                    ProcessorDefinition<?> rdInTemplateSource = rtdSource.from("{{fromUrl}}")
                            .errorHandler(new ErrorHandlerBuilderRef("{{errorHandler}}"));
                    if (!ObjectHelper.isEmpty(marshallDataFormat)) {
                        rdInTemplateSource = rdInTemplateSource.marshal(marshallDataFormat);
                    }
                    if (!ObjectHelper.isEmpty(unmarshallDataFormat)) {
                        rdInTemplateSource = rdInTemplateSource.unmarshal(unmarshallDataFormat);
                    }

                    if (getContext().getRegistry().lookupByName("aggregate") != null) {
                        AggregationStrategy s = getContext().getRegistry().lookupByNameAndType(CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NAME, AggregationStrategy.class);
                        rdInTemplateSource = rdInTemplateSource.aggregate(s)
                                .constant(true)
                                .completionSize("{{aggregationSize}}")
                                .completionTimeout("{{aggregationTimeout}}");
                    }

                    if (idempotencyEnabled) {
                        rdInTemplateSource = rdInTemplateSource.idempotentConsumer(simple("{{idempotentExpression}}")).messageIdRepositoryRef("{{idempotentRepository}}");
                    }

                    rdInTemplateSource.removeHeaders("{{headersExcludePattern}}")
                            .to("kamelet:sink");

                    //creating sink template
                    RouteTemplateDefinition rtdSink = routeTemplate("ckcSink")
                            .templateParameter("toUrl")
                            .templateParameter("errorHandler", "ckcErrorHandler")
                            //TODO: enable or delete these parameters once https://issues.apache.org/jira/browse/CAMEL-16551 is resolved
//                            .templateParameter("marshall", "dummyDataformat")
//                            .templateParameter("unmarshall", "dummyDataformat")

                            //TODO: change CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NA to ckcAggregationStrategy?
                            .templateParameter("aggregationStrategy", CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NAME)
                            .templateParameter("aggregationSize", "1")
                            .templateParameter("aggregationTimeout", String.valueOf(Long.MAX_VALUE))

                            .templateParameter("idempotentExpression", "dummyExpression")
                            .templateParameter("idempotentRepository", "ckcIdempotentRepository")
                            .templateParameter("headersExcludePattern", "(?!)");


                    ProcessorDefinition<?> rdInTemplateSink = rtdSink.from("kamelet:source")
                            .errorHandler(new ErrorHandlerBuilderRef("{{errorHandler}}"));
                    if (!ObjectHelper.isEmpty(marshallDataFormat)) {
                        rdInTemplateSink = rdInTemplateSink.marshal(marshallDataFormat);
                    }
                    if (!ObjectHelper.isEmpty(unmarshallDataFormat)) {
                        rdInTemplateSink = rdInTemplateSink.unmarshal(unmarshallDataFormat);
                    }

                    if (getContext().getRegistry().lookupByName("aggregate") != null) {
                        AggregationStrategy s = getContext().getRegistry().lookupByNameAndType(CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NAME, AggregationStrategy.class);
                        rdInTemplateSink = rdInTemplateSink.aggregate(s)
                                .constant(true)
                                .completionSize("{{aggregationSize}}")
                                .completionTimeout("{{aggregationTimeout}}");
                    }

                    if (idempotencyEnabled) {
                        rdInTemplateSink = rdInTemplateSink.idempotentConsumer(simple("{{idempotentExpression}}")).messageIdRepositoryRef("{{idempotentRepository}}");
                    }

                    rdInTemplateSink.removeHeaders("{{headersExcludePattern}}")
                            .to("{{toUrl}}");

                    //creating the actual route
                    from(from).toD(to);
                }
            });

            return camelMain;
        }
    }
}
