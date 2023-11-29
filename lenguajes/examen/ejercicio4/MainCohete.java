package examen.ejercicio4;

public class MainCohete {
    public static boolean todosListos = false;
    public static final Object lock = new Object();

    public static void main(String[] args) {
        int numeroArg = Integer.parseInt(args[1]);
        PropulsorFallo[] prototipo = new PropulsorFallo[numeroArg];
        Thread[] numeroPropulsores = new Thread[numeroArg];
        for (int i = 0; i < numeroArg; i++) {
            prototipo[i] = new PropulsorFallo("Propulsor" + i, numeroArg);
            numeroPropulsores[i] = new Thread(prototipo[i]);
        }
        synchronized (lock) {
            todosListos = true;
            lock.notifyAll();
        }
        for (int i = 0; i < numeroArg; i++) {
            numeroPropulsores[i].start();
        }

        for (int i = 0; i < numeroArg; i++) {
            try {
                numeroPropulsores[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
