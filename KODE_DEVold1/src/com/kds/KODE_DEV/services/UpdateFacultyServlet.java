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
import com.kds.KODE_DEV.dao.UpdateFacultyDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

@SuppressWarnings("serial")
public class UpdateFacultyServlet extends CommonService {
	
	String host="" ;
	String pop="";
	int    port;
	String emailid="";
	String password="";
	
	public void run() throws ServletException,IOException
	{
		AdminDomain adomain=new AdminDomain();
		UpdateFacultyDao fdao=new UpdateFacultyDao();
		adomain.setAdminName(request.getParameter("fname"));
		//System.out.println("user name is:"+request.getParameter("fname"));
		adomain.setAdminId(request.getParameter("fid"));
		//System.out.println("user name is:"+request.getParameter("fid"));
		adomain.setPwd(request.getParameter("pwd"));
		adomain.setEmail(request.getParameter("email"));
		adomain.setAddress(request.getParameter("address"));
		adomain.setPhone(request.getParameter("phone"));
		adomain.setPrivilege(request.getParameter("privilege"));
		
		adomain.setOrgid(request.getParameter("organizationId"));
		adomain.setCreated_by(request.getParameter("createdid"));
		SendEmailDao senderDao =new SendEmailDao();
		//session.removeAttribute("MsgValue");
		SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
		 emailid=senderDom.getEmailid();
		 password=senderDom.getPassword();
		 host= senderDom.getHost();
		 pop=senderDom.getPop();
		   port=senderDom.getPort();
		  // link=senderDao.getWebLink();//selecting web room link from dao
		
		if(request.getParameter("update")!=null)
    	{
			//System.out.println("this is update link");
    		
    		
    		String updatestatus=fdao.updateFacultyValues(adomain);
    		if("success".equalsIgnoreCase(updatestatus))
    		{
    			String to=adomain.getEmail();
              	 //System.out.println("email ID:"+to);
              	 sendMailIntern(host,to); 
    			String Facultymsg="profile updated successfully";
    			request.setAttribute("AdminSuccess",Facultymsg);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacultySearch.jsp");
    			rd.forward(request, response);
    		}else {
    			String Facultymsg="Failed for updating profile";
    			request.setAttribute("AdminFailure",Facultymsg);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacultySearch.jsp");
    			rd.forward(request, response);
    		}
    		
	}
    		else if(request.getParameter("delete")!=null)
   	{
   		//System.out.println("this is delete link");

    		
    		
   		String deletestatus=fdao.deletFacultyValues(adomain);
    		if("success".equalsIgnoreCase(deletestatus))
    		{
    			String to=adomain.getEmail();
             	 //System.out.println("email ID:"+to);
             	 sendMailInternDelete(host,to); 
    			String Facultymsg="Profile deleted successfully";
    			request.setAttribute("AdminSuccess",Facultymsg);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacultySearch.jsp");
    			rd.forward(request, response);
    		}else {
    			String Facultymsg="Failed for deleting profile";
    			request.setAttribute("AdminFailure",Facultymsg);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacultySearch.jsp");
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
			message.setText("Hi "+request.getParameter("fname")+" ,\n"    +"Your profile has been updated "+"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com" ); 
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
			message.setText("Hi "+request.getParameter("fname")+" ,\n"  +"Your ID deleted  "+"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com" ); 
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
    

