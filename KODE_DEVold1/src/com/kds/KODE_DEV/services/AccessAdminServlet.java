package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AdminAccessDao;
import com.kds.KODE_DEV.domain.AdminDomain;


@SuppressWarnings("serial")
public class AccessAdminServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(AccessAdminServlet.class);
	public void run() throws ServletException,IOException{
		
		 session = request.getSession();
		AdminDomain adminDomain=new AdminDomain();
		AdminAccessDao adminAccessDao=new AdminAccessDao();
		adminDomain.setAdminId(request.getParameter("adminid"));
		String organizationId=(String)session.getAttribute("orgid");
		LOGGER.info("organization id:"+organizationId+ "adminid:"+request.getParameter("adminid"));
		adminDomain.setOrgid(organizationId);
		
		String retstatus=adminAccessDao.searchAccessDetails(adminDomain);//selecting privilege from dao 
		LOGGER.info("result status is"+retstatus);
		if(retstatus!=null){
			String privilege=retstatus;
		 LOGGER.info("privilege value is"+privilege);
		 adminDomain.setPrivilege(privilege);
		 ArrayList<AdminDomain>  resultvalue=adminAccessDao.accessAdminDetails(adminDomain);
			
			LOGGER.info("Result value is"+resultvalue);
			ArrayList<AdminDomain>    AvailableValueinDB=adminAccessDao.retriveAdminDetails(adminDomain);
			 LOGGER.info("process id are"+AvailableValueinDB);
			 for(AdminDomain id:AvailableValueinDB)
			 {
				 LOGGER.info("AvailableValueinDB values are"+id.getProcessid());
			 }
			session.setAttribute("adminid", adminDomain.getAdminId());
			session.setAttribute("AvailableValueinDB",AvailableValueinDB);
			session.setAttribute("resultvalue", resultvalue);
			/*RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayAccessAdmin.jsp");
			rd.forward(request, response);*/
			 response.sendRedirect("../JSP/DisplayAccessAdmin.jsp");
	}
	}
}
