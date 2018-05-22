package com.kds.KODE_DEV.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;
import com.kds.KODE_DEV.services.CommonService;

@SuppressWarnings("serial")
public class IndividualStudentDetailsService extends CommonService{
	public void run() throws ServletException, IOException
	{		
	 session=request.getSession();
		IndividualStudentDetailsDao isd=new IndividualStudentDetailsDao();
		ResultSet resultSet=isd.IndividualStudentDetails(request.getParameter("studentId"));
		request.setAttribute("studentinfo", resultSet);
		FacultyIndividualStudentAssesmentDao fis=new FacultyIndividualStudentAssesmentDao();
		DisplayCoursesDomain ri=new DisplayCoursesDomain();
		ri.setCourseName(request.getParameter("studentId"));
		ri.setCourseDetails(session.getAttribute("userId").toString());
		ri.setCourseFee(session.getAttribute("orgId").toString());
		ArrayList<RetriveImagesDomain> arl=fis.selectCourses(ri);
		request.setAttribute("assesments", arl);
		FacultyIndividualStudentAssignmentDao fis1=new FacultyIndividualStudentAssignmentDao();
		ArrayList<RetriveImagesDomain> arl1=fis1.selectCourses(ri);
		request.setAttribute("assignments", arl1);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/IndividualStudentDetails.jsp");
		rd.forward(request, response);		
	}
}
