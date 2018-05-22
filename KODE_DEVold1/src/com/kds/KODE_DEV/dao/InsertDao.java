package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;


/**
 * Servlet implementation class InsertDao
 */

public class InsertDao  {
	Connection connection=null;
	PreparedStatement prepareStatement=null;
	
	ResultSet resultSet=null;
	//DBTransaction dbT = new DBTransaction();
	public String addAdminDetails(AdminDomain adomain)
	{
		
        String status = null;
		String privilege=adomain.getPrivilege();
		//System.out.println("the privilege is:"+privilege);
		try {

			connection = DBTransaction.connect();
			String query="insert into users_info(username, user_id, password, email, address, contact_no, privilege, organization_id,created_by) " +
					"values(?,?,?,?,?,?,?,?,?)";
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1,adomain.getAdminName());
			prepareStatement.setString(2,adomain.getAdminId());
			prepareStatement.setString(3,adomain.getPwd());
			prepareStatement.setString(4,adomain.getEmail());
			prepareStatement.setString(5,adomain.getAddress());
			prepareStatement.setString(6,adomain.getPhone());
			prepareStatement.setString(7,"Admin");
			
			prepareStatement.setString(8,adomain.getOrgid());
			prepareStatement.setString(9,adomain.getCreated_by());
			//System.out.println("the query is:"+prepareStatement);
			int n = prepareStatement.executeUpdate();
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
				   connection.close();
				   prepareStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
			
	}
	public boolean duplicate(AdminDomain adomain)
	{
		
    	
    	String adminid=adomain.getAdminId();
    	boolean result=false;
    	try
    	{
    		connection = DBTransaction.connect();
    	String quary="select user_id from users_info where user_id=?";
    	prepareStatement = connection.prepareStatement(quary);
    	prepareStatement.setString(1,adminid);
    	resultSet=prepareStatement.executeQuery();
    	 if(resultSet.next())
    		 result = true; 
    	 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally
		   {
			   try{
				   connection.close();
				   prepareStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
    	return result;
	}
	
	public String addGuestUserDetails(AdminDomain adminDomain)
	{
		
        String status = null;
		String privilege=adminDomain.getPrivilege();
		//System.out.println("the privilege is:"+privilege);
		try {

			connection = DBTransaction.connect();
			String query="insert into users_info(username, user_id, password, email, address, contact_no,privilege) " +
					"values(?,?,?,?,?,?,?)";
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1,adminDomain.getAdminName());
			prepareStatement.setString(2,adminDomain.getAdminId());
			prepareStatement.setString(3,adminDomain.getPwd());
			prepareStatement.setString(4,adminDomain.getEmail());
			prepareStatement.setString(5,adminDomain.getAddress());
			prepareStatement.setString(6,adminDomain.getPhone());
			prepareStatement.setString(7,"Guest");
			
			
			//System.out.println("the query is:"+prepareStatement);
			int n = prepareStatement.executeUpdate();
			//System.out.println("query is inserted successfully");
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
		}catch(Exception e){
			e.printStackTrace();
		}finally
		   {
			   try{
				   connection.close();
				   prepareStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
			
}
}
	