package TCP.Ejercicios.Ejercicio2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                Socket socket = server.accept();
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                String[] comandos = { "ls", "/var" };
                ProcessBuilder processBuilder = new ProcessBuilder(comandos);
                Process proceso = processBuilder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                    out.writeUTF(linea);
                }
                out.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
