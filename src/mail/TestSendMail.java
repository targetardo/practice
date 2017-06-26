package mail;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class TestSendMail {
	private String smm;
//public static void main(String[] args) {
public void sender(){
		String senderMail = "pashagabela@gmail.com";
		String password = "wbrkbycrbq230897";
		String receiverMail = "pasha-s777@mail.ru";
		
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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverMail));
			message.setSubject("About all");

			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText("Data has updated!");
			
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);

			message.setContent(mp);
			message.setSentDate(new Date());
			message.saveChanges();

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
public void setSmm(String smm) {
			this.smm = smm;
		}
	}
//}
