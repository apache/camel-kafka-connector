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

package org.apache.camel.kafkaconnector.aws.v2.cw.sink;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.CamelSinkTask;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.test.CamelSinkTestSupport;
import org.apache.camel.kafkaconnector.common.test.StringMessageProducer;
import org.apache.camel.test.infra.aws.common.services.AWSService;
import org.apache.camel.test.infra.aws2.clients.AWSSDKClientUtils;
import org.apache.camel.test.infra.aws2.services.AWSLocalContainerService;
import org.apache.camel.test.infra.aws2.services.AWSRemoteService;
import org.apache.camel.test.infra.aws2.services.AWSServiceFactory;
import org.apache.camel.test.infra.aws2.services.Service;
import org.apache.camel.test.infra.common.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.Dimension;
import software.amazon.awssdk.services.cloudwatch.model.ListMetricsRequest;
import software.amazon.awssdk.services.cloudwatch.model.ListMetricsResponse;
import software.amazon.awssdk.services.cloudwatch.model.Metric;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfSystemProperty(named = "enable.slow.tests", matches = "true")
public class CamelSinkAWSCWITCase extends CamelSinkTestSupport {

    @RegisterExtension
    public static AWSService awsService = AWSServiceFactory
            .builder()
            .addRemoteMapping(AWSRemoteService::new)
            .addLocalMapping(CustomAWSCloudWatchLocalContainerService::new)
            .withPropertyNameFormat("%s-service.instance.type")
            .build();

    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkAWSCWITCase.class);

    private static String metricName = "test-metric";
    private CloudWatchClient client;
    private String namespace;

    private volatile int received;
    private final int expect = 10;

    // Dimensions are broken in localstack implementation of CloudWatch 0.12.10
    private static class CustomAWSCloudWatchLocalContainerService extends AWSLocalContainerService {
        public CustomAWSCloudWatchLocalContainerService() {
            super("localstack/localstack:0.12.9.1", Service.CLOUD_WATCH);
        }
    }

    private static class CustomProducer extends StringMessageProducer {
        public CustomProducer(String bootstrapServer, String topicName, int count) {
            super(bootstrapServer, topicName, count);
        }

        @Override
        public Map<String, String> messageHeaders(String text, int current) {
            Map<String, String> headers = new HashMap<>();

            headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "metric-name", metricName);
            //TODO: once this https://github.com/apache/camel-kamelets/pull/522 is published  the following headers
            // must be changed to metric-dimension-name and metric-dimension-value
            headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsCwMetricDimensionName",
                    "test-dimension-" + current);
            headers.put(CamelSinkTask.HEADER_CAMEL_PREFIX + "CamelAwsCwMetricDimensionValue", String.valueOf(current));

            return headers;
        }
    }

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-aws-cloudwatch-sink-kafka-connector"};
    }

    @BeforeEach
    public void setUp() {
        client = AWSSDKClientUtils.newCloudWatchClient();

        namespace = "cw-" + TestUtils.randomWithRange(0, 1000);
        LOG.debug("Using namespace {} for the test", namespace);

        received = 0;
    }

    @Override
    protected void consumeMessages(CountDownLatch latch) {
        try {
            ListMetricsRequest request = ListMetricsRequest.builder()
                    .namespace(namespace)
                    .metricName(metricName)
                    .build();

            while (true) {
                ListMetricsResponse response = client.listMetrics(request);

                for (Metric metric : response.metrics()) {
                    LOG.info("Retrieved metric {}, dimensions {}", metric.metricName(), metric.dimensions());

                    for (Dimension dimension : metric.dimensions()) {
                        LOG.info("Dimension {} value: {}", dimension.name(), dimension.value());
                        received++;

                        if (received == expect) {
                            return;
                        }
                    }
                }

                if (!waitForData()) {
                    break;
                }
            }
        } finally {
            latch.countDown();
        }
    }

    @Override
    protected void verifyMessages(CountDownLatch latch) throws InterruptedException {
        if (latch.await(110, TimeUnit.SECONDS)) {
            assertEquals(expect, received, "Didn't process the expected amount of messages: " + received + " != " + expect);
        } else {
            fail(String.format("Failed to receive the messages within the specified time: received %d of %d",
                    received, expect));
        }
    }

    @Test
    @Timeout(value = 120)
    public void testBasicSendReceive() throws Exception {
        Properties amazonProperties = awsService.getConnectionProperties();
        String topicName = getTopicForTest(this);

        ConnectorPropertyFactory testProperties = CamelAWSCWPropertyFactory
                .basic()
                .withTopics(topicName)
                .withConfiguration(TestCloudWatchConfiguration.class.getName())
                .withAmazonConfig(amazonProperties)
                .withNamespace(namespace);

        runTest(testProperties, new CustomProducer(getKafkaService().getBootstrapServers(), topicName, expect));
    }
}
