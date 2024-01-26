package UDP.BroadCast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class EditorSend {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Ingrese una palabra: ");
        String cadena = sc.nextLine();
        String ip = "255.255.255.255";
        int puerto = 2033;
        DatagramSocket ds = new DatagramSocket();
        enviarNumero(ds, ip, puerto, cadena);
        ds.close();
        }
    }

    private static void enviarNumero(DatagramSocket ds, String ip, int puerto, String cadena) throws IOException {
        byte[] mensajeBytes = cadena.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(
                mensajeBytes, mensajeBytes.length, InetAddress.getByName(ip), puerto);
        ds.send(sendPacket);
    }
}
