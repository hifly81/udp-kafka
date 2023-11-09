package org.hifly.udp.kafka.multicast;

public class Application {

    public static void main (String [] args) throws Exception {
        int servers = 5;
        for (int i = 0; i < servers; i++) {
            new MulticastServer("230.0.0.0", 4446).start();
        }
    }

}