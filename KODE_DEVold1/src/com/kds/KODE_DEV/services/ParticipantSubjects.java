package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.ParticipantCourses;
import com.kds.KODE_DEV.dao.ParticipantSubjectsDao;
import com.kds.KODE_DEV.domain.DepartmentsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@SuppressWarnings("serial")
public class ParticipantSubjects extends CommonService {
	
   
	public void run() throws ServletException,IOException
	{
		 session = request.getSession();

		//System.out.println("hi");
		//System.out.println(request.getParameter("SemesterName"));
		UsersInfoDomain uid=new UsersInfoDomain();
		uid.setUserId(session.getAttribute("userId").toString());
		uid.setOrgId(session.getAttribute("orgId").toString());
		
		ParticipantCourses pc=new ParticipantCourses();
		ArrayList<DepartmentsDomain> arl=pc.selectSemesters(uid);
		request.setAttribute("depart12",arl);
		
		
		DisplayCoursesDomain dcd1=new DisplayCoursesDomain();
		dcd1.setCourseDetails(session.getAttribute("orgId").toString());
		dcd1.setCourseFee(session.getAttribute("DepartmentsName").toString());
		dcd1.setCourseName(request.getParameter("SemesterName"));
		
		//System.out.println(dcd1.getCourseName());
		ParticipantSubjectsDao ddd1=new ParticipantSubjectsDao();
		ArrayList<DepartmentsDomain> arl1=ddd1.selectSubjects(dcd1);
		request.setAttribute("depart",arl1);
		request.setAttribute("DepartName", request.getParameter("SemesterName"));
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/ParticipantSemesterView.jsp");
	rd.forward(request, response);
	
	}
	}

