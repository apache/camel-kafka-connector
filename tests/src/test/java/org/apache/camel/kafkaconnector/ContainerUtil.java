/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.camel.kafkaconnector;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class ContainerUtil {

    /**
     * Wait for the container to be in running state
     * @param container the container to wait for
     */
    public static void waitForInitialization(GenericContainer container) {
        int retries = 5;

        do {
            boolean state = container.isRunning();

            if (state == false) {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    container.stop();
                    fail("Test interrupted");
                }

                retries--;
            }
            else {
                break;
            }
        } while (retries > 0);
    }

    /**
     * Wait for the container to be in running state
     * @param container the container to wait for
     */
    public static void waitForHttpInitialization(GenericContainer container, int port) {
        int retries = 5;

        do {
            boolean state = container.isRunning();

            if (state == false) {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    container.stop();
                    fail("Test interrupted");
                }

                retries--;
            }
            else {
                container.waitingFor(Wait.forHttp("/").forPort(port));
                break;
            }
        } while (retries > 0);
    }
}
