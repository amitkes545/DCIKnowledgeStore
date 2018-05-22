package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;

/**
 * Servlet implementation class AccessReportsDao
 */

public class AccessReportsDao  {
	Connection con= null;	
	//DBTransaction db= new DBTransaction();
	ResultSet rs= null;
	PreparedStatement pstmt=null;
ArrayList<AdminDomain> al= new ArrayList<AdminDomain>();
	
	AdminDomain adomain= new AdminDomain();
	//DBTransaction DBT = new DBTransaction();
	public  ArrayList<AdminDomain> searchAccessReports(AdminDomain dDomain) {
		
		ArrayList<AdminDomain> admindomain=new ArrayList<AdminDomain>();
           //System.out.println("this is data base page");
		try {
			
			 con = DBTransaction.connect();
			
			//String quary = "select * from process_mapping where user_id='"+dDomain.getAdminId()+"'";
			String getProcess_Details="select pmap.process_id,pd.process_name from process_mapping pmap,process_details pd where pmap.process_id=pd.process_id and pmap.user_id=? and pd.organization_id=?";
			//System.out.println("the quary is:"+getProcess_Details);
			
			pstmt = con.prepareStatement(getProcess_Details);
			pstmt.setString(1, dDomain.getAdminId());
			pstmt.setString(2,dDomain.getOrgid());
			rs = pstmt.executeQuery();
			 while(rs.next()) {
				 AdminDomain ad=new AdminDomain();
				String processid=rs.getString("process_id");
				String roleid=rs.getString("process_name");
				//System.out.println("process id are"+processid);
				ad.setProcessid(processid);
				ad.setProcessname(roleid);
				admindomain.add(ad);
				
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
		//System.out.println("the return value is:"+dDomain);
		
		return admindomain;
	}
	public boolean duplicate(AdminDomain udomain)
	{
		
    	
    	
    	boolean result=false;
    	try
    	{
    		Connection con = DBTransaction.connect();
         
         String quary = "select user_id from process_mapping where user_id='"+udomain.getAdminId()+"'";
         pstmt = con.prepareStatement(quary);
    	
    	  rs=pstmt.executeQuery();
    	 if(rs.next())
    		 result = true;
    	}
    	catch(Exception e){
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
public  ArrayList<AdminDomain> getProcessName(AdminDomain udao ,String orgid) {
		
		ArrayList<AdminDomain> admindomain=new ArrayList<AdminDomain>();
           //System.out.println("this is data base page");
		try {
			
			 con = DBTransaction.connect();
			
			String quary = "select process_name from process_details where user_id='"+udao.getAdminId()+"'"+" and organization_id='"+orgid+"'";
			//System.out.println("the quary is:"+quary);
			pstmt = con.prepareStatement(quary);
			rs = pstmt.executeQuery();
			 while(rs.next()) {
				 AdminDomain ad=new AdminDomain();
				String processid=rs.getString("process_name");
				ad.setProcessid(processid);
				admindomain.add(ad);
				
			}
			////System.out.println("the return value is:"+dDomain);
				
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
		return admindomain;
	}
ArrayList<String> sadminid=new ArrayList<String>();
public ArrayList<String> getSuperadminUserId(String orgid)
{
try {
		
		con=DBTransaction.connect();
		String sql= "select user_id from users_info where organization_id='"+orgid+"'"+" and privilege in('SuperAdmin','superadmin','Superadmin')";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		//System.out.println("quary is"+sql);
			while(rs.next())
			{	
				sadminid.add(rs.getString("user_id"));
			 
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
	 return sadminid;
}
ArrayList<String> adminid=new ArrayList<String>();
public ArrayList<String> getAdminUserId(String orgid,String userid)
{
try {
		
		con=DBTransaction.connect();
		String sql= "select user_id from users_info where organization_id='"+orgid+"'"+" and created_by='"+userid+"'"+ " and privilege in('Admin','admin')";
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
