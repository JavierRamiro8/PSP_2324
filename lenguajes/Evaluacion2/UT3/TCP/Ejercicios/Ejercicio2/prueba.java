package TCP.Ejercicios.Ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prueba {
    public static void main(String[] args) throws IOException {
        String[] comandos={"ls","/var"};
        ProcessBuilder processBuilder = new ProcessBuilder(comandos);
        Process proceso = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String linea;
        while ((linea = reader.readLine()) != null) {
            System.out.println(linea);
        }
    }
}
