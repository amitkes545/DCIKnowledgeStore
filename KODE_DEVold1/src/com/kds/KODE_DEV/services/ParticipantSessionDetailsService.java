package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.ParticipantSessionDetailsDao;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.ParticipantSessionDomain;

@SuppressWarnings("serial")
public class ParticipantSessionDetailsService extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(ParticipantSessionDetailsService.class);
	public void run() throws ServletException,IOException
	{		
		//System.out.println("hi");
        session=request.getSession();
		DisplayCoursesDomain displayCoursesDomain=new DisplayCoursesDomain(); 
		displayCoursesDomain.setImagePath(session.getAttribute("userId").toString());
		displayCoursesDomain.setCourseName(request.getParameter("assessmentName"));
		displayCoursesDomain.setCourseFee(session.getAttribute("orgId").toString());
		displayCoursesDomain.setCourseDetails(request.getParameter("facultyName"));
		ParticipantSessionDetailsDao padd=new ParticipantSessionDetailsDao();
		ArrayList<ParticipantSessionDomain> arl=padd.selectCourses(displayCoursesDomain);
		request.setAttribute("assessmentDetails",arl);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantSession.jsp");
		requestDispatcher.forward(request, response);
	}
}
