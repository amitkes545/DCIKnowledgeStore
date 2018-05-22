package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SubjectMasterDomain;

public class SubjectMasterDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	SubjectMasterDomain sDomain = new SubjectMasterDomain();
	ArrayList<String> arl=new ArrayList<String>();
	public ArrayList<String> fetchValue() {

		try {
			
			//String discipline=request.getPartameter("discipline");
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select * from subject_master";// where organization_id=''";
			preparedStatement = connection.prepareStatement(sql);

		////System.out.println("the query is:" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				////System.out.println(resultSet.getString("Subject"));
			     sDomain.setSubject(resultSet.getString("subject"));
			     sDomain.setDisciplineID(resultSet.getString("discipline_id"));
			     sDomain.setSubjectID(resultSet.getString("subject_id"));
			     
				String sub=resultSet.getString("Subject");
				String subid=resultSet.getString("subject_id");
				String dispid=resultSet.getString("discipline_id");
				arl.add(sub);
				arl.add(subid);
				arl.add(dispid);
								 
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