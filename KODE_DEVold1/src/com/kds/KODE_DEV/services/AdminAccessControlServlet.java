package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.OwnerAControlKStoreDao;
import com.kds.KODE_DEV.domain.OwnerAControlKStoreDomain;

@SuppressWarnings("serial")
public class AdminAccessControlServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(AdminAccessControlServlet.class);
	
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
             
			retStatus = mksDao.updateValues(mksDomain);

			if ("success".equals(retStatus)) {

				String msg = "Knowledge store active now !!";
				request.setAttribute("AdminSuccess", msg);
			}
         // method for updating knowStore status inactive
		} else if ("InActive".equals(status)) {
			retStatus = mksDao.updateValuesInActive(mksDomain);

			if ("success".equals(retStatus)) {

				String msg2 = "Knowledge store inactive now !!";

				request.setAttribute("AdminSuccess", msg2);	
			}
            
		}
		//forwarding success message to home page
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("../JSP/Home.jsp");
		requestDispatcher.forward(request, response);
	}
}