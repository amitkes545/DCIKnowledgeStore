package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;


public class UsersReportsDao  {
	Connection con= null;	
	//DBTransaction db= new DBTransaction();
	ResultSet rs= null;
	PreparedStatement pstmt=null;
ArrayList<AdminDomain> al= new ArrayList<AdminDomain>();
	
	AdminDomain adomain= new AdminDomain();
	//DBTransaction DBT = new DBTransaction();
	public  AdminDomain searchUsersReports(AdminDomain dDomain) {
		
           //System.out.println("this is data base page");
		try {
			
			 con = DBTransaction.connect();
			
			String quary = "select * from users_info where user_id='"+dDomain.getAdminId()+"'";
			//System.out.println("the quary is:"+quary);
			pstmt = con.prepareStatement(quary);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				
				adomain.setAdminId(rs.getString("user_id"));
				//System.out.println("userid is:"+rs.getString("user_id"));
				
				adomain.setAdminName(rs.getString("username"));
				adomain.setPwd(rs.getString("password"));
				adomain.setEmail(rs.getString("email"));
				adomain.setAddress(rs.getString("address"));
				
				adomain.setPhone(rs.getString("contact_no"));
				adomain.setPrivilege(rs.getString("privilege"));
				adomain.setOrgid(rs.getString("organization_id"));
				adomain.setCreated_by(rs.getString("created_by"));
				
			}
			//System.out.println("the return value is:"+adomain);
				
				}catch(Exception e){
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   con.close();
				   pstmt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return adomain;
	}
	public String duplicate(AdminDomain udomain)
	{
		
    	
    	String userid=udomain.getAdminId();
    	String result=null;
    	try
    	{
    	
    		con = DBTransaction.connect();
    	String quary="select user_id from users_info where user_id=?";
    	
    	pstmt = con.prepareStatement(quary);
    	pstmt.setString(1,userid);
    	  rs=pstmt.executeQuery();
    	 if(rs.next()){
    		 result = "success"; 
    	}else {
    		result="userid is wrong";
    		
    	}
    	 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally
		   {
			   try{
				   con.close();
				   pstmt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
    	return result;
	}
	ArrayList<String> ad=new ArrayList<String>();
	public ArrayList<String> getUserId(String orgid)
	{
	try {
			
			con=DBTransaction.connect();
			String sql= "select user_id from users_info where organization_id='"+orgid+"'";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			//System.out.println("quary is"+sql);
				while(rs.next())
				{	
					ad.add(rs.getString("user_id"));
				 
				 }
			 
		}catch(Exception e){
			e.printStackTrace();
		}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
		 return ad;
	}
	public ArrayList<String> getSuperAdminUserId(String orgid,String userid)
	{
	try {
			
			con=DBTransaction.connect();
			String sql= "select user_id from users_info where organization_id='"+orgid+"'"+" and privilege in('SuperAdmin','Superadmin','superadmin')"+" and created_by='"+userid+"'";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			//System.out.println("quary is"+sql);
				while(rs.next())
				{	
					ad.add(rs.getString("user_id"));
				 
				 }
			 
		}catch(Exception e){
			e.printStackTrace();
		}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
		 return ad;
	}
	public ArrayList<String> getAdminUserId(String orgid,String userid)
	{
	try {
			
			con=DBTransaction.connect();
			String sql= "select user_id from users_info where organization_id='"+orgid+"'"+" and privilege in('Faculty','faculty')"+" and created_by='"+userid+"'";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			//System.out.println("quary is"+sql);
				while(rs.next())
				{	
					ad.add(rs.getString("user_id"));
				 
				 }
			 
		}catch(Exception e){
			e.printStackTrace();
		}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
		 return ad;
	}
	public ArrayList<String> getSuperAdminReportsId(String orgid,String userid)
	{
	try {
			
			con=DBTransaction.connect();
			String sql= "select user_id from users_info where organization_id='"+orgid+"'"+" and privilege in('SuperAdmin','Superadmin','superadmin')";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			//System.out.println("quary is"+sql);
				while(rs.next())
				{	
					ad.add(rs.getString("user_id"));
				 
				 }
			 
		}catch(Exception e){
			e.printStackTrace();
		}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
		 return ad;
	}

	public  AdminDomain searchSuperadminUsersReports(AdminDomain dDomain) {
		
        //System.out.println("this is data base page");
		try {
			
			 con = DBTransaction.connect();
			
			String quary = "select * from users_info where user_id='"+dDomain.getAdminId()+"'"+" and organization_id='"+dDomain.getOrgid()+"'";
			//System.out.println("the quary is:"+quary);
			pstmt = con.prepareStatement(quary);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				
				adomain.setAdminId(rs.getString("user_id"));
				//System.out.println("userid is:"+rs.getString("user_id"));
				
				adomain.setAdminName(rs.getString("username"));
				adomain.setPwd(rs.getString("password"));
				adomain.setEmail(rs.getString("email"));
				adomain.setAddress(rs.getString("address"));
				
				adomain.setPhone(rs.getString("contact_no"));
				adomain.setPrivilege(rs.getString("privilege"));
				adomain.setOrgid(rs.getString("organization_id"));
				adomain.setCreated_by(rs.getString("created_by"));
				
			}
			//System.out.println("the return value is:"+adomain);
				
				}catch(Exception e){
			e.printStackTrace();
		}finally
		   {
			   try{
				   con.close();
				   pstmt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return adomain;
	}
}
