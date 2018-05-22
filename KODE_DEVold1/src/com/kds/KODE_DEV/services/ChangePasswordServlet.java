package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.LoginDao;
import com.kds.KODE_DEV.domain.LoginDomain;

/**
 * Servlet implementation class ChangePasswordServlet
 */
//@WebServlet("/ChangePasswordServlet")
@SuppressWarnings("serial")
public class ChangePasswordServlet extends CommonService {
	//private static final long serialVersionUID = 1L;
       
   @SuppressWarnings("unused")
public void run() throws ServletException,IOException{
	   
	   LoginDomain cdomain=new LoginDomain();
	   String userid=request.getParameter("userid");
	   //System.out.println("userid:"+userid);
	   cdomain.setCurrentpwd(request.getParameter("cpwd"));
	   //System.out.println("password value"+request.getParameter("cpwd"));
	   cdomain.setNewpwd(request.getParameter("npwd"));
	   //System.out.println("new pwd value"+request.getParameter("npwd"));
	   cdomain.setConformpwd(request.getParameter("cnpwd"));
	   LoginDao ldao=new LoginDao();
	   String result=ldao.changePassword(cdomain,userid);
	   if("success".equalsIgnoreCase(result)){
		   response.sendRedirect("../JSP/success.jsp");
		
	   }else {
		   String sessionStatus="Fail for inserted certify value";
			
	   }
	   
   }
}
