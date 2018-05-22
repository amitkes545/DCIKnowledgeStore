package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.OwnerReportKnowStoreDomain;

public class OwnerSelectKnowStoreIdDao {

	OwnerReportKnowStoreDomain rDomain = new OwnerReportKnowStoreDomain();
	ArrayList<String> arrayList=new ArrayList<String>();
	//int counter=0;
	
	public ArrayList<String> fetchKsIdAndUserId(String s1) {

		try {
			Connection connection = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select ksid,user_id from knowledgestore_info where created_by ='"+s1+"'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				//System.out.println(resultSet.getString("ksid"));
				rDomain.setKsId(resultSet.getString("ksid"));
				String ksid=resultSet.getString("ksid");
				arrayList.add(ksid);
				rDomain.setUserId(resultSet.getString("user_id"));
				String userId=resultSet.getString("user_id");
				arrayList.add(userId);
				////System.out.println(counter++);			 
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
}