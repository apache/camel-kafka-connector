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

package org.apache.camel.kafkaconnector.sjms2.common;

import org.apache.camel.test.infra.dispatch.router.services.DispatchRouterContainer;
import org.apache.camel.test.infra.messaging.services.MessagingServiceFactory;

public final class SJMS2Common {
    /**
     * The default JMS queue name used during the tests
     */
    public static final String DEFAULT_JMS_QUEUE = "ckc.queue";

    private SJMS2Common() {

    }

    public static MessagingServiceFactory.MessagingLocalContainerService<DispatchRouterContainer> createLocalService() {
        DispatchRouterContainer container = new DispatchRouterContainer();

        return new MessagingServiceFactory.MessagingLocalContainerService<>(container, c -> container.defaultEndpoint());
    }
}
