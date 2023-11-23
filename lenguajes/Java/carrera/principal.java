package Java.carrera;

public class principal {
    private static final int DORSAL = 5000;
    private static int KMRECORRERMAX = 100;
    private static int CORREDORESMAX = 10;

    public static void main(String[] args) throws InterruptedException {
        /*
         * int dorsal=10;
         * int dorsal2=101;
         * corredor corredorThread = new corredor(KMRECORRERMAX, dorsal);
         * corredor corredorThread2 = new corredor(KMRECORRERMAX, dorsal2);
         * Thread corredor=new Thread(corredorThread);
         * Thread corredor2=new Thread(corredorThread2);
         * corredor.start();
         * corredor2.start();
         * 
         * try {
         * corredor.join();
         * corredor2.join();
         * } catch (InterruptedException e) {
         * e.printStackTrace();
         * }
         * System.out.println("La carrera ha terminado");
         */

        Thread[] corredorThread = new Thread[CORREDORESMAX];
        corredor[] corredores = new corredor[CORREDORESMAX];
        Thread salida = new Thread();
        for (int i = 0; i < CORREDORESMAX; i++) {
            corredores[i] = new corredor(KMRECORRERMAX, DORSAL + i);
            corredorThread[i] = new Thread(corredores[i]);
        }
        for(int i=0;i<corredores.length;i++){
            corredorThread[i].start();
        }

        synchronized (salida) {
            corredorThread.notifyAll();
        }
        for (int i = 0; i < CORREDORESMAX; i++) {
            try {
                corredorThread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
