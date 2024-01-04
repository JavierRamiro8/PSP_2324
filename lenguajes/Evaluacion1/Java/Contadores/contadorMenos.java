package Java.Contadores;

public class contadorMenos extends Thread {
    private static final int MAXCONT = 10000000;
    int contadorRestar = 0;

    public void restar() {
        for (int i = 0; i < MAXCONT; i++) {
            contadorRestar--;
        }
    }

    public int getContadorRestar() {
        return contadorRestar;
    }
}
