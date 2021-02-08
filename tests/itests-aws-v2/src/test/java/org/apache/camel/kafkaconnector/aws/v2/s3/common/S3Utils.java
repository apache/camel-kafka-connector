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

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

public final class S3Utils {
    private static final Logger LOG = LoggerFactory.getLogger(S3Utils.class);

    private S3Utils() {

    }

    public static List<S3Object> listObjects(S3Client s3Client, String bucketName) {
        try {
            ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .build();

            ListObjectsV2Response objectListing = s3Client.listObjectsV2(listObjectsRequest);

            return objectListing.contents();
        } catch (Exception e) {
            LOG.debug("Error listing: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Delete an S3 bucket using the provided client. Coming from AWS documentation:
     * https://docs.aws.amazon.com/AmazonS3/latest/dev/Versioning.html
     *
     * AWS SDK v1 doc for reference:
     * https://docs.aws.amazon.com/AmazonS3/latest/dev/delete-or-empty-bucket.html#delete-bucket-sdk-java
     * @param s3Client the AmazonS3 client instance used to delete the bucket
     * @param bucketName a String containing the bucket name
     */
    public static void deleteBucket(S3Client s3Client, String bucketName) {
        // Delete all objects from the bucket. This is sufficient
        // for non versioned buckets. For versioned buckets, when you attempt to delete objects, Amazon S3 inserts
        // delete markers for all objects, but doesn't delete the object versions.
        // To delete objects from versioned buckets, delete all of the object versions before deleting
        // the bucket (see below for an example).
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        ListObjectsV2Response objectListing;
        do {
            objectListing = s3Client.listObjectsV2(listObjectsRequest);

            for (S3Object s3Object : objectListing.contents()) {
                s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(s3Object.key()).build());
            }

            listObjectsRequest = ListObjectsV2Request.builder().bucket(bucketName)
                    .continuationToken(objectListing.nextContinuationToken())
                    .build();
        } while (objectListing.isTruncated());

        s3Client.deleteBucket(DeleteBucketRequest.builder().bucket(bucketName).build());
    }


    public static void createBucket(S3Client s3Client, String bucketName) {
        CreateBucketRequest request = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();

        s3Client.createBucket(request);
    }

    public static File[] getFilesToSend(File dir) throws IOException {
        File[] files = dir.listFiles(f -> f.getName().endsWith(".test"));
        if (files == null) {
            throw new IOException("Either I/O error or the path used is not a directory");
        }

        if (files.length == 0) {
            throw new IOException("Not enough files to run the test");
        }

        return files;
    }

    public static void sendFilesFromPath(S3Client s3Client, String bucketName, File[] files) {
        LOG.debug("Putting S3 objects");

        for (File file : files) {
            LOG.debug("Trying to read file {}", file.getName());


            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(file.getName())
                    .build();

            s3Client.putObject(putObjectRequest, file.toPath());
        }
    }
}
