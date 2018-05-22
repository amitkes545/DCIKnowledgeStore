package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorKAssetsReportKnowDao;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;

@SuppressWarnings("serial")
public class FacilitatorKAssetsReportServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorKAssetsReportServlet.class);

	@Override
	public void run() throws ServletException, IOException {

		

		 session = request.getSession();

		//session.removeAttribute("MsgValue");

		String kID = request.getParameter("ksid");

		FacilitatorKAssetsReportDomain mksDomain = new FacilitatorKAssetsReportDomain();

		mksDomain.setKsId(kID);
		
		FacilitatorKAssetsReportKnowDao mksDao = new FacilitatorKAssetsReportKnowDao();

		// boolean a = loginDao.checkUserCredentials(lDomain);
		FacilitatorKAssetsReportDomain retStatus =mksDao.fetchValue(mksDomain);

		// LOGGER.info("retStatus: "+ retStatus);

		

		// if(a == true){
		//if ("success".equals(retStatus)) {

			//msg = "success";
			/*session.setAttribute("MsgValue", retStatus );
		
			response.sendRedirect("../JSP/FacilitatorKAssetsShowReport.jsp");*/
		
		request.setAttribute("MsgValue",retStatus);
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/FacilitatorKAssetsShowReport.jsp");
	    requestDispatcher.forward(request, response);

		}
	}