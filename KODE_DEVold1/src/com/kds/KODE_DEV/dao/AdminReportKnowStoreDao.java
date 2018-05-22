package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminReportKnowStoreDomain;

public class AdminReportKnowStoreDao {

	AdminReportKnowStoreDomain adminreportDomain = new AdminReportKnowStoreDomain();
    // method for fetching all value according to data base 
	public AdminReportKnowStoreDomain fetchValue(AdminReportKnowStoreDomain adminreportDomain) {
		PreparedStatement preparedStatement = null;
		Connection connection =null;

		try {
			connection = DBTransaction.connect();
			
			ResultSet resultSet = null;
			String sql = " select * from knowledgestore_info where ksid = '"+ adminreportDomain.getKsId() + "'";
					
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				String kID = resultSet.getString("ksid");

				//System.out.println("userid is:" + kID);

				adminreportDomain.setKsId(resultSet.getString("ksid"));
				adminreportDomain.setOrgId(resultSet.getString("organization_id"));
				adminreportDomain.setUserId(resultSet.getString("user_id"));
				adminreportDomain.setCreatedBy(resultSet.getString("created_by"));
				adminreportDomain.setKnowSpace(resultSet.getString("knowledge_store_space"));		
				adminreportDomain.setStatus(resultSet.getString("status"));
					 
			}// closing if block
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
		return adminreportDomain;
	}//close method fetchValue
}// close class body