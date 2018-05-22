package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.OwnerManageKnowStoreDomain;

public class OwnerUpdateManageKStoreDao {

	public String updateValues(OwnerManageKnowStoreDomain mksDomain) {

		String status = null;

		try {
			Connection connection = DBTransaction.connect();

			String sql = "update knowledgestore_info set " + "ksid='"
					+ mksDomain.getKsId() + "'" + "," + "organization_id='"
					+ mksDomain.getOrgId() + "'" + "," + "user_id='"
					+ mksDomain.getUserId() + "'" + ","
					+ "knowledge_store_space='" + mksDomain.getKsSize() + "'"
					+ "," + "status='" + mksDomain.getStatus() + "'"
					+ "where ksid='" + mksDomain.getKsId() + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			int i = preparedStatement.executeUpdate();
			//System.out.println(i + "row is updated successfully");

			if (i == 1)
				status = "success";
			else
				status = "failure";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}