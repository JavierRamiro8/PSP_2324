package Java.Contador;

public class ContadorMain {
    public static final int VECES = 1000;

    public static void main(String[] args) {
        Contador c = new Contador();
        Thread t1 = new Thread(new Incrementador(c));
        Thread t2 = new Thread(new Decrementador(c));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException i) {
            i.getMessage();
        }
    }
}
