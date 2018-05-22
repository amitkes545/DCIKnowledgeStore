package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.kds.KODE_DEV.dao.FacultySessionDetailsDao;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@SuppressWarnings("serial")
public class FacultySessionDetailsService extends CommonService {
	public void run() throws ServletException,IOException
	{		
		HttpSession session=request.getSession();	
		UsersInfoDomain uid=new UsersInfoDomain();
		uid.setCreatedBy(session.getAttribute("userId").toString());
		uid.setOrgId(session.getAttribute("orgId").toString());
		uid.setUserId(request.getParameter("sessionId"));
		FacultySessionDetailsDao fkad=new FacultySessionDetailsDao();
		ArrayList<AssessmentsDetailsDomain> arl=fkad.knowledgeAssestsDetails(uid);
		request.setAttribute("assessmentDetails",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacultySessionDetails.jsp");
		rd.forward(request, response);
	}
}
