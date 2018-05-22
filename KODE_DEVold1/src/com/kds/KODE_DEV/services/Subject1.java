package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.DisplaySemestersDao;
import com.kds.KODE_DEV.dao.DisplaySubjectListsDao;
import com.kds.KODE_DEV.domain.DepartmentsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

@SuppressWarnings("serial")
public class Subject1 extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(Subject1.class);
	public void run() throws ServletException,IOException
	{
	    session = request.getSession();

		LOGGER.info("hi");
		DisplayCoursesDomain dcd=new DisplayCoursesDomain();
		dcd.setCourseName(session.getAttribute("DepartmentsName").toString());
		
		LOGGER.info(dcd.getCourseName());
		DisplaySemestersDao ddd=new DisplaySemestersDao();
		ArrayList<DepartmentsDomain> arl=ddd.selectSemesters(dcd);
		
		/*sess.setAttribute("depart",arl);
		response.sendRedirect("../JSP/Semesters.jsp");*/
		request.setAttribute("depart12",arl);
		
		
		
		DisplayCoursesDomain dcd1=new DisplayCoursesDomain();
		
		dcd1.setCourseName(request.getParameter("SemesterName"));
		
		LOGGER.info(dcd1.getCourseName());
		DisplaySubjectListsDao ddd1=new DisplaySubjectListsDao();
		ArrayList<DepartmentsDomain> arl1=ddd1.selectSubjects(dcd1);
		request.setAttribute("depart",arl1);
		request.setAttribute("DepartName", request.getParameter("SemesterName"));
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/SemesterView.jsp");
	rd.forward(request, response);
	
	}
	}

