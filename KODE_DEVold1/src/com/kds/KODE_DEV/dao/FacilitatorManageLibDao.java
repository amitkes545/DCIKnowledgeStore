package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;

public class FacilitatorManageLibDao {
	Connection con = null;
	
	PreparedStatement preparedStatement = null;

    ArrayList<String> arrayList = new ArrayList<String>();	
	public ArrayList<String> fetchValue(String userId,String orgId) {
		FacilitatorManageKnowStoreDomain rDomain1=new FacilitatorManageKnowStoreDomain();

		try {
			con = DBTransaction.connect();
			
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql="select lib_id,name_of_lib from knowledgestorelib_info where user_id='"+userId+"' and organization_id='"+orgId+"'";
			
			preparedStatement = con.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println(" the query in FacilitatorManageLibDao="+sql);
			while(resultSet.next()) 
			{
				rDomain1.setLibId(resultSet.getString("lib_id"));
				rDomain1.setLibName(resultSet.getString("name_of_lib"));
				String libId=resultSet.getString("lib_id");
				String libName=resultSet.getString("name_of_lib");
				arrayList.add(libId);
				arrayList.add(libName);
				
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
	
	public ArrayList<String> fetchValueforStudent(String userId,String orgId) {
		FacilitatorManageKnowStoreDomain rDomain1=new FacilitatorManageKnowStoreDomain();

		try {
			con = DBTransaction.connect();
			
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql="select lib_id,name_of_lib from knowledgestorelib_info where organization_id='"+orgId+"'";
			//System.out.println(sql);
			preparedStatement = con.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			//preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
				rDomain1.setLibId(resultSet.getString("lib_id"));
				rDomain1.setLibName(resultSet.getString("name_of_lib"));
				String libId=resultSet.getString("lib_id");
				String libName=resultSet.getString("name_of_lib");
				arrayList.add(libId);
				arrayList.add(libName);
				
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
	
}