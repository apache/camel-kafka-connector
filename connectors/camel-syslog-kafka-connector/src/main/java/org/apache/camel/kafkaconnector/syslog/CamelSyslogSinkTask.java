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
package org.apache.camel.kafkaconnector.syslog;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.netty.CamelNettySinkTask;

public class CamelSyslogSinkTask extends CamelNettySinkTask {

    @Override
    protected CamelSinkConnectorConfig getCamelSinkConnectorConfig(
            Map<String, String> props) {
        return new CamelSyslogSinkConnectorConfig(props);
    }
    @Override
    protected Map<String, String> getDefaultConfig() {
        Map<String, String> defaultConfig = new HashMap<String, String>();
        defaultConfig.putAll(super.getDefaultConfig());
        defaultConfig.put(CamelSinkConnectorConfig.CAMEL_SINK_MARSHAL_CONF, "syslog");
        defaultConfig.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "encoders", "#syslogencoder");
        defaultConfig.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "sync", "false");
        defaultConfig.put(CamelSinkTask.getCamelSinkEndpointConfigPrefix() + "useByteBuf", "true");
        defaultConfig.put("camel.beans.syslogencoder", "#class:org.apache.camel.component.syslog.netty.Rfc5425Encoder");
        return defaultConfig;
    }
}