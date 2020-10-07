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
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Endpoint;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.support.PropertyBindingSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelMainSupport {
    public static final String CAMEL_DATAFORMAT_PROPERTIES_PREFIX = "camel.dataformat.";
    private static final Logger LOG = LoggerFactory.getLogger(CamelMainSupport.class);

    private final CamelKafkaConnectMain camelMain;

    public CamelMainSupport(Map<String, String> props, String fromUrl, String toUrl, List<CamelKafkaConnectDataformat> dataformats, int aggregationSize, long aggregationTimeout, CamelContext camelContext) {
        camelMain = new CamelKafkaConnectMain(camelContext);
        camelMain.configure().setAutoConfigurationLogSummary(false);

        Properties camelProperties = new Properties();
        camelProperties.putAll(props);

        LOG.info("Setting initial properties in Camel context: [{}]", camelProperties);
        camelMain.setInitialProperties(camelProperties);

        //creating the actual route
        camelMain.configure().addRoutesBuilder(new RouteBuilder() {
            public void configure() {
                //from
                RouteDefinition rd = from(fromUrl);

                //dataformats
                LOG.info("Creating Camel route from({})", fromUrl);
                for (CamelKafkaConnectDataformat dataformat : dataformats) {
                    String dataformatId = dataformat.getDataformatId();
                    switch (dataformat.getDataformatKind()) {
                        case MARSHALL:
                            LOG.info(".marshal().custom({})", dataformatId);
                            getContext().getRegistry().bind(dataformatId, lookupAndInstantiateDataformat(dataformatId));
                            rd.marshal().custom(dataformatId);
                            break;
                        case UNMARSHALL:
                            LOG.info(".unmarshal().custom({})", dataformatId);
                            getContext().getRegistry().bind(dataformatId, lookupAndInstantiateDataformat(dataformatId));
                            rd.unmarshal().custom(dataformatId);
                            break;
                        default:
                            throw new UnsupportedOperationException("Unsupported dataformat: " + dataformat);
                    }
                }

                if (getContext().getRegistry().lookupByName("aggregate") != null) {
                    //aggregation
                    AggregationStrategy s = (AggregationStrategy) getContext().getRegistry().lookupByName("aggregate");
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

    public void start() {
        LOG.info("Starting CamelContext");

        try {
            camelMain.start();
        } catch (Exception e) {
            LOG.info("CamelContext failed to start", e);
            throw e;
        }

        LOG.info("CamelContext started");
    }

    public void stop() {
        LOG.info("Stopping CamelContext");

        try {
            camelMain.stop();
        } catch (Exception e) {
            LOG.info("CamelContext failed to stop", e);
            throw e;
        }

        LOG.info("CamelContext stopped");
    }

    public ProducerTemplate createProducerTemplate() {
        return camelMain.getProducerTemplate();
    }

    public Endpoint getEndpoint(String uri) {
        return camelMain.getCamelContext().getEndpoint(uri);
    }

    public Collection<Endpoint> getEndpoints() {
        return camelMain.getCamelContext().getEndpoints();
    }

    public ConsumerTemplate createConsumerTemplate() {
        return camelMain.getConsumerTemplate();
    }

    private DataFormat lookupAndInstantiateDataformat(String dataformatName) {
        DataFormat df = camelMain.getCamelContext().resolveDataFormat(dataformatName);

        if (df == null) {
            df = camelMain.getCamelContext().createDataFormat(dataformatName);

            final String prefix = CAMEL_DATAFORMAT_PROPERTIES_PREFIX + dataformatName + ".";
            final Properties props = camelMain.getCamelContext().getPropertiesComponent().loadProperties(k -> k.startsWith(prefix));

            CamelContextAware.trySetCamelContext(df, camelMain.getCamelContext());

            if (!props.isEmpty()) {
                PropertyBindingSupport.build()
                        .withCamelContext(camelMain.getCamelContext())
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
