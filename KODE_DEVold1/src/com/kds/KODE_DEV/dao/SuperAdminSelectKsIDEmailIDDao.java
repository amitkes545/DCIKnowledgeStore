package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SuperAdminSetupKnowStoreDomain;

public class SuperAdminSelectKsIDEmailIDDao {

	SuperAdminSetupKnowStoreDomain aksDomain = new SuperAdminSetupKnowStoreDomain();

	public String fetchKnowStoreID(SuperAdminSetupKnowStoreDomain rDomain) {
		String kID="";
		try {
			Connection connection = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select ksid from knowledgestore_info where organization_id = '"
					+ rDomain.getOrgId() + "' and created_by= '"
					+ rDomain.getCreatedBy() + "'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("The query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				kID = resultSet.getString("ksid");

				//System.out.println("Ks ID is : " + kID);

				rDomain.setKsId(resultSet.getString("ksid"));
			}
			////System.out.println("The return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kID;
	}
	public String fetchEmailId(SuperAdminSetupKnowStoreDomain rDomain) {
		String emailId="";

		try {
			Connection connection = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select email from users_info where user_id = '"
					+ rDomain.getUserId() + "' and organization_id= '"
					+ rDomain.getOrgId() + "'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("The query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

			    emailId = resultSet.getString("email");

				//System.out.println("Email ID is :"+ emailId);

				rDomain.setEmailId(resultSet.getString("email"));
			}
			////System.out.println("The return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailId;
	}
}