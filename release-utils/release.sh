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
SOURCE=${BASH_SOURCE[0]}
while [ -L "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  DIR=$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )
  SOURCE=$(readlink "$SOURCE")
  [[ $SOURCE != /* ]] && SOURCE=$DIR/$SOURCE # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
DIR=$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )

prefix=camel-kafka-connector-

read -r -p "Enter release version: " releaseV
export RELEASE_VERSION=$releaseV
releaseVbranch="${releaseV%.*}.x"

read -r -p "Enter next version: " nextV
export NEXT_VERSION=$nextV

defaultReleaseBranch=$prefix$releaseVbranch
read -r -p "Enter release branch [$defaultReleaseBranch]: " releaseBranch
export RELEASE_BRANCH=${releaseBranch:-$defaultReleaseBranch}

defaultReleaseTag=$prefix$releaseV
read -r -p "Enter release tag: [$defaultReleaseTag]" releaseTag
export RELEASE_TAG=${releaseTag:-$defaultReleaseTag}

read -r -p "Enter apache username: " user
export APACHE_USER=$user

export APACHE_PASS=`$DIR/scripts/askpass_stars.sh Enter apache password: `

defaultGpgProfile=gpg
read -r -p "Enter the maven gpg profile: [$defaultGpgProfile]" gpgProfile
export GPG_PROFILE=${gpgProfile:-$defaultGpgProfile}

defaultCheckoutReleaseBranch=Y
while true; do
  read -r -p "Do you want to automatically checkout -b into $RELEASE_BRANCH branch?: [Y/n]" checkoutReleaseBranch
  checkoutReleaseBranch=${checkoutReleaseBranch:-$defaultCheckoutReleaseBranch}
  case $checkoutReleaseBranch in
      [Yy]* )
          export CHECKOUT_RELEASE_TAG=Y
          break
          ;;
      [Nn]* )
          export CHECKOUT_RELEASE_TAG=N
          break
          ;;
      * )
          echo "Invalid input. Please enter y or n"
          ;;
  esac
done

if [ "$CHECKOUT_RELEASE_TAG" == Y ]; then
    git checkout -b "$RELEASE_BRANCH"
fi
./mvnw -Prelease -P"$GPG_PROFILE" -DreleaseVersion="$RELEASE_VERSION" -DdevelopmentVersion="$NEXT_VERSION" -Dtag="$RELEASE_TAG" -Dusername="$APACHE_USER" -Dpassword="$APACHE_PASS" release:prepare && \
git checkout "$RELEASE_TAG" && git add ./*.json && git commit -m"[after release perform chore]: regen catalog descriptors with new version" && git tag -f "$RELEASE_TAG" && git push -f upstream "$RELEASE_TAG" && git checkout "$RELEASE_BRANCH" && \
./mvnw -Prelease -Pgpg -Dusername="$APACHE_USER" -Dpassword="$APACHE_PASS" release:perform
