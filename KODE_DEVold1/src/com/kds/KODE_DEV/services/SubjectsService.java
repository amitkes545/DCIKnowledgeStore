package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.DisplaySubjectListsDao;
import com.kds.KODE_DEV.domain.DepartmentsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

@SuppressWarnings("serial")
public class SubjectsService extends CommonService {
	//private static final long serialVersionUID = 1L;
   
	static final Logger LOGGER = Logger.getLogger(SubjectsService.class);
	public void run() throws ServletException,IOException
	{
		DisplayCoursesDomain dcd=new DisplayCoursesDomain();
		
		dcd.setCourseName(request.getParameter("SemesterName"));
		
		LOGGER.info(dcd.getCourseName());
		DisplaySubjectListsDao ddd=new DisplaySubjectListsDao();
		ArrayList<DepartmentsDomain> arl=ddd.selectSubjects(dcd);
		request.setAttribute("depart",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/Subjects.jsp");
	rd.forward(request, response);
	
	}
	}

