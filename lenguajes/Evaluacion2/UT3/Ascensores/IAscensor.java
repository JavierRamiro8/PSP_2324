package Ascensores;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class IAscensor implements Runnable {
    private int id;
    private String ip;
    private int puerto;

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket()) {
            while (true) {
                String mensaje = "COD_ASCENSOR: " + id + " Piso: 0 Direccion: 0\n";

                byte[] mensajeBytes = mensaje.getBytes();

                InetAddress direccionDestino = InetAddress.getByName(ip);
                DatagramPacket packet = new DatagramPacket(mensajeBytes, mensajeBytes.length, direccionDestino, puerto);

                socket.send(packet);

                long tiempoEspera = 100;
                Thread.sleep(tiempoEspera);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public IAscensor(int id, String ip, int puerto) {
        this.id = id;
        this.ip = ip;
        this.puerto = puerto;
    }

    synchronized void subir() {
    }

    synchronized void bajar() {
    }

    synchronized void getPlanta() {

    }

    synchronized void toProtocolo() {

    }
}
