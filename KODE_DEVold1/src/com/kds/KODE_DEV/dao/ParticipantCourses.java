package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DepartmentsDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class ParticipantCourses {
	Connection con = null;
	PreparedStatement pstmt = null;
	 public ArrayList<DepartmentsDomain> selectSemesters(UsersInfoDomain dcd){
		   //DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<DepartmentsDomain> arl=new  ArrayList<DepartmentsDomain>();
			try {
				
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "select semester_info,department_id from semester_information where exists( select departments_id from student_course_mapping where user_id='"+dcd.getUserId()+"' and organization_id='"+dcd.getOrgId()+"')";
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{		
					DepartmentsDomain dd123=new DepartmentsDomain();		
					dd123.setDepartmentsNmae(rs.getString("semester_info"));
					dd123.setImageLocation(rs.getString("department_id"));
				
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
