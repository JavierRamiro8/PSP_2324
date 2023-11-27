package Java.e1.ejercicio1;

public class ejercicio1_2 {
    public static void main(String[] args) {
        holamundo1_2 holamundo = new holamundo1_2();
        Thread manejadorHolaMundo = new Thread(holamundo);
        manejadorHolaMundo.run();
    }
}
