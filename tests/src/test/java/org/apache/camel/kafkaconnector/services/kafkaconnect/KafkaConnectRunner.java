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

package org.apache.camel.kafkaconnector.services.kafkaconnect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.camel.kafkaconnector.ConnectorPropertyFactory;
import org.apache.kafka.common.utils.Time;
import org.apache.kafka.common.utils.Utils;
import org.apache.kafka.connect.connector.policy.AllConnectorClientConfigOverridePolicy;
import org.apache.kafka.connect.connector.policy.ConnectorClientConfigOverridePolicy;
import org.apache.kafka.connect.runtime.Connect;
import org.apache.kafka.connect.runtime.ConnectorConfig;
import org.apache.kafka.connect.runtime.Herder;
import org.apache.kafka.connect.runtime.Worker;
import org.apache.kafka.connect.runtime.WorkerInfo;
import org.apache.kafka.connect.runtime.isolation.Plugins;
import org.apache.kafka.connect.runtime.rest.RestServer;
import org.apache.kafka.connect.runtime.rest.entities.ConnectorInfo;
import org.apache.kafka.connect.runtime.standalone.StandaloneConfig;
import org.apache.kafka.connect.runtime.standalone.StandaloneHerder;
import org.apache.kafka.connect.storage.FileOffsetBackingStore;
import org.apache.kafka.connect.util.ConnectUtils;
import org.apache.kafka.connect.util.FutureCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * An embeddable Kafka Connect runtime for usage during the tests. It is equivalent
 * to the Kafka connect standalone CLI
 */
class KafkaConnectRunner {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConnectRunner.class);

    private final String bootstrapServer;
    private final KafkaConnectPropertyFactory kafkaConnectPropertyFactory;
    private final List<ConnectorPropertyFactory> connectorPropertyFactories = new ArrayList<>();

    private Connect connect;
    private Herder herder;


    /**
     * Contains the initialization state. This just abstracts internal details from the
     * runner, so those details don't leak in other parts of the test code
     */
    public class ConnectorInitState {
        private Map<String, String> configs;
        private boolean created;
        private Throwable error;

        public ConnectorInitState(Map<String, String> configs, boolean created, Throwable error) {
            this.configs = configs;
            this.created = created;
            this.error = error;
        }

        public Map<String, String> getConfigs() {
            return configs;
        }

        public boolean isCreated() {
            return created;
        }

        public Throwable getError() {
            return error;
        }
    }

    /**
     * Constructs the properties using the given bootstrap server
     * @param bootstrapServer the address of the server in the format
     *                       PLAINTEXT://${address}:${port}
     */
    public KafkaConnectRunner(String bootstrapServer) {
        this.bootstrapServer = bootstrapServer;
        this.kafkaConnectPropertyFactory = new DefaultKafkaConnectPropertyFactory(bootstrapServer);
    }


    /**
     * here does not seem to be a public interface for embedding a Kafka connect runtime,
     * therefore, this code is modeled from the behavior taken from
     * https://github.com/apache/kafka/blob/2.1/connect/runtime/src/main/java/org/apache/kafka/connect/cli/ConnectStandalone.java
     * and performs the initialization in a roughly similar manner.
     *
     */
    private void init() {
        LOG.info("Started worked initialization");

        Time time = Time.SYSTEM;

        // Initializes the system runtime information and logs some of the information
        WorkerInfo initInfo = new WorkerInfo();
        initInfo.logAll();

        Properties props = kafkaConnectPropertyFactory.getProperties();

        Map<String, String> standAloneProperties = Utils.propsToStringMap(props);

        // Not needed, but we need this one to initialize the worker
        Plugins plugins = new Plugins(standAloneProperties);

        StandaloneConfig config = new StandaloneConfig(standAloneProperties);
        String kafkaClusterId = ConnectUtils.lookupKafkaClusterId(config);
        AllConnectorClientConfigOverridePolicy allConnectorClientConfigOverridePolicy = new AllConnectorClientConfigOverridePolicy();

        RestServer rest = new RestServer(config);
        rest.initializeServer();

        /*
         According to the Kafka source code "... Worker runs a (dynamic) set of tasks
         in a set of threads, doing the work of actually moving data to/from Kafka ..."
         */
        Worker worker = new Worker(bootstrapServer, time, plugins, config, new FileOffsetBackingStore(), allConnectorClientConfigOverridePolicy);

        /*
        From Kafka source code: " ... The herder interface tracks and manages workers
        and connectors ..."
         */
        herder = new StandaloneHerder(worker, kafkaClusterId, allConnectorClientConfigOverridePolicy);
        connect = new Connect(herder, rest);
        LOG.info("Finished initializing the worker");
    }

    /**
     * Offers the list of connector properties producers to be configured prior to running
     * the embeddable connect runtime
     * @return A list object that can be modified to include or remove connector property
     * producers
     */
    public List<ConnectorPropertyFactory> getConnectorPropertyProducers() {
        return connectorPropertyFactories;
    }


    public void initializeConnector(ConnectorPropertyFactory connectorPropertyFactory,
                                    Consumer<ConnectorInitState> callback) throws ExecutionException, InterruptedException {
        Properties connectorProps = connectorPropertyFactory.getProperties();

        FutureCallback<Herder.Created<ConnectorInfo>> cb = new FutureCallback<>((error, info) ->
                callback.accept(new ConnectorInitState(info.result().config(), info.created(), error)));

        herder.putConnectorConfig(
                connectorProps.getProperty(ConnectorConfig.NAME_CONFIG),
                Utils.propsToStringMap(connectorProps), false, cb);

        cb.get();
    }

    public <T> void initializeConnector(ConnectorPropertyFactory connectorPropertyFactory,
                                        BiConsumer<ConnectorInitState, T> callback, T payload) throws ExecutionException, InterruptedException {
        Properties connectorProps = connectorPropertyFactory.getProperties();

        FutureCallback<Herder.Created<ConnectorInfo>> cb = new FutureCallback<>((error, info) ->
                callback.accept(new ConnectorInitState(info.result().config(), info.created(), error), payload));

        herder.putConnectorConfig(
                connectorProps.getProperty(ConnectorConfig.NAME_CONFIG),
                Utils.propsToStringMap(connectorProps), false, cb);

        cb.get();
    }


    /**
     * Run the embeddable Kafka connect runtime
     * @return true if successfully started the runtime or false otherwise
     */
    public boolean run(CountDownLatch latch) {
        try {
            init();

            LOG.info("Starting the connect interface");
            connect.start();
            LOG.info("Started the connect interface");

        } catch (Throwable t) {
            LOG.error("Container init or start has failed due to: ", t);
        } finally {
            latch.countDown();
        }

        connect.awaitStop();
        return true;
    }


    /**
     * Stops the embeddable Kafka connect runtime
     */
    public void stop() {
        if (connect != null) {
            connect.stop();
        } else {
            LOG.warn("Trying to stop an uninitialized Kafka Connect Runner");
        }
    }
}
