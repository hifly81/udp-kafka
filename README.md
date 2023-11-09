echo -n "test2222" | nc -4u -w0 230.0.0.0 4446

echo -n "end" | nc -4u -w0 230.0.0.0 4446

python udp_stress_client.py 1000 10