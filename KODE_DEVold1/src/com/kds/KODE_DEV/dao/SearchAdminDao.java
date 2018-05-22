package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;




/**
 * Servlet implementation class SearchAdminDao
 */

public class SearchAdminDao {
	Connection con= null;	
	//DBTransaction db= new DBTransaction();
	ResultSet rs= null;
	PreparedStatement ps=null;
	ArrayList<AdminDomain> ad= new ArrayList<AdminDomain>();
	ArrayList<AdminDomain> sd= new ArrayList<AdminDomain>();
	public ArrayList<AdminDomain> getAdminId(String orgid,String userid){
		
		
	try{
		
		con=DBTransaction.connect();
		String sql= "select user_id from users_info where privilege in ('superadmin','admin','Superadmin','SuperAdmin','Admin','faculty','student','Student','Faculty','Client')"+" and created_by='"+userid+"'"+" and organization_id='"+orgid+"'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		//System.out.println("the query is:"+ps);
		while(rs.next())
		{
		 AdminDomain adomain= new AdminDomain();
		 adomain.setAdminId(rs.getString("user_id"));
		 ad.add(adomain);
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
	//System.out.println("result ids are:"+ad);
	return ad;	
	}
	/*
	public ArrayList<AdminDomain> selectAdminID(String orgid,String userid){
		Connection con= null;	
		//DBTransaction db= new DBTransaction();
		ResultSet rs= null;
		PreparedStatement ps=null;
	try{
		
		con=DBTransaction.connect();
		String sql= "select user_id from users_info where privilege in ('admin','Admin')"+" and organization_id='"+orgid+"'"+" and created_by='"+userid+"'";
		ps=con.prepareStatement(sql);
		//System.out.println("sql query is:"+sql);
		rs=ps.executeQuery();
		while(rs.next())
		{
		 AdminDomain adomain= new AdminDomain();
		 adomain.setAdminId(rs.getString("user_id"));
		 sd.add(adomain);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return sd;	
	}*/
	ArrayList<AdminDomain> fd= new ArrayList<AdminDomain>();
	public ArrayList<AdminDomain> selectFacultyId(String orgid,String userid){
		//Connection con= null;	
		//DBTransaction db= new DBTransaction();
		//ResultSet rs= null;
		//PreparedStatement ps=null;
	try{
		 
		con=DBTransaction.connect();
		String sql= "select user_id from users_info where privilege in ('faculty','Faculty') and organization_id='"+orgid+"'"+" and created_by='"+userid+"'";

		ps=con.prepareStatement(sql);
		//System.out.println("sql quary is:"+sql);
		rs=ps.executeQuery();
		while(rs.next())
		{
		 AdminDomain adomain= new AdminDomain();
		 adomain.setAdminId(rs.getString("user_id"));
		 fd.add(adomain);
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
	return fd;	
	}
	Set<String> Fd= new HashSet<String>();
	public Set<String> selectFacultyID(String orgid){
		Connection con= null;	
		//DBTransaction db= new DBTransaction();
		ResultSet rs= null;
		PreparedStatement ps=null;
	try{
		
		con=DBTransaction.connect();
		//String sql= "select user_id from users_info where organization_id=(select organization_id from users_info where  privilege in ('faculty'))";
          String sql="select faculty_id from faculty_subject_mapping where organization_id='"+orgid+"'";
		ps=con.prepareStatement(sql);
		//System.out.println("sql quary is:"+sql);
		rs=ps.executeQuery();
		while(rs.next())
		{
		 //AdminDomain adomain= new AdminDomain();
		// adomain.setAdminId(rs.getString("faculty_id"));
		 Fd.add(rs.getString("faculty_id"));
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
	
	return Fd;	
	}
	ArrayList<AdminDomain> SD= new ArrayList<AdminDomain>();
	public ArrayList<AdminDomain> selectSuperAdminID(String orgid,String userid){
		//Connection con= null;	
		//DBTransaction db= new DBTransaction();
		//ResultSet rs= null;
		//PreparedStatement ps=null;
		
	try{
		
		con=DBTransaction.connect();
		String sql= "select user_id from users_info where privilege  in ('SuperAdmin','superadmin','Superadmin')"+" and organization_id='"+orgid+"'"+" and created_by='"+userid+"'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		//System.out.println("the query is:"+ps);
		while(rs.next())
		{
		 AdminDomain adomain= new AdminDomain();
		 adomain.setAdminId(rs.getString("user_id"));
		 SD.add(adomain);
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
	//System.out.println("status of SD:"+SD);
	return SD;	
	}
	
	ArrayList<AdminDomain> FD= new ArrayList<AdminDomain>();
	public ArrayList<AdminDomain> getFacultyID(String orgid,String userid){
		//Connection con= null;	
		//DBTransaction db= new DBTransaction();
		//ResultSet rs= null;
		//PreparedStatement ps=null;
	try{
		 
		con=DBTransaction.connect();
		String sql= "select user_id from users_info where privilege in ('faculty','Faculty') and organization_id='"+orgid+"'"+" and created_by='"+userid+"'";

		ps=con.prepareStatement(sql);
		//System.out.println("sql quary is:"+sql);
		rs=ps.executeQuery();
		while(rs.next())
		{
		 AdminDomain adomain= new AdminDomain();
		 adomain.setAdminId(rs.getString("user_id"));
		 FD.add(adomain);
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
	return FD;	
	
		}
	public ArrayList<AdminDomain> selectAccessID(String orgid,String userid){
		//Connection con= null;	
		//DBTransaction db= new DBTransaction();
		//ResultSet rs= null;
		//PreparedStatement ps=null;
	try{
		
		con=DBTransaction.connect();
		String sql= "select user_id from users_info where privilege in ('admin','Admin')and organization_id='"+orgid+"'"+" and created_by='"+userid+"'";
		ps=con.prepareStatement(sql);
		//System.out.println("sql query is:"+sql);
		rs=ps.executeQuery();
		while(rs.next())
		{
		 AdminDomain adomain= new AdminDomain();
		 adomain.setAdminId(rs.getString("user_id"));
		 sd.add(adomain);
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
	ArrayList<AdminDomain> adminid= new ArrayList<AdminDomain>();
	public ArrayList<AdminDomain> selectAdminID(String orgid,String userid){
		//Connection con= null;	
		//DBTransaction db= new DBTransaction();
		//ResultSet rs= null;
		//PreparedStatement ps=null;
		
	try{
		
		con=DBTransaction.connect();
		String sql= "select user_id from users_info where organization_id='"+orgid+"'"+" and created_by='"+userid+"'"+" and privilege in('admin','Admin')";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		//System.out.println("the query is:"+ps);
		while(rs.next())
		{
		 AdminDomain adomain= new AdminDomain();
		 adomain.setAdminId(rs.getString("user_id"));
		 adminid.add(adomain);
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
	//System.out.println("status of SD:"+adminid);
	return adminid;	
	}
	ArrayList<AdminDomain> userID= new ArrayList<AdminDomain>();
	public ArrayList<AdminDomain> selectSuperAdminReportsID(String orgid){
		//Connection con= null;	
		//DBTransaction db= new DBTransaction();
		//ResultSet rs= null;
		//PreparedStatement ps=null;
		
	try{
		
		con=DBTransaction.connect();
		String sql= "select user_id from users_info where organization_id='"+orgid+"'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		//System.out.println("the query is:"+ps);
		while(rs.next())
		{
		 AdminDomain adomain= new AdminDomain();
		 adomain.setAdminId(rs.getString("user_id"));
		 userID.add(adomain);
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
	//System.out.println("status of SD:"+userID);
	return userID;	
	}
	
	ArrayList<AdminDomain> admindomain= new ArrayList<AdminDomain>();
	public ArrayList<AdminDomain> selectStudentId(String orgid,String userid){
		//Connection con= null;	
		//DBTransaction db= new DBTransaction();
		//ResultSet rs= null;
		//PreparedStatement ps=null;
	try{
		 
		con=DBTransaction.connect();
		String sql= "select user_id from users_info where privilege in ('student','Student') and organization_id='"+orgid+"'"+" and created_by='"+userid+"'";

		ps=con.prepareStatement(sql);
		//System.out.println("sql quary is:"+sql);
		rs=ps.executeQuery();
		while(rs.next())
		{
		 AdminDomain adomain= new AdminDomain();
		 adomain.setAdminId(rs.getString("user_id"));
		 fd.add(adomain);
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
	return fd;	
}
}
