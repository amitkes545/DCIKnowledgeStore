package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.CertiftStudentDao;
import com.kds.KODE_DEV.domain.SessionDomain;

@SuppressWarnings("serial")
public class StudentReportsServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(StudentReportsServlet.class);
	public void run() throws ServletException,IOException{
		session=request.getSession();
		String assid=request.getParameter("asID");
		LOGGER.info("assess id:"+assid);
		CertiftStudentDao csdao=new CertiftStudentDao();
		SessionDomain asdomain=csdao.getDetailsOfStudent(assid);
		LOGGER.info("result values:"+asdomain);
		session.setAttribute("SessionDomain", asdomain);
		response.sendRedirect("../JSP/AssessmentStudentDetails.jsp");
	}
}
