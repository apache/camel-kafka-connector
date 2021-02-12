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

package org.apache.camel.kafkaconnector.syslog.services;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.syslog.SyslogDataFormat;
import org.apache.camel.component.syslog.netty.Rfc5425FrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SinkRouteConfigurator implements RouteConfigurator {
    private static final Logger LOG = LoggerFactory.getLogger(SinkRouteConfigurator.class);

    private final String protocol;
    private final String host;
    private final int port;

    public SinkRouteConfigurator(String protocol, String host, int port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
    }

    @Override
    public void configure(CamelContext camelContext) throws Exception {
        camelContext.getRegistry().bind("decoder", new Rfc5425FrameDecoder());

        LOG.debug("Adding routes");
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                fromF("netty:%s://%s:%d?sync=false&decoders=#decoder", protocol, host, port)
                        .unmarshal(new SyslogDataFormat()).to("seda:syslog");
            }
        });
    }
}
