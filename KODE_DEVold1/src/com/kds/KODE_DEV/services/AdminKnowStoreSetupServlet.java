package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacultySetupknowStoreDao;
import com.kds.KODE_DEV.dao.SenderEmailDetailsDAO;
import com.kds.KODE_DEV.domain.FacultySetupKnowStoreDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.services.Mailer;

@SuppressWarnings("serial")
public class AdminKnowStoreSetupServlet extends CommonService {

	static final Logger LOGGER = Logger.getLogger(AdminKnowStoreSetupServlet.class);

	String userid = "";
	String emailid = "";
	String password = "";
	String host = "";
	String pop = "";
	int port;
	String ksId = "";
	FacultySetupKnowStoreDomain aksDomain = new FacultySetupKnowStoreDomain();
	FacultySetupknowStoreDao ksDao = new FacultySetupknowStoreDao();

	@Override
	public void run() throws ServletException, IOException {

		session = request.getSession(false);
		String teachingSourceName=(String)session.getAttribute("organizationName");
		SenderEmailDetailsDAO senderDao = new SenderEmailDetailsDAO();
		SenderEmailDetailsDomain senderDom = (SenderEmailDetailsDomain) senderDao.senderdetails();
		emailid = senderDom.getEmailid();
		password = senderDom.getPassword();
		host = senderDom.getHost();
		pop = senderDom.getPop();
		port = senderDom.getPort();

		// session.removeAttribute("MsgValue");

		// String orgID = request.getParameter("orgid");
		String userID = request.getParameter("data");
		String ksSize1 = request.getParameter("kssize");

		int ksSize = Integer.parseInt(ksSize1);
		//System.out.println("ksSize in jsp+" + ksSize);
		//System.out.println("user in jsp+" + userID);
		//System.out.println("user in jsp2+" + userID);

		String staTus = request.getParameter("status");

		aksDomain.setOrgId(session.getAttribute("orgid").toString());
		aksDomain.setUserId(userID);
		aksDomain.setCreatedBy(session.getAttribute("userid").toString());
		aksDomain.setKsSize(ksSize);
		aksDomain.setStatus(staTus);

		session.setAttribute("usersID", userID);

		System.out.println("orgid " + session.getAttribute("orgid").toString());
		System.out.println("setUserId " + aksDomain.getUserId());
		System.out.println("getCreatedBy " + aksDomain.getCreatedBy());
		System.out.println("kssize " + aksDomain.getKsSize());
		System.out.println("orgid " + aksDomain.getStatus());

		// ArrayList list = selectIdDao.fetchValue(aksDomain);

		// SuperAdminSetupknowStoreDao ksDao = new
		// SuperAdminSetupknowStoreDao();

		// String retStatus = ksDao.insertAllInformation(aksDomain);
		String user1 = session.getAttribute("userid").toString();
		String allfacultySize = ksDao.selectAllFacultySpace(user1);
		// System.out.println("allAdminSize in db:" + allAdminSize);

		int allFacultySizeInt = 0;
		if (allfacultySize != null) {

			allFacultySizeInt = Integer.parseInt(allfacultySize);
		}
		// System.out.println("allAdminSizeInt +++" + allAdminSizeInt);

		String AdminSize = ksDao.selectAdminSpace(user1);
		
		System.out.println("AdminSize="+AdminSize);
		/*if(AdminSize==null ||AdminSize=="")
			AdminSize="0";*/
		int AdminSizeInt = Integer.parseInt(AdminSize);
		
		System.out.println("AdminSizeInt="+AdminSizeInt);

		// System.out.println("superAdminSize:" + superAdminSize);

		int remainingSize = (AdminSizeInt - allFacultySizeInt);
		// System.out.println("the operation is" + (remainingSize =
		// superAdminSizeInt - allAdminSizeInt));
		// System.out.println("remainingSize:" + remainingSize);
		String adminUsers = aksDomain.getUserId();

		adminUsers = adminUsers.substring(0, adminUsers.length() - 1);
		System.out.println("adminUsers :" + adminUsers);
		String[] arrayUsers = adminUsers.split(",");
		int lengthUsers = arrayUsers.length;
		System.out.println("Length of the array:" + lengthUsers);
		// /int newSize = 0;
		if (lengthUsers > 1) {
			ksSize = lengthUsers * ksSize;
		}
		System.out.println("Final ksSize:" + ksSize);

		int count = 0;
		String subject = "knowledge Store space allocation";
		String message = "";
		int size = aksDomain.getKsSize();
		if (remainingSize >= ksSize) {

			for (String user : arrayUsers) {
				System.out.println("user="+user);
				
				String retStatus = ksDao.insertAllInformation(aksDomain, user);

				if ("success".equals(retStatus)) {

					String to = ksDao.getEmailId(user);
					 /*
					   "Hi XYZ, 

		You have been provided 1GB space in Knowledge store by your Admin.

		Thanks and Regards
		Anna University

		For any technical assistance please contact on the details provided below:
		Toll Free No: 1800123456
		SMS: 
		Email ID: techicalsupport@kompaceducation.com"

					  */
					message = "Hi " + user + ",<br/><br/>" + "You have been provided " + size+ " GB space in Knowledge store by your Admin."+"<br/><br/>Thanks and Regards<br/>" +teachingSourceName+ "<br/><br/>" + "For any technical assistance please contact on the details provided below:" + "<br/>"
							 + "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
					System.out.println("size=" + size);
					System.out.println("message=" + message);
					try {
						Mailer.send(to, subject, message);
					} catch (AddressException e) {

						e.printStackTrace();
					} catch (MessagingException e) {

						e.printStackTrace();
					}

				}
				count++;
				//ksDao.createFolders(user);

			}
			
			System.out.println("count="+count);
			System.out.println("arrayUsers.length="+arrayUsers.length);
			
			if(count==arrayUsers.length)
			{
						String msg = "Knowledge Store setup successful; details sent via e-mail";

						request.setAttribute("SuperAdminSuccess", msg);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/AdminKnowSetup.jsp");
						requestDispatcher.forward(request, response);
						
			}
		} else {
			System.out.println("else running");
			String msg = "Sorry, not adequate space left";
			//session.setAttribute("AdminSizeInt",msg);
			request.setAttribute("AdminSizeInt", msg);
			//response.sendRedirect("../JSP/AdminKnowSetup.jsp");
			RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("../JSP/AdminKnowSetup.jsp");
			requestDispatcher1.forward(request, response);
		}

	}

	// }

	public void sendMail(String host1, String to) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", pop);
		props.put("mail.smtp.starttls.enable", pop);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailid, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailid));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Knowledge Store space allocation");
			message.setContent(message, "text/html; charset=utf-8");
			
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
