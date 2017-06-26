package mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import controller.MailSender;

public class TestReceivMail2 {
	public static void main(String[] args) {
 
		String userName = "pashagabela@gmail.com";
		String password = "wbrkbycrbq230897";
		
		String protocol = "pop3";
		String host = "pop.gmail.com";
		String port = "995";
			
	   Properties properties = getServerProperties(protocol, host, port);
	    Session session = Session.getDefaultInstance(properties);
	    
	    try {
	        // connecting to the message store
	        Store store = session.getStore(protocol);
	        store.connect(userName, password);
	        // opens the inbox folder
	        Folder folderInbox = store.getFolder("INBOX");
	        folderInbox.open(Folder.READ_ONLY);

	        // fetching new messages from server
	        Message[] messages = folderInbox.getMessages();
	        int n = messages.length;
	        System.out.println("the total number of messages " + n);
	        for (int i = n-1; i >= 0 && i > n - 10; i--) {  
	           Message msg = messages[i];
	            Address[] fromAddress = msg.getFrom();
	            String from = fromAddress[0].toString();
	            String subject = msg.getSubject();
	            String sentDate = msg.getSentDate().toString();
	            String contentType = msg.getContentType();
	            String messageContent = "";

	            if (contentType.contains("text/plain")
	                    || contentType.contains("text/html")) {
	                try {
	                    Object content = msg.getContent();
	                    if (content != null) {
	                        messageContent = content.toString();
	                    }
	                } catch (Exception ex) {
	                    messageContent = "[Error downloading content]";
	                    ex.printStackTrace();
	                }
	            }

	            // print out details of each message
	            System.out.println("Message #" + (i + 1) + ":");
	            System.out.println("\t From: " + from);
	            System.out.println("\t Subject: " + subject);
	            System.out.println("\t Sent Date: " + sentDate);
	            System.out.println("\t Message: " + messageContent);
	        }  
	        
	        // disconnect
	        folderInbox.close(false);
	        store.close();
	    } catch (NoSuchProviderException ex) {
	        System.out.println("No provider for protocol: " + protocol);
	        ex.printStackTrace();
	    } catch (MessagingException ex) {
	        System.out.println("Could not connect to the message store");
	        ex.printStackTrace();
	    }


	}
	private static Properties getServerProperties(String protocol, String host, String port) {
	    Properties properties = new Properties();

	    // server setting
	    properties.put(String.format("mail.%s.host", protocol), host);
	    properties.put(String.format("mail.%s.port", protocol), port);

	    // SSL setting
	    properties.setProperty(
	            String.format("mail.%s.socketFactory.class", protocol),
	            "javax.net.ssl.SSLSocketFactory");
	    properties.setProperty(
	            String.format("mail.%s.socketFactory.fallback", protocol),
	            "false");
	    properties.setProperty(
	            String.format("mail.%s.socketFactory.port", protocol),
	            String.valueOf(port));

	    return properties;
	}
	
}
