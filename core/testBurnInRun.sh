#!/bin/sh
# Run this script from the project root directory.
# It will run core module tests in burn in mode (i.e. untill they fail or this scritp is manually terminated)
for (( i = 1; ; i++ ))
do
  echo "Attempt $i"
  ./mvnw test -pl core -am
  exitcode=$?
  if [ $exitcode -ne 0 ]
  then
    echo "Error at attempt $i"
    exit
  fi
done

