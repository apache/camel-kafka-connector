#!/bin/bash

# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

prefix=camel-kafka-connector-

read -p "Enter release version: " releaseV
export RELEASE_VERSION=$releaseV
releaseVbranch="${releaseV%.*}.x"

read -p "Enter next version: " nextV
export NEXT_VERSION=$nextV

defaultReleaseBranch=$prefix$releaseVbranch
read -p "Enter release branch [$defaultReleaseBranch]: " releaseBranch
export RELEASE_BRANCH=${releaseBranch:-$defaultReleaseBranch}

defaultReleaseTag=$prefix$releaseV
read -p "Enter release tag: [$defaultReleaseTag]" releaseTag
export RELEASE_TAG=${releaseTag:-$defaultReleaseTag}

read -p "Enter apache username: " user
export APACHE_USER=$user

read -p "Enter apache password: " pass
export APACHE_PASS=$pass

git checkout -b $RELEASE_BRANCH && \
./mvnw -Prelease -Pgpg -DreleaseVersion=$RELEASE_VERSION -DdevelopmentVersion=$NEXT_VERSION -Dtag=$RELEASE_TAG -Dusername=$APACHE_USER -Dpassword=$APACHE_PASS release:prepare && \
git checkout $RELEASE_TAG && git add *.json && git commit -m"[after release perform chore]: regen catalog descriptors with new version" && git tag -f $RELEASE_TAG && git push -f upstream $RELEASE_TAG && git checkout $RELEASE_BRANCH && \
./mvnw -Prelease -Pgpg -Dusername=$APACHE_USER -Dpassword=$APACHE_PASS release:perform
