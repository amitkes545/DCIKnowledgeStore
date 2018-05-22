package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.VocationalCourseDetailsDao;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class VocationalCoursesService extends CommonService {
	private static final long serialVersionUID = 1L;
   
	public void run() throws ServletException,IOException
	{		DisplayCoursesDomain dcd=new DisplayCoursesDomain();
		dcd.setCourseName(request.getParameter("VocationalCourseName"));
		VocationalCourseDetailsDao ild=new VocationalCourseDetailsDao();
		ArrayList<DisplayCoursesDomain> arl=ild.selectVocationalCourseDetails(dcd);
		request.setAttribute("openCoursesList",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/VocationalCourseDetails.jsp");
	rd.forward(request, response);
			
	}
}
