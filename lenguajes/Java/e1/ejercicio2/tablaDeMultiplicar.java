
package Java.e1.ejercicio2;

public class tablaDeMultiplicar implements Runnable {
    private int numero;

    public tablaDeMultiplicar(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + numero + " x " + i + " = " + (numero * i));
        }
    }
}