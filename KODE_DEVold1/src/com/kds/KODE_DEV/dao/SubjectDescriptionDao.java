package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.SubjectDescriptionDomain;

public class SubjectDescriptionDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	 public ArrayList<SubjectDescriptionDomain> subjectDescription(DisplayCoursesDomain dcd){
		  // DBTransaction dbt = new DBTransaction();
			 ArrayList<SubjectDescriptionDomain> arl=new  ArrayList<SubjectDescriptionDomain>();
			try {
				//System.out.println("hi");
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "select * from subject_details where subject_name='"+dcd.getCourseName()+"'";
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					SubjectDescriptionDomain dd=new SubjectDescriptionDomain();
					dd.setSubjectName(rs.getString("subject_name"));
					dd.setSubjectDescription(rs.getString("description"));
					dd.setMoreDetails(rs.getString("more_details"));
					dd.setAuthorName(rs.getString("author_name"));
					dd.setImage(rs.getString("logo"));
					arl.add(dd);
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
