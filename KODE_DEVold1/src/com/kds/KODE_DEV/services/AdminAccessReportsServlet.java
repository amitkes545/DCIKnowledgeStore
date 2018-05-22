package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AccessReportsDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class AdminAccessReportsServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(AdminAccessReportsServlet.class);
	public void run() throws ServletException,IOException {
    	session=request.getSession(true);
    	AdminDomain adminDomain=new AdminDomain();
    	AccessReportsDao accessReportsDao=new AccessReportsDao();
		String userid=request.getParameter("userid");
		request.setAttribute("AccessUserId", userid);
		String orgid=(String)session.getAttribute("orgid");
		adminDomain.setAdminId(userid);
		adminDomain.setOrgid(orgid);
		LOGGER.info("access values in service:"+userid+ "organization id:"+orgid);
		 if(session.getAttribute("orgid")!=null && session.getAttribute("userid")!=null){
		
		boolean duplicate = accessReportsDao.duplicate(adminDomain);//checking it is duplicate or not from dao
     	 LOGGER.info("duplicate value is"+duplicate);
     	
       if(duplicate)
       {
    	   LOGGER.info("duplicate value in if"+duplicate);
  		 ArrayList<AdminDomain> udao=accessReportsDao.searchAccessReports(adminDomain);
  		 //ArrayList<AdminDomain> processName=urdao.getProcessName(udao);
  		
		 request.setAttribute("AdminDomain", udao);
		 RequestDispatcher rd=request.getRequestDispatcher("../JSP/AdminAccessReportsDetails.jsp");
		 rd.forward(request,response);
       }else  {
    	   LOGGER.info("duplicate value in else:"+duplicate);
      	  String msg = "NO Record Exit";
 		  request.setAttribute("MsgValue", msg);
 		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/AdminAccessDetails.jsp");
 		   rd.forward(request, response);
		// response.sendRedirect("../JSP/AccessReportsDetails.jsp");
    	
    }}
    }
}
