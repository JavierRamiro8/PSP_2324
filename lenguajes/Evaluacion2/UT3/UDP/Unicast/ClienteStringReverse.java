package UDP.Unicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteStringReverse {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java ClienteStringReverse <ip> <puerto>");
            return;
        }

        try (DatagramSocket ds = new DatagramSocket()) {
            String ip = args[0];
            int puerto = Integer.parseInt(args[1]);

            Scanner in = new Scanner(System.in);
            System.out.println("Introduce la cadena para revertirla:");
            String mensaje = in.nextLine();

            byte[] bufferEnvio = mensaje.getBytes();
            DatagramPacket paqueteEnvio = new DatagramPacket(
                    bufferEnvio,
                    bufferEnvio.length,
                    InetAddress.getByName(ip),
                    puerto);
            ds.send(paqueteEnvio);

            // Preparar el buffer y el paquete para recibir la respuesta
            byte[] bufferRecepcion = new byte[MAX_LENGTH];
            DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

            // Recibir la respuesta
            ds.receive(paqueteRecepcion);

            String mensajeRecibido = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
            System.out.println("Mensaje revertido desde el servidor: " + mensajeRecibido);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
