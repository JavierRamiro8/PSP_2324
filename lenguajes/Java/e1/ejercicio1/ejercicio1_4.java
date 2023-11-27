package Java.e1.ejercicio1;

public class ejercicio1_4 {
    public static void main(String[] args) {
        holamundo1_1 holamundo = new holamundo1_1();
        holamundo.start();
        System.out.println("\n");
        holamundo1_2 holamundo2 = new holamundo1_2();
        Thread manejadorHolaMundo = new Thread(holamundo2);
        manejadorHolaMundo.run();
        System.out.println("\n");
        Thread hilo3 = new Thread(() -> System.out.println("Hola Mundo desde un hilo con Lambda"));
        hilo3.start();
    }
}
