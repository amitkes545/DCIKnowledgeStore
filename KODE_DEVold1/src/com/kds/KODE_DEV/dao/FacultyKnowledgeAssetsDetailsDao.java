package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class FacultyKnowledgeAssetsDetailsDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	 public ArrayList<AssessmentsDetailsDomain> knowledgeAssestsDetails(UsersInfoDomain uid){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<AssessmentsDetailsDomain> arl=new  ArrayList<AssessmentsDetailsDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "select * from knowledgeassets_info where subject='"+uid.getUserId()+"'";
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					AssessmentsDetailsDomain dcd=new AssessmentsDetailsDomain();
					dcd.setAssessmentName(rs.getString("departments"));
					dcd.setAssessmentDescription(rs.getString("subject"));
					dcd.setUploadedBy(rs.getString("description"));
					dcd.setUploadedDate(rs.getString("file_name"));
					dcd.setUploadedTime(rs.getString("status"));
					dcd.setFilePath(rs.getString("path_of_file"));
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

