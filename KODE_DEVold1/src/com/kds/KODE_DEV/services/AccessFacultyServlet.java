package com.kds.KODE_DEV.services;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SuperAdminAccessDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class AccessFacultyServlet extends CommonService{
	
	static final Logger LOGGER = Logger.getLogger(AccessFacultyServlet.class);
	public void run() throws ServletException,IOException{
		
		session=request.getSession(true);
		AdminDomain adminDomain=new AdminDomain();
		SuperAdminAccessDao superAdminDao=new SuperAdminAccessDao();
		
		String facultyId=request.getParameter("facultyId");
		LOGGER.info("faculty id ::"+facultyId);
		//sadomain.setOrgid(request.getParameter("superAdminOrgid"));
		String Orgid=(String)session.getAttribute("orgid");
		LOGGER.info("organization id::"+Orgid);
		adminDomain.setOrgid(Orgid);
		adminDomain.setAdminId(facultyId);
		
		
			 String retstatus=superAdminDao.searchAccessDetails(adminDomain);
		LOGGER.info("result status is"+retstatus);
		if(retstatus!=null){
			String privilege=retstatus;
		 LOGGER.info("privilege value is"+privilege);
		 
		 adminDomain.setPrivilege(privilege);
			
		 ArrayList<AdminDomain>  AccessValues=superAdminDao.accessDetails(adminDomain);
			
			LOGGER.info("Result value is"+AccessValues);
			ArrayList<AdminDomain>    AvailableValueinDB=superAdminDao.retriveDetails(adminDomain);
			 LOGGER.info("process id are"+AvailableValueinDB);
			
			
			session.setAttribute("facultyId", request.getParameter("facultyId"));
			session.setAttribute("AvailableValueinDB", AvailableValueinDB);
			session.setAttribute("AccessValues", AccessValues);
			LOGGER.info("session values are:");
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DisplayAccessFaculty.jsp");
			requestDispatcher.forward(request, response);
			//response.sendRedirect("../JSP/DisplayAccessSuperAdmin.jsp");
			
			
		}
		
	}

}
