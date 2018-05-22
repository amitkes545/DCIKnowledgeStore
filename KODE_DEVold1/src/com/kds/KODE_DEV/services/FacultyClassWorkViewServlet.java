package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.StudentClassWorkFileDao;
import com.kds.KODE_DEV.domain.ClassWorkFileDomain;

@SuppressWarnings("serial")
public class FacultyClassWorkViewServlet extends CommonService {

	ClassWorkFileDomain fDomain = new ClassWorkFileDomain();
	

	public void run() throws ServletException, IOException {
		System.out.println(" in faculty classwork view servlet");
		session = request.getSession();
		//String sesid=session.getAttribute("SessionID").toString();
		String sesid=request.getParameter("ses_id");
		//fDomain.setLibId(request.getParameter("libid"));
		System.out.println("sesid in servlet"+sesid);
		fDomain.setSessionID(sesid);
		fDomain.setUploadedBy(session.getAttribute("userId").toString());
		//fDomain.setOrgId(session.getAttribute("orgId").toString());
		
		//String libID = request.getParameter("libid");
		StudentClassWorkFileDao assetDao = new StudentClassWorkFileDao();
			ArrayList<ClassWorkFileDomain> assetValue = assetDao.fetchStudentClassWorkFilesInfo(fDomain);
			System.out.println("assetValue="+assetValue);
			request.setAttribute("CWValue", assetValue);
			//request.setAttribute("libid", libID);
			// response.sendRedirect("../JSP/KSFacultyShare.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacultyClasswork.jsp?ses_id="+sesid+"&selected=view");
			dispatcher.forward(request, response);
	}
}