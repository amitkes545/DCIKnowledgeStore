package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.SessionDomain;

@SuppressWarnings("serial")
public class PostPoneServlet extends CommonService {
	
	
	static final Logger LOGGER = Logger.getLogger(PostPoneServlet.class);
	public void run() throws ServletException,IOException{
		
		session=request.getSession();
		CreateSessionDao createSessionDao=new CreateSessionDao();
		SessionDomain sessionDomain=new SessionDomain();
		 CreateSessionDao sessiondao=new CreateSessionDao();
		 
		ArrayList<SessionDomain> arrayList=new ArrayList<SessionDomain>();
		String Sessionid=request.getParameter("sessionid");
		//System.out.println("session id value:"+Sessionid);
		String sessionname=request.getParameter("sessionname");
		sessionDomain.setSessionName(request.getParameter("sessionname"));
		String facultyid=(String)session.getAttribute("userid");
		String organizationId=(String)session.getAttribute("orgid");
		/*sessionDomain.setFacultyId(facultyid);
		sessionDomain.setOrgId(organizationId);*/
		
		LOGGER.info("category :"+sessionname+ "facultyid:"+facultyid);
		
		
		////System.out.println("buttonValue:"+buttonUpdate);
		if(request.getParameter("sessionid")!=null && !(request.getParameter("sessionid").equalsIgnoreCase("All"))){
			
			ArrayList<SessionDomain> sessionDetails=createSessionDao.getSesstionTime(facultyid,Sessionid,organizationId);
		request.setAttribute("sessionDetails", sessionDetails);
		session.setAttribute("oldSessionDetails", sessionDetails);
		LOGGER.info("value of sessiontime is"+ sessionDetails);
		request.setAttribute("SessionName", Sessionid);
		session.setAttribute("facultyid",facultyid);
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/PostPoneSession.jsp");
		requestDispatcher.forward(request, response);
	}else if(request.getParameter("sessionid").equalsIgnoreCase("All")){
		//System.out.println("selected all ");
		ArrayList<SessionDomain> sessionDetails=createSessionDao.getSesstionAllDetails(facultyid,Sessionid,organizationId);
		request.setAttribute("sessionDetails", sessionDetails);
		session.setAttribute("oldSessionDetails", sessionDetails);
		LOGGER.info("value of sessiontime is"+ sessionDetails);
		request.setAttribute("SessionName", Sessionid);
		session.setAttribute("facultyid",facultyid);
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/PostPoneSession.jsp");
		requestDispatcher.forward(request, response);
	}
	}
}
