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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

/**
 * Tracks which sink records have been handed to the Camel route but not yet delivered, so that
 * {@link org.apache.kafka.connect.sink.SinkTask#preCommit} can hold back the offsets of records whose delivery has
 * not completed.
 *
 * A record is in flight from the moment it is sent into the route until the exchange carrying it - which, when
 * aggregation is configured, is the aggregated exchange rather than the one the record entered on - has completed.
 */
class SinkRecordDeliveryTracker {

    private final Map<TopicPartition, NavigableSet<Long>> inFlight = new ConcurrentHashMap<>();

    void inFlight(TopicPartition partition, long offset) {
        inFlight.computeIfAbsent(partition, p -> new ConcurrentSkipListSet<>()).add(offset);
    }

    void delivered(TopicPartition partition, long offset) {
        NavigableSet<Long> offsets = inFlight.get(partition);
        if (offsets != null) {
            offsets.remove(offset);
        }
    }

    /**
     * Offsets safe to commit: for a partition with records still in flight, everything strictly before the oldest of
     * them; otherwise whatever Kafka Connect proposed.
     */
    Map<TopicPartition, OffsetAndMetadata> safeOffsets(Map<TopicPartition, OffsetAndMetadata> currentOffsets) {
        Map<TopicPartition, OffsetAndMetadata> safe = new HashMap<>(currentOffsets.size());

        for (Map.Entry<TopicPartition, OffsetAndMetadata> entry : currentOffsets.entrySet()) {
            NavigableSet<Long> offsets = inFlight.get(entry.getKey());
            Long oldestInFlight = offsets == null || offsets.isEmpty() ? null : offsets.first();

            if (oldestInFlight == null || oldestInFlight >= entry.getValue().offset()) {
                safe.put(entry.getKey(), entry.getValue());
            } else {
                // commit up to, but not including, the oldest record still awaiting delivery
                safe.put(entry.getKey(), new OffsetAndMetadata(oldestInFlight));
            }
        }

        return safe;
    }

    /**
     * Drops any state for partitions that are no longer assigned to this task.
     */
    void forget(Collection<TopicPartition> partitions) {
        partitions.forEach(inFlight::remove);
    }

    void clear() {
        inFlight.clear();
    }

    // visible for testing
    int inFlightCount(TopicPartition partition) {
        NavigableSet<Long> offsets = inFlight.get(partition);
        return offsets == null ? 0 : offsets.size();
    }
}
