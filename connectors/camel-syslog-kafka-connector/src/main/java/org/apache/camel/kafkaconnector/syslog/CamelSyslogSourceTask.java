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

import org.apache.camel.kafkaconnector.CamelSourceConnectorConfig;
import org.apache.camel.kafkaconnector.CamelSourceTask;
import org.apache.camel.kafkaconnector.netty.CamelNettySourceTask;

public class CamelSyslogSourceTask extends CamelNettySourceTask {

    @Override
    protected CamelSourceConnectorConfig getCamelSourceConnectorConfig(
            Map<String, String> props) {
        return new CamelSyslogSourceConnectorConfig(props);
    }
    @Override
    protected Map<String, String> getDefaultConfig() {
        Map<String, String> defaultConfig = new HashMap<String, String>();
        defaultConfig.putAll(super.getDefaultConfig());
        defaultConfig.put(CamelSourceConnectorConfig.CAMEL_SOURCE_UNMARSHAL_CONF, "syslog");
        defaultConfig.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "decoders", "#syslogdecoder");
        defaultConfig.put(CamelSourceTask.getCamelSourceEndpointConfigPrefix() + "sync", "false");
        defaultConfig.put("camel.beans.syslogdecoder", "#class:org.apache.camel.component.syslog.netty.Rfc5425FrameDecoder");
        return defaultConfig;
    }
}