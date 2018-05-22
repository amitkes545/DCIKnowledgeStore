package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;

@SuppressWarnings("serial")
public class FacilitatorAssetKnowViewServlet extends CommonService {

	FacilitatorKAssetsReportDomain fDomain = new FacilitatorKAssetsReportDomain();
	

	public void run() throws ServletException, IOException {
		session = request.getSession();

		fDomain.setLibId(request.getParameter("libid"));
		String userid = (String) session.getAttribute("userId").toString();
		fDomain.setUserId(userid);
		fDomain.setOrgId(session.getAttribute("orgId").toString());


		String libID = request.getParameter("libid");
		String courseID = request.getParameter("courseId");
		String subjectID = request.getParameter("subjectId");
		fDomain.setCourseId(courseID);
		fDomain.setSubjctId(subjectID);
		System.out.println("=======================");
		System.out.println("libId "+libID);
		System.out.println("courseID "+courseID);
		System.out.println("subjectID "+subjectID);
		System.out.println("libId "+libID);
		
		if(request.getParameter("libid")!=null && request.getParameter("courseId")!=null && request.getParameter("subjectId")!=null) 
		{
			System.out.println("in service="+libID);
			FacilitatorSelectKnowStoreIdDao assetDao = new FacilitatorSelectKnowStoreIdDao();
			//ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.fetchAssetsInfo(fDomain);
			ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.viewItems(libID,courseID,subjectID,userid);
			System.out.println("arl="+assetValue);
			request.setAttribute("aValue", assetValue);
			request.setAttribute("libid", libID);
			request.setAttribute("courseid", courseID);
			request.setAttribute("subjectid", subjectID);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorKAssetsViewInList.jsp");
			dispatcher.forward(request, response);
		}
		/*if(request.getParameter("libid")!=null && !(request.getParameter("libid").equalsIgnoreCase("All"))){
		System.out.println("in service="+libID);
		FacilitatorSelectKnowStoreIdDao assetDao = new FacilitatorSelectKnowStoreIdDao();
		ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.fetchAssetsInfo(fDomain);
		System.out.println("arl="+assetValue);
		request.setAttribute("aValue", assetValue);
		request.setAttribute("libid", libID);
		// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorKAssetsViewInList.jsp");
		dispatcher.forward(request, response);
		}else if(request.getParameter("libid").equalsIgnoreCase("All")){
			FacilitatorSelectKnowStoreIdDao assetDao = new FacilitatorSelectKnowStoreIdDao();
			ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.fetchAllAssetsInfo(fDomain);
			
			request.setAttribute("aValue", assetValue);
			request.setAttribute("libid", libID);
			// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorKAssetsViewInList.jsp");
			dispatcher.forward(request, response);
		}*/
	}
}