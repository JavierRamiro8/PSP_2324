package Java.e1.ejercicio3;

public class cantaCanciones implements Runnable {
    private int numeroMaxAnimal = 0;
    private String animal = "";
    private String accionAnimal = "";

    public cantaCanciones(int numeroMaxAnimal, String animal, String accionAnimal) {
        this.numeroMaxAnimal = numeroMaxAnimal;
        this.animal = animal;
        this.accionAnimal = accionAnimal;
    }

    @Override
    public void run() {
        for (int i = 1; i <= numeroMaxAnimal; i++) {
            System.out.println(i + animal + " se " + accionAnimal
                    + " sobre la tela de una araña, como veia que no se caia, llamo a otro " + animal + "\n");
            int numeroRand = (int) (Math.random() * 2000) + 1000;
            if (esPrimo(numeroRand)) {
                System.out.println(numeroRand + " Es primo\n");
            } else {
                System.out.println(numeroRand + " No es primo\n");

            }
        }
    }

    public static boolean esPrimo(int num) {
        if (num <= 1) {
            return false; // Los números menores o iguales a 1 no son primos
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // Si es divisible por algún número, no es primo
            }
        }

        return true; // Si no es divisible por ningún número, es primo
    }

}
