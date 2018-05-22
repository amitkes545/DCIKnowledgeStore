package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorSessionReportDomain;

public class FacilitatorSessionSendingMailDao {
	Connection connection = null;
	//PreparedStatement preparedStatement = null;
	
		String status;
		FacilitatorSessionReportDomain mksDomain = new FacilitatorSessionReportDomain();
		ArrayList<String> al= new ArrayList<String>();
		public ArrayList<String> getMailingList(String userid,String orgid) throws Exception{
			
			try 
			{
				connection= DBTransaction.connect();
				//PreparedStatement ps= null;
				ResultSet resultSet= connection.prepareStatement("SELECT email FROM users_info where created_by='"+userid+"'and organization_id='"+orgid+"'").executeQuery();
				while(resultSet.next())
				{
				  al.add(resultSet.getString("email"));	
				}
				//System.out.println("dao of emailid="+al);
				//System.out.println("userId Is"+userid);
		     }
			catch (ClassNotFoundException e) 
			{
				
				e.printStackTrace();
			} 
			catch (SQLException e) 
			{
				
				e.printStackTrace();
			} 
			finally
			   {
				   try{
					   connection.close();
					  // pstmt.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return al;
		}
   }	
