#!/bin/bash

echo "Stopping docker containers..."
docker-compose down --volumes

echo "Stop UDP Server..."
echo -n "end" | nc -4u -w0 230.0.0.0 4446