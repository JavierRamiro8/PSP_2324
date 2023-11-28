package Java.Contadores;

public class contadorMenosSynch extends Thread {
    private static final int MAXCONT = 1000;
    int contadorRestar = 0;

    public synchronized void restarSynch() {
        for (int i = 0; i < MAXCONT; i++) {
            contadorRestar--;
        }

    }

    public int getContadorRestarSynch() {
        return contadorRestar;
    }
}
