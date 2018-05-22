package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.OwnerManageKnowStoreDomain;

public class OwnerDeleteManageKStoreDao {
	
	public String deleteValues(OwnerManageKnowStoreDomain mksDomain) {

	String status=null;

	try {
                Connection con = DBTransaction.connect();
			
				String sql = "delete from knowledgestore_info where ksid='"+ mksDomain.getKsId() +"'";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				
				//System.out.println("the query is:"+sql);
				int i = preparedStatement.executeUpdate();
				//System.out.println(i+"row is Deleted successfully");
				
				if (i==1)
					status = "success";
				else
					status = "failure";
			}catch(Exception e){
				e.printStackTrace();
			}
			return status;
		}
   }