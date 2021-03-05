package org.apache.camel.kafkaconnector;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.Map;

public class CamelSourceRecord extends SourceRecord {
    private Integer claimCheck = null;

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
