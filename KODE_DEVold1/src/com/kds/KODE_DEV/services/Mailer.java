package com.kds.KODE_DEV.services;

import java.sql.Connection;
import com.kds.KODE_DEV.dbconnection.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;



public class Mailer {
	static final Logger logger = Logger.getLogger(Mailer.class);

	public static void send(String to, String subject, String msg) throws AddressException, MessagingException {
		logger.info(" Start of send() for Sending Mail >>");
		String from = "", password = "";
		String host = "";
		int port = 0;
		try {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			conn = DBTransaction.connect();
			ps = conn.prepareStatement("select * from senderemaildetails");
			rs = ps.executeQuery();

			if (rs.next()) {
				from = rs.getString("emailid");
				password = rs.getString("password");
				host = rs.getString("host");
				port = rs.getInt("port");
			}
		} catch (Exception e) {
			logger.error("Error while getting Credential, host and Port for Sending Mail " + e.getMessage());
		}

		// Configuring the email related configuration Anup old.

		final String user = from;
		final String pass = password;

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);

		// taking the session
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, pass);
					}
				});

		// composing the message
	//	try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(subject);
		//	message.setText(msg);
			message.setContent(msg, "text/html; charset=utf-8");
			// sending the message to receiver.
			logger.info(" Before Sending Mail to User >>");
			Transport.send(message);
			logger.info(" After Sending Mail to User >>");

		/*} catch (MessagingException e) {
			logger.error("Error while Sending mail " + e.getMessage());
			//throw new RuntimeException(e);
		}*/
	}
}
