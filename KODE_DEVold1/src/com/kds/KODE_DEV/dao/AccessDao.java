package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.CreateDomain;
public class AccessDao  {
	Connection con= null;	
	//DBTransaction db= new DBTransaction();
	ResultSet rs= null;
	PreparedStatement ps=null;
	public String searchAccessDetails(String userid)
	{
		
        String status = null;
	//	System.out.println("userid is"+userid);
		
		try {

			
			con=DBTransaction.connect();
			String sql= "select organization_id from users_info where user_id='"+userid+"'";
			//System.out.println("quary is"+sql);
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				String orgid=rs.getString(1);
				//System.out.println("organization id is"+orgid);
				status=orgid;
				String org_id=rs.getString("organization_id");

				//System.out.println("userid is"+org_id);

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
	public ArrayList<AdminDomain> accessDetails(AdminDomain pdomain)
	{
		
		ArrayList<AdminDomain> admindomain=new ArrayList<AdminDomain>();
				try {

			
			con=DBTransaction.connect();
			String sql= "select * from process_details where organization_id='"+pdomain.getOrgid()+"'"+" and privilege='"+pdomain.getPrivilege()+"'";
			//System.out.println("quary is"+sql);
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
				while(rs.next())
				{	
					AdminDomain ad=new AdminDomain();
				ad.setProcessid(rs.getString("process_id"));
				ad.setProcessname(rs.getString("process_name"));
				ad.setOrgid(rs.getString("organization_id"));
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
		System.out.println("status value is"+pdomain);
	
	  return admindomain;
	}
	public ArrayList<AdminDomain> retriveDetails(AdminDomain pdomain)
	{
		
		ArrayList<AdminDomain> admindomain=new ArrayList<AdminDomain>();
		System.out.println("admin id :"+pdomain.getAdminId());
				try {

			
			con=DBTransaction.connect();
			String sql= "select process_id from process_mapping where user_id='"+pdomain.getAdminId()+"'";
			System.out.println("quary is"+sql);
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
				while(rs.next())
				{	
					AdminDomain ad=new AdminDomain();
				ad.setProcessid(rs.getString("process_id"));
				System.out.println("result process id:"+rs.getString("process_id"));
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
		System.out.println("status value is"+pdomain);
	
	  return admindomain;
	}
	ArrayList<CreateDomain> arrayList= new ArrayList<CreateDomain>();
	public ArrayList<CreateDomain> selectAccessId(){
		
		
	try{
		
		con=DBTransaction.connect();
		String sql= "select organization_id,organization_name from org_details";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		System.out.println("the query is:"+ps);
		while(rs.next())
		{
			CreateDomain createDomain= new CreateDomain();
			createDomain.setOrg_id(rs.getString("organization_id"));
			createDomain.setOrg_name(rs.getString("organization_name"));
			arrayList.add(createDomain);
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
	//System.out.println("organization ids are:"+orgid);
	return arrayList;	
	}
}
	


