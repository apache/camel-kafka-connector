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

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.support.SynchronizationAdapter;

/**
 * Wraps the aggregation strategy named by the connector configuration, keeping each record associated with the
 * aggregated exchange it was merged into.
 *
 * The aggregate EIP completes an incoming exchange as soon as it has been merged into the buffer, while the data is
 * delivered later on the aggregated exchange. Without this, a record's offset would be committed while its data was
 * still in the aggregation buffer, and a failure of the aggregated exchange would never reach the task.
 */
class DeliveryTrackingAggregationStrategy implements AggregationStrategy {

    static final String AGGREGATED_RECORDS_PROPERTY = "CamelKafkaConnectorAggregatedRecords";
    static final String COMPLETION_REGISTERED_PROPERTY = "CamelKafkaConnectorAggregatedCompletionRegistered";

    private final AggregationStrategy delegate;
    private final BiConsumer<List<SinkRecordReference>, Exchange> onAggregatedExchangeDone;

    DeliveryTrackingAggregationStrategy(AggregationStrategy delegate,
                                        BiConsumer<List<SinkRecordReference>, Exchange> onAggregatedExchangeDone) {
        this.delegate = delegate;
        this.onAggregatedExchangeDone = onAggregatedExchangeDone;
    }

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        SinkRecordReference incoming = newExchange == null
                ? null
                : newExchange.getProperty(CamelSinkTask.RECORD_REFERENCE_PROPERTY, SinkRecordReference.class);

        // An aggregation strategy commonly returns newExchange, so the exchange carrying the batch changes identity on
        // every call. Carry the accumulated records across so the list always describes the whole batch.
        List<SinkRecordReference> records = oldExchange == null ? new ArrayList<>() : aggregatedRecords(oldExchange);

        Exchange aggregated = delegate.aggregate(oldExchange, newExchange);

        if (aggregated == null) {
            return null;
        }

        if (incoming != null) {
            records.add(incoming);
        }
        aggregated.setProperty(AGGREGATED_RECORDS_PROPERTY, records);

        // Only the exchange that ends up leaving the aggregator completes, so registering on each candidate is safe:
        // the ones that are superseded never fire, and the one that does holds the whole batch.
        if (aggregated.getProperty(COMPLETION_REGISTERED_PROPERTY) == null) {
            aggregated.setProperty(COMPLETION_REGISTERED_PROPERTY, Boolean.TRUE);
            aggregated.getExchangeExtension().addOnCompletion(new SynchronizationAdapter() {
                @Override
                public void onDone(Exchange exchange) {
                    onAggregatedExchangeDone.accept(new ArrayList<>(records), exchange);
                }
            });
        }

        return aggregated;
    }

    @SuppressWarnings("unchecked")
    private List<SinkRecordReference> aggregatedRecords(Exchange exchange) {
        List<SinkRecordReference> records = exchange.getProperty(AGGREGATED_RECORDS_PROPERTY, List.class);
        return records == null ? new ArrayList<>() : records;
    }
}
