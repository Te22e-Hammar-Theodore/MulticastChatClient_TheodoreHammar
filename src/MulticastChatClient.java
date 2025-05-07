import java.net.*;
import java.io.*;

public class MulticastChatClient {
    public static void main(String args[]) throws Exception {

        // Default port number
        int portnumber = 5001; // Matchar serverns port
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        // Create a MulticastSocket
        MulticastSocket chatMulticastSocket = new MulticastSocket(portnumber);

        // Determine the IP address of the multicast group
        InetAddress group = InetAddress.getByName("225.4.5.6");

        // Join the multicast group
        chatMulticastSocket.joinGroup(group);

        // Prompt user for input
        String msg = "";
        System.out.println("Type a message for the server:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        msg = br.readLine();

        // Send the message to the multicast address
        DatagramPacket data = new DatagramPacket(
                msg.getBytes(),
                0,
                msg.length(),
                group,
                portnumber
        );
        chatMulticastSocket.send(data);

        // Close the socket
        chatMulticastSocket.close();
    }
}
