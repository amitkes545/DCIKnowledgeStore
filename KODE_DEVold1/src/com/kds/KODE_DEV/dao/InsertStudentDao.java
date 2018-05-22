package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;


public class InsertStudentDao  {
	//DBTransaction dbT = new DBTransaction(); 
	Connection con =null;
	PreparedStatement preparedStatement=null;
	public String addStudentDetails(AdminDomain sdomain)
	{
		
        String status = null;
		String privilege=sdomain.getPrivilege();
		//System.out.println("the privilege is:"+privilege);
		try {
 
			con = DBTransaction.connect();
			String quary="insert into users_info(username, user_id, password, email, address, contact_no, privilege, organization_id,created_by,country,state,city,postal_code,users_status,gender,dateofbirth) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement = con.prepareStatement(quary);
			preparedStatement.setString(1,sdomain.getAdminName());
			preparedStatement.setString(2,sdomain.getAdminId());
			preparedStatement.setString(3,sdomain.getPwd());
			preparedStatement.setString(4,sdomain.getEmail());
			preparedStatement.setString(5,sdomain.getAddress());
			preparedStatement.setString(6,sdomain.getPhone());
			preparedStatement.setString(7,"Student");
			preparedStatement.setString(8,sdomain.getOrgid());
			preparedStatement.setString(9,sdomain.getCreated_by());
			preparedStatement.setString(10,sdomain.getCountry());
			preparedStatement.setString(11,sdomain.getState());
			preparedStatement.setString(12, sdomain.getCity());
			preparedStatement.setString(13,sdomain.getPostalCode());
			preparedStatement.setString(14, "Active");
			preparedStatement.setString(15,sdomain.getGender());
			preparedStatement.setDate(16,sdomain.getDateofbirth());
			//System.out.println("the quary is:"+preparedStatement);
			int n = preparedStatement.executeUpdate();
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
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
			
	}
	public boolean email_conflict(String email)
	{
		
    	//PreparedStatement ps=null;
    	ResultSet rs=null;
    	//String adminid=adomain.getAdminId();
    	//System.out.println("Checking dublicate userid dao= "+adomain);
    	boolean result=false;
    	try
    	{
    	
    		con = DBTransaction.connect();
    	String quary="select user_id from users_info where email="+"'"+email+"'";
    	
    	preparedStatement = con.prepareStatement(quary);
    	/*ps.setString(1,adomain);*/
    	  rs=preparedStatement.executeQuery();
    	 if(rs.next()){
    		 result = true; 
    	 }else{
    		 result=false;
    	 }
    	 System.out.println("Checking Result for dublicate email=== "+preparedStatement);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
    	return result;
	}
	public boolean phone_conflict(String phone)
	{
		
    	//PreparedStatement ps=null;
    	ResultSet rs=null;
    	//String adminid=adomain.getAdminId();
    	//System.out.println("Checking dublicate userid dao= "+adomain);
    	boolean result=false;
    	try
    	{
    	
    		con = DBTransaction.connect();
    	String quary="select user_id from users_info where contact_no="+"'"+phone+"'";
    	
    	preparedStatement = con.prepareStatement(quary);
    	/*ps.setString(1,adomain);*/
    	  rs=preparedStatement.executeQuery();
    	 if(rs.next()){
    		 result = true; 
    	 }else{
    		 result=false;
    	 }
    	 System.out.println("Checking Result for dublicate phone=== "+preparedStatement);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
    	return result;
	}
	public boolean duplicate(String adomain)
	{
		
    	//PreparedStatement ps=null;
    	ResultSet rs=null;
    	//String adminid=adomain.getAdminId();
    	//System.out.println("Checking dublicate userid dao= "+adomain);
    	boolean result=false;
    	try
    	{
    	
    		con = DBTransaction.connect();
    	String quary="select user_id from users_info where user_id="+"'"+adomain+"'";
    	
    	preparedStatement = con.prepareStatement(quary);
    	/*ps.setString(1,adomain);*/
    	  rs=preparedStatement.executeQuery();
    	 if(rs.next()){
    		 result = true; 
    	 }else{
    		 result=false;
    	 }
    	 //System.out.println("Checking Result for dublicate=== "+result);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
    	return result;
	}
	
	public boolean duplicateAgain(String adomain)
	{
		
    	//PreparedStatement ps=null;
    	ResultSet rs=null;
    	//String adminid=adomain.getAdminId();
    	//System.out.println("Checking dublicate userid dao= "+adomain);
    	boolean result=false;
    	try
    	{
    	
    		con = DBTransaction.connect();
    	String quary="select user_id from users_info where user_id="+"'"+adomain+"'";
    	
    	preparedStatement = con.prepareStatement(quary);
    	/*ps.setString(1,adomain);*/
    	  rs=preparedStatement.executeQuery();
    	 if(rs.next()){
    		 result = true; 
    	 }else{
    		 result=false;
    	 }
    	 //System.out.println("Checking Result for dublicate=== "+result);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
    	return result;
	}
/*	public String studentFacultyMapping(AdminDomain sdomain)
	{
		
        String status = null;
		String privilege=sdomain.getPrivilege();
		//System.out.println("the privilege is:"+privilege);
		try {

			con = DBTransaction.connect();
			String quary="insert into student_faculty_mapping(organization_id, student_id, faculty_id) " +
					"values(?,?,?)";
			preparedStatement = con.prepareStatement(quary);
			preparedStatement.setString(1,sdomain.getOrgid());
			preparedStatement.setString(2,sdomain.getAdminId());
			preparedStatement.setString(3,sdomain.getCreated_by());
			//System.out.println("the quary is:"+ps);
			int n = preparedStatement.executeUpdate();
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
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
			
	}*/
}
