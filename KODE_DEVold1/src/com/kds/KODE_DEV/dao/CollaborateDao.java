package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;



public class CollaborateDao  {
	
	Connection connection=null;
	PreparedStatement prepareStatement=null;
	ResultSet resultSet=null;
	//DBTransaction DBT = new DBTransaction();
	ArrayList<AdminDomain> usersInfo=new ArrayList<AdminDomain>();
	/*public ArrayList<AdminDomain> getUserId(AdminDomain fdomain){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			 con = DBT.connect();
			String sql="select user_id from users_info where created_by='"+fdomain.getAdminId()+"'" + "and dept_details='"+fdomain.getDeptDetails()+"'";
		    ps=con.prepareStatement(sql);
			//System.out.println("quary is:"+sql);
			 rs=ps.executeQuery();
			while(rs.next()){
				AdminDomain sdomain=new AdminDomain();
				sdomain.setAdminId(rs.getString("user_id"));
				//System.out.println("user id:"+rs.getString("user_id"));
				ad.add(sdomain);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ad;
	}
	*/
	
	public String insertGroupId(ArrayList<AdminDomain> groupdomain,String groupname,AdminDomain Fdomain){
		
		String status="null";
		//System.out.println("domain values are:"+groupdomain);
		int n=0;
		String studentid="";
		Iterator<AdminDomain> it= groupdomain.iterator();
		while(it.hasNext()){
			AdminDomain ad=new AdminDomain();
			ad=(AdminDomain)it.next();
			
			studentid=studentid+ad.getAdminId()+"#";
		}
		//System.out.println(studentid);
		//System.out.println("department values are:"+groupname);
		try {
			connection=DBTransaction.connect();
			String sql="insert into student_groups(student_id,group_name,organization_id,created_by) values('"+studentid+"','"+groupname+"','"+Fdomain.getOrgid()+"','"+Fdomain.getCreated_by()+"')";
		
			
			prepareStatement=connection.prepareStatement(sql);
			//System.out.println("sql quary is:"+sql);
			 n=prepareStatement.executeUpdate();
			
			 if(n>=1){
				 //System.out.println(n+"row inserted");
				 status="success";
			 }else {
				 //System.out.println("no row inserted");
				 status="failure";
			 }
			
			
		}catch(Exception e){
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
		return status;
	}
	public ArrayList<AdminDomain> getStudentId(String userid,String organizationId){
		
		try {
			connection = DBTransaction.connect();
			String sql="select user_id,username from users_info left join course_description on users_info.organization_id=course_description.customer_id and users_info.course_id=course_description.course_id_defined_by_teaching_source"
				+ "				where  organization_id='"+organizationId+"' and privilege='Student'";
			prepareStatement=connection.prepareStatement(sql);
			//System.out.println("quary is:"+sql);
			resultSet=prepareStatement.executeQuery();
			while(resultSet.next()){
				AdminDomain adminDomain=new AdminDomain();
				adminDomain.setAdminId(resultSet.getString("user_id"));
				adminDomain.setAdminName(resultSet.getString("username"));
				////System.out.println("user id:"+rs.getString("user_id"));
				usersInfo.add(adminDomain);
				
			}
		}catch(Exception e){
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
		return usersInfo;
	}

	
}
