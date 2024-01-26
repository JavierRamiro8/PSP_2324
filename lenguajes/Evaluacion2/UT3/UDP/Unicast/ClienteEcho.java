package UDP.Unicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteEcho {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: java ClienteEcho <ip> <puerto> <mensaje>");
            return;
        }

        DatagramSocket ds = null;

        try {
            String ip = args[0];
            int puerto = Integer.valueOf(args[1]);
            byte[] buffer = args[2].getBytes();

            ds = new DatagramSocket();

            DatagramPacket sendPacket = new DatagramPacket(
                    buffer,
                    buffer.length,
                    InetAddress.getByName(ip),
                    puerto);

            ds.send(sendPacket);
            byte[] receiveBuffer = new byte[MAX_LENGTH];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, MAX_LENGTH);

            ds.receive(receivePacket);

            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Servidor: " + receivedData);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null && !ds.isClosed()) {
                ds.close();
            }
        }
    }
}
