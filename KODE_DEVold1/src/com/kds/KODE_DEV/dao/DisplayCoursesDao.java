package com.kds.KODE_DEV.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;
public class DisplayCoursesDao {
   public ArrayList<RetriveImagesDomain> selectCourses(){
		//DBTransaction dbt = new DBTransaction();
		 ArrayList<RetriveImagesDomain> arl=new  ArrayList<RetriveImagesDomain>();
		 Connection con = null;
			PreparedStatement pstmt = null;
		try {
			con = DBTransaction.connect();
			//PreparedStatement pstmt = null;
			ResultSet rs = null;
			String quary = "SELECT distinct course_id,course_image,length(course_image),course_image_location FROM course_information;";
			pstmt = con.prepareStatement(quary);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				RetriveImagesDomain dcd=new RetriveImagesDomain();
				dcd.setCourseName(rs.getString("course_id"));
				//dcd.setImage(rs.getBytes("course_image"));
				//dcd.setCourseImage(rs.getBinaryStream("course_image"));
				//dcd.setCourseImageSize((int)rs.getString("course_image").length());
				dcd.setCourseImageLocation(rs.getString("course_image_location"));
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
