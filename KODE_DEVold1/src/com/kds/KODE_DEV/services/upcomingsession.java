package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.kds.KODE_DEV.dao.AssessmentDao;
import com.kds.KODE_DEV.dao.CreateSessionDao;
import com.kds.KODE_DEV.dao.FacilitatorSessionReportDao;
import com.kds.KODE_DEV.dao.ParameterDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.SessionDomain;

@SuppressWarnings("serial")
public class upcomingsession extends CommonService{
	
	
	public void run() throws ServletException,IOException{
		
		System.out.println("hello1111");
		 session=request.getSession();
		 String organizationId = (String) session.getAttribute("orgid");
		 String userId = (String) session.getAttribute("createdBy");
		 String userid = (String) session.getAttribute("userid");
		 String userID = (String) session.getAttribute("userId");
		
		System.out.println("session service");
		String course=request.getParameter("course");
		String subject_Name=request.getParameter("subject");
		
		System.out.println("course="+course);
		System.out.println("subject_Name="+subject_Name);
		
		FacilitatorSessionReportDao facilitatorSessionDao = new FacilitatorSessionReportDao();
	//	ArrayList<SessionDomain> sessionDomain=null;
	//	ArrayList<SessionDomain> sessionDomain= facilitatorSessionDao.facultysessionUpcomingDetails(organizationId, userId,subject_Name); 
		if(subject_Name.equals("All"))
		{
			System.out.println("All----------------");
			ArrayList<SessionDomain> sessionDomain=facilitatorSessionDao.facultySessionUpcomingDetails(organizationId, userID, course,subject_Name);
			request.setAttribute("sessionDomain", sessionDomain);
		}
		else
		{
			System.out.println("individuals");
			ArrayList<SessionDomain>sessionDomain=facilitatorSessionDao.facultySessionUpcomingDetailsForIndividual(organizationId, userID,subject_Name );
			System.out.println("sessionDomain="+sessionDomain.size());
			request.setAttribute("sessionDomain", sessionDomain);
		}
			
			
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/FacilitatorSessionUpcomingReport.jsp");
		requestDispatcher.forward(request, response);

	}
	
}

