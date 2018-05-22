package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.LoginDao;
import com.kds.KODE_DEV.domain.LoginDomain;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
@SuppressWarnings("serial")
public class LoginServlet extends CommonService {
	
	
	//private static final long serialVersionUID = 1L;
  	static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	public void run() throws ServletException,IOException {
	
		
		session=request.getSession(true);
	   LoginDomain lDomain=new LoginDomain();
	   String organizationID="",organizationName="";
	   String userid=request.getParameter("loginid");
	  //System.out.println("login id"+userid);
	   LOGGER.info("user id:"+userid);
	  lDomain.setLoginId(request.getParameter("loginid"));
	  lDomain.setPassword(request.getParameter("pwd"));
	  
	 //System.out.println("login domain:"+lDomain);
	  LOGGER.info("login domain===>>>"+lDomain);
	  
	  LoginDao loginDao=new LoginDao();
	  String username=loginDao.retriveUserName(userid);
	 // //System.out.println("status username:"+username);
	  LOGGER.info("status username:"+username);
	List<String> domain=loginDao.retriveOrgid(userid);
	 // //System.out.println("organization id:"+orgid);
	//System.out.println("domain value:"+domain);
	ListIterator<String> it=domain.listIterator();
	
	while(it.hasNext()){
		 organizationID=it.next();
		 organizationName=it.next();
		 //System.out.println("organizationName:"+organizationName);
	}
	//System.out.println("organizationName:"+organizationName);
	 LOGGER.info("organization id:"+domain);
	  String retStatus=loginDao.checkUserCredentials(lDomain);
	  ////System.out.println("retstatus is:"+retStatus);
	  LOGGER.info("retstatus is:"+retStatus);
	  String msg=null;
	  session.setAttribute("username",username);
	  session.setAttribute("userid",userid);
	  session.setAttribute("orgid",organizationID);
	  session.setAttribute("organizationName", organizationName);
	  if("owner".equals(retStatus))
	  {
		    msg = "owner";	
		   // session.setAttribute("username",username);
			session.setAttribute("MsgValue", msg);
			//session.setAttribute("userid",userid);
			//session.setAttribute("orgid",organizationID);
		  response.sendRedirect("../JSP/Home.jsp");
	  }
	  if("superadmin".equals(retStatus))
		  
	  {
		  msg = "superadmin";	
		 // session.setAttribute("username",username);
			session.setAttribute("MsgValue", msg);
			//session.setAttribute("orgid",organizationID);
			//session.setAttribute("userid",userid);
		  response.sendRedirect("../JSP/Home.jsp");
	  }
	  if("admin".equals(retStatus))
	  {
		  msg = "admin";
		   //session.setAttribute("username",username);
		  // session.setAttribute("userid",userid);
		  // session.setAttribute("orgid",organizationID);
			session.setAttribute("MsgValue", msg);
		  response.sendRedirect("../JSP/Home.jsp");
    }
	  if("faculty".equals(retStatus))
	  {
		  msg = "faculty";	
		  //session.setAttribute("username",username);
		  //session.setAttribute("userid",userid);
		//  session.setAttribute("orgid",organizationID);
			session.setAttribute("MsgValue", msg);
		  response.sendRedirect("../JSP/Home.jsp");
	  }
	  if("student".equalsIgnoreCase(retStatus))
	  {
		  msg = "student";	
		 
		  
			session.setAttribute("MsgValue", msg);
		  response.sendRedirect("../JSP/Home.jsp");
	  }
	  if("pwdnotvalid".equalsIgnoreCase(retStatus)){
		  
		    msg = "Enter Current password!";			
		   request.setAttribute("MsgValue", msg);
		   RequestDispatcher rd=request.getRequestDispatcher("../JSP/Login.jsp");
		   rd.forward(request, response);
		 // response.sendRedirect("../JSP/Login.jsp");
		 // request.setAttribute("errorMessage", "password is wrong!");
	  }
	  if("useridnotvalid".equalsIgnoreCase(retStatus)){
		    
		  msg = "User ID is not valid..please verify";
		  request.setAttribute("MsgValue", msg);
		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/Login.jsp");
		   rd.forward(request, response);
		 // request.setAttribute("errorMessage", "userid is wrong!");
	  }
	  if("useridnotvalid".equalsIgnoreCase(retStatus) && "pwdnotvalid".equalsIgnoreCase(retStatus)) {
		  
		  msg = "UserId & password is notvalid..please verify";
		  request.setAttribute("MsgValue", msg);
		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/Login.jsp");
		   rd.forward(request, response);
	  }
	}
}
	

