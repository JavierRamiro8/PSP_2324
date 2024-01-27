package UDP.BroadCast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EditorReceive {
      private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) throws IOException, InterruptedException {
        int puerto = 2033;
        DatagramSocket dsServer = new DatagramSocket(puerto);

        while (true) {
            // Recibir mensaje del cliente
            DatagramPacket receivePacket = recibirNumero(dsServer);
            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if(receivedData.equals("editor") || receivedData.equals("gedit")){
                System.out.println("el usuario "+receivePacket.getAddress()+": "+receivePacket.getPort()+" has escrito la palabra magica, la palabra que has escrito es: "+receivedData);
                //Ejecutar el comando que pasa por el receiveData
                String comando = receivedData;
            ProcessBuilder processBuilder = new ProcessBuilder(comando);

            Process proceso = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            }else{
                System.out.println("el usuario "+receivePacket.getAddress()+":"+receivePacket.getPort()+" no has escrito la palabra magica, la palabra que has escrito es: "+receivedData);
            }
        }
    }

    private static DatagramPacket recibirNumero(DatagramSocket ds) throws IOException {
        byte[] buffer = new byte[MAX_LENGTH];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        ds.receive(receivePacket);
        return receivePacket;
    }
}
