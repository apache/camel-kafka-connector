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
package org.apache.camel.kafkaconnector.utils;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class StringJoinerAggregator implements AggregationStrategy {
    private String delimiter = ",";

    public String getDelimiter() {
        return delimiter;
    }

    public StringJoinerAggregator setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        // lets append the old body to the new body
        if (oldExchange == null) {
            return newExchange;
        }

        String body = oldExchange.getIn().getBody(String.class);
        if (body != null) {
            Message newIn = newExchange.getIn();
            String newBody = newIn.getBody(String.class);
            if (newBody != null) {
                body += delimiter + newBody;
            }

            newIn.setBody(body);
        }
        return newExchange;
    }
}
