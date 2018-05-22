package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AccessDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class CreateProductAcl extends CommonService {
	//private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(CreateProductAcl.class);
	public void run() throws ServletException,IOException
	{
		session=request.getSession(true);
		String userid=(String)session.getAttribute("orgid");
		String privilege=request.getParameter("privilege");
		String organizationId=request.getParameter("orgid");
		session.setAttribute("orgid", organizationId);
		session.setAttribute("privilege", privilege);
		session.setAttribute("userid", userid);
		session.setAttribute("privilege",privilege);
		
		LOGGER.info("userid is"+userid+ "privilege :"+privilege);
		AdminDomain processdomain=new AdminDomain();
		processdomain.setOrgid(request.getParameter("orgid"));
		processdomain.setPrivilege(request.getParameter("privilege"));
		AccessDao accessdao=new AccessDao();
		
		/*String retstatus=adao.searchAccessDetails(userid);
		LOGGER.info("result status is"+retstatus);
		
		if(retstatus!=null){
			String org_id=retstatus;
		 LOGGER.info("org value is"+org_id);
		 
		 pdomain.setOrgid(org_id);*/
			
		 ArrayList<AdminDomain>  AccessValues=accessdao.accessDetails(processdomain);//selecting the process values from dao class
		 
		/* ArrayList<AdminDomain>    AccessIdInDB=adao.retriveDetails(pdomain);
		 LOGGER.info("process id are"+AccessIdInDB);
		 for(AdminDomain id:AccessIdInDB)
		 {
			 LOGGER.info("process id values are"+id.getProcessid());
		 }*/
			LOGGER.info("AccessValues are"+AccessValues);
			session.setAttribute("AccessValues", AccessValues);
			//request.setAttribute("AccessIdInDB", AccessIdInDB);
			
			LOGGER.info("userid in service"+userid);
			/*RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
			rd.forward(request, response);*/
			 response.sendRedirect("../JSP/ProductAccess.jsp");
		/*else {
			String msg="organization id value is null";
			request.setAttribute("msgvalue",msg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAcl.jsp");
			rd.forward(request,response);
			
		}*/
		

		}
		
		
	}
       
    

