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
package org.apache.camel.kafkaconnector.cxf.services;

import org.apache.camel.kafkaconnector.common.utils.NetworkUtils;
import org.apache.camel.kafkaconnector.cxf.common.CXFProperties;
import org.apache.camel.test.infra.common.TestUtils;
import org.apache.cxf.BusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.jaxws.EndpointImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CXFEmbeddedServerService implements CXFService {
    private static final Logger LOG = LoggerFactory.getLogger(CXFEmbeddedServerService.class);

    private final ServerFactoryBeanConfigurator serverFactoryBeanConfigurator;
    private final JaxWsServiceConfigurator jaxwsServiceConfigurator;
    private Server server;
    private EndpointImpl endpoint;

    private int simplePort;
    private int jaxWsPort;

    public CXFEmbeddedServerService(ServerFactoryBeanConfigurator serverFactoryBeanConfigurator, JaxWsServiceConfigurator jaxwsServiceConfigurator) {
        this.serverFactoryBeanConfigurator = serverFactoryBeanConfigurator;
        this.jaxwsServiceConfigurator = jaxwsServiceConfigurator;
    }

    @Override
    public String getSimpleServerAddress() {
        return String.format("http://%s:%d/%s/simpletest", NetworkUtils.getHostname(), simplePort, getClass().getSimpleName());
    }

    @Override
    public String getJaxWsServerAddress() {
        return String.format("http://%s:%d/%s/jaxwstest", NetworkUtils.getHostname(), jaxWsPort, getClass().getSimpleName());
    }

    public void configure() {
        simplePort = NetworkUtils.getFreePort();
        jaxWsPort = NetworkUtils.getFreePort();

        ServerFactoryBean svrBean = new ServerFactoryBean();

        // start a simple front service
        svrBean.setAddress(getSimpleServerAddress());

        serverFactoryBeanConfigurator.configure(svrBean);
        svrBean.setBus(BusFactory.getDefaultBus());

        server = svrBean.create();
        server.getEndpoint().getInInterceptors().add(new LoggingInInterceptor());
        server.getEndpoint().getOutInterceptors().add(new LoggingOutInterceptor());

        // start a jaxws front service from the custom configurator
        endpoint = jaxwsServiceConfigurator.configureEndpoint(getJaxWsServerAddress());

        endpoint.getInInterceptors().add(new LoggingInInterceptor());
        endpoint.getOutInterceptors().add(new LoggingOutInterceptor());

        TestUtils.waitFor(() -> endpoint.isPublished());
    }

    @Override
    public void registerProperties() {
        System.setProperty(CXFProperties.SIMPLE_SERVER_ADDRESS, getSimpleServerAddress());
        System.setProperty(CXFProperties.JAXWS_SERVER_ADDRESS, getJaxWsServerAddress());
    }

    @Override
    public void initialize() {
        LOG.info("Trying to start the CXF embedded server");
        configure();

        registerProperties();

        LOG.info("CXF simple service running at {}", getSimpleServerAddress());
        LOG.info("CXF JAX WS service running at {}", getJaxWsServerAddress());
    }

    @Override
    public void shutdown() {
        LOG.info("Stopping the CXF embedded server");

        endpoint.stop();
        server.stop();
        server.destroy();
    }
}
