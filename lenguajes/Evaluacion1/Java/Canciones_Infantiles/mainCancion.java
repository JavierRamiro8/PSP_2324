package Java.Canciones_Infantiles;

import java.util.Scanner;

public class mainCancion {
    private static final int NUMEROMAXIMO = 5;
    private static final String ANIMALELEFANTE = "elefante";
    private static final String ANIMALPATO = "pato";
    private static final String ANIMALGATO = "gato";
    private static final int NUMEROANIMALESMAXIMO = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] accion = new String[3];
        for (int i = 0; i < NUMEROANIMALESMAXIMO; i++) {
            System.out.println("Introduce una accion");
            accion[i] = sc.nextLine().toString().toLowerCase();
        }
        Thread cancion1 = new Thread(new ComprobadorPrimoEImprimirCancion(ANIMALELEFANTE, accion[0], NUMEROMAXIMO));
        Thread cancion2 = new Thread(new ComprobadorPrimoEImprimirCancion(ANIMALPATO, accion[1], NUMEROMAXIMO));
        Thread cancion3 = new Thread(new ComprobadorPrimoEImprimirCancion(ANIMALGATO, accion[2], NUMEROMAXIMO));

        cancion1.setPriority(Thread.MAX_PRIORITY);
        cancion2.setPriority(Thread.NORM_PRIORITY);
        cancion3.setPriority(Thread.MIN_PRIORITY);

        cancion1.start();
        cancion2.start();
        cancion3.start();
        sc.close();
    }
}
