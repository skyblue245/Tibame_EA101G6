package com.shop.model;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ShopMailService extends Thread{
	private String to;
	private String subject;
	private String messageText;
	
	public void run(){
		sendMail(to, subject, messageText);
	}
	public ShopMailService(String to, String subject, String messageText) {
		this.to=to;
		this.subject=subject;
		this.messageText=messageText;
	}
	
	public void sendMail(String to, String subject, String messageText) {
			
	   try {
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

	     final String myGmail = "akk9798909@gmail.com";
	     final String myGmail_password = "0976890050";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   message.setSubject(subject);
		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("傳送成功");
     }catch (MessagingException e){
	     System.out.println("傳送失敗");
	     e.printStackTrace();
     }
   }
	
	 public static void main (String args[]){

//      String to = "chizai1101@gmail.com";
//      
//      String subject = "密碼通知";
//      
//      String ch_name = "peter1";
//      String passRandom = "111";
//      String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)"; 
//       
//      ShopMailService mailService = new ShopMailService();
//      mailService.sendMail(to, subject, messageText);

   }


}

