package Java.carrera;

public class Corredor implements Runnable {
    private static final long TIEMPO_DESCANSO = 500;
    private static final double INTERVALO_KM = 10;
    private int kmRecorridos;
    private int kmTotales;
    private int dorsal;

    public Corredor(int kmTotales, int dorsal) {
        this.kmTotales = kmTotales;
        this.dorsal = dorsal;
        this.kmRecorridos = 0;
    }

    @Override
    public void run() {
        synchronized (Principal.lock) {
            try {
                while (!Principal.todosListos) {
                    Principal.lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(String.format("Soy el dorsal %d, inicio mi carrera", dorsal));

        while (kmRecorridos < kmTotales) {
            try {
                System.out.println(String.format("Soy el dorsal %d, he recorrido %d KM de %d totales", dorsal,
                        kmRecorridos, kmTotales));
                long tiempoDeDescanso = (long) (Math.random() * TIEMPO_DESCANSO) + TIEMPO_DESCANSO;
                Thread.sleep(tiempoDeDescanso);
                System.out.println(String.format("Soy el dorsal %d, he descansado %d", dorsal, tiempoDeDescanso));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kmRecorridos += Math.random() * INTERVALO_KM;
        }
        System.out.println(String.format("Soy el dorsal %d, TERMINE!!!", dorsal));
    }
}
