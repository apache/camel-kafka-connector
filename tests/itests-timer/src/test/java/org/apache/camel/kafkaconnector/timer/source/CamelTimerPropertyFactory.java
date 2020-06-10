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

package org.apache.camel.kafkaconnector.timer.source;

import org.apache.camel.kafkaconnector.common.EndpointUrlBuilder;
import org.apache.camel.kafkaconnector.common.SourceConnectorPropertyFactory;

final class CamelTimerPropertyFactory extends SourceConnectorPropertyFactory<CamelTimerPropertyFactory> {

    private CamelTimerPropertyFactory() {

    }

    public CamelTimerPropertyFactory withRepeatCount(int repeatCount) {
        return setProperty("camel.source.endpoint.repeatCount", repeatCount);
    }

    public CamelTimerPropertyFactory withTimerName(String timerName) {
        return setProperty("camel.source.path.timerName", timerName);
    }

    public EndpointUrlBuilder<CamelTimerPropertyFactory> withUrl(String timerName) {
        String url = String.format("timer:%s", timerName);

        return new EndpointUrlBuilder<>(this::withSourceUrl, url);
    }

    public static CamelTimerPropertyFactory basic() {
        return new CamelTimerPropertyFactory()
                .withName("CamelTimerSourceConnector")
                .withTasksMax(1)
                .withConnectorClass("org.apache.camel.kafkaconnector.timer.CamelTimerSourceConnector")
                .withKeyConverterClass("org.apache.kafka.connect.storage.StringConverter")
                .withValueConverterClass("org.apache.kafka.connect.storage.StringConverter");
    }
}
