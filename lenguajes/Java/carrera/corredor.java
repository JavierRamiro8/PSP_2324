package Java.carrera;

public class corredor implements Runnable {
    private static final long TIEMPODESCANSO = 500;
    private static final double INTERVALO_KM = 10;
    int kmRecorridos;
    int kmTotales;
    int dorsal;

    public corredor(int kmTotales, int dorsal) {
        this.kmTotales = kmTotales;
        this.dorsal = dorsal;
        this.kmRecorridos = 0;

    }

    @Override
    public void run() {
        System.out.println(String.format("Soy el dorsal %d, inicio mi carrera", dorsal));
        while (kmRecorridos < kmTotales) {
            try {
                System.out.println(String.format("Soy el dorsal %d, he recorrido %d KM de %d totales", dorsal,
                        kmRecorridos, kmTotales));
                long tiempoDeDescanso = (long) (Math.random() * TIEMPODESCANSO) + TIEMPODESCANSO;
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
