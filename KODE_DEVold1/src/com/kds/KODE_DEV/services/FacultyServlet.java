package com.kds.KODE_DEV.services;

import java.io.IOException;
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

import com.kds.KODE_DEV.dao.CustomerDao;
import com.kds.KODE_DEV.dao.InsertFacultyDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;


@SuppressWarnings("serial")
public class FacultyServlet extends CommonService {
	String host="" ;
	String pop="";
	int    port;
	String emailid="";
	String password="";
       String link="";
       String classRoomLink="";
       String kStoreLink="",genaratePassword="";
    public void run() throws ServletException,IOException{
    	AdminDomain facultyDomain=new AdminDomain();
    	facultyDomain.setAdminName(request.getParameter("fname"));
   	 String facultyid=request.getParameter("fid");
   	 String facultyName=request.getParameter("fname");
   	 //System.out.println("faculty id is"+facultyid+ "faculty name:"+facultyName);
   	facultyDomain.setAdminId(request.getParameter("fid"));
   	//fdomain.setPwd(request.getParameter("pwd"));
   	facultyDomain.setEmail(request.getParameter("email"));
   	facultyDomain.setAddress(request.getParameter("address"));
   	facultyDomain.setPhone(request.getParameter("phone"));
   	facultyDomain.setPrivilege(request.getParameter("privilege"));
   	facultyDomain.setOrgid(request.getParameter("organizationId"));
   	facultyDomain.setCreated_by(request.getParameter("createdid"));
   	SendEmailDao senderDao =new SendEmailDao();
	//session.removeAttribute("MsgValue");
	SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
	 emailid=senderDom.getEmailid();
	 password=senderDom.getPassword();
	 host= senderDom.getHost();
	 pop=senderDom.getPop();
	   port=senderDom.getPort();
	   link=senderDao.getWebLink();//selecting web room link from dao
	   classRoomLink=senderDao.getClassRoomLink();//selecting classroom link from dao
	   kStoreLink=senderDao.getKnowStoreLink();//selecting know store link from dao
   	 InsertFacultyDao fdao=new InsertFacultyDao();
   	 CustomerDao customerDao=new CustomerDao();
   	 //calling dao method for generate password randomlly
	   String password= customerDao. randomSeriesForThreeCharacter();
     //String  superAdminname="";
     if (facultyName.length() > 3){
    	 facultyName =facultyName.substring(0, 3);
     }
     genaratePassword=facultyName+password;
     //System.out.println("new password:"+genaratePassword);
     facultyDomain.setPwd(genaratePassword);
   	 boolean isDuplicate = fdao.duplicate(facultyDomain);
      	 //System.out.println("duplicate value is"+isDuplicate);
      	
        if(isDuplicate)
        {
        	String msg = "UserId is exit!please try another";
   		  request.setAttribute("MsgValue", msg);
   		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/Faculty.jsp");
   		   rd.forward(request, response);
        }
    	String retstatus=fdao.addFacultyDetails(facultyDomain);
   	 if ("valid".equals(retstatus)) {
   	
      	 String to=facultyDomain.getEmail();
   	     sendMailIntern(host,to); 
   		String Facultymsg= "Faculty created successfully"+"#"+" User ID & password sent to your email ID";
		request.setAttribute("AdminSuccessMail",Facultymsg);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/Faculty.jsp");
		rd.forward(request, response);
		}
		if ("notvalid".equals(retstatus)) {
			
			String Facultymsg="Failed for create faculty! try again";
			request.setAttribute("AdminFailure",Facultymsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Faculty.jsp");
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
			message.setText("Hi "+request.getParameter("fname")+" ,\n"  +"Welcome to KODE_DEV!!!\n "  +"Your  userID :"+ request.getParameter("fid")+" "+  "& "+ "Password :"+genaratePassword+"\n Role :Faculty"+",\n\n\n"+" Kindly login into the below URL to start accessing it:\n"+link +"\n For classroom login kindly accessing below link:\n"+classRoomLink+"\n For knowstore login kindly accessing below link:\n"+kStoreLink+
					"\n\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com"); 
			//message.setText("SessionStartTime:"+SessionStartTime);
			//message.setText("SessionEndTime:"+SessionEndTime);
			//message.setText("Duration :"+hours);
			//System.out.println();
			 Transport.send(message);
			//System.out.println("send successfully");
			/*try {
				response.sendRedirect("../JSP/success.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		 	}
		 catch(MessagingException e)
		 {
			 throw new RuntimeException(e);
		 }
    }	
    }

