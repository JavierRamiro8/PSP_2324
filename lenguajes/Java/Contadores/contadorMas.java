package Java.Contadores;

public class contadorMas extends Thread {
    private static final int MAXCONT = 10000000;
    int contadorSum = 0;

    public void sumar() {
        for (int i = 0; i < MAXCONT; i++) {
            contadorSum++;
        }
    }

    public int getContadorSum() {
        return contadorSum;
    }
}
