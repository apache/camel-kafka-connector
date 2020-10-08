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
import org.apache.camel.CamelContextAware;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.kafkaconnector.CamelConnectorConfig;
import org.apache.camel.main.BaseMainSupport;
import org.apache.camel.main.MainListener;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.support.PropertyBindingSupport;
import org.apache.camel.support.service.ServiceHelper;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelKafkaConnectMain extends BaseMainSupport {
    public static final String CAMEL_DATAFORMAT_PROPERTIES_PREFIX = "camel.dataformat.";
    private static final Logger LOG = LoggerFactory.getLogger(CamelKafkaConnectMain.class);

    protected volatile ConsumerTemplate consumerTemplate;
    protected volatile ProducerTemplate producerTemplate;

    public CamelKafkaConnectMain(CamelContext context) {
        this.camelContext = context;
    }

    @Override
    protected void doInit() throws Exception {
        super.doInit();
        postProcessCamelContext(camelContext);
    }

    @Override
    protected void doStart() throws Exception {
        LOG.info("Starting Main");

        for (MainListener listener : listeners) {
            listener.beforeStart(this);
        }

        super.doStart();

        getCamelContext().start();

        for (MainListener listener : listeners) {
            listener.afterStart(this);
        }

        LOG.info("Main started");
    }

    @Override
    protected void doStop() throws Exception {
        LOG.info("Stopping Main");

        ServiceHelper.stopService(consumerTemplate);
        consumerTemplate = null;

        ServiceHelper.stopService(producerTemplate);
        producerTemplate = null;

        for (MainListener listener : listeners) {
            listener.beforeStop(this);
        }

        super.doStart();

        getCamelContext().stop();

        for (MainListener listener : listeners) {
            listener.afterStop(this);
        }

        LOG.info("Main stopped");
    }

    @Override
    protected ProducerTemplate findOrCreateCamelTemplate() {
        throw new UnsupportedOperationException("Should not happen");
    }

    @Override
    protected CamelContext createCamelContext() {
        throw new UnsupportedOperationException("Should not happen");
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

                    //dataformats
                    if (!ObjectHelper.isEmpty(marshallDataFormat)) {
                        LOG.info(".marshal().custom({})", marshallDataFormat);
                        getContext().getRegistry().bind(marshallDataFormat, lookupAndInstantiateDataformat(getContext(), marshallDataFormat));
                        rd.marshal().custom(marshallDataFormat);
                    }
                    if (!ObjectHelper.isEmpty(unmarshallDataFormat)) {
                        LOG.info(".unmarshal().custom({})", unmarshallDataFormat);
                        getContext().getRegistry().bind(unmarshallDataFormat, lookupAndInstantiateDataformat(getContext(), unmarshallDataFormat));
                        rd.unmarshal().custom(unmarshallDataFormat);
                    }
                    if (getContext().getRegistry().lookupByName("aggregate") != null) {
                        //aggregation
                        AggregationStrategy s = getContext().getRegistry().lookupByNameAndType(CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NAME, AggregationStrategy.class);
                        LOG.info(".aggregate({}).constant(true).completionSize({}).completionTimeout({})", s, aggregationSize, aggregationTimeout);
                        LOG.info(".to({})", to);
                        rd.aggregate(s).constant(true).completionSize(aggregationSize).completionTimeout(aggregationTimeout).toD(to);
                    } else {
                        //to
                        LOG.info(".to({})", to);
                        rd.toD(to);
                    }
                }
            });

            return camelMain;
        }
    }

    private static DataFormat lookupAndInstantiateDataformat(CamelContext camelContext, String dataformatName) {
        DataFormat df = camelContext.resolveDataFormat(dataformatName);

        if (df == null) {
            df = camelContext.createDataFormat(dataformatName);

            final String prefix = CAMEL_DATAFORMAT_PROPERTIES_PREFIX + dataformatName + ".";
            final Properties props = camelContext.getPropertiesComponent().loadProperties(k -> k.startsWith(prefix));

            CamelContextAware.trySetCamelContext(df, camelContext);

            if (!props.isEmpty()) {
                PropertyBindingSupport.build()
                    .withCamelContext(camelContext)
                    .withOptionPrefix(prefix)
                    .withRemoveParameters(false)
                    .withProperties((Map) props)
                    .withTarget(df)
                    .bind();
            }
        }

        //TODO: move it to the caller?
        if (df == null) {
            throw new UnsupportedOperationException("No DataFormat found with name " + dataformatName);
        }
        return df;
    }
}
