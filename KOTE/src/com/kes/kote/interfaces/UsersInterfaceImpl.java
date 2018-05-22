package com.kes.kote.interfaces;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.kes.kote.dao.UsersDAO;
import com.kes.kote.services.Mailer;
import com.kes.kote.util.GenerateRandomPwd;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;

public class UsersInterfaceImpl implements UsersInterface{
	
	UsersDAO dao=new UsersDAO();
	
	@Override
	public boolean validateUserLogin(String UserName, String Pwd,HttpSession sess)
	{
		boolean ret=false;
		try
		{
			HashMap<String,String> status=dao.validateLogin(UserName, Pwd,sess);
			
			if(status!=null && status.size()>0)
			{
				String role_type=status.get("role_type").toString().trim();
				String username=status.get("username").toString().trim();
				String organization_id=status.get("organization_id").toString().trim();
				String email=status.get("email").toString().trim();
				String user_id=status.get("user_id").toString().trim();
				SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
				
				util.setuserID(user_id);
				util.setusrRole(role_type);
				util.setuserName(username);
				util.setusrorg(organization_id);
				util.setusremail(email);
				sess.setAttribute("SessionUtil", util);
				
				setsuccessmsg(PropertiesUtil.getMessageProperty("login.success"),sess);
				ret=true;
			}
			else
			{
				setfailedmsg(PropertiesUtil.getMessageProperty("login.failed"),sess);
				ret=false;
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		
		return ret;
	}
	
	@Override
	public boolean changeUsrPwd(String UserId,String NewPwd,HttpSession sess)
	{
		boolean ret=true;
		try
		{
			dao.changeusrPwdbyUserID(UserId, NewPwd,sess);
			setsuccessmsg(PropertiesUtil.getMessageProperty("changepwd.success"),sess);
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	
	@Override
	public boolean ForgotUsrPwd(String usrNameorEmail,HttpSession sess)
	{
		boolean ret=false;
		try
		{
			String Email=dao.checkUserisValid(usrNameorEmail,sess);
			if(Email.trim().length()>0)
			{
				String subject=PropertiesUtil.getMessageProperty("forgotpwd.mailsubject");
				String Msg=PropertiesUtil.getMessageProperty("forgotpwd.mailbody");
				String Link=PropertiesUtil.getMessageProperty("forgotpwd.resetlink")+""+Email; 
				String Random=GenerateRandomPwd.getRandomPwd();
				String RandomPwd="<strong>"+PropertiesUtil.getMessageProperty("forgotpwd.resetpwd")+"</strong> "+Random;
				String Footer=PropertiesUtil.getMessageProperty("forgotpwd.mailfooter");
				
				SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
				util.print("RandomPwd == "+Random);
				Msg=Msg+"<br> <br>"
						+Link +"<br> <br>"
						+RandomPwd +"<br> <br>"
						+Footer;
				
				//Msg="hello";
				//MailService.send(Email, subject, Msg);//sending mail to user with random pwd
				
				Mailer.send(Email, subject, Msg,sess);
				
				dao.changeusrPwdbyEmail(Email, Random,sess);
				
				setsuccessmsg(PropertiesUtil.getMessageProperty("forgotpwd.success"),sess);
				ret=true;
			}else
			{
				setfailedmsg(PropertiesUtil.getMessageProperty("forgotpwd.failed"),sess);	
				ret=false;
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	
	@Override
	public boolean checkRandomPwdwithUser(String Email,String RandomPwd,HttpSession sess)
	{
		boolean ret=false;
		try
		{
			ret=dao.checkRandomPwdwithUser(Email, RandomPwd,sess);
			if(!ret)
				setfailedmsg(PropertiesUtil.getMessageProperty("resetpwd.failed"),sess);
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}

	@Override
	public void changePwdbyEmail(String Email,String Pwd,HttpSession sess)
	{
		try
		{
			
			dao.changeusrPwdbyEmail(Email, Pwd,sess);
			setsuccessmsg(PropertiesUtil.getMessageProperty("resetpwd.success"),sess);
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	@Override
	public void setsuccessmsg(String Msg,HttpSession sess)
	{
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		util.setsuccessmsg(Msg);
	}
	
	@Override
	public void setfailedmsg(String Msg,HttpSession sess)
	{
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		util.setfailedmsg(Msg);
	}
}
