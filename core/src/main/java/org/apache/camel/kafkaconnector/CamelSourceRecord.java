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

import java.util.Map;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.source.SourceRecord;

public class CamelSourceRecord extends SourceRecord {
    private Integer claimCheck;

    public CamelSourceRecord(Map<String, ?> sourcePartition, Map<String, ?> sourceOffset, String topic, Integer partition, Schema valueSchema, Object value) {
        super(sourcePartition, sourceOffset, topic, partition, valueSchema, value);
    }

    public CamelSourceRecord(Map<String, ?> sourcePartition, Map<String, ?> sourceOffset, String topic, Schema valueSchema, Object value) {
        super(sourcePartition, sourceOffset, topic, valueSchema, value);
    }

    public CamelSourceRecord(Map<String, ?> sourcePartition, Map<String, ?> sourceOffset, String topic, Schema keySchema, Object key, Schema valueSchema, Object value) {
        super(sourcePartition, sourceOffset, topic, keySchema, key, valueSchema, value);
    }

    public CamelSourceRecord(Map<String, ?> sourcePartition, Map<String, ?> sourceOffset, String topic, Integer partition, Schema keySchema, Object key, Schema valueSchema, Object value) {
        super(sourcePartition, sourceOffset, topic, partition, keySchema, key, valueSchema, value);
    }

    public CamelSourceRecord(Map<String, ?> sourcePartition, Map<String, ?> sourceOffset, String topic, Integer partition, Schema keySchema, Object key, Schema valueSchema, Object value, Long timestamp) {
        super(sourcePartition, sourceOffset, topic, partition, keySchema, key, valueSchema, value, timestamp);
    }

    public CamelSourceRecord(Map<String, ?> sourcePartition, Map<String, ?> sourceOffset, String topic, Integer partition, Schema keySchema, Object key, Schema valueSchema, Object value, Long timestamp, Iterable<Header> headers) {
        super(sourcePartition, sourceOffset, topic, partition, keySchema, key, valueSchema, value, timestamp, headers);
    }

    public Integer getClaimCheck() {
        return claimCheck;
    }

    public void setClaimCheck(Integer claimCheck) {
        this.claimCheck = claimCheck;
    }
}
