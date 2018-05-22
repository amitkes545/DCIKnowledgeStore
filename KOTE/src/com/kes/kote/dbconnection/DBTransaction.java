package com.kes.kote.dbconnection;

	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kes.kote.util.PropertiesUtil;

public class DBTransaction {

		public static Connection openConnection() throws Exception {
			   
			   
			   String driver = PropertiesUtil.getDBProperty("db.driver");
			   String DBURL = PropertiesUtil.getDBProperty("db.url");
			   
		       String userName = PropertiesUtil.getDBProperty("db.username");
		        
		       String password =PropertiesUtil.getDBProperty("db.password");
		      System.out.println(driver +""+DBURL+"--"+ userName+"=="+password);
		       Class.forName(driver).newInstance();
		       Connection conn = DriverManager.getConnection(DBURL, userName,password);

		       
		       return conn;
		    }
  
		 public static void closeConnection(Connection conn,Statement st,ResultSet rs) {

		        try {

		            conn.close();
		            st.close();
		            rs.close();
		        } catch (SQLException e) {

		        }

		    }
		}