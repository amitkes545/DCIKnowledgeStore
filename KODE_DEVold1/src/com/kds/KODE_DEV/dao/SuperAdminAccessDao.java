package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;


public class SuperAdminAccessDao  {
	Connection con= null;	
	//DBTransaction db= new DBTransaction();
	ResultSet rs= null;
	PreparedStatement ps=null;
	public String searchAccessDetails(AdminDomain sadomain)
	{
		
        String status = null;
		
		try {

			
			con=DBTransaction.connect();
			String sql= "select privilege from users_info where user_id='"+sadomain.getAdminId()+"'"+" and organization_id='"+sadomain.getOrgid()+"'";
			
			//System.out.println("query is"+sql);
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				String privilege=rs.getString(1);
				//System.out.println("privilege is"+privilege);
				status=privilege;
					//System.out.println("privilege is"+privilege);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("status value is"+status);
	
	  return status;

}
	ArrayList<String> ad=new ArrayList<String>();
	public ArrayList<String> getUserID(String orgid)
	{
	try {
			
			con=DBTransaction.connect();
			String sql= "select user_id from users_info where organization_id='"+orgid+"'"+" and privilege in('SuperAdmin','superadmin','Superadmin')";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			//System.out.println("quary is"+sql);
				while(rs.next())
				{	
					ad.add(rs.getString("user_id"));
				 
				 }
			 
		}catch(Exception e){
			e.printStackTrace();
		}
		 return ad;
	}
	public ArrayList<AdminDomain> retriveDetails(AdminDomain sadomain)
	{
		
		ArrayList<AdminDomain> admindomain=new ArrayList<AdminDomain>();
		//System.out.println("admin id :"+sadomain.getAdminId());
				try {

			
			con=DBTransaction.connect();
			String sql= "select process_id from process_mapping where user_id='"+sadomain.getAdminId()+"'";
			//System.out.println("quary is"+sql);
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
				while(rs.next())
				{	
					AdminDomain ad=new AdminDomain();
				ad.setProcessid(rs.getString("process_id"));
				//System.out.println("result process id:"+rs.getString("process_id"));
				admindomain.add(ad);
				 }
			 
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("status value is"+sadomain);
	
	  return admindomain;
	}
       
	public ArrayList<AdminDomain> accessDetails(AdminDomain sadomain)
	{
		
		ArrayList<AdminDomain> admindomain=new ArrayList<AdminDomain>();
				try {

			
			con=DBTransaction.connect();
			String sql= "select * from process_details where privilege='"+sadomain.getPrivilege()+"'"+" and organization_id='"+sadomain.getOrgid()+"'";
			//System.out.println("quary is"+sql);
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
				while(rs.next())
				{	
					AdminDomain ad=new AdminDomain();
				ad.setProcessid(rs.getString("process_id"));
				ad.setProcessname(rs.getString("process_name"));
				//System.out.println();
				admindomain.add(ad);
				 
	             
				 }
			 
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("status value is"+sadomain);
	
	  return admindomain;
	}
	
       
}
