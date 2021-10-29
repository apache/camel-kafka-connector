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

package org.apache.camel.kafkaconnector.google.pubsub.clients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.SubscriptionAdminSettings;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminSettings;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.Subscription;
import com.google.pubsub.v1.TopicName;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GooglePubEasy {
    private static final Logger LOG = LoggerFactory.getLogger(GooglePubEasy.class);
    private final List<String> receivedMessages = new ArrayList<>();

    private final String serviceAddress;
    private final String project;


    private final ManagedChannel channel;
    private final FixedTransportChannelProvider channelProvider;

    private ProjectSubscriptionName projectSubscriptionName;
    private Subscriber subscriber;

    public GooglePubEasy(String serviceAddress, String project) {
        this.serviceAddress = serviceAddress;
        this.project = project;

        channel = ManagedChannelBuilder
                .forTarget(String.format(serviceAddress))
                .usePlaintext()
                .build();

        channelProvider =
                FixedTransportChannelProvider.create(GrpcTransportChannel.create(channel));
    }

    public void createTopic(String topicName) throws IOException, InterruptedException {
        doCreateTopic(topicName);
    }

    public void deleteTopic(String topicName) throws IOException, InterruptedException {
        doDeleteTopic(topicName);
    }

    public void createSubscription(String subscriptionName, String topicName) throws IOException {
        TopicName googleTopic = TopicName.of(project, topicName);

        projectSubscriptionName = ProjectSubscriptionName.of(project, subscriptionName);

        SubscriptionAdminSettings adminSettings = SubscriptionAdminSettings
                .newBuilder()
                .setCredentialsProvider(NoCredentialsProvider.create())
                .setTransportChannelProvider(channelProvider)
                .build();

        try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create(adminSettings)) {
            Subscription subscription = subscriptionAdminClient.createSubscription(
                    projectSubscriptionName, googleTopic, PushConfig.getDefaultInstance(), 10);
        }
    }

    public void deleteSubscription(String subscriptionName) throws IOException {
        projectSubscriptionName = ProjectSubscriptionName.of(project, subscriptionName);

        SubscriptionAdminSettings adminSettings = SubscriptionAdminSettings
                .newBuilder()
                .setCredentialsProvider(NoCredentialsProvider.create())
                .setTransportChannelProvider(channelProvider)
                .build();

        try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create(adminSettings)) {
            subscriptionAdminClient.deleteSubscription(projectSubscriptionName);
        }
    }

    private void doCreateTopic(String topicName) throws IOException, InterruptedException {
        TopicName googleTopic = TopicName.of(project, topicName);

        TopicAdminSettings topicAdminSettings = TopicAdminSettings
                .newBuilder()
                .setCredentialsProvider(NoCredentialsProvider.create())
                .setTransportChannelProvider(channelProvider)
                .build();

        try (TopicAdminClient client = TopicAdminClient.create(topicAdminSettings)) {
            LOG.info("Creating topic {} (original {})", googleTopic.toString(), googleTopic.getTopic());

            client.createTopic(googleTopic);

            if (client.awaitTermination(10, TimeUnit.SECONDS)) {
                client.shutdownNow();
            }
        }
    }

    private void doDeleteTopic(String topicName) throws IOException, InterruptedException {
        TopicName googleTopic = TopicName.of(project, topicName);

        TopicAdminSettings topicAdminSettings = TopicAdminSettings
                .newBuilder()
                .setCredentialsProvider(NoCredentialsProvider.create())
                .setTransportChannelProvider(channelProvider)
                .build();

        try (TopicAdminClient client = TopicAdminClient.create(topicAdminSettings)) {
            LOG.info("Deleting topic {} (original {})", googleTopic.toString(), googleTopic.getTopic());

            client.deleteTopic(googleTopic);

            if (client.awaitTermination(10, TimeUnit.SECONDS)) {
                client.shutdownNow();
            }
        }
    }

    public void receive() {
        try {
            MessageReceiver receiver = (pubsubMessage, ackReplyConsumer) -> {
                String data = pubsubMessage.getData().toString();
                LOG.info("Received: {}", data);
                receivedMessages.add(data);

                if (receivedMessages.size() >= 10) {
                    subscriber.stopAsync();
                }

                ackReplyConsumer.ack();
            };

            subscriber = Subscriber
                    .newBuilder(projectSubscriptionName, receiver)
                    .setCredentialsProvider(NoCredentialsProvider.create())
                    .setChannelProvider(channelProvider)
                    .build();


            LOG.info("Adding listener ...");
            subscriber.addListener(
                    new Subscriber.Listener() {
                        @Override
                        public void failed(Subscriber.State from, Throwable failure) {
                            LOG.error(failure.getMessage(), failure);
                        }
                    },
                    MoreExecutors.directExecutor());

            LOG.info("Starting async ...");
            subscriber.startAsync().awaitRunning();
            LOG.info("Waiting for messages ...");
            subscriber.awaitTerminated(25, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            subscriber.stopAsync();
        } finally {
            if (subscriber != null) {
                subscriber.stopAsync();
            }
        }
    }

    public void shutdown() {
        if (channel != null) {
            channel.shutdown();
        }
    }

    public List<String> getReceivedMessages() {
        return Collections.unmodifiableList(receivedMessages);
    }
}
