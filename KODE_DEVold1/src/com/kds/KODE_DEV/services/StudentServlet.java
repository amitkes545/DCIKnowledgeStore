package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.kds.KODE_DEV.dao.InsertStudentDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;


@SuppressWarnings("serial")
public class StudentServlet extends CommonService {
	//private static final long serialVersionUID = 1L;
	String host="" ;
	String pop="";
	int    port;
	String emailid="";
	String password="";
	String link="",genaratePassword="",classRoomLink="",country="",mobile="",generatedStudentId="",studentName="",studentName1="",
			lastName="",dateofbirth="",generatedStudentId1="";
	
	static final Logger LOGGER = Logger.getLogger(StudentServlet.class);
    public void run() throws ServletException,IOException{
    	session = request.getSession(false);
    	
    	AdminDomain studentDomain=new AdminDomain();
    	InsertStudentDao insertStudentDao=new InsertStudentDao();
    	 CustomerDao customerDao=new CustomerDao();
    	//setting values to domain  
    	 String studID=request.getParameter("studentID");
    	 studentDomain.setAdminName(request.getParameter("studentName"));
    	 studentName1=request.getParameter("studentName");
    	 lastName=request.getParameter("lastName");
	 	 LOGGER.info("faculty id is"+studentName);
	   	 LOGGER.info("faculty id is"+studentName);  
	   	 String phone_no=request.getParameter("phone");
	 	studentDomain.setPhone(phone_no);//request.getParameter("phone"));
	   //	studentDomain.setAdminId(request.getParameter("sid"));
	   //	sessionDomain.setPwd(request.getParameter("pwd"));
	 	String email=request.getParameter("email");
	   	studentDomain.setEmail(email);//request.getParameter("email"));
	   	studentDomain.setGender(request.getParameter("gender"));
	   	dateofbirth=request.getParameter("datepicker");
	   	
	   	java.sql.Date sqlDate = null;
	   	DateFormat  formatter = new SimpleDateFormat("dd/MM/yyyy");
		
	    try {
	        java.util.Date date = formatter.parse(dateofbirth);
	        System.out.println("date="+date);
	        sqlDate = new java.sql.Date(date.getTime());
	   
	        System.out.println("sqlDate="+sqlDate);
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	   	studentDomain.setDateofbirth(sqlDate);//request.getParameter("datepicker"));	  
	   	String country=request.getParameter("country");
	   	studentDomain.setCountry(country);
	   	//System.out.println("country== "+country);
	   	studentDomain.setState(request.getParameter("state"));
	   	studentDomain.setCity(request.getParameter("city"));  
	   	studentDomain.setAddress(request.getParameter("address"));
	   	//System.out.println("#############"+request.getParameter("addressdemoval")+"country== "+request.getParameter("country")
	   			// +" state== "+request.getParameter("state")+" city== "+request.getParameter("city"));
	   	studentDomain.setPostalCode(request.getParameter("pcode"));
	   	//studentDomain.setPrivilege(request.getParameter("privilege"));
	   	/*String ses=(String)session.getAttribute("orgId");
	   	//System.out.println("organizationid================in service  "+ses);*/
	   	String organization=session.getAttribute("orgid").toString();					// change by jitendra
	   	//System.out.println("organizationid================ "+organization+"in service");
	   	studentDomain.setOrgid(organization);										// change by jitendra
	   	studentDomain.setCreated_by(session.getAttribute("userId").toString());	// change by jitendra
	   	//System.out.println("createdBy:==  "+session.getAttribute("userId").toString());
	   	LOGGER.info("user id:"+request.getParameter("createdid"));
	   	LOGGER.info("organization id:"+request.getParameter("organizationId"));
	  /* 	SendEmailDao senderDao =new SendEmailDao();
		//session.removeAttribute("MsgValue");
		SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
		 emailid=senderDom.getEmailid();
		 password=senderDom.getPassword();
		 host= senderDom.getHost();
		 pop=senderDom.getPop();
		   port=senderDom.getPort();
		   link=senderDao.getKnowStoreLink();//selecting web room link from dao
		   classRoomLink=senderDao.getClassRoomLink();*/
		   
		   //generating student id based on name,country and city
		   /*if (request.getParameter("country").length() >= 3){
        	   country =request.getParameter("country").substring(0, 3);
           }*/
		  /* if (studentName1.length() >= 3){
        	   studentName =studentName1.substring(0, 3);
           }*/
		   //////////////////////////generating userid/////////////////////
		   if ((studentName1.length() <= 3)
					&& ((lastName).length() <= 3))
			{
			   studentName = studentName1.toLowerCase()+ lastName.toLowerCase()+dateofbirth.substring(0,
						(6 - (studentName1.toLowerCase().length() + lastName.toLowerCase().length())));
			}
			else if (((studentName1.toLowerCase()).length() <= 3)&&((lastName.toLowerCase()).length()>3)) 
			{
				studentName = studentName1.toLowerCase()+ lastName.toLowerCase().substring(0, 3)+dateofbirth.substring(0,
						(6-(studentName1.toLowerCase().length() + lastName.toLowerCase().substring(0,3).length())));
			} 
			else if(((studentName1.toLowerCase()).length() > 3)&&((lastName.toLowerCase()).length() <= 3))
			{
				studentName = studentName1.toLowerCase().substring(0, 3)
						+ lastName.toLowerCase()+ dateofbirth.substring(0,
			(6 - (studentName1.toLowerCase().substring(0,3).length() + lastName.toLowerCase().length())));
			} 
			else
			{
				studentName = studentName1.toLowerCase().substring(0, 3)
						+ lastName.toLowerCase().substring(0, 3);
			}
		   ////////////////////////////////////////////////////////////////////////////
		   if (request.getParameter("phone").length() >= 3){
        	 //  mobile =request.getParameter("phone").substring(3, 6);
			   String phone=request.getParameter("phone");
			   mobile =phone.substring(0,phone.length()-4);
			   System.out.println("phone="+phone);
			   System.out.println("mobile="+mobile);
           }
		   String randomno= customerDao. randomSeriesForThreeCharacter();
		   String randomnopass= customerDao. randomSeriesForThreeCharacter();
		   String org_first_three= organization.substring(0,3);
		   System.out.println("org_first_three="+org_first_three);
		   //generatedStudentId=studentName+mobile;
		   generatedStudentId=org_first_three+"STU"+mobile;//studentName+mobile;
		   System.out.println("generatedStudentId::"+generatedStudentId);
		  // String password= "kds@"+randomnopass+"#9";
		   String password= randomnopass;
           //String  superAdminname="";
           /*if (studentName.length() >= 3){
        	   studentName =studentName.substring(0, 3);
           }*/
           genaratePassword=password;
           LOGGER.info("new password:"+genaratePassword);
           
           studentDomain.setPwd(genaratePassword);
		   
		   
	   	 //testing student id is exit or not
		   
	   	 boolean isDuplicate = insertStudentDao.duplicate(studID);//generatedStudentId);
	      	 LOGGER.info("duplicate value is"+isDuplicate);
	      	//System.out.println("duplicate value is"+isDuplicate);
	      	
        	//System.out.println("randomno = "+randomno);
	        if(isDuplicate==true)
	        {  
	        	/*generatedStudentId=studentName+randomno;
	        	//System.out.println("generatedStudentId = "+generatedStudentId);
	        boolean isDuplicateAgain = insertStudentDao.duplicateAgain(generatedStudentId);
	        	 if(isDuplicateAgain==true)
	 	        {
	          String msg = "UserId is exist!please try another username";
	   		  request.setAttribute("MsgValue", msg);
	   		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
	   		  rd.forward(request, response);
	 	        }else{
	 	        	studentDomain.setAdminId(generatedStudentId);
	 	        }*/
	        	String Studentymsg="Student ID already exists! Please try another Student ID.";
				request.setAttribute("FacultyFailure",Studentymsg);
				RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
				rd.forward(request, response);
				return;
	         }else{
	        	 studentDomain.setAdminId(studID);//generatedStudentId);
	         }
	       
	   	 boolean email_conflict=insertStudentDao.email_conflict(email);
	     if(email_conflict==true)
	        {  
	    	 String Studentymsg="Email ID already exists.";
				request.setAttribute("FacultyFailure",Studentymsg);
				RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
				rd.forward(request, response);
				return;
	        }
	     boolean phone_conflict=insertStudentDao.phone_conflict(phone_no);
	     if(phone_conflict==true)
	        {  
	    	 String Studentymsg="Mobile No. already exists.";
				request.setAttribute("FacultyFailure",Studentymsg);
				RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
				rd.forward(request, response);
				return;
	        }
	     //inserting student details into database
	   	 String retstatus=insertStudentDao.addStudentDetails(studentDomain);
	   	 
	   
	   	 if ("valid".equals(retstatus)) {
	   		// String mappingStatus=insertStudentDao.studentFacultyMapping(studentDomain);
	   		 //LOGGER.info("student faculty mapping:"+mappingStatus);
	   		//String to=studentDomain.getEmail();
	       //  sendMailIntern(host,to); 
	   		String Studentymsg="Student created successfully.";
			request.setAttribute("FacultySuccessMail",Studentymsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
			rd.forward(request, response);
			}
			if ("notvalid".equals(retstatus)) {
				
				String Studentymsg="Failed to create student.";
				request.setAttribute("FacultyFailure",Studentymsg);
				RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
				rd.forward(request, response);
			}
    }
    public void sendMailIntern(String host1,String to )
   	{
   	 
   		 Properties props= new Properties();
   		 props.put("mail.smtp.auth",pop); 
   	     props.put("mail.smtp.starttls.enable",pop);
   		 props.put("mail.smtp.host",host);
   		 props.put("mail.smtp.port",port);
   		 props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
   		 Session session= Session.getInstance(props, new javax.mail.Authenticator(){
   			 protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication(emailid,password);
   			 																		} });
   		 try
   		 { 
   			Message message= new MimeMessage(session);
   			message.setFrom(new InternetAddress(emailid));
   			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
   			//message.setSubject("Testing");
   			//message.setText("UserID:"+emailid+ "Password:"+password);
   			message.setSubject("User Information"); // Now set the actual message
   			message.setText("Hi "+studentName1+" "+lastName+" ,\n"  +"Welcome to KODE_DEV!!!\n" +"Your userID:"+ generatedStudentId+" and "+ "Password :" +" " +genaratePassword+",\n"+"Role ::student" +" " +",\n"  +" Kindly login into the below URL to start accessing it:\n"+classRoomLink+
   					"\n\n"+"Thanks & Regards"+"\n"+"KDS Team"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com"); 
   			/*message.setSubject(" About Pongal Holiday");
   			message.setText("Hi sakshi,\n We want to know about pongal holidays\n\n Thanks & Regards\n KDS Family");*/
   			//message.setText("SessionStartTime:"+SessionStartTime);
   			//message.setText("SessionEndTime:"+SessionEndTime);
   			//message.setText("Duration :"+hours);
   			//LOGGER.info();
   			 Transport.send(message);
   			LOGGER.info("send successfully");
   			/*try {
   				response.sendRedirect("../JSP/success.jsp");
   			} catch (IOException e) {
   				e.printStackTrace();
   			}*/
   		 	}
   		 catch(MessagingException e)
   		 {
   			String Studentymsg="Please check your net connection and try Again!";
			request.setAttribute("FacultyFailure",Studentymsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
   			 throw new RuntimeException(e);
   		 }catch(Exception e){
   			String Studentymsg="Please Again Later!";
			request.setAttribute("FacultyFailure",Studentymsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
   		 }
       }	
}
