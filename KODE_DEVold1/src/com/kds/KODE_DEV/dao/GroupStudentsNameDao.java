package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class GroupStudentsNameDao {
	public ArrayList<String> IndividualStudentDetails(DisplayCoursesDomain dcd){
		ResultSet resultset = null;
		PreparedStatement prepareStatemt = null;
		Connection con =null;
		//DBTransaction1 dbt = new DBTransaction1();
		 ArrayList<String> studentIds=new  ArrayList<String>();

		try {
			con = DBTransaction.connect();
			
			String quary = "select student_id from student_groups where group_name='"+dcd.getCourseName()+"' and created_by='"+dcd.getCourseDetails()+"' and organization_id='"+dcd.getCourseFee()+"'";
			//System.out.println(quary);
			prepareStatemt = con.prepareStatement(quary);
			resultset = prepareStatemt.executeQuery();
			while(resultset.next())
			{
				String[] s=resultset.getString("student_id").split("#");
				for(String s1:s)
				{
					//PreparedStatement pstmt1 = null;
					//ResultSet rs1 = null;			
					String quary1 = "select username,user_id from users_info where user_id='"+s1+"'";
					//System.out.println(quary1);
					prepareStatemt = con.prepareStatement(quary1);
					resultset = prepareStatemt.executeQuery();
					while(resultset.next())
					{
						studentIds.add(resultset.getString("user_id"));
						/*RetriveImagesDomain dcd1=new RetriveImagesDomain();
						dcd1.setCourseName(rs1.getString("username"));		
						dcd1.setCourseImageLocation(rs1.getString("user_id"));
						arl.add(dcd1);*/
					}
					
				}
			}
			
	}catch(Exception e){
		e.printStackTrace();
	}
		finally
		   {
			   try{
				   con.close();
				   prepareStatemt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return studentIds;
	}
}
