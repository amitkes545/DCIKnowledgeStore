package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacultyKnowledgeAssetsDetailsDao;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@SuppressWarnings("serial")
public class FacultyKnowledgeAssetsDetailsService  extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacultyKnowledgeAssetsDetailsService.class);
	
	public void run() throws ServletException,IOException
	{		
		HttpSession session=request.getSession();	
		UsersInfoDomain uid=new UsersInfoDomain();
		uid.setCreatedBy(session.getAttribute("userId").toString());
		uid.setOrgId(session.getAttribute("orgId").toString());
		uid.setUserId(request.getParameter("ksId"));
		FacultyKnowledgeAssetsDetailsDao fkad=new FacultyKnowledgeAssetsDetailsDao();
		ArrayList<AssessmentsDetailsDomain> arl=fkad.knowledgeAssestsDetails(uid);
		request.setAttribute("assessmentDetails",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacultyKnowledgeAssetsDetails.jsp");
		rd.forward(request, response);
	}
}
