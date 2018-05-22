package com.kds.KODE_DEV.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;
import com.kds.KODE_DEV.domain.FacultyShareFileDomain;
//import oracle.sql.ArrayDescriptor;

public class FacultyShareFileDao{

	@SuppressWarnings("unused")
Connection con = null;
	java.sql.Array sqlArray = null;
	PreparedStatement pstmt = null;
	public String insertShareFileInformation(FacultyShareFileDomain ksfDomain) {

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
				//Object[] filepath=ksfDomain.getFilePath();
				//String filenamebuffer=ksfDomain.getFilePath();
				/*String actualpath[]=((String) filepath[0]).split("/");
				StringBuffer filenamebuffer = new StringBuffer();
				if(actualpath.length > 0){ 
					filenamebuffer.append(actualpath[0]);
		                for(int i=1; i < actualpath.length-1; i++){
		                	filenamebuffer.append("/").append(actualpath[i]);
		                }
		        }
				System.out.println("filenamebuffer:"+filenamebuffer);*/
				System.out.println("getFileName:"+ksfDomain.getFileName());
				System.out.println("getFilePath:"+ksfDomain.getFilePath());
				System.out.println("getFileName:"+ksfDomain.getParticipants());
				
			con = DBTransaction.connect();
			String sql="INSERT INTO classroom_file_info(uploaded_by,upload_date,session_id,upload_time,file_name,actions,participants,file_path) VALUES ('"+ksfDomain.getUploadedBy()+"',(CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta')::date,'"+ksfDomain.getSessionID()+"', (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta')::time, '"+ksfDomain.getFileName()+"', '"+ksfDomain.getAction()+"', '"+ksfDomain.getParticipants()+"','"+ksfDomain.getFilePath()+"')";
			
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
 }