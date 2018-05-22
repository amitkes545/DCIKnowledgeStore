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

import com.kds.KODE_DEV.dao.InsertSuperAdminDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;


@SuppressWarnings("serial")
public class CreateSuperAdminServlet extends CommonService {
	String host="" ;
	String pop="";
	int    port;
	String emailid="";
	String password="";
	String link="";
	static final Logger LOGGER = Logger.getLogger(CreateSuperAdminServlet.class);
	 public void run() throws ServletException,IOException
	 {
		 AdminDomain sdomain=new AdminDomain();
		// SenderEmailDetailsDomain mdomain=new SenderEmailDetailsDomain();
		 InsertSuperAdminDao sdao=new InsertSuperAdminDao();
		 SendEmailDao senderDao =new SendEmailDao();
			//session.removeAttribute("MsgValue");
			SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
    	 sdomain.setAdminId(request.getParameter("sid"));
    	// String name=request.getParameter("sname");
    	 LOGGER.info("super admin id is:"+request.getParameter("sid"));
    	 sdomain.setAdminName(request.getParameter("sname"));
    	 sdomain.setPwd(request.getParameter("pwd"));
    	 sdomain.setEmail(request.getParameter("email"));
    	 sdomain.setAddress(request.getParameter("address"));
    	 sdomain.setPhone(request.getParameter("phone"));
    	 sdomain.setPrivilege(request.getParameter("privilege"));
         sdomain.setOrgid(request.getParameter("orgid")); 
         sdomain.setCreated_by(request.getParameter("createdid"));
         
         emailid=senderDom.getEmailid();
		 password=senderDom.getPassword();
		 host= senderDom.getHost();
		 pop=senderDom.getPop();
		   port=senderDom.getPort();
		   link=senderDao.getWebLink();//selecting web room link from dao
    	 
    	 boolean isDuplicate = sdao.duplicate(sdomain);
       	 LOGGER.info("duplicate value is"+isDuplicate);
       	
         if(isDuplicate)
         {
//         	request.setAttribute("isDuplicate", "Y");
//         	request.setAttribute("message", "Oganization id"+organization_id+"already present in DB");
//             response.sendRedirect("../JSP/SetupCustomer.jsp");
         	String msg = "UserId is exit!please try another";
    		  request.setAttribute("MsgValue", msg);
    		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperAdmin.jsp");
    		   rd.forward(request, response);
         }
    	 String retstatus=sdao.addSuperAdminDetails(sdomain);
    	 
    	 if ("valid".equals(retstatus)) {
    		 String to=sdomain.getEmail();
        	 LOGGER.info("email ID:"+to);
        	 sendMailIntern(host,to); 
    		 String msg="Super admin created successfully"+"#"+" UserID & password sent to your emailID";
    		 LOGGER.info("line surator value:"+msg);
 			request.setAttribute("OwnerSuccessMAil",msg);
 			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperAdmin.jsp");
 			rd.forward(request, response);
  		}
  		if ("notvalid".equals(retstatus)) {
  			

  			String msg="Failed to create super admin ! try again";
			request.setAttribute("OwnerFailure",msg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperAdmin.jsp");
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
				message.setText("Hi "+request.getParameter("sname")+" ,\n"  +"Welcome to KODE_DEV!!!\n "  +"Your userID:"+ request.getParameter("sid")+"& "+ "Password :" +" " +request.getParameter("pwd")+",\n"+"Role :" +" " +request.getParameter("privilege")+",\n"+" Kindly login into the below URL to start accessing it:\n"+link+
						"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com"); 
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
				 throw new RuntimeException(e);
			 }
	    }	
   
}
