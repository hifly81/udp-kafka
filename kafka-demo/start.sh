#!/bin/bash

if [ "$#" -ne 4 ]
then
  echo "Incorrect number of arguments. Use with 4 args <number of servers> <bind address> <bind port> <topic name>"
  exit 1
fi

sh kafka-demo/stop.sh

echo "Starting docker containers..."
docker-compose up -d

echo "wait 10 seconds..."
sleep 10

mvn clean compile

echo "Starting $1 UDP Servers listening on $2 and port $3 for kafka topic $4..."
nohup mvn exec:java -Dexec.mainClass="org.hifly.udp.kafka.multicast.Application" -Dexec.args=$1 -Dexec.args=$2 -Dexec.args=$3 -Dexec.args=$4 >/dev/null 2>&1 &