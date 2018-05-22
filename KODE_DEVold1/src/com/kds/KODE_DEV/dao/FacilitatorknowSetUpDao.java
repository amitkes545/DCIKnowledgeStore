package com.kds.KODE_DEV.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;
import com.kds.KODE_DEV.services.FacilitatorKAssetsKnowSetupServiceFTP;

public class FacilitatorknowSetUpDao {

	@SuppressWarnings("unused")
Connection con = null;
	
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	static final Logger LOGGER = Logger.getLogger(FacilitatorknowSetUpDao.class);
	public String insertAllInformation(FacilitatorKAssetsReportDomain ksfDomain) {
	
		String status = null;

		try {
			
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date date = new java.sql.Date(utilDate.getTime());
				//System.out.println("date :"+date);
				Calendar calendar = Calendar.getInstance();
				java.util.Date now = calendar.getTime();
				java.sql.Time currentTime = new java.sql.Time(now.getTime());
				//System.out.println("time:"+currentTime);

			con = DBTransaction.connect();
			//String sql="INSERT INTO knowledgeassets_info(ksid, departments, subject, description, uploaded_by, file_name, file_size, upload_date, upload_time, path_of_file, status,recipient_type,lib_id) VALUES (?, ?, ?, ?, ?, ?, ?, (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta')::date, (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta')::time, ?, ?, ?,?)";
			String sql="INSERT INTO knowledgeassets_info(ksid, departments, subject, description, uploaded_by, file_name,file_size, "
					+ "upload_date, upload_time, path_of_file, status, recipient_type,lib_id,course_id, topic_id, "
					+ "subtopic_id)  VALUES (?, ?, ?, ?, ?, ?, ?, "
					+ "(CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta')::date, (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta')::time, "
					+ "?, ?, ?,?, ?, ?, ?)";
		    
			pstmt = con.prepareStatement(sql);
			System.out.println("Query  " +sql);
			pstmt.setString(1, ksfDomain.getKsId());
			System.out.println("ksid  "  +ksfDomain.getKsId());
			
			pstmt.setString(2,ksfDomain.getCourses());
			System.out.println("course  "  +ksfDomain.getCourses());
			
			pstmt.setString(3, ksfDomain.getSubject());
			System.out.println("subject  "  +ksfDomain.getSubject());
			
			pstmt.setString(4, ksfDomain.getDescription());
			System.out.println("Description  "   +ksfDomain.getDescription());
			
			pstmt.setString(5, ksfDomain.getUploadedBy());
			System.out.println("UploadedBy  "   +ksfDomain.getUploadedBy());
			
			pstmt.setString(6, ksfDomain.getFileName());
			System.out.println("FileName  "   +ksfDomain.getFileName());
			
			pstmt.setInt(7, ksfDomain.getFileSize());
			System.out.println("FileSize  "   +ksfDomain.getFileSize());
			
			pstmt.setString(8, ksfDomain.getFilePath());
			System.out.println("FilePath  "   +ksfDomain.getFilePath());
			
			pstmt.setString(9, "Inactive");
			//System.out.println(ksfDomain.getKsId());
			
			pstmt.setString(10, ksfDomain.getRecipientType());
			System.out.println("RecipientType  "   +ksfDomain.getRecipientType());
			
			pstmt.setString(11,ksfDomain.getLibId());
			System.out.println("LibId  "   +ksfDomain.getLibId());
			
			pstmt.setString(12,ksfDomain.getCourses());
			System.out.println("Courses  "   +ksfDomain.getCourses());
			
			pstmt.setString(13,ksfDomain.getDepartments());
			System.out.println("Departments  "   +ksfDomain.getDepartments());
			
			pstmt.setString(14,ksfDomain.getSubjects());
			System.out.println("Subjects  "   +ksfDomain.getSubjects());
			
			//HttpServletRequest request;

			System.out.println("the query is:" + pstmt);
			int n = pstmt.executeUpdate();
			System.out.println("query is inserted successfully"+n);
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
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
	public long CheckLibSize(String orgid,String libid) {

		long avail_size = 0;

		try {
			
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date date = new java.sql.Date(utilDate.getTime());
				//System.out.println("date :"+date);
				Calendar calendar = Calendar.getInstance();
				java.util.Date now = calendar.getTime();
				java.sql.Time currentTime = new java.sql.Time(now.getTime());
				//System.out.println("time:"+currentTime);

			con = DBTransaction.connect();
			String sql="select Organization_id,KSLI.lib_id,size_of_lib, case when space_used is null then 0 else space_used end as space_used from knowledgestorelib_info KSLI left join "
					+ "(select sum(file_size) space_used,lib_id from knowledgeassets_info group by lib_id) KAI ON KSLI.lib_id = KAI.lib_id "
					+ "where organization_id='"+orgid+"' AND KSLI.lib_id='"+libid+"' ";
			
			pstmt = con.prepareStatement(sql);

			System.out.println("the query is:" + pstmt);
			LOGGER.info("query in dao to find space of lib="+pstmt);
			resultSet = pstmt.executeQuery();
			long file_size=0,size_of_lib=0;
			while (resultSet.next()) {
				file_size = resultSet.getInt("space_used");
				size_of_lib = resultSet.getInt("size_of_lib");
			}
			LOGGER.info("space_used in dao="+file_size);
			LOGGER.info("size_of_lib in dao="+size_of_lib);
			LOGGER.info("size_of_lib in byte in dao="+size_of_lib*1024*1024*1024);
			System.out.println("size_of_lib in byte in dao="+size_of_lib*1024*1024*1024);
			avail_size=(size_of_lib*1024*1024*1024)-file_size;
			System.out.println("avail_size="+avail_size);
			LOGGER.info("avail_size for lib in dao="+avail_size);
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
		return avail_size;
	}
 }