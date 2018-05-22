package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;

/**
 * Servlet implementation class AdminAccessDao
 */

public class AdminAccessDao  {
	Connection con= null;	
	//DBTransaction db= new DBTransaction();
	ResultSet rs= null;
	PreparedStatement ps=null;
	public String searchAccessDetails(AdminDomain domain)
	{
		
        String status = null;
		
		try {

			
			con=DBTransaction.connect();
			String sql= "select privilege from users_info where user_id='"+domain.getAdminId()+"'"+" and organization_id='"+domain.getOrgid()+"'";
			//System.out.println("quary is"+sql);
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
		//System.out.println("status value is"+status);
	
	  return status;

}
	public ArrayList<AdminDomain> accessAdminDetails(AdminDomain adomain)
	{
		
		ArrayList<AdminDomain> admindomain=new ArrayList<AdminDomain>();
				try {

			
			con=DBTransaction.connect();
			String sql= "select * from process_details where privilege='"+adomain.getPrivilege()+"'"+" and organization_id='"+adomain.getOrgid()+"'";
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
		//System.out.println("status value is"+adomain);
	
	  return admindomain;
	}
	public ArrayList<AdminDomain> retriveAdminDetails(AdminDomain adomain)
	{
		
		ArrayList<AdminDomain> admindomain=new ArrayList<AdminDomain>();
		//System.out.println("admin id :"+adomain.getAdminId());
				try {

			
			con=DBTransaction.connect();
			String sql= "select * from process_mapping where user_id='"+adomain.getAdminId()+"'";
			//System.out.println("quary is"+sql);
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
				while(rs.next())
				{	
					AdminDomain ad=new AdminDomain();
				ad.setProcessid(rs.getString("process_id"));
				//ad.setProcessname(rs.getString("process_name"));
				//System.out.println("result process id:"+rs.getString("process_id"));
				admindomain.add(ad);
				 }
			 
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
		//System.out.println("status value is"+adomain);
	
	  return admindomain;
	}
       
	public ArrayList<AdminDomain> retriveAdminAllDetails(AdminDomain adomain)
	{
		
		ArrayList<AdminDomain> admindomain=new ArrayList<AdminDomain>();
		//System.out.println("admin id :"+adomain.getAdminId());
				try {

			
			con=DBTransaction.connect();
			String sql= "select pmap.process_id,pd.process_name from process_mapping pmap,process_details pd where pmap.process_id=pd.process_id and pmap.user_id=? and pd.organization_id=?";
			//System.out.println("quary is"+sql);
			ps=con.prepareStatement(sql);
			ps.setString(1, adomain.getAdminId());
			ps.setString(2,adomain.getOrgid());
			rs=ps.executeQuery();
				while(rs.next())
				{	
					AdminDomain ad=new AdminDomain();
				ad.setProcessid(rs.getString("process_id"));
				ad.setProcessname(rs.getString("process_name"));
				//System.out.println("result process id:"+rs.getString("process_id"));
				admindomain.add(ad);
				 }
			 
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
		//System.out.println("status value is"+adomain);
	
	  return admindomain;
	}
}
