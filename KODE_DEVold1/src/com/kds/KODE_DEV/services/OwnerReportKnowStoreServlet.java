package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.OwnerReportKnowStoreDao;
import com.kds.KODE_DEV.domain.OwnerReportKnowStoreDomain;

@SuppressWarnings("serial")
public class OwnerReportKnowStoreServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(OwnerReportKnowStoreServlet.class);

	@Override
	public void run() throws ServletException, IOException {

		 session = request.getSession();

		//session.removeAttribute("MsgValue");

		String kID = request.getParameter("ksid");

		OwnerReportKnowStoreDomain mksDomain = new OwnerReportKnowStoreDomain();

		mksDomain.setKsId(kID);
		
		OwnerReportKnowStoreDao mksDao = new OwnerReportKnowStoreDao();

		OwnerReportKnowStoreDomain reStatus =mksDao.fetchValue(mksDomain);
		
		
		     request.setAttribute("MsgValue", reStatus);
		     
		   RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/OwnerShowReportKnowStore.jsp");
		   requestDispatcher.forward(request, response);

			/*session.setAttribute("MsgValue", retStatus );
		
			response.sendRedirect("../JSP/OwnerShowReportKnowStore.jsp");*/
		}
	}