package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.IOException;
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

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.OwnerSelectUserIDKStoreDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dao.SenderEmailDetailsDAO;
import com.kds.KODE_DEV.dao.SuperAdminSelectKsIDEmailIDDao;
import com.kds.KODE_DEV.dao.SuperAdminSetupknowStoreDao;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.SuperAdminSetupKnowStoreDomain;

@SuppressWarnings("serial")
public class SuperAdminKnowStoreSetupServlet extends CommonService {

	static final Logger LOGGER = Logger
			.getLogger(SuperAdminKnowStoreSetupServlet.class);

	String host = "smtp.gmail.com";
	String userid = "";
	String emailid = "";
	String password = "";
	String pop = "";
	int port;
	String ksId = "";
	String organizationId = "";
	String link = "";
	SuperAdminSelectKsIDEmailIDDao saskeDao = new SuperAdminSelectKsIDEmailIDDao();
	SuperAdminSetupKnowStoreDomain aksDomain = new SuperAdminSetupKnowStoreDomain();
	SuperAdminSetupknowStoreDao ksDao = new SuperAdminSetupknowStoreDao();
	String teachingSourceName;
	@Override
	public void run() throws ServletException, IOException {

		session = request.getSession();
		teachingSourceName=(String)session.getAttribute("organizationName");
		SendEmailDao senderLinkDao =new SendEmailDao();
		organizationId = session.getAttribute("orgId").toString();
		OwnerSelectUserIDKStoreDao selectIdDao = new OwnerSelectUserIDKStoreDao();
		link = senderLinkDao.getWebLink();
		SenderEmailDetailsDAO senderDao = new SenderEmailDetailsDAO();
		SenderEmailDetailsDomain senderDom = (SenderEmailDetailsDomain) senderDao
				.senderdetails();
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
		System.out.println("ksSize in jsp+" + ksSize);
		System.out.println("user in jsp+" + userID);
		System.out.println("user in jsp2+" + userID);

		String staTus = request.getParameter("status");

		aksDomain.setOrgId(session.getAttribute("orgid").toString());
		aksDomain.setUserId(userID);
		aksDomain.setCreatedBy(session.getAttribute("userid").toString());
		aksDomain.setKsSize(ksSize);
		aksDomain.setStatus(staTus);

		session.setAttribute("usersID", userID);
		boolean isDuplicate = selectIdDao.superadminDuplicate(aksDomain);
		LOGGER.info("duplicate value is" + isDuplicate);

		if (isDuplicate) {
			String msg3 = "User exit ! please try another";
			session.setAttribute("MsgValue5", msg3);
			response.sendRedirect("../JSP/SuperUserAdminKnowSetup.jsp");
		} else {

			// SuperAdminSetupknowStoreDao ksDao = new
			// SuperAdminSetupknowStoreDao();

			// String retStatus = ksDao.insertAllInformation(aksDomain);
			String user1 = session.getAttribute("userid").toString();
			String allAdminSize = ksDao.selectAllAdminSpace(user1);
			//System.out.println("allAdminSize in db:" + allAdminSize);
			
			
			int allAdminSizeInt = 0;
			if(allAdminSize != null){
			
			 allAdminSizeInt = Integer.parseInt(allAdminSize);
			}
			//System.out.println("allAdminSizeInt +++" + allAdminSizeInt);

			String superAdminSize = ksDao.selectSuperAdminSpace(user1);
			if(superAdminSize==null ||superAdminSize=="")
				superAdminSize="0";
			int superAdminSizeInt = Integer.parseInt(superAdminSize);

			//System.out.println("superAdminSize:" + superAdminSize);

			int remainingSize = (superAdminSizeInt - allAdminSizeInt);
			//System.out.println("the operation is"	+ (remainingSize = superAdminSizeInt - allAdminSizeInt));
			//System.out.println("remainingSize:" + remainingSize);
			String adminUsers = aksDomain.getUserId();
			
			adminUsers = adminUsers.substring(0, adminUsers.length() -1);
			System.out.println("adminUsers :"+adminUsers);
			String[] arrayUsers = adminUsers.split(",");
			int lengthUsers =  arrayUsers.length;
			System.out.println("Length of the array:"+lengthUsers);
//			/int newSize = 0;
			int count = 0;
			String message = "";
			String subject = "Knowledge Store Space Allocation";
			if(lengthUsers > 1) {
				ksSize = lengthUsers*ksSize;
			} 
			System.out.println("Fional ksSize:"+ksSize);
			
			

			if (remainingSize >= ksSize) {

				for (String user : arrayUsers) {
					String retStatus = ksDao.insertAllInformation(aksDomain, user);

					if ("success".equals(retStatus)) {
						String to = ksDao.getEmailId(user);
				     /*message = "Hello : " + user + ",<br/><br/>" + "You got the Knowlege store size " + aksDomain.getKsSize()
				    	       + " by your Admin";*/
					message = "Succeessfully assigned space";
				    	     System.out.println("size=" + aksDomain.getKsSize());
				    	      sendMail(to, host, user);
				    	    }
				    	    count++;
				    	    //createFolders(user);
				}
				System.out.println("count "+count);
				System.out.println("arrayUsers.length "+arrayUsers.length);
				if(count==arrayUsers.length)
				   {
					String msg = "KnowStore space allocated and sent via e-mail";

					request.setAttribute("SuperAdmin", msg);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/SuperUserAdminKnowSetup.jsp");
					requestDispatcher.forward(request, response);
				      
				   }
			} else {

				String msg = "Sorry, not adequate space left..";
				request.setAttribute("SuperAdminSize", msg);
				response.sendRedirect("../JSP/SuperUserAdminKnowSetup.jsp");
			}
		}

	}
	
	/*public void createFolders(String userid)
	 {
	   File files = new File("C:\\KES\\kds\\guna\\"+userid);
	         if (!files.exists()) {
	             if (files.mkdirs()) {
	                 System.out.println("Multiple directories are created!");
	             } else {
	                 System.out.println("Failed to create multiple directories!");
	             }
	         }
	 }*/
	
	private void sendMail( String to, String host, String user) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.starttls.enable", pop);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailid, password);
			}
		});
		try {
			String msg="Hi " + user + ",<br/><br/>" + "Welcome to KODE_DEV !!<br/><br/>" + "Please find your Institution ID: "
					+ organizationId + "<br/><br/>You have been allotted " + aksDomain.getKsSize() + "GB " + "space by " + aksDomain.getCreatedBy() + "."
					+ "Kindly login into the URL to start accessing the product:" + "<br/>"
					+ "<a href='https://www.kompacdigit.com:8443/KODE_DEV/'>https://www.kompacdigit.com:8443/KODE_DEV/</a>" + "<br/><br/>" + "Thanks and Regards" + "<br/>" + teachingSourceName+"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
					+   "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
		
			// System.out.println("in try");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailid));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Space Allocation"); // Now set the actual
													// message
			/*
			 * "Hi XYZ,

Welcome to KODE_DEV !!

Please find your Institution ID:
Admin ID:
Password:

You have been allotted <1GB> space by <User>. Kindly login into the URL to start accessing the product:
http://www.kompacdigit.com:8443/KODE_DEV/

Thanks and Regards
Anna University

For any technical assistance please contact on the details provided below:
Toll Free No: 1800123456
SMS: 
Email ID: techicalsupport@kompaceducation.com"

			 */
		message.setContent(msg, "text/html; charset=utf-8");
		Transport.send(message);
			LOGGER.info("send successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
