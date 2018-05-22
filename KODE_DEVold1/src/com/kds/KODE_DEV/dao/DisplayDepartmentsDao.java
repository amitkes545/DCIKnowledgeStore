package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DepartmentsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class DisplayDepartmentsDao {
	   public ArrayList<DepartmentsDomain> selectDepartments(DisplayCoursesDomain dcd){
		   //DBTransaction dbt = new DBTransaction();
			 ArrayList<DepartmentsDomain> arl=new  ArrayList<DepartmentsDomain>();
			 Connection con = null;
				PreparedStatement pstmt = null;
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "select distinct departments,department_image_location from course_information where course_id='"+dcd.getCourseName()+"'";
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					DepartmentsDomain dd=new DepartmentsDomain();
					dd.setDepartmentsNmae(rs.getString("departments"));
					dd.setImageLocation(rs.getString("department_image_location"));
					//System.out.println(dd.getDepartmentsNmae());
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
