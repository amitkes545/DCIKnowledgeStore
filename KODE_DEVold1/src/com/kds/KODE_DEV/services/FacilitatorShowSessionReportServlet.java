package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.kds.KODE_DEV.dao.FacilitatorShowSessionReportDao;
import com.kds.KODE_DEV.domain.FacilitatorSessionReportDomain;

@SuppressWarnings("serial")
public class FacilitatorShowSessionReportServlet extends CommonService {
	
	@Override
	public void run() throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//session.removeAttribute("MsgValue");
		String sessionID = request.getParameter("sessionid");
	    FacilitatorSessionReportDomain mksDomain = new FacilitatorSessionReportDomain();
		mksDomain.setSessionId(sessionID);
		FacilitatorShowSessionReportDao mksDao = new FacilitatorShowSessionReportDao();
		FacilitatorSessionReportDomain retStatus =mksDao.fetchValue(mksDomain);
		session.setAttribute("MsgValues", retStatus );
		response.sendRedirect("../JSP/FacilitatorShowSessionReport.jsp");

		}
}
