package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SuperAdminDeleteManageKStoreDao;
import com.kds.KODE_DEV.domain.SuperAdminManageKnowStoreDomain;

@SuppressWarnings("serial")
public class SuperAdminDeletekStore extends CommonService {

	static final Logger LOGGER = Logger.getLogger(SuperAdminDeletekStore.class);
	
	@Override
	public void run() throws ServletException, IOException {
		
		SuperAdminDeleteManageKStoreDao sadmDao = new SuperAdminDeleteManageKStoreDao();
		
		SuperAdminManageKnowStoreDomain samksDomain = new SuperAdminManageKnowStoreDomain();
		
		samksDomain.setKsId(request.getParameter("ksid"));
		samksDomain.setOrgId(request.getParameter("orgid"));
		samksDomain.setUserId(request.getParameter("userid"));
		samksDomain.setKsSize(request.getParameter("kssize"));
		samksDomain.setStatus(request.getParameter("status"));
		
		String s1 = sadmDao.deleteValues(samksDomain);
		
		if (s1.equals("success")){
		
			String msg = "Knowledge store deleted successfully !!";
		    request.setAttribute("SuperAdminSuccess11", msg);
		    RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/SuperAdminKnowManage.jsp");
			requestDispatcher.forward(request, response);
		
		}else if(s1.equals("failure")){
			
			String msg = "Failed to delete knowlwdge store ! please try again ";
			request.setAttribute("SuperAdminFailure111", msg);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/SuperAdminKnowManage.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}