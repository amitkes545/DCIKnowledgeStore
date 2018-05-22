package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DepartmentsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class DisplaySemestersDao {
	 public ArrayList<DepartmentsDomain> selectSemesters(DisplayCoursesDomain dcd){
		  // DBTransaction dbt = new DBTransaction();
			 ArrayList<DepartmentsDomain> arl=new  ArrayList<DepartmentsDomain>();
			 Connection con = null;
				PreparedStatement pstmt = null;
			try {
				
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "select semester_info from semester_information where department_id='"+dcd.getCourseName()+"'";
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{		
					DepartmentsDomain dd123=new DepartmentsDomain();		
					dd123.setDepartmentsNmae(rs.getString("semester_info"));
				
					arl.add(dd123);
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
