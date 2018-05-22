package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;

public class ParticipantAssignmentsDao {
	Connection con = null;
	PreparedStatement pstmt1 = null,pstmt = null;
	ResultSet rs = null;
	   public ArrayList<RetriveImagesDomain> selectCourses(DisplayCoursesDomain ri){
			//DBTransaction dbt = new DBTransaction();
			 ArrayList<RetriveImagesDomain> arl=new  ArrayList<RetriveImagesDomain>();
			try {
				con = DBTransaction.connect();				
				//PreparedStatement pstmt1 = null;
				ResultSet rs1 = null;
				String quary1 = "select faculty_id from student_faculty_mapping where organization_id='"+ri.getCourseDetails()+"' and student_id='"+ri.getCourseName()+"'";
				//System.out.println(quary1);
				pstmt1 = con.prepareStatement(quary1);
				rs1 = pstmt1.executeQuery();
				while(rs1.next())
				{	
					//PreparedStatement pstmt = null;
					//ResultSet rs = null;
				
				String quary = "SELECT assignment_name,uploaded_by,assignment_id FROM assignment_info where organization_id='"+ri.getCourseDetails()+"' and uploaded_by='"+rs1.getString("faculty_id")+"' and (recipient_type like '%"+ri.getCourseName()+"%' or recipient_type='All')"; 
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					RetriveImagesDomain dcd=new RetriveImagesDomain();
					dcd.setCourseName(rs.getString("assignment_name"));
					dcd.setCourseImageSize(rs.getString("assignment_id"));
					dcd.setCourseImageLocation(rs.getString("uploaded_by"));
					arl.add(dcd);
				}
				}
				}catch(Exception e){
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   pstmt.close();
					   pstmt1.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl;
		   
	   }
	}