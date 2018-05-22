package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.services.ParticipantLoginServlet;
import com.kds.KODE_DEV.dao.LoginDao;
import com.kds.KODE_DEV.domain.LoginDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@SuppressWarnings("serial")
public class ParticipantLoginServlet extends CommonService {
	
	static final Logger logger = Logger.getLogger(ParticipantLoginServlet.class);
	private static final long serialVersionUID = 1L;
	
	public void run() throws ServletException,IOException {
		HttpSession session = request.getSession(true);

		 LoginDomain lDomain=new LoginDomain();
		  lDomain.setLoginId(request.getParameter("loginid"));
		  lDomain.setPassword(request.getParameter("pwd"));
		  
		  //System.out.println("login domain:"+lDomain.getLoginId());
		  /** 
		   * Creating object to LoginDao to check Login credentials */
		  
		  LoginDao loginDao=new LoginDao();
		  //checking username and password
		  String status=loginDao.checkWebViewUserCredentials(lDomain);
		  logger.debug("status in login servlet="+status);
		  //System.out.println("status:"+status);
		  if("pwdnotvalid".equalsIgnoreCase(status)){
			  //System.out.println("pwdnotvalid:"+status);
			  String message="Please check your Password";//pwdnotvalid";
			  request.setAttribute("message",message);
			  RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/LoginWebView.jsp");
			  requestDispatcher.forward(request, response);
		  }else if("useridnotvalid".equalsIgnoreCase(status)){
			  //System.out.println("useridnotvalid:"+status);
			  String message="Please check your User Name";// useridnotvalid";
			  request.setAttribute("message",message);
			  RequestDispatcher requestDispatcher1=request.getRequestDispatcher("../JSP/LoginWebView.jsp");
			  requestDispatcher1.forward(request, response);
		  }else if("success".equalsIgnoreCase(status)){
		  /** 
		   * Setting values to UsersInfoDomain object reference to Handle Session */
			  //System.out.println("in success");
			  SessionMaintenance s2=new SessionMaintenance();
			  //String priv=session.getAttribute("privilege").toString();
			 // String usrid=session.getAttribute("userid").toString();
		  UsersInfoDomain uInfo=loginDao.loginUsersInfo(lDomain);
		  List<String> domain=loginDao.retriveOrgid(lDomain.getLoginId());
			 // //System.out.println("organization id:"+orgid);
			//System.out.println("domain value:"+domain);
			ListIterator<String> it=domain.listIterator();
			
			while(it.hasNext()){
				// organizationID=it.next();
				 //organizationName=it.next();
				 session.setAttribute("organizationName", it.next());
				 //System.out.println("organizationName:"+organizationName);
			}
			//System.out.println("organizationName "+ session.getAttribute("organizationName"));
			if("success".equals(s2.createSession(request.getParameter("loginid"), uInfo.getPrevilege()))){
		//session.setAttribute("userid", id);
		//session.setAttribute("previlege",rs.getString("privilege"));
			}

		  session.setAttribute("userName", uInfo.getUserName());
		  session.setAttribute("previlege", uInfo.getPrevilege());
		  session.setAttribute("orgId", uInfo.getOrgId());
		  session.setAttribute("userId", uInfo.getUserId());
		  session.setAttribute("createdBy", uInfo.getCreatedBy());
		  
		  String uid=(String) session.getAttribute("userId");
		  String logoName=loginDao.getLogoName(uid);  
		  System.out.println("logoName="+logoName);
		  session.setAttribute("logoname",logoName);  
		  
		  if(uInfo.getPrevilege().equalsIgnoreCase("faculty"))
		  {
			  response.sendRedirect("../JSP/FacultyView.jsp");
		  }
		  else  if(uInfo.getPrevilege().equalsIgnoreCase("student")){
		  response.sendRedirect("../JSP/ParticipantViewHeader.jsp");
			  
			  //response.sendRedirect("../JSP/stream.jsp");
		  }
		  else  if(uInfo.getPrevilege().equalsIgnoreCase("owner")){
			  /*response.sendRedirect("../JSP/ParticipantView.jsp");*/
				  
				  response.sendRedirect("../JSP/OwnerView.jsp");
			  }
		  else  if(uInfo.getPrevilege().equalsIgnoreCase("Admin")){
			     
		      
		      response.sendRedirect("../JSP/AdminView.jsp");
		     }
		  else if (uInfo.getPrevilege().equalsIgnoreCase("Super Admin")) {
			    System.out.println("Inside the super admin");
			    response.sendRedirect("../JSP/SuperAdminView.jsp");
			   }
		  }
	}
	

}
