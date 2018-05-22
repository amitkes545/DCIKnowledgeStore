package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.UsersReportsDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class UsersAllReportsServlet extends CommonService{
	public void run() throws ServletException,IOException{
		session=request.getSession(true);
		AdminDomain udomain=new AdminDomain();
		UsersReportsDao urdao=new UsersReportsDao();
		//String sorgid=request.getParameter("orgId");
		String userid=request.getParameter("userid");
		udomain.setAdminId(userid);
		udomain.setOrgid(request.getParameter("orgid"));
		/*if(sorgid!=null){
			//System.out.println("ornanization in service if:"+sorgid);
			ArrayList<String> resultuserid=urdao.getUserId(sorgid);
			String[] resulArr = new String[resultuserid.size()];
			 resulArr = resultuserid.toArray(resulArr);
			 session.setAttribute("resultuserid", resulArr);
			 session.setAttribute("scriptorgid",sorgid);
			 response.sendRedirect("../JSP/UsersReports.jsp");
		}else*/
		//if(request.getParameter("orgid")!=null && request.getParameter("userid")!=null || request.getParameter("userid")!=""){
		
			
		//System.out.println("organization id:"+request.getParameter("orgid")+ "user id:"+request.getParameter("userid"));
		/*String result = urdao.duplicateSuperadmin(udomain);
      	 //System.out.println("duplicate value is"+result);
      	
        if("userid is wrong".equalsIgnoreCase(result))
        {

        	String msg = "UserId is wrong!please try again";
   		  request.setAttribute("MsgValue", msg);
   		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/UsersAllReports.jsp");
   		   rd.forward(request, response);
        }else if("success".equalsIgnoreCase(result)) {*/
		AdminDomain udao=urdao.searchSuperadminUsersReports(udomain);
		//System.out.println("create domain value is:"+udao.getAddress());
		 
		 session.setAttribute("AdminDomain", udao);
		 response.sendRedirect("../JSP/UsersAllReportsDetails.jsp");
        //}
	}
	//}   
   

}
