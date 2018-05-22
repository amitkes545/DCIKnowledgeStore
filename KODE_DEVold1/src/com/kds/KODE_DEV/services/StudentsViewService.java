package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.ActivateStudentDao;
//import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class StudentsViewService  extends CommonService {
	public void run() throws ServletException,IOException
	{		
		System.out.println("hi service");
		 session=request.getSession();
		if(session.getAttribute("userId")==null){
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/error.jsp");
			rd.forward(request, response);
		}
		/*DisplayCoursesDomain ri=new DisplayCoursesDomain(); 
		ri.setImagePath(session.getAttribute("userId").toString());
		
		ri.setCourseName(request.getParameter("assessmentId"));
		ri.setCourseFee(session.getAttribute("orgId").toString());
		ri.setUploady(request.getParameter("facultyName"));*/
		//System.out.println("assessment id in upload:"+session.getAttribute("uploadedId"));
		//ri.setUploady(session.getAttribute("uploadedId").toString());
		String orgid=session.getAttribute("orgId").toString();
		
		ArrayList<AdminDomain> arl=null;
		ActivateStudentDao padd=new ActivateStudentDao();
		arl=padd.studentsList(orgid);
		
		request.setAttribute("studentDetails",arl);
		//session.setAttribute("assessmentDetails",arl);
		//response.sendRedirect("../JSP/create-student-file.jsp");
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/create-student-file.jsp");
		requestDispatcher.forward(request, response);
	}
}
