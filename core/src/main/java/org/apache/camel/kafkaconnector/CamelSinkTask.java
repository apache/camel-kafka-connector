/**
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

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class CamelSinkTask extends SinkTask {
   private static Logger log = LoggerFactory.getLogger(CamelSinkTask.class);
   private CamelSinkConnectorConfig config;

   @Override
   public String version() {
      return VersionUtil.getVersion();
   }

   @Override
   public void start(Map<String, String> map) {
      config = new CamelSinkConnectorConfig(map);
      //TODO: redo the camel way
   }

   @Override
   public void put(Collection<SinkRecord> collection) {
      if (collection.isEmpty()) {
         return;
      }
      //TODO: redo the camel way
      boolean useProto = true;
      final int recordsCount = collection.size();
      log.info("Received {} records", recordsCount);
      Iterator it = collection.iterator();
      while (it.hasNext()) {
         SinkRecord record = (SinkRecord) it.next();
         log.info("Record kafka coordinates:({}-{}-{}). Writing it to Infinispan...", record.topic(), record.key(),
               record.value());
      }
      //TODO
   }

   @Override
   public void flush(Map<TopicPartition, OffsetAndMetadata> map) {
   }

   @Override
   public void stop() {
      //TODO: clean up camel
   }
}
