package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import com.kds.KODE_DEV.domain.SubTopicInfoDomain;

@SuppressWarnings("serial")
public class AdminFacultyCourseMappingServlet extends CommonService{

	String to="";
	String emailid = "";
	String password = "";
	String host = "";
	String pop = "";
	int port;
	//String emailid = "kompaceducation@gmail.com";
	//String password = "Kompac123";
	String user = "";
	String organizationId = "";
	String courseId = "";
	String userSuper = "";
	String link ="";
	String teachingSourceName;
	@Override
	public void run() throws ServletException, IOException {
		SenderEmailDetailsDAO senderEDao = new SenderEmailDetailsDAO();
		SenderEmailDetailsDomain senderDom = (SenderEmailDetailsDomain) senderEDao
				.senderdetails();
		emailid = senderDom.getEmailid();
		password = senderDom.getPassword();
		host = senderDom.getHost();
		pop = senderDom.getPop();
		port = senderDom.getPort();
		
		HttpSession session = request.getSession();
		teachingSourceName=(String)session.getAttribute("organizationName");
		CourseMappingDao mappingDao = new CourseMappingDao();
		Connection con = null;
		PreparedStatement prepareStatement = null;
		try {
			con = DBTransaction.connect();
			String insertQuery = "insert into admin_faculty_mapping (admin_user_id, faculty_user_id, faculty_subject_id, faculty_topic_id, faculty_sub_topic_id, course_id_defined_by_teaching_source,organization_id) values (?,?,?,?,?,?,?)";
			prepareStatement = con.prepareStatement(insertQuery);
			// System.out.println("insert users info="+insertQuery);
			// LOGGER.info("the quary is:"+sql);
			user = request.getParameter("faculty");
			organizationId = session.getAttribute("orgid").toString();
			courseId = request.getParameter("course");
			userSuper = session.getAttribute("userid").toString();
			
			System.out.println("Faculty "+request.getParameter("faculty"));
			System.out.println("Course "+request.getParameter("course"));
			System.out.println("Subject "+request.getParameter("subject"));
			System.out.println("Topic "+request.getParameter("topic"));
			System.out.println("Sub Topic "+request.getParameter("subtopic"));
			System.out.println("Sub Topic hidden :"+request.getParameter("subtopicdata"));
			
			
			 String subTopicsSelected = request.getParameter("subtopicdata").substring(0, request.getParameter("subtopicdata").length()-1);
			 String[] selectedArray = subTopicsSelected.split(",");
			 for(String subTopic : selectedArray) {
				  
				    SubTopicInfoDomain subTopicInfo =  mappingDao.getSubTopicDetails(subTopic);
				 
				    prepareStatement.setString(1, session.getAttribute("userid").toString());
					prepareStatement.setString(2, request.getParameter("faculty"));
					prepareStatement.setString(3, request.getParameter("subject"));
					prepareStatement.setString(4, subTopicInfo.getTopicId());
					prepareStatement.setString(5, subTopicInfo.getSubTopicId());
					prepareStatement.setString(6, request.getParameter("course"));
					prepareStatement.setString(7, organizationId);
					System.out.println("prepareStatement "+prepareStatement);
					int p = prepareStatement.executeUpdate();

			 }
			System.out.println("Organization ID; "+session.getAttribute("orgid").toString());
			to = mappingDao.getFacultyEmail(request.getParameter("faculty"), session.getAttribute("orgid").toString());
			SendEmailDao senderDao =new SendEmailDao();
			senderDao.getWebLink();
			link = senderDao.getWebLink();
			System.out.println("Email :"+to);
			sendMail(host, to,link);
			String OrgStatus = "Faculty successfully mapped to the subject";
			request.setAttribute("AdminFacultySuccess", OrgStatus);
			System.out.println("orgstatus for success="+OrgStatus);
			RequestDispatcher rd = request.getRequestDispatcher("../JSP/AdminFacultyCouseConfiguration.jsp");
			rd.forward(request, response);
		
			
			
			/*else {
				String OrgStatus = "Failure for assigning course";
				request.setAttribute("SuperAdminFail", OrgStatus);
				RequestDispatcher rd = request.getRequestDispatcher("../JSP/AdminFacultyCouseConfiguration.jsp");
				rd.forward(request, response);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			String OrgStatus = "Faculty already mapped to the subject";
			System.out.println("orgstatus for failure="+OrgStatus);
			request.setAttribute("AdminFacultyFail", OrgStatus);
			RequestDispatcher rd = request.getRequestDispatcher("../JSP/AdminFacultyCouseConfiguration.jsp");
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
	
	public void sendMail(String host1, String to, String link) {

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
		 System.out.println("in sending");
		try {
			String msg="Hi " + user + ",<br/><br/>" + "Welcome to KODE_DEV !!<br/><br/>" + "Please find your Institution ID: "
					+ organizationId + "<br/>" + "You have been assigned to: " + courseId + " course for: "+request.getParameter("subject")+" subject by " +userSuper+ ".<br/>"+ "Kindly login into the URL to start accessing the product:" + "<br/>" +"<a href='https://www.kompacdigit.com:8443/KODE_DEV/'>https://www.kompacdigit.com:8443/KODE_DEV/</a>"
					+  "<br/><br/>" + "Thanks and Regards" + "<br/>" + teachingSourceName+"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
					+   "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
			
			 System.out.println("in try");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailid));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			// message.setSubject("Testing");
			// message.setText("UserID:"+emailid+ "Password:"+password);
			//message.setSubject("Course Alloted"); // Now set the actual
													// message
			message.setSubject("Mapped for below subjects");
			/*
			 * "Hi XYZ,

Welcome to KODE_DEV !!

Please find your Institution ID:
You have been assigned to: <CS01>  course for: <Java> subject by <Admin>. Kindly login into the URL to start accessing the product: http://www.kompacdigit.com:8443/KODE_DEV/

Thanks and Regards
Anna University

For any technical assistance please contact on the details provided below:
Toll Free No: 1800123456
SMS: 
Email ID: techicalsupport@kompaceducation.com"

			 */
			// message.setText("SessionStartTime:"+SessionStartTime);
			// message.setText("SessionEndTime:"+SessionEndTime);
			// message.setText("Duration :"+hours);
			// LOGGER.info();
//			 System.out.println("text= Hi " + user + " ,<br/>" + "Welcome to KODE_DEV!!!<br/>" + "Your Institution ID :"
//					+ organizationId + ",<br/>" + "You have been assigned to the course :" + courseId + " for Subject: "+request.getParameter("subject")+" by " +userSuper+ ",<br/>"+ "Kindly login into the below URL to start accessing it:<br/>" + ""
//					+ "https://localhost:8443/KODE_DEV/" + "<br/><br/>" + "Thanks & Regards" + "<br/>" + "Kompac Education Systems Pvt. Ltd." + "<br/>"
//					+ "Website : www.kompaceducation.com");
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
