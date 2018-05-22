package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SessionAttendDomain;


public class SessionAttendDao   {
	//DBTransaction dbT = new DBTransaction();
	Connection connection=null;
	PreparedStatement prepareStatement=null;
	ResultSet resultSet=null;
	SessionAttendDomain al= new  SessionAttendDomain();

	public String insertAttendSession(String sesid,String studid)
	{
		
        String status = null;
		//System.out.println("group values in dao:"+sdomain.getGroupId());
		try {

			connection = DBTransaction.connect();
			String query="insert into session_attend(session_id,student_id) values('"+sesid+"','"+studid+"')";
			prepareStatement=connection.prepareStatement(query);
			
			/*prepareStatement.setString(1,sdomain.getSessionId());
			prepareStatement.setString(2,sdomain.getStudentId());*/
			
			System.out.println("the query is:"+query);
			
			int n=prepareStatement.executeUpdate();
			 if(n>=1)
                 status="valid";
             else
             	status="notvalid";
	}catch(Exception e)
	{
		e.printStackTrace();
	}
		finally
		   {
			   try{
				   connection.close();
				   prepareStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
   return status;          
		}

	
	}
	



	
