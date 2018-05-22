package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.FacilitatorSessionReportDao;
import com.kds.KODE_DEV.domain.SessionDomain;

@SuppressWarnings("serial")
public class StudentUpcomingSession extends CommonService{
	String teachingSourceName;
	public void run() throws ServletException,IOException{
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
		ArrayList<SessionDomain> sessionDomain= facilitatorSessionDao.sessionUpcomingDetails(organizationId, userId,userID,course,subject_Name); 	
		
		request.setAttribute("sessionData", sessionDomain);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/StudentSessionUpcomingReport.jsp");
		requestDispatcher.forward(request, response);
		
				
		}
			       	
					       
	}
	


