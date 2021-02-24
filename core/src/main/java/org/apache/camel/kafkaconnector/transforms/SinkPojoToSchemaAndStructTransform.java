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
package org.apache.camel.kafkaconnector.transforms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import io.apicurio.registry.utils.converter.avro.AvroData;
import io.apicurio.registry.utils.converter.avro.AvroDataConfig;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.transforms.Transformation;
import org.apache.kafka.connect.transforms.util.SimpleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SinkPojoToSchemaAndStructTransform<R extends ConnectRecord<R>> implements Transformation<R> {
    public static final String CAMEL_TRANSFORMER_SINK_POJO_CLASS_PROPERTY = "camel.transformer.sink.pojo.class";

    private static final Logger LOG = LoggerFactory.getLogger(SinkPojoToSchemaAndStructTransform.class);

    private static final ObjectMapper MAPPER = new ObjectMapper(new AvroFactory());
    private static final String CAMEL_TRANSFORMER_SINK_POJO_CLASS_DOC = "Full qualified class name of the pojo you want your record value converted to";
    private static final Object CAMEL_TRANSFORMER_SINK_POJO_CLASS_DEFAULT = ConfigDef.NO_DEFAULT_VALUE;
    private static final ConfigDef CONFIG_DEF = (new ConfigDef()).define(CAMEL_TRANSFORMER_SINK_POJO_CLASS_PROPERTY, ConfigDef.Type.STRING, CAMEL_TRANSFORMER_SINK_POJO_CLASS_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_TRANSFORMER_SINK_POJO_CLASS_DOC);

    private String pojoClass;
    private ObjectReader objectReader;
    private AvroData avroData;


    @Override
    public R apply(R r) {
        LOG.debug("Incoming record: {}", r);

        if (r.value() != null && r.valueSchema() != null && Schema.Type.STRUCT.equals(r.valueSchema().type())) {
            GenericRecord avroGenericRecord = (GenericRecord)avroData.fromConnectData(r.valueSchema(), r.value());

            if (avroGenericRecord == null) {
                LOG.warn("No GenericRecord was converted as part of this transformation");

                return r;
            }

            LOG.debug("GenericRecord created: {} \nwith schema: {}", avroGenericRecord, avroGenericRecord.getClass().getName());

            GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<>(avroGenericRecord.getSchema());

            Object pojo;
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
                writer.write(avroGenericRecord, encoder);
                encoder.flush();

                byte[] avroData = out.toByteArray();
                pojo = objectReader
                        .with(new AvroSchema(avroGenericRecord.getSchema()))
                        .readValue(avroData);
                LOG.debug("Pojo of class {} created: {}", pojo.getClass(), pojo);
            } catch (IOException e) {
                throw new ConnectException("Error in generating POJO from Struct.", e);
            }

            LOG.debug("Generate pojo: {}", pojo);
            return r.newRecord(r.topic(), r.kafkaPartition(), r.keySchema(), r.key(),
                    null, pojo, r.timestamp());
        } else {
            LOG.debug("Incoming record with a null value or a value schema != Schema.Type.STRUCT, nothing to be done.");
            return r;
        }
    }

    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }

    @Override
    public void close() {
        //NOOP
    }

    @Override
    public void configure(Map<String, ?> configs) {
        SimpleConfig config = new SimpleConfig(CONFIG_DEF, configs);
        this.pojoClass = config.getString(CAMEL_TRANSFORMER_SINK_POJO_CLASS_PROPERTY);

        this.avroData = new AvroData(new AvroDataConfig(configs));

        try {
            this.objectReader = MAPPER.readerFor(Class.forName(pojoClass));
        } catch (ClassNotFoundException e) {
            throw new ConnectException("Unable to initialize SinkPojoToSchemaAndStructTransform ", e);
        }
    }
}