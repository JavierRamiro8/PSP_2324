package Java.hilos;

public class holaThread implements Runnable{
    
    private String nombre;
    private int nVeces;


    
    public holaThread(String nombre, int nVeces) {
        this.nombre = nombre;
        this.nVeces = nVeces;
    }

    public static void main(String[] args) {
        
    }

    @Override
    public void run() {
        
        for(int i=0;i<nVeces;i++){
            System.out.println(nombre);
        }
    }
}
