package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.ParticipantAssignmentDetailsDao;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

@SuppressWarnings("serial")
public class ParticipantAssignmentDetailsService  extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(ParticipantAssignmentDetailsService.class);
	public void run() throws ServletException,IOException
	{		
		 session=request.getSession();
		DisplayCoursesDomain ri=new DisplayCoursesDomain(); 
		String assignid=request.getParameter("assessmentId");
		System.out.println("assignment id in ParticipantAssignmentDetailsService:"+assignid);
		session.setAttribute("assignmentid", assignid);
		if(session.getAttribute("userId")==null){
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/error.jsp");
			rd.forward(request, response);
		}
		ri.setImagePath(session.getAttribute("userId").toString());
		ri.setCourseName(request.getParameter("assessmentId"));
		ri.setCourseFee(session.getAttribute("orgId").toString());
		ri.setUploady(request.getParameter("facultyName"));
		
		ArrayList<AssessmentsDetailsDomain> arl=null;
		if(assignid!=null && !(assignid.equalsIgnoreCase("All"))){
		ParticipantAssignmentDetailsDao padd=new ParticipantAssignmentDetailsDao();
		arl=padd.selectCourses(ri);
		}else if(assignid.equalsIgnoreCase("All")){
			ParticipantAssignmentDetailsDao padd=new ParticipantAssignmentDetailsDao();
			arl=padd.selectAllCourses(ri);
		}
		request.setAttribute("recipientTypeForAssignment",arl);
		request.setAttribute("asignID", assignid);
		request.setAttribute("assignId", assignid);
		session.setAttribute("recipientTypeForAssignment",arl);
		String Tabmsg="tab2";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/StudentWorkware.jsp?tabValue="+Tabmsg);
		requestDispatcher.forward(request, response);
		//response.sendRedirect("../JSP/ParticipantAssignment.jsp");
	}
}
