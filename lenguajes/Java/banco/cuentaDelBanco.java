package Java.banco;

public class cuentaDelBanco {
    private static final int NUMEROMAXTRANS = 1000;
    int contadorAlice = 0;
    int contadorBob = 0;
    int cuentaBob = 0;
    int cuentaAlice = 0;
    int cuentaTotal = 0;
    private static final int DINERO = 10;

    public synchronized void tranferencia(String usuario) {
        for (int i = 0; i < NUMEROMAXTRANS; i++) {
            if (usuario.equals("bob")) {
                cuentaBob += DINERO;
                contadorBob++;
            } else if (usuario.equals("alice")) {
                cuentaAlice -= DINERO;
                contadorAlice++;
            }
        }
        cuentaTotal = cuentaBob + cuentaAlice;
    }

    public int getCuenta() {
        return cuentaTotal;
    }
}
