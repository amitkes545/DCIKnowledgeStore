package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.FacilitatorAssetsKnowDao;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;

@SuppressWarnings("serial")
public class ViewWorkWareService extends CommonService{
	
	
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
		//newly added 		
		String ksid=(String) session.getAttribute("KSID");		
		String orgid=(String) session.getAttribute("orgId");		
		System.out.println("ksid Audit Trail"+ksid);		
		System.out.println("orgid "+orgid);		
		fDomain.setOrgId(orgid);			
		fDomain.setKsId(ksid);		
		//newly added for audit trail purpose by dinesh
		
		fDomain.setCourseId(courseID);
		fDomain.setSubjctId(subjectID);
		System.out.println("=======================");
		System.out.println("libId "+libID);
		System.out.println("courseID "+courseID);
		System.out.println("subjectID "+subjectID);
		System.out.println("libId "+libID);
		//if(request.getParameter("libid")!=null && !(request.getParameter("libid").equalsIgnoreCase("All")))
		
		if(request.getParameter("libid")!=null && request.getParameter("courseId")!=null && request.getParameter("subjectId")!=null) 
		{
		System.out.println("in service="+libID);
		FacilitatorAssetsKnowDao assetDao = new FacilitatorAssetsKnowDao();
		ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.getItems(libID,courseID,subjectID,userid);
		
		request.setAttribute("aValue", assetValue);
		request.setAttribute("libid", libID);
		request.setAttribute("courseid", courseID);
		request.setAttribute("subjectid", subjectID);
		
		// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
		dispatcher.forward(request, response);
		}
	/*	else if(request.getParameter("libid").equalsIgnoreCase("All")){
			FacilitatorAssetsKnowDao assetDao = new FacilitatorAssetsKnowDao();
			ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.getAllItems(fDomain);
			
			request.setAttribute("aValue", assetValue);
			request.setAttribute("libid", libID);
			// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
			dispatcher.forward(request, response);
		}
*/
/*		if(request.getParameter("libid")!=null && !(request.getParameter("libid").equalsIgnoreCase("All"))){
		//System.out.println("in service="+libID);
		FacilitatorAssetsKnowDao assetDao = new FacilitatorAssetsKnowDao();
		ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.getItems(fDomain);
		
		request.setAttribute("aValue", assetValue);
		request.setAttribute("libid", libID);
		// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
		dispatcher.forward(request, response);
		}
		else if(request.getParameter("libid").equalsIgnoreCase("All"))
		{
			FacilitatorAssetsKnowDao assetDao = new FacilitatorAssetsKnowDao();
			ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.getAllItems(fDomain);
			
			request.setAttribute("aValue", assetValue);
			request.setAttribute("libid", libID);
			// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("courseId")!=null && !(request.getParameter("courseId").equalsIgnoreCase("All")))
		{
			FacilitatorAssetsKnowDao assetDao = new FacilitatorAssetsKnowDao();
			ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.getItems(fDomain);
			request.setAttribute("aValue", assetValue);
			request.setAttribute("courseId", courseID);
			// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("courseId").equalsIgnoreCase("All"))
		{
			FacilitatorAssetsKnowDao assetDao = new FacilitatorAssetsKnowDao();
			ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.getAllItems(fDomain);
			
			request.setAttribute("aValue", assetValue);
			request.setAttribute("courseId", courseID);
			// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("subjectId")!=null && !(request.getParameter("subjectId").equalsIgnoreCase("All")))
		{
			FacilitatorAssetsKnowDao assetDao = new FacilitatorAssetsKnowDao();
			ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.getItems(fDomain);
			request.setAttribute("aValue", assetValue);
			request.setAttribute("subjectId", subjectID);
			// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("subjectId").equalsIgnoreCase("All"))
		{
			FacilitatorAssetsKnowDao assetDao = new FacilitatorAssetsKnowDao();
			ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.getAllItems(fDomain);
			
			request.setAttribute("aValue", assetValue);
			request.setAttribute("subjectId", subjectID);
			// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
			dispatcher.forward(request, response);
		}*/
		}
	
	
	
	
	

}
