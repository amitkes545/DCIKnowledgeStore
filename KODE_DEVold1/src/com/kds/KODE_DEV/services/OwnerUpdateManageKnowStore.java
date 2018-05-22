package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.OwnerReportKnowStoreDao;
import com.kds.KODE_DEV.domain.OwnerReportKnowStoreDomain;

@SuppressWarnings("serial")
public class OwnerUpdateManageKnowStore extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(OwnerUpdateManageKnowStore.class);

	public void run() throws ServletException, IOException {
		OwnerReportKnowStoreDomain rDomain = new OwnerReportKnowStoreDomain();
		rDomain.setKsId(request.getParameter("ksid"));
		
		OwnerReportKnowStoreDao rks = new OwnerReportKnowStoreDao();
		rDomain = rks.fetchValue(rDomain);
		
		request.setAttribute("msgvalue", rDomain);
		
		RequestDispatcher requestdispatcher = request
				.getRequestDispatcher("../JSP/OwnerUpdateDelete.jsp");
		requestdispatcher.forward(request, response);

	}
}