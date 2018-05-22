package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao;
import com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;

@SuppressWarnings("serial")
public class StudentAssetKnowViewServlet extends CommonService {

	FacilitatorKAssetsReportDomain fDomain = new FacilitatorKAssetsReportDomain();
	
	
	public void run() throws ServletException, IOException {
		session = request.getSession();
      
       
       // System.out.println("cid   and s id  "+courseId+"=="+subjectId);
        
		System.out.println("hii fetching data");
		
		System.out.println("Lib Id==>"+request.getParameter("libid"));
		
		fDomain.setLibId(request.getParameter("libid"));
		fDomain.setUserId(session.getAttribute("userId").toString());
		fDomain.setOrgId(session.getAttribute("orgId").toString());
		fDomain.setSubjectId(request.getParameter("subjectId"));
		fDomain.setCourseId(request.getParameter("courseId"));
		
		//audit trail purpose by dinesh - start
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		FacilitatorSelectKsidKStoreDao dobj2 = new FacilitatorSelectKsidKStoreDao();
			String ksidHidden = dobj2.fetchValue1(orgid, userid);
			System.out.println("ksidHidden"+ksidHidden);
			System.out.println("ksid Audit Trail"+ksidHidden);
			System.out.println("orgid "+orgid);
			fDomain.setKsId(ksidHidden);
	
	//audit trail purpose by dinesh - end
	
		System.out.println("SubId=====>"+request.getParameter("subjectId"));
		
		String libID = request.getParameter("libid");
		if(request.getParameter("libid")!=null && !(request.getParameter("subject").equalsIgnoreCase("All"))){
		System.out.println("in service StudentAssetKnowViewServlet="+libID);
		FacilitatorSelectKnowStoreIdDao assetDao = new FacilitatorSelectKnowStoreIdDao();
		ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.fetchAssetsInfo(fDomain);
		System.out.println("arl="+assetValue);
		request.setAttribute("aValue", assetValue);
		request.setAttribute("libid", libID);
		// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/KnowStoreStudent.jsp");
		dispatcher.forward(request, response);
		}else if(request.getParameter("subject").equalsIgnoreCase("All")){
			FacilitatorSelectKnowStoreIdDao assetDao = new FacilitatorSelectKnowStoreIdDao();
			ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.fetchAllStudentAssetsInfo(fDomain);
			
			request.setAttribute("aValue", assetValue);
			request.setAttribute("libid", libID);
			// response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/KnowStoreStudent.jsp");
			dispatcher.forward(request, response);
		}
	}
}