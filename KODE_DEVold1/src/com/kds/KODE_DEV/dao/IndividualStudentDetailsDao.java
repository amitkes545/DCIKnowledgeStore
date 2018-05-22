package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class IndividualStudentDetailsDao {
	ResultSet resultSet = null;
	PreparedStatement prepareStatement = null;
	Connection con =null;
	public ResultSet IndividualStudentDetails(String s){
	
		//DBTransaction1 dbt = new DBTransaction1();
		
		try {
			con = DBTransaction.connect();
			
			String quary = "select * from users_info where user_id='"+s+"'";
			//System.out.println(quary);
			prepareStatement = con.prepareStatement(quary);
			resultSet = prepareStatement.executeQuery();
			
	}catch(Exception e){
		e.printStackTrace();
	}
		finally
		   {
			   try{
				   con.close();
				   prepareStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return resultSet;
	}
	public ArrayList<DisplayDomain> getStreamInfo(String streamId){
		ArrayList<DisplayDomain> streamInfo=new ArrayList<DisplayDomain>();
		//DBTransaction1 dbt = new DBTransaction1();
		
		try {
			con = DBTransaction.connect();
			//PreparedStatement pstmt = null;			
			String quary = "select * from department_master where stream_id='"+streamId+"'";
			//System.out.println(quary);
			prepareStatement = con.prepareStatement(quary);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				DisplayDomain displayDomain=new DisplayDomain();
				displayDomain.setDepartId(resultSet.getString("dept_id"));
				displayDomain.setDepartName(resultSet.getString("dept_name"));
				displayDomain.setOrg_id(resultSet.getString("organization_id"));
				displayDomain.setStream(resultSet.getString("stream_id"));
				streamInfo.add(displayDomain);
			}
	}catch(Exception e){
		e.printStackTrace();
	}
		finally
		   {
			   try{
				   con.close();
				   prepareStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return streamInfo;
	}
public ArrayList<UsersInfoDomain> getDepartmentInfo(){
		ArrayList<UsersInfoDomain> organizationName=new ArrayList<UsersInfoDomain>();
		//DBTransaction1 dbt = new DBTransaction1();
		
		try {
			con = DBTransaction.connect();
			//PreparedStatement pstmt = null;			
			String quary = "select org_type_name from organization_type_master";
			//System.out.println(quary);
			prepareStatement = con.prepareStatement(quary);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				
				UsersInfoDomain usersInfoDomain=new UsersInfoDomain();
				usersInfoDomain.setOrgId(resultSet.getString("org_type_name"));
				organizationName.add(usersInfoDomain);
			}
	}catch(Exception e){
		e.printStackTrace();
	}
		finally
		   {
			   try{
				   con.close();
				   prepareStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return organizationName;
	}
public ArrayList<UsersInfoDomain> getUniversityType(UsersInfoDomain universityType){
	ArrayList<UsersInfoDomain> universityName=new ArrayList<UsersInfoDomain>();
	//DBTransaction1 dbt = new DBTransaction1();
	
	try {
		con = DBTransaction.connect();
		//PreparedStatement pstmt = null;			
		String quary = "select organization_name from org_details where org_type='"+universityType.getOrgId()+"'";
		//System.out.println(quary);
		prepareStatement = con.prepareStatement(quary);
		resultSet = prepareStatement.executeQuery();
		while(resultSet.next()){
			
			UsersInfoDomain usersInfoDomain=new UsersInfoDomain();
			usersInfoDomain.setOrgId(resultSet.getString("organization_name"));
			universityName.add(usersInfoDomain);
		}
}catch(Exception e){
	e.printStackTrace();
}
	finally
	   {
		   try{
			   con.close();
			   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return universityName;
}

}
