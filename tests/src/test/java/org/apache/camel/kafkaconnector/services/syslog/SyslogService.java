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
package org.apache.camel.kafkaconnector.services.syslog;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.syslog.SyslogDataFormat;
import org.apache.camel.component.syslog.netty.Rfc5425FrameDecoder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SyslogService implements BeforeAllCallback, AfterAllCallback {
    private static final CamelContext CAMEL_CONTEXT = new DefaultCamelContext();

    private static String protocol;
    private static String host;
    private static int port;

    public SyslogService(String protocol, String host, int port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        CAMEL_CONTEXT.getRegistry().bind("decoder", new Rfc5425FrameDecoder());
        CAMEL_CONTEXT.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("netty:" + protocol + ":" + host + ":" + port + "?sync=false&decoders=#decoder").unmarshal(new SyslogDataFormat()).to("seda:syslog");
            }
        });
        CAMEL_CONTEXT.start();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        CAMEL_CONTEXT.stop();
    }

    public Exchange getFirstExchangeToBeReceived() {
        return CAMEL_CONTEXT.createConsumerTemplate().receive("seda:syslog", 10000L);
    }
}
