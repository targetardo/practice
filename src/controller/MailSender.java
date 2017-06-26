package controller;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {
public static void send(String to, String subject, String text) {
String senderMail = "pashagabela@gmail.com";
String password = "wbrkbycrbq230897";

Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "587");

Session session = Session.getInstance(props, new Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(senderMail, password);
}
});

try {
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress(senderMail));
message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
message.setSubject(subject);

MimeBodyPart mbp = new MimeBodyPart();
mbp.setText(text);

Multipart mp = new MimeMultipart();
mp.addBodyPart(mbp);

message.setContent(mp);
message.setSentDate(new Date());
message.saveChanges();

Transport.send(message);

System.out.println("Done");

} catch (MessagingException e) {
throw new RuntimeException(e);
}

}
}