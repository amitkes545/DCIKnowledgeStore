package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.StudentClassWorkViewDao;
import com.kds.KODE_DEV.domain.FacultyShareFileDomain;

@SuppressWarnings("serial")
public class StudentClassWorkViewServlet extends CommonService {

	FacultyShareFileDomain fDomain = new FacultyShareFileDomain();
	

	public void run() throws ServletException, IOException {
		session = request.getSession();
		System.out.println("in StudentClassWorkViewServlet");
		//fDomain.setLibId(request.getParameter("libid"));
		String sesid=request.getParameter("ses_id");
		fDomain.setUploadedBy(session.getAttribute("userId").toString());
		fDomain.setSessionID(sesid);
		//fDomain.setOrgId(session.getAttribute("orgId").toString());
		System.out.println("userid="+session.getAttribute("userId").toString());
		//String libID = request.getParameter("libid");
		StudentClassWorkViewDao assetDao = new StudentClassWorkViewDao();
			ArrayList<FacultyShareFileDomain> assetValue = assetDao.fetchAllFilesInfo(fDomain);
			System.out.println("assetValue="+assetValue);
			request.setAttribute("aValue", assetValue);
			//request.setAttribute("libid", libID);
			// response.sendRedirect("../JSP/KSFacultyShare.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/StudentClasswork.jsp?ses_id="+sesid+"&selected=view");
			dispatcher.forward(request, response);
	}
}