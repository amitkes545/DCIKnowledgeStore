package com.kes.kote.interfaces;

import javax.servlet.http.HttpSession;

public interface UsersInterface {
	
	public abstract boolean validateUserLogin(String UserName, String Pwd,HttpSession sess);
	
	public abstract boolean changeUsrPwd(String UserId,String NewPwd,HttpSession sess);
	
	public abstract boolean ForgotUsrPwd(String usrNameorEmail,HttpSession sess);
	
	public abstract boolean checkRandomPwdwithUser(String Email,String RandomPwd,HttpSession sess);
	
	public abstract void changePwdbyEmail(String Email,String Pwd,HttpSession sess);
	
	public abstract void setsuccessmsg(String Msg,HttpSession sess);
	
	public abstract void setfailedmsg(String Msg,HttpSession sess);

}
