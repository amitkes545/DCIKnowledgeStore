package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AccessReportsDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class AccessReportsServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(AccessReportsServlet.class);
    public void run() throws ServletException,IOException {
    	session=request.getSession(true);
    	AdminDomain adminDomain=new AdminDomain();
    	AccessReportsDao accessReportsDao=new AccessReportsDao();
    	String scriptOrganizationId=request.getParameter("orgId");
		String userid=request.getParameter("userid");
		session.setAttribute("AccessUserId", userid);
		//String organizationId=request.getParameter("orgid");
		adminDomain.setAdminId(userid);
		adminDomain.setOrgid(request.getParameter("orgid"));
		if(scriptOrganizationId!=null){
			ArrayList<String> resultuserid=accessReportsDao.getUserId(scriptOrganizationId);//selecting userId from dao
			String[] resulArr = new String[resultuserid.size()];
			 resulArr = resultuserid.toArray(resulArr);
			 LOGGER.info("result array:"+resulArr);
			 session.setAttribute("resultuserid",resulArr);
			 session.setAttribute("orgid",scriptOrganizationId);
			 
			 response.sendRedirect("../JSP/AccessDetails.jsp");
		}else if(request.getParameter("orgid")!=null && request.getParameter("userid")!=null){
		
		boolean duplicate = accessReportsDao.duplicate(adminDomain);//checking it is duplicate or not in dao
     	 LOGGER.info("duplicate value is"+duplicate);
     	
       if(duplicate)
       {
    	   LOGGER.info("duplicate value in if"+duplicate);
  		 ArrayList<AdminDomain> udao=accessReportsDao.searchAccessReports(adminDomain);
  		 //ArrayList<AdminDomain> processName=urdao.getProcessName(udao);
  		
		 request.setAttribute("AdminDomain", udao);
		 RequestDispatcher rd=request.getRequestDispatcher("../JSP/AccessReportsDetails.jsp");
		 rd.forward(request,response);
       }else  {
    	   LOGGER.info("duplicate value in else:"+duplicate);
      	  String msg = "NO Record Exit";
 		  request.setAttribute("MsgValue", msg);
 		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/AccessDetails.jsp");
 		   rd.forward(request, response);
		// response.sendRedirect("../JSP/AccessReportsDetails.jsp");
    	
    }}
    }
}
