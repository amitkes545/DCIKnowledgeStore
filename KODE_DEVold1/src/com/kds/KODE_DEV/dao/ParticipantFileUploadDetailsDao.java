package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;


public class ParticipantFileUploadDetailsDao {
	Connection con = null;
	PreparedStatement ps = null;
	public  String fileUploadDetails(AssessmentsDetailsDomain add) {
		String status="";
		//DBTransaction1 dbt = new DBTransaction1();
		try {
			con = DBTransaction.connect();
			java.util.Date utilDate = new Date();
			java.sql.Date date = new java.sql.Date(utilDate.getTime());
			//System.out.println("date :"+date);
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Time currentTime = new java.sql.Time(now.getTime());
			//System.out.println("time:"+currentTime);
		
			//PreparedStatement ps = null;
			//ResultSet rs1 = null;
		//	String quary1 = "";
			String query="insert into assessment_info(assessment_id,organization_id,uploaded_by,uploaded_time,uploaded_date,assessment_description,recipient_type,path_of_file,assessment_name) values(?,?,?,?,?,?,?,?,?)";
			 ps=con.prepareStatement(query);
			 ps.setString(1, add.getAssessmentId());
			ps.setString(2,add.getOrganizationId());
			ps.setString(3,add.getUploadedBy());
			ps.setTime(4,currentTime);
			ps.setDate(5,date);
			ps.setString(6,add.getAssessmentDescription());
			ps.setString(7,add.getRecipientType());
			ps.setString(8,add.getFilePath());
			ps.setString(9,add.getAssessmentName());
			System.out.println("the query file upload:"+query);
			int n = ps.executeUpdate();
			System.out.println("query is inserted successfully");
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
		}catch (Exception e){
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
	public  String InsertFileDetails(AssessmentsDetailsDomain add) {
		String status="";
		//Connection connection= null;	
		ResultSet resulSet= null;
		//PreparedStatement prepareStatement=null;
		//DBTransaction1 dbt = new DBTransaction1();
		try {
			con = DBTransaction.connect();
			//connection=DBTransaction.connect();
			java.util.Date utilDate = new Date();
			java.sql.Date date = new java.sql.Date(utilDate.getTime());
			//System.out.println("date :"+date);
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Time currentTime = new java.sql.Time(now.getTime());
			//System.out.println("time:"+currentTime);
		
			//PreparedStatement ps = null;
			//ResultSet rs1 = null;
		//	String quary1 = "";
			//System.out.println("select *from assessment_student_info where assessment_id='"+add.getAssessmentId()+"'");
			String existqry="select *from assessment_student_info where assessment_id='"+add.getAssessmentId()+"'";
			ps=con.prepareStatement(existqry);
			resulSet=ps.executeQuery();
			int n=0;
			if(resulSet.next())
			{
				//System.out.println("update assessment_student_info set path='"+add.getFilePath()+"' where assessment_id='"+add.getAssessmentId()+"'");
				String query="update assessment_student_info set path='"+add.getFilePath()+"' where assessment_id='"+add.getAssessmentId()+"'";
				ps=con.prepareStatement(query);
				 n=ps.executeUpdate();
			}
			else{
			String query="insert into assessment_student_info(assessment_id,uploaded_by,path) values(?,?,?)";
			 ps=con.prepareStatement(query);
			 ps.setString(1, add.getAssessmentId());
			ps.setString(2,add.getUploadedBy());
			ps.setString(3,add.getFilePath());
			
			//System.out.println("the query is:"+query);
			 n = ps.executeUpdate();
			}
			System.out.println("query is inserted successfully"+status);
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
			
		}catch (Exception e){
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
