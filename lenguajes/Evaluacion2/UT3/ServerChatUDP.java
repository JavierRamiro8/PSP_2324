import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ServerChatUDP {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java ServerChatUDP <ip> <puerto>");
            return;
        }

        String ip = args[0];
        int puerto = Integer.valueOf(args[1]);

        try (DatagramSocket dsServer = new DatagramSocket(puerto, InetAddress.getByName(ip))) {
            byte[] buffer = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(buffer, MAX_LENGTH);
                dsServer.receive(receivePacket);

                // Obtener la longitud del mensaje desde los primeros 4 bytes
                int messageLength = bytesToInt(receivePacket.getData(), 0);

                String receivedData = new String(receivePacket.getData(), 4, messageLength);
                System.out.println("Cliente con la ip " + receivePacket.getAddress() + " Con puerto: "
                        + receivePacket.getPort() + " con el mensaje " + receivedData);

                Scanner sc = new Scanner(System.in);
                byte[] mensaje = sc.nextLine().getBytes();

                // Crear un nuevo array de bytes que contenga la longitud del mensaje
                byte[] data = new byte[mensaje.length + 4];
                System.arraycopy(intToBytes(mensaje.length), 0, data, 0, 4);
                System.arraycopy(mensaje, 0, data, 4, mensaje.length);

                // Utilizar la longitud del mensaje original para el paquete de envío
                DatagramPacket sendPacket = new DatagramPacket(
                        data,
                        data.length,
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

    private static byte[] intToBytes(int value) {
        return new byte[] {
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value
        };
    }

    private static int bytesToInt(byte[] bytes, int offset) {
        return ((bytes[offset] & 0xFF) << 24) |
                ((bytes[offset + 1] & 0xFF) << 16) |
                ((bytes[offset + 2] & 0xFF) << 8) |
                (bytes[offset + 3] & 0xFF);
    }
}
