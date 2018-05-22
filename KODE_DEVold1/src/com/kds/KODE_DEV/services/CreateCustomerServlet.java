/* @Authour Lavanya
 * Creating Customer service

 */
package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.CustomerDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

@SuppressWarnings("serial")
public class CreateCustomerServlet extends CommonService {

	String host = "";
	String pop = "";
	int port;
	String emailid = "";
	String password = "";
	String link = "",genaratePassword="";
	static final Logger LOGGER = Logger.getLogger(CreateCustomerServlet.class);

	
	/* //method for password created ramdomly
    public static String randomSeriesForThreeCharacter() {
           LOGGER.info("calling password method");
	    Random random = new Random();
	    LOGGER.info("random charactor:"+random);
	             String value="";
	                int random_Char ;
	    for(int i=0; i<2;i++)
	             { 
	               random_Char = random.nextInt(74);
	                value=value+random_Char;
	             }
	    return value;
	}*/
    
	public void run() throws ServletException, IOException {
		session = request.getSession(true);
		String message = "";
		AdminDomain adminDomain = new AdminDomain();// object creation for admin
		CustomerDao customerDao=new CustomerDao();											// domin
		adminDomain.setAdminId(request.getParameter("cid"));
		adminDomain.setAdminName(request.getParameter("cname"));
		 String adminName=request.getParameter("cname");
		//adminDomain.setPwd(request.getParameter("pwd"));
		adminDomain.setEmail(request.getParameter("email"));
		adminDomain.setAddress(request.getParameter("address"));
		adminDomain.setPhone(request.getParameter("phone"));
		adminDomain.setPrivilege(request.getParameter("privilege"));
		adminDomain.setOrgid(request.getParameter("orgid"));
		adminDomain.setCreated_by(request.getParameter("createdid"));
		// LOGGER.info("organization id is:"+request.getParameter("orgid"));
		// LOGGER.info("created by in service:"+request.getParameter("createdid"));
		SendEmailDao senderDao = new SendEmailDao();
		// session.removeAttribute("MsgValue");
		SenderEmailDetailsDomain senderDom = (SenderEmailDetailsDomain) senderDao
				.senderdetails();
		emailid = senderDom.getEmailid();
		password = senderDom.getPassword();
		host = senderDom.getHost();
		pop = senderDom.getPop();
		port = senderDom.getPort();
		link = senderDao.getWebLink();// selecting web room link from dao
		//generating random password 
        String password=customerDao.randomSeriesForThreeCharacter();	// customer
        String  adminName1="";
        if (adminName.length() > 3){
        	adminName1 = adminName.substring(0, 3);
        }
        
        genaratePassword=adminName1+password;
		//System.out.println("password:"+genaratePassword);	
		adminDomain.setPwd(genaratePassword);
		CustomerDao customerdao = new CustomerDao();// dao class object for
													// customer dao class
		boolean isDuplicate = customerdao.duplicate(adminDomain);
		LOGGER.info("duplicate value is" + isDuplicate);
		RequestDispatcher requestDispatcher;
		if (isDuplicate) {
			message = "UserID is exit!please try another";
			request.setAttribute("MsgValue", message);
			requestDispatcher = request
					.getRequestDispatcher("../JSP/CreateCustomer.jsp");
			requestDispatcher.forward(request, response);
		}
		String retstatus = customerdao.insertCustomerValues(adminDomain);// inserting
																			// the
																		// details
		if ("valid".equals(retstatus)) {
			String to = adminDomain.getEmail();
			sendMailIntern(host, to);
			message = "New customer sucessfully configured ";
			request.setAttribute("OwnerSuccessMAil", message);
			requestDispatcher = request
					.getRequestDispatcher("../JSP/CreateCustomer.jsp");
			requestDispatcher.forward(request, response);
		} else if ("notvalid".equals(retstatus)) {
			message = "Customer Creation is failed! try again";
			request.setAttribute("OwnerFailure", message);
			requestDispatcher = request
					.getRequestDispatcher("../JSP/CreateCustomer.jsp");
			requestDispatcher.forward(request, response);
		} else if ("true".equalsIgnoreCase(retstatus)) {
			message = "UserId is exit..please verify";
			request.setAttribute("MsgValue", message);
			requestDispatcher = request
					.getRequestDispatcher("../JSP/CreateCustomer.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	public void sendMailIntern(String host1, String to) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", pop);
		props.put("mail.smtp.starttls.enable", pop);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(emailid, password);
					}
				});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailid));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			// message.setSubject("Testing");
			// message.setText("UserID:"+emailid+ "Password:"+password);
			message.setSubject("User Information"); // Now set the actual
													// message
			message.setText("Hi "
					+ request.getParameter("cname")
					+ " ,\n"
					+ "Welcome to KODE_DEV!!!\n "
					+ "Your userID :"
					+ request.getParameter("cid")
					+ "& "
					+ "Password :"
					+ genaratePassword
					+ ",\n"
					+ "Role :"
					+ request.getParameter("privilege")
					+ ",\n"
					+ "Kindly login into the below URL to start accessing it:\n"
					+ link + "\n\n" + "Thanks & Regards" + "\n"
					+ "Kompac Digital Systems Pvt. Ltd." + "\n"
					+ "Website : www.kompacdigit.com");
			// message.setText("SessionStartTime:"+SessionStartTime);
			// message.setText("SessionEndTime:"+SessionEndTime);
			// message.setText("Duration :"+hours);
			// LOGGER.info();
			Transport.send(message);
			LOGGER.info("send successfully");
			/*
			 * try { response.sendRedirect("../JSP/success.jsp"); } catch
			 * (IOException e) { e.printStackTrace(); }
			 */
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
