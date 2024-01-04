package Java.carrera;

public class Principal {
    private static final int DORSAL = 5000;
    private static int KM_RECORRER_MAX = 100;
    private static int CORREDORES_MAX = 10;
    public static final Object lock = new Object(); // Objeto compartido para la sincronización
    public static boolean todosListos = false;

    public static void main(String[] args) throws InterruptedException {
        Thread[] corredorThread = new Thread[CORREDORES_MAX];
        Corredor[] corredores = new Corredor[CORREDORES_MAX];

        for (int i = 0; i < CORREDORES_MAX; i++) {
            corredores[i] = new Corredor(KM_RECORRER_MAX, DORSAL + i);
            corredorThread[i] = new Thread(corredores[i]);
        }

        // Notificar a los corredores que están listos para comenzar
        synchronized (lock) {
            todosListos = true;
            lock.notifyAll();
        }

        for (int i = 0; i < corredores.length; i++) {
            corredorThread[i].start();
        }

        for (int i = 0; i < CORREDORES_MAX; i++) {
            try {
                corredorThread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
