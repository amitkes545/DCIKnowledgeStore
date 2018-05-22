package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AssessmentDomain;

public class TrackAssessmentDao  {
	
	//DBTransaction db= new DBTransaction();
	Connection con= null;	
	ResultSet rs= null;
	PreparedStatement ps=null;
	ArrayList<AssessmentDomain> al= new ArrayList<AssessmentDomain>();
	
	public ArrayList<AssessmentDomain> retriveAssessmentID(String facultyid,String orgid){
		//System.out.println("dao faculty id:"+facultyid);
	try{
	
		con=DBTransaction.connect();
		String sql= "select assessment_id from assessment_info where organization_id='"+orgid+"'"+"and uploaded_by='"+facultyid+"'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		//System.out.println("sql query is:"+sql);
		while(rs.next())
		{
			AssessmentDomain cDomain= new AssessmentDomain();
		 cDomain.setAssessmentId(rs.getString("assessment_id"));
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
	
	public String getrecipientTypeId(String assessid){
		//System.out.println("dao assessment id:"+assessid);
		String status="";
	try{
	
		con=DBTransaction.connect();
		String sql= "select recipient_type from assessment_info where assessment_id='"+assessid+"'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		//System.out.println("query is:"+sql);
		while(rs.next())
		{
			
			status=rs.getString("recipient_type");
		 
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
	ArrayList<String> TL=new ArrayList<String>();
	public ArrayList<String> getAssessmentTypeId(String studentid,String assessId){
		//System.out.println("dao student id:"+studentid);
	try{
	
		con=DBTransaction.connect();
		String sql= "select uploaded_by from assessment_student_info where assessment_id='"+assessId+"'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next())
		{
			TL.add(rs.getString("uploaded_by"));
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
	return  TL;	
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
