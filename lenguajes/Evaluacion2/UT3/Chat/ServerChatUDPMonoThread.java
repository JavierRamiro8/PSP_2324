package Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ServerChatUDPMonoThread {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java ServerChatUDP <puerto>");
            return;
        }

        String ip = "127.0.0.1";
        int puerto = Integer.parseInt(args[0]);

        try (DatagramSocket dsServer = new DatagramSocket(puerto, InetAddress.getByName(ip));
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                // Recibir mensaje del cliente
                DatagramPacket receivePacket = recibirMensaje(dsServer);
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Cliente: " + receivedData);

                // Enviar respuesta al cliente
                enviarMensaje(dsServer, receivePacket.getAddress(), receivePacket.getPort(), sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DatagramPacket recibirMensaje(DatagramSocket ds) throws IOException {
        byte[] buffer = new byte[MAX_LENGTH];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        ds.receive(receivePacket);
        return receivePacket;
    }

    private static void enviarMensaje(DatagramSocket ds, InetAddress address, int puerto, String mensaje) throws IOException {
        byte[] mensajeBytes = mensaje.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(
                mensajeBytes, mensajeBytes.length, address, puerto);
        ds.send(sendPacket);
    }
}
