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
package org.apache.camel.kafkaconnector.rabbitmq.clients;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.MessageProperties;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A basic RabbitMQ client
 */
public class RabbitMQClient {
    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQClient.class);
    private static final String DEFAULT_EXCHANGE_TYPE = "direct";

    private Connection connection;
    private Channel channel;

    private ConnectionFactory factory;

    public RabbitMQClient(String uri) {
        factory = new ConnectionFactory();
        try {
            factory.setUri(uri);
        } catch (Exception e) {
            LOG.error("Unable to create the RabbitMQ client {}", e.getMessage(), e);
            Assertions.fail(e);
        }
    }

    private static void capturingClose(Closeable closeable, String closableDescription) {
        LOG.debug("Closing the " + closableDescription);

        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable t) {
                LOG.warn("Error closing the {}: {}", closableDescription, t.getMessage(), t);
            }
        }
    }

    private static void capturingClose(AutoCloseable closeable, String closableDescription) {
        LOG.debug("Closing the " + closableDescription);

        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable t) {
                LOG.warn("Error closing the {}: {}", closableDescription, t.getMessage(), t);
            }
        }
    }

    public void start() throws Exception {
        LOG.debug("Starting the RabbitMQ client");

        try {
            LOG.debug("Creating the connection");
            connection = factory.newConnection();
            LOG.debug("Connection created successfully");

            LOG.debug("Creating the Channel");
            channel = connection.createChannel();
            LOG.debug("Channel created successfully");
        } catch (Throwable t) {
            LOG.trace("Something wrong happened while initializing the RabbitMQ client: {}", t.getMessage(), t);

            capturingClose(connection, "connection");
            throw t;
        }
    }

    public void stop() {
        try {
            LOG.debug("Stopping the channel");
            capturingClose(channel, "channel");

            LOG.debug("Stopping the RabbitMQ connection");
            capturingClose(connection, "connection");
        } finally {
            channel = null;
            connection = null;
        }
    }

    public AMQP.Queue.DeclareOk createQueue(final String queueName) {
        try {
            start();
            return channel.queueDeclare(queueName, true, false, false, null);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());

            // unreachable
            return null;
        } finally {
            stop();
        }
    }

    public AMQP.Exchange.DeclareOk createExchange(final String exchangeName) {
        return createExchange(exchangeName, DEFAULT_EXCHANGE_TYPE);
    }

    public AMQP.Exchange.DeclareOk createExchange(final String exchangeName, final String exchangeType) {
        try {
            start();
            return channel.exchangeDeclare(exchangeName, exchangeType);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());

            // unreachable
            return null;
        } finally {
            stop();
        }
    }

    public AMQP.Queue.BindOk bindExchangeToQueue(final String exchangeName, final String queueName) {
        try {
            start();
            return channel.queueBind(exchangeName, exchangeName, "");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());

            // unreachable
            return null;
        } finally {
            stop();
        }
    }

    /**
     * Sends data to a RabbitMQ queue
     *
     * @param queue the queue to send data to
     * @param data  the (string) data to send
     * @throws IOException
     */
    public void send(final String queue, final String data) {
        try {
            start();
            channel.basicPublish("", queue, MessageProperties.PERSISTENT_TEXT_PLAIN, data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        } finally {
            stop();
        }
    }

    /**
     * Receives data from a JMS queue or topic
     *
     * @param queue     the queue or topic to receive data from
     * @param deliverCallback the callback used to test each received messages
     */
    public void receive(final String queue, DeliverCallback deliverCallback) throws Exception {
        channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });
    }
}
