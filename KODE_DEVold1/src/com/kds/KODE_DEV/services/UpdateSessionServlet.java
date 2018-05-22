package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
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

import com.kds.KODE_DEV.dao.AssessmentDao;
import com.kds.KODE_DEV.dao.CreateSessionDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dao.SessionDao;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.SessionDomain;


@SuppressWarnings("serial")
public class UpdateSessionServlet extends CommonService {
	String userid="";
	String SessionStartTime="";
	String SessionEndTime="";
	String hours="";
	String SessionName="";
	String emailid="";
	String password="";
	String host="" ;
	String pop="";
	int    port;
	String link="";
	public void run() throws ServletException,IOException {
		session=request.getSession();
		SessionDomain sessionDomain=new SessionDomain();
		SessionDao sessionDao=new SessionDao();
		AssessmentDao assessmentDao=new AssessmentDao();
		 CreateSessionDao sessiondao=new CreateSessionDao();
		 SendEmailDao senderDao =new SendEmailDao();
		// CreateSessionDao createsessionDao=new CreateSessionDao();
		 String groupvalue="";
		 sessionDomain.setSessionId(request.getParameter("sid"));
		 sessionDomain.setSessionName(request.getParameter("sname"));
	//	String sName=request.getParameter("sname");
		 sessionDomain.setCategory(request.getParameter("category"));
		 sessionDomain.setCostOfSession(request.getParameter("cost"));
		 sessionDomain.setFacultyId(request.getParameter("fid"));
		 String facultyid=request.getParameter("fid");
		 String organizationId=request.getParameter("orgid");
		 sessionDomain.setOrgId(request.getParameter("orgid"));
		 sessionDomain.setRecipientType(request.getParameter("recipient"));
		//String recipientType=request.getParameter("recipient");
		////System.out.println("recipient type:"+recipientType);
		 
			//session.removeAttribute("MsgValue");
		 //retriving mail details from dao
			SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
			
			 emailid=senderDom.getEmailid();
			 password=senderDom.getPassword();
			 host= senderDom.getHost();
			 pop=senderDom.getPop();
			   port=senderDom.getPort();
			   link=senderDao.getClassRoomLink();//selecting class room link from dao
		 String individual=request.getParameter("student_id");
		   String groupid=request.getParameter("group_id");
		   String all=request.getParameter("group");
		   //System.out.println("update session values:"+individual+"group id:"+groupid+ "all:"+all);
		   if(groupid!="select"){
				 groupvalue=assessmentDao.getGroupValues(groupid,facultyid,organizationId);
				//System.out.println("group values:"+groupvalue);
			}
		   sessionDomain.setAll(all);
		   sessionDomain.setIndividualId(individual);
		   sessionDomain.setGroupId(groupvalue);
		   SessionDomain sessionOldValues=(SessionDomain)session.getAttribute("SesssionDetails");
		   
		  //System.out.println("group value in servlet:"+sessionDomain.getGroupId());
		  /* //System.out.println("individual:"+individual+  "groupid:"+groupid+  "all: "+all);*/
		   //System.out.println("sessionname:"+request.getParameter("sname"));
		   //System.out.println("orgid is:"+request.getParameter("orgid"));
		/*String updatestatus=sdao.updateSessionDetails(sdomain);
		if("success".equalsIgnoreCase(updatestatus))
		{
			String sessionmsg="Session values updated successfully";
			request.setAttribute("FacultySuccess",sessionmsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ManageSessionSearch.jsp");
			rd.forward(request, response);
		}else {
			String sessionmsg="Session values updation has failed";
			request.setAttribute("FacultyFailure",sessionmsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ManageSessionSearch.jsp");
			rd.forward(request, response);
		}*/
	
	
	if("select".equalsIgnoreCase(groupid) && "select".equalsIgnoreCase(individual)){
    	    	
    	    	//System.out.println("all in if else :"+all+"recipient type:"+sessionDomain.getAll());

		////System.out.println("recipient type:"+recipientType);
    	//String updatestatus=sdao.updateSessionDetails(sdomain);
    	String resultAll=sessionDao.insertSessionAll(sessionDomain);
    	
 	  //System.out.println("resultstatus:"+resultAll);
 	   if("valid".equalsIgnoreCase(resultAll)){
 		  if(!(sessionDomain.getSessionName().equals(sessionOldValues.getSessionName()))){
			   
			   //System.out.println("session name  changed:"+sessionDomain.getSessionName());
			   ArrayList<String> usersId=sessiondao.getUsersId(sessionDomain);
	        	ArrayList<String> resultID=sessiondao.getAllEmailId(usersId);
	        	for(String TO:resultID){
	        		sendMailInternSessionName(host,TO); 
	        	}
		   }
		   if(!(sessionDomain.getCostOfSession().equals(sessionOldValues.getCostOfSession())))
		   {
			   //System.out.println("cost value  changed:"+sessionDomain.getCostOfSession());
			   ArrayList<String> usersId=sessiondao.getUsersId(sessionDomain);
	        	ArrayList<String> resultID=sessiondao.getAllEmailId(usersId);
	        	for(String TO:resultID){
	        		sendMailIntern(host,TO); 
	        	}
		   }
 		  
 		  String sessionStatus="Session values updated successfully";
			request.setAttribute("FacultySuccess",sessionStatus);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ManageSessionSearch.jsp");
			rd.forward(request, response);
 	   }
    else if("notvalid".equalsIgnoreCase(resultAll)){
 		  String sessionStatus="Failed for updating session values";
			request.setAttribute("FacultyFailure",sessionStatus);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ManageSessionSearch.jsp");
			rd.forward(request, response);
 	   }
    	
    }else if("select".equalsIgnoreCase(groupid)){
    	
    	//System.out.println("induvidual values are:"+individual+ "recipientType:"+sessionDomain.getIndividualId());
    	
   String resultIndividual=sessionDao.insertSessionIndividual(sessionDomain);
   
    	 if("valid".equalsIgnoreCase(resultIndividual)){
    		 if(!(sessionDomain.getSessionName().equals(sessionOldValues.getSessionName()))){
  			   
  			   //System.out.println("session name  changed:"+sessionDomain.getSessionName());
  			 String studentid=sessionDomain.getIndividualId();
           	String to=sessiondao.getEmailId(studentid);
           	sendMailInternSessionName(host,to); 
  		   }
  		   if(!(sessionDomain.getCostOfSession().equals(sessionOldValues.getCostOfSession())))
  		   {
  			   //System.out.println("cost value  changed:"+sessionDomain.getCostOfSession());
  			 String studentid=sessionDomain.getIndividualId();
           	String to=sessiondao.getEmailId(studentid);
           	sendMailIntern(host,to); 
  		   }
   		  
    		 
    		  String sessionStatus="Session values updated successfully";
 			request.setAttribute("FacultySuccess",sessionStatus);
 			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ManageSessionSearch.jsp");
 			rd.forward(request, response);
    	   }else if("notvalid".equalsIgnoreCase(resultIndividual)){
    		  String sessionStatus="Failed for updating session values";
 			request.setAttribute("FacultyFailure",sessionStatus);
 			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ManageSessionSearch.jsp");
 			rd.forward(request, response);
    	   }
    	
    }else if("select".equalsIgnoreCase(individual)){
    	
    	//System.out.println("group id in if else:"+groupid+"recipientType:"+sessionDomain.getGroupId());
    	
    	String resultGroup=sessionDao.insertSessionGroupId(sessionDomain);
    	if("valid".equalsIgnoreCase(resultGroup)){
    		 if(!(sessionDomain.getSessionName().equals(sessionOldValues.getSessionName()))){
  			   
  			   //System.out.println("session name  changed:"+sessionDomain.getSessionName());
  			 String groupId=sessionDomain.getGroupId();
         	String groupIDS[]=groupId.split("#");
         	ArrayList<String> to=sessiondao.getEmailGroupId(groupIDS);
         	for(String TO:to){
         		//System.out.println("user id are:"+TO);
         		sendMailInternSessionName(host,TO); 
         	}
  		   }
  		   if(!(sessionDomain.getCostOfSession().equals(sessionOldValues.getCostOfSession())))
  		   {
  			   //System.out.println("cost value  changed:"+sessionDomain.getCostOfSession());
  			 String groupId=sessionDomain.getGroupId();
         	String groupIDS[]=groupId.split("#");
         	ArrayList<String> to=sessiondao.getEmailGroupId(groupIDS);
         	for(String TO:to){
         		//System.out.println("user id are:"+TO);
         		sendMailIntern(host,TO); 
         	}
  		   }
   		  
    		
  		  String sessionStatus="Session values updated successfully";
			request.setAttribute("FacultySuccess",sessionStatus);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ManageSessionSearch.jsp");
			rd.forward(request, response);
  	   }else if("notvalid".equalsIgnoreCase(resultGroup)){
  		  String sessionStatus="Failed for update session values";
			request.setAttribute("FacultyFailure",sessionStatus);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ManageSessionSearch.jsp");
			rd.forward(request, response);
  	   }
    }
	}
	 public void sendMailInternSessionName(String host1,String to )
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
				message.setSubject("Session update information");
				message.setText("Hi\n Your Session Name changed"+"\n Now session name is:"+request.getParameter("sname")+"\n Kindly login into the below URL to start accessing it:\n\n"+link +"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com");
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
				message.setSubject("Session update information");
				message.setText("Hi\n Your session cost was changed "+"\n Now session cost is:"+request.getParameter("cost")+"\n Kindly login into the below URL to start accessing it:\n"+link +"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com");
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
	
