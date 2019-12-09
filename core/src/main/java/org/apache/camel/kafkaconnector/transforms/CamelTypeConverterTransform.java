package org.apache.camel.kafkaconnector.transforms;

import java.util.Map;

import org.apache.camel.TypeConverter;
import org.apache.camel.kafkaconnector.utils.SchemaHelper;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.errors.DataException;
import org.apache.kafka.connect.transforms.util.SchemaUtil;
import org.apache.kafka.connect.transforms.util.SimpleConfig;

public abstract class CamelTypeConverterTransform<R extends ConnectRecord<R>> extends CamelTransformSupport<R> {

    private interface ConfigName {
        String FIELD_TARGET_TYPE = "target.type";
    }

    private Class<?> fieldTargetType;

    public static final ConfigDef CONFIG_DEF = new ConfigDef()
            .define(ConfigName.FIELD_TARGET_TYPE, ConfigDef.Type.CLASS, null, ConfigDef.Importance.HIGH,
                    "The target field type to convert the value from, this is full qualified Java class, e.g: java.util.Map");

    @Override
    public R apply(R record) {
        final Schema schema = operatingSchema(record);
        final Object value = operatingValue(record);

        final Object convertedValue = convertValueWithCamelTypeConverter(value);
        final Schema updatedSchema = getOrBuildRecordSchema(schema, convertedValue);

        return newRecord(record, updatedSchema, convertedValue);
    }

    private Object convertValueWithCamelTypeConverter(final Object originalValue) {
        final TypeConverter converter = getCamelContext().getTypeConverter();
        final Object convertedValue = converter.tryConvertTo(fieldTargetType, originalValue);

        if (convertedValue == null) {
            throw new DataException(String.format("CamelTypeConverter was not able to converter value `%s` to target type of `%s`", originalValue, fieldTargetType.getSimpleName()));
        }

        return convertedValue;
    }

    private Schema getOrBuildRecordSchema(final Schema originalSchema, final Object value) {
        final SchemaBuilder builder = SchemaUtil.copySchemaBasics(originalSchema, SchemaHelper.buildSchemaBuilderForType(value));

        if (originalSchema.isOptional())
            builder.optional();
        if (originalSchema.defaultValue() != null)
            builder.defaultValue(convertValueWithCamelTypeConverter(originalSchema.defaultValue()));

        return builder.build();
    }

    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> props) {
        final SimpleConfig config = new SimpleConfig(CONFIG_DEF, props);
        fieldTargetType = config.getClass(ConfigName.FIELD_TARGET_TYPE);
    }

    protected abstract Schema operatingSchema(R record);

    protected abstract Object operatingValue(R record);

    protected abstract R newRecord(R record, Schema updatedSchema, Object updatedValue);

    public static final class Key<R extends ConnectRecord<R>> extends CamelTypeConverterTransform<R> {
        @Override
        protected Schema operatingSchema(R record) {
            return record.keySchema();
        }

        @Override
        protected Object operatingValue(R record) {
            return record.key();
        }

        @Override
        protected R newRecord(R record, Schema updatedSchema, Object updatedValue) {
            return record.newRecord(record.topic(), record.kafkaPartition(), updatedSchema, updatedValue, record.valueSchema(), record.value(), record.timestamp());
        }
    }

    public static final class Value<R extends ConnectRecord<R>> extends CamelTypeConverterTransform<R> {
        @Override
        protected Schema operatingSchema(R record) {
            return record.valueSchema();
        }

        @Override
        protected Object operatingValue(R record) {
            return record.value();
        }

        @Override
        protected R newRecord(R record, Schema updatedSchema, Object updatedValue) {
            return record.newRecord(record.topic(), record.kafkaPartition(), record.keySchema(), record.key(), updatedSchema, updatedValue, record.timestamp());
        }
    }
}
