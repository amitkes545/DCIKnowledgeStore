package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.CreateDomain;


public class InsertSuperAdminDao {
	Connection con = null;
	PreparedStatement ps = null;
	//DBTransaction dbT = new DBTransaction();
	public String addSuperAdminDetails(AdminDomain sdomain)
	{
		
        String status = null;
		String privilege=sdomain.getPrivilege();
		//System.out.println("the privilege is:"+privilege);
		try {

			con = DBTransaction.connect();
			String quary="insert into users_info(username, user_id, password, email, address, contact_no, privilege,organization_id,created_by) " +
					"values(?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(quary);
			ps.setString(1,sdomain.getAdminName());
			ps.setString(2,sdomain.getAdminId());
			ps.setString(3,sdomain.getPwd());
			ps.setString(4,sdomain.getEmail());
			ps.setString(5,sdomain.getAddress());
			ps.setString(6,sdomain.getPhone());
			ps.setString(7,sdomain.getPrivilege());
			ps.setString(8,sdomain.getOrgid());
			ps.setString(9,sdomain.getCreated_by());
			
			//System.out.println("orgnization type is:"+sdomain.getOrgid());
			//System.out.println("the quary is:"+ps);
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
	public boolean duplicate(AdminDomain cdomain)
	{
		
    	//PreparedStatement ps=null;
    	ResultSet rs=null;
    	String adminid=cdomain.getAdminId();
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
	ArrayList<CreateDomain> al= new ArrayList<CreateDomain>();
	public ArrayList<CreateDomain> getOrgId( ){
		
	try{
		//Connection con= null;	
		//DBTransaction db= new DBTransaction();
		ResultSet rs= null;
		//PreparedStatement ps=null;
		con=DBTransaction.connect();
		String sql= "select organization_id,organization_name from org_details";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		
		while(rs.next())
		{
		 CreateDomain cDomain= new CreateDomain();
		 cDomain.setOrg_id(rs.getString("organization_id"));
		 cDomain.setOrg_name(rs.getString("organization_name"));
		 al.add(cDomain);
		}
	}
	catch(Exception e)
	{
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
	return al;	
	}
	
}
	

