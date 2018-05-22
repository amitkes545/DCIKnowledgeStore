package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;

public class SuperAdminDao {

	public boolean isAdminUserAvailable(String email, String userId, String privilege) {

		boolean isAdminUser = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {

			connection = DBTransaction.connect();
			
			ResultSet resultSet = null;
			String sql = "select * from users_info where email=? and privilege=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, userId);
		//	preparedStatement.setString(3, "Admin");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isAdminUser = true;

			}
			return isAdminUser;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isAdminUser;
	}

}
