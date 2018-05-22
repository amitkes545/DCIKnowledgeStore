package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SuperAdminReportKnowStoreDao;
import com.kds.KODE_DEV.domain.SuperAdminReportKnowStoreDomain;

@SuppressWarnings("serial")
public class SuperAdminUpdateManageKnowStore extends CommonService{
	
	static final Logger LOGGER = Logger.getLogger(SuperAdminUpdateManageKnowStore.class);

	public void run() throws ServletException, IOException {
		
		SuperAdminReportKnowStoreDomain aDomain = new SuperAdminReportKnowStoreDomain();
		aDomain.setKsId(request.getParameter("ksid"));
		
		SuperAdminReportKnowStoreDao adao = new SuperAdminReportKnowStoreDao();
		aDomain = adao.fetchValue(aDomain);
		
		request.setAttribute("msgvalue", aDomain);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/SuperAdminKnowUpdateDelete.jsp");
		requestDispatcher.forward(request, response);
	}
}
