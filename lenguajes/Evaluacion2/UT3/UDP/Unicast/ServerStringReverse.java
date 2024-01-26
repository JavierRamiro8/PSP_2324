package UDP.Unicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerStringReverse {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: javaServerStringReverse <ip> <puerto>");
            return;
        }
        String ip = args[0];
        int puerto = Integer.valueOf(args[1]);
        try {
            try (DatagramSocket ds = new DatagramSocket(puerto, InetAddress.getByName(ip))) {
                byte[] buffer = new byte[MAX_LENGTH];

                DatagramPacket p = new DatagramPacket(
                        buffer,
                        MAX_LENGTH);

                ds.receive(p);
                System.out.println(p.getData());
                String mensajeRecibido = new String(p.getData(), 0, p.getLength());
                System.out.println("Mensaje recibido: " + mensajeRecibido);
                String mensajeRevertido = new StringBuilder(mensajeRecibido).reverse().toString();
                System.out.println("Mensaje Revertido: " + mensajeRevertido);
                byte[] mensajeVueltaRevertido = mensajeRevertido.getBytes();
                DatagramPacket PaqueteVuelta = new DatagramPacket(mensajeVueltaRevertido, mensajeVueltaRevertido.length,
                        p.getAddress(), p.getPort());
                ds.send(PaqueteVuelta);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}