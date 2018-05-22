package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisciplineMasterDomain;

public class DisciplineMasterDao {
	DisciplineMasterDomain sDomain = new DisciplineMasterDomain();
	ArrayList<String> arl=new ArrayList<String>();
	
	Connection connection= null;	
	
	PreparedStatement preparedStatement=null;
	
	public ArrayList<String> fetchValue() {

		try {
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select customer_id,discipline,discipline_id from discipline_master";// where organization_id=''";
			preparedStatement = connection.prepareStatement(sql);

		////System.out.println("the query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				////System.out.println(resultSet.getString("discipline"));
			     sDomain.setDiscipline(resultSet.getString("discipline"));
			     sDomain.setDisciplineID(resultSet.getString("discipline_id"));
			    String customer=resultSet.getString("customer_id"); 
			    ////System.out.println("customer_id:"+customer);
				String disc=resultSet.getString("discipline");
				String discid=resultSet.getString("discipline_id");
				arl.add(customer);
				arl.add(disc);
				arl.add(discid);
								 
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
		return arl;
	}
}