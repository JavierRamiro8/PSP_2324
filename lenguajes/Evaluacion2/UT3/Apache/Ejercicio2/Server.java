package Apache.Ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.nio.Buffer;

/***
 * 
 * @author Jorge Dueñas Lerín
 *	Objetivo: entender cómo se programa un servicio usando de ejemplo el protocolo HTTP
 */
public class Server {

	private static final int DEFAULT_PORT = 8765;
	private static final int RESOURCE_POSITION = 1;

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(DEFAULT_PORT);

		while (true){
			Socket connCliente = server.accept();
			BufferedReader reader = new BufferedReader(
										new InputStreamReader(
											connCliente.getInputStream()
										)
									);
			String header = reader.readLine();
			System.out.println(header);
			// GET ________ HTTP/1.1
			String info = extraeInformacion(header);
			String html = generaPagina(info);
			
			BufferedWriter writer = new BufferedWriter(
										new OutputStreamWriter(
											connCliente.getOutputStream()
										)
									);
			// Escribir cabecera
			writer.write("HTTP/1.1 200 OK\n");
			writer.write("Content-Type: application/json; charset=utf-8\n");
			writer.write("\n");
			writer.write(html);
			writer.flush();

			reader.close();
			writer.close();
			connCliente.close();
		}
	}

	private static String generaPagina(String info) {
        String[] datos=info.split("/");
        int resultado;
        if(datos[1].toLowerCase().equals("suma")){
            int numero1=Integer.valueOf(datos[2].toString());
            int numero2=Integer.valueOf(datos[3].toString());
            resultado=numero1+numero2;
        }else if(datos[1].toLowerCase().equals("resta")){
            int numero1=Integer.valueOf(datos[2].toString());
            int numero2=Integer.valueOf(datos[3].toString());
            resultado=numero1-numero2;
        }else if(datos[1].toLowerCase().equals("multiplicacion")){
            int numero1=Integer.valueOf(datos[2].toString());
            int numero2=Integer.valueOf(datos[3].toString());
            resultado=numero1*numero2;
        }else{
            int numero1=Integer.valueOf(datos[2].toString());
            int numero2=Integer.valueOf(datos[3].toString());
            if(numero1<numero2){
            resultado=numero2/numero1;
            }else{
            resultado=numero1/numero2;
            }
        }
		//return String.format("<h1>Has pedido <b>%s</b></h1>", info);
		return String.format("{\"%s\": \"%s\"}",datos[1], String.valueOf(resultado));
	}

	private static String extraeInformacion(String header) {
		return header.split(" ")[RESOURCE_POSITION];
	}
}
