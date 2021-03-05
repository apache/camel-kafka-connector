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

package org.apache.camel.kafkaconnector.google.pubsub.sink;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SinkConnectorPropertyFactory;

public class CamelGooglePubSubPropertyFactory extends SinkConnectorPropertyFactory<CamelGooglePubSubPropertyFactory> {

    public CamelGooglePubSubPropertyFactory withProjectId(String value) {
        return setProperty("camel.sink.path.projectId", value);
    }

    public CamelGooglePubSubPropertyFactory withDestinationName(String value) {
        return setProperty("camel.sink.path.destinationName", value);
    }

    public CamelGooglePubSubPropertyFactory withEndpoint(String value) {
        return setProperty("camel.component.google-pubsub.endpoint", value);
    }


    public EndpointUrlBuilder<CamelGooglePubSubPropertyFactory> withUrl(String projectId, String destinationName) {
        String queueUrl = String.format("google-pubsub:%s:%s", projectId, destinationName);

        return new EndpointUrlBuilder<>(this::withSinkUrl, queueUrl);
    }

    public static CamelGooglePubSubPropertyFactory basic() {
        return new CamelGooglePubSubPropertyFactory()
                    .withTasksMax(1)
                    .withName("CamelGooglePubSub")
                    .withConnectorClass("org.apache.camel.kafkaconnector.googlepubsub.CamelGooglepubsubSinkConnector")
                    .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                    .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");

    }

}
