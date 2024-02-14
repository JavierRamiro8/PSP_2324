package Apache.Ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final static int NUMEROSERVER = 9875;
    private static final int RESOURCE_POSITION = 1;
    private static final String RUTA ="/var/www/html/";

    public static void main(String[] args) {
        while (true) {
            ServerSocket server;
            try {
                server = new ServerSocket(NUMEROSERVER);
            
            Socket connCliente = server.accept();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            connCliente.getInputStream()));
            String header = reader.readLine();
            System.out.println(header);
            // GET ________ HTTP/1.1
            String info = extraeInformacion(header);

            String html = generaPagina(info);

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            connCliente.getOutputStream()));
            if (html == null) {
                writer.write("HTTP/1.1 404 Not Found\n");
                writer.write("\n");
                writer.write("<h1>404 Not Found</h1>");
                writer.flush();
            } else {
                writer.write("HTTP/1.1 200 OK\n");
                writer.write("\n");
                writer.write(html);
                writer.flush();
            }
            reader.close();
            writer.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Error Icono");
        }
        }
    }

    private static String generaPagina(String info) throws IOException {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader lector = new BufferedReader(new FileReader(RUTA + info))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        }catch(FileNotFoundException f){
            System.out.println("Error Icono no encontrado :(");
        }
        return contenido.toString();
    }
    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }
}
