package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.DisplayAdminDomain;

/**
 * Servlet implementation class DisplaySuperAdminDao
 */

public class DisplaySuperAdminDao  {
ArrayList<AdminDomain> al= new ArrayList<AdminDomain>();
Connection con=null;
PreparedStatement pstmt = null;
	AdminDomain adminDomain= new AdminDomain();
	
	public  AdminDomain retriveSuperAdminData(DisplayAdminDomain dDomain) {
		//DBTransaction DBT = new DBTransaction();
           //System.out.println("this is data base page");
		try {
			String sid=dDomain.getSid();
			con = DBTransaction.connect();
			//PreparedStatement pstmt = null;
			ResultSet rs = null;
			String quary = "select * from users_info where user_id='"+sid+"'";
			//System.out.println("the quary is:"+quary);
			pstmt = con.prepareStatement(quary);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				
				String adminid=rs.getString("user_id");
				//System.out.println("userid is:"+adminid);
				adminDomain.setAdminId(rs.getString("user_id"));
				adminDomain.setAdminName(rs.getString("username"));
				adminDomain.setPwd(rs.getString("password"));
				adminDomain.setEmail(rs.getString("email"));
				adminDomain.setAddress(rs.getString("address"));
				
				adminDomain.setPhone(rs.getString("contact_no"));
				adminDomain.setPrivilege(rs.getString("privilege"));
				adminDomain.setOrgid(rs.getString("organization_id"));
				adminDomain.setCreated_by(rs.getString("created_by"));
				
				
			}
			//System.out.println("the return value is:"+adminDomain);
				
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
		return adminDomain;
	}
       
	ArrayList<String> ad=new ArrayList<String>();
	public ArrayList<String> getUserId(String orgid)
	{
	try {
			//Connection con= null;	
			//DBTransaction db= new DBTransaction();
			ResultSet rs= null;
			//PreparedStatement ps=null;
			con=DBTransaction.connect();
			String sql= "select user_id from users_info where organization_id='"+orgid+"'"+"and privilege in('SuperAdmin','Superadmin','superadmin')";
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
}
