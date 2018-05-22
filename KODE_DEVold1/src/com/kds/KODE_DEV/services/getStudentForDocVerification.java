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

import com.kds.KODE_DEV.dao.ActivateStudentDao;
import com.kds.KODE_DEV.dao.CreateSessionDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.ActiveSessionDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.SessionDomain;

@SuppressWarnings("serial")
public class getStudentForDocVerification extends CommonService {
	
	
	static final Logger LOGGER = Logger.getLogger(getStudentForDocVerification.class);
	public void run() throws ServletException,IOException{
		
		System.out.println("run started");
		session=request.getSession();
		ActivateStudentDao createSessionDao=new ActivateStudentDao();
		ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
		 
		ArrayList<ActiveSessionDomain> arrayList=new ArrayList<ActiveSessionDomain>();
		String selectstudent=request.getParameter("selectstudent");
		String courseid=request.getParameter("courseid");
		String status=request.getParameter("status");
		
		String streamId=request.getParameter("streamid");
		String orgid=(String)session.getAttribute("orgId");
		System.out.println("orgid="+orgid);
		System.out.println("streamId="+streamId);	
		
		//System.out.println("user value:"+selectstudent);
		String sessionname=request.getParameter("sessionid");
		sessionDomain.setSessionName(request.getParameter("sessionid"));
		String facultyid=(String)session.getAttribute("userid");
		String organizationId=(String)session.getAttribute("orgId");
	
		
		LOGGER.info("category :"+sessionname+ "facultyid:"+facultyid);
		
		
		////System.out.println("buttonValue:"+buttonUpdate);
		System.out.println("selectstudent"+selectstudent);
		
		System.out.println("hello");
		
		ArrayList<ActiveSessionDomain> sessionDetailsAll=createSessionDao.getStudentForDocVerification(streamId,organizationId);
		System.out.println("sessionDetailsAll="+sessionDetailsAll.size());
		if(sessionDetailsAll.size()==0)
		{
			System.out.println("no data");
			String sessionStatus="No data available for this candidate";
  			request.setAttribute("FacultySuccess",sessionStatus);
  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DocumentVerification.jsp");
  			requestDispatcher.forward(request, response);
  			
		}
		else
		{
			request.setAttribute("sessionDetails", sessionDetailsAll);
					
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DocumentVerification.jsp");
			requestDispatcher.forward(request, response);	
	
	
			
		}
		
		
	
	}
	
}

