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

package org.apache.camel.kafkaconnector.sink.salesforce;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.camel.kafkaconnector.AbstractKafkaTest;
import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.kafka.KafkaClient;
import org.apache.camel.kafkaconnector.clients.salesforce.SalesforceCliContainer;
import org.apache.camel.kafkaconnector.clients.salesforce.SfdxCommand;
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

import static org.apache.camel.kafkaconnector.clients.salesforce.SalesforceCliContainer.verifyCommand;
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
public class CamelSinkSalesforceITCase extends AbstractKafkaTest {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSinkSalesforceITCase.class);

    @Container
    public final SalesforceCliContainer container = new SalesforceCliContainer();

    private final String clientId = System.getProperty("it.test.salesforce.client.id");
    private final String clientSecret = System.getProperty("it.test.salesforce.client.secret");
    private final String password = System.getProperty("it.test.salesforce.password");
    private final String userName = System.getProperty("it.test.salesforce.username");

    private String accountName;
    private boolean recordCreated;

    @BeforeEach
    public void setUp() {
        accountName = "TestSinkAccount" + TestCommon.randomWithRange(1, 100);
    }

    @AfterEach
    public void tearDown() throws IOException, InterruptedException {
        SfdxCommand sfdxCommand = SfdxCommand.forceDataRecordDelete()
                .withArgument("--sobjecttype", "Account")
                .withArgument("--where", String.format("Name=%s", accountName));

        LOG.debug("Deleting the test account {}", accountName);
        ExecResult result = container.execCommand(sfdxCommand);
        if (!verifyCommand(sfdxCommand, result)) {
            fail("Unable to delete the test account on Salesforce");
        }

        accountName = null;
    }


    private boolean waitForRecordCreation() {
        SfdxCommand sfdxCommand = SfdxCommand.forceDataRecordGet()
                .withArgument("--sobjecttype", "Account")
                .withArgument("--where", String.format("Name=%s", accountName));

        LOG.debug("Check if the test account {} was created on Salesforce", accountName);
        try {
            ExecResult result = container.execCommand(sfdxCommand);

            if (verifyCommand(sfdxCommand, result)) {
                recordCreated = true;
                return true;
            }

        } catch (IOException e) {
            LOG.warn("I/O exception while checking if the record was created: {}", e.getMessage(), e);

            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            LOG.warn("The thread was interrupted while waiting for the record creation");
            return false;
        }

        return false;
    }


    private void runTest(ConnectorPropertyFactory connectorPropertyFactory) throws ExecutionException, InterruptedException {
        connectorPropertyFactory.log();
        getKafkaConnectService().initializeConnectorBlocking(connectorPropertyFactory, 1);

        KafkaClient<String, String> kafkaClient = new KafkaClient<>(getKafkaService().getBootstrapServers());

        // Ideally we should use the DTOs, but they cause the source check to fail
        String data = String.format("{\"attributes\":{\"referenceId\":null,\"type\":\"Account\",\"url\":null},"
                + "\"Description\":\"%s\",\"Name\":\"%s\"}", "Created during sink test", accountName);

        LOG.info("Sending new account {}", data);

        kafkaClient.produce(TestCommon.getDefaultTestTopic(this.getClass()), data);
    }

    @Test
    @Timeout(180)
    public void testBasicProduce() throws ExecutionException, InterruptedException {
        ConnectorPropertyFactory factory = CamelSalesforcePropertyFactory.basic()
                .withKafkaTopic(TestCommon.getDefaultTestTopic(this.getClass()))
                .withUserName(userName)
                .withPassword(password)
                .withClientId(clientId)
                .withClientSecret(clientSecret)
                .withRawPayload(true)
                .withPackages("org.apache.camel.salesforce.dto")
                .withSObjectName("Account")
                .withOperationName("createSObject");

        runTest(factory);

        TestCommon.waitFor(this::waitForRecordCreation);
        assertTrue(recordCreated, "The record was not created");
    }
}
