package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.OwnerSetUpKnowStoreDomain;

public class FacilitatorSelectUsersIDDao {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	OwnerSetUpKnowStoreDomain sDomain = new OwnerSetUpKnowStoreDomain();
	ArrayList<String> arl = new ArrayList<String>();

	public ArrayList<String> fetchValue(String orgId) {

		try {
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select user_id from users_info where organization_id='"+orgId+"'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				//System.out.println(resultSet.getString("user_id"));
				sDomain.setUserId(resultSet.getString("user_id"));
				String userID = resultSet.getString("user_id");
				arl.add(userID);

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
		return arl;
	}
}