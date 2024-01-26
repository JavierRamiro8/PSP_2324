package UDP.Unicast.NumeroPrimo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class NumeroPrimoReceive {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) throws IOException {
        String ip = "127.0.0.1";
        int puerto = 2033;
        DatagramSocket dsServer = new DatagramSocket(puerto, InetAddress.getByName(ip));

        while (true) {
            // Recibir mensaje del cliente
            DatagramPacket receivePacket = recibirNumero(dsServer);
            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Convierte la cadena recibida en un entero
            int numeroRecibido = Integer.parseInt(receivedData);

            // Verifica si el número es primo y muestra el resultado
            String esPrimo = isPrimo(numeroRecibido);
            System.out.print("Número recibido: " + numeroRecibido+" ");
            System.out.println(esPrimo);
        }
    }

    private static DatagramPacket recibirNumero(DatagramSocket ds) throws IOException {
        byte[] buffer = new byte[MAX_LENGTH];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        ds.receive(receivePacket);
        return receivePacket;
    }

    private static String isPrimo(int numero) {
        if (numero <= 1) {
            return "No es Primo";
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return "no Es Primo";
            }
        }
        return "Si es primo";
    }
}

