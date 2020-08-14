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

package org.apache.camel.kafkaconnector.salesforce.source;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.kafkaconnector.common.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.common.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.common.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.common.utils.TestUtils;
import org.apache.camel.kafkaconnector.salesforce.clients.SalesforceCliContainer;
import org.apache.camel.kafkaconnector.salesforce.clients.SfdxCommand;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.apache.camel.kafkaconnector.salesforce.clients.SalesforceCliContainer.verifyCommand;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/* This test is disabled by default because requires setup on Salesforce end.

Creating API keys:
https://help.salesforce.com/articleView?id=connected_app_create_api_integration.htm

You need to set the following system properties to run this test:
-Dit.test.salesforce.enable=true to enable the test
-Dit.test.salesforce.client.id=<client ID>
-Dit.test.salesforce.client.secret=<client secret>
-Dit.test.salesforce.password=<user password>
-Dit.test.salesforce.username=<user name>
-Dit.test.salesforce.sfdx.path=/path/to/sfdx

The it.test.salesforce.sfdx.path property should point to the directory containing the sfdx
CLI client configuration. This can be generated using the following steps:

1. Run the Salesforce CLI container:
docker run --rm --name salesforce-cli -it -v /path/to/sfdx:/root/.sfdx salesforce/salesforcedx

2. Within the container, use the following command to login:
sfdx force:auth:device:login -s -d -i <client ID>

3. Provide the client secret when request and execute the steps requested by the CLI.

4. Verify that you are logged in correctly using the following command
sfdx force:auth:list

It should present an output like:

#### authenticated orgs
ALIAS  USERNAME              ORG ID              INSTANCE URL                 OAUTH METHOD
─────  ────────────────────  ──────────────────  ───────────────────────────  ────────────
       angusyoung@gmail.com  SOME NUMERIC ID     https://eu31.salesforce.com  web


Note: after leaving the container you might need to adjust the permissions of the directory
containing the sfdx configuration files (/path/to/sfdx).
*/
@Testcontainers
@EnabledIfSystemProperty(named = "it.test.salesforce.enable", matches = "true")
public class CamelSourceSalesforceITCase extends AbstractKafkaTest  {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSourceSalesforceITCase.class);

    @Container
    public final SalesforceCliContainer container = new SalesforceCliContainer();

    private final String clientId = System.getProperty("it.test.salesforce.client.id");
    private final String clientSecret = System.getProperty("it.test.salesforce.client.secret");
    private final String password = System.getProperty("it.test.salesforce.password");
    private final String userName = System.getProperty("it.test.salesforce.username");

    private volatile boolean received;
    private String account;

    @Override
    protected String[] getConnectorsInTest() {
        return new String[] {"camel-salesforce-kafka-connector"};
    }

    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
        received = false;

        account = "TestAccount" + TestUtils.randomWithRange(1, 100);

        SfdxCommand sfdxCommand = SfdxCommand.forceDataRecordCreate()
                .withArgument("--sobjecttype", "Account")
                .withArgument("--values", String.format("Name=%s", account));

        LOG.debug("Creating the test account");
        ExecResult result = container.execCommand(sfdxCommand);
        if (!verifyCommand(sfdxCommand, result)) {
            fail("Unable to create test account on Salesforce");
        }
    }

    @AfterEach
    public void tearDown() throws IOException, InterruptedException {
        SfdxCommand sfdxCommand = SfdxCommand.forceDataRecordDelete()
                .withArgument("--sobjecttype", "Account")
                .withArgument("--where", String.format("Name=%s", account));

        LOG.debug("Deleting the test account");
        ExecResult result = container.execCommand(sfdxCommand);
        if (!verifyCommand(sfdxCommand, result)) {
            fail("Unable to delete the test account on Salesforce");
        }
        account = null;
    }

    private <T> boolean checkRecord(ConsumerRecord<String, T> record) {
        LOG.debug("Received: {}", record.value());
        received  = true;

        return false;
    }

    public void runBasicTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

        LOG.debug("Creating the consumer ...");
        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());
        kafkaClient.consume(TestUtils.getDefaultTestTopic(this.getClass()), this::checkRecord);
        LOG.debug("Created the consumer ...");

        assertTrue(received, "Didn't receive any messages");
    }


    private boolean updateTestAccount() {
        final int limit = 50;
        int count = 0;


        while (!received && count < limit) {
            LOG.debug("Updating the account to desc {}", count);

            try {
                SfdxCommand sfdxCommand = SfdxCommand.forceDataRecordUpdate()
                        .withArgument("--sobjecttype", "Account")
                        .withArgument("--where", String.format("Name=%s", account))
                        .withArgument("--values", String.format("Description=desc%d", count));

                LOG.debug("Updating the test account");
                ExecResult result = container.execCommand(sfdxCommand);
                if (!verifyCommand(sfdxCommand, result)) {
                    fail("Unable to delete the test account on Salesforce");
                }

                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (IOException e) {
                LOG.error("I/O exception while updating the account: {}", e.getMessage(), e);

                return false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOG.error("Interrupted while updating the account: {}", e.getMessage(), e);

                return false;
            }

            count++;
        }

        if (count >= limit) {
            return false;
        }

        return true;
    }


    @Test
    @Timeout(180)
    public void testBasicConsume() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory factory = CamelSalesforcePropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withUserName(userName)
                .withPassword(password)
                .withClientId(clientId)
                .withClientSecret(clientSecret)
                .withNotifyForFields("ALL")
                .withUpdateTopic(true)
                .withRawPayload(true)
                .withPackages("org.apache.camel.salesforce.dto")
                .withSObjectClass("org.apache.camel.salesforce.dto.Account")
                .withSObjectQuery("SELECT Id, Name FROM Account")
                .withTopicName("CamelKafkaConnectorTopic");

        Executors.newCachedThreadPool().submit(this::updateTestAccount);

        runBasicTest(factory);
    }


    @Test
    @Timeout(180)
    public void testBasicConsumeUsingUrl() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory factory = CamelSalesforcePropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withUserName(userName)
                .withPassword(password)
                .withClientId(clientId)
                .withClientSecret(clientSecret)
                .withUrl("CamelKafkaConnectorTopic")
                    .append("notifyForFields", "ALL")
                    .append("updateTopic", "true")
                    .append("rawPayload", "true")
                    .append("sObjectClass", "org.apache.camel.salesforce.dto.Account")
                    .append("sObjectQuery", "SELECT Id, Name FROM Account")
                    .buildUrl();

        Executors.newCachedThreadPool().submit(this::updateTestAccount);

        runBasicTest(factory);
    }


    /*
     For this test to work, Change Data Capture need to be enabled on the setup. For lightnining, as of now, this is
     Setup -> Integrations -> Change Data Capture
     */
    @Test
    @Timeout(180)
    public void testBasicCDC() throws ExecutionException, InterruptedException {
        /*
         * NOTE: this test requires SalesForce API >= than 37.0. Camel defaults to
         * API version 34.0.
         *
         * The reason is that on older versions of this API did not return the list
         * of supported extensions during the hand-shake (ie.:
         * ext={replay=true, payload.format=true}). This behavior causes the rcvMeta
         * handler on the CometDReplayExtension class in the salesforce component to
         * consider the replay extension as "not supported".
         *
         * Subsequently, when using the /meta/subscribe channel to subscribe to
         * account change events on /data/AccountChangeEvent, the replay ID is not
         * provided on the request message - and it is a required parameter. This
         * leads to a situation where the Salesforce API server returns a plain
         * HTTP error 500 without much details.
         */
        ConnectorPropertyFactory factory = CamelSalesforcePropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withUserName(userName)
                .withPassword(password)
                .withClientId(clientId)
                .withClientSecret(clientSecret)
                .withRawPayload(true)
                .withReplayId(-2)
                .withApiVersion("37.0")
                .withTopicName("/data/AccountChangeEvent");

        runBasicTest(factory);
    }


    @Test
    @Timeout(180)
    public void testBasicCDCUsingUrl() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory factory = CamelSalesforcePropertyFactory.basic()
                .withKafkaTopic(TestUtils.getDefaultTestTopic(this.getClass()))
                .withUserName(userName)
                .withPassword(password)
                .withClientId(clientId)
                .withClientSecret(clientSecret)
                .withApiVersion("37.0")
                .withUrl("data/AccountChangeEvent")
                    .append("replayId", "-2")
                    .append("rawPayload", "true")
                    .buildUrl();

        runBasicTest(factory);
    }
}
