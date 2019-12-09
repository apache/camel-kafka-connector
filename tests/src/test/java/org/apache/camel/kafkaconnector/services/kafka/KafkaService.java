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

package org.apache.camel.kafkaconnector.services.kafka;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Provides an interface for any type of Kafka service: remote instances, local container, etc
 */
public interface KafkaService extends MethodRule {

    /**
     * Gets the addresses of the bootstrap servers in the format host1:port,host2:port,etc
     * @return
     */
    String getBootstrapServers();


    /**
     * Perform any initialization necessary
     */
    void initialize();


    @Override
    default Statement apply(Statement base, FrameworkMethod frameworkMethod, Object o) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                try {
                    base.evaluate();
                } finally {

                }
            }
        };
    }

}
