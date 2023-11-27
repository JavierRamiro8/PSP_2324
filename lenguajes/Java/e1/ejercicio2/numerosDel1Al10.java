package Java.e1.ejercicio2;

public class numerosDel1Al10 implements Runnable {

    @Override
    public void run() {
        final int finCuenta = 10;
        for (int i = 0; i < finCuenta; i++) {
            System.out.println(i);
        }
    }

}
