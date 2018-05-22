package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.InstitutionsDetailsDao;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

@SuppressWarnings("serial")
public class InstitutionDetailsService extends CommonService {

	
	public void run() throws ServletException,IOException
	{	DisplayCoursesDomain dc=new DisplayCoursesDomain();
		dc.setCourseName(request.getParameter("InstitutionName"));
		InstitutionsDetailsDao ild=new InstitutionsDetailsDao();
		ArrayList<DisplayCoursesDomain> arl=ild.selectInstitutionsDetails(dc);
		request.setAttribute("InstitutionsList",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/InstitutionsDetails.jsp");
		rd.forward(request, response); 
			
	}
}