package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.OwnerAControlKStoreDao;
import com.kds.KODE_DEV.domain.OwnerAControlKStoreDomain;

@SuppressWarnings("serial")
public class SuperAdminAccessControllServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(SuperAdminAccessControllServlet.class);

	@Override
	public void run() throws ServletException, IOException {

		// session.removeAttribute("MsgValue");

		String kID = request.getParameter("ksid");
		String status = request.getParameter("status");
		LOGGER.info(status);
		
		OwnerAControlKStoreDomain mksDomain = new OwnerAControlKStoreDomain();
		String retStatus = "";
		mksDomain.setKsId(kID);

		OwnerAControlKStoreDao mksDao = new OwnerAControlKStoreDao();
		//method for updating knowStore status active
		if ("Active".equals(status)) {
             
			retStatus = mksDao.updateKnowStoreStatusActive(mksDomain);

			if ("success".equals(retStatus)) {

				String msg = "Knowledge store active now !!";
				request.setAttribute("SuperAdminSuccess55", msg);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("../JSP/SuperAdminKnowAccessControl.jsp");
				requestDispatcher.forward(request, response);
			}
         // method for updating knowStore status inactive
		} else if ("InActive".equals(status)) {
			retStatus = mksDao.updateKnowStoreStatusInActive(mksDomain);

			if ("success".equals(retStatus)) {

				String msg2 = "Knowledge store inactive now !!";

				request.setAttribute("SuperAdminSuccess55", msg2);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("../JSP/SuperAdminKnowAccessControl.jsp");
				requestDispatcher.forward(request, response);
			}   
		}
	}
}