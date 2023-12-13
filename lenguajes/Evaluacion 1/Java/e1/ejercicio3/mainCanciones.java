package Java.e1.ejercicio3;

import java.util.Scanner;

public class mainCanciones {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Cual es el animal que cantaremos");
        String animal = in.nextLine();
        System.out.println("Cual es la accion que hara el animal");
        String accion = in.nextLine();
        System.out.println("Cuantos animales habrá");
        int numAnimales = in.nextInt();
        cantaCanciones cancion = new cantaCanciones(numAnimales, animal, accion);
        Thread lanzaCanciones = new Thread(cancion);
        lanzaCanciones.run();
        in.close();
    }
}
