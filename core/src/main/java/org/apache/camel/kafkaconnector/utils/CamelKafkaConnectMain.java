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

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.main.BaseMainSupport;
import org.apache.camel.main.MainListener;
import org.apache.camel.support.service.ServiceHelper;
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
}
