package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;


public class InsertFacultyDao  {
	Connection con = null;
	PreparedStatement ps = null;
	//DBTransaction dbT = new DBTransaction();
	public String addFacultyDetails(AdminDomain fdomain)
	{
		
        String status = null;
		String privilege=fdomain.getPrivilege();
		//System.out.println("the privilege is:"+privilege);
		try {

			con = DBTransaction.connect();
			String quary="insert into users_info(username, user_id, password, email, address, contact_no, privilege,organization_id,created_by) " +
					"values(?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(quary);
			ps.setString(1,fdomain.getAdminName());
			ps.setString(2,fdomain.getAdminId());
			ps.setString(3,fdomain.getPwd());
			ps.setString(4,fdomain.getEmail());
			ps.setString(5,fdomain.getAddress());
			ps.setString(6,fdomain.getPhone());
			ps.setString(7,"faculty");
			
			ps.setString(8,fdomain.getOrgid());
			ps.setString(9,fdomain.getCreated_by());
			
			//System.out.println("the query is:"+ps);
			int n = ps.executeUpdate();
			//System.out.println("query is inserted successfully");
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
		}catch(Exception e){
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   con.close();
				   ps.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
			
	}
	public boolean duplicate(AdminDomain adomain)
	{
		
    	//PreparedStatement ps=null;
    	ResultSet rs=null;
    	String adminid=adomain.getAdminId();
    	boolean result=false;
    	try
    	{
    	
    		con = DBTransaction.connect();
    	String quary="select user_id from users_info where user_id=?";
    	
    	 ps = con.prepareStatement(quary);
    	ps.setString(1,adminid);
    	  rs=ps.executeQuery();
    	 if(rs.next())
    		 result = true; 
    	 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally
		   {
			   try{
				   con.close();
				   ps.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
    	return result;
	}
	
	
	}


