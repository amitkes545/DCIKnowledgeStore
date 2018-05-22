package com.kes.kote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.kes.kote.dbconnection.DBTransaction;
import com.kes.kote.util.Encryption;
import com.kes.kote.util.SessionUtil;

public class UsersDAO {
	
	public HashMap<String,String> validateLogin(String userName,String Pwd,HttpSession sess)
	{
		HashMap<String,String> ret=new HashMap<String,String>();
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			Pwd=Encryption.encrypt(Pwd);
			conn=DBTransaction.openConnection();
			String sql="select a.role_type, c.username, c.organization_id, c.email from mst_roles a,mst_user_role_map b,users_info c where a.role_id=b.role_id and b.user_id=c.user_id and c.user_id= ? and c.password= ?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, userName);
			st.setString(2, Pwd);
			util.print("Login query : "+st.toString());
			result=st.executeQuery();
			
			while(result.next()) 
			{
				String role_type=result.getString("role_type").toString().trim();
				String username=result.getString("username").toString().trim();
				String organization_id=result.getString("organization_id").toString().trim();
				String email=result.getString("email").toString().trim();
				String user_id=userName;
				
				if(role_type.trim().equalsIgnoreCase("Owner"))
				{
					ret.put("role_type", role_type);
					ret.put("username", username);
					ret.put("organization_id", organization_id);
					ret.put("email", email);
					ret.put("user_id", user_id);	
				}
				
			}
			
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					conn.close();
					st.close();
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}

	public void changeusrPwdbyUserID(String userID,String newPwd,HttpSession sess)
	{
		Connection conn=null;
		PreparedStatement st=null;
				
		try
		{
			newPwd=Encryption.encrypt(newPwd);
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="UPDATE users_info SET password= ? WHERE user_id= ?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, newPwd);
			st.setString(2, userID);
			util.print("changeusrPwdbyUserID query : "+st.toString());
			st.executeUpdate();
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					conn.close();
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void changeusrPwdbyEmail(String userEmail,String newPwd,HttpSession sess)
	{
		Connection conn=null;
		PreparedStatement st=null;
				
		try
		{
			newPwd=Encryption.encrypt(newPwd);
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="UPDATE users_info SET password= ? WHERE email= ?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, newPwd);
			st.setString(2, userEmail);
			util.print("changeusrPwdbyEmail query : "+st.toString());
			st.executeUpdate();
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					conn.close();
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public String checkUserisValid(String userNameorEmail,HttpSession sess)
	{
		String ret="";
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="select email from users_info where user_id in ( ? )";
			
			st=conn.prepareStatement(sql);
			st.setString(1, userNameorEmail);
			
			util.print("checkUserisValid by user_id "+st.toString());
			result=st.executeQuery();
			
			while(result.next()) 
			{
				ret=result.getString("email").toString().trim();
				// ret=ret+"_ByuserID";
			}
			
			result.close();
			st.close();
			
			if(ret.trim().length()==0)
			{
				sql="select email from users_info where email in ( ? ) ";
				
				st=conn.prepareStatement(sql);
				st.setString(1, userNameorEmail);
				util.print("checkUserisValid by email "+st.toString());
				result=st.executeQuery();
				
				while(result.next()) 
				{
					ret=result.getString("email").toString().trim();
					// ret=ret+"_ByEmailID";
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					conn.close();
					st.close();
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	public boolean checkRandomPwdwithUser(String Email,String RandomPwd,HttpSession sess)
	{
		boolean ret=false;
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			RandomPwd=Encryption.encrypt(RandomPwd);
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="select * from users_info where email= ? and password= ?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, Email);
			st.setString(2, RandomPwd);
			util.print("checkRandomPwdwithUser "+st.toString());
			result=st.executeQuery();
			
			while(result.next()) 
			{
				ret=true;
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					conn.close();
					st.close();
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}
}
