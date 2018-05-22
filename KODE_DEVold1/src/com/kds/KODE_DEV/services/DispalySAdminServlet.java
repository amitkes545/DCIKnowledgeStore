package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.DisplaySuperAdminDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.DisplayAdminDomain;


//@WebServlet("/DispalySAdminServlet")
@SuppressWarnings("serial")
public class DispalySAdminServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(DispalySAdminServlet.class);
	public void run() throws ServletException,IOException
	{
		session=request.getSession();
		
		DisplayAdminDomain daDomain=new DisplayAdminDomain();
		 DisplaySuperAdminDao dDao=new DisplaySuperAdminDao();
		String orgid=request.getParameter("orgId");
		String sid=request.getParameter("sid");
		daDomain.setSid(sid);
		 session.setAttribute("scriptOrgid",orgid);
		LOGGER.info("super admin is :"+sid);
		//selecting user ids from dao based on organization ID
		if(orgid!=null){
			ArrayList<String> resultuserid=dDao.getUserId(orgid);
			String[] resulArr = new String[resultuserid.size()];
			 resulArr = resultuserid.toArray(resulArr);
			
			 session.setAttribute("resultuserid",resulArr);
			 response.sendRedirect("../JSP/SuperAdminSearch.jsp");
		}
		 //selecting user details from dao
		else if(request.getParameter("orgid")!=null && request.getParameter("sid")!=null){
		 AdminDomain adomain1=dDao.retriveSuperAdminData(daDomain);
		 //System.out.println("create domain value is:"+adomain1.getAddress());
		 
		 session.setAttribute("AdminDomain", adomain1);
		 response.sendRedirect("../JSP/SuperAdminDetails.jsp");
	}
	}
    
}
