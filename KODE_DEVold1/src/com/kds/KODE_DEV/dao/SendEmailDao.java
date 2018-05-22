package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;


public class SendEmailDao  {
	Connection con= null;	
	//DBTransaction db= new DBTransaction();
	ResultSet rs= null;
	PreparedStatement ps=null;
	public SenderEmailDetailsDomain senderdetails(){
		
		SenderEmailDetailsDomain sdomain= new SenderEmailDetailsDomain();
	try{
		
		con=DBTransaction.connect();
		String sql= "select * from senderemaildetails";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		//System.out.println("the query is:"+ps);
		while(rs.next())
		{
			
		     sdomain.setEmailid(rs.getString("emailid"));
		     sdomain.setHost(rs.getString("host"));
		     sdomain.setPassword(rs.getString("password"));
		     sdomain.setPop(rs.getString("pop"));
		     sdomain.setPort(rs.getInt("port"));
		
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
	return sdomain;	
	}
	public String getClassRoomLink(){
		String status="";
		try{
			con=DBTransaction.connect();
			String sql= "select link from classroom_link";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			//System.out.println("the query is:"+ps);
			while(rs.next())
			{
			    status=rs.getString("link");
			 
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
		return status;	
		}
	public String getWebLink(){
		String status="";
		try{
			con=DBTransaction.connect();
			String sql= "select web_link from classroom_link";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			//System.out.println("the query is:"+ps);
			while(rs.next())
			{
			    status=rs.getString("web_link");
			 
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
		
		return status;	
		}
	public String getKnowStoreLink(){
		String status="";
		try{
			con=DBTransaction.connect();
			String sql= "select kstore_link from classroom_link";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			//System.out.println("the query is:"+ps);
			while(rs.next())
			{
			    status=rs.getString("kstore_link");
			 
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
		return status;	
		}
	}

