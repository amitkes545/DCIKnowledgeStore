package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.ParticipantSubjectDescriptionDao;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.SubjectDescriptionDomain;

@SuppressWarnings("serial")
public class ParticipantSubjectDescription extends CommonService {
	   
		public void run() throws ServletException,IOException
		{
		    session = request.getSession();
			DisplayCoursesDomain dcd=new DisplayCoursesDomain();
					
			dcd.setCourseName(request.getParameter("Subjects"));
			dcd.setCourseDetails(session.getAttribute("orgId").toString());
			
			//System.out.println(dcd.getCourseName());
			ParticipantSubjectDescriptionDao ddd=new ParticipantSubjectDescriptionDao();
			ArrayList<SubjectDescriptionDomain> arl=ddd.subjectDescription(dcd);
			//request.setAttribute("departments",arl);
			//response.sendRedirect("../JSP/Departments.jsp?departments="+arl);
			request.setAttribute("Subjects",request.getParameter("Subjects"));
			request.setAttribute("departm",arl);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ParticipantSubjectView.jsp");
		rd.forward(request, response);
		}
	}