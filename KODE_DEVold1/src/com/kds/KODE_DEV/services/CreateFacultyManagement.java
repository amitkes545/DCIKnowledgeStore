package com.kds.KODE_DEV.services;

import java.io.IOException;

import com.kds.KODE_DEV.dao.*;
import com.kds.KODE_DEV.domain.*;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.services.Mailer;

@SuppressWarnings({ "serial", "unused" })
// @MultipartConfig(maxFileSize = 16177215)
public class CreateFacultyManagement extends CommonService {

	String name1 = "", name = "", email = "", phone = "", dob = "",
			gender = "", country = "", state = "", city = "", address = "",
			pincode = "", userid = "", privilege = "Faculty", created_by = "",
			users_status = "Active", organization_id = "",
			genaratePassword = "";
	java.sql.Date sqlDate;
	String teachingSourceName=null;
	static final Logger LOGGER = Logger
			.getLogger(CreateFacultyManagement.class);

	@Override
	public void run() throws ServletException, IOException {

		HttpSession session = request.getSession();
		created_by = (String) session.getAttribute("userid");
		organization_id = (String) session.getAttribute("orgId");
		teachingSourceName=(String)session.getAttribute("organizationName");
		//System.out.println("teachingSourceName "+ teachingSourceName);
		Connection dbCon = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload pic = new ServletFileUpload(factory);
			java.util.List<FileItem> items = pic.parseRequest(request);

			FileItem facultyname = (FileItem) items.get(0);
			name1 = facultyname.getString();
			name = removeSpaceFromString(name1);
			System.out.println("name=" + name);

			FileItem email = (FileItem) items.get(1);
			String emailid = email.getString();
			System.out.println("emailid=" + emailid);

			FileItem phoneno = (FileItem) items.get(2);
			String phone = phoneno.getString();
			System.out.println("phone=" + phone);

			FileItem dateofbirth = (FileItem) items.get(3);
			String dob = dateofbirth.getString();
			System.out.println("dob=" + dob);

			FileItem gen = (FileItem) items.get(4);
			String gender = gen.getString();
			System.out.println("gender=" + gender);

			FileItem countryname = (FileItem) items.get(5);
			String country = countryname.getString();
			System.out.println("country=" + country);

			FileItem statename = (FileItem) items.get(6);
			String state = statename.getString();
			System.out.println("state=" + state);

			FileItem cityname = (FileItem) items.get(7);
			String city = cityname.getString();
			System.out.println("city=" + city);

			FileItem addr = (FileItem) items.get(8);
			String address = addr.getString();
			System.out.println("address=" + address);

			FileItem pin = (FileItem) items.get(9);
			String pincode = pin.getString();
			System.out.println("address=" + pincode);

			String password = randomSeriesForThreeCharacter();

			genaratePassword = password + name.substring(0, 3);

			DateFormat  formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			InsertStudentDao insertStudentDao=new InsertStudentDao();
	       	 boolean email_conflict=insertStudentDao.email_conflict(emailid);
	  	     if(email_conflict==true)
	  	        {  
	  	    	 String Studentymsg="Email ID already exists.";
	  				request.setAttribute("OwnerFailure",Studentymsg);
	  				RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateFaculty.jsp");
	  				rd.forward(request, response);
	  				return;
	  	        }
	  	     boolean phone_conflict=insertStudentDao.phone_conflict(phone);
	  	     if(phone_conflict==true)
	  	        {  
	  	    	 String Studentymsg="Mobile No. already exists.";
	  				request.setAttribute("OwnerFailure",Studentymsg);
	  				RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateFaculty.jsp");
	  				rd.forward(request, response);
	  				return;
	  	        }
	  	     
		    try {
		    
		        java.util.Date date = formatter.parse(dob);
		        System.out.println("date="+date);
		        sqlDate = new java.sql.Date(date.getTime());
		   
		        System.out.println("sqlDate="+sqlDate);
		
		       
		    } catch (Exception e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		    
			dbCon = DBTransaction.connect();
			st = dbCon.createStatement();
			//Connection con = DBTransaction.connect();
			String insertQuery = "insert into users_info(username,user_id,password,email,address,contact_no,privilege,organization_id,created_by,country,state,city,postal_code,users_status,gender,dateofbirth,space_uom) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println(insertQuery);
			PreparedStatement prepareStatement = dbCon
					.prepareStatement(insertQuery);
			String userid = "";

			for (int i = 0; i < 3; i++)
				userid += name.charAt(i);
			for (int i = 0; i < 3; i++)
				userid += emailid.charAt(i);
			for (int i = phone.length() - 1, c = 0; c < 3; i--, c++)
				userid += phone.charAt(i);

			userid=removeSpaceFromUserid(userid);
			LOGGER.info("the quary is:" + insertQuery);
			System.out.println("data is going to insert");
			prepareStatement.setString(1, name);
			prepareStatement.setString(2, userid);
			prepareStatement.setString(3, genaratePassword);
			prepareStatement.setString(4, emailid);
			prepareStatement.setString(5, address);
			prepareStatement.setString(6, phone);
			prepareStatement.setString(7, privilege);
			prepareStatement.setString(8, organization_id);
			prepareStatement.setString(9, created_by);
			prepareStatement.setString(10, country);
			prepareStatement.setString(11, state);
			prepareStatement.setString(12, city);
			prepareStatement.setString(13, pincode);
			prepareStatement.setString(14, users_status);
			prepareStatement.setString(15, gender);
			prepareStatement.setDate(16, sqlDate);
			prepareStatement.setString(17,"GB" );
			System.out.println("query=" + prepareStatement);
			String subject = "Credentials for accessing DCI";
			//String getTeachingSourceName= ;
			
			/*
			 * "Hi XYZ, 

You have been successfully created as the faculty in DCI. Please find your login credentials below;

User ID: User ID
Password: Password
DCI URL -  https://www.kompacdigit.com:8443/KODE_DEV

Thanks and Regards
Anna University

For any technical assistance please contact on the details provided below:
Toll Free No: 1800123456
SMS: 
Email ID: techicalsupport@kompaceducation.com"

			 */
			int p = prepareStatement.executeUpdate();
//			System.out.println("organization_id" + organization_id);
//			rs=st.executeQuery("select organization_name from org_details where organization_id='"+organization_id+"'");
//			if(rs.next()){
//			teachingSourceName=rs.getString("organization_name");
//			}
			
			String message = "Hi " + name + ",<br/><br/>"
					+ "You have been successfully created as the Faculty in DCI. Please find your login credentials below;" + "<br/><br/>"
					+ "User ID: " + userid + "<br/>"
					+ "Password: " + genaratePassword+" <br/>DCI URL - <a href='https://www.kompacdigit.com:8443/KODE_DEV/'>https://www.kompacdigit.com:8443/KODE_DEV/</a>"+ "<br/><br/>Thanks and Regards" + "<br/>" + teachingSourceName +"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
					+ "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
			
			System.out.println("query=" + prepareStatement);
			if (p != 0) {
				Mailer.send(emailid, subject, message);
				System.out.println("before sending setup info");

				String OrgStatus = "Faculty creation successful; credentials shared via e-mail";
				request.setAttribute("OwnerSuccessMAil", OrgStatus);
				RequestDispatcher rd = request
						.getRequestDispatcher("../JSP/CreateFaculty.jsp");
				rd.forward(request, response);
			} else {
				String OrgStatus = "Failure for creating Faculty";
				request.setAttribute("OwnerFailure", OrgStatus);
				RequestDispatcher rd = request
						.getRequestDispatcher("../JSP/CreateFaculty.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			String OrgStatus = "Failure for creating Faculty";
			request.setAttribute("OwnerFailure", OrgStatus);
			RequestDispatcher rd = request
					.getRequestDispatcher("../JSP/CreateFaculty.jsp");
			rd.forward(request, response);
			
		}
		finally{
			
			DBTransaction.closeConnection(dbCon);
			
		}

	}

	private String removeSpaceFromString(String str) {
		if (str != null)
			str = str.replaceAll("\\s+", " ").trim();
		return str;
	}
	
	private String removeSpaceFromUserid(String str) {
		if (str != null)
			str = str.replaceAll("\\s+","");
		return str;
	}

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

}
