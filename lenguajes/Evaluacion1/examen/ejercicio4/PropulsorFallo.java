package examen.ejercicio4;

import Java.e1.ejercicio2.numerosDel1Al10;

public class PropulsorFallo implements Runnable {
    private static final int TIEMPO_DESCANSO = 1000;
    private static final int INTERVALO_KM = 50;

    private int suertePropulsor() {
        int suerte = (int) Math.random() * 1;
        return suerte;
    }

    public PropulsorFallo(String cadenaNombre, int numeroPropulsores) {
        this.cadenaNombre = cadenaNombre;
        this.numeroPropulsores = numeroPropulsores;
    }

    private int kmTope = 408;
    private int kmRecorridos = 0;
    private String cadenaNombre = "";
    private int contadorExplosion = 0;
    private int numeroPropulsores = 0;

    @Override
    public void run() {
        synchronized (MainCohete.lock) {
            try {
                while (!MainCohete.todosListos) {
                    MainCohete.lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Empezamos a despegar");
        while (kmRecorridos < kmTope) {
            System.out.println(cadenaNombre + "he recorrido " + kmRecorridos);
            int tiempoDeDescanso = (int) (Math.random() * TIEMPO_DESCANSO);
            try {
                Thread.sleep(tiempoDeDescanso);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Soy el cohete%d, he descansado %d", cadenaNombre, tiempoDeDescanso));
            if (suertePropulsor() == 1) {
                Thread.interrupted();
                contadorExplosion++;
            }
            kmRecorridos += Math.random() * INTERVALO_KM;
        }
        System.out.println(String.format("Soy el propulsor %d, TERMINE!!!", cadenaNombre));
        System.out.println("Han explotado: " + contadorExplosion);
        if (numeroPropulsores > contadorExplosion) {
            System.out.println("la mision fue un exito");
        } else {
            System.out.println("la mision fue un fracaso");
        }
    }

}
