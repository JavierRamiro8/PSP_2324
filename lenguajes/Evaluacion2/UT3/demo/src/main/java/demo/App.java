package demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args)throws IOException {
        String archivo="./demo/src/main/java/demo/dirr.txt";
        BufferedReader bufferLector=new BufferedReader(new FileReader(archivo));
        
        while(bufferLector.readLine()!=null){
            String cuentaCorreo=bufferLector.readLine().toString();
            Email email = EmailBuilder.startingBlank()
            .to("Javier", cuentaCorreo)
            .from("Javier", "javier.ramiro8@educa.madrid.org")
            .withReplyTo("javier", "javier.ramiro8@educa.madrid.org")
            .withSubject("Proyecto Spamtoso")
            .withHTMLText("<h1>Hola!!</h1><p>¿Qué tal?</p>")
            .withPlainText("Hola! ¿Qué tal?")
            .buildEmail();

    Mailer mailer = MailerBuilder
            .withSMTPServer("smtp.educa.madrid.org", 587, "javier.ramiro8", "Smr2_2021")
            .withTransportStrategy(TransportStrategy.SMTP_TLS)
            .clearEmailValidator() // turns off email validation
            .buildMailer();
    mailer.sendMail(email);
        }
        bufferLector.close();
    }
}
