#!/bin/bash

sh kafka-demo/stop.sh

echo "Starting docker containers..."
docker-compose up -d

echo "wait 10 seconds..."
sleep 10

mvn clean compile

echo "Starting UDP Server on 230.0.0.0 and port 4446..."
nohup mvn exec:java -Dexec.mainClass="org.hifly.udp.kafka.multicast.Application" >/dev/null 2>&1 &