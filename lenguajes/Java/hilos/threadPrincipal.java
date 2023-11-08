package Java.hilos;

public class threadPrincipal {
    public static void main(String[] args) {
        holaThread ta=new holaThread("Alicia", 9);
        holaThread tb=new holaThread("Bobby", 12);
        //ta.run();
        //tb.run();

        Thread exeAlice=new Thread(ta);
        Thread exeBobby=new Thread(tb);
        exeAlice.run();
        exeBobby.run();
    }
}
