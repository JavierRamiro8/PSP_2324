import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ServerChatUDP {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java ServerChatUDP <puerto>");
            return;
        }

        String ip = "127.0.0.1";
        int puerto = Integer.parseInt(args[0]);

        try (DatagramSocket dsServer = new DatagramSocket(puerto, InetAddress.getByName(ip))) {
            while (true) {
                // Recibir mensaje del cliente
                byte[] buffer = new byte[MAX_LENGTH];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                dsServer.receive(receivePacket);

                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Cliente: " + receivedData);

                // Enviar respuesta al cliente
                Scanner sc = new Scanner(System.in);
                byte[] mensaje = sc.nextLine().getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        mensaje, mensaje.length, receivePacket.getAddress(), receivePacket.getPort());
                dsServer.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
