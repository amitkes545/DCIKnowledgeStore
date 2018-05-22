package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;

public class FacilitatorManageCourseDao {
	Connection con = null;
	
	PreparedStatement preparedStatement = null;

    ArrayList<String> arrayList = new ArrayList<String>();	
    
	public ArrayList<String> fetchValue(String userId,String orgId) {     // Fetch the value for Library
		
		FacilitatorManageKnowStoreDomain rDomain1=new FacilitatorManageKnowStoreDomain();

		try {
			con = DBTransaction.connect();
			
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			//String sql="select lib_id,name_of_lib from knowledgestorelib_info where user_id='"+userId+"' and organization_id='"+orgId+"'";
			String sql=" select distinct(a.course_id), c.department_name from knowledgeassets_info a, mst_course_catalogue b, transaction_department_info c where " 
			+" a.uploaded_by = '"+userId+"' and a.status = 'Inactive' and "
			+"  b.organization_id = '"+orgId+"' and b.deptid_by_ts = a.course_id and "
			+" c.department_id = b.dept_id ";
			
			preparedStatement = con.prepareStatement(sql);

			System.out.println("the query is FacilitatorManageCourseDao:" + sql);
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println(" the query in FacilitatorManageLibDao="+sql);
			while(resultSet.next()) 
			{
				String courseid=resultSet.getString(1);
				String coursename=resultSet.getString(2);
				rDomain1.setCourseId(courseid);
				rDomain1.setDepartment_name(coursename);
				
				arrayList.add(courseid);
				arrayList.add(coursename);
				
			}
					}catch(Exception e){
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayList;
	
	}
	
public ArrayList<String> viewValue(String userId,String orgId) {     // Fetch the value for Library
		
		FacilitatorManageKnowStoreDomain rDomain1=new FacilitatorManageKnowStoreDomain();

		try {
			con = DBTransaction.connect();
			
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			//String sql="select lib_id,name_of_lib from knowledgestorelib_info where user_id='"+userId+"' and organization_id='"+orgId+"'";
			/*String sql=" select distinct(a.course_id), c.department_name from knowledgeassets_info a, mst_course_catalogue b, transaction_department_info c where " 
			+" a.uploaded_by = '"+userId+"' and a.status = 'Inactive' and "
			+"  b.organization_id = '"+orgId+"' and b.deptid_by_ts = a.course_id and "
			+" c.department_id = b.dept_id ";*/
			String sql=" select distinct(a.course_id), c.department_name from knowledgeassets_info a, mst_course_catalogue b, transaction_department_info c where "
					+" a.uploaded_by = '"+userId+"' and "
			+" b.organization_id = '"+orgId+"' and b.deptid_by_ts = a.course_id and "
			+" c.department_id = b.dept_id ";
			preparedStatement = con.prepareStatement(sql);

			System.out.println("the query is FacilitatorManageCourseDao:" + sql);
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println(" the query in FacilitatorManageLibDao="+sql);
			while(resultSet.next()) 
			{
				String courseid=resultSet.getString(1);
				String coursename=resultSet.getString(2);
				rDomain1.setCourseId(courseid);
				rDomain1.setDepartment_name(coursename);
				
				arrayList.add(courseid);
				arrayList.add(coursename);
				
			}
					}catch(Exception e){
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayList;
	
	}
//*******************************************************************************************************************************************************************//	
		
}