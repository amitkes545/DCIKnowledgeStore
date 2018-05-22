package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CourseDomain;


public class CourseDao  {
	//DBTransaction db= new DBTransaction();
	Connection connection= null;	
	
	ResultSet resulSet= null;
	PreparedStatement prepareStatement=null;
	
	ArrayList<CourseDomain> al= new ArrayList<CourseDomain>();
	
	public ArrayList<CourseDomain> getCourseId(String facultyid){
		
	try{
		
		connection=DBTransaction.connect();
		//String sql= "select subject_id from faculty_subject_mapping where faculty_id='"+facultyid+"'";
		//Qurry added by Prity on 11 March' 16
		//Because in subject_id, value is showing like Sub1,Sub2
		/*String sql= "select distinct discipline_master.discipline from faculty_subject_mapping inner join discipline_master on discipline_master.discipline_id=faculty_subject_mapping.discipline where faculty_id='"+facultyid+"'";*/
		String sql="select * from course_description where customer_id = '"+facultyid+"'";
		prepareStatement=connection.prepareStatement(sql);
		System.out.println("prepareStatement "+prepareStatement);
		resulSet=prepareStatement.executeQuery();
		System.out.println("sql for getCourseId="+sql);
		while(resulSet.next())
		{
			CourseDomain cDomain= new CourseDomain();
		 cDomain.setCourse_id(resulSet.getString("course_id_defined_by_teaching_source"));
		 cDomain.setCourse_name(resulSet.getString("course_name"));
		 al.add(cDomain);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}		
	finally
	   {
		   try{
		   connection.close();
		   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return al;	
	}	
}
