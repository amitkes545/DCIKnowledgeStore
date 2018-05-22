package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileUploadException;

import com.kds.KODE_DEV.dao.StudentDashBoardDao;
import com.kds.KODE_DEV.domain.AssessmentDomain;
import com.kds.KODE_DEV.domain.FacilitatorKnowReportDomain;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;
import com.kds.KODE_DEV.domain.SessionDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@SuppressWarnings("serial")
public class StudentDashBoardService extends CommonService{

	@Override
	public void run() throws ServletException, IOException {
			session=request.getSession();	
			//System.out.println("in student service");
		UsersInfoDomain uid=new UsersInfoDomain();
		//System.out.println("in student service1");
		uid.setOrgId(session.getAttribute("orgId").toString());
		uid.setUserId(session.getAttribute("userId").toString());
		//System.out.println("in student service:"+session.getAttribute("userId").toString());
		//uid.setUserId(session.getAttribute("userid").toString());
		StudentDashBoardDao fd=new StudentDashBoardDao();
		//ArrayList<RetriveImagesDomain> arl=new ArrayList<RetriveImagesDomain>();
		//arl=fd.selectKnowledgeAssetsName(uid);
		//request.setAttribute("knowlegeAssets", arl);
		ArrayList<AssessmentDomain> arl1=new ArrayList<AssessmentDomain>();
		arl1= fd.selectAsssesmentName(uid);
		request.setAttribute("assessments", arl1);
		ArrayList<AssessmentDomain> arl2=new ArrayList<AssessmentDomain>();
		arl2= fd.selectAsssignmentName(uid);
		request.setAttribute("assignments", arl2);
		ArrayList<SessionDomain> arl3=new ArrayList<SessionDomain>();
		arl3= fd.selectSessionDetails(uid);
		request.setAttribute("sessionDetails", arl3);
		ArrayList<FacilitatorKnowReportDomain> arl4=new ArrayList<FacilitatorKnowReportDomain>();
		arl4= fd.selectLibraryInfo(uid);
		request.setAttribute("libraryDetails", arl4);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/Studentdashboard.jsp");
		rd.forward(request, response);
		
	}
	

}
