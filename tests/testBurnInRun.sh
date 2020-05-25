#!/bin/sh
# Run this script from the project root directory.
# It will run core module tests in burn in mode (i.e. untill they fail or this scritp is manually terminated)

repeat=${1:-99}
for (( i = 1; i < $repeat ; i++ ))
do
  echo "Attempt $i of $repeat"
  ./mvnw -DskipIntegrationTests=false test -pl tests -am
  exitcode=$?
  if [ $exitcode -ne 0 ]
  then
    echo "Error at attempt $i"
    exit
  fi
done
