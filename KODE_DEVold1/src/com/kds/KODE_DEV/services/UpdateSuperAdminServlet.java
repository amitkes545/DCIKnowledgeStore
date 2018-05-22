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

import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dao.UpdateSuperAdminDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

public class UpdateSuperAdminServlet extends CommonService {
	String host="" ;
	String pop="";
	int    port;
	String emailid="";
	String password="";
	String link="";
	private static final long serialVersionUID = 1L;
	@Override
	public void run() throws ServletException,IOException{
		////System.out.println("this is update page");
		AdminDomain adomain=new AdminDomain();
		UpdateSuperAdminDao sadmindao=new UpdateSuperAdminDao();
		adomain.setAdminName(request.getParameter("sname"));
		adomain.setAdminId(request.getParameter("sid"));
		adomain.setPwd(request.getParameter("pwd"));
		adomain.setEmail(request.getParameter("email"));
		adomain.setAddress(request.getParameter("address"));
		adomain.setPhone(request.getParameter("phone"));
		adomain.setPrivilege(request.getParameter("privilege"));
		adomain.setOrgid(request.getParameter("orgid"));
		//System.out.println("organization id:"+request.getParameter("orgid"));
		SendEmailDao senderDao =new SendEmailDao();
		//session.removeAttribute("MsgValue");
		//selecting mail details from dao
		SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
		 emailid=senderDom.getEmailid();
		 password=senderDom.getPassword();
		 host= senderDom.getHost();
		 pop=senderDom.getPop();
		   port=senderDom.getPort();
		   link=senderDao.getWebLink();//selecting web room link from dao

		if(request.getParameter("update")!=null)
    	{
			////System.out.println("this is update link");
    		//calling dao function for update superAdmin details
    		String updatestatus=sadmindao.updateSuperAdminValues(adomain);
    		if("success".equalsIgnoreCase(updatestatus))
    		{
    			String to=adomain.getEmail();
           	 //System.out.println("email ID:"+to);
           	 sendMailIntern(host,to); //calling function to send mail
    			String msg="Profile updated successfully";
			request.setAttribute("OwnerSuccess",msg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperAdminSearch.jsp");
			rd.forward(request, response);
    		}else {
    			String msg="updation failed";
    			request.setAttribute("OwnerFailure",msg);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperAdminSearch.jsp");
    			rd.forward(request, response);
    		}
    		
	}
		
		else if(request.getParameter("delete")!=null)
   	     {
   		////System.out.println("this is delete link");

    		
			//calling dao function for delete superAdmin details
   		String deletestatus=sadmindao.deleteSuperAdminValues(adomain);
    		if("success".equalsIgnoreCase(deletestatus))
    		{
    			String to=adomain.getEmail();
              	 //System.out.println("email ID:"+to);
              	 sendMailInternDelete(host,to); //calling function to send mail
    			String msg="profile deleted successfully";
    			request.setAttribute("OwnerSuccess",msg);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperAdminSearch.jsp");
    			rd.forward(request, response);
    		}else {
    			String msg="Failed for deleting profile";
    			request.setAttribute("OwnerFailure",msg);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperAdminSearch.jsp");
    			rd.forward(request, response);
   		}
    		
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
				message.setText("Hi "+request.getParameter("sname")+" ,\n"    +"Your profile has been updated "+"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com"); 
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
	 public void sendMailInternDelete(String host1,String to )
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
				message.setText("Hi "+request.getParameter("sname")+" ,\n"   +"Your ID deleted  " +"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com"); 
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
	
       
    

