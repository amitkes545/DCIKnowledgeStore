package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SubjectDescriptionDao;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.SubjectDescriptionDomain;

@SuppressWarnings("serial")
public class SubjectDescriptionService extends CommonService {
   
	static final Logger LOGGER = Logger.getLogger(SubjectDescriptionService.class);
	public void run() throws ServletException,IOException
	{
		
		DisplayCoursesDomain dcd=new DisplayCoursesDomain();
				
		dcd.setCourseName(request.getParameter("Subjects"));
		
		LOGGER.info(dcd.getCourseName());
		SubjectDescriptionDao ddd=new SubjectDescriptionDao();
		ArrayList<SubjectDescriptionDomain> arl=ddd.subjectDescription(dcd);
		//request.setAttribute("departments",arl);
		//response.sendRedirect("../JSP/Departments.jsp?departments="+arl);
		request.setAttribute("Subjects",request.getParameter("Subjects"));
		request.setAttribute("departm",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/SubjectView.jsp");
	rd.forward(request, response);
	}
}
