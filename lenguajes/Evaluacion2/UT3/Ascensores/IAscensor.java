package Ascensores;

public class IAscensor implements Runnable {
    private int id;
    private String ip;
    private int puerto;
    private int contSubir;
    private int contBajar;

    void config() {
        System.out.println(id + " " + ip + " " + puerto);
    }

    public void run() {
        System.out.println("COD_ASCESOR: " + id + " Piso: " + "0" + " Direccion: " + "0");
    }

    synchronized void subir() {
        contSubir++;
    }

    public IAscensor(int id, String ip, int puerto) {
        this.id = id;
        this.ip = ip;
        this.puerto = puerto;
    }

    synchronized void bajar() {
        contBajar++;
    }

    synchronized void getPlanta() {

    }

    synchronized void toProtocolo() {

    }
}
