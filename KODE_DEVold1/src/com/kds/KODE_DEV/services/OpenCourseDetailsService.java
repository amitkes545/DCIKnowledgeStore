package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.OpenCourseDetailsDao;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

@SuppressWarnings("serial")
public class OpenCourseDetailsService extends CommonService {

	public void run() throws ServletException,IOException
	{		
		DisplayCoursesDomain dcd= new DisplayCoursesDomain();
		dcd.setCourseName(request.getParameter("OpenCourseName"));
		////System.out.println("hi");
		OpenCourseDetailsDao ild=new OpenCourseDetailsDao();
		ArrayList<DisplayCoursesDomain> arl=ild.selectOpenCourseDetails(dcd);
		request.setAttribute("openCoursesList",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/OpenCourseDetails.jsp");
	rd.forward(request, response);
			
	}
}

