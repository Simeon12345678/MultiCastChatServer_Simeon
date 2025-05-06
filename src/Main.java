import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // default port number we are going to use
        int portNumber = 5000;
        if (args.length >= 1) {
            portNumber = Integer.parseInt(args[0]);
        }

        // create multiCast server
        MulticastSocket serverMulticastSocket = new MulticastSocket(portNumber);
        System.out.println("MulticastSocket is created at port " + portNumber);

        // determine ip address of a host, given the host name
        InetAddress group = InetAddress.getByName("255.4.5.6");
        // getByName returns ip address of given host
        serverMulticastSocket.joinGroup(group);
        System.out.println("JoinGroup method is called...");
        boolean infinite = true;

        // continually receive data and prints them
        while (infinite) {
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);
            serverMulticastSocket.receive(data);
            String msg = new String(data.getData()).trim();
            System.out.println("Message received from client = " + msg);
        }
        serverMulticastSocket.close();
    }
}