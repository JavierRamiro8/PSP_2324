import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteChatUDP {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java ClienteChatUDP <ip> <puerto>");
            return;
        }

        String ip = args[0];
        int puerto = Integer.parseInt(args[1]);
        try (DatagramSocket ds = new DatagramSocket()) {
            Scanner sc = new Scanner(System.in);

            while (true) {
                // Obtener el mensaje del usuario y enviarlo
                byte[] mensaje = sc.nextLine().getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        mensaje, mensaje.length, InetAddress.getByName(ip), puerto);
                ds.send(sendPacket);

                // Recibir la respuesta del servidor
                byte[] receiveBuffer = new byte[MAX_LENGTH];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                ds.receive(receivePacket);

                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Servidor: " + receivedData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

