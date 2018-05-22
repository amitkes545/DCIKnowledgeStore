package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.ForgotPasswordDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

@SuppressWarnings("serial")
public class ForgotPasswordServlet extends CommonService{
	public void run() throws ServletException, IOException {
		//System.out.println("action......");
		String Eid=request.getParameter("eid");
		String Uid=request.getParameter("uid");
	
ForgotPasswordDao fdao=new ForgotPasswordDao();

String ratestatus=fdao.checkPwdCredentials(Eid,Uid);
//System.out.println("ratestatus"  +" " +ratestatus);
if("valid".equals(ratestatus)){
	String successMessage="Hi Your Password sent to your email ID!!";
	request.setAttribute("successMessage", successMessage);
	RequestDispatcher rd=request.getRequestDispatcher("../JSP/ForgotPassword.jsp");
	rd.forward(request, response);
}
else{
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/ForgotPassword.jsp?message=" +ratestatus);
		rd.forward(request, response);
		
}
}
	public void SendPwd(String password1,String Emailid){
		String to = Emailid;  //change accordingly
		//System.out.println("sender email id:"+to);
		// Sender's email ID needs to be mentioned
		//final String from = "jay.prakash@digitalastraasol.com"; ////change accordingly
		//final String password = "Terepyar@12345";//change accordingly 
		// Assuming you are sending email through relay.jangosmtp.net 
		
		String host="" ;
		String pop="";
		int    port;
		SendEmailDao senderDao =new SendEmailDao();
		SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
		
		final String emailid =senderDom.getEmailid();
		final String password=senderDom.getPassword();
		 host= senderDom.getHost();
		 pop=senderDom.getPop();
		   port=senderDom.getPort();
		
		//String host = "smtp.gmail.com"; 
		Properties props = new Properties();
	 	props.put("mail.smtp.starttls.enable",  pop);
		props.put("mail.smtp.auth",  pop); 
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port",  port); 
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// Get the Session object. 
		Session session = Session.getInstance(props, new javax.mail.Authenticator() 
		{ protected PasswordAuthentication getPasswordAuthentication() 
		{ return new PasswordAuthentication(emailid, password); } }); 
		try { 
			// Create a default MimeMessage object. 
			Message message = new MimeMessage(session); 
			// Set From: header field of the header. 
			message.setFrom(new InternetAddress(emailid)); 
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); 
			// Set Subject: header field 
			message.setSubject("User Information"); // Now set the actual message
			message.setText("Hi,\n"  +"Welcome to KODE_DEV!!!\n "  +"your Password is:" +"  " +password1+   "\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com" ); 
			// Send message 
			//System.out.println("Before Sending message");
			Transport.send(message); 
			//System.out.println("Sent message successfully....");
	} catch (MessagingException e) {
		throw new RuntimeException(e);
		} 
	}
}
