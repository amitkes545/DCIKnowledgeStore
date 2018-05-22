package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AdminReportKnowStoreDao;
import com.kds.KODE_DEV.domain.AdminReportKnowStoreDomain;

@SuppressWarnings("serial")
public class AdminUpdateManageKnowStore extends CommonService{
	
	static final Logger LOGGER = Logger.getLogger(AdminUpdateManageKnowStore.class);

	public void run() throws ServletException, IOException {
		
		AdminReportKnowStoreDomain aDomain = new AdminReportKnowStoreDomain();
		aDomain.setKsId(request.getParameter("ksid"));
		
		AdminReportKnowStoreDao adao = new AdminReportKnowStoreDao();
		aDomain = adao.fetchValue(aDomain);
		
		request.setAttribute("msgvalue", aDomain);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/AdminUpdateDelete.jsp");
		dispatcher.forward(request, response);
		
	}
}
