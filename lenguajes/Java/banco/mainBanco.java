package Java.banco;

public class mainBanco {
    public static void main(String[] args) {
        cuentaDelBanco banco = new cuentaDelBanco();
        Thread alice = new Thread(() -> banco.tranferencia("alice"));
        Thread bob = new Thread(() -> banco.tranferencia("bob"));
        alice.start();
        bob.start();
        System.out.println(banco.getCuenta());
    }
}
