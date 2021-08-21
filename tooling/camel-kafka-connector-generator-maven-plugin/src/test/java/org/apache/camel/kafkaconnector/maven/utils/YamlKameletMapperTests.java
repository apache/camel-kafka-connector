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
package org.apache.camel.kafkaconnector.maven.utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.camel.kafkaconnector.maven.model.KameletModel;
import org.apache.camel.kafkaconnector.maven.model.KameletPropertyModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YamlKameletMapperTests {
    @Test
    public void parseKameletYamlTest() throws IOException {
        KameletModel km = YamlKameletMapper.parseKameletYaml(YamlKameletMapperTests.class.getResource("/kamelets/aws-s3-source.kamelet.yaml"));

        assertNotNull(km);
        assertEquals("aws-s3-source", km.getName());
        assertEquals("source", km.getType());
        assertEquals("Receive data from AWS S3.", km.getDescription());

        assertEquals(2, km.getDependencies().size());
        assertTrue(km.getDependencies().contains("camel:aws2-s3"));
        assertTrue(km.getDependencies().contains("camel:kamelet"));

        assertEquals(4, km.getRequiredProperties().size());
        assertTrue(km.getRequiredProperties().contains("bucketNameOrArn"));
        assertTrue(km.getRequiredProperties().contains("accessKey"));
        assertTrue(km.getRequiredProperties().contains("secretKey"));
        assertTrue(km.getRequiredProperties().contains("region"));

        assertEquals(7, km.getProperties().size());
        List<KameletPropertyModel> regionProperty = km.getProperties().stream().filter(kpm -> "region".equals(kpm.getName())).collect(Collectors.toList());
        assertEquals(1, regionProperty.size());
        assertEquals("AWS Region", regionProperty.get(0).getTitle());
        assertEquals("The AWS region to connect to", regionProperty.get(0).getDescription());
        assertEquals("string", regionProperty.get(0).getType());
        assertEquals("eu-west-1", regionProperty.get(0).getExample());

        List<KameletPropertyModel> secretKeyProperty = km.getProperties().stream().filter(kpm -> "secretKey".equals(kpm.getName())).collect(Collectors.toList());
        assertEquals(1, secretKeyProperty.size());
        assertEquals("Secret Key", secretKeyProperty.get(0).getTitle());
        assertEquals("The secret key obtained from AWS", secretKeyProperty.get(0).getDescription());
        assertEquals("string", secretKeyProperty.get(0).getType());
        assertEquals("password", secretKeyProperty.get(0).getFormat());
    }

    @Test
    public void parseKameletYamlNoPropetiesTest() throws IOException {
        KameletModel km = YamlKameletMapper.parseKameletYaml(YamlKameletMapperTests.class.getResource("/kamelets/noproperties.kamelet.yaml"));
        assertNotNull(km);
        assertTrue(km.getProperties().isEmpty());
        assertTrue(km.getRequiredProperties().isEmpty());
    }

    @Test
    public void parseKameletYamlNoDependenciesTest() throws IOException {
        KameletModel km = YamlKameletMapper.parseKameletYaml(YamlKameletMapperTests.class.getResource("/kamelets/nodependencies.kamelet.yaml"));
        assertNotNull(km);
        assertTrue(km.getDependencies().isEmpty());
    }
}
