package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao;
import com.kds.KODE_DEV.domain.FacilitatorKnowReportDomain;

@SuppressWarnings("serial")
public class FacilitatorLibInfoListViewKnowServlet extends CommonService {
	

	FacilitatorKnowReportDomain fDomain = new FacilitatorKnowReportDomain();
	FacilitatorSelectKnowStoreIdDao libDao = new FacilitatorSelectKnowStoreIdDao();

		public void run() throws ServletException, IOException {
			session = request.getSession();
			
			String userId = session.getAttribute("userid").toString();
			String orgId = session.getAttribute("orgId").toString();
			String ksID = request.getParameter("ksid");

			fDomain.setKsId(ksID);
			
			ArrayList<FacilitatorKnowReportDomain> libValue = libDao.fetchAllLibInfo(userId,orgId);
			//System.out.println("HEY Service Value is+"+libValue);
			
			session.setAttribute("libValue", libValue);
			session.setAttribute("ksid", ksID);
			response.sendRedirect("../JSP/FacilitatorLibInfoListViewKnow.jsp");
			/*RequestDispatcher dispatcher = request
					.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
			dispatcher.forward(request, response);
	*/
		}
	
	
	
	
	

}
