package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminManageKnowStoreDomain;

public class AdminDeleteManageKStoreDao {
	
	public String deleteValues(AdminManageKnowStoreDomain mksDomain) {

	String status=null;
	PreparedStatement preparedStatement = null;
	Connection connection =null;

	try {
                 connection = DBTransaction.connect();
                //PreparedStatement preparedStatement = null;
				String sqlQuery = "delete from knowledgestore_info where ksid='"+ mksDomain.getKsId() +"'";
				preparedStatement = connection.prepareStatement(sqlQuery);
				
				//System.out.println("the query is:"+sqlQuery);
				int queryResult = preparedStatement.executeUpdate();
				//System.out.println(queryResult+"row is Deleted successfully");
				
				if (queryResult==1)
					status = "success";
				else
					status = "failure";
				//close for try block
			}catch(Exception e){
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
			//close for deleteValues()
		}
}//close for class body