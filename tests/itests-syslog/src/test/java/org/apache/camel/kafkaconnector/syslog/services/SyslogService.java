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
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.infra.common.TestUtils;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SyslogService implements BeforeAllCallback, AfterAllCallback {
    private final CamelContext camelContext = new DefaultCamelContext();

    private final RouteConfigurator routeConfigurator;

    public SyslogService(RouteConfigurator routeConfigurator) {
        this.routeConfigurator = routeConfigurator;
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        routeConfigurator.configure(camelContext);

        camelContext.start();
        TestUtils.waitFor(camelContext::isStarted);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        camelContext.stop();
        TestUtils.waitFor(camelContext::isStopped);
    }

    public CamelContext getCamelContext() {
        return camelContext;
    }

    public Exchange getFirstExchangeToBeReceived() {
        return camelContext.createConsumerTemplate().receive("seda:syslog", 10000L);
    }

    public static SyslogService sinkSyslogServiceFactory(String protocol, String host, int port) {
        SinkRouteConfigurator sinkRouteConfigurator = new SinkRouteConfigurator(protocol, host, port);

        return new SyslogService(sinkRouteConfigurator);
    }

    public static SyslogService sourceSyslogServiceFactory(String protocol, String host, int port) {
        SourceRouteConfigurator sourceRouteConfigurator = new SourceRouteConfigurator(protocol, host, port);

        return new SyslogService(sourceRouteConfigurator);
    }
}
