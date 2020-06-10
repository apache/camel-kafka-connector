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

package org.apache.camel.kafkaconnector.sjms2.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JMSServiceFactory {
    private static final Logger LOG = LoggerFactory.getLogger(JMSServiceFactory.class);

    private JMSServiceFactory() {
    }

    public static JMSService createService() {
        String jmsInstanceType = System.getProperty("jms-service.instance.type");

        if (jmsInstanceType == null || jmsInstanceType.equals("local-dispatch-router-container")) {
            return new ContainerLocalService(new QpidDispatchRouterContainer());
        }

        if (jmsInstanceType.equals("local-artemis-container")) {
            return new ContainerLocalService(new ArtemisContainer());
        }

        if (jmsInstanceType.equals("remote")) {
            return new RemoteJMSService();
        }

        LOG.error("Invalid JMS instance type: {}. Must be one of 'remote', 'local-artemis-container' or 'local-dispatch-router-container",
                jmsInstanceType);
        throw new UnsupportedOperationException("Invalid JMS instance type:");
    }
}
