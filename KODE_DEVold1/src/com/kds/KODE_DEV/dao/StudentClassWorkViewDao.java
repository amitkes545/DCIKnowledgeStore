package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacultyShareFileDomain;
public class StudentClassWorkViewDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;	
	ArrayList<FacultyShareFileDomain>  arrayListAssetDomain= new ArrayList<FacultyShareFileDomain>();
	public  ArrayList<FacultyShareFileDomain> fetchAllFilesInfo(FacultyShareFileDomain assetsDomain)
	{
		
		try{
			
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select * from classroom_file_info where uploaded_by=(select faculty_name from session_details where session_id='"+assetsDomain.getSessionID()+"') and participants @> ARRAY['"+assetsDomain.getUploadedBy()+"']::varchar[] ; ";
			preparedStatement = connection.prepareStatement(sql);

			System.out.println("the query is:" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				FacultyShareFileDomain ffAssetDomain = new FacultyShareFileDomain();
				ffAssetDomain.setFileName(resultSet.getString("file_name"));
				ffAssetDomain.setAction(resultSet.getString("actions"));
				ffAssetDomain.setUploadDate(resultSet.getDate("upload_date"));
				ffAssetDomain.setUploadTime(resultSet.getTimestamp("upload_time"));
				ffAssetDomain.setFilePath(resultSet.getString("file_path"));
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
}