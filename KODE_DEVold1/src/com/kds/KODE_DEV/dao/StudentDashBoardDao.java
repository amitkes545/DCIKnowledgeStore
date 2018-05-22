package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AssessmentDomain;
import com.kds.KODE_DEV.domain.FacilitatorKnowReportDomain;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;
import com.kds.KODE_DEV.domain.SessionDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class StudentDashBoardDao {

	PreparedStatement prepareStatement = null;
	Connection connection=null;
	ResultSet resultSet = null;
	 public ArrayList<RetriveImagesDomain> selectKnowledgeAssetsName(UsersInfoDomain uid){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<RetriveImagesDomain> arl=new  ArrayList<RetriveImagesDomain>();
			try {
				connection = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "select subject,ksid from knowledgeassets_info where ksid=(SELECT ksid FROM knowledgestore_info where organization_id='"+uid.getOrgId()+"' and user_id='"+uid.getUserId()+"') and upload_date >= current_date order by upload_date desc";
				//System.out.println(quary);
				prepareStatement = connection.prepareStatement(quary);
				rs = prepareStatement.executeQuery();
				while(rs.next())
				{
					RetriveImagesDomain dcd=new RetriveImagesDomain();
					dcd.setCourseName(rs.getString("subject"));
					arl.add(dcd);
				}
				}catch(Exception e){
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   connection.close();
					   prepareStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl;
		   
	   }
	 
	 public ArrayList<AssessmentDomain> selectAsssesmentName(UsersInfoDomain uid){
			//DBTransaction1 dbt = new DBTransaction1();
		//	DBTransaction1 dbt1 = new DBTransaction1();
			 ArrayList<AssessmentDomain> arl=new  ArrayList<AssessmentDomain>();
			try {
				connection = DBTransaction.connect();
				
				String quary = "SELECT assessment_name,uploaded_time,uploaded_date FROM assessment_info where recipient_type='"+uid.getUserId()+"' limit 5";	
				//String quary = "SELECT assessment_name,uploaded_time,uploaded_date FROM assessment_info where organization_id='"+uid.getOrgId()+"' and uploaded_by='"+uid.getUserId()+"'";
				//System.out.println(quary);
				prepareStatement = connection.prepareStatement(quary);
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next())
				{
					AssessmentDomain domain=new AssessmentDomain();
					domain.setAssessmentName(resultSet.getString("assessment_name"));
					domain.setUploadedDate(resultSet.getString("uploaded_date"));
					domain.setUploadedTime(resultSet.getString("uploaded_time"));
					//dcd.setCourseImageLocation(rs.getString("uploaded_by"));
					arl.add(domain);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   connection.close();
					   prepareStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl;
		   
	   }
	 public ArrayList<AssessmentDomain> selectAsssignmentName(UsersInfoDomain uid){
			//DBTransaction1 dbt = new DBTransaction1();
		//	DBTransaction1 dbt1 = new DBTransaction1();
			 ArrayList<AssessmentDomain> arl=new  ArrayList<AssessmentDomain>();
			try {
				connection = DBTransaction.connect();
							
				String quary = "SELECT assignment_name,uploaded_time,uploaded_date FROM assignment_info where recipient_type='"+uid.getUserId()+"' limit 5";
				//System.out.println(quary);
				prepareStatement = connection.prepareStatement(quary);
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next())
				{
					AssessmentDomain domain=new AssessmentDomain();
					domain.setAssessmentName(resultSet.getString("assignment_name"));
					domain.setUploadedDate(resultSet.getString("uploaded_date"));
					domain.setUploadedTime(resultSet.getString("uploaded_time"));
					////System.out.println("assignment name::"+resultSet.getString("assignment_name"));
					//domain.set
					//dcd.setCourseImageLocation(rs.getString("uploaded_by"));
					arl.add(domain);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   connection.close();
					   prepareStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl;
		   
	   }
	 public ArrayList<SessionDomain> selectSessionDetails(UsersInfoDomain uid){
			//DBTransaction1 dbt = new DBTransaction1();
		//	DBTransaction1 dbt1 = new DBTransaction1();
			 ArrayList<SessionDomain> arl=new  ArrayList<SessionDomain>();
			try {
				connection = DBTransaction.connect();
				String quary = "SELECT session_name,session_start_time,session_id FROM session_details where recipient_type='"+uid.getUserId()+"' limit 5";
				//String quary = "SELECT session_name,session_start_time,session_id FROM session_details where organization_id='"+uid.getOrgId()+"' and faculty_name='"+uid.getUserId()+"'";
				//System.out.println(quary);
				prepareStatement = connection.prepareStatement(quary);
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next())
				{
					SessionDomain domain=new SessionDomain();
					domain.setSessionId(resultSet.getString("session_id"));
					domain.setSessionName(resultSet.getString("session_name"));
					domain.setSessionStartTime(resultSet.getString("session_start_time"));
					
					////System.out.println("assignment name::"+resultSet.getString("assignment_name"));
					//domain.set
					//dcd.setCourseImageLocation(rs.getString("uploaded_by"));
					arl.add(domain);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   connection.close();
					   prepareStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl;
		   
	   }
	 public ArrayList<FacilitatorKnowReportDomain> selectLibraryInfo(UsersInfoDomain uid){
			//DBTransaction1 dbt = new DBTransaction1();
		//	DBTransaction1 dbt1 = new DBTransaction1();
			 ArrayList<FacilitatorKnowReportDomain> arl=new  ArrayList<FacilitatorKnowReportDomain>();
			try {
				connection = DBTransaction.connect();
							
				String quary = "SELECT name_of_lib,lib_id,ksid,user_id,organization_id FROM knowledgestorelib_info where organization_id='"+uid.getOrgId()+"' and user_id='"+uid.getUserId()+"'";
				//System.out.println(quary);
				prepareStatement = connection.prepareStatement(quary);
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next())
				{
					FacilitatorKnowReportDomain domain=new FacilitatorKnowReportDomain();
					domain.setLibName(resultSet.getString("name_of_lib"));
					domain.setLibId(resultSet.getString("lib_id"));
					domain.setKsId(resultSet.getString("ksid"));
					domain.setUserId(resultSet.getString("user_id"));
					domain.setOrgId(resultSet.getString("organization_id"));
					
					arl.add(domain);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   connection.close();
					   prepareStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl;
		   
	   }
	/* public String  getKnowStoreID(String facultyId,String organizationId){
			//DBTransaction1 dbt = new DBTransaction1();
		//	DBTransaction1 dbt1 = new DBTransaction1();
			String knowStoreId="";
			try {
				connection = DBTransaction.connect();
							
				String quary = "SELECT ksid FROM knowledgestore_info where organization_id='"+organizationId+"' and user_id='"+facultyId+"'";
				//System.out.println(quary);
				prepareStatement = connection.prepareStatement(quary);
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next())
				{
					knowStoreId=resultSet.getString("ksid");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		   
		return knowStoreId;
		   
	   }*/
	 public ArrayList<FacilitatorKnowReportDomain>  getLibraryFiles(String libraryId){
			//DBTransaction1 dbt = new DBTransaction1();
		//	DBTransaction1 dbt1 = new DBTransaction1();
		 ArrayList<FacilitatorKnowReportDomain> arl=new  ArrayList<FacilitatorKnowReportDomain>();
			try {
				connection = DBTransaction.connect();
							
				String quary = "SELECT path_of_file FROM knowledgeassets_info where lib_id='"+libraryId+"'";
				//System.out.println(quary);
				prepareStatement = connection.prepareStatement(quary);
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next())
				{
					FacilitatorKnowReportDomain domain=new FacilitatorKnowReportDomain();
					domain.setFilePath(resultSet.getString("path_of_file"));
					arl.add(domain);
					//knowStoreId=resultSet.getString("ksid");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   connection.close();
					   prepareStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl;
		   
	   }

}
