package com.kds.KODE_DEV.dbconnection;

	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

		public class DBConSingleton {

		   public static Connection getConnection() throws Exception {
System.out.print("Hi Harman");
		        String url = "jdbc:postgresql://192.168.1.116:5432/";
		        String dbName = "KODE";
		        String driver = "org.postgresql.Driver";
		        String userName = "postgres";
		        String password = "root";

		        Class.forName(driver).newInstance();
		        Connection conn = DriverManager.getConnection(url + dbName, userName,password);

		        return conn;
		    }
		    public static void closeConnection(Connection conn) {

		        try {

		            conn.close();

		        } catch (SQLException e) {

		        }

		    }
		}