package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SuperAdminManageKnowStoreDomain;

public class SuperAdminDeleteManageKStoreDao {
	
	public String deleteValues(SuperAdminManageKnowStoreDomain mksDomain) {

	String status=null;

	try {
                Connection connection = DBTransaction.connect();
                PreparedStatement preparedStatement = null;
				String sql = "delete from knowledgestore_info where ksid='"+ mksDomain.getKsId() +"'";
				preparedStatement = connection.prepareStatement(sql);
				
				//System.out.println("the query is:"+sql);
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
			return status;
			//close for deleteValues()
		}
}//close for class body