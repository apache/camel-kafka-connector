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

package org.apache.camel.kafkaconnector.http.sink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpRequestHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.protocol.HttpContext;


class HTTPTestValidationHandler implements HttpRequestHandler {
    private final List<String> replies = new ArrayList<>();

    private final Lock lock = new ReentrantLock();
    private final Condition receivedExpectedMessages = lock.newCondition();
    private final int expected;

    HTTPTestValidationHandler(int expected) {
        this.expected = expected;
    }

    public Future<List<String>> getReplies() throws InterruptedException {
        lock.lock();
        try {
            receivedExpectedMessages.await(10, TimeUnit.SECONDS);

            List<String> ret = new ArrayList<>(replies);
            replies.clear();
            return CompletableFuture.supplyAsync(() -> ret);
        } finally {
            lock.unlock();
        }


    }

    @Override
    public void handle(ClassicHttpRequest classicHttpRequest, ClassicHttpResponse classicHttpResponse, HttpContext httpContext) throws HttpException, IOException {
        lock.lock();
        try {
            HttpEntity entity = classicHttpRequest.getEntity();
            String content = EntityUtils.toString(entity);

            replies.add(content);
            if (replies.size() == expected) {
                receivedExpectedMessages.signal();
            }

            classicHttpResponse.setCode(HttpStatus.SC_OK);
        } finally {
            lock.unlock();
        }
    }
}
