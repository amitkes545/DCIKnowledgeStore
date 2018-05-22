package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.TopicMasterDomain;

public class TopicMasterDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	TopicMasterDomain sDomain = new TopicMasterDomain();
	ArrayList<String> arl=new ArrayList<String>();
	public ArrayList<String> fetchValue() {

		try {
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select topic_id,subject_id,topic_details from topic_master";// where organization_id=''";
			preparedStatement = connection.prepareStatement(sql);

		////System.out.println("the query is:" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				////System.out.println(resultSet.getString("Subject"));
			     sDomain.setTopicDetails(resultSet.getString("topic_details"));
				String topic=resultSet.getString("topic_details");
				String topic_id=resultSet.getString("topic_id");
				String subject_id=resultSet.getString("subject_id");
				arl.add(topic_id);
				arl.add(subject_id);
				arl.add(topic);
								 
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