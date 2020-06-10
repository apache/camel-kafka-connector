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

package org.apache.camel.kafkaconnector.sjms2.clients;

import java.util.function.Function;
import java.util.function.Predicate;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A basic multi-protocol JMS client
 */
public class JMSClient {
    private static final Logger LOG = LoggerFactory.getLogger(JMSClient.class);

    private Connection connection;
    private Session session;

    private ConnectionFactory factory;

    public JMSClient(Function<String, ? extends ConnectionFactory> connectionFactory,
                     String url) {
        factory = connectionFactory.apply(url);
    }

    public JMSClient(String className, String url) {
        Class<? extends ConnectionFactory> clazz;
        try {
            clazz = (Class<? extends ConnectionFactory>) Class.forName(className);

            factory = clazz.getConstructor(String.class).newInstance(url);
        } catch (Exception e) {
            LOG.error("Unable to create the JMS client classL {}", e.getMessage(), e);
            Assertions.fail(e);
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public static Throwable capturingClose(MessageProducer closeable) {
        LOG.debug("Closing the producer ");

        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable t) {
                LOG.warn("Error closing the producer: {}", t.getMessage(), t);
                return t;
            }
        }
        return null;
    }

    private static void capturingClose(Session closeable) {
        LOG.debug("Closing the session ");

        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable t) {
                LOG.warn("Error closing the session: {}", t.getMessage(), t);
            }
        }
    }

    private static void capturingClose(MessageConsumer closeable) {
        LOG.debug("Closing the consumer");

        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable t) {
                LOG.warn("Error closing the consumer: {}", t.getMessage(), t);
            }
        }
    }

    private static void capturingClose(Connection closeable) {
        LOG.debug("Closing the connection");

        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable t) {
                LOG.warn("Error closing the connection: {}", t.getMessage(), t);
            }
        }
    }


    public void start() throws Exception {
        LOG.debug("Starting the JMS client");

        try {
            LOG.debug("Creating the connection");
            connection = factory.createConnection();
            LOG.debug("Connection created successfully");

            LOG.debug("Creating the JMS session");
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            LOG.debug("JMS session created successfully");
        } catch (Throwable t) {
            LOG.trace("Something wrong happened while initializing the JMS client: {}", t.getMessage(), t);

            capturingClose(connection);
            throw t;
        }

        connection.start();
    }

    public void stop() {
        try {
            LOG.debug("Stopping the JMS session");
            capturingClose(session);

            LOG.debug("Stopping the JMS connection");
            capturingClose(connection);
        } finally {
            session = null;
            connection = null;
        }
    }

    private Destination createDestination(final String destinationName) {
        try {
            return session.createQueue(destinationName);
        } catch (JMSException e) {
            Assertions.fail(e.getMessage());

            // unreachable
            return null;
        }
    }


    /**
     * Receives data from a JMS queue or topic
     *
     * @param queue     the queue or topic to receive data from
     * @param predicate the predicate used to test each received message
     * @throws JMSException
     */
    public void receive(final String queue, Predicate<Message> predicate) throws JMSException {
        final long timeout = 3000;

        MessageConsumer consumer = null;

        try {
            consumer = session.createConsumer(createDestination(queue));

            while (true) {
                final Message message = consumer.receive(timeout);

                if (!predicate.test(message)) {
                    return;
                }
            }
        } finally {
            capturingClose(consumer);
        }
    }


    /**
     * Sends data to a JMS queue or topic
     *
     * @param queue the queue or topic to send data to
     * @param data  the (string) data to send
     * @throws JMSException
     */
    public void send(final String queue, final String data) throws JMSException {
        MessageProducer producer = null;

        try {
            producer = session.createProducer(createDestination(queue));

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            producer.setTimeToLive(0);

            Message message = session.createTextMessage(data);

            producer.send(message);
        } finally {
            capturingClose(producer);
        }
    }

    /**
     * Sends data to a JMS queue or topic
     *
     * @param queue the queue or topic to send data to
     * @param data  the (string) data to send
     * @throws JMSException
     */
    public void send(final String queue, int data) throws JMSException {
        MessageProducer producer = null;

        try {
            producer = session.createProducer(createDestination(queue));

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            producer.setTimeToLive(0);

            Message message = session.createObjectMessage(data);

            producer.send(message);
        } finally {
            capturingClose(producer);
        }
    }
}
