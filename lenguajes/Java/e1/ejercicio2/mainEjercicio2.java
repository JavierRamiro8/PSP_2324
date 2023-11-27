package Java.e1.ejercicio2;

public class mainEjercicio2 {
    public static void main(String[] args) {
        tablaDeMultiplicar tabla1 = new tablaDeMultiplicar(1);
        Thread hilo1 = new Thread(tabla1);
        hilo1.start();

        // Modificación para crear un array de N Threads y esperar por ellos
        int numHilos = 10;
        Thread[] hilos = new Thread[numHilos];

        // Crear e iniciar N hilos que escriben las tablas de multiplicar
        for (int i = 0; i < numHilos; i++) {
            tablaDeMultiplicar tabla = new tablaDeMultiplicar(i + 1);
            hilos[i] = new Thread(tabla);
            hilos[i].setName("Hilo" + (i + 1));
            hilos[i].start();
        }

        // Esperar por la finalización de todos los hilos
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Todos los hilos han terminado.");
    }
}
