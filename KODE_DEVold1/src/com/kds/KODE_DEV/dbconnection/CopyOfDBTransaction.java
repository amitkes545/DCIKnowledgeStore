package com.kds.KODE_DEV.dbconnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CopyOfDBTransaction implements Cloneable {
    private static Connection transaction=null;
    @SuppressWarnings("unused")
	private static CopyOfDBTransaction dbtransaction=null;
	private static String dbUser = "postgres";
	private static String dbPassword = "root";//root
	//private static String dbPassword = "root";
	
	//private static String uRL = "jdbc:postgresql://localhost:5432/KODE_DEV";  //mysql=3306 oracle=1521  SQL Server=1433
	//private static String uRL = "jdbc:postgresql://220.225.125.221:5432/KODE_DEV";
	private static String uRL = "jdbc:postgresql://106.51.46.212:5432/KODE";
	  
	
	@SuppressWarnings("static-access")
	public String getDBUser(){return this.dbUser;}
	@SuppressWarnings("static-access")
	public String getDBPassword(){return this.dbPassword;}
	private CopyOfDBTransaction(){}//private constructor
	  
	/*public Connection connect() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
		
		Class.forName("org.postgresql.Driver"); //1.)com.mysql.jdbc.Driver 2.) oracle.jdbc.driver.OracleDriver 3.)com.microsoft.sqlserver.jdbc.SQLServerDriver
		dbCon = DriverManager.getConnection(uRL, this.getDBUser(), this.getDBPassword());
		
		return dbCon;
	}*/
	public static synchronized Connection connect() throws ClassNotFoundException
	{
		if(transaction == null){
			dbtransaction=new CopyOfDBTransaction();
			
			//1.)com.mysql.jdbc.Driver 2.) oracle.jdbc.driver.OracleDriver 3.)com.microsoft.sqlserver.jdbc.SQLServerDriver
			try {
				Class.forName("org.postgresql.Driver"); 
				transaction = DriverManager.getConnection(uRL,dbUser , dbPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return transaction;
	}
	
	
	public Object clone() throws CloneNotSupportedException{
		 return new CloneNotSupportedException();
	}
	/*public void close() throws SQLException{
		
		dbCon.close();
	}*/
	
}
