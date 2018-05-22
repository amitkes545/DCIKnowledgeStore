package com.kds.KODE_DEV.dbconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTransaction17Feb {

	//private String driver="com.mysql.jdbc.Driver";
	//private String url="jdbc:mysql://localhost:3306/garudarmaster";
	//private static String url="jdbc:postgresql://192.168.1.123:5432/KODE_DEV";
	//private String url = "jdbc:postgresql://220.225.125.221:5432/KHRM";
	private static String url = "jdbc:postgresql://192.168.1.116:5432/KODE";
	
	//jdbc:postgresql://localhost:5432/
    private static String dbuser="postgres";
   private static String dbpwd="root";
    //private static String dbpwd=";
    //private String dbuser = "kompac";
	//private String dbpwd = "kompac";
    static Connection dbCon = null;
           
    public static String getDbuser() {return dbuser;}
    public static String getDbpwd() {return dbpwd;}
    //public String getDriver() {return this.driver;}
  //  public String getUrl() {return this.url;}
    
    public static Connection connect() throws ClassNotFoundException, SQLException,IllegalAccessException,InstantiationException
    {
    	//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    	Class.forName("org.postgresql.Driver");
        dbCon = DriverManager.getConnection(url, getDbuser(), getDbpwd());
        return dbCon;
    }
    public void close() throws SQLException 
    {
    	dbCon.close();
    }
   

}