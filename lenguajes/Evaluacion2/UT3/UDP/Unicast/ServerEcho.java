package UDP.Unicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerEcho {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java ServerEcho <ip> <puerto>");
            return;
        }

        String ip = args[0];
        int puerto = Integer.valueOf(args[1]);

        try (DatagramSocket dsServer = new DatagramSocket(puerto, InetAddress.getByName(ip))) {
            byte[] buffer = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(buffer, MAX_LENGTH);
                dsServer.receive(receivePacket);

                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Cliente con la ip " + receivePacket.getAddress() + " Con puerto: "
                        + receivePacket.getPort() + " con el mensaje " + receivedData);

                DatagramPacket sendPacket = new DatagramPacket(
                        receivePacket.getData(),
                        receivePacket.getLength(),
                        receivePacket.getAddress(),
                        receivePacket.getPort());
                dsServer.send(sendPacket);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
