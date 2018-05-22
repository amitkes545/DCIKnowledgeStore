package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.AccessDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class SuperadminProductAcl extends CommonService{

	public void run() throws ServletException,IOException
	{
		session=request.getSession(true);
		String userid=(String)session.getAttribute("userid");
		
		String privilege=request.getParameter("privilege");
		String organizationId=request.getParameter("orgid");
		session.setAttribute("orgid", organizationId);
		session.setAttribute("privilege", privilege);
		session.setAttribute("userid", userid);
		session.setAttribute("privilege",privilege);
		
		//System.out.println("userid is"+userid+ "privilege :"+privilege+ "organizationId:"+organizationId);
		AdminDomain processdomain=new AdminDomain();
		processdomain.setOrgid(request.getParameter("orgid"));
		processdomain.setPrivilege(request.getParameter("privilege"));
		AccessDao accessdao=new AccessDao();
		
		
			
		 ArrayList<AdminDomain>  AccessValues=accessdao.accessDetails(processdomain);//selecting the process values from dao class
		 
		
			//System.out.println("AccessValues are"+AccessValues);
			session.setAttribute("AccessValues", AccessValues);
			//request.setAttribute("AccessIdInDB", AccessIdInDB);
			
			//System.out.println("userid in service"+userid);
			/*RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
			rd.forward(request, response);*/
			 response.sendRedirect("../JSP/SuperadminProductAccess.jsp");
		/*else {
			String msg="organization id value is null";
			request.setAttribute("msgvalue",msg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAcl.jsp");
			rd.forward(request,response);
			
		}*/
		

		}
		
}
