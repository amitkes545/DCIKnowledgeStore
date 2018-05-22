package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
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

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.OwnerSelectUserIDKStoreDao;
import com.kds.KODE_DEV.dao.OwnerSetUpKnowStoreDao;
import com.kds.KODE_DEV.dao.SelectKnowStoreIDDao;
import com.kds.KODE_DEV.dao.SenderEmailDetailsDAO;
import com.kds.KODE_DEV.domain.OwnerSetUpKnowStoreDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

@SuppressWarnings("serial")
public class OwnerSetUpKnowStoreServlet extends CommonService {

	static final Logger LOGGER = Logger
			.getLogger(OwnerSetUpKnowStoreServlet.class);

	OwnerSetUpKnowStoreDomain ksDomain = new OwnerSetUpKnowStoreDomain();

	String userid = "";
	String emailid = "";
	String password = "";
	String host = "";
	String pop = "";
	int port;
	String ksId = "";
	SelectKnowStoreIDDao sksIdDao = new SelectKnowStoreIDDao();
	String teachingSourceName;
	String ksSize;
	ArrayList<String> users_info_array_list=new ArrayList<String>();
	@Override
	public void run() throws ServletException, IOException {

		OwnerSetUpKnowStoreDao ksDao = new OwnerSetUpKnowStoreDao();
		OwnerSelectUserIDKStoreDao selectIdDao = new OwnerSelectUserIDKStoreDao();
		// String message = "";
		 
		
		System.out.println("in servlet");
		session = request.getSession(true);
		teachingSourceName=(String)session.getAttribute("organizationName");
		SenderEmailDetailsDAO senderDao = new SenderEmailDetailsDAO();
		SenderEmailDetailsDomain senderDom = (SenderEmailDetailsDomain) senderDao
				.senderdetails();
		emailid = senderDom.getEmailid();
		password = senderDom.getPassword();
		host = senderDom.getHost();
		pop = senderDom.getPop();
		port = senderDom.getPort();

		String orgId = request.getParameter("orgid");
		//System.out.println("orgId:"+orgId);
		//String orgID = request.getParameter("organizationId");
		////System.out.println("organizationID:"+orgID);
		//orgId=orgID;
		//String usersID = request.getParameter("userid");
	//	String userID=session.getAttribute("userid").toString();
		users_info_array_list=selectIdDao.getuserid(orgId);
	//	System.out.println("usersID:"+users_info_array_list.get(0));
		ksSize = request.getParameter("kssize");
		//System.out.println("ksSize:"+ksSize);
		// int ksSize1 = Integer.parseInt(ksSize);
		String staTus = request.getParameter("status");
		//System.out.println("staTus:"+staTus);
		LOGGER.info("userid for mine=" + users_info_array_list.get(0));

		ksDomain.setOrgId(orgId);
		ksDomain.setUserId(users_info_array_list.get(0));
		//System.out.println("usersID:"+usersID);
		ksDomain.setCreatedBy(session.getAttribute("userid").toString());
		System.out.println("usersID:"+session.getAttribute("userid"));
		ksDomain.setKsSize(ksSize);
		ksDomain.setStatus(staTus);
		session.setAttribute("usersID", users_info_array_list.get(0));
		
		boolean isDuplicate = selectIdDao.ownerDuplicate(ksDomain);
		//System.out.println("isDuplicate:"+isDuplicate);
		LOGGER.info("duplicate value is" + isDuplicate);

		String user1 = session.getAttribute("userid").toString();  
		String allSuperAdminSize = ksDao.selectAllSuperAdminSpace(user1);
		if(allSuperAdminSize==null)  
			allSuperAdminSize="0"; 
		int allsize=Integer.parseInt(allSuperAdminSize);
		int ownersize=ksDao.selectOwnerKnowSpace(); 
		int remainingSize = (ownersize - allsize);
		String ksSize1 = request.getParameter("kssize"); 
		int ksSize = Integer.parseInt(ksSize1);	
		
		System.out.println("allsize "+allsize);
		System.out.println("remainingSize "+remainingSize);
		System.out.println("ksSize "+ksSize);
		if (isDuplicate) {
			//System.out.println("isduplicate");
			String msg = "User exist ! please try another";
			request.setAttribute("MsgValue", msg);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("../JSP/OwnerKnowSetup.jsp");
			requestDispatcher.forward(request, response);
		} 
		else if(remainingSize<=ksSize)  
		{ 
			
			String msg = "Sorry, not adequate space left"; 
			System.out.println("msg="+msg);
			request.setAttribute("OwnerFailure", msg); 
			RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("../JSP/OwnerKnowSetup.jsp");
			requestDispatcher1.forward(request, response);
			return;
		}
		else {

			/*
			 * if(orgID !=null){ ArrayList<String>
			 * resultUserId=selectIdDao.fetchValue(orgID); String[] resulArr =
			 * new String[resultUserId.size()]; resulArr =
			 * resultUserId.toArray(resulArr);
			 * session.setAttribute("orgid",orgID);
			 * session.setAttribute("resultUserId", resulArr);
			 * response.sendRedirect("../JSP/OwnerKnowSetup.jsp"); }
			 */

			if (orgId != null) {
				//System.out.println("orgID!=null");
				ArrayList<String> resultUserId = selectIdDao.fetchValue(orgId);
				LOGGER.info("script calling in service");
				String[] resulArr = new String[resultUserId.size()];
				resulArr = resultUserId.toArray(resulArr);
				session.setAttribute("orgid", orgId);
				session.setAttribute("resultUserId", resulArr);
				//response.sendRedirect("../JSP/OwnerKnowSetup.jsp");
			//}else{
				//System.out.println("else");
			/*if(request.getParameter("orgid") != null
					&& request.getParameter("userid") != null
					&& request.getParameter("kssize") != null
					&& request.getParameter("status") != null) {
				LOGGER.info("organization id service:"
						+ request.getParameter("orgid") + "user id:"
						+ request.getParameter("userid") + "ksize:"
						+ request.getParameter("kssize"));*/
				String retStatus = ksDao.checkUserCredential1s(ksDomain);

				String ksId = (String) sksIdDao.fetchKnowStoreID(ksDomain);
				String emailId = (String) sksIdDao.fetchEmailId(ksDomain);

				LOGGER.info("KsId is:" + ksId);
				LOGGER.info("Email Id :" + emailId);
				LOGGER.info("retStatus:" + retStatus);

				if ("valid".equals(retStatus)) {
					// LOGGER.info("success");
					//System.out.println("valid");
					String to = ksDomain.getEmailId();
					sendMail(host, to);

					// message="Knowledge Store created successfully"+"#"+"Know Store ID sent to your email ID";

					String msg = "Knowledge Store space successfully allocated";
					request.setAttribute("OwnerSuccess", msg);
					RequestDispatcher rd = request
							.getRequestDispatcher("../JSP/OwnerKnowSetup.jsp");
					rd.forward(request, response);
					// response.sendRedirect("../JSP/OwnerKnowSetup.jsp");
				} else if ("notvalid".equals(retStatus)) {
					//System.out.println("notvalid");
					String msg = "Failed To Create Knowlwdge Store !!";
					request.setAttribute("OwnerFailure", msg);
					RequestDispatcher rd = request
							.getRequestDispatcher("../JSP/OwnerKnowSetup.jsp");
					rd.forward(request, response);
					// response.sendRedirect("../JSP/OwnerKnowSetup.jsp");

				}
			}
		}

	}

	public void sendMail(String host1, String to) {

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
			message.setSubject("Knowledge Store space allocation");
			/*
			  Hi XYZ, 

You have been provided <20 GB> space in Knowledge store by Kompac Education Systems.

Thanks and Regards
Knowledge Store Technical Team

For any technical assistance please contact on the details provided below:
Toll Free No: 1800123456
SMS: 
Email ID: techicalsupport@kompaceducation.com
*/		/*+ "Know Store ID is:" + sksIdDao.fetchKnowStoreID(ksDomain));*/
			 
			String msg="Hi "+users_info_array_list.get(1)+",<br/><br/>"+ "You have been provided "+ksSize+" GB space in Knowledge store by Kompac Education Systems."
			+"<br/><br/>" + "Thanks and Regards" + "<br/>" + "Knowledge Store Technical Team"+"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
			+ "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
				     
			message.setContent(msg, "text/html; charset=utf-8");
			Transport.send(message);
			LOGGER.info("send successfully");
			/*
			 * try {
			 * 
			 * } catch (IOException e) { e.printStackTrace(); }
			 */
		} catch (MessagingException e) {
			//System.out.println(e);
			throw new RuntimeException(e);

		}
	}
}