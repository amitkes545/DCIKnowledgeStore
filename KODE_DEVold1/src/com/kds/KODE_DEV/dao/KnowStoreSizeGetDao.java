package com.kds.KODE_DEV.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.KnowStoreSizeSelectDomain;
import com.kds.KODE_DEV.services.FacilitatorKAssetsKnowSetupServiceFTP;

public class KnowStoreSizeGetDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	//OwnerSetUpKnowStoreDomain sDomain = new OwnerSetUpKnowStoreDomain();
	static final Logger LOGGER = Logger.getLogger(KnowStoreSizeGetDao.class);
	KnowStoreSizeSelectDomain kDomain = new KnowStoreSizeSelectDomain();
	//ArrayList<String> arl = new ArrayList<String>();

	public String fetchKnowStoreSpace(String userId,String orgId) {
		String size = null;

		try {
			connection = DBTransaction.connect();
			// preparedStatement = null;
			ResultSet resultSet = null;
			//String sql = "select knowledge_store_space from knowledgestore_info where user_id='"+userId+"' and organization_id='"+orgId+"'";
			String sql = "select knowledge_store_space from knowledgestore_info where organization_id='"+orgId+"'";
			preparedStatement = connection.prepareStatement(sql);
LOGGER.info(sql);

			System.out.println("the query is:" + sql);
			
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				//System.out.println(resultSet.getString("knowledge_store_space"));
				kDomain.setKsSize(resultSet.getString("knowledge_store_space"));
				String knowSpace = resultSet.getString("knowledge_store_space");
				size=knowSpace;
				//arl.add(knowSpace);

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
		return size;
	}
}