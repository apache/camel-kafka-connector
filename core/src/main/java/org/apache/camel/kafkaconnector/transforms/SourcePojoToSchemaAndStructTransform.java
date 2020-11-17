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

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import io.apicurio.registry.utils.converter.avro.AvroData;
import io.apicurio.registry.utils.converter.avro.AvroDataConfig;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.SchemaAndValue;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourcePojoToSchemaAndStructTransform<R extends ConnectRecord<R>> implements Transformation<R> {
    private static final Logger LOG = LoggerFactory.getLogger(SourcePojoToSchemaAndStructTransform.class);
    private static final ObjectMapper MAPPER = new ObjectMapper(new AvroFactory());

    private AvroData avroData;
    private ConcurrentMap<String, CacheEntry> avroSchemaWrapperCache;

    @Override
    public R apply(R r) {
        LOG.debug("Incoming record: {}", r);

        if (r.value() != null) {
            String recordClassCanonicalName = r.value().getClass().getCanonicalName();
            CacheEntry cacheEntry = avroSchemaWrapperCache.computeIfAbsent(recordClassCanonicalName, new Function<String, CacheEntry>() {
                @Override
                public CacheEntry apply(String s) {
                    //cache miss
                    AvroSchemaGenerator gen = new AvroSchemaGenerator();

                    try {
                        MAPPER.acceptJsonFormatVisitor(r.value().getClass(), gen);
                    } catch (JsonMappingException e) {
                        throw new ConnectException("Error in generating POJO schema.", e);
                    }

                    AvroSchema schemaWrapper = gen.getGeneratedSchema();
                    LOG.debug("Generated and cached avro schema: {}", schemaWrapper.getAvroSchema().toString(true));

                    return new CacheEntry(schemaWrapper, MAPPER.writer(schemaWrapper));
                }
            });

            SchemaAndValue connectSchemaAndData = null;
            try {
                byte[] avroDataByte = cacheEntry.getObjectWriter().writeValueAsBytes(r.value());
                Decoder decoder = DecoderFactory.get().binaryDecoder(avroDataByte, null);
                org.apache.avro.Schema avroSchema = cacheEntry.getAvroSchemaWrapper().getAvroSchema();
                DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(avroSchema);
                GenericRecord genericAvroData = datumReader.read(null, decoder);

                connectSchemaAndData = this.avroData.toConnectData(avroSchema, genericAvroData);
            } catch (IOException e) {
                throw new ConnectException("Error in generating POJO Struct.", e);
            }

            LOG.debug("Generate kafka connect schema: {}", connectSchemaAndData.schema());
            LOG.debug("Generate kafka connect value (as Struct): {}", connectSchemaAndData.value());
            return r.newRecord(r.topic(), r.kafkaPartition(), r.keySchema(), r.key(),
                    connectSchemaAndData.schema(), connectSchemaAndData.value(), r.timestamp());
        } else {
            LOG.debug("Incoming record with a null value, nothing to be done.");
            return r;
        }
    }

    @Override
    public ConfigDef config() {
        return AvroDataConfig.baseConfigDef();
    }

    @Override
    public void close() {
        //NOOP
    }

    @Override
    public void configure(Map<String, ?> configs) {
        this.avroSchemaWrapperCache = new ConcurrentHashMap<>();
        this.avroData = new AvroData(new AvroDataConfig(configs));
    }

    public Map<String, CacheEntry> getCache() {
        return this.avroSchemaWrapperCache;
    }

    public class CacheEntry {
        private AvroSchema avroSchemaWrapper;
        private ObjectWriter objectWriter;

        public CacheEntry(AvroSchema avroSchemaWrapper, ObjectWriter objectWriter) {
            this.avroSchemaWrapper = avroSchemaWrapper;
            this.objectWriter = objectWriter;
        }

        public AvroSchema getAvroSchemaWrapper() {
            return avroSchemaWrapper;
        }

        public ObjectWriter getObjectWriter() {
            return objectWriter;
        }
    }
}