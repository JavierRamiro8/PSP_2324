package Ascensores;

public class MainAscensores {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("El esquema para este programa es <id> <ip> <puerto>");
            return;
        }
        int id = Integer.parseInt(args[0]);
        String ip = args[1];
        int puerto = Integer.parseInt(args[2]);
        IAscensor ascensorPaco = new IAscensor(id, ip, puerto);
        long tiempoActual = System.currentTimeMillis();
        long tiempoEspera = 100;
        while (true) {
            if (System.currentTimeMillis() - tiempoActual >= tiempoEspera) {
                ascensorPaco.run();
            }
        }
    }
}
