package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class ParticipantAssignmentDetailsDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	 public ArrayList<AssessmentsDetailsDomain> selectCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<AssessmentsDetailsDomain> arl=new  ArrayList<AssessmentsDetailsDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "SELECT distinct AI.assignment_id,assignment_name,assignment_description,AI.uploaded_by,uploaded_date,uploaded_time,AI.path_of_file AIP,ASI.path_of_file ASIP FROM assignment_info AI left join assignment_student_info ASI on AI.assignment_id=ASI.assignment_id where organization_id='"+ri.getCourseFee()+"' and AI.assignment_id='"+ri.getCourseName()+"'";
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					AssessmentsDetailsDomain dcd=new AssessmentsDetailsDomain();				
					dcd.setAssessmentName(rs.getString("assignment_name"));
					dcd.setAssessmentId(rs.getString("assignment_id"));
					dcd.setAssessmentDescription(rs.getString("assignment_description"));
					dcd.setUploadedBy(rs.getString("uploaded_by"));
					dcd.setUploadedDate(rs.getString("uploaded_date"));
					dcd.setUploadedTime(rs.getString("uploaded_time"));
					dcd.setFilePath(rs.getString("AIP"));
					dcd.setFileUploadPath(rs.getString("ASIP"));
					arl.add(dcd);
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
			
		return arl;
		   
	   }
	 public ArrayList<AssessmentsDetailsDomain> selectAllCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<AssessmentsDetailsDomain> arl=new  ArrayList<AssessmentsDetailsDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "SELECT distinct AI.assignment_id,assignment_name,assignment_description,AI.uploaded_by,uploaded_date,uploaded_time,AI.path_of_file,ASI.path_of_file path FROM assignment_info AI left join assignment_student_info ASI on AI.assignment_id=ASI.assignment_id where organization_id='"+ri.getCourseFee()+"' and AI.uploaded_by='"+ri.getUploady()+"'  and (recipient_type = '"+ri.getImagePath()+"' or recipient_type='All')";
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					AssessmentsDetailsDomain dcd=new AssessmentsDetailsDomain();				
					dcd.setAssessmentName(rs.getString("assignment_name"));
					dcd.setAssessmentId(rs.getString("assignment_id"));
					dcd.setAssessmentDescription(rs.getString("assignment_description"));
					dcd.setUploadedBy(rs.getString("uploaded_by"));
					dcd.setUploadedDate(rs.getString("uploaded_date"));
					dcd.setUploadedTime(rs.getString("uploaded_time"));
					dcd.setFilePath(rs.getString("path_of_file"));
					dcd.setFileUploadPath(rs.getString("path"));
					arl.add(dcd);
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
			
		return arl;
		   
	   }
	}	

