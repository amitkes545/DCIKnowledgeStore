package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;

@SuppressWarnings("serial")
public class FacultyShareViewServlet extends CommonService {

	FacilitatorKAssetsReportDomain fDomain = new FacilitatorKAssetsReportDomain();
	

	public void run() throws ServletException, IOException {
		session = request.getSession();
		System.out.println("in Faculty Share View Servlet");
		//fDomain.setLibId(request.getParameter("libid"));
		fDomain.setUserId(session.getAttribute("userId").toString());
		String sesid=request.getParameter("ses_id");
		System.out.println("sesid in FacultyShareViewServlet="+sesid);
		//fDomain.setOrgId(session.getAttribute("orgId").toString());
		System.out.println("userid in FacultyShareViewServlet="+session.getAttribute("userid").toString());
		System.out.println("userId in FacultyShareViewServlet="+session.getAttribute("userId").toString());
		//String libID = request.getParameter("libid");
			FacilitatorSelectKnowStoreIdDao assetDao = new FacilitatorSelectKnowStoreIdDao();
			ArrayList<FacilitatorKAssetsReportDomain> assetValue = assetDao.fetchAllAssetsInfo(fDomain);
			System.out.println("assetValue="+assetValue);
			request.setAttribute("aValue", assetValue);
			//request.setAttribute("libid", libID);
			// response.sendRedirect("../JSP/KSFacultyShare.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacultyClasswork.jsp?ses_id="+sesid+"&selected=share");
			dispatcher.forward(request, response);
	}
}