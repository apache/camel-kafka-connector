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

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Common test constants and utilities
 */
public final class TestCommon {
    /**
     * The default JMS queue name used during the tests
     */
    public static final String DEFAULT_JMS_QUEUE = "ckc.queue";

    /**
     * The default SQS queue name used during the tests
     */
    public static final String DEFAULT_SQS_QUEUE = "ckc";

    /**
     * The default SQS queue name used during the tests
     */
    public static final String DEFAULT_SQS_QUEUE_FOR_SNS = "ckcsns";

    /**
     * The default SNS queue name used during the tests
     */
    public static final String DEFAULT_SNS_QUEUE = "ckc-sns";

    /**
     * The default S3 bucket name used during the tests
     */
    public static final String DEFAULT_S3_BUCKET = "ckc-s3";

    /**
     * The default Kinesis stream name used during the tests
     */
    public static final String DEFAULT_KINESIS_STREAM = "ckc-kin-stream";

    /**
     * The default ElasticSearch cluster name for usage during the tests
     */
    public static final String DEFAULT_ELASTICSEARCH_CLUSTER = "docker-cluster";

    /**
     * The default ElasticSearch index for usage during the tests
     */
    public static final String DEFAULT_ELASTICSEARCH_INDEX = "ckc-index";

    private static final Logger LOG = LoggerFactory.getLogger(TestCommon.class);

    private TestCommon() {
    }


    public static String getDefaultTestTopic(Class<?> clazz) {
        return clazz.getName();
    }


    /**
     * Wait for a given condition to be true or the retry amount (30) to expire
     * @param resourceCheck
     * @param payload
     * @param <T>
     */
    public static <T> void waitFor(Predicate<T> resourceCheck, T payload) {
        boolean state;
        int retries = 30;
        int waitTime = 1000;
        do {
            try {
                state = resourceCheck.test(payload);

                if (!state) {
                    LOG.debug("The resource is not yet available. Waiting {} seconds before retrying",
                            TimeUnit.MILLISECONDS.toSeconds(waitTime));
                    retries--;
                    Thread.sleep(waitTime);
                }
            } catch (InterruptedException e) {
                break;
            }

        } while (!state && retries > 0);
    }


    /**
     * Wait for a given condition to be true or the retry amount (30) to expire
     * @param resourceCheck
     */
    public static void waitFor(BooleanSupplier resourceCheck) {
        boolean state;
        int retries = 30;
        int waitTime = 1000;
        do {
            try {
                state = resourceCheck.getAsBoolean();

                if (!state) {
                    LOG.debug("The resource is not yet available. Waiting {} seconds before retrying",
                            TimeUnit.MILLISECONDS.toSeconds(waitTime));
                    retries--;
                    Thread.sleep(waitTime);
                }
            } catch (InterruptedException e) {
                break;
            }

        } while (!state && retries > 0);
    }

    public static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;

        return (int)(Math.random() * range) + min;
    }
}
