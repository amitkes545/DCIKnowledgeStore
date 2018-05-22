package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.OwnerDeleteManageKStoreDao;
import com.kds.KODE_DEV.domain.OwnerManageKnowStoreDomain;

@SuppressWarnings("serial")
public class OwnerDeleteKStore extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(OwnerDeleteKStore.class);
	
	@Override
	public void run() throws ServletException, IOException {
		
		OwnerDeleteManageKStoreDao dlt = new OwnerDeleteManageKStoreDao();
		
		OwnerManageKnowStoreDomain mks = new OwnerManageKnowStoreDomain();
		
		mks.setKsId(request.getParameter("ksid"));
		mks.setOrgId(request.getParameter("orgid"));
		mks.setUserId(request.getParameter("userid"));
		mks.setKsSize(request.getParameter("kssize"));
		mks.setStatus(request.getParameter("status"));
		
		String s1 = dlt.deleteValues(mks);
	  
		if (s1.equals("success")){
			
			
		String	msg = "Knowledge Store Deleted Successfuly !!";

			request.setAttribute("OwnerSuccess", msg);

			/*RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("../JSP/Home.jsp");
			requestDispatcher.forward(request, response);*/
		} else {
			String msg2 = "Failed To Deleted Knowlwdge Store !!";
			request.setAttribute("OwnerFailure", msg2);
			
		}
		
		RequestDispatcher requestdispatcher = request
				.getRequestDispatcher("../JSP/OwnerKnowStoreManage.jsp");
		requestdispatcher.forward(request, response);	
	}
}