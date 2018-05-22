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

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.CreateSessionDao;
import com.kds.KODE_DEV.dao.FacilitatorSessionCancelDao;
import com.kds.KODE_DEV.dao.SenderEmailDetailsDAO;
import com.kds.KODE_DEV.domain.FacilitatorSessionReportDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.SessionDomain;

@SuppressWarnings("serial")
public class FacilitatorSessionCancelServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorSessionCancelServlet.class);
	
	String emailid="";
	String password="";
	String host="";
	String pop="";
	int    port=0;
	String recipientType="";
	
	@Override
	public void run() throws ServletException, IOException, IllegalAccessException, InstantiationException {

	    session = request.getSession();
		SenderEmailDetailsDAO senderDao =new SenderEmailDetailsDAO();
		FacilitatorSessionCancelDao mksDao = new FacilitatorSessionCancelDao();
		SessionDomain sessionDomain=new SessionDomain();
		CreateSessionDao sessiondao=new CreateSessionDao();
		//session.removeAttribute("MsgValue");
		String orgId = request.getParameter("orgid");
		String userId = request.getParameter("userid");
		SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
		//String retStatus="";
		 emailid=senderDom.getEmailid();
	     password=senderDom.getPassword();
		 host= senderDom.getHost();
		 pop=senderDom.getPop();
		    port=senderDom.getPort();
        FacilitatorSessionReportDomain mksDomain = new FacilitatorSessionReportDomain();
		
		
		String sessionName = request.getParameter("sessionName");
		mksDomain.setSessionName(sessionName);
		LOGGER.info("sessionName in service:"+sessionName);
		//mksDomain.setRecipientType(recipientType);
		mksDomain.setUserId(userId);
		mksDomain.setOrgId(orgId);
		sessionDomain.setFacultyId(userId);
		sessionDomain.setOrgId(orgId);
		sessionDomain.setSessionName(sessionName);
		if(sessionName!=null && request.getParameter("cancel") == null && request.getParameter("available") == null){
			//retStatus = mksDao.updateValuesAvailable(mksDomain);
			String sessionStatus=mksDao.getSessionStatus(mksDomain);
			LOGGER.info("session Status:"+sessionStatus);
			request.setAttribute("sessionName", sessionName);
			request.setAttribute("sessionStatus",sessionStatus);
			//response.sendRedirect("../JSP/FacilitatorSessionCancel.jsp");
			RequestDispatcher rquestDispatcher=request.getRequestDispatcher("../JSP/FacilitatorSessionCancel.jsp");
			rquestDispatcher.forward(request, response);
		}
		else if(request.getParameter("cancel")!=null && sessionName!=null && request.getParameter("available") == null){
			String sessionValue=request.getParameter("cancel");
			String sessionValue1=request.getParameter("available");
			LOGGER.info("sessionValue in service:"+sessionValue+"sessionName in else:"+sessionName+ "sessionValue1 in else :"+sessionValue1);
			String status=mksDao.updateValuesAvailable(mksDomain);
			LOGGER.info("status:"+status);
			if("success".equalsIgnoreCase(status)){
				 ArrayList<String> domain=sessiondao.getRecipientType(sessionDomain);
				 //SessionName=sessionDomain.getSessionName();
				 for (int j = 0; j < domain.size(); j++) {
					// SessionStartTime  = domain.get(0);
					 recipientType=domain.get(1);
					// SessionEndTime=domain.get(2);
				        LOGGER.info("session values if valid:"+recipientType);
				    }
					if(recipientType.equalsIgnoreCase("all")){
						ArrayList<String> usersId=sessiondao.getUsersId(sessionDomain);
		            	ArrayList<String> resultID=sessiondao.getAllEmailId(usersId);
		            	for(String TO:resultID){
		            		sendMailIntern(host,TO); 
		            	}
					}else {
		            	String groupIDS[]=recipientType.split("#");
		            	ArrayList<String> to=sessiondao.getEmailGroupId(groupIDS);
		            	for(String TO:to){
		            		LOGGER.info("user id are:"+TO);
		            		sendMailIntern(host,TO); 
		            	}
					}
				String successMessage="session canceled successfully";
				request.setAttribute("FacultySuccess",successMessage);
				RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacilitatorSessionCancel.jsp");
				rd.forward(request, response);
		}else if("failure".equalsIgnoreCase(status)){
			String successMessage="failed for cancel session";
			request.setAttribute("FacultyFailure",successMessage);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacilitatorSessionCancel.jsp");
			rd.forward(request, response);
		}
		}else if(sessionName!=null && request.getParameter("available")!=null && request.getParameter("cancel") == null){
			String sessionValue=request.getParameter("cancel");
			String sessionValue1=request.getParameter("available");
			LOGGER.info("sessionValue in service:"+sessionValue+"sessionName in else:"+sessionName+ "sessionValue1 in else :"+sessionValue1);
			String status=mksDao.updateValuesCancel(mksDomain);
			LOGGER.info("status:"+status);
			if("success".equalsIgnoreCase(status)){
				ArrayList<String> domain=sessiondao.getRecipientType(sessionDomain);
				 //SessionName=sessionDomain.getSessionName();
				 for (int j = 0; j < domain.size(); j++) {
					// SessionStartTime  = domain.get(0);
					 recipientType=domain.get(1);
					// SessionEndTime=domain.get(2);
				        LOGGER.info("session values if valid:"+recipientType);
				    }
					if(recipientType.equalsIgnoreCase("all")){
						ArrayList<String> usersId=sessiondao.getUsersId(sessionDomain);
		            	ArrayList<String> resultID=sessiondao.getAllEmailId(usersId);
		            	for(String TO:resultID){
		            		sendMailIntern(host,TO); 
		            	}
					}else {
		            	String groupIDS[]=recipientType.split("#");
		            	ArrayList<String> to=sessiondao.getEmailGroupId(groupIDS);
		            	for(String TO:to){
		            		LOGGER.info("user id are:"+TO);
		            		sendMailIntern(host,TO); 
		            	}
					}
				String successMessage="session available ";
				request.setAttribute("FacultySuccess",successMessage);
				RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacilitatorSessionCancel.jsp");
				rd.forward(request, response);
		}else if("failure".equalsIgnoreCase(status)){
			String successMessage="failed for available session";
			request.setAttribute("FacultyFailure",successMessage);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacilitatorSessionCancel.jsp");
			rd.forward(request, response);
		}
		}
		//LOGGER.info("session name:"+sessionName);
		//String recipientType = request.getParameter("recipienttype");
		
		//String status=request.getParameter("status");
		//LOGGER.info(status);
		
		
		/*ArrayList<String> bl= new ArrayList<String>();
		String userid=(String)session.getAttribute("userid");
		String orgid=(String)session.getAttribute("orgid");*/
		
		//LoginDomain ldom=( LoginDomain)session.getAttribute("lDomain");
		//LOGGER.info("ldom"+ldom);
		
		//ArrayList<String> al= (new FacilitatorSessionSendingMailDao()).getMailingList(userid,orgid);
		

		
			/*if("Available".equals(status)){
		// boolean a = loginDao.checkUserCredentials(lDomain);
		retStatus = mksDao.updateValuesAvailable(mksDomain);
		for(String to:al)
		 {
			sendMailIntern(host,to); 
		 }

		// LOGGER.info("retStatus: "+ retStatus);
			}
			else if("Cancelled".equals(status)){
				retStatus = mksDao.updateValuesCancel(mksDomain);
				for(String to:al)
				 { 
					sendMailIntern(host,to); 
				 }
			}		*/
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
				message.setText("Hi\n Your session "+request.getParameter("sessionName") +"was canceled"  +"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com");
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