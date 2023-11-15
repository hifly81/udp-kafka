# UDP to Kafka

Publish UDP packets to Kafka

## How to run on local

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