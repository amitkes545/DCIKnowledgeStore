package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.CreateDomain;
import com.kds.KODE_DEV.services.CreateCustomerServlet;


public class CustomerDao  {
Connection con= null;	
ArrayList<String> arl= new ArrayList<String>();
	PreparedStatement ps=null;
	//DBTransaction dbT = new DBTransaction();
	static final Logger LOGGER = Logger.getLogger(CustomerDao.class);
	public String insertCustomerValues(AdminDomain cdomain)
	{
		
        String status = null;
		String privilege=cdomain.getPrivilege();
		//System.out.println("the privilege is:"+privilege);
		
		try {

			con = DBTransaction.connect();
			String quary="insert into users_info(username, user_id, password, email, address, contact_no, privilege,organization_id,created_by) " +
					"values(?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(quary);
			ps.setString(1,cdomain.getAdminName());
			ps.setString(2,cdomain.getAdminId());
			ps.setString(3,cdomain.getPwd());
			ps.setString(4,cdomain.getEmail());
			ps.setString(5,cdomain.getAddress());
			ps.setString(6,cdomain.getPhone());
			ps.setString(7,cdomain.getPrivilege());
			ps.setString(8,cdomain.getOrgid());
			ps.setString(9,cdomain.getCreated_by());
			//ps.setString(8,cdomain.getOrgDetails());
			//ps.setString(9,cdomain.getOrgType());
			//String orgtype=cdomain.getOrgType();
			////System.out.println("organization type is"+orgtype);
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
	
	public ArrayList<String> fetchValueCustomerType() {
		
		
		try {
			con = DBTransaction.connect();
			
			String sql = "SELECT distinct (top_layer_teaching_source_name) from transaction_top_layer_teaching_source";
		
			ps= con.prepareStatement(sql);

		System.out.println("the query is fetchValueDiscipline:" + sql);
		
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {	
				CreateDomain createDomain= new CreateDomain();
				////System.out.println(resultSet.getString("organization_id"));
			   //  sDomain.setOrgId(resultSet.getString("top_layer_teaching_source_name"));
			   //  sDomain.setOrgName(resultSet.getString("discipline_name"));
				createDomain.setOrg_id(resultSet.getString("top_layer_teaching_source_name"));
				String orgid=resultSet.getString("top_layer_teaching_source_name");
				//String orgidname=orgid+"("+orgname+")";
				arl.add(orgid);
				//arl.add(orgname);
								 
			}
		
		} catch (Exception e) {
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
		return arl;
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
	
	public ArrayList<CreateDomain> getRetriveid(){
		ArrayList<CreateDomain> sd= new ArrayList<CreateDomain>();
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
				CreateDomain createDomain= new CreateDomain();
				createDomain.setOrg_id(rs.getString("organization_id"));
				createDomain.setOrg_name(rs.getString("organization_name"));
				////System.out.println("organization id:"+createDomain.getOrg_id()+ "organization name:"+createDomain.getOrg_name());
			     sd.add(createDomain);
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
		return sd;	
		}
	 //method for password created ramdomly
    public  String randomSeriesForThreeCharacter() {
           LOGGER.info("calling password method");
	    Random random = new Random();
	    LOGGER.info("random charactor:"+random);
	             String value="";
	                int random_Char ;
	    for(int i=0; i<2;i++)
	             { 
	               random_Char = random.nextInt(74);
	                value=value+random_Char;
	             }
	    return value;
	}
}
	



