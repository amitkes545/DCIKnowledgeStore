package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AdminUpdateManageKStoreDao;
import com.kds.KODE_DEV.domain.AdminManageKnowStoreDomain;

@SuppressWarnings("serial")
public class AdminUpdateKStore extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(AdminUpdateKStore.class);

	//HttpSession session = null;

	@Override
	public void run() throws ServletException, IOException {

		AdminUpdateManageKStoreDao ou = new AdminUpdateManageKStoreDao();

		AdminManageKnowStoreDomain mks = new AdminManageKnowStoreDomain();

		mks.setKsId(request.getParameter("ksid"));
		LOGGER.info("ksid value is" + request.getParameter("ksid"));
		mks.setOrgId(request.getParameter("orgid"));
		mks.setUserId(request.getParameter("userid"));
		LOGGER.info("userid is" + request.getParameter("userid"));
		mks.setStatus(request.getParameter("status"));
		mks.setKsSize(request.getParameter("kssize"));

		String s1 = ou.updateValues(mks);

		if (s1.equals("success")){
			
			String msg = "Record updated successfully !!";
			request.setAttribute("AdminSuccess", msg);
			
		}else{
			
			String msg1 = "Record Updation Failed !!";
			request.setAttribute("AdminFailure", msg1);	
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/AdminKnowManage.jsp");
		requestDispatcher.forward(request, response);
	}
}