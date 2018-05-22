package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class ParticipantAssessmentDetailsDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	 public ArrayList<AssessmentsDetailsDomain> selectCoursesForFaculty(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<AssessmentsDetailsDomain> arl=new  ArrayList<AssessmentsDetailsDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "SELECT distinct AI.assessment_id,AI.due_date,AI.assessment_type,assessment_name,assessment_description,AI.uploaded_by,AI.uploaded_date,uploaded_time,path_of_file,path,recipient_type,AI.marks FROM assessment_info AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id where organization_id='"+ri.getCourseFee()+"' and AI.assessment_id='"+ri.getCourseName()+"'";
			/*	String quary = "SELECT distinct AI.assessment_id,assessment_name,assessment_description,AI.uploaded_by,uploaded_date,uploaded_time,path_of_file,path,ASI.uploaded_by ,USER_ID "
						+ " FROM(SELECT AINFO.*  , UI.USER_ID FROM assessment_info AINFO left join student_groups SG on "
						+ "AINFO.recipient_type=SG.group_name and AINFO.organization_id = SG.organization_id "
						+ "cross join users_info UI where  privilege='Student' and (CASE WHEN AINFO.recipient_type = 'All' then TRUE "
						+ "WHEN UI.user_id IN (SELECT regexp_split_to_table(RECIPIENT_TYPE,E'\\#+')) THEN TRUE "
						+ "else UI.user_id IN (SELECT regexp_split_to_table(student_id,E'\\#+'))  end) "
						+ "and AINFO.assessment_id='"+ri.getCourseName()+"'    )AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id AND AI.USER_ID = ASI.UPLOADED_BY where organization_id='"+ri.getCourseFee()+"' "
								+ " and AI.USER_ID = '"+ri.getImagePath()+"' and AI.assessment_id='"+ri.getCourseName()+"'"
						+ " order by uploaded_date desc,uploaded_time desc";*/
				
				System.out.println("selectCourses="+quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					AssessmentsDetailsDomain dcd=new AssessmentsDetailsDomain();
					dcd.setAssessmentName(rs.getString("assessment_name"));
					dcd.setAssessmentId(rs.getString("assessment_id"));
					dcd.setAssessmentDescription(rs.getString("assessment_description"));
					dcd.setUploadedBy(rs.getString("uploaded_by"));
					dcd.setUploadedDate(rs.getString("uploaded_date"));
					dcd.setUploadedTime(rs.getString("uploaded_time"));
					dcd.setFilePath(rs.getString("path_of_file"));
					dcd.setFileUploadPath(rs.getString("path"));
					dcd.setRecipientType(rs.getString("recipient_type"));
					System.out.println("recipent type="+rs.getString("recipient_type"));
					System.out.println("recipent type="+rs.getString("marks"));
					dcd.setTotalMarks(rs.getString("marks"));
					dcd.setDueDate(rs.getString("due_date"));
					dcd.setAssessment_type(rs.getString("assessment_type"));
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
	 /*public ArrayList<AssessmentsDetailsDomain> selectCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<AssessmentsDetailsDomain> arl=new  ArrayList<AssessmentsDetailsDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
			//	String quary = "SELECT distinct AI.assessment_id,assessment_name,assessment_description,AI.uploaded_by,uploaded_date,uploaded_time,path_of_file,path FROM assessment_info AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id where organization_id='"+ri.getCourseFee()+"' and AI.assessment_id='"+ri.getCourseName()+"'";
				String quary = "SELECT distinct AI.assessment_id,assessment_name,assessment_description,AI.uploaded_by,uploaded_date,uploaded_time,path_of_file,path,ASI.uploaded_by ,USER_ID "
						+ " FROM(SELECT AINFO.*  , UI.USER_ID FROM assessment_info AINFO left join student_groups SG on "
						+ "AINFO.recipient_type=SG.group_name and AINFO.organization_id = SG.organization_id "
						+ "cross join users_info UI where  privilege='Student' and (CASE WHEN AINFO.recipient_type = 'All' then TRUE "
						+ "WHEN UI.user_id IN (SELECT regexp_split_to_table(RECIPIENT_TYPE,E'\\#+')) THEN TRUE "
						+ "else UI.user_id IN (SELECT regexp_split_to_table(student_id,E'\\#+'))  end) "
						+ "and AINFO.assessment_id='"+ri.getCourseName()+"'    )AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id AND AI.USER_ID = ASI.UPLOADED_BY where organization_id='"+ri.getCourseFee()+"' "
								+ " and AI.USER_ID = '"+ri.getImagePath()+"' and AI.assessment_id='"+ri.getCourseName()+"'"
						+ " order by uploaded_date desc,uploaded_time desc";
				
				System.out.println("selectCourses="+quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					AssessmentsDetailsDomain dcd=new AssessmentsDetailsDomain();
					dcd.setAssessmentName(rs.getString("assessment_name"));
					dcd.setAssessmentId(rs.getString("assessment_id"));
					dcd.setAssessmentDescription(rs.getString("assessment_description"));
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
	 public ArrayList<AssessmentsDetailsDomain> selectAllCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<AssessmentsDetailsDomain> arl=new  ArrayList<AssessmentsDetailsDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				//String quary = "SELECT distinct AI.assessment_id,assessment_name,assessment_description,AI.uploaded_by,uploaded_date,uploaded_time,path_of_file,path  FROM assessment_info AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id where organization_id='"+ri.getCourseFee()+"' and AI.uploaded_by='"+ri.getUploady()+"' and (recipient_type = '"+ri.getImagePath()+"' or recipient_type='All') order by uploaded_date desc,uploaded_time desc";
				String quary = "SELECT distinct AI.assessment_id,assessment_name,assessment_description,AI.uploaded_by,uploaded_date,uploaded_time,path_of_file,path "
						+ " FROM assessment_info AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id where organization_id='"+ri.getCourseFee()+"' "
								+ "and AI.uploaded_by='"+ri.getUploady()+"' and (recipient_type like '%"+ri.getImagePath()+"%' or recipient_type='All' or recipient_type in "
						+ "(select group_name from student_groups where student_id like '%"+ri.getImagePath()+"%' )) order by uploaded_date desc,uploaded_time desc";
			
				String quary = "SELECT distinct AI.assessment_id,assessment_name,assessment_description,AI.uploaded_by,uploaded_date,uploaded_time,path_of_file,path,ASI.uploaded_by ,USER_ID "
						+ " FROM(SELECT AINFO.*  , UI.USER_ID FROM assessment_info AINFO left join student_groups SG on "
						+ "AINFO.recipient_type=SG.group_name and AINFO.organization_id = SG.organization_id "
						+ "cross join users_info UI where  privilege='Student' and (CASE WHEN AINFO.recipient_type = 'All' then TRUE "
						+ "WHEN UI.user_id IN (SELECT regexp_split_to_table(RECIPIENT_TYPE,E'\\#+')) THEN TRUE "
						+ "else UI.user_id IN (SELECT regexp_split_to_table(student_id,E'\\#+'))  end) "
						+ "    )AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id AND AI.USER_ID = ASI.UPLOADED_BY where organization_id='"+ri.getCourseFee()+"' "
								+ " and AI.USER_ID = '"+ri.getImagePath()+"' "
						+ " order by uploaded_date desc,uploaded_time desc";
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					AssessmentsDetailsDomain dcd=new AssessmentsDetailsDomain();
					dcd.setAssessmentName(rs.getString("assessment_name"));
					dcd.setAssessmentId(rs.getString("assessment_id"));
					dcd.setAssessmentDescription(rs.getString("assessment_description"));
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
		   
	   }*/
	 
	 public ArrayList<AssessmentsDetailsDomain> selectCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<AssessmentsDetailsDomain> arl=new  ArrayList<AssessmentsDetailsDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
			//	String quary = "SELECT distinct AI.assessment_id,assessment_name,assessment_description,AI.uploaded_by,uploaded_date,uploaded_time,path_of_file,path FROM assessment_info AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id where organization_id='"+ri.getCourseFee()+"' and AI.assessment_id='"+ri.getCourseName()+"'";
			/*	String quary = "SELECT distinct AI.assessment_id,assessment_name,assessment_description,AI.uploaded_by,uploaded_date,uploaded_time,path_of_file,path,ASI.uploaded_by ,USER_ID "
						+ " FROM(SELECT AINFO.*  , UI.USER_ID FROM assessment_info AINFO left join student_groups SG on "
						+ "AINFO.recipient_type=SG.group_name and AINFO.organization_id = SG.organization_id "
						+ "cross join users_info UI where  privilege='Student' and (CASE WHEN AINFO.recipient_type = 'All' then TRUE "
						+ "WHEN UI.user_id IN (SELECT regexp_split_to_table(RECIPIENT_TYPE,E'\\#+')) THEN TRUE "
						+ "else UI.user_id IN (SELECT regexp_split_to_table(student_id,E'\\#+'))  end) "
						+ "and AINFO.assessment_id='"+ri.getCourseName()+"'    )AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id AND AI.USER_ID = ASI.UPLOADED_BY where organization_id='"+ri.getCourseFee()+"' "
								+ " and AI.USER_ID = '"+ri.getImagePath()+"' and AI.assessment_id='"+ri.getCourseName()+"'"
						+ " order by uploaded_date desc,uploaded_time desc";
				*/
				
				
				System.out.println("updated file");
				
			
				
				
				String quary="select assessment_id ,assessment_name,assessment_description,uploaded_by,uploaded_date,uploaded_time,path_of_file from assessment_info where  assessment_id='"+ri.getCourseName()+"'";
				System.out.println("selectCourses="+quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					AssessmentsDetailsDomain dcd=new AssessmentsDetailsDomain();
					dcd.setAssessmentName(rs.getString("assessment_name"));
					dcd.setAssessmentId(rs.getString("assessment_id"));
					dcd.setAssessmentDescription(rs.getString("assessment_description"));
					dcd.setUploadedBy(rs.getString("uploaded_by"));
					dcd.setUploadedDate(rs.getString("uploaded_date"));
					dcd.setUploadedTime(rs.getString("uploaded_time"));
					dcd.setFilePath(rs.getString("path_of_file"));
					//dcd.setFileUploadPath(rs.getString("path"));
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
				//String quary = "SELECT distinct AI.assessment_id,assessment_name,assessment_description,AI.uploaded_by,uploaded_date,uploaded_time,path_of_file,path  FROM assessment_info AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id where organization_id='"+ri.getCourseFee()+"' and AI.uploaded_by='"+ri.getUploady()+"' and (recipient_type = '"+ri.getImagePath()+"' or recipient_type='All') order by uploaded_date desc,uploaded_time desc";
				/*String quary = "SELECT distinct AI.assessment_id,assessment_name,assessment_description,AI.uploaded_by,uploaded_date,uploaded_time,path_of_file,path "
						+ " FROM assessment_info AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id where organization_id='"+ri.getCourseFee()+"' "
								+ "and AI.uploaded_by='"+ri.getUploady()+"' and (recipient_type like '%"+ri.getImagePath()+"%' or recipient_type='All' or recipient_type in "
						+ "(select group_name from student_groups where student_id like '%"+ri.getImagePath()+"%' )) order by uploaded_date desc,uploaded_time desc";
			*/
				
				System.out.println("updated file all");
				
				/*String quary = "SELECT distinct AI.assessment_id,assessment_name,assessment_description,AI.uploaded_by,uploaded_date,uploaded_time,path_of_file,path,ASI.uploaded_by ,USER_ID "
						+ " FROM(SELECT AINFO.*  , UI.USER_ID FROM assessment_info AINFO left join student_groups SG on "
						+ "AINFO.recipient_type=SG.group_name and AINFO.organization_id = SG.organization_id "
						+ "cross join users_info UI where  privilege='Student' and (CASE WHEN AINFO.recipient_type = 'All' then TRUE "
						+ "WHEN UI.user_id IN (SELECT regexp_split_to_table(RECIPIENT_TYPE,E'\\#+')) THEN TRUE "
						+ "else UI.user_id IN (SELECT regexp_split_to_table(student_id,E'\\#+'))  end) "
						+ "    )AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id AND AI.USER_ID = ASI.UPLOADED_BY where organization_id='"+ri.getCourseFee()+"' "
								+ " and AI.USER_ID = '"+ri.getImagePath()+"' "
						+ " order by uploaded_date desc,uploaded_time desc";*/
				//System.out.println(quary);
				
				System.out.println("dao allll");
			
				
				String quary="select assessment_id ,assessment_name,assessment_description,uploaded_by,uploaded_date,uploaded_time,path_of_file from assessment_info";
				System.out.println("selectCourses="+quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					AssessmentsDetailsDomain dcd=new AssessmentsDetailsDomain();
					dcd.setAssessmentName(rs.getString("assessment_name"));
					dcd.setAssessmentId(rs.getString("assessment_id"));
					dcd.setAssessmentDescription(rs.getString("assessment_description"));
					dcd.setUploadedBy(rs.getString("uploaded_by"));
					dcd.setUploadedDate(rs.getString("uploaded_date"));
					dcd.setUploadedTime(rs.getString("uploaded_time"));
					dcd.setFilePath(rs.getString("path_of_file"));
					//dcd.setFileUploadPath(rs.getString("path"));
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
	 
	 
	 
	 
	 public ArrayList<AssessmentsDetailsDomain> selectAllViewCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<AssessmentsDetailsDomain> arl=new  ArrayList<AssessmentsDetailsDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "SELECT distinct AI.assessment_id,AI.due_date,AI.assessment_type,assessment_name,assessment_description,AI.uploaded_by,AI.uploaded_date,uploaded_time,path_of_file,path,recipient_type,AI.marks  FROM assessment_info AI left join assessment_student_info ASI on AI.assessment_id=ASI.assessment_id where organization_id='"+ri.getCourseFee()+"' and AI.uploaded_by='"+ri.getUploady()+"'  order by uploaded_date desc,uploaded_time desc";
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				System.out.println("quary="+quary);

				rs = pstmt.executeQuery();
				while(rs.next()){
					AssessmentsDetailsDomain dcd=new AssessmentsDetailsDomain();
					dcd.setAssessmentName(rs.getString("assessment_name"));
					dcd.setAssessmentId(rs.getString("assessment_id"));
					dcd.setAssessmentDescription(rs.getString("assessment_description"));
					dcd.setUploadedBy(rs.getString("uploaded_by"));
					dcd.setUploadedDate(rs.getString("uploaded_date"));
					dcd.setUploadedTime(rs.getString("uploaded_time"));
					dcd.setFilePath(rs.getString("path_of_file"));
					dcd.setFileUploadPath(rs.getString("path"));
					dcd.setRecipientType(rs.getString("recipient_type"));
					dcd.setTotalMarks(rs.getString("marks"));
					dcd.setAssessment_type(rs.getString("assessment_type"));
					dcd.setDueDate(rs.getString("due_date"));
					System.out.println("date in rs="+rs.getString("due_date"));
					System.out.println("due date in domain="+dcd.getDueDate());
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

