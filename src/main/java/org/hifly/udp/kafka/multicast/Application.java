package org.hifly.udp.kafka.multicast;

public class Application {

    public static void main (String [] args) throws Exception {
        int servers = 5;
        String address = "230.0.0.0";
        int port = 4446;
        String topic = "telemetry";

        if(args !=null && args.length == 4) {
            servers = args[0] != null && !args[0].equals("")? Integer.valueOf(args[0]): servers;
            address = args[1] != null && !args[1].equals("")? args[1]: address;
            port = args[2] != null && !args[2].equals("")? Integer.valueOf(args[2]): port;
            topic = args[3] != null && !args[3].equals("")? args[3]: topic;
        }

        for (int i = 0; i < servers; i++) {
            new MulticastServer(address, port, topic).start();
        }
    }

}