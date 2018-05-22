package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.ParticipantCourses;
import com.kds.KODE_DEV.domain.DepartmentsDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@SuppressWarnings("serial")
public class ParticipantCoursesService extends CommonService {
	
	public void run() throws ServletException,IOException
	{
	    session = request.getSession();
		//System.out.println("hi");
		UsersInfoDomain uid=new UsersInfoDomain();
		uid.setUserId(session.getAttribute("userId").toString());
		uid.setOrgId(session.getAttribute("orgId").toString());
		
		ParticipantCourses pc=new ParticipantCourses();
		ArrayList<DepartmentsDomain> arl=pc.selectSemesters(uid);
		request.setAttribute("depart12",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/ParticipantSemesterView.jsp");
		rd.forward(request, response);
		
	}

}
