package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.GroupStudentsNameDao;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

@SuppressWarnings("serial")
public class GroupDetailsService  extends CommonService{
	public void run() throws ServletException, IOException
	{	
		 session=request.getSession();
		DisplayCoursesDomain displayCoursesDomain=new DisplayCoursesDomain();
		displayCoursesDomain.setCourseName(request.getParameter("groupName"));
		//System.out.println("group name:"+request.getParameter("groupName"));
		session.setAttribute("groupId", request.getParameter("groupName"));
		displayCoursesDomain.setCourseDetails(session.getAttribute("userId").toString());
		displayCoursesDomain.setCourseFee(session.getAttribute("orgId").toString());
		GroupStudentsNameDao groupStudentNameDao=new GroupStudentsNameDao();
		ArrayList<String> arl=groupStudentNameDao.IndividualStudentDetails(displayCoursesDomain);
		session.setAttribute("studentinfo", arl);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/GroupParticipants.jsp");
		requestDispatcher.forward(request, response);
		/*RequestDispatcher rd=request.getRequestDispatcher("../JSP/GroupStudentLists.jsp");
		rd.forward(request, response);	*/	
	}
}