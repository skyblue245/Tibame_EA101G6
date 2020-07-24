package com.emp.model;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmpMailService extends Thread {
		private EmpVO empVO;
		private String mail;
		private String newPwd;
		private String empno;
		private String action;//此action讓下面判斷現在要寄的信件是新員工的信還是忘記密碼的信
		
		public EmpMailService(){
			
		}
		
		//透過傳進不同的參數，來分辨是新進員工的信件還是忘記密碼的信件
		//將傳進的參數指定給全域變數
		public EmpMailService(EmpVO empVO, String mail, String newPwd){
			this.empVO = empVO;
			this.mail = mail;
			this.newPwd = newPwd;
			this.action = "getNewPwd";
		}
		
		public EmpMailService(EmpVO empVO, String empno){
			this.empVO = empVO;
			this.empno = empno;
			this.action = "getNewEmp";
		}
		
		//分辨是哪種信件後，呼叫各自需要的方法
		public void run(){
			if("getNewPwd".equals(action)) {
				getNewPwd();
			}else {
				getNewEmp();
			}
		}
		
		//本來參數是由EmpMailService()接到後傳給方法，方法拿這些參數利用，現在的方法變成可以，直接去全域變數取得方法內部所要的參數
		public void getNewPwd() {
			String name = empVO.getEmpname();
			String subject = "忘記密碼通知";
			String messageText = "Hello! " + name + " 請謹記此新密碼: " + newPwd ;
			sendMail(mail, subject, messageText);
		}
	
		public void getNewEmp() {
		
			String name = empVO.getEmpname();
			String mail = empVO.getMail();
			String ranPwd = empVO.getEmppwd();
			
			String subject = "密碼通知";
			String messageText = "Hello! " + name + " 請謹記此密碼: " + ranPwd + "\n" +" (已經啟用)" + "您的員編為：" + empno;
			
			sendMail(mail, subject, messageText);
		}
		
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
		public void sendMail(String to, String subject, String messageText) {
			
		   try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

	       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "tristaEA101@gmail.com";
		     final String myGmail_password = "EA101_G6";
			   Session session = Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //設定信中的主旨  
			   message.setSubject(subject);
			   //設定信中的內容 
			   message.setText(messageText);

			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
	   }
		
		 public static void main (String args[]){

//	      String to = "lct840116@gmail.com";
//	      
//	      String subject = "密碼通知";
//	      
//	      String ch_name = "peter1";
//	      String passRandom = "111";
//	      String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)"; 
//	       
//	      EmpMailService empMailService = new EmpMailService();
//	      empMailService.sendMail(to, subject, messageText);

	   }
}
