package com.kds.KODE_DEV.services;


import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.DisplayAdminDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.DisplayAdminDomain;

@SuppressWarnings("serial")
public class DisplayFacultyServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(DisplayFacultyServlet.class);
    public void run() throws ServletException,IOException
    {
    	session=request.getSession();
    	 String adminid=request.getParameter("facultyid");
    	 
		DisplayAdminDomain daDomain=new DisplayAdminDomain();
		 daDomain.setAdminId(adminid);
		 LOGGER.info("domain value is:"+daDomain);
		 DisplayAdminDao dDao=new DisplayAdminDao();
		 
		 
		 AdminDomain adomain1=dDao.retriveData(daDomain);
		 LOGGER.info("create domain value is:"+adomain1.getOrgid());
		 
		 session.setAttribute("AdminDomain", adomain1);
		 response.sendRedirect("../JSP/FacultyDetails.jsp");
    	 }
    }
    

