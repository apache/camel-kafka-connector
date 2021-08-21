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
package org.apache.camel.kafkaconnector.maven;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenRepository;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;
import org.apache.camel.kafkaconnector.maven.utils.MavenUtils;
import org.apache.camel.tooling.util.Strings;

import static com.soebes.itf.extension.assertj.MavenExecutionResultAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@MavenJupiterExtension
@MavenRepository
class GenerateCamelKafkaConnectorsMojoIT {

    @MavenTest
//    @MavenOption(MavenCLIOptions.DEBUG)
    public void test_generate(MavenExecutionResult result) throws IOException {
        assertThat(result).isSuccessful();
        assertThat(result)
            .out()
            .info()
            .anyMatch(s -> s.startsWith("Excluded Components that won't be used to generate a kafka connector: "))
            .anyMatch(s -> s.startsWith("Components found to be used to generate/update a kafka connector: ["))
            .anyMatch(s -> s.startsWith("Kamelets found to be used to generate/update a kafka connector: ["))
            .anyMatch(s -> s.startsWith("Creating camel kafka connector for"))
            .anyMatch(s -> s.startsWith("Creating camel kafka kamelet connector for"))
            .containsSequence(
                "Creating a new pom.xml for the connector from scratch",
                "Creating a new package.xml for the connector.")
            .anyMatch(s -> s.startsWith("Updated doc file:"));

        List<String> stdout = Files.readAllLines(result.getMavenLog().getStdout());
        List<String> generated = extractGenerated(stdout);
        List<String> excluded = extractExcluded(stdout);

        // verify component directories are really generated
        generated.stream()
            .map(MavenUtils::sanitizeMavenArtifactId)
            .map(m -> "camel-" + m + "-kafka-connector")
            .forEach(m -> assertThat(result).project().hasModule(m));

        // verify excluded components are not generated
        Set<String> files = Arrays.stream(Objects.requireNonNull(result.getMavenProjectResult().getBaseDir().listFiles()))
            .map(File::getName)
            .collect(Collectors.toCollection(HashSet::new));
        excluded.stream()
            .map(MavenUtils::sanitizeMavenArtifactId)
            .map(m -> "camel-" + m + "-kafka-connector")
            .forEach(m -> assertFalse(files.contains(m), "component should be excluded"));
    }

    private List<String> extractExcluded(List<String> stdout) {
        return stdout.stream()
            .filter(s -> s.startsWith("[INFO] Excluded Components that won't be used to generate a kafka connector: ["))
            .findFirst()
            .map(s -> Strings.between(s, "[INFO] Excluded Components that won't be used to generate a kafka connector: [", "]"))
            .map(s -> Arrays.asList(s.split(", ")))
            .orElse(Collections.emptyList());
    }

    private List<String> extractGenerated(List<String> stdout) {
        return stdout.stream()
            .filter(s -> s.startsWith("[INFO] Components found to be used to generate/update a kafka connector: ["))
            .findFirst()
            .map(s -> Strings.between(s, "[INFO] Components found to be used to generate/update a kafka connector: [", "]"))
            .map(s -> Arrays.asList(s.split(", ")))
            .orElse(Collections.emptyList());
    }
}
