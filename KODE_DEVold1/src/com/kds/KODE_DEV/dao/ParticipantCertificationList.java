package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class ParticipantCertificationList {
	Connection con = null;
	PreparedStatement pstmt = null;
	 public ArrayList<DisplayCoursesDomain> selectCertifications(String s1){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<DisplayCoursesDomain> arl=new  ArrayList<DisplayCoursesDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "SELECT assessment_id, marks, status, remarks FROM assessment_student_info where uploaded_by='"+s1+"'";
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					DisplayCoursesDomain dc=new DisplayCoursesDomain();
					dc.setCourseName(rs.getString("assessment_id"));
					dc.setImagePath(rs.getString("marks"));
					dc.setCourseFee(rs.getString("status"));
					dc.setCourseDetails(rs.getString("remarks"));
				arl.add(dc);
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
	 
		 public ArrayList<DisplayCoursesDomain> selectAssignCertifications(String s1){
				//DBTransaction1 dbt = new DBTransaction1();
				 ArrayList<DisplayCoursesDomain> arl=new  ArrayList<DisplayCoursesDomain>();
				try {
					con = DBTransaction.connect();
					//PreparedStatement pstmt = null;
					ResultSet rs = null;
					String quary = "SELECT assignment_id, marks, status, remarks FROM assignment_student_info where uploaded_by='"+s1+"'";
					pstmt = con.prepareStatement(quary);
					rs = pstmt.executeQuery();
					while(rs.next()){
						DisplayCoursesDomain dc=new DisplayCoursesDomain();
						dc.setCourseName(rs.getString("assignment_id"));
						dc.setImagePath(rs.getString("marks"));
						dc.setCourseFee(rs.getString("status"));
						dc.setCourseDetails(rs.getString("remarks"));
					arl.add(dc);
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
