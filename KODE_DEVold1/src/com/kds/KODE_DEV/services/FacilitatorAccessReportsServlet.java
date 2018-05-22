package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.AccessReportsDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class FacilitatorAccessReportsServlet extends CommonService {
	public void run() throws ServletException,IOException {
    	session=request.getSession(true);
    	AdminDomain adminDomain=new AdminDomain();
    	AccessReportsDao accessReportsDao=new AccessReportsDao();
		String userid=request.getParameter("userid");
		session.setAttribute("AccessUserId", userid);
		String orgid=(String)session.getAttribute("orgid");
		adminDomain.setAdminId(userid);
		adminDomain.setOrgid(orgid);
		//System.out.println("access values in service:"+userid+ "organization id:"+orgid);
		 if(session.getAttribute("orgid")!=null && session.getAttribute("userid")!=null){
		
		boolean duplicate = accessReportsDao.duplicate(adminDomain);//checking it is duplicate or not in dao
     	 //System.out.println("duplicate value is"+duplicate);
     	
       if(duplicate)
       {
    	   //System.out.println("duplicate value in if"+duplicate);
  		 ArrayList<AdminDomain> udao=accessReportsDao.searchAccessReports(adminDomain);
  		 //ArrayList<AdminDomain> processName=urdao.getProcessName(udao);
  		
		 request.setAttribute("AdminDomain", udao);
		 RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacilitatorAccessReportsDetails.jsp");
		 rd.forward(request,response);
       }else  {
    	   //System.out.println("duplicate value in else:"+duplicate);
      	  String msg = "NO Record Exit";
 		  request.setAttribute("MsgValue", msg);
 		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/FacilitatorAccessDetails.jsp");
 		   rd.forward(request, response);
		// response.sendRedirect("../JSP/AccessReportsDetails.jsp");
    	
    }}
    }
}
