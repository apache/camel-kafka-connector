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
package org.apache.camel.kafkaconnector.aws2s3.transformers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Map;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.header.Headers;
import org.apache.kafka.connect.transforms.Transformation;
import org.apache.camel.kafkaconnector.aws2s3.models.StorageHeader;
import org.apache.camel.kafkaconnector.aws2s3.models.StorageRecord;
import java.io.ByteArrayInputStream;

public class RecordToJSONTransforms<R extends ConnectRecord<R>> implements Transformation<R> {
  public static final String FIELD_KEY_CONFIG = "key";
  public static final ConfigDef CONFIG_DEF =
      new ConfigDef()
          .define(
              FIELD_KEY_CONFIG,
              ConfigDef.Type.STRING,
              null,
              ConfigDef.Importance.MEDIUM,
              "Add the key and the header to the record value");

  @Override
  public void configure(Map<String, ?> configs) {}

  @Override
  public R apply(R record) {
    // Convert headers to StorageHeader format
    Headers headers = record.headers();
    ArrayList<StorageHeader> headerList = new ArrayList<StorageHeader>(headers.size());
    for (Header h : headers) {
      headerList.add(new StorageHeader(h.key(), (String) h.value()));
    }
    StorageHeader[] storageHeaders = new StorageHeader[headers.size()];
    StorageRecord storageRecord =
        new StorageRecord(
            (String) record.key(), (String) record.value(), headerList.toArray(storageHeaders));

    // Serialize
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();
    String storageRecordJSON = gson.toJson(storageRecord, StorageRecord.class);
    InputStream storageRecordStream = new ByteArrayInputStream(storageRecordJSON.getBytes())
    return record.newRecord(
        record.topic(),
        record.kafkaPartition(),
        null,
        record.key(),
        Schema.STRING_SCHEMA,
        storageRecordStream,
        record.timestamp());
  }

  @Override
  public void close() {}

  @Override
  public ConfigDef config() {
    return CONFIG_DEF;
  }
}
