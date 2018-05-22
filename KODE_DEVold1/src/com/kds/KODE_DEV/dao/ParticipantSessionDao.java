package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;

public class ParticipantSessionDao {
	Connection con = null;
	PreparedStatement pstmt1 = null;
	   public ArrayList<RetriveImagesDomain> selectCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
		//	DBTransaction1 dbt1 = new DBTransaction1();
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
				PreparedStatement pstmt = null;
				ResultSet rs = null;			
				String quary = "SELECT session_id,session_name,faculty_name FROM session_details where organization_id='"+ri.getCourseDetails()+"' and faculty_name='"+rs1.getString("faculty_id")+"' and (recipient_type like '%"+ri.getCourseName()+"%' or recipient_type='All')";
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					RetriveImagesDomain dcd=new RetriveImagesDomain();
					dcd.setCourseImageSize(rs.getString("session_id"));
					dcd.setCourseName(rs.getString("session_name"));		
					dcd.setCourseImageLocation(rs.getString("faculty_name"));
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
					   pstmt1.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl;
		   
	   }
	}