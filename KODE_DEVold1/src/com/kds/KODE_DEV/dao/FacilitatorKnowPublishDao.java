package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorKnowPublishDomain;


public class FacilitatorKnowPublishDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	public String updateValues(FacilitatorKnowPublishDomain mksDomain) {

		String status = "";
		
		try {
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			// ResultSet rs = null;
			String sql = "update knowledgeassets_info set status='Active' where ksid='" + mksDomain.getKsId() + "'";
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
	public String updateValuesInActive(FacilitatorKnowPublishDomain mksDomain) {

		String status = "";

		try {
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			// ResultSet rs = null;
			String sql = "update knowledgeassets_info set status='InActive' where ksid='" + mksDomain.getKsId() + "'";
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
}