package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.OpenCoursesListDao;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class OpenCourses extends CommonService {
	private static final long serialVersionUID = 1L;
   
	public void run() throws ServletException,IOException
	{		
		////System.out.println("hi");
		OpenCoursesListDao ild=new OpenCoursesListDao();
		ArrayList<DisplayCoursesDomain> arl=ild.selectOpenCourses();
		request.setAttribute("openCoursesList",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/OpenCourses.jsp");
	rd.forward(request, response);
			
	}
}


