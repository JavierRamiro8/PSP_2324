package TCP.EjerciciosMultiThread.Ejercicio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int MAXRAND = 100;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(1234)) {
            while (true) {
                final Socket socket = server.accept();
                new Thread(() -> {
                    try (DataInputStream in = new DataInputStream(socket.getInputStream());
                         DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
                        String cadenaRecibida = in.readUTF();
                        if (!cadenaRecibida.equalsIgnoreCase("salir")) {
                            int numeroAleatorio = (int) (Math.random() * MAXRAND); // * NO %
                            out.writeUTF(String.valueOf(numeroAleatorio));
                        } else {
                            out.writeUTF("Salida");
                            out.close();
                            socket.close();//INCLUYELOOOOOOO
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

