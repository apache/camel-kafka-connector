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
package org.apache.camel.kafkaconnector.dataset;

import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.kafkaconnector.CamelSinkConnectorConfig;
import org.apache.kafka.common.config.ConfigDef;

@Generated("This class has been generated by camel-kafka-connector-generator-maven-plugin, remove this annotation to prevent it from being generated.")
public class CamelDatasetSinkConnectorConfig extends CamelSinkConnectorConfig {

    public static final String CAMEL_SINK_DATASET_PATH_NAME_CONF = "camel.sink.path.name";
    private static final String CAMEL_SINK_DATASET_PATH_NAME_DOC = "Name of DataSet to lookup in the registry";
    private static final String CAMEL_SINK_DATASET_PATH_NAME_DEFAULT = null;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_DATA_SET_INDEX_CONF = "camel.sink.endpoint.dataSetIndex";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_DATA_SET_INDEX_DOC = "Controls the behaviour of the CamelDataSetIndex header. For Consumers: - off = the header will not be set - strict/lenient = the header will be set For Producers: - off = the header value will not be verified, and will not be set if it is not present = strict = the header value must be present and will be verified = lenient = the header value will be verified if it is present, and will be set if it is not present One of: [strict] [lenient] [off]";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_DATA_SET_INDEX_DEFAULT = "lenient";
    public static final String CAMEL_SINK_DATASET_ENDPOINT_ASSERT_PERIOD_CONF = "camel.sink.endpoint.assertPeriod";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_ASSERT_PERIOD_DOC = "Sets a grace period after which the mock endpoint will re-assert to ensure the preliminary assertion is still valid. This is used for example to assert that exactly a number of messages arrives. For example if expectedMessageCount(int) was set to 5, then the assertion is satisfied when 5 or more message arrives. To ensure that exactly 5 messages arrives, then you would need to wait a little period to ensure no further message arrives. This is what you can use this method for. By default this period is disabled.";
    private static final Long CAMEL_SINK_DATASET_ENDPOINT_ASSERT_PERIOD_DEFAULT = 0L;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_CONSUME_DELAY_CONF = "camel.sink.endpoint.consumeDelay";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_CONSUME_DELAY_DOC = "Allows a delay to be specified which causes a delay when a message is consumed by the producer (to simulate slow processing)";
    private static final Long CAMEL_SINK_DATASET_ENDPOINT_CONSUME_DELAY_DEFAULT = 0L;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_EXPECTED_COUNT_CONF = "camel.sink.endpoint.expectedCount";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_EXPECTED_COUNT_DOC = "Specifies the expected number of message exchanges that should be received by this endpoint. Beware: If you want to expect that 0 messages, then take extra care, as 0 matches when the tests starts, so you need to set a assert period time to let the test run for a while to make sure there are still no messages arrived; for that use setAssertPeriod(long). An alternative is to use NotifyBuilder, and use the notifier to know when Camel is done routing some messages, before you call the assertIsSatisfied() method on the mocks. This allows you to not use a fixed assert period, to speedup testing times. If you want to assert that exactly n'th message arrives to this mock endpoint, then see also the setAssertPeriod(long) method for further details.";
    private static final Integer CAMEL_SINK_DATASET_ENDPOINT_EXPECTED_COUNT_DEFAULT = -1;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_FAIL_FAST_CONF = "camel.sink.endpoint.failFast";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_FAIL_FAST_DOC = "Sets whether assertIsSatisfied() should fail fast at the first detected failed expectation while it may otherwise wait for all expected messages to arrive before performing expectations verifications. Is by default true. Set to false to use behavior as in Camel 2.x.";
    private static final Boolean CAMEL_SINK_DATASET_ENDPOINT_FAIL_FAST_DEFAULT = false;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_LAZY_START_PRODUCER_CONF = "camel.sink.endpoint.lazyStartProducer";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_DATASET_ENDPOINT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_REPORT_GROUP_CONF = "camel.sink.endpoint.reportGroup";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_REPORT_GROUP_DOC = "A number that is used to turn on throughput logging based on groups of the size.";
    private static final Integer CAMEL_SINK_DATASET_ENDPOINT_REPORT_GROUP_DEFAULT = null;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_RESULT_MINIMUM_WAIT_TIME_CONF = "camel.sink.endpoint.resultMinimumWaitTime";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_RESULT_MINIMUM_WAIT_TIME_DOC = "Sets the minimum expected amount of time (in millis) the assertIsSatisfied() will wait on a latch until it is satisfied";
    private static final Long CAMEL_SINK_DATASET_ENDPOINT_RESULT_MINIMUM_WAIT_TIME_DEFAULT = 0L;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_RESULT_WAIT_TIME_CONF = "camel.sink.endpoint.resultWaitTime";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_RESULT_WAIT_TIME_DOC = "Sets the maximum amount of time (in millis) the assertIsSatisfied() will wait on a latch until it is satisfied";
    private static final Long CAMEL_SINK_DATASET_ENDPOINT_RESULT_WAIT_TIME_DEFAULT = 0L;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_RETAIN_FIRST_CONF = "camel.sink.endpoint.retainFirst";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_RETAIN_FIRST_DOC = "Specifies to only retain the first n'th number of received Exchanges. This is used when testing with big data, to reduce memory consumption by not storing copies of every Exchange this mock endpoint receives. Important: When using this limitation, then the getReceivedCounter() will still return the actual number of received Exchanges. For example if we have received 5000 Exchanges, and have configured to only retain the first 10 Exchanges, then the getReceivedCounter() will still return 5000 but there is only the first 10 Exchanges in the getExchanges() and getReceivedExchanges() methods. When using this method, then some of the other expectation methods is not supported, for example the expectedBodiesReceived(Object...) sets a expectation on the first number of bodies received. You can configure both setRetainFirst(int) and setRetainLast(int) methods, to limit both the first and last received.";
    private static final Integer CAMEL_SINK_DATASET_ENDPOINT_RETAIN_FIRST_DEFAULT = -1;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_RETAIN_LAST_CONF = "camel.sink.endpoint.retainLast";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_RETAIN_LAST_DOC = "Specifies to only retain the last n'th number of received Exchanges. This is used when testing with big data, to reduce memory consumption by not storing copies of every Exchange this mock endpoint receives. Important: When using this limitation, then the getReceivedCounter() will still return the actual number of received Exchanges. For example if we have received 5000 Exchanges, and have configured to only retain the last 20 Exchanges, then the getReceivedCounter() will still return 5000 but there is only the last 20 Exchanges in the getExchanges() and getReceivedExchanges() methods. When using this method, then some of the other expectation methods is not supported, for example the expectedBodiesReceived(Object...) sets a expectation on the first number of bodies received. You can configure both setRetainFirst(int) and setRetainLast(int) methods, to limit both the first and last received.";
    private static final Integer CAMEL_SINK_DATASET_ENDPOINT_RETAIN_LAST_DEFAULT = -1;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_SLEEP_FOR_EMPTY_TEST_CONF = "camel.sink.endpoint.sleepForEmptyTest";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_SLEEP_FOR_EMPTY_TEST_DOC = "Allows a sleep to be specified to wait to check that this endpoint really is empty when expectedMessageCount(int) is called with zero";
    private static final Long CAMEL_SINK_DATASET_ENDPOINT_SLEEP_FOR_EMPTY_TEST_DEFAULT = 0L;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_COPY_ON_EXCHANGE_CONF = "camel.sink.endpoint.copyOnExchange";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_COPY_ON_EXCHANGE_DOC = "Sets whether to make a deep copy of the incoming Exchange when received at this mock endpoint. Is by default true.";
    private static final Boolean CAMEL_SINK_DATASET_ENDPOINT_COPY_ON_EXCHANGE_DEFAULT = true;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_BASIC_PROPERTY_BINDING_CONF = "camel.sink.endpoint.basicPropertyBinding";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_BASIC_PROPERTY_BINDING_DOC = "Whether the endpoint should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_DATASET_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT = false;
    public static final String CAMEL_SINK_DATASET_ENDPOINT_SYNCHRONOUS_CONF = "camel.sink.endpoint.synchronous";
    private static final String CAMEL_SINK_DATASET_ENDPOINT_SYNCHRONOUS_DOC = "Sets whether synchronous processing should be strictly used, or Camel is allowed to use asynchronous processing (if supported).";
    private static final Boolean CAMEL_SINK_DATASET_ENDPOINT_SYNCHRONOUS_DEFAULT = false;
    public static final String CAMEL_SINK_DATASET_COMPONENT_LAZY_START_PRODUCER_CONF = "camel.component.dataset.lazyStartProducer";
    private static final String CAMEL_SINK_DATASET_COMPONENT_LAZY_START_PRODUCER_DOC = "Whether the producer should be started lazy (on the first message). By starting lazy you can use this to allow CamelContext and routes to startup in situations where a producer may otherwise fail during starting and cause the route to fail being started. By deferring this startup to be lazy then the startup failure can be handled during routing messages via Camel's routing error handlers. Beware that when the first message is processed then creating and starting the producer may take a little time and prolong the total processing time of the processing.";
    private static final Boolean CAMEL_SINK_DATASET_COMPONENT_LAZY_START_PRODUCER_DEFAULT = false;
    public static final String CAMEL_SINK_DATASET_COMPONENT_BASIC_PROPERTY_BINDING_CONF = "camel.component.dataset.basicPropertyBinding";
    private static final String CAMEL_SINK_DATASET_COMPONENT_BASIC_PROPERTY_BINDING_DOC = "Whether the component should use basic property binding (Camel 2.x) or the newer property binding with additional capabilities";
    private static final Boolean CAMEL_SINK_DATASET_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT = false;

    public CamelDatasetSinkConnectorConfig(
            ConfigDef config,
            Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public CamelDatasetSinkConnectorConfig(Map<String, String> parsedConfig) {
        this(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        ConfigDef conf = new ConfigDef(CamelSinkConnectorConfig.conf());
        conf.define(CAMEL_SINK_DATASET_PATH_NAME_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DATASET_PATH_NAME_DEFAULT, ConfigDef.Importance.HIGH, CAMEL_SINK_DATASET_PATH_NAME_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_DATA_SET_INDEX_CONF, ConfigDef.Type.STRING, CAMEL_SINK_DATASET_ENDPOINT_DATA_SET_INDEX_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_DATA_SET_INDEX_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_ASSERT_PERIOD_CONF, ConfigDef.Type.LONG, CAMEL_SINK_DATASET_ENDPOINT_ASSERT_PERIOD_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_ASSERT_PERIOD_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_CONSUME_DELAY_CONF, ConfigDef.Type.LONG, CAMEL_SINK_DATASET_ENDPOINT_CONSUME_DELAY_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_CONSUME_DELAY_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_EXPECTED_COUNT_CONF, ConfigDef.Type.INT, CAMEL_SINK_DATASET_ENDPOINT_EXPECTED_COUNT_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_EXPECTED_COUNT_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_FAIL_FAST_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DATASET_ENDPOINT_FAIL_FAST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_FAIL_FAST_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DATASET_ENDPOINT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_REPORT_GROUP_CONF, ConfigDef.Type.INT, CAMEL_SINK_DATASET_ENDPOINT_REPORT_GROUP_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_REPORT_GROUP_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_RESULT_MINIMUM_WAIT_TIME_CONF, ConfigDef.Type.LONG, CAMEL_SINK_DATASET_ENDPOINT_RESULT_MINIMUM_WAIT_TIME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_RESULT_MINIMUM_WAIT_TIME_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_RESULT_WAIT_TIME_CONF, ConfigDef.Type.LONG, CAMEL_SINK_DATASET_ENDPOINT_RESULT_WAIT_TIME_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_RESULT_WAIT_TIME_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_RETAIN_FIRST_CONF, ConfigDef.Type.INT, CAMEL_SINK_DATASET_ENDPOINT_RETAIN_FIRST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_RETAIN_FIRST_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_RETAIN_LAST_CONF, ConfigDef.Type.INT, CAMEL_SINK_DATASET_ENDPOINT_RETAIN_LAST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_RETAIN_LAST_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_SLEEP_FOR_EMPTY_TEST_CONF, ConfigDef.Type.LONG, CAMEL_SINK_DATASET_ENDPOINT_SLEEP_FOR_EMPTY_TEST_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_SLEEP_FOR_EMPTY_TEST_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_COPY_ON_EXCHANGE_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DATASET_ENDPOINT_COPY_ON_EXCHANGE_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_COPY_ON_EXCHANGE_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DATASET_ENDPOINT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_BASIC_PROPERTY_BINDING_DOC);
        conf.define(CAMEL_SINK_DATASET_ENDPOINT_SYNCHRONOUS_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DATASET_ENDPOINT_SYNCHRONOUS_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_ENDPOINT_SYNCHRONOUS_DOC);
        conf.define(CAMEL_SINK_DATASET_COMPONENT_LAZY_START_PRODUCER_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DATASET_COMPONENT_LAZY_START_PRODUCER_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_COMPONENT_LAZY_START_PRODUCER_DOC);
        conf.define(CAMEL_SINK_DATASET_COMPONENT_BASIC_PROPERTY_BINDING_CONF, ConfigDef.Type.BOOLEAN, CAMEL_SINK_DATASET_COMPONENT_BASIC_PROPERTY_BINDING_DEFAULT, ConfigDef.Importance.MEDIUM, CAMEL_SINK_DATASET_COMPONENT_BASIC_PROPERTY_BINDING_DOC);
        return conf;
    }
}