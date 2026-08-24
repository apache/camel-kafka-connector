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

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.kafkaconnector.CamelConnectorConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * camel.remove.headers.pattern is the documented mitigation for untrusted headers reaching the route. On the sink
 * path the headers are mapped onto the exchange before it enters the route, so the removal stage has to run before
 * the stages that consume them; on the source path they arrive from the consumer and are read off the exchange after
 * the route, so it stays last.
 */
public class RemoveHeadersOrderTest {

    private static final String STRIPPED_HEADER = "CamelExecCommandExecutable";

    private RecordingAggregationStrategy runRouteWith(boolean removeHeadersFirst) throws Exception {
        Map<String, String> props = new HashMap<>();
        props.put(CamelConnectorConfig.CAMEL_CONNECTOR_REMOVE_HEADERS_PATTERN_CONF, STRIPPED_HEADER);

        DefaultCamelContext context = new DefaultCamelContext();
        RecordingAggregationStrategy strategy = new RecordingAggregationStrategy();
        context.getRegistry().bind(CamelConnectorConfig.CAMEL_CONNECTOR_AGGREGATE_NAME, strategy);

        CamelKafkaConnectMain cms = CamelKafkaConnectMain.builder("direct://start", "log://end")
            .withProperties(props)
            .withHeadersExcludePattern(STRIPPED_HEADER)
            .withAggregationSize(1)
            .withAggregationTimeout(1000L)
            .withRemoveHeadersFirst(removeHeadersFirst)
            .build(context);

        cms.start();
        try {
            cms.getProducerTemplate().sendBodyAndHeader("direct://start", "payload", STRIPPED_HEADER, "/bin/sh");
        } finally {
            cms.stop();
        }
        return strategy;
    }

    @Test
    public void testSinkDirectionStripsHeadersBeforeTheAggregationStage() throws Exception {
        RecordingAggregationStrategy strategy = runRouteWith(true);

        assertNull(strategy.seenHeaderValue,
                "camel.remove.headers.pattern must strip the header before the aggregation stage sees it on the "
                        + "sink path, but the strategy saw: " + strategy.seenHeaderValue);
    }

    @Test
    public void testSourceDirectionKeepsHeadersUntilTheEndOfTheRoute() throws Exception {
        RecordingAggregationStrategy strategy = runRouteWith(false);

        assertEquals("/bin/sh", strategy.seenHeaderValue,
                "on the source path the stripping stage stays last, so intermediate stages still see the header");
    }

    private static final class RecordingAggregationStrategy implements AggregationStrategy {

        private volatile Object seenHeaderValue;

        @Override
        public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
            if (newExchange != null) {
                seenHeaderValue = newExchange.getMessage().getHeader(STRIPPED_HEADER);
            }
            return newExchange;
        }
    }
}
