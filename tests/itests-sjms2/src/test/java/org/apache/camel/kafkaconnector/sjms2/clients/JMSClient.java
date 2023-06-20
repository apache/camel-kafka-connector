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

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.DeliveryMode;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;

import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.fail;

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
     * @param predicate the predicate used to test each received message
     * @throws JMSException
     */
    public void receive(MessageConsumer consumer, Predicate<Message> predicate, long timeout) throws JMSException {
        while (true) {
            final Message message = consumer.receive(timeout);

            if (!predicate.test(message)) {
                return;
            }
        }
    }


    /**
     * Receives data from a JMS queue or topic
     *
     * @param predicate the predicate used to test each received message
     * @throws JMSException
     */
    public void receive(MessageConsumer consumer, Predicate<Message> predicate) throws JMSException {
        receive(consumer, predicate, 3000);
    }

    public MessageConsumer createConsumer(String queue) throws JMSException {
        return session.createConsumer(createDestination(queue));
    }


    /**
     * Receives data from a JMS queue or topic
     *
     * @param queue     the queue or topic to receive data from
     * @param predicate the predicate used to test each received message
     * @throws JMSException
     */
    public void receive(final String queue, Predicate<Message> predicate) throws JMSException {
        MessageConsumer consumer = null;

        try {
            consumer = createConsumer(queue);

            receive(consumer, predicate);
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


    public static void produceMessages(JMSClient jmsProducer, String queue, int count, Function<Integer, String> supplier) {
        try {
            jmsProducer.start();
            for (int i = 0; i < count; i++) {
                jmsProducer.send(queue, supplier.apply(i));
            }
        } catch (JMSException e) {
            LOG.error("JMS exception trying to send messages to the queue: {}", e.getMessage(), e);
            fail(e.getMessage());
        } catch (Exception e) {
            LOG.error("Failed to send messages to the queue: {}", e.getMessage(), e);
            fail(e.getMessage());
        } finally {
            jmsProducer.stop();
        }
    }

    public static void produceMessages(JMSClient jmsProducer, String queue, int count, String baseText) {
        try {
            jmsProducer.start();
            for (int i = 0; i < count; i++) {
                jmsProducer.send(queue, baseText + " " + i);
            }
        } catch (JMSException e) {
            LOG.error("JMS exception trying to send messages to the queue: {}", e.getMessage(), e);
            fail(e.getMessage());
        } catch (Exception e) {
            LOG.error("Failed to send messages to the queue: {}", e.getMessage(), e);
            fail(e.getMessage());
        } finally {
            jmsProducer.stop();
        }
    }

    public static void produceMessages(JMSClient jmsProducer, String queue, int count) {
        try {
            jmsProducer.start();
            for (int i = 0; i < count; i++) {
                jmsProducer.send(queue, i);
            }
        } catch (JMSException e) {
            LOG.error("JMS exception trying to send messages to the queue: {}", e.getMessage(), e);
            fail(e.getMessage());
        } catch (Exception e) {
            LOG.error("Failed to send messages to the queue: {}", e.getMessage(), e);
            fail(e.getMessage());
        } finally {
            jmsProducer.stop();
        }
    }

    private static JMSClient newLocalClient(String endpoint) {
        String jmsClientType = System.getProperty("jms-service.transport.protocol");

        if (jmsClientType == null || jmsClientType.isEmpty() || jmsClientType.equals("qpid")) {
            return new JMSClient(org.apache.qpid.jms.JmsConnectionFactory::new, endpoint);
        }

        throw new UnsupportedOperationException("Invalid JMS transport protocol");
    }

    private static JMSClient newRemoteClient(String endpoint) {
        String tmpConnectionFactory = System.getProperty("camel.component.sjms2.connection-factory");
        if (tmpConnectionFactory == null) {
            throw new UnsupportedOperationException("JMS connection factory class must be provided");
        }

        String connectionFactory = tmpConnectionFactory.replace("#class:", "");


        String jmsClientType = System.getProperty("jms-service.transport.protocol");
        if (jmsClientType == null || jmsClientType.isEmpty() || jmsClientType.equals("qpid")) {
            return new JMSClient(connectionFactory, endpoint);
        }

        if (jmsClientType.equals("openwire")) {
            return new JMSClient(connectionFactory, endpoint);
        }

        throw new UnsupportedOperationException("Invalid JMS transport protocol");
    }

    public static JMSClient newClient(String endpoint) {
        String jmsInstanceType = System.getProperty("jms-service.instance.type");

        if (jmsInstanceType == null || !jmsInstanceType.equals("remote")) {
            return newLocalClient(endpoint);
        }

        return newRemoteClient(endpoint);
    }
}
