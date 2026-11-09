#!/usr/bin/env bash

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

set -euo pipefail

script_dir=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
repo_root=$(cd "$script_dir/../.." && pwd)
test_root=$(mktemp -d "${TMPDIR:-/tmp}/camel-kafka-release-script.XXXXXX")
trap 'rm -rf "$test_root"' EXIT

fixture="$test_root/project"
mock_bin="$test_root/bin"
log_file="$test_root/commands.log"
mkdir -p "$fixture/release-utils" "$mock_bin"
cp "$repo_root/release-utils/release.sh" "$fixture/release-utils/release.sh"

cat > "$fixture/mvnw" <<'EOF'
#!/usr/bin/env bash
{
  printf 'mvnw'
  for argument in "$@"; do printf '\t%s' "$argument"; done
  printf '\n'
} >> "$LOG_FILE"
EOF

cat > "$mock_bin/git" <<'EOF'
#!/usr/bin/env bash
{
  printf 'git'
  for argument in "$@"; do printf '\t%s' "$argument"; done
  printf '\n'
} >> "$LOG_FILE"
EOF
chmod +x "$fixture/mvnw" "$mock_bin/git"

(
  cd "$fixture"
  printf '4.22.0\n4.22.1-SNAPSHOT\n\n\n\n\n' |
    PATH="$mock_bin:$PATH" LOG_FILE="$log_file" ./release-utils/release.sh >/dev/null 2>&1
)

if grep -Eq '(^|[[:space:]])-D(username|password)=' "$log_file"; then
  printf 'Maven was given SCM credentials on its command line.\n' >&2
  exit 1
fi

if ! grep -Fxq $'mvnw\t-Prelease\t-Pgpg\t-DreleaseVersion=4.22.0\t-DdevelopmentVersion=4.22.1-SNAPSHOT\t-Dtag=camel-kafka-connector-4.22.0\t-DpushChanges=false\trelease:prepare' "$log_file"; then
  printf 'release:prepare did not disable automatic pushes.\n' >&2
  exit 1
fi

if grep -Eq '(^|[[:space:]])(-f|--force|--force-with-lease)([[:space:]]|$)' "$log_file"; then
  printf 'The release script attempted a forced Git operation.\n' >&2
  exit 1
fi

push_count=$(grep -Ec '^git[[:space:]]+push([[:space:]]|$)' "$log_file" || true)
if [[ "$push_count" -ne 2 ]] || \
   ! grep -Fxq $'git\tpush\torigin\tcamel-kafka-connector-4.22.x' "$log_file" || \
   ! grep -Fxq $'git\tpush\torigin\tcamel-kafka-connector-4.22.0' "$log_file"; then
  printf 'Expected one non-forced push of the release branch and one of the final tag.\n' >&2
  exit 1
fi

prepare_line=$(grep -Fn $'mvnw\t-Prelease\t-Pgpg\t-DreleaseVersion=4.22.0\t-DdevelopmentVersion=4.22.1-SNAPSHOT\t-Dtag=camel-kafka-connector-4.22.0\t-DpushChanges=false\trelease:prepare' "$log_file" | cut -d: -f1)
branch_push_line=$(grep -Fn $'git\tpush\torigin\tcamel-kafka-connector-4.22.x' "$log_file" | cut -d: -f1)
tag_push_line=$(grep -Fn $'git\tpush\torigin\tcamel-kafka-connector-4.22.0' "$log_file" | cut -d: -f1)
perform_line=$(grep -Fn $'mvnw\t-Prelease\t-Pgpg\trelease:perform' "$log_file" | cut -d: -f1)
if ! (( prepare_line < branch_push_line && branch_push_line < tag_push_line && tag_push_line < perform_line )); then
  printf 'The release branch and final tag must be pushed before release:perform.\n' >&2
  exit 1
fi

printf 'Release script regression test passed.\n'
