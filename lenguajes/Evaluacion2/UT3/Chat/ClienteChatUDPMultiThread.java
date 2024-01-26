package Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteChatUDPMultiThread {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java ClienteChatUDP <ip> <puerto>");
            return;
        }

        String ip = args[0];
        int puerto = Integer.parseInt(args[1]);

        try (DatagramSocket ds = new DatagramSocket();
             Scanner sc = new Scanner(System.in)) {

            Thread recibirThread = new Thread(() -> {
                try {
                    while (true) {
                        // Recibir la respuesta del servidor
                        String receivedData = recibirMensaje(ds);
                        System.out.println("Servidor: " + receivedData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            recibirThread.start();

            while (true) {
                // Obtener el mensaje del usuario y enviarlo
                enviarMensaje(ds, ip, puerto, sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarMensaje(DatagramSocket ds, String ip, int puerto, String mensaje) throws IOException {
        byte[] mensajeBytes = mensaje.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(
                mensajeBytes, mensajeBytes.length, InetAddress.getByName(ip), puerto);
        ds.send(sendPacket);
    }

    private static String recibirMensaje(DatagramSocket ds) throws IOException {
        byte[] receiveBuffer = new byte[MAX_LENGTH];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        ds.receive(receivePacket);
        return new String(receivePacket.getData(), 0, receivePacket.getLength());
    }
}

