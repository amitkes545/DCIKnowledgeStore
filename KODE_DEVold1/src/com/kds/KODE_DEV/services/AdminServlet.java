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

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.CustomerDao;
import com.kds.KODE_DEV.dao.InsertDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

@SuppressWarnings("serial")
public class AdminServlet extends CommonService {
	String host="" ;
	String pop="";
	int    port;
	String emailid="";
	String password="";
	String link="",genaratePassword="";
	static final Logger LOGGER = Logger.getLogger(AdminServlet.class);
     public void run() throws ServletException,IOException
     {
    	 AdminDomain adminDomain=new AdminDomain();
    	 InsertDao insertDao=new InsertDao();
    	 SendEmailDao senderDao =new SendEmailDao();
    	 CustomerDao customerDao=new CustomerDao();
    	 
    	 adminDomain.setAdminName(request.getParameter("adminname"));
    	 String AdminName=request.getParameter("adminname");
    	 adminDomain.setAdminId(request.getParameter("adminid"));
    	 //adminDomain.setPwd(request.getParameter("pwd"));
    	 adminDomain.setEmail(request.getParameter("email"));
    	 adminDomain.setAddress(request.getParameter("address"));
    	 adminDomain.setPhone(request.getParameter("phone"));
    	 adminDomain.setPrivilege(request.getParameter("privilege"));
    	 adminDomain.setOrgid(request.getParameter("organizationId"));
    	 adminDomain.setCreated_by(request.getParameter("createdid"));
    	
			//session.removeAttribute("MsgValue");
			SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
			 emailid=senderDom.getEmailid();
			 password=senderDom.getPassword();
			 host= senderDom.getHost();
			 pop=senderDom.getPop();
			   port=senderDom.getPort();
			   link=senderDao.getWebLink();//selecting web room link from dao
			   
			   //calling dao method for generate password randomlly
			   String password= customerDao. randomSeriesForThreeCharacter();
               //String  superAdminname="";
               if (AdminName.length() > 3){
             	  AdminName =AdminName.substring(0, 3);
               }
               genaratePassword=AdminName+password;
               //System.out.println("new password:"+genaratePassword);
               adminDomain.setPwd(genaratePassword);
    	 boolean isDuplicate = insertDao.duplicate(adminDomain);
       	 //System.out.println("duplicate value is"+isDuplicate);
       	
         if(isDuplicate)
         {

         	String msg = "UserId is exit!please try another";
    		  request.setAttribute("MsgValue", msg);
    		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/Admin.jsp");
    		   rd.forward(request, response);
         }
         //calling Dao method for inserting details of customer 
    	 String retstatus=insertDao.addAdminDetails(adminDomain);
    	 
    	 if ("valid".equals(retstatus)) {
    		 String to=adminDomain.getEmail();
        	 sendMailIntern(host,to); 
    		 String AdminMsg="Admin created successfully"+"#"+" User ID & password sent to your email ID";
 			request.setAttribute("SuperAdminSuccessMAil",AdminMsg);
 			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Admin.jsp");
 			rd.forward(request, response);
 		}
 		if ("notvalid".equals(retstatus)) {
 			String AdminMsg="Failed for create admin! try again";
			request.setAttribute("SuperAdminFailure",AdminMsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Admin.jsp");
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
				message.setText("Hi "+request.getParameter("adminname")+" ,\n"  +"Welcome to KODE_DEV!!!\n "  +"Your  User ID :"+ request.getParameter("adminid")+   "& "+ "Password :" +" " +genaratePassword+",\n"+"Role :Admin" +",\n"+" Kindly login into the below URL to start accessing it:\n"+link+
						"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com"); 
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
