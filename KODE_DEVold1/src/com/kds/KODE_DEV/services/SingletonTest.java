package com.kds.KODE_DEV.services;


	import java.sql.*;

	import com.kds.KODE_DEV.dbconnection.DBConSingleton;

	public class SingletonTest {

	    public void text() {
	        Connection conn = null;
	        PreparedStatement statement = null;
	        ResultSet resultado = null;
	        String query = "SELECT * FROM users_info";

	        try {
	            conn = DBConSingleton.getConnection();
	            statement = conn.prepareStatement(query);
	            resultado = statement.executeQuery();

	            while (resultado.next()) {
	                System.out.println(resultado.getString(1) + "\t" + resultado.getString(2) + "\t" + resultado.getString(3) + "\t" );
	            }
	        } 
	        catch (Exception e) {
	            System.err.println("El porque del cascar: " + e.getMessage());
	        } 
	        finally {
	        	DBConSingleton.closeConnection(conn);

	        }
	    }
	
	public static void main(String args[])
	{
		SingletonTest t= new SingletonTest();
		t.text();
	}
	}

