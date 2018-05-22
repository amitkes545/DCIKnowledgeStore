package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorSessionReportDomain;


public class FacilitatorSessionCancelDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	String status = null;
	
	public String updateValuesAvailable(FacilitatorSessionReportDomain mksDomain) {

		try {
			connection = DBTransaction.connect();

			String sql = "update session_details set session_status='cancel' where session_name='"
					+ mksDomain.getSessionName()+ "'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			int i = preparedStatement.executeUpdate();
			//System.out.println(i + "row is updated successfully");

			if (i == 1)
				status = "success";
			else
				status = "failure";
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
	}
	
	
	public String updateValuesCancel(FacilitatorSessionReportDomain mksDomain) {

		try {
			connection = DBTransaction.connect();

			String sql = "update session_details set session_status='Available' where session_name='"
					+ mksDomain.getSessionName() + "'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			int i = preparedStatement.executeUpdate();
			//System.out.println(i + "row is updated successfully");

			if (i == 1)
				status = "success";
			else
				status = "failure";
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
	}
	public String getSessionStatus(FacilitatorSessionReportDomain mksDomain) {

		try {
			connection = DBTransaction.connect();

			String sql = "select session_status from session_details where session_name='"
					+ mksDomain.getSessionName() + "'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			ResultSet rs = preparedStatement.executeQuery();
			

			while (rs.next()){
				status=rs.getString("session_status");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
	}
}