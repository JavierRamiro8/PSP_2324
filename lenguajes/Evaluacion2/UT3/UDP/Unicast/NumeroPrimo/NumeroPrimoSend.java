package UDP.Unicast.NumeroPrimo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class NumeroPrimoSend {
     public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Ingrese un número: ");
        int numeroAEnviar = sc.nextInt();
        sc.nextLine(); // Consume la nueva línea pendiente
        String ip = "127.0.0.1";
        int puerto = 2033;
        DatagramSocket ds = new DatagramSocket();
        enviarNumero(ds, ip, puerto, numeroAEnviar);
        ds.close();
        }
    }

    private static void enviarNumero(DatagramSocket ds, String ip, int puerto, int numero) throws IOException {
        byte[] mensajeBytes = Integer.toString(numero).getBytes();
        DatagramPacket sendPacket = new DatagramPacket(
                mensajeBytes, mensajeBytes.length, InetAddress.getByName(ip), puerto);
        ds.send(sendPacket);
    }
}
