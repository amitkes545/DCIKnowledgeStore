package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DepartmentsDomain;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class DisplaySubjectListsDao {
	
	 public ArrayList<DepartmentsDomain> selectSubjects(DisplayCoursesDomain dcd){
		     //DBTransaction dbt = new DBTransaction();
			 ArrayList<DepartmentsDomain> arl=new  ArrayList<DepartmentsDomain>();
			 Connection con=null;
				PreparedStatement pstmt = null;
			try {
				DepartmentsDomain dd1=new DepartmentsDomain();
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "SELECT subject_code FROM semester_information where semester_info='"+dcd.getCourseName()+"'";

				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{					
					dd1.setDepartmentsNmae(rs.getString("subject_code"));
					//System.out.println(dd1.getDepartmentsNmae());
					arl.add(dd1);
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
