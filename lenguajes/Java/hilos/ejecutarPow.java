package Java.hilos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ejecutarPow {
    public static void main(String[] args) throws IOException {
        String[] comandos = {
                "./PSP_2324/lenguajes/Java/hilos/pow"
        };
        ProcessBuilder pb = new ProcessBuilder(comandos);
        Process p = pb.start();
        BufferedWriter bufferEscritor = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
        bufferEscritor.write('9');
        bufferEscritor.write('\n');
        bufferEscritor.flush();
        bufferEscritor.close();
        BufferedReader bufferLector = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String s=null;
        while ((s=bufferLector.readLine())!=null) {
            System.out.println(s);
        }
    }
}
