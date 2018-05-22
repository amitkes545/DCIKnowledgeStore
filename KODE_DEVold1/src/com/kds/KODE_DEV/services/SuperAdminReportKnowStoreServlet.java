package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AdminReportKnowStoreDao;
import com.kds.KODE_DEV.domain.AdminReportKnowStoreDomain;

@SuppressWarnings("serial")
public class SuperAdminReportKnowStoreServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(SuperAdminReportKnowStoreServlet.class);
	
	@Override
	public void run() throws ServletException, IOException {
		
	    session = request.getSession();
		session.removeAttribute("MsgValue");
		String kID = request.getParameter("ksid");
		AdminReportKnowStoreDomain mksDomain = new AdminReportKnowStoreDomain();
		mksDomain.setKsId(kID);
		AdminReportKnowStoreDao mksDao = new AdminReportKnowStoreDao();
		AdminReportKnowStoreDomain retStatus =mksDao.fetchValue(mksDomain);
		session.setAttribute("MsgValues", retStatus );
		response.sendRedirect("../JSP/SuperAdminShowReportKnowStore.jsp");

		}
	}