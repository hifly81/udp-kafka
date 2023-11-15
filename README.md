# UDP to Kafka

Publish UDP packets to Kafka.

## How to run on local

## Start demo environment

```bash
kafka-demo/start.sh <number of servers> <bind address> <bind port> <topic name>
```

example:
```bash
kafka-demo/start.sh 5 230.0.0.0 4446 telemetry
```

- Start 5 UDP Multicast receivers listening on 230.0.0.0 and port 4446 publishing to kafka topic telemetry.
- Start kafka broker on port 9092

## Tests

Simple session test with _nc_ command:

```bash
echo -n "test2222" | nc -4u -w0 230.0.0.0 4446
## close the session
echo -n "end" | nc -4u -w0 230.0.0.0 4446
```

Simulate a stress test:

```bash
# send 1000 bytes 10 times
python udp_stress_client.py 1000 10
```

## Teardown

```bash
kafka-demo/stop.sh
```