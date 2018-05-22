package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javax.servlet.http.HttpSession;

import com.kds.KODE_DEV.dao.CourseMappingDao;
import com.kds.KODE_DEV.dao.GetAdminsForOrgDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dao.SenderEmailDetailsDAO;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

import android.provider.ContactsContract.CommonDataKinds.Organization;

@SuppressWarnings("serial")
public class AdminCourseMappingServlet extends CommonService{

	String host = "smtp.gmail.com";
	String to="";
	int port = 465;
	String emailid = "";
	String password = "Kompac123";
	String user = "";
	String organizationId = "";
	String courseId = "";
	String userSuper = "";
	String link ="";
	String teachingSourceName;
	
	@Override
	public void run() throws ServletException, IOException {
		HttpSession session = request.getSession();
		teachingSourceName=(String)session.getAttribute("organizationName");
		GetAdminsForOrgDao getAdmin = new GetAdminsForOrgDao();
		CourseMappingDao mappingDao = new CourseMappingDao();
		Connection con = null;
		PreparedStatement prepareStatement = null;
		try {
			con = DBTransaction.connect();
			String insertQuery = "insert into superadmin_admin_mapping (organization_id, super_admin_user_id, admin_user_id, course_id_defined_by_teaching_source) values (?,?,?,?)";
			prepareStatement = con.prepareStatement(insertQuery);
			// System.out.println("insert users info="+insertQuery);
			// LOGGER.info("the quary is:"+sql);
			SenderEmailDetailsDAO senderDao = new SenderEmailDetailsDAO();
			SenderEmailDetailsDomain senderDom = (SenderEmailDetailsDomain) senderDao
					.senderdetails();
			emailid = senderDom.getEmailid();
			user = request.getParameter("admin");
			organizationId = session.getAttribute("orgid").toString();
			courseId = request.getParameter("course");
			userSuper = session.getAttribute("userid").toString();
			System.out.println("userSuper :"+userSuper);
			System.out.println("courseId :"+courseId);
		    boolean isDuplicate = mappingDao.getDuplicateCourseMapping(session.getAttribute("userid").toString(), request.getParameter("course"), organizationId);
			if(isDuplicate) {
				
				String mapError = "Course already mapped";
				request.setAttribute("mapFailure", mapError);
				RequestDispatcher rd = request.getRequestDispatcher("../JSP/AdminCouseConfiguration.jsp");
				rd.forward(request, response);
				
			} else {
			
			prepareStatement.setString(1, session.getAttribute("orgid").toString());
			prepareStatement.setString(2, session.getAttribute("userid").toString());
			prepareStatement.setString(3, request.getParameter("admin"));
			prepareStatement.setString(4, request.getParameter("course"));
			int p = prepareStatement.executeUpdate();
			System.out.println("insert users info"+p);
			// prepareStatement="+prepareStatement);
			if (p != 0) {
				// System.out.println("before sending setup info");
				to = getAdmin.getAdminEmail(request.getParameter("admin"), session.getAttribute("orgid").toString());
				SendEmailDao sendEmailDao =new SendEmailDao();
				sendEmailDao.getWebLink();
				link = sendEmailDao.getWebLink();
				System.out.println("Email :"+to);
				sendMail(host, to,link);
				String OrgStatus = "Admin mapped successfully to the course";
				request.setAttribute("SuperAdminSuccess", OrgStatus);
				RequestDispatcher rd = request.getRequestDispatcher("../JSP/AdminCouseConfiguration.jsp");
				rd.forward(request, response);
			}

			else {
				String OrgStatus = "Admin is already  mapped to the course";
				request.setAttribute("SuperAdminFail", OrgStatus);
				RequestDispatcher rd = request.getRequestDispatcher("../JSP/AdminCouseConfiguration.jsp");
				rd.forward(request, response);
			  }
			}
		} catch (Exception e) {
			String mapErrorConstraint = "Course already mapped to user";
			request.setAttribute("mapFailureConstraint", mapErrorConstraint);
			RequestDispatcher rd = request.getRequestDispatcher("../JSP/AdminCouseConfiguration.jsp");
			rd.forward(request, response);
		} finally {
			try {
				con.close();
				prepareStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void sendMail(String host1, String to,String link) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.starttls.enable", pop);
		props.put("mail.smtp.host", host1);
		props.put("mail.smtp.socketFactory.port", host1);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailid, password);
			}
		});
		// System.out.println("in sending");
		try {
			String msg="Hi " + user + ",<br/><br/>" + "Welcome to KODE_DEV !!<br/><br/>" + "Please fine your Institution ID: "
					+ organizationId + ",<br/>" + "You have been assigned to: " + courseId + " course by " +userSuper+ ".<br/>"+ "Kindly login into the URL to start accessing the product:" + "<a href='https://www.kompacdigit.com:8443/KODE_DEV/'>https://www.kompacdigit.com:8443/KODE_DEV/</a>"
					+ "<br/><br/>" + "Thanks and Regards" + "<br/>" + teachingSourceName+"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
					  +   "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
		
			// System.out.println("in try");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailid));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Assigned for course");
			/*"Hi XYZ,

			Welcome to KODE_DEV !!

			Please find your Institution ID:
			You have been assigned to: <CS01>  course by <user>.
			Kindly login into the URL to start accessing the product:
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
			//LOGGER.info("send successfully");
			/*
			 * try { response.sendRedirect("../JSP/success.jsp"); } catch
			 * (IOException e) { e.printStackTrace(); }
			 */
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
