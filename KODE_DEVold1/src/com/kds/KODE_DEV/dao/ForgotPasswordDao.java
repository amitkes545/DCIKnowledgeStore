package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.services.ForgotPasswordServlet1;

public class ForgotPasswordDao {
	
	Connection connection = null;
	PreparedStatement prepareStatement = null;

	public String checkPwdCredentials(String emailid, String userid) {

		String status = "";
		try {
			
			//Establish the connection between Java application to DataBase(postgreSql)
			
			connection = DBTransaction.connect();
			ResultSet resultSet = null;
			prepareStatement = connection.prepareStatement("select password from users_info where email=?");
			prepareStatement.setString(1, emailid);
			resultSet = prepareStatement.executeQuery();

			if (resultSet.next()) {

				prepareStatement = connection
						.prepareStatement("select password,username from users_info where email=? and user_id=?");
				prepareStatement.setString(1, emailid);
				prepareStatement.setString(2, userid);

				resultSet = prepareStatement.executeQuery();
				if (resultSet.next()) {

					System.out.println("pwd1");
					ForgotPasswordServlet1 fpservlet = new ForgotPasswordServlet1();
					fpservlet.SendPwd(resultSet.getString("password"), emailid, resultSet.getString("username"));
					status = "valid";
				} else {
					status = "User ID is wrong";

				}
			} else {
				status = "Email ID is wrong";

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			
			//close connection and statement
			
			try {
				connection.close();
				prepareStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;

	}
}
