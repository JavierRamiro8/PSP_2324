package Java.Contador;

public class Contador {
    int c;
    public static final int UNO=1;

    public void increment() {
        c = c + UNO;
    }

    public void decrement() {
        c = c - UNO;
    }

    @Override
    public String toString() {
        return String.valueOf(c);
    }
}
