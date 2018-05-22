package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SuperAdminAccessDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class AccessStudentServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(AccessStudentServlet.class);
	public void run() throws ServletException,IOException{
		
		session=request.getSession();
		AdminDomain adminDomain=new AdminDomain();
		SuperAdminAccessDao superAdminDao=new SuperAdminAccessDao();
		
		String studentId=request.getParameter("studentId");
		LOGGER.info("faculty id ::"+studentId);
		//sadomain.setOrgid(request.getParameter("superAdminOrgid"));
		String Orgid=(String)session.getAttribute("orgid");
		LOGGER.info("organization id::"+Orgid);
		adminDomain.setOrgid(Orgid);
		adminDomain.setAdminId(studentId);
		
		
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
			
			
			session.setAttribute("studentId", request.getParameter("studentId"));
			session.setAttribute("AvailableValueinDB", AvailableValueinDB);
			session.setAttribute("AccessValues", AccessValues);
			LOGGER.info("session values are:");
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DisplayAccessStudent.jsp");
			requestDispatcher.forward(request, response);
			//response.sendRedirect("../JSP/DisplayAccessSuperAdmin.jsp");
			
			
		}
	}

}
