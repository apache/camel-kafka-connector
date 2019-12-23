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

package org.apache.camel.kafkaconnector.clients.jms;

import java.util.Properties;
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
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A basic multi-protocol JMS client
 */
public class JMSClient {
    private static final Logger LOG = LoggerFactory.getLogger(JMSClient.class);

    private final String url;
    private Connection connection;
    private Session session;

    private final Function<String, ? extends ConnectionFactory> connectionFactory;
    private final Function<String, ? extends Queue> destinationFactory;

    public JMSClient(Function<String, ? extends ConnectionFactory> connectionFactory,
                     Function<String, ? extends Queue> destinationFactory,
                     String url) {
        this.connectionFactory = connectionFactory;
        this.destinationFactory = destinationFactory;
        this.url = url;
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
            final ConnectionFactory factory = connectionFactory.apply(url);

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
        return destinationFactory.apply(destinationName);
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

    public static JMSClient createClient(String url) {
        String jmsInstanceType = System.getProperty("jms-service.instance.type");

        if (jmsInstanceType == null || jmsInstanceType.equals("local-dispatch-router-container")) {
            return new JMSClient(org.apache.qpid.jms.JmsConnectionFactory::new,
                    org.apache.qpid.jms.JmsQueue::new, url);
        }

        if (jmsInstanceType.equals("local-artemis-container")) {
            return new JMSClient(
                    org.apache.activemq.ActiveMQConnectionFactory::new,
                    org.apache.activemq.command.ActiveMQQueue::new,
                    url);
        }

        LOG.error("Invalid JMS instance type: {}. Must be one of 'local-artemis-container' or 'local-dispatch-router-container",
                jmsInstanceType);
        throw new UnsupportedOperationException("Invalid JMS instance type:");
    }

    public static Properties getConnectionProperties(String url) {
        Properties properties = new Properties();

        String jmsInstanceType = System.getProperty("jms-service.instance.type");

        if (jmsInstanceType == null || jmsInstanceType.equals("local-dispatch-router-container")) {
            properties.put("camel.component.sjms2.connection-factory", "#class:org.apache.qpid.jms.JmsConnectionFactory");
            properties.put("camel.component.sjms2.connection-factory.remoteURI", url);

            return properties;
        }

        if (jmsInstanceType.equals("local-artemis-container")) {
            properties.put("camel.component.sjms2.connection-factory", "#class:org.apache.activemq.ActiveMQConnectionFactory");
            properties.put("camel.component.sjms2.connection-factory.brokerURL", url);

            return properties;
        }

        LOG.error("Invalid JMS instance type: {}. Must be one of 'local-artemis-container' or 'local-dispatch-router-container",
                jmsInstanceType);
        throw new UnsupportedOperationException("Invalid JMS instance type:");
    }

}
