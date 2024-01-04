package Java.e1.ejercicio1;

public class ejercicio1_3 {
    public static void main(String[] args) {
        Thread hilo3 = new Thread(() -> System.out.println("Hola Mundo desde un hilo con Lambda"));
        hilo3.start();
    }
}
