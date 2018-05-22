package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;

public class FacilitatorGetSessionBackupDao{
	Connection con = null;
	PreparedStatement pstmt = null;
	   public ArrayList<RetriveImagesDomain> selectCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<RetriveImagesDomain> arl=new  ArrayList<RetriveImagesDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "select session_id,session_name, file_path from session_details where faculty_name='"+ri.getCourseName()+"' and organization_id='"+ri.getCourseDetails()+"'";
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					RetriveImagesDomain dcd=new RetriveImagesDomain();
					dcd.setCourseName(rs.getString("session_id"));
					dcd.setCourseImageSize(rs.getString("session_name"));
					//dcd.setImage(rs.getBytes("course_image"));
					//dcd.setCourseImage(rs.getBinaryStream("course_image"));
					//dcd.setCourseImageSize((int)rs.getString("course_image").length());
					dcd.setCourseImageLocation(rs.getString("file_path"));
					////System.out.println(dcd.getCourseName());
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
