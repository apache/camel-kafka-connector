package org.apache.camel.kafkaconnector.utils;

import java.util.Map;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;

public final class SchemaHelper {

    /**
     * Try to build a {@link SchemaBuilder} for a value of type {@link Object}
     * However, this will only build the schema only for known types, in case it can not return the precise SchemaBuilder type
     * it will return an optional {@link SchemaBuilder.BYTE}
     * @param value to return the SchemaBuilder for
     *
     * @return {@link SchemaBuilder} instance
     */
    public static SchemaBuilder buildSchemaBuilderForType(final Object value) {
        if (value instanceof Byte) {
            return SchemaBuilder.bytes();
        }
        if (value instanceof Short) {
            return SchemaBuilder.int16();
        }
        if (value instanceof Integer) {
            return SchemaBuilder.int32();
        }
        if (value instanceof Long) {
            return SchemaBuilder.int64();
        }
        if (value instanceof Float) {
            return SchemaBuilder.float32();
        }
        if (value instanceof Double) {
            return SchemaBuilder.float64();
        }
        if (value instanceof Boolean) {
            return SchemaBuilder.bool();
        }
        if (value instanceof String) {
            return SchemaBuilder.string();
        }
        if (value instanceof Map) {
            // Note: optimally we should define the schema better for map, however for now we will keep it abstract
            return new SchemaBuilder(Schema.Type.MAP);
        }
        if (value instanceof Iterable) {
            // Note: optimally we should define the schema better for Iterable, however for now we will keep it abstract
            return new SchemaBuilder(Schema.Type.ARRAY);
        }
        if (value instanceof Struct) {
            return SchemaBuilder.struct();
        }

        // if we do not fine any of schema out of the above, we just return an an optional byte schema
        return SchemaBuilder.bytes().optional();
    }
}
