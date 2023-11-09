package org.hifly.udp.kafka.multicast;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Properties;

public class MulticastServer extends Thread {

    protected MulticastSocket socket;
    protected byte[] buf = new byte[2048];
    protected InetAddress group;
    private final KafkaProducer<String, String> kafkaProducer;

    private static Properties createProducerConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    public MulticastServer(String address, int port) throws IOException {
        this.kafkaProducer = new KafkaProducer<>(createProducerConfig());
        socket = new MulticastSocket(port);
        socket.setReuseAddress(true);
        group = InetAddress.getByName(address);
        socket.joinGroup(group);
    }

    public void run() {
        try {
            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String msg = new String(buf, 0, packet.getLength());
                kafkaProducer.send(new ProducerRecord<>("telemetry", msg));
                // Reset the length of the packet before reusing it.
                packet.setLength(buf.length);
                if (msg.equals("end")) {
                    break;
                }
            }
            socket.leaveGroup(group);
            socket.close();
            kafkaProducer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}