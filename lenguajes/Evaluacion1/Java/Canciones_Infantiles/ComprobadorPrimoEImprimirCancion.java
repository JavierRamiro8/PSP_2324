package Java.Canciones_Infantiles;

import java.util.Random;

public class ComprobadorPrimoEImprimirCancion implements Runnable {
    private String animal;
    private String accion;
    private int maxNumero;
    private final int MINNUMERORANDOM=100000;
    private final int MAXNUMERORANDOM=300001;
    private final int UNO=1;
    private final int CERO=0;
    public ComprobadorPrimoEImprimirCancion(String animal, String accion, int maxNumero) {
        this.animal = animal;
        this.accion = accion;
        this.maxNumero = maxNumero;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 1; i <= maxNumero; i++) {
            int NumeroRandom = MINNUMERORANDOM + random.nextInt(MAXNUMERORANDOM);
            System.out.printf("%s %s %s sobre la tela de una araña\n Como veia que resistia, fueron a llamar a otro %s\n",i,animal,accion,animal);
            System.out.printf("Número aleatorio generado: %d\n", NumeroRandom);

            boolean esPrimo = esPrimo(NumeroRandom);

            if (esPrimo) {
                System.out.println("El número aleatorio es primo.");
            } else {
                System.out.println("El número aleatorio no es primo.");
            }
            System.out.println("--------");
        }
    }

    private boolean esPrimo(int numero) {
        if (numero <= UNO) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == CERO) {
                return false;
            }
        }
        return true;
    }
}
