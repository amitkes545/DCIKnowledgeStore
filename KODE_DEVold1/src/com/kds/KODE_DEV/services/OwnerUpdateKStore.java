package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.OwnerUpdateManageKStoreDao;
import com.kds.KODE_DEV.domain.OwnerManageKnowStoreDomain;

@SuppressWarnings("serial")
public class OwnerUpdateKStore extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(OwnerUpdateKStore.class);
	
	//HttpSession session = null;

	@Override
	public void run() throws ServletException, IOException {
		OwnerUpdateManageKStoreDao ou = new OwnerUpdateManageKStoreDao();
		
		OwnerManageKnowStoreDomain mks = new OwnerManageKnowStoreDomain();
		
		mks.setKsId(request.getParameter("ksid"));
		
		LOGGER.info("ksid value is" + request.getParameter("ksid"));
		
		mks.setOrgId(request.getParameter("orgid"));
		mks.setUserId(request.getParameter("userid"));
		
		LOGGER.info("userid is" + request.getParameter("userid"));
		
		mks.setStatus(request.getParameter("status"));
		mks.setKsSize(request.getParameter("kssize"));

		String s1 = ou.updateValues(mks);
        String msg = null;
		if("success".equals(s1)){ 
			
		 msg = "Knowlwdge Store Updated Successfully !!";
			
			request.setAttribute("OwnerSuccess", msg);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/OwnerKnowStoreManage.jsp");
		requestDispatcher.forward(request, response);
		
		} else {
			
		String	 msg2 = "Failed To Update Knowledge Store !!";
			request.setAttribute("OwnerFailure", msg2);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/OwnerKnowStoreManage.jsp");
		requestDispatcher.forward(request, response);
		}
		
	}
}