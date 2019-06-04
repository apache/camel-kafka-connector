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

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

public class CamelSinkTask extends SinkTask {
   public static final String KAFKA_RECORD_KEY_HEADER = "camel-.kafka.connector.record.key";
   private static Logger log = LoggerFactory.getLogger(CamelSinkTask.class);
   private static final String LOCAL_URL = "direct:start";
   private CamelContext camel;
   private ProducerTemplate producer;
   private CamelSinkConnectorConfig config;

   @Override
   public String version() {
      return new CamelSinkConnector().version();
   }

   @Override
   public void start(Map<String, String> props) {
      try {
         log.info("Starting CamelSinkTask connector task");
         config = new CamelSinkConnectorConfig(props);

         final String remoteUrl = config.getString(CamelSinkConnectorConfig.CAMEL_SINK_URL_CONF);

         camel = new DefaultCamelContext();

         log.info("Creating Camel route from({}).to({})", LOCAL_URL, remoteUrl);
         camel.addRoutes(new RouteBuilder() {
            public void configure() {
               from(LOCAL_URL).to(remoteUrl);
            }
         });

         producer = camel.createProducerTemplate();

         log.info("Starting CamelContext");
         camel.start();
         log.info("CamelContext started");
         log.info("CamelSinkTask connector task started");
      } catch (Exception e) {
         throw new ConnectException("Failed to create and start Camel context", e);
      }
   }

   @Override
   public void put(Collection<SinkRecord> sinkRecords) {
      for (SinkRecord record : sinkRecords) {
         producer.sendBodyAndHeader(LOCAL_URL, record.value(), KAFKA_RECORD_KEY_HEADER, record.key());
      }
   }

   @Override
   public void stop() {
      try {
         camel.stop();
      } catch (Exception e) {
         throw new ConnectException("Failed to stop Camel context", e);
      }
   }
}
