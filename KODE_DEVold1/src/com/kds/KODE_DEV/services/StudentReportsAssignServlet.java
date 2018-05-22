package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.CertiftStudentDao;
import com.kds.KODE_DEV.domain.SessionDomain;

/**
 * Servlet implementation class StudentReportsAssignServlet
 */

@SuppressWarnings("serial")
public class StudentReportsAssignServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(StudentReportsAssignServlet.class);
	public void run() throws ServletException,IOException{
		session=request.getSession();
		String assignid=request.getParameter("assignID");
		//System.out.println("assign id:"+assignid);
		CertiftStudentDao csdao=new CertiftStudentDao();
		SessionDomain asdomain=csdao.getDetailsOfAsignment(assignid);
		//System.out.println("result values:"+asdomain);
		session.setAttribute("SessionDomain", asdomain);
		response.sendRedirect("../JSP/AssignmentStudentDetails.jsp");
	}

}
