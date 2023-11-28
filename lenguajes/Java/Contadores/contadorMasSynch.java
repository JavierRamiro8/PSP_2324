package Java.Contadores;

public class contadorMasSynch extends Thread {
    private static final int MAXCONT = 1000;
    int contadorSum = 0;

    public synchronized void sumarSynch() {
        for (int i = 0; i < MAXCONT; i++) {
            contadorSum++;
        }
    }

    public int getContadorSumSynch() {
        return contadorSum;
    }
}
