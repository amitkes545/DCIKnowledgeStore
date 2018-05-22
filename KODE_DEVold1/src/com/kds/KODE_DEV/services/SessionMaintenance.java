package com.kds.KODE_DEV.services;
import java.sql.*;

import com.kds.KODE_DEV.dbconnection.DBTransaction;



public class SessionMaintenance{
	Connection con=null;
 public	String createSession(String id,String privilege)
 	{
		 java.util.Date date= new java.util.Date();
		 //System.out.println(new Timestamp(date.getTime()));
		 try{
			 Class.forName("org.postgresql.Driver");
			  con = DBTransaction.connect();
			 java.sql.Statement st= con.createStatement();
			// java.sql.Connection con = 
			 //DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV", "postgres","kompac_security");
			// DriverManager.getConnection("jdbc:postgresql://122.165.130.69:5432/KODE_DEV", "postgres","kompac_security");
			 //java.sql.Statement st= con.createStatement();
		//st.executeUpdate("insert into Tracking_table(student_id,date_time,log_status,privilege) values('"+id+"','"+new Timestamp(date.getTime()).toString()+"','in')");
			//System.out.println(new Timestamp(date.getTime()).toString());
		//System.out.println();
		if(1==st.executeUpdate("insert into Tracking_table(student_id,date_time,log_status,privilege) values('"+id+"','"+new Timestamp(date.getTime()).toString()+"','in','"+privilege+"')"))
			{
				return "success";
			}
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 finally
		 {
		 	DBTransaction.closeConnection(con);
		 }
		return null;
	}
 public	String endSession(String id,String previlege){
	 java.util.Date date= new java.util.Date();
	 //System.out.println(new Timestamp(date.getTime()));
	 try{
		 Class.forName("org.postgresql.Driver");
		 con = DBTransaction.connect();
		 java.sql.Statement st= con.createStatement();
		 //java.sql.Connection con = 
		 //DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV", "postgres","kompac_security");
				// DriverManager.getConnection("jdbc:postgresql://122.165.130.69:5432/KODE_DEV", "postgres","kompac_security");
		// java.sql.Statement st= con.createStatement();
	//st.executeUpdate("insert into Tracking_table(student_id,date_time,log_status) values('"+id+"','"+new Timestamp(date.getTime()).toString()+"','in')");
		
	//System.out.println();
	if(1==st.executeUpdate("insert into Tracking_table(student_id,date_time,log_status,privilege) values('"+id+"','"+new Timestamp(date.getTime()).toString()+"','out','"+previlege+"')"))
		{
			return "success";
		}
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 }
	 finally
	 {
	 	DBTransaction.closeConnection(con);
	 }
	return null;
 }

}
