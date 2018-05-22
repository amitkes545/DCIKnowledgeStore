package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SuperAdminAccessDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class AccessSuperAdminServlet extends CommonService {
	//private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(AccessSuperAdminServlet.class);
	public void run() throws ServletException,IOException {
		
		session=request.getSession();
		AdminDomain adminDomain=new AdminDomain();
		SuperAdminAccessDao superAdminDao=new SuperAdminAccessDao();
		
		String superAdminId=request.getParameter("superAdminId");
		LOGGER.info("super admin id ::"+superAdminId);
		//sadomain.setOrgid(request.getParameter("superAdminOrgid"));
		String Orgid=(String)session.getAttribute("orgid");
		LOGGER.info("organization id::"+Orgid);
		adminDomain.setOrgid(Orgid);
		adminDomain.setAdminId(superAdminId);
		/*if(selectedOrgid !=null && superAdminId.isEmpty()){
			LOGGER.info("AccessSuperAdmin page forwarded");
			ArrayList<String> resultUserId=sadao.getUserID(selectedOrgid);
			 String[] resulArr = new String[resultUserId.size()];
			 resulArr = resultUserId.toArray(resulArr);
			 session.setAttribute("selectedOrgid",selectedOrgid);
			 session.setAttribute("resultUserId", resulArr);
			 RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AccessSuperAdmin.jsp");
			 requestDispatcher. forward(request, response);
			 //response.sendRedirect("../JSP/AccessSuperAdmin.jsp");
		} */
		// if(request.getParameter("superAdminOrgid")!=null && request.getParameter("superAdminId")!=null){
		
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
			
			 /*for(AdminDomain id:AvailableValueinDB)
			 {
				 LOGGER.info("AvailableValueinDB values are"+id.getProcessid());
			 }*/
			session.setAttribute("superAdminId", request.getParameter("superAdminId"));
			session.setAttribute("AvailableValueinDB", AvailableValueinDB);
			session.setAttribute("AccessValues", AccessValues);
			LOGGER.info("session values are:");
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DisplayAccessSuperAdmin.jsp");
			requestDispatcher.forward(request, response);
			//response.sendRedirect("../JSP/DisplayAccessSuperAdmin.jsp");
			
			
		}
		}

		}
		
	//}

