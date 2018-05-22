package com.kds.KODE_DEV.dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.services.CommonService;

@SuppressWarnings("serial")
public class GroupDetailsService  extends CommonService{
	public void run() throws ServletException, IOException
	{	
		 session=request.getSession();
		DisplayCoursesDomain dcd=new DisplayCoursesDomain();
		dcd.setCourseName(request.getParameter("groupName"));
		session.setAttribute("groupId", request.getParameter("groupName"));
		dcd.setCourseDetails(session.getAttribute("userId").toString());
		dcd.setCourseFee(session.getAttribute("orgId").toString());
		GroupStudentsNameDao isd=new GroupStudentsNameDao();
		ArrayList<String> arl=isd.IndividualStudentDetails(dcd);
		request.setAttribute("studentinfo", arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/GroupStudentLists.jsp");
		rd.forward(request, response);		
	}
}