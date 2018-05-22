package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.InsertStudentDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dao.SuperAdminDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

@SuppressWarnings("serial")
public class CreateSetupAdmin extends CommonService {

	String host = "smtp.gmail.com";
	//String pop = ;
	int port = 465;
	String emailid = "kompaceducation@gmail.com";
	String password = "Kompac123";
	String to = "";
	String organization_name = "";
	String organization_id = "";
	String link = "", superAdminID = "", Org_Type_Name = "", genaratePassword = "";
	String user = "";
	String userIdGenerated = "";
	String teachingSourceName="";
	 java.sql.Date sqlDate;
	 String uid="";
	 
	// method for password created ramdomly`
	public static String randomSeriesForThreeCharacter() {
		LOGGER.info("calling password method");
		Random random = new Random();
		LOGGER.info("random charactor:" + random);
		String value = "";
		int random_Char;
		for (int i = 0; i < 2; i++) {
			random_Char = random.nextInt(74);
			value = value + random_Char;
		}
		return value;
	}

	static final Logger LOGGER = Logger.getLogger(CreateSetupAdmin.class);

	@Override
	public void run() throws ServletException, IOException {
		HttpSession session = request.getSession();
		teachingSourceName=(String)session.getAttribute("organizationName");
		organization_id = session.getAttribute("orgId").toString();
		user = removeSpaceFromString(request.getParameter("adminname"));
		SuperAdminDao adminDao = new SuperAdminDao();
		
		try {
			
			SendEmailDao senderDao =new SendEmailDao();
			link = senderDao.getWebLink();
			System.out.println(session.getAttribute("previlege").toString());
			InsertStudentDao insertStudentDao=new InsertStudentDao();
       	 boolean email_conflict=insertStudentDao.email_conflict(request.getParameter("emailid"));
  	     if(email_conflict==true)
  	        {  
  	    	 String Studentymsg="Email ID already exists.";
  				request.setAttribute("OwnerFailure",Studentymsg);
  				RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateAdmin.jsp");
  				rd.forward(request, response);
  				return;
  	        }
  	     boolean phone_conflict=insertStudentDao.phone_conflict(request.getParameter("phone"));
  	     if(phone_conflict==true)
  	        {  
  	    	 String Studentymsg="Mobile No. already exists.";
  				request.setAttribute("OwnerFailure",Studentymsg);
  				RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateAdmin.jsp");
  				rd.forward(request, response);
  				return;
  	        }

			boolean isDuplicate = adminDao.isAdminUserAvailable(request.getParameter("emailid"), session.getAttribute("userId").toString(), session.getAttribute("previlege").toString());
			if(!isDuplicate) {
				System.out.println("session owner "+session.getAttribute("orgId"));
				System.out.println("session owner "+session.getAttribute("createdBy"));
				String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
				LOGGER.info(timeStamp);
				Connection con = DBTransaction.connect();
				String password = randomSeriesForThreeCharacter();
				
				String datepickers=request.getParameter("datepicker");
				   DateFormat  formatter = new SimpleDateFormat("dd/MM/yyyy");
				         		
				 			    try {
				 			    
				 			        java.util.Date date = formatter.parse(datepickers);
				 			        System.out.println("date="+date);
				 			        sqlDate = new java.sql.Date(date.getTime());
				 			   
				 			        System.out.println("sqlDate="+sqlDate);
				 			
				 			       
				 			    } catch (Exception e) {
				 			        // TODO Auto-generated catch block
				 			        e.printStackTrace();
				 			    }
				 			    
				genaratePassword = password+user.substring(0, 3);
				LOGGER.info("password:" + password + "string with password:" + genaratePassword);
				String sql = "insert into users_info(username, user_id, password, email, address, contact_no, privilege, organization_id,created_by,country,state,city,postal_code,users_status,gender,dateofbirth,space_uom) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				//// System.out.println("insert org details="+sql);
				System.out.println("the quary is:" + sql);
				System.out.println("username "+request.getParameter("adminname"));
				System.out.println("username "+request.getParameter("emailid"));
				System.out.println("username user id "+session.getAttribute("userId"));
				userIdGenerated = generateUserId(user, request.getParameter("phone"), request.getParameter("emailid"));
				userIdGenerated=removeSpaceFromUserid(userIdGenerated);
				preparedStatement.setString(1, user);
				preparedStatement.setString(2, userIdGenerated);
				preparedStatement.setString(3, genaratePassword);
				preparedStatement.setString(4, request.getParameter("emailid"));
				preparedStatement.setString(5, request.getParameter("address"));
				preparedStatement.setString(6, request.getParameter("phone"));
				preparedStatement.setString(7, "Admin");
				preparedStatement.setString(8, (String) session.getAttribute("orgId"));
				preparedStatement.setString(9, (String) session.getAttribute("userId"));
				preparedStatement.setString(10, request.getParameter("country"));
				preparedStatement.setString(11, request.getParameter("state"));
				preparedStatement.setString(12, request.getParameter("city"));
				preparedStatement.setString(13, request.getParameter("pcode"));
				preparedStatement.setString(14, "Active");
				preparedStatement.setString(15, request.getParameter("gender"));
				preparedStatement.setDate(16, sqlDate);
				preparedStatement.setString(17, "GB");
				System.out.println("date value");
				int n = preparedStatement.executeUpdate();
				System.out.println("database query:" + preparedStatement);
				System.out.println("integer value:" + n);
				to = request.getParameter("emailid");
				if (n != 0) {

					sendMail(host, to);
					String OrgStatus = "Admin created successfully and details sent via e-mail";
					request.setAttribute("OwnerSuccess", OrgStatus);
					RequestDispatcher rd = request.getRequestDispatcher("../JSP/CreateAdmin.jsp");
					rd.forward(request, response);
				} else {
					String OrgStatus = "Failure for creating admin account";
					request.setAttribute("OwnerFailure", OrgStatus);
					RequestDispatcher rd = request.getRequestDispatcher("../JSP/CreateAdmin.jsp");
					rd.forward(request, response);
				}
			} else {
				String userExists = "User already exists";
				request.setAttribute("UserExists", userExists);
				RequestDispatcher rd = request.getRequestDispatcher("../JSP/CreateAdmin.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			String OrgStatus = "Failure for creating admin account";
			request.setAttribute("OwnerFailure", OrgStatus);
			RequestDispatcher rd = request.getRequestDispatcher("../JSP/CreateAdmin.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}
	
	
	private String generateUserId(String name, String mobile, String email) {
		return name.substring(0, 3)+mobile.substring(0, 3)+email.substring(0, 3);
	}
	
	private  String removeSpaceFromString(String str){
		if(str != null)
		 str=  str.replaceAll("\\s+", " ").trim();
		 return str;		
	}

	public void sendMail(String host1, String to) {

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
		String msg="Hi " + user + ",<br/><br/>" + "Welcome to KODE_DEV !!<br/><br/>" + "Please find your Institution ID: "
				+ organization_id + "<br/>Admin ID: " + userIdGenerated + " " + "<br/>" + "Password: "
				+ genaratePassword + "<br/><br/>" + "Kindly login into the URL to start accessing the product:" + "<br/>"
				+ "<a href='https://www.kompacdigit.com:8443/KODE_DEV/'>https://www.kompacdigit.com:8443/KODE_DEV/</a>" + "<br/><br/>" + "Thanks and Regards" + "<br/>" + teachingSourceName+"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
				  + "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
		try {
			// System.out.println("in try");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailid));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			// message.setSubject("Testing");
			// message.setText("UserID:"+emailid+ "Password:"+password);
			message.setSubject("User Information"); // Now set the actual
													// message
			message.setContent(msg, "text/html; charset=utf-8");
			/*
			 * "Hi XYZ,

Welcome to KODE_DEV !!

Please find your Institution ID:
Admin ID:
Password:

Kindly login into the URL to start accessing the product: http://www.kompacdigit.com:8443/KODE_DEV/

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
			// System.out.println("text=");
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
	private String removeSpaceFromUserid(String str) {
		if (str != null)
			str = str.replaceAll("\\s+","");
		return str;
	}
	
	
}
