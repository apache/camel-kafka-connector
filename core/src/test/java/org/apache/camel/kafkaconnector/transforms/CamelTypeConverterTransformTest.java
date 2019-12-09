package org.apache.camel.kafkaconnector.transforms;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.kafkaconnector.utils.SchemaHelper;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.junit.Test;
import org.apache.kafka.connect.connector.ConnectRecord;

import static org.junit.Assert.*;

public class CamelTypeConverterTransformTest {

    @Test
    public void testIfItConvertsConnectRecordCorrectly() {
        final SourceRecord connectRecord = new SourceRecord(Collections.emptyMap(), Collections.emptyMap(), "topic", Schema.STRING_SCHEMA, "TRUE");
        final Map<String, Object> props = new HashMap<>();
        props.put("target.type", "java.lang.Boolean");

        final Transformation<SourceRecord> transformation = new CamelTypeConverterTransform.Value<>();

        transformation.configure(props);

        final SourceRecord transformedSourceRecord = transformation.apply(connectRecord);

        assertEquals(true, transformedSourceRecord.value());
        assertEquals(Schema.BOOLEAN_SCHEMA, transformedSourceRecord.valueSchema());
    }

}