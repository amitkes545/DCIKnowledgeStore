package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.AccessReportsDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class SuperadminAccessReportsServlet extends CommonService {
	
	  public void run() throws ServletException,IOException {
	    	session=request.getSession(true);
	    	AdminDomain adminDomain=new AdminDomain();
	    	AccessReportsDao accessReportsDao=new AccessReportsDao();
	    	String scriptOrganizationId=request.getParameter("orgId");
			String userid=request.getParameter("userid");
			session.setAttribute("AccessUserId", userid);
			String organizationId=request.getParameter("orgid");
			adminDomain.setAdminId(userid);
			adminDomain.setOrgid(request.getParameter("orgid"));
			
			if(scriptOrganizationId!=null){
				ArrayList<String> resultuserid=accessReportsDao.getSuperadminUserId(scriptOrganizationId);//selecting userId from dao
				String[] resulArr = new String[resultuserid.size()];
				 resulArr = resultuserid.toArray(resulArr);
				 //System.out.println("result array:"+resulArr);
				 session.setAttribute("resultuserid",resulArr);
				 session.setAttribute("orgid",scriptOrganizationId);
				 
				 response.sendRedirect("../JSP/SuperadminAccessDetails.jsp");
			}else if(request.getParameter("orgid")!=null && request.getParameter("userid")!=null){
			
			boolean duplicate = accessReportsDao.duplicate(adminDomain);//checking it is duplicate or not in dao
	     	 //System.out.println("duplicate value is"+duplicate);
	     	
	       if(duplicate)
	       {
	    	   //System.out.println("duplicate value in if"+duplicate);
	  		 ArrayList<AdminDomain> udao=accessReportsDao.searchAccessReports(adminDomain);
	  		 //ArrayList<AdminDomain> processName=urdao.getProcessName(udao);
	  		
			 request.setAttribute("AdminDomain", udao);
			 RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperadminAccessReportsDetails.jsp");
			 rd.forward(request,response);
	       }else  {
	    	   //System.out.println("duplicate value in else:"+duplicate);
	      	  String msg = "NO Record Exit";
	 		  request.setAttribute("MsgValue", msg);
	 		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperadminAccessDetails.jsp");
	 		   rd.forward(request, response);
			// response.sendRedirect("../JSP/AccessReportsDetails.jsp");
	    	
	    }}
	    }
}
