package com.kes.kote.services;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;

public class Mailer {
	static final Logger logger = Logger.getLogger(Mailer.class);

	public static void send(String to, String subject, String msg,HttpSession sess) throws AddressException, MessagingException {
		
		String status=PropertiesUtil.getMailProperty("sendmail");
		
		if(!status.trim().equalsIgnoreCase("yes"))
			return;
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		//util.print(" Start of send() for Sending Mail >>");

		// Configuring the email related configuration

		final String username = PropertiesUtil.getMailProperty("mail.email");
		final String pass = PropertiesUtil.getMailProperty("mail.password");
		final String host=PropertiesUtil.getMailProperty("mail.host");
		final String port=PropertiesUtil.getMailProperty("mail.port");

		Properties props = new Properties();
			
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// taking the session
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, pass);
					}
				});

		// composing the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(subject);
			//message.setText(msg);
			message.setContent(msg, "text/html; charset=utf-8");

			// sending the message to receiver.
			util.print(" Before Sending Mail to User >>"+msg);
			Transport.send(message);
			util.print(" After Sending Mail to User >>");

		} catch (MessagingException e) {
			//e.printStackTrace();
			util.print("Error while Sending mail " + e.getMessage());
			//throw new RuntimeException(e);
		}
	}
	
	
}
