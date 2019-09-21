/**
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
package org.apache.camel.kafkaconnector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.header.Headers;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.Test;

public class CamelSourceTaskTest {

    @Test
    public void testSourcePolling() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "timer:kafkaconnector");
        props.put("camel.source.kafka.topic", "mytopic");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        Thread.sleep(2000L);
        List<SourceRecord> poll = camelSourceTask.poll();
        assertEquals(2, poll.size());
        assertEquals("mytopic", poll.get(0).topic());
        Headers headers = poll.get(0).headers();
        boolean containsHeader = false;
        for (Iterator iterator = headers.iterator(); iterator.hasNext();) {
            Header header = (Header)iterator.next();
            if (header.key().equalsIgnoreCase("CamelPropertyCamelTimerPeriod")) {
                containsHeader = true;
                break;
            }
        }
        assertTrue(containsHeader);

        camelSourceTask.stop();
    }

    @Test
    public void testSourcePollingTimeout() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "timer:kafkaconnector");
        props.put("camel.source.kafka.topic", "mytopic");
        props.put("camel.source.maxPollDuration", "1");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        Thread.sleep(3000L);
        List<SourceRecord> poll;
        int retries = 3;
        do {
            poll = camelSourceTask.poll();
            if (poll == null) {
                retries--;

                if (retries == 0) {
                    fail("Exhausted the maximum retries and no record was returned");
                }

                Thread.sleep(3000L);
            }
        } while (poll == null && retries > 0);

        assertEquals(1, poll.size());

        camelSourceTask.stop();
    }

    @Test
    public void testSourcePollingMaxRecordNumber() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "timer:kafkaconnector");
        props.put("camel.source.kafka.topic", "mytopic");
        props.put("camel.source.maxBatchPollSize", "1");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        Thread.sleep(2000L);
        List<SourceRecord> poll = camelSourceTask.poll();
        assertEquals(1, poll.size());

        camelSourceTask.stop();
    }

    @Test
    public void testSourcePollingConsumerOptions() throws InterruptedException {
        Map<String, String> props = new HashMap<>();
        props.put("camel.source.url", "timer:kafkaconnector");
        props.put("camel.source.kafka.topic", "mytopic");
        props.put("camel.source.pollingConsumerQueueSize", "10");
        props.put("camel.source.pollingConsumerBlockTimeout", "1000");
        props.put("camel.source.pollingConsumerBlockWhenFull", "false");

        CamelSourceTask camelSourceTask = new CamelSourceTask();
        camelSourceTask.start(props);

        assertEquals(2, camelSourceTask.getCms().getEndpoints().size());

        camelSourceTask.getCms().getEndpoints().stream()
                .filter( e -> e.getEndpointUri().startsWith("direct"))
                .forEach( e -> assertEquals("direct://end?pollingConsumerBlockTimeout=1000&pollingConsumerBlockWhenFull=false&pollingConsumerQueueSize=10", e.getEndpointUri()));

        camelSourceTask.stop();
    }
}
