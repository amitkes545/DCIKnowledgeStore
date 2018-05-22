package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorBuildKnowLibDao;
import com.kds.KODE_DEV.dao.FacultyDashBoardDao;
import com.kds.KODE_DEV.domain.AssessmentDomain;
import com.kds.KODE_DEV.domain.FacilitatorKnowReportDomain;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;
import com.kds.KODE_DEV.domain.SessionDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@SuppressWarnings("serial")
public class FacilitatorDashBoardService extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorDashBoardService.class);
	public void run() throws ServletException,IOException
	{		
		session=request.getSession();
		FacilitatorBuildKnowLibDao fbkDAo = new FacilitatorBuildKnowLibDao();		
		String orgid=(String)session.getAttribute("orgid");
		System.out.println("Session="+session);
		UsersInfoDomain uid=new UsersInfoDomain();
		String userid=(String)session.getAttribute("userId");
		if(userid==null)
			 response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired");
		uid.setOrgId(session.getAttribute("orgId").toString());
		uid.setUserId(session.getAttribute("userId").toString());
		FacultyDashBoardDao fd=new FacultyDashBoardDao();
		ArrayList<RetriveImagesDomain> arl=new ArrayList<RetriveImagesDomain>();
		arl=fd.selectKnowledgeAssetsName(uid);
		request.setAttribute("knowlegeAssets", arl);
		ArrayList<AssessmentDomain> arl1=new ArrayList<AssessmentDomain>();
		arl1= fd.selectAsssesmentName(uid);
		request.setAttribute("assessments", arl1);
		/*ArrayList<AssessmentDomain> arl2=new ArrayList<AssessmentDomain>();
		arl2= fd.selectAsssignmentName(uid);*/
		
		int knowStoreSize = fbkDAo.selectFacultyKnowSpace(orgid);
		String allLibSize2 = fbkDAo.selectAllLibSpace(userid);
		//request.setAttribute("assignments", arl2);
		request.setAttribute("totalspace", knowStoreSize);
		request.setAttribute("usedspace", allLibSize2);
		ArrayList<SessionDomain> arl3=new ArrayList<SessionDomain>();
		arl3= fd.selectSessionDetails(uid);
		request.setAttribute("sessionDetails", arl3);
		ArrayList<FacilitatorKnowReportDomain> arl4=new ArrayList<FacilitatorKnowReportDomain>();
		arl4= fd.selectLibraryInfo(uid);
		request.setAttribute("libraryDetails", arl4);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/dashboard.jsp");
		rd.forward(request, response);
		
	}

}
