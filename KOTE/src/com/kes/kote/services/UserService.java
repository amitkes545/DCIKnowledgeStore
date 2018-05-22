package com.kes.kote.services;

import java.io.IOException;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kes.kote.interfaces.UsersInterface;
import com.kes.kote.interfaces.UsersInterfaceImpl;
import com.kes.kote.util.SessionUtil;


public class UserService extends CommonService 
{
	static final Logger logger = Logger.getLogger(UserService.class);
	private static final long serialVersionUID = 1L;
	
	UsersInterface userIF=new UsersInterfaceImpl();
	
	public void run() throws ServletException,IOException {
		
		session=request.getSession(true);
		
		String From=request.getParameter("From");
		if(From.trim().equalsIgnoreCase("Login"))
			checkLoginForUser();
		else if(From.trim().equalsIgnoreCase("ChangePwd"))
			changePwd();
		else if(From.trim().equalsIgnoreCase("ForgotPwd"))
			ForgotPwd();
		else if(From.trim().equalsIgnoreCase("resetPwd"))
			ResetPwd();	
	}
	
	void checkLoginForUser()
	{
		try
		{
			SessionUtil util=new SessionUtil();
			session.setAttribute("SessionUtil", util);
			
			String UserName=request.getParameter("usernm");
			String Pwd=request.getParameter("password");
			boolean status=userIF.validateUserLogin(UserName, Pwd,session);
			if(status)
			{
				session.setAttribute("username", UserName);
				response.sendRedirect("../JSP/teaching-source.jsp");
			}
			else
			{
				session.setAttribute("username", null);
				response.sendRedirect("../JSP/login.jsp");
			}
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	public void changePwd()
	{
		try
		{
			String password=request.getParameter("password");
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			String userID=util.getuserID();
			
			userIF.changeUsrPwd(userID, password,session);
			response.sendRedirect("../JSP/login.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
		
	}
	public void ForgotPwd()
	{
		try
		{
			String Email=request.getParameter("usernm");
			
			boolean status=userIF.ForgotUsrPwd(Email,session);
			if(status)
				response.sendRedirect("../JSP/login.jsp");
			else
				response.sendRedirect("../JSP/forgot-password.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void ResetPwd()
	{
		try
		{
			String usrEmail=request.getParameter("usrEmail");
			String randompassword=request.getParameter("randompassword");
			String password=request.getParameter("password");
			//String confirmpassword=request.getParameter("confirmpassword");
			
			boolean status=userIF.checkRandomPwdwithUser(usrEmail, randompassword,session);
			if(status)
			{
				userIF.changePwdbyEmail(usrEmail, password,session);
				response.sendRedirect("../JSP/login.jsp");
			}else
			{
				response.sendRedirect("../JSP/reset-password.jsp?Email="+usrEmail);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
	}
}
