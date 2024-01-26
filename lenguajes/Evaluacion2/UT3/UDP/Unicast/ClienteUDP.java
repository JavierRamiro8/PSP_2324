package UDP.Unicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP {
    public static void main(String[] args) {
        try {

            DatagramSocket ds = new DatagramSocket();
            String ip = args[0];
            int puerto = Integer.valueOf(args[1]);
            byte buffer[] = "Hola mundo\n".getBytes();
            // ds.setBroadcast(true);
            DatagramPacket p = new DatagramPacket(
                    buffer,
                    buffer.length,
                    InetAddress.getByName(ip),
                    puerto);

            ds.send(p);
            ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}