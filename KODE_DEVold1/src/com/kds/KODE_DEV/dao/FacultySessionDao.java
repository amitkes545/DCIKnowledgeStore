package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;

public class FacultySessionDao {
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
				String quary = "SELECT session_id,session_name FROM session_details where organization_id='"+ri.getCourseDetails()+"' and faculty_name='"+ri.getCourseName()+"'";
				//System.out.println(quary);
				pstmt1 = con.prepareStatement(quary);
				rs1 = pstmt1.executeQuery();
				while(rs1.next())
				{	
					RetriveImagesDomain rid=new RetriveImagesDomain();
					rid.setCourseName(rs1.getString("session_name"));
					rid.setCourseImageLocation(rs1.getString("session_id"));
					//System.out.println(rid.getCourseImageLocation());
					arl.add(rid);
					
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
