package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class OpenCoursesListDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	 public ArrayList<DisplayCoursesDomain> selectOpenCourses(){
			//DBTransaction dbt = new DBTransaction();
			 ArrayList<DisplayCoursesDomain> arl=new  ArrayList<DisplayCoursesDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				//ResultSet rs = null;
				String quary = "select * from OpenSourceCourses";
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					DisplayCoursesDomain dcd=new DisplayCoursesDomain();
					dcd.setCourseName(rs.getString("opensoursecoursename"));
					dcd.setImagePath(rs.getString("Imagelocation"));
					//System.out.println(dcd.getCourseName());
					arl.add(dcd);
				}
				}catch(Exception e){
				e.printStackTrace();
			}
		   
		return arl;
		   
	   }
}