package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.DisplaySemestersDao;
import com.kds.KODE_DEV.domain.DepartmentsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

@SuppressWarnings("serial")
public class SemestersService extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(SemestersService.class);
	public void run() throws ServletException,IOException
	{
		 session = request.getSession();

		DisplayCoursesDomain dcd=new DisplayCoursesDomain();
		dcd.setCourseName(request.getParameter("DepartmentsName"));
		session.setAttribute("DepartmentsName", request.getParameter("DepartmentsName"));
		
		//System.out.println(dcd.getCourseName());
		DisplaySemestersDao ddd=new DisplaySemestersDao();
		ArrayList<DepartmentsDomain> arl=ddd.selectSemesters(dcd);
		
		/*sess.setAttribute("depart",arl);
		response.sendRedirect("../JSP/Semesters.jsp");*/
		request.setAttribute("depart12",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/SemesterView.jsp");
		//RequestDispatcher rd=request.getRequestDispatcher("../JSP/Semesters.jsp");
	rd.forward(request, response);
	
	}
}
