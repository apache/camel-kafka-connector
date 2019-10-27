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

package org.apache.camel.kafkaconnector;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Regions;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import static org.junit.Assert.fail;

public final class ContainerUtil {

    private ContainerUtil() {
    }

    /**
     * Wait for the container to be in running state
     *
     * @param container the container to wait for
     */
    public static void waitForInitialization(GenericContainer container) {
        int retries = 5;

        do {
            boolean state = container.isRunning();

            if (!state) {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    container.stop();
                    fail("Test interrupted");
                }

                retries--;
            } else {
                break;
            }
        } while (retries > 0);
    }

    /**
     * Wait for the container to be in running state
     *
     * @param container the container to wait for
     */
    public static void waitForHttpInitialization(GenericContainer container, int port) {
        int retries = 5;

        do {
            boolean state = container.isRunning();

            if (!state) {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    container.stop();
                    fail("Test interrupted");
                }

                retries--;
            } else {
                container.waitingFor(Wait.forHttp("/").forPort(port));
                break;
            }
        } while (retries > 0);
    }


    public static Properties setupAWSConfigs(LocalStackContainer container, int service) {
        Properties properties = new Properties();

        final String amazonAWSHost = "localhost:" + container.getMappedPort(service);
        properties.put(AWSConfigs.AMAZON_AWS_HOST,  amazonAWSHost);
        System.setProperty(AWSConfigs.AMAZON_AWS_HOST, amazonAWSHost);

        AWSCredentials credentials = container.getDefaultCredentialsProvider().getCredentials();

        properties.put(AWSConfigs.ACCESS_KEY, credentials.getAWSAccessKeyId());
        System.setProperty(AWSConfigs.ACCESS_KEY, credentials.getAWSAccessKeyId());

        properties.put(AWSConfigs.SECRET_KEY, credentials.getAWSSecretKey());
        System.setProperty(AWSConfigs.SECRET_KEY, credentials.getAWSSecretKey());

        properties.put(AWSConfigs.REGION, Regions.US_EAST_1.name());
        System.setProperty(AWSConfigs.REGION, Regions.US_EAST_1.name());

        return properties;
    }
}
