package Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerChatUDPMultiThread {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java ServerChatUDP <puerto>");
            return;
        }

        int puerto = Integer.parseInt(args[0]);

        try (DatagramSocket dsServer = new DatagramSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);

            while (true) {
                DatagramPacket receivePacket = recibirMensaje(dsServer);
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Cliente: " + receivedData);

                // Crear un hilo para manejar la comunicación con el cliente
                Thread clientThread = new Thread(new ClientHandler(dsServer, clientAddress, clientPort, receivedData));
                clientThread.start();
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
}

class ClientHandler implements Runnable {
    private DatagramSocket socket;
    private InetAddress clientAddress;
    private int clientPort;
    private String receivedData;

    public ClientHandler(DatagramSocket socket, InetAddress clientAddress, int clientPort, String receivedData) {
        this.socket = socket;
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
        this.receivedData = receivedData;
    }

    @Override
    public void run() {
        try {
            // Realizar el procesamiento específico para este cliente
            String response = "Recibido: " + receivedData;
            enviarMensaje(socket, clientAddress, clientPort, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarMensaje(DatagramSocket ds, InetAddress address, int puerto, String mensaje) throws IOException {
        byte[] mensajeBytes = mensaje.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(mensajeBytes, mensajeBytes.length, address, puerto);
        ds.send(sendPacket);
    }
}

