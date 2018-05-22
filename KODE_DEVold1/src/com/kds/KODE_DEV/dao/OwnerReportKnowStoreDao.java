package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.OwnerReportKnowStoreDomain;

public class OwnerReportKnowStoreDao {

	OwnerReportKnowStoreDomain rDomain = new OwnerReportKnowStoreDomain();

	public OwnerReportKnowStoreDomain fetchValue(OwnerReportKnowStoreDomain rDomain) {

		try {
			Connection connection = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = " select * from knowledgestore_info where ksid = '"
					+ rDomain.getKsId() + "'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				String kID = resultSet.getString("ksid");

				//System.out.println("userid is:" + kID);

				rDomain.setKsId(resultSet.getString("ksid"));
				rDomain.setOrgId(resultSet.getString("organization_id"));
				rDomain.setUserId(resultSet.getString("user_id"));
				rDomain.setCreatedBy(resultSet.getString("created_by"));
				rDomain.setKnowSpace(resultSet.getString("knowledge_store_space"));

				rDomain.setStatus(resultSet.getString("status"));

			}
			//System.out.println("the return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rDomain;
	}
}