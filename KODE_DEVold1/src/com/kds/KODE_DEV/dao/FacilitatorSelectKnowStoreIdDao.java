package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CourseDomain;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;
import com.kds.KODE_DEV.domain.FacilitatorKnowPublishDomain;
import com.kds.KODE_DEV.domain.FacilitatorKnowReportDomain;

public class FacilitatorSelectKnowStoreIdDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;	
	
	FacilitatorKnowPublishDomain fDomain = new FacilitatorKnowPublishDomain();
	ArrayList<FacilitatorKnowReportDomain>  arrayListDomain= new ArrayList<FacilitatorKnowReportDomain>();
	ArrayList<FacilitatorKAssetsReportDomain>  arrayListAssetDomain= new ArrayList<FacilitatorKAssetsReportDomain>();
	
	ArrayList<String> arrayList = new ArrayList<String>();
	
	public ArrayList<String> fetchKsid(String userId)
	{
	
		try{
			
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select ksid from knowledgestore_info where organization_id ='"+userId+"'";
			preparedStatement = connection.prepareStatement(sql);

			System.out.println("the query is fetchKsid:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				//System.out.println(resultSet.getString("ksid"));
				fDomain.setKsId(resultSet.getString("ksid"));
				String ksid=resultSet.getString("ksid");
				arrayList.add(ksid);					 
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayList;
	}
	
	public String fetchDynamicKsid(String userId)
	{
		String ksID = "";
		try{
			
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select ksid from knowledgestore_info where organization_id ='"+userId+"'";
			preparedStatement = connection.prepareStatement(sql);

			System.out.println("the query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				//System.out.println(resultSet.getString("ksid"));
				fDomain.setKsId(resultSet.getString("ksid"));
				ksID=resultSet.getString("ksid");
							 
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return ksID;
	}
	public  ArrayList<String> fetchFKsid(String userId)
	{
		
		try{
			
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select ksid,departments from knowledgeassets_info where uploaded_by ='"+userId+"'limit 1 ";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				//System.out.println(resultSet.getString("ksid"));
				fDomain.setKsId(resultSet.getString("ksid"));
				String ksid=resultSet.getString("ksid");
				String departments=resultSet.getString("departments");
				arrayList.add(ksid);
				arrayList.add(departments);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayList;
	}	
	public  ArrayList<String> fetchLibId(String userId,String orgId)
	{
		
		
		
		try{
			
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select lib_id,name_of_lib from knowledgestorelib_info where user_id ='"+userId+"' and organization_id ='"+orgId+"'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				//System.out.println(resultSet.getString("lib_id"));
				fDomain.setKsId(resultSet.getString("lib_id"));
				String ksid=resultSet.getString("lib_id");
				String libName=resultSet.getString("name_of_lib");
				arrayList.add(ksid);
				arrayList.add(libName);
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayList;
	}	
	public  ArrayList<FacilitatorKnowReportDomain> fetchAllLibInfo(String userId, String orgId)
	{
		
		try{
			
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select * from knowledgestorelib_info where user_id='"+userId+"' and organization_id='"+orgId+"'";
			//preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				FacilitatorKnowReportDomain ffDomain = new FacilitatorKnowReportDomain();
				ffDomain.setLibId(resultSet.getString("lib_id"));
				ffDomain.setKsId(resultSet.getString("ksid"));
				ffDomain.setLibName(resultSet.getString("name_of_lib"));
				ffDomain.setLibSize(resultSet.getInt("size_of_lib"));
				arrayListDomain.add(ffDomain);
				
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListDomain;
	}
	/*
	//Select Library info
	
	public  ArrayList<FacilitatorKnowReportDomain> fetchStudentAllLibInfo(String userId, String orgId)
	{
		
		try{
			
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			//String sql = "select * from knowledgestorelib_info where organization_id='"+orgId+"'";
			//Temp. Query added by prity
			String sql = "select  distinct(KSL.lib_id),name_of_lib From knowledgestorelib_info KSL left join student_faculty_mapping SFM on (KSL.user_id=SFM.faculty_id and KSL.organization_id=SFM.organization_id ) "
					+ "left join knowledgeassets_info KAI on SFM.faculty_id=KAI.uploaded_by where SFM.organization_id='"+orgId+"' and SFM.student_id='"+userId+"'";

			//String sql="select * from knowledgestorelib_info KSL left join knowledgeassets_info KAI on KAI.uploaded_by=KSL.user_id where organization_id='"+orgId+"'";
		//	String sql="select * from knowledgestorelib_info KSL left join knowledgeassets_info KAI on KAI.uploaded_by=KSL.user_id where organization_id='"+orgId+"'";
			
			System.out.println("orgId DAO==>"+orgId);
			CourseDomain coursedomain=new CourseDomain();
			String courseId=coursedomain.getCourse_id();
			String subjectId=coursedomain.getSubject_id();
			
			System.out.println(courseId+"=========<<<<<<"+subjectId);
			
			
		//	String sql="select distinct KSL.lib_id,KSL.name_of_lib  from knowledgestorelib_info KSL left join knowledgeassets_info KAI on KAI.uploaded_by=KSL.user_id and KAI.lib_id=KSL.lib_id where organization_id='"+orgId+"'";
			
			//String sql="select distinct(a.lib_id), b.name_of_lib from knowledgeassets_info a, knowledgestorelib_info b where a.course_id = '"+courseId+"' and a.subject = '"+subjectId+"' and b.lib_id = a.lib_id";
			
			String sql="select distinct(a.lib_id), b.name_of_lib from knowledgeassets_info a, knowledgestorelib_info b where a.course_id = 'course216' and a.subject = 'subid439' and b.lib_id = a.lib_id";
			
			
			preparedStatement = connection.prepareStatement(sql);

			System.out.println("the query in fetchStudentAllLibInfo:" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				FacilitatorKnowReportDomain ffDomain = new FacilitatorKnowReportDomain();
				ffDomain.setLibId(resultSet.getString("lib_id"));
				//ffDomain.setKsId(resultSet.getString("ksid"));
				ffDomain.setLibName(resultSet.getString("name_of_lib"));
				//ffDomain.setLibSize(resultSet.getInt("size_of_lib"));
				//ffDomain.setFilePath(resultSet.getString("path_of_file"));
				arrayListDomain.add(ffDomain);
				
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListDomain;
	}*/
	public  ArrayList<FacilitatorKAssetsReportDomain> fetchAssetsInfo(FacilitatorKAssetsReportDomain assetsDomain)
	{
		
		try{
			
			//String subjectId=assetsDomain.getSubjectId();
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			System.out.println("Lib Id===>"+assetsDomain.getLibId());
			
		String sql = "select * from knowledgeassets_info KAI join knowledgestore_info KSI on KAI.ksid=KSI.ksid  where KSI.organization_id='"+assetsDomain.getOrgId()+"' and subject='"+assetsDomain.getSubjectId()+"'";
			
			
			System.out.println("heat data for reterive lib details");
			
			
		/*	String sql ="select distinct(a.lib_id), b.name_of_lib from knowledgeassets_info a, knowledgestorelib_info b where  a.course_id = '"+assetsDomain.getCourseId()+"' and a.subject = '"+assetsDomain.getSubjectId()+"' and b.lib_id = a.lib_id";*/
			
			
			preparedStatement = connection.prepareStatement(sql);

			System.out.println("the query is:................" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				FacilitatorKAssetsReportDomain ffAssetDomain = new FacilitatorKAssetsReportDomain();
				ffAssetDomain.setKsId(resultSet.getString("ksid"));
				ffAssetDomain.setDepartment(resultSet.getString("departments"));
				ffAssetDomain.setSubject(resultSet.getString("subject"));
				ffAssetDomain.setDescription(resultSet.getString("description"));
				ffAssetDomain.setFileName(resultSet.getString("file_name"));
				ffAssetDomain.setFileSize(resultSet.getInt("file_size"));
				ffAssetDomain.setUploadDate(resultSet.getDate("upload_date"));
				ffAssetDomain.setUploadTime(resultSet.getTimestamp("upload_time"));
				ffAssetDomain.setStatus(resultSet.getString("status"));
				ffAssetDomain.setFilePath(resultSet.getString("path_of_file"));
				arrayListAssetDomain.add(ffAssetDomain);
				
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListAssetDomain;
	}	
	public  ArrayList<FacilitatorKAssetsReportDomain> fetchAllStudentAssetsInfo(FacilitatorKAssetsReportDomain assetsDomain)
	{
		
		try{
			
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			//String sql = "select KAI.* from knowledgeassets_info KAI join knowledgestore_info KSI on KAI.ksid=KSI.ksid where KSI.organization_id='"+assetsDomain.getOrgId()+"' and KAI.status='Active' order by lib_id";
			//temp query by prity
			String sql = "select * from knowledgeassets_info KAI join knowledgestore_info KSI on KAI.ksid=KSI.ksid  where KSI.organization_id='"+assetsDomain.getOrgId()+"'";
			preparedStatement = connection.prepareStatement(sql);

			System.out.println("the query is:" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				FacilitatorKAssetsReportDomain ffAssetDomain = new FacilitatorKAssetsReportDomain();
				//ffAssetDomain.setKsId(resultSet.getString("ksid"));
				ffAssetDomain.setDepartment(resultSet.getString("departments"));
				ffAssetDomain.setSubject(resultSet.getString("subject"));
				ffAssetDomain.setDescription(resultSet.getString("description"));
				ffAssetDomain.setFileName(resultSet.getString("file_name"));
				ffAssetDomain.setFileSize(resultSet.getInt("file_size"));
				ffAssetDomain.setUploadDate(resultSet.getDate("upload_date"));
				ffAssetDomain.setUploadTime(resultSet.getTimestamp("upload_time"));
				ffAssetDomain.setStatus(resultSet.getString("status"));
				ffAssetDomain.setFilePath(resultSet.getString("path_of_file"));
				arrayListAssetDomain.add(ffAssetDomain);
								
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListAssetDomain;
	}	
	public  ArrayList<FacilitatorKAssetsReportDomain> fetchAllAssetsInfo(FacilitatorKAssetsReportDomain assetsDomain)
	{
		System.out.println(" in fetchAllAssetsInfo");
		try{
			
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select * from knowledgeassets_info where uploaded_by='"+assetsDomain.getUserId()+"' order by lib_id";
			preparedStatement = connection.prepareStatement(sql);

			System.out.println("the query is fetchAllAssetsInfo:" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				FacilitatorKAssetsReportDomain ffAssetDomain = new FacilitatorKAssetsReportDomain();
				ffAssetDomain.setKsId(resultSet.getString("ksid"));
				ffAssetDomain.setDepartment(resultSet.getString("departments"));
				ffAssetDomain.setSubject(resultSet.getString("subject"));
				ffAssetDomain.setDescription(resultSet.getString("description"));
				ffAssetDomain.setFileName(resultSet.getString("file_name"));
				ffAssetDomain.setFileSize(resultSet.getInt("file_size"));
				ffAssetDomain.setUploadDate(resultSet.getDate("upload_date"));
				ffAssetDomain.setUploadTime(resultSet.getTimestamp("upload_time"));
				ffAssetDomain.setStatus(resultSet.getString("status"));
				ffAssetDomain.setFilePath(resultSet.getString("path_of_file"));
				arrayListAssetDomain.add(ffAssetDomain);
								
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListAssetDomain;
	}	

	public  ArrayList<FacilitatorKnowReportDomain> viewLibInfo(String userId, String orgId,String subId,String courseId)
	{
		
		try{
			
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			//String sql = "select * from knowledgestorelib_info where user_id='"+userId+"' and organization_id='"+orgId+"'";
			

			String sql = " select distinct(a.lib_id), b.name_of_lib from knowledgeassets_info a,"
					+ " knowledgestorelib_info b where  "
					+ " a.uploaded_by = '"+userId+"' and  "
					+ " a.course_id = '"+courseId+"' and a.subject =  '"+subId+"'  and "
					+ " b.lib_id = a.lib_id";
			//preparedStatement = connection.prepareStatement(sql);

			System.out.println("the query in FacilitatorSelectKnowStoreIdDao:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				FacilitatorKnowReportDomain ffDomain = new FacilitatorKnowReportDomain();
				ffDomain.setLibId(resultSet.getString(1));
				//ffDomain.setKsId(resultSet.getString("ksid"));
				ffDomain.setLibName(resultSet.getString(2));
				//ffDomain.setLibSize(resultSet.getInt("size_of_lib"));
				arrayListDomain.add(ffDomain);
				
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListDomain;
	}
	public ArrayList<FacilitatorKAssetsReportDomain> viewItems(String libID,String courseID,String subjectID,String userid){
		try {
			connection = DBTransaction.connect();
			//PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			//System.out.println("query= select file_name,lib_id,file_size,path_of_file,status from knowledgeassets_info where lib_id='"+assetDomain1.getLibId()+"'");
			System.out.println("assetDomain1.getLibId()   " +libID);
			System.out.println("assetDomain1.getCourses()   " +courseID);
			System.out.println("assetDomain1.getSubject()   " +subjectID);
			System.out.println("assetDomain1.getUploadedBy()   " +userid);
			System.out.println("assetDomain1.getUserId()   " +userid);
			//	String query = "select file_name, lib_id, file_size, path_of_file, status, uploaded_by from knowledgeassets_info where lib_id='"+assetDomain1.getLibId()+"'";
			
			
			String query = "  select b.topicname, c.subtopicname, a.file_name, a.file_size, a.upload_date, a.upload_time, a.status from "
					+"  knowledgeassets_info a, topic_description b, sub_topic_description c where "
			+" a.uploaded_by = '"+userid+"' and a.course_id = '"+courseID+"' and a.subject = '"+subjectID+"' and a.lib_id = '"+libID+"' and "
			+" b.subject_id = a.subject and "
			+" b.topic_id = a.topic_id and "
			+" c.topic_id = b.topic_id and "
			+" c.sub_topic_id = a.subtopic_id ";
			
			preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				System.out.println("the query in viwe"+query);
				String topic,subtopic,filename=null;
				int filesize;
				java.sql.Date dt;
				java.sql.Timestamp ts;
				while(resultSet.next()){
				
					FacilitatorKAssetsReportDomain ffAssetDomain = new FacilitatorKAssetsReportDomain();
					topic = resultSet.getString(1);
					subtopic=resultSet.getString(2);
					filename=resultSet.getString(3);
					filesize=resultSet.getInt(4);
					dt = resultSet.getDate(5);
					ts = resultSet.getTimestamp(6);
					String status = resultSet.getString(7);
					System.out.println("topic "+topic);
					System.out.println("subtopic "+subtopic);
					System.out.println("filename"+filename);
					System.out.println("filesize"+filesize);
					System.out.println("dt "+dt);
					System.out.println("ts "+ts);
					System.out.println("Status "+status);
					if(status.equals("Active"))
					{
					ffAssetDomain.setTopic(topic);
					ffAssetDomain.setSubTopic(subtopic);
					ffAssetDomain.setFileName(filename);
					ffAssetDomain.setFileSize(filesize);
					ffAssetDomain.setUploadDate(dt);
					ffAssetDomain.setUploadTime(ts);
					ffAssetDomain.setStatus(status);
					arrayListAssetDomain.add(ffAssetDomain);
					}
					/*topic = resultSet.getString(1);
					subtopic=resultSet.getString(2);
					filename=resultSet.getString(3);
					System.out.println("topic "+topic);
					System.out.println("subtopic "+subtopic);
					System.out.println("filename"+filename);
					ffAssetDomain.setTopic(topic);
					ffAssetDomain.setSubTopic(subtopic);
					ffAssetDomain.setFileName(filename);
					ffAssetDomain.setFileSize(resultSet.getInt(4));
					ffAssetDomain.setUploadDate(resultSet.getDate(5));
					ffAssetDomain.setUploadTime(resultSet.getTimestamp(6));
					ffAssetDomain.setStatus(resultSet.getString(7));*/
					/*ffAssetDomain.setSubject(resultSet.getString("subject"));
					ffAssetDomain.setDescription(resultSet.getString("description"));
					
					
					
					ffAssetDomain.setFilePath(resultSet.getString("path_of_file"));*/
			//	arrayListAssetDomain.add(ffAssetDomain);
					//assetDomain.setSubTopicId(resultSet.getString(8));
					
					//existing code
				/*	assetDomain.setLibId(resultSet.getString("lib_id"));
					assetDomain.setUploadedBy(resultSet.getString("uploaded_by"));
					assetDomain.setFileName(resultSet.getString("file_name"));
					assetDomain.setFileSize(resultSet.getInt("file_size"));
					assetDomain.setFilePath(resultSet.getString("path_of_file"));
					assetDomain.setStatus(resultSet.getString("status"));*/
					////System.out.println("asset value"+assetDomain.getFileName());
				
					
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}			
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListAssetDomain;	
		}
	
		
	
	
}