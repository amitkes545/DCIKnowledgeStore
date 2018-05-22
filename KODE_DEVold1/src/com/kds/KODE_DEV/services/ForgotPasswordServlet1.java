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

public class ForgotPasswordServlet1 extends CommonService {

	public void run() throws ServletException, IOException {

		String EmailId = request.getParameter("emialId");

		String UserId = request.getParameter("userId");
		
		ForgotPasswordDao forgotPasswordDao = new ForgotPasswordDao();

		String ratestatus = forgotPasswordDao.checkPwdCredentials(EmailId, UserId);

		System.out.println("ratestatus" + " " + ratestatus);
		if ("valid".equals(ratestatus)) {
			String successMessage = "Password sent to Registered Email ID";
			request.setAttribute("successMessage", successMessage);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/ForgetPasswordWebView.jsp");
			requestDispatcher.forward(request, response);
		} else {
			
			String message = ratestatus;
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/ForgetPasswordWebView.jsp");
			requestDispatcher.forward(request, response);

		}
	}

	//This method is using sending password to User via Mail
	
	public void SendPwd(String password1, String Emailid, String UserId) {
		String to = Emailid;
		System.out.println("sender email id:" + to);

		String host = "";
		String pop = "";
		int port;
		SendEmailDao senderDao = new SendEmailDao();
		SenderEmailDetailsDomain senderDom = (SenderEmailDetailsDomain) senderDao.senderdetails();

		final String emailid = senderDom.getEmailid();
		final String password = senderDom.getPassword();
		host = senderDom.getHost();
		pop = senderDom.getPop();
		port = senderDom.getPort();

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", pop);
		props.put("mail.smtp.auth", pop);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		//  creating Session object.
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailid, password);
			}
		});
		try {
	
			
			Message message = new MimeMessage(session);
		
			message.setFrom(new InternetAddress(emailid));
			
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			
			
			message.setSubject("Forgotten Password?"); 
			
			String msg = "Hi " + UserId + ",<br/><br/>" + "Your password is:" + "  " + password1 + "<br/><br/>"
					+ "Thanks and Regards" + "<br/>" + "Knowledge Store Technical Team" + "<br/><br/>"
					+ "For any technical assistance please contact on the details provided below:" + "<br/>"
					+ "<strong>Toll Free No:</strong> 1800123456" + "<br/>" + "<strong>SMS:</strong> 1234567891"
					+ "<br/>"
					+ "<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
			message.setContent(msg, "text/html; charset=utf-8");
			// Send message
			System.out.println("Before Sending message");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
