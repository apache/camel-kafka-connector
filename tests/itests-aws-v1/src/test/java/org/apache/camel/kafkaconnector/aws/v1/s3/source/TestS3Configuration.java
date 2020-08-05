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

package org.apache.camel.kafkaconnector.aws.v1.s3.source;

import com.amazonaws.services.s3.AmazonS3;
import org.apache.camel.component.aws.s3.S3Configuration;
import org.apache.camel.kafkaconnector.aws.v1.clients.AWSClientUtils;

public class TestS3Configuration extends S3Configuration {
    private AmazonS3 amazonS3;


    private AmazonS3 buildClient() {
        return AWSClientUtils.newS3Client();
    }

    @Override
    public AmazonS3 getAmazonS3Client() {
        if (amazonS3 == null) {
            amazonS3 = buildClient();
        }

        return amazonS3;
    }
}
