package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class VocationalCourseDetailsDao {
	Connection con = null;
	PreparedStatement pstmt = null;
  public ArrayList<DisplayCoursesDomain> selectVocationalCourseDetails(DisplayCoursesDomain dcd1){
	 // DBTransaction dbt = new DBTransaction();
		 ArrayList<DisplayCoursesDomain> arl=new  ArrayList<DisplayCoursesDomain>();
		try {
			con = DBTransaction.connect();
			//PreparedStatement pstmt = null;
			ResultSet rs = null;
			String quary = "SELECT * FROM vocationalcourses where vocationalcoursename='"+dcd1.getCourseName()+"'";

			pstmt = con.prepareStatement(quary);
			rs = pstmt.executeQuery();
			while(rs.next()){
				DisplayCoursesDomain dcd=new DisplayCoursesDomain();
				dcd.setCourseName(rs.getString("vocationalcoursename"));
				dcd.setImagePath(rs.getString("imagelocation"));
				dcd.setCourseDetails(rs.getString("course_details"));
				dcd.setCourseFee(rs.getString("course_fee"));
				//System.out.println(dcd.getCourseName());
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