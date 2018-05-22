package com.kds.KODE_DEV.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.ClassWorkFileDomain;
//import oracle.sql.ArrayDescriptor;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;

public class StudentClassWorkFileDao{

	@SuppressWarnings("unused")
Connection con = null;
	java.sql.Array sqlArray = null;
	PreparedStatement pstmt = null;
	public String insertClassWorkFileInformation(ClassWorkFileDomain ksfDomain) {

		String status = null;

		try {
			
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date date = new java.sql.Date(utilDate.getTime());
				System.out.println("date :"+date);
				Calendar calendar = Calendar.getInstance();
				java.util.Date now = calendar.getTime();
				java.sql.Time currentTime = new java.sql.Time(now.getTime());
				System.out.println("time:"+ksfDomain.getFileName());
				//ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("CHAR_ARRAY", conn);
				String filepath=ksfDomain.getFilePath();
				String actualpath[]=filepath.split("/");
				StringBuffer filenamebuffer = new StringBuffer();
				if(actualpath.length > 0){ 
					filenamebuffer.append(actualpath[0]);
		                for(int i=1; i < actualpath.length-1; i++){
		                	filenamebuffer.append("/").append(actualpath[i]);
		                }
		        }
				System.out.println("filenamebuffer:"+filenamebuffer);
				System.out.println("getFilePath:"+ksfDomain.getFilePath());
			con = DBTransaction.connect();
			String sql="INSERT INTO classwork_file_info(uploaded_by,upload_date,session_id,upload_time,file_name,file_path) VALUES ('"+ksfDomain.getUploadedBy()+"',(CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta')::date,'"+ksfDomain.getSessionID()+"', (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta')::time, '"+ksfDomain.getFileName()+"','"+ksfDomain.getFilePath()+"')";
			
			pstmt = con.prepareStatement(sql);
			/*pstmt.setString(1, ksfDomain.getUploadedBy());
			pstmt.setString(2, ksfDomain.getSessionID());
			pstmt.setString(3, ksfDomain.getFileName());
			pstmt.setString(4, ksfDomain.getAction());
			pstmt.setString(5, "hello");
			pstmt.setString(6, ksfDomain.getFilePath());*/
			
		//	HttpServletRequest request;

			System.out.println("the query is:" + pstmt);
			int n = pstmt.executeUpdate();
			System.out.println("query is inserted successfully"+pstmt);
			if (n != 0)
				status = "success";
			else
				status = "failure";
		} catch (Exception e) {
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
		return status;
	}
	ArrayList<ClassWorkFileDomain>  arrayListAssetDomain= new ArrayList<ClassWorkFileDomain>();
	public  ArrayList<ClassWorkFileDomain> fetchStudentClassWorkFilesInfo(ClassWorkFileDomain assetsDomain)
	{
		System.out.println("	fetchStudentClassWorkFilesInfo");
		try{
			
			con = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select * from classwork_file_info where session_id='"+assetsDomain.getSessionID()+"'";
			pstmt = con.prepareStatement(sql);

			System.out.println("the query is fetchStudentClassWorkFilesInfo :" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				ClassWorkFileDomain ffAssetDomain = new ClassWorkFileDomain();
				ffAssetDomain.setFileName(resultSet.getString("file_name"));
				ffAssetDomain.setUploadDate(resultSet.getDate("upload_date"));
				ffAssetDomain.setUploadedBy(resultSet.getString("uploaded_by"));
				ffAssetDomain.setUploadTime(resultSet.getTimestamp("upload_time"));
				ffAssetDomain.setFilePath(resultSet.getString("file_path"));
				arrayListAssetDomain.add(ffAssetDomain);
								
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		   {
			   try{
				   con.close();
				   pstmt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListAssetDomain;
	}	
 }