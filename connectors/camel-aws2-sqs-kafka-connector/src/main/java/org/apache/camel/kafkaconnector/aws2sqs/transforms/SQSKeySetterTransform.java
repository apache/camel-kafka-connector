package org.apache.camel.kafkaconnector.aws2sqs.transforms;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.header.Headers;
import org.apache.kafka.connect.transforms.Transformation;

import java.util.Map;

public class SQSKeySetterTransform<R extends ConnectRecord<R>> implements Transformation<R> {

    public static final ConfigDef CONFIG_DEF = new ConfigDef()
            .define("test", ConfigDef.Type.STRING, "test", ConfigDef.Importance.MEDIUM, "Fetch the Camel.CamelAwsSqsMessageId header and set it as the key for the kafka record");


    @Override
    public R apply(R record) {
        Headers headers = record.headers();
        String key = (String) headers.lastWithName("CamelHeader.CamelAwsSqsMessageId").value();
        return record.newRecord(record.topic(), record.kafkaPartition(), null, key, Schema.STRING_SCHEMA, record.value(), record.timestamp());
    }

    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
