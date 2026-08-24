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
package org.apache.camel.kafkaconnector.transforms;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CamelTransformSupport<R extends ConnectRecord<R>> implements Transformation<R> {

    private static final Logger LOG = LoggerFactory.getLogger(CamelTransformSupport.class);

    private final CamelContext camelContext = new DefaultCamelContext();

    protected CamelContext getCamelContext() {
        return camelContext;
    }

    /**
     * Stops the {@link CamelContext} created for this transform instance. Kafka Connect re-instantiates transforms on
     * every connector configuration update, so a subclass must release it from {@link Transformation#close()} rather
     * than let it accumulate for the lifetime of the worker.
     */
    protected void stopCamelContext() {
        try {
            camelContext.stop();
        } catch (Exception e) {
            LOG.warn("Failed to stop the Camel context of {}: {}", getClass().getSimpleName(), e.getMessage(), e);
        }
    }
}
