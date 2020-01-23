#!/usr/bin/env bash

CurrentDir=$(dirname $0)

MAX_NUM=1
MIN_NUM=0

n=1

while [ $n -ge $MIN_NUM ] && [ $n -le $MAX_NUM ]; do
  echo "第"$n"次执行"
  find $CurrentDir -name "build" | xargs rm -rf
  find $CurrentDir -name "out" | xargs rm -rf
  $CurrentDir/gradlew clean  build
  ((n++))
done
