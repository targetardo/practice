package mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
 
import java.util.Properties;
 
 
/**
 * Created by Анна on 28.04.2017.
 */
public class Sender {
    private String username;
    private String password;
    private Properties properties;
 
    public Sender(String username, String password) {
        this.username = username;
        this.password = password;
        this.properties = new Properties();
 
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }
    public void send(String subject, String text, String toEmail) throws MessagingException {
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        //от кого
        message.setFrom(new InternetAddress(username));
        //кому
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        //Заголовок письма
        message.setSubject(subject);
        //Содержимое
        message.setText(text);
        Transport.send(message);
    }
}