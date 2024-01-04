package Java.hilos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class comprimir {
    public static void main(String[] args) throws IOException {

        int numRutasDestino = args.length - 1;
        String[] rutasDestino = new String[numRutasDestino];

        for (int i = 0; i < numRutasDestino; i++) {
            rutasDestino[i] = args[i];
        }

        // Obtiene la fecha actual en formato "ddMMyyyy"
        SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy");
        String fecha = formatoFecha.format(new Date());

        // Construye el comando tar para comprimir cada ruta por separado
        for (String rutaDestino : rutasDestino) {
            String nombre = rutaDestino + fecha + ".tar.gz";
            String[] comandoTar = new String[4];
            comandoTar[0] = "tar";
            comandoTar[1] = "-czvf";
            comandoTar[2] = nombre;
            comandoTar[3] = rutaDestino;

            ProcessBuilder pb = new ProcessBuilder(comandoTar);

            try {
                Process proceso = pb.start();
                int codigoSalida = proceso.waitFor();

                if (codigoSalida == 0) {
                    System.out.println("Se ha creado el archivo comprimido: " + nombre);
                } else {
                    System.out.println("Error al crear el archivo comprimido. Código de salida: " + codigoSalida);
                }
            } catch (Exception e) {
                System.out.println("Error al ejecutar el comando: " + e.getMessage());
            }
        }
    }
}