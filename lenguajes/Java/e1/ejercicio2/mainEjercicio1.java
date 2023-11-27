package Java.e1.ejercicio2;

public class mainEjercicio1 {
    public static void main(String[] args) {
        numerosDel1Al10 cuenta = new numerosDel1Al10();
        Thread contenedorCuenta = new Thread(cuenta);
        contenedorCuenta.run();
    }
}
