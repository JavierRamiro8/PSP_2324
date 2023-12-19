import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteChatUDP {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        while (true) {
            if (args.length < 2) {
                System.out.println("Uso: java ClienteChatUDP <ip> <puerto>");
                return;
            }
            DatagramSocket ds = null;

            try {
                // Obtener la dirección IP y el puerto del servidor desde la línea de comandos
                String ip = args[0];
                int puerto = Integer.valueOf(args[1]);

                // Obtener el mensaje del usuario
                Scanner sc = new Scanner(System.in);
                byte[] mensaje = sc.nextLine().getBytes();

                // Crear un nuevo array de bytes que contenga la longitud del mensaje
                byte[] data = new byte[mensaje.length + 4];

                // Copiar la longitud del mensaje al inicio del array
                System.arraycopy(intToBytes(mensaje.length), 0, data, 0, 4);

                // Copiar el contenido del mensaje después de la longitud
                System.arraycopy(mensaje, 0, data, 4, mensaje.length);

                // Crear un socket Datagram
                ds = new DatagramSocket();

                // Crear un paquete DatagramPacket con los datos del mensaje y enviarlo al
                // servidor
                DatagramPacket sendPacket = new DatagramPacket(
                        data,
                        data.length, // Usar la longitud del mensaje
                        InetAddress.getByName(ip),
                        puerto);

                ds.send(sendPacket);

                // Preparar un buffer para recibir la respuesta del servidor
                byte[] receiveBuffer = new byte[MAX_LENGTH];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                // Recibir la respuesta del servidor
                ds.receive(receivePacket);

                // Obtener la longitud del mensaje desde los primeros 4 bytes
                int messageLength = bytesToInt(receivePacket.getData(), 0);

                // Construir la cadena a partir de los datos del paquete, omitiendo los primeros
                // 4 bytes que contienen la longitud
                String receivedData = new String(receivePacket.getData(), 4, messageLength);

                // Imprimir la respuesta del servidor
                System.out.println("Servidor: " + receivedData);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Cerrar el socket Datagram
                if (ds != null && !ds.isClosed()) {
                    ds.close();
                }
            }
        }
    }

    // Método para convertir un entero en un array de bytes (big-endian)
    private static byte[] intToBytes(int value) {
        return new byte[] {
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value
        };
    }

    // Método para convertir un array de bytes en un entero (big-endian)
    private static int bytesToInt(byte[] bytes, int offset) {
        return ((bytes[offset] & 0xFF) << 24) |
                ((bytes[offset + 1] & 0xFF) << 16) |
                ((bytes[offset + 2] & 0xFF) << 8) |
                (bytes[offset + 3] & 0xFF);
    }
}
