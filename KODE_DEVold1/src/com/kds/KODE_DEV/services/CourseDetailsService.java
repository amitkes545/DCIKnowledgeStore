package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.DisplayDepartmentsDao;
import com.kds.KODE_DEV.domain.DepartmentsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

@SuppressWarnings("serial")
public class CourseDetailsService  extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(CourseDetailsService.class);
	public void run() throws ServletException,IOException
	{
		
		DisplayCoursesDomain dcd=new DisplayCoursesDomain();
		
		
		dcd.setCourseName(request.getParameter("CourseName"));
		
		LOGGER.info(dcd.getCourseName());
		DisplayDepartmentsDao ddd=new DisplayDepartmentsDao();
		ArrayList<DepartmentsDomain> arl=ddd.selectDepartments(dcd);
		//request.setAttribute("departments",arl);
		//response.sendRedirect("../JSP/Departments.jsp?departments="+arl);
		request.setAttribute("departments",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/Departments.jsp");
	rd.forward(request, response);
	}
}
