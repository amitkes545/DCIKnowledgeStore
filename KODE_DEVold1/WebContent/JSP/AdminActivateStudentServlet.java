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
public class AdminActivateStudentServlet extends CommonService {
	
	
	static final Logger LOGGER = Logger.getLogger(AdminActivateStudentServlet.class);
	public void run() throws ServletException,IOException{
		
		session=request.getSession();
		ActivateStudentDao createSessionDao=new ActivateStudentDao();
		ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
		 
		ArrayList<ActiveSessionDomain> arrayList=new ArrayList<ActiveSessionDomain>();
		String selectstudent=request.getParameter("selectstudent");
		String courseid=request.getParameter("courseid");
		String status=request.getParameter("status");
		System.out.println("Sessionid"+selectstudent);
		System.out.println("courseid"+courseid);
		System.out.println("status"+status);
		
		//System.out.println("user value:"+selectstudent);
		String sessionname=request.getParameter("sessionid");
		sessionDomain.setSessionName(request.getParameter("sessionid"));
		String facultyid=(String)session.getAttribute("userid");
		String organizationId=(String)session.getAttribute("orgid");
	
		
		LOGGER.info("category :"+sessionname+ "facultyid:"+facultyid);
		
		
		////System.out.println("buttonValue:"+buttonUpdate);
		System.out.println("selectstudent"+selectstudent);
		 if(!(request.getParameter("selectstudent").equalsIgnoreCase("All"))&& (!(request.getParameter("status").equalsIgnoreCase("All")))){
				
				ArrayList<ActiveSessionDomain> sessionDetailsAll=createSessionDao.getStudentForIndividual(selectstudent,courseid,status);
			if(sessionDetailsAll.size()==0)
			{
				System.out.println("no data");
				String sessionStatus="No data available for this candidate";
	  			request.setAttribute("FacultySuccess",sessionStatus);
	  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AdminActivateStudent.jsp");
	  			requestDispatcher.forward(request, response);
			}
			else
				request.setAttribute("sessionDetails", sessionDetailsAll);
				
				
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AdminActivateStudent.jsp");
				requestDispatcher.forward(request, response);		
			}
		 else if(request.getParameter("selectstudent").equalsIgnoreCase("All")&& (request.getParameter("status").equalsIgnoreCase("All"))){
			
			ArrayList<ActiveSessionDomain> sessionDetailsAll=createSessionDao.getStudentsForStatus(courseid);
			request.setAttribute("sessionDetails", sessionDetailsAll);
			
			
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AdminActivateStudent.jsp");
			requestDispatcher.forward(request, response);		
		}
		
		
		else if(request.getParameter("selectstudent").equalsIgnoreCase("All")&& ((!request.getParameter("status").equalsIgnoreCase("All")))){
			
			ArrayList<ActiveSessionDomain> sessionDetailsAll=createSessionDao.getStuDetails(courseid,status);
			request.setAttribute("sessionDetails", sessionDetailsAll);
			
			
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AdminActivateStudent.jsp");
			requestDispatcher.forward(request, response);		
		}
		
		else if(request.getParameter("selectstudent")!=null && !(request.getParameter("selectstudent").equalsIgnoreCase("All"))&& (request.getParameter("status").equalsIgnoreCase("All"))){
			
			ArrayList<ActiveSessionDomain> sessionDetails=createSessionDao.getStuDetailsForAll(selectstudent,courseid,status);
		request.setAttribute("sessionDetails", sessionDetails);
		session.setAttribute("oldSessionDetails", sessionDetails);
		LOGGER.info("value of sessiontime is"+ sessionDetails);
		request.setAttribute("SessionName", selectstudent);
		session.setAttribute("facultyid",facultyid);
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AdminActivateStudent.jsp");
		requestDispatcher.forward(request, response);
	}
		
	/*	else if(request.getParameter("selectstudent")!=null && !(request.getParameter("selectstudent").equalsIgnoreCase("All"))&& (!(request.getParameter("status").equalsIgnoreCase("All")))&&(request.getParameter("courseid")!=null)){
			ArrayList<ActiveSessionDomain> sessionDetails=createSessionDao.getStuDetailsStatusWise(selectstudent,courseid,status);
			request.setAttribute("sessionDetails", sessionDetails);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AdminActivateStudent.jsp");
			requestDispatcher.forward(request, response);
		}*/
		else if(request.getParameter("courseid")!=null)
			{
				ArrayList<ActiveSessionDomain> sessionDetails=createSessionDao.getSesstionAllDetailsForStatusAll(courseid);
			request.setAttribute("sessionDetails", sessionDetails);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AdminActivateStudent.jsp");
			requestDispatcher.forward(request, response);
			}
			
		
	
	}
}

