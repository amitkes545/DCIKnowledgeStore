package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;

public class FacilitatorAssetsKnowDao { 
	
	Connection con=null;
	PreparedStatement pstmt = null;
	ArrayList<FacilitatorKAssetsReportDomain> assetList = new ArrayList<FacilitatorKAssetsReportDomain>();

	//public ArrayList<FacilitatorKAssetsReportDomain> getItems(FacilitatorKAssetsReportDomain assetDomain1){
	public ArrayList<FacilitatorKAssetsReportDomain> getItems(String libID,String courseID,String subjectID,String userid){
		try {
			con = DBTransaction.connect();
			//PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			//System.out.println("query= select file_name,lib_id,file_size,path_of_file,status from knowledgeassets_info where lib_id='"+assetDomain1.getLibId()+"'");
			System.out.println("assetDomain1.getLibId()   " +libID);
			System.out.println("assetDomain1.getCourses()   " +courseID);
			System.out.println("assetDomain1.getSubject()   " +subjectID);
			System.out.println("assetDomain1.getUploadedBy()   " +userid);
			System.out.println("assetDomain1.getUserId()   " +userid);
			//	String query = "select file_name, lib_id, file_size, path_of_file, status, uploaded_by from knowledgeassets_info where lib_id='"+assetDomain1.getLibId()+"'";
			String query = "  select b.topicname, c.subtopicname, a.file_name, a.file_size, a.path_of_file, a.status,b.topic_id ,a.subtopic_id from "
					+"  knowledgeassets_info a, topic_description b, sub_topic_description c where "
			+" a.uploaded_by = '"+userid+"'  and a.status = 'Inactive' and a.course_id = '"+courseID+"' and a.subject = '"+subjectID+"' and a.lib_id = '"+libID+"' and "
			+" b.subject_id = a.subject and "
			+" b.topic_id = a.topic_id and "
			+" c.topic_id = b.topic_id and "
			+" c.sub_topic_id = a.subtopic_id " ;
			
		
			
				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();
				System.out.println("the query in viwe"+query);
				while(resultSet.next()){
				
					FacilitatorKAssetsReportDomain assetDomain = new FacilitatorKAssetsReportDomain();
					assetDomain.setTopic(resultSet.getString(1));
					assetDomain.setSubTopic(resultSet.getString(2));
					assetDomain.setFileName(resultSet.getString(3));
					assetDomain.setFileSize(resultSet.getInt(4));
					assetDomain.setFilePath(resultSet.getString(5));
					assetDomain.setStatus(resultSet.getString(6));
					assetDomain.setTopicId(resultSet.getString(7));
					assetDomain.setSubTopicId(resultSet.getString(8));
					
					//existing code
				/*	assetDomain.setLibId(resultSet.getString("lib_id"));
					assetDomain.setUploadedBy(resultSet.getString("uploaded_by"));
					assetDomain.setFileName(resultSet.getString("file_name"));
					assetDomain.setFileSize(resultSet.getInt("file_size"));
					assetDomain.setFilePath(resultSet.getString("path_of_file"));
					assetDomain.setStatus(resultSet.getString("status"));*/
					////System.out.println("asset value"+assetDomain.getFileName());
					assetList.add(assetDomain);
					
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
			   pstmt.close();
			  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return assetList;	
		}
	
	
//**********************************************************************************************************************************************************//	
	
	ArrayList<FacilitatorKAssetsReportDomain> arl=new ArrayList<FacilitatorKAssetsReportDomain>();
	public ArrayList<FacilitatorKAssetsReportDomain> getAllItems(FacilitatorKAssetsReportDomain aDomain) {
		{
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet resultSet = null;
				//System.out.println("query= select file_name,lib_id,file_size,path_of_file,status from knowledgeassets_info where lib_id='"+assetDomain1.getLibId()+"'");
				//	String query = "select file_name,lib_id,file_size,path_of_file,status,uploaded_by from knowledgeassets_info where uploaded_by='"+aDomain.getUserId()+"' order by lib_id";// and organization_id='"+aDomain.getOrgId()+"'";

					/*String query = " select b.topicname, c.subtopicname, a.file_name, a.file_size, a.path_of_file, a.status from " 
+"  knowledgeassets_info a, topic_description b, sub_topic_description c where "
+"  a.uploaded_by = '"+aDomain.getUserId()+"' and a.status = 'InActive' and a.lib_id ='"+aDomain.getUserId()+"'  and "
+"  b.subject_id = a.subject and "
+"  b.topic_id = a.topic_id and "
+"  c.topic_id = b.topic_id and "
+"  c.sub_topic_id = a.subtopic_id";*/
					String query = "  select b.topicname, c.subtopicname, a.file_name, a.file_size, a.path_of_file, a.status from "
							+"  knowledgeassets_info a, topic_description b, sub_topic_description c where "
					+" a.uploaded_by = '"+aDomain.getUserId()+"'  and a.status = 'Inactive' and a.course_id = '"+aDomain.getCourses()+"' and a.subject = '"+aDomain.getSubject()+"' and a.lib_id = '"+aDomain.getLibId()+"' and "
					+" b.subject_id = a.subject and "
					+" b.topic_id = a.topic_id and "
					+" c.topic_id = b.topic_id and "
					+" c.sub_topic_id = a.subtopic_id " ;


					pstmt = con.prepareStatement(query);
					resultSet = pstmt.executeQuery();
					//System.out.println("the query in viwe"+pstmt);
					while(resultSet.next()){
					
						FacilitatorKAssetsReportDomain assetDomain = new FacilitatorKAssetsReportDomain();
						/*assetDomain.setLibId(resultSet.getString("lib_id"));
						assetDomain.setUploadedBy(resultSet.getString("uploaded_by"));
						assetDomain.setFileName(resultSet.getString("file_name"));
						assetDomain.setFileSize(resultSet.getInt("file_size"));
						assetDomain.setFilePath(resultSet.getString("path_of_file"));
						assetDomain.setStatus(resultSet.getString("status"));*/
						////System.out.println("asset value"+assetDomain.getFileName());
						assetDomain.setTopic(resultSet.getString(1));
						assetDomain.setSubTopic(resultSet.getString(2));
						assetDomain.setFileName(resultSet.getString(3));
						assetDomain.setFileSize(resultSet.getInt(4));
						assetDomain.setFilePath(resultSet.getString(5));
						assetDomain.setStatus(resultSet.getString(6));
					
						
						
						arl.add(assetDomain);
						
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
				   pstmt.close();
				  
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return arl;	
			}
		}	
	
	public ArrayList<FacilitatorKAssetsReportDomain> getcourseItems(FacilitatorKAssetsReportDomain assetDomain1){
		
		try {
			con = DBTransaction.connect();
			//PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			//System.out.println("query= select file_name,lib_id,file_size,path_of_file,status from knowledgeassets_info where lib_id='"+assetDomain1.getLibId()+"'");
			System.out.println("assetDomain1.getLibId()   " +assetDomain1.getLibId());
			System.out.println("assetDomain1.getCourses()   " +assetDomain1.getCourses());
			System.out.println("assetDomain1.getSubject()   " +assetDomain1.getSubject());
			System.out.println("assetDomain1.getUploadedBy()   " +assetDomain1.getUploadedBy());
			System.out.println("assetDomain1.getUserId()   " +assetDomain1.getUserId());
			//	String query = "select file_name, lib_id, file_size, path_of_file, status, uploaded_by from knowledgeassets_info where lib_id='"+assetDomain1.getLibId()+"'";
			String query=" select distinct(a.course_id), c.department_name from knowledgeassets_info a, mst_course_catalogue b, transaction_department_info c where " 
					+" a.uploaded_by = '"+assetDomain1.getUserId()+"' and a.status = 'Inactive' and "
					+" b.organization_id = '"+assetDomain1.getOrgId()+"' and b.deptid_by_ts = a.course_id and "
					+" c.department_id = b.dept_id ";	
											
				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();
				//System.out.println("the query in viwe"+query);
				while(resultSet.next()){
				
					FacilitatorKAssetsReportDomain assetDomain = new FacilitatorKAssetsReportDomain();
					assetDomain.setTopic(resultSet.getString(1));
					assetDomain.setSubTopic(resultSet.getString(2));
					assetDomain.setFileName(resultSet.getString(3));
					assetDomain.setFileSize(resultSet.getInt(4));
					assetDomain.setFilePath(resultSet.getString(5));
					assetDomain.setStatus(resultSet.getString(6));
					
					//existing code
				/*	assetDomain.setLibId(resultSet.getString("lib_id"));
					assetDomain.setUploadedBy(resultSet.getString("uploaded_by"));
					assetDomain.setFileName(resultSet.getString("file_name"));
					assetDomain.setFileSize(resultSet.getInt("file_size"));
					assetDomain.setFilePath(resultSet.getString("path_of_file"));
					assetDomain.setStatus(resultSet.getString("status"));*/
					////System.out.println("asset value"+assetDomain.getFileName());
					assetList.add(assetDomain);
					
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
			   pstmt.close();
			  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return assetList;	
		}
}
		// closing fetchUsers()
	// closing class body


//**********************************************************************************************************************************************************//
