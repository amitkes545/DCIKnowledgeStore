package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class FacultySessionDetailsDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	 public ArrayList<AssessmentsDetailsDomain> knowledgeAssestsDetails(UsersInfoDomain uid){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<AssessmentsDetailsDomain> arl=new  ArrayList<AssessmentsDetailsDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "select * from session_details where session_id='"+uid.getUserId()+"' and organization_id='"+uid.getOrgId()+"' and faculty_name='"+uid.getCreatedBy()+"'";
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					AssessmentsDetailsDomain dcd=new AssessmentsDetailsDomain();
					dcd.setAssessmentId(rs.getString("session_id"));
					dcd.setAssessmentName(rs.getString("session_name"));
					dcd.setAssessmentDescription(rs.getString("category"));
					dcd.setUploadedTime(rs.getString("duration"));
					dcd.setUploadedDate(rs.getString("session_start_time"));
					dcd.setSessionEndTime(rs.getString("session_end_time"));
					dcd.setUploadedBy(rs.getString("cost_of_session"));
					dcd.setRecipientType(rs.getString("recipient_type"));
					dcd.setFilePath(rs.getString("file_path"));
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

