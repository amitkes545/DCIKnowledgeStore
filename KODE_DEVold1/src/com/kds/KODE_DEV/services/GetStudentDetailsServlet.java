package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileUploadException;

import com.kds.KODE_DEV.dao.GetStudentDetailsForAttendanceMarkingDAO;
import com.kds.KODE_DEV.domain.GetSessionForFacultyDomain;

public class GetStudentDetailsServlet extends CommonService {

	@Override
	public void run() throws ServletException, IOException, FileUploadException, Exception {
	
		session = request.getSession();

		String uisess = request.getParameter("choose_session");
	   // System.out.println("Studentdet==>"+uisess);
	
		GetStudentDetailsForAttendanceMarkingDAO getstudentDetailsForAttendanceMarking = new GetStudentDetailsForAttendanceMarkingDAO();
		GetSessionForFacultyDomain gsda = new GetSessionForFacultyDomain();
		/*
		 * System.out.println("DAO  Calling");
		 * System.out.println("Studentdet++++==>"+uisess);
		 */
		ArrayList<GetSessionForFacultyDomain> getStudentDetails = getstudentDetailsForAttendanceMarking
				.getStudentListAttendanceMarking((String) session.getAttribute("orgid"), uisess);
		
		
		//System.out.println("Studentdet------"+uisess);

		System.out.println("getStudentDetails=" + getStudentDetails.size());
		request.setAttribute("getStudentDetails", getStudentDetails);

		String orgid = (String) session.getAttribute("orgid");

		//System.out.println("orgid--->" + orgid);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/AttendanceMarking.jsp");
		requestDispatcher.forward(request, response);

	}

}
