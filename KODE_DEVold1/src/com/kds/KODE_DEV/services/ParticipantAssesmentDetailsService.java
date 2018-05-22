package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.ParticipantAssessmentDetailsDao;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

@SuppressWarnings("serial")
public class ParticipantAssesmentDetailsService  extends CommonService {
	public void run() throws ServletException,IOException
	{		
		//System.out.println("hi");
		 session=request.getSession();
		String assessmentId=request.getParameter("assessmentId");
		//System.out.println("assessment id in service:"+assessmentId);
		session.setAttribute("assessmentid",assessmentId);
		if(session.getAttribute("userId")==null){
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/error.jsp");
			rd.forward(request, response);
		}
		DisplayCoursesDomain ri=new DisplayCoursesDomain(); 
		ri.setImagePath(session.getAttribute("userId").toString());
		
		ri.setCourseName(request.getParameter("assessmentId"));
		ri.setCourseFee(session.getAttribute("orgId").toString());
		ri.setUploady(request.getParameter("facultyName"));
		//System.out.println("assessment id in upload:"+session.getAttribute("uploadedId"));
		//ri.setUploady(session.getAttribute("uploadedId").toString());
		
		ArrayList<AssessmentsDetailsDomain> arl=null;
		if(assessmentId!=null && !(assessmentId.equalsIgnoreCase("All"))){
		ParticipantAssessmentDetailsDao padd=new ParticipantAssessmentDetailsDao();
		arl=padd.selectCourses(ri);
		}else if(assessmentId.equalsIgnoreCase("All")){
			ParticipantAssessmentDetailsDao padd=new ParticipantAssessmentDetailsDao();
			 arl=padd.selectAllCourses(ri);	
		}
		request.setAttribute("assessmentDetails",arl);
		request.setAttribute("asessID", assessmentId);
		session.setAttribute("assessmentDetails",arl);
		//response.sendRedirect("../JSP/ParticipantAssessment.jsp");
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/StudentWorkware.jsp");
		requestDispatcher.forward(request, response);
	}
}
