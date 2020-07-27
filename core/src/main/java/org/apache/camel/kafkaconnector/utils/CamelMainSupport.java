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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Endpoint;
import org.apache.camel.ExtendedCamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.catalog.RuntimeCamelCatalog;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.BaseMainSupport;
import org.apache.camel.main.Main;
import org.apache.camel.main.MainListener;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.support.PropertyBindingSupport;
import org.apache.camel.util.OrderedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelMainSupport {
    public static final String CAMEL_DATAFORMAT_PROPERTIES_PREFIX = "camel.dataformat.";
    private static final Logger LOG = LoggerFactory.getLogger(CamelMainSupport.class);

    private Main camelMain;
    private CamelContext camel;

    private final ExecutorService exService = Executors.newSingleThreadExecutor();
    private final CountDownLatch startFinishedSignal = new CountDownLatch(1);

    public CamelMainSupport(Map<String, String> props, String fromUrl, String toUrl, List<CamelKafkaConnectDataformat> dataformats, int aggregationSize, long aggregationTimeout) throws Exception {
        this(props, fromUrl, toUrl, dataformats, aggregationSize, aggregationTimeout, new DefaultCamelContext());
    }

    public CamelMainSupport(Map<String, String> props, String fromUrl, String toUrl, List<CamelKafkaConnectDataformat> dataformats, int aggregationSize, long aggregationTimeout, CamelContext camelContext) throws Exception {
        camel = camelContext;
        camelMain = new Main() {
            @Override
            protected ProducerTemplate findOrCreateCamelTemplate() {
                return camel.createProducerTemplate();
            }

            @Override
            protected CamelContext createCamelContext() {
                return camel;
            }
        };

        camelMain.addMainListener(new CamelMainFinishedListener());
        camelMain.configure().setAutoConfigurationLogSummary(false);

        // reordering properties to place the one starting with "#class:" first
        LinkedHashMap<String, String> orderedProps = new LinkedHashMap<>();
        props.keySet().stream()
                .filter(k -> props.get(k).startsWith("#class:"))
                .forEach(k -> orderedProps.put(k, props.get(k)));
        props.keySet().stream()
                .filter(k -> !props.get(k).startsWith("#class:"))
                .forEach(k -> orderedProps.put(k, props.get(k)));

        Properties camelProperties = new OrderedProperties();
        camelProperties.putAll(orderedProps);

        LOG.info("Setting initial properties in Camel context: [{}]", camelProperties);
        this.camel.getPropertiesComponent().setInitialProperties(camelProperties);

        camelMain.init();
        //creating the actual route
        this.camel.addRoutes(new RouteBuilder() {
            public void configure() {
                //from
                RouteDefinition rd = from(fromUrl);

                //dataformats
                LOG.info("Creating Camel route from({})");
                for (CamelKafkaConnectDataformat dataformat : dataformats) {
                    String dataformatId = dataformat.getDataformatId();
                    switch (dataformat.getDataformatKind()) {
                        case MARSHALL:
                            LOG.info(".marshal().custom({})", dataformatId);
                            camel.getRegistry().bind(dataformatId, lookupAndInstantiateDataformat(dataformatId));
                            rd.marshal().custom(dataformatId);
                            break;
                        case UNMARSHALL:
                            LOG.info(".unmarshal().custom({})", dataformatId);
                            camel.getRegistry().bind(dataformatId, lookupAndInstantiateDataformat(dataformatId));
                            rd.unmarshal().custom(dataformatId);
                            break;
                        default:
                            throw new UnsupportedOperationException("Unsupported dataformat: " + dataformat);
                    }
                }


                if (camel.getRegistry().lookupByName("aggregate") != null) {
                    //aggregation
                    AggregationStrategy s = (AggregationStrategy) camel.getRegistry().lookupByName("aggregate");
                    LOG.info(".aggregate({}).constant(true).completionSize({}).completionTimeout({})", s, aggregationSize, aggregationTimeout);
                    LOG.info(".to({})", toUrl);
                    rd.aggregate(s).constant(true).completionSize(aggregationSize).completionTimeout(aggregationTimeout).toD(toUrl);
                } else {
                    //to
                    LOG.info(".to({})", toUrl);
                    rd.toD(toUrl);
                }
            }
        });
    }

    public void start() throws Exception {
        LOG.info("Starting CamelContext");

        CamelContextStarter starter = new CamelContextStarter();
        exService.execute(starter);
        startFinishedSignal.await();

        if (starter.hasException()) {
            LOG.info("CamelContext failed to start", starter.getException());
            throw starter.getException();
        }

        LOG.info("CamelContext started");
    }

    public void stop() {
        LOG.info("Stopping CamelContext");

        camelMain.stop();
        exService.shutdown();

        LOG.info("CamelContext stopped");
    }

    public ProducerTemplate createProducerTemplate() {
        return camel.createProducerTemplate();
    }

    public Endpoint getEndpoint(String uri) {
        return camel.getEndpoint(uri);
    }

    public Collection<Endpoint> getEndpoints() {
        return camel.getEndpoints();
    }

    public ConsumerTemplate createConsumerTemplate() {
        return camel.createConsumerTemplate();
    }

    public RuntimeCamelCatalog getRuntimeCamelCatalog() {
        return camel.adapt(ExtendedCamelContext.class).getRuntimeCamelCatalog();
    }

    private DataFormat lookupAndInstantiateDataformat(String dataformatName) {
        DataFormat df = camel.resolveDataFormat(dataformatName);

        if (df == null) {
            df = camel.createDataFormat(dataformatName);

            final String prefix = CAMEL_DATAFORMAT_PROPERTIES_PREFIX + dataformatName + ".";
            final Properties props = camel.getPropertiesComponent().loadProperties(k -> k.startsWith(prefix));

            CamelContextAware.trySetCamelContext(df, camel);

            if (!props.isEmpty()) {
                PropertyBindingSupport.build()
                        .withCamelContext(camel)
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

    private class CamelMainFinishedListener implements MainListener {
        @Override
        public void configure(CamelContext context) {

        }

        @Override
        public void beforeStart(BaseMainSupport main) {

        }

        @Override
        public void afterStart(BaseMainSupport main) {
            LOG.trace("Signaling CamelContext startup is finished (startFinishedSignal.countDown();) due to CamelMainFinishedListener been called");
            startFinishedSignal.countDown();
        }

        @Override
        public void beforeStop(BaseMainSupport main) {

        }

        @Override
        public void afterStop(BaseMainSupport main) {

        }

        @Override
        public void beforeConfigure(BaseMainSupport main) {
        }

        @Override
        public void afterConfigure(BaseMainSupport main) {

        }

        @Override
        public void beforeInitialize(BaseMainSupport main) {

        }
    }

    private class CamelContextStarter implements Runnable {
        private Exception startException;

        @Override
        public void run() {
            try {
                camelMain.run();
            } catch (Exception e) {
                LOG.error("An exception has occurred before CamelContext startup has finished", e);
                startException = e;
                if (startFinishedSignal.getCount() > 0) {
                    LOG.trace("Signaling CamelContext startup is finished (startFinishedSignal.countDown();) due to an exception");
                    startFinishedSignal.countDown();
                }
            }
        }

        public boolean hasException() {
            return startException != null;
        }

        public Exception getException() {
            return startException;
        }
    }
}
