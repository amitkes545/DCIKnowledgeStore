package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorKnowSetUpDomain;

public class FacilitatorSelectKsidKStoreDao {
	
	
	FacilitatorKnowSetUpDomain sDomain = new FacilitatorKnowSetUpDomain();
	ArrayList<String> arl = new ArrayList<String>();
	
	Connection con=null ;
	PreparedStatement preparedStatement =null;
	
	public ArrayList<String> fetchValue(String orgId , String userId) {
		
		try {
			con= DBTransaction.connect();
			
			ResultSet resultSet = null;
			String sql = "select ksid,user_id from knowledgestore_info where organization_id='"+orgId+"' and user_id='"+userId+"'";
		
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println(" the query in fetch value="+sql);
			while (resultSet.next()) {
				//System.out.println(resultSet.getString("ksid"));
				sDomain.setKsId(resultSet.getString("ksid"));
				String ksid = resultSet.getString("ksid");
				String userId1 = resultSet.getString("user_id");
				arl.add(ksid);
				arl.add(userId1);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arl;
	}
	
public String fetchValue1(String orgId, String userId) {
	String ksid="";
		try {
			
			
			con= DBTransaction.connect();
			
			ResultSet resultSet = null;
			String sql = "select ksid from knowledgestore_info where organization_id='"+orgId+"' ";
			//System.out.println(sql);
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				//System.out.println(resultSet.getString("ksid"));
				sDomain.setKsId(resultSet.getString("ksid"));
			    ksid = resultSet.getString("ksid");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return ksid;
	}
}		