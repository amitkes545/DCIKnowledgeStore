package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AssessmentDomain;


public class TrackAssignmentDao  {
	//DBTransaction db= new DBTransaction();
	Connection con= null;	
	ResultSet rs= null;
	PreparedStatement ps=null;
	ArrayList<AssessmentDomain> al= new ArrayList<AssessmentDomain>();
	
	public ArrayList<AssessmentDomain> retriveAssignmentID(String facultyid,String orgid){
		//System.out.println("dao faculty id:"+facultyid);
	try{
	
		con=DBTransaction.connect();
		String sql= "select assignment_id from assignment_info where organization_id='"+orgid+"'"+"and uploaded_by='"+facultyid+"'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		
		while(rs.next())
		{
			AssessmentDomain cDomain= new AssessmentDomain();
		 cDomain.setAssessmentId(rs.getString("assignment_id"));
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
	ArrayList<String> tl=new ArrayList<String>();
	public ArrayList<String> getrecipientTypeId(String assignid){
		//System.out.println("dao assessment id:"+assignid);
	try{
	
		con=DBTransaction.connect();
		String sql= "select recipient_type from assignment_info where assignment_id='"+assignid+"'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		
		while(rs.next())
		{
			
			tl.add(rs.getString("recipient_type"));
		 
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
	return  tl;	
	}
	ArrayList<String> TL=new ArrayList<String>();
	public ArrayList<String> getAssignmentTypeId(String studentid,String assignId){
		//System.out.println("dao student id:"+studentid);
	try{
	
		con=DBTransaction.connect();
		String sql= "select uploaded_by from assignment_student_info where assignment_id='"+assignId+"'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next())
		{
			tl.add(rs.getString("uploaded_by"));
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
	return  tl;	
	}
	public String getStudentId(String studentid){
		//System.out.println("dao student id:"+studentid);
		String status="";
	try{
	
		con=DBTransaction.connect();
		String sql= "select username from users_info where user_id='"+studentid+"'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next())
		{
			status=rs.getString("username");
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
	return  status;	
	}
}

