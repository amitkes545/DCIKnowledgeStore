package com.kds.KODE_DEV.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class DBTransactionForDCI implements Cloneable{
	static final Logger logger = Logger.getLogger(DBTransactionForDCI.class);
	
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static final String  url = "jdbc:postgresql://192.168.1.116:5432/KODE";
	private static final String  user = "postgres";

	private static final String  pass = "root";
	public static Connection dbConnection() {
		logger.info(" Connection Open >>");
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
			logger.error("Unable to Establish Connection with Database " + e.getMessage());
		}
		return con;
	}
	
	public static void closeConnection(Connection con) {
		logger.info(" Connection Closed>>");
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
			logger.error("Unable to close Connection " + e.getMessage());
			}
		}
	}
	
	public static void closeStatementResultSet(PreparedStatement ps, ResultSet rs) {
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error("Unable to close PreparedStatement " + e.getMessage());	
			}
		}
		
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("Unable to close ResultSet " + e.getMessage());	
			}
		}
	}	
}
