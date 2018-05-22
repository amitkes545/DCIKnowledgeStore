package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorKnowReportDao;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;

@SuppressWarnings("serial")
public class FacilitatorManageLibServlet extends CommonService{
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorManageLibServlet.class);

	public void run() throws ServletException, IOException {
		session=request.getSession();
		
		FacilitatorManageKnowStoreDomain aDomain = new FacilitatorManageKnowStoreDomain();
		aDomain.setLibId(request.getParameter("libid"));
		aDomain.setUserId(session.getAttribute("userId").toString());
		aDomain.setOrgId(session.getAttribute("orgId").toString());
		
		String libId = request.getParameter("libid");
		
		if(request.getParameter("libid")!=null && !(request.getParameter("libid").equalsIgnoreCase("All"))){

		
		FacilitatorKnowReportDao adao = new FacilitatorKnowReportDao();
		ArrayList<FacilitatorManageKnowStoreDomain>  dDomain = adao.fetchLibId(aDomain);
		
		request.setAttribute("msgvalue", dDomain);
		request.setAttribute("libid", libId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorManageLib.jsp");
		
		dispatcher.forward(request, response);
		
	}else if(request.getParameter("libid").equalsIgnoreCase("All")){
		
		
		FacilitatorKnowReportDao adao = new FacilitatorKnowReportDao();
		ArrayList<FacilitatorManageKnowStoreDomain>  dDomain = adao.fetchAllLibValue(aDomain);
	
	request.setAttribute("msgvalue", dDomain);
	request.setAttribute("libid", libId);
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacilitatorManageLib.jsp");
	
	dispatcher.forward(request, response);	
	}

  }
}