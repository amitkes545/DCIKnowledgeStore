package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorKnowReportDao;
import com.kds.KODE_DEV.domain.FacilitatorKnowReportDomain;

@SuppressWarnings("serial")
public class FacilitatorKnowReportServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorKnowReportServlet.class);

	@Override
	public void run() throws ServletException, IOException {

		// LOGGER.info("run hit");

		 session = request.getSession(true);

		//session.removeAttribute("MsgValue");

		String libId = request.getParameter("libid");
		//String libname = request.getParameter("nameoflib");

		FacilitatorKnowReportDomain mksDomain = new FacilitatorKnowReportDomain();

		mksDomain.setLibId(libId);
		//mksDomain.setLibName(libname);
		
		
		FacilitatorKnowReportDao mksDao = new FacilitatorKnowReportDao();

		// boolean a = loginDao.checkUserCredentials(lDomain);
		FacilitatorKnowReportDomain retStatus =mksDao.fetchValue(mksDomain);

		
			request.setAttribute("MsgValue", retStatus );
		
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/FacilitatorKnowShowReport.jsp");
			requestDispatcher.forward(request, response);

		}
	}