package Java.Contadores;

public class mainContador {
    private static final int MAXHILOS = 5;

    public static void main(String[] args) {
        System.out.println("");
        contadorMasSynch sumarSynch = new contadorMasSynch();
        contadorMenosSynch restarSynch = new contadorMenosSynch();
        Thread hilosumarSynch[] = new Thread[MAXHILOS];
        Thread hilorestarSynch[] = new Thread[MAXHILOS];
        for (int i = 0; i < MAXHILOS; i++) {
            hilosumarSynch[i] = new Thread(() -> sumarSynch.sumarSynch());
            hilorestarSynch[i] = new Thread(() -> restarSynch.restarSynch());
            hilosumarSynch[i].start();
            hilorestarSynch[i].start();
        }
        try {
            for (int i = 0; i < MAXHILOS; i++) {
                hilosumarSynch[i].join();
                hilorestarSynch[i].join();
            }
        } catch (InterruptedException e) {
        }
        System.out.println(sumarSynch.getContadorSumSynch() + restarSynch.getContadorRestarSynch());

        System.out.println("\n");
        contadorMas sumar = new contadorMas();
        contadorMenos restar = new contadorMenos();
        Thread hiloSumar[] = new Thread[MAXHILOS];
        Thread hiloRestar[] = new Thread[MAXHILOS];
        for (int i = 0; i < MAXHILOS; i++) {
            hiloSumar[i] = new Thread(() -> sumar.sumar());
            hiloRestar[i] = new Thread(() -> restar.restar());
            hiloSumar[i].start();
            hiloRestar[i].start();
        }
        try {
            for (int i = 0; i < MAXHILOS; i++) {
                hiloSumar[i].join();
                hiloRestar[i].join();
            }
        } catch (InterruptedException e) {
        }
        System.out.println(sumar.getContadorSum() + restar.getContadorRestar());
    }
}
