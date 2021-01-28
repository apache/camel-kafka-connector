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

package org.apache.camel.kafkaconnector.aws.v2.s3.common;

import org.apache.camel.component.aws2.s3.AWS2S3Configuration;
import org.apache.camel.test.infra.aws2.clients.AWSSDKClientUtils;
import software.amazon.awssdk.services.s3.S3Client;

public class TestS3Configuration extends AWS2S3Configuration {
    private S3Client s3Client;

    private S3Client buildClient() {
        return AWSSDKClientUtils.newS3Client();
    }

    @Override
    public S3Client getAmazonS3Client() {
        if (s3Client == null) {
            s3Client = buildClient();
        }

        return s3Client;
    }
}
