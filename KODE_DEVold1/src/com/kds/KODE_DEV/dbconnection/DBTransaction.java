package com.kds.KODE_DEV.dbconnection;

	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.kds.KODE_DEV.util.PropertiesUtil;

		public class DBTransaction {

		   public static Connection connect() throws Exception {
			   String url = PropertiesUtil.getProperty("dbURL");
		    //    String url = "jdbc:postgresql://183.82.35.240:5432/";
			// String url = "jdbc:postgresql://192.168.1.116:5432/";
			// String url = "jdbc:postgresql://localhost:5433/";
			   String dbName =PropertiesUtil.getProperty("dbName");
			   String driver = "org.postgresql.Driver";
		        /*String dbName = "KODEKS";
		        String driver = "org.postgresql.Driver";
		        String userName = "postgres";
		        
		        String password ="root";*/
			   String userName =  PropertiesUtil.getProperty("dbUsername");
			   String password =PropertiesUtil.getProperty("dbpwd");
			 
		        Class.forName(driver).newInstance();
		        Connection conn = DriverManager.getConnection(url + dbName, userName,password);

		        return conn;
		    }
		   
		   public static Connection connectKnoWStore() throws Exception {

		      //  String url = "jdbc:postgresql://localhost:5432/";
			   String url = PropertiesUtil.getProperty("dbURL");
			 //String url = "jdbc:postgresql://61.12.44.18:5432/";
		      //  String dbName = "KODEKS";
			   String dbName =PropertiesUtil.getProperty("dbName");
		        String driver = "org.postgresql.Driver";
		      /*  String userName = "postgres";
		        
		        String password ="root";*/
		        String userName =  PropertiesUtil.getProperty("dbUsername");
				   String password =PropertiesUtil.getProperty("dbpwd");
				 
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