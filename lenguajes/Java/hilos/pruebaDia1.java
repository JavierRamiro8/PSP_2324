package Java.hilos;

import java.io.IOException;
public class pruebaDia1 {
    public static void main(String[] args) throws IOException {
        final int NUMERO3=3;
        ProcessBuilder pb = new ProcessBuilder("xed");

        for (int i = 0; i < NUMERO3; i++) {
            pb.start();
        }
        System.out.println("SE ha terminado el proceso");
    }
}
