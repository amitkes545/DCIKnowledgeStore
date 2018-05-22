package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SuperAdminUpdateManageKStoreDao;
import com.kds.KODE_DEV.domain.SuperAdminManageKnowStoreDomain;

@SuppressWarnings("serial")
public class SuperAdminUpdateKStore extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(SuperAdminUpdateKStore.class);

	HttpSession session = null;

	@Override
	public void run() throws ServletException, IOException {

		SuperAdminUpdateManageKStoreDao saumksDao = new SuperAdminUpdateManageKStoreDao();

		SuperAdminManageKnowStoreDomain samksDomain = new SuperAdminManageKnowStoreDomain();

		samksDomain.setKsId(request.getParameter("ksid"));
		samksDomain.setOrgId(request.getParameter("orgid"));
		samksDomain.setUserId(request.getParameter("userid"));
		samksDomain.setStatus(request.getParameter("status"));
		samksDomain.setKsSize(request.getParameter("kssize"));

		String s1 = saumksDao.updateValues(samksDomain);

		if (s1.equals("success")){
			
			String msg = "Record updated successfully !!";
			request.setAttribute("SuperAdminSuccess", msg);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/SuperAdminKnowManage.jsp");
			requestDispatcher.forward(request, response);
			
		}else if(s1.equals("failure")){
			
			String msg = "Record Updation Failed !!";
			request.setAttribute("SuperAdminFailure", msg);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/SuperAdminKnowManage.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}