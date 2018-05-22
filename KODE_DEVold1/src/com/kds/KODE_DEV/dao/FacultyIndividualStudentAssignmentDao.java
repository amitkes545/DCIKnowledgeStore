package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;

public class FacultyIndividualStudentAssignmentDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	
	   public ArrayList<RetriveImagesDomain> selectCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
		//	DBTransaction1 dbt1 = new DBTransaction1();
			 ArrayList<RetriveImagesDomain> arl=new  ArrayList<RetriveImagesDomain>();
			try {
				con = DBTransaction.connect();
				
				//PreparedStatement pstmt = null;
				ResultSet rs = null;			
				String quary = "SELECT assignment_name,uploaded_by FROM assignment_info where organization_id='"+ri.getCourseFee()+"' and uploaded_by='"+ri.getCourseDetails()+"' and (recipient_type like '%"+ri.getCourseName()+"%' or recipient_type='All')";
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					RetriveImagesDomain dcd=new RetriveImagesDomain();
					dcd.setCourseName(rs.getString("assignment_name"));		
					//dcd.setCourseImageLocation(rs.getString("uploaded_by"));
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
