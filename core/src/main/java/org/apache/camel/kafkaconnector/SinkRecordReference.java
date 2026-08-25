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

import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkRecord;

/**
 * Identifies a sink record while its delivery is outstanding, so its offset can be held back until the exchange
 * carrying it has completed. The record itself is kept so a failed delivery can still be routed to the DLQ.
 */
final class SinkRecordReference {

    private final TopicPartition topicPartition;
    private final long offset;
    private final SinkRecord record;

    SinkRecordReference(SinkRecord record) {
        this.topicPartition = new TopicPartition(record.topic(), record.kafkaPartition());
        this.offset = record.kafkaOffset();
        this.record = record;
    }

    TopicPartition topicPartition() {
        return topicPartition;
    }

    long offset() {
        return offset;
    }

    SinkRecord record() {
        return record;
    }

    @Override
    public String toString() {
        return topicPartition + "@" + offset;
    }
}
