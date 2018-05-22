package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminReportKnowStoreDomain;


public class AdminSelectKnowStoreIdDao {

	AdminReportKnowStoreDomain adminReportknowDomain = new AdminReportKnowStoreDomain();
	ArrayList<String> arrayList = new ArrayList<String>();
	//int counter=0;
	//method for selecting know store id according to user id
	public ArrayList<String> fetchValue(String userId) {
		PreparedStatement preparedStatement = null;
		Connection connection =null;

		try {
			 connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sqlQuery = "select ksid from knowledgestore_info where created_by ='"+userId+"'";
			preparedStatement = connection.prepareStatement(sqlQuery);

			//System.out.println("the query is:" + sqlQuery);
			preparedStatement = connection.prepareStatement(sqlQuery);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				//System.out.println(resultSet.getString("ksid"));
				adminReportknowDomain.setKsId(resultSet.getString("ksid"));
				String ksid=resultSet.getString("ksid");
				arrayList.add(ksid);
				////System.out.println(counter++);			 
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
			   connection.close();
				preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayList;
	}//closing fetchValue()
}// closing class body 