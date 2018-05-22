package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminSetUpKnowStoreDomain;

public class AdminSetUpKnowStoreFieldSelectorDao {

	AdminSetUpKnowStoreDomain adminsetupDomain = new AdminSetUpKnowStoreDomain();

	ArrayList<String> arrayList = new ArrayList<String>();
	PreparedStatement preparedStatement = null;
	Connection con =null;

	public ArrayList<String> fetchKsIdAndUserId(String orgId, String userId) {

		try {

			con = DBTransaction.connect();
			preparedStatement = null;
			ResultSet resultSet = null;
			String sqlQuery = "select ksid,user_id from knowledgestore_info where organization_id='"+orgId+"' and created_by='"+userId+"'";
			preparedStatement = con.prepareStatement(sqlQuery);

			//System.out.println("the query is:" + sqlQuery);

			preparedStatement = con.prepareStatement(sqlQuery);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				//System.out.println(resultSet.getString("ksid"));
				adminsetupDomain.setKsId(resultSet.getString("ksid"));
				adminsetupDomain.setUserId(resultSet.getString("user_id"));
				String ksid = resultSet.getString("ksid");
				String userId1 = resultSet.getString("user_id");
				arrayList.add(ksid);
				arrayList.add(userId1);

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
		return arrayList;
	}

	public ArrayList<String> fetchValue2(String userId) {

		try {
			con = DBTransaction.connect();
			preparedStatement = null;
			ResultSet resultSet = null;
			String sqlQuery = "select organization_id from knowledgestore_info where user_id='"+userId+"'";
			preparedStatement = con.prepareStatement(sqlQuery);

			//System.out.println("the query is:" + sqlQuery);

			preparedStatement = con.prepareStatement(sqlQuery);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				//System.out.println(resultSet.getString("organization_id"));
				adminsetupDomain.setOrgId(resultSet.getString("organization_id"));
				String orgid = resultSet.getString("organization_id");
				arrayList.add(orgid);
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
		return arrayList;
	}

	public ArrayList<String> fetchValue3(String orgId) {

		try {
			con = DBTransaction.connect();
			preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select user_id from users_info where organization_id='"+orgId+"' and privilege='Faculty'";
			preparedStatement = con.prepareStatement(sql);

			//System.out.println("the query is:" + sql);

			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				//System.out.println(resultSet.getString("user_id"));
				adminsetupDomain.setUserId(resultSet.getString("user_id"));
				String userid = resultSet.getString("user_id");
				arrayList.add(userid);
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
		return arrayList;
	}
	public ArrayList<String> fetchValue4(String orgId) {

		try {
			con = DBTransaction.connect();
			preparedStatement = null;
			ResultSet resultSet = null;
			String sqlQuery = "select user_id from users_info where organization_id='"+orgId+"' and privilege='Admin'";
			preparedStatement = con.prepareStatement(sqlQuery);

			//System.out.println("the query is:" + sqlQuery);

			preparedStatement = con.prepareStatement(sqlQuery);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				//System.out.println(resultSet.getString("user_id"));
				adminsetupDomain.setUserId(resultSet.getString("user_id"));
				String userid = resultSet.getString("user_id");
				arrayList.add(userid);

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
		return arrayList;
	}
	

	public ArrayList<String> fetchUserIdWithPrivilege(String orgId) {

		try {
			con = DBTransaction.connect();
			preparedStatement = null;
			ResultSet resultSet = null;
			String sqlQuery = "select user_id from users_info where organization_id='"+orgId+"' and privilege='SuperAdmin'";
			preparedStatement = con.prepareStatement(sqlQuery);

			//System.out.println("the query is:" + sqlQuery);

			preparedStatement = con.prepareStatement(sqlQuery);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				//System.out.println(resultSet.getString("user_id"));
				adminsetupDomain.setUserId(resultSet.getString("user_id"));
				String userid = resultSet.getString("user_id");
				arrayList.add(userid);

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
		return arrayList;
	}
	
	public ArrayList<String> fetchKnowStoreStatus(String userId) {

		try {
			con = DBTransaction.connect();
			preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select status from knowledgestore_info where created_by='"+userId+"'";
			preparedStatement = con.prepareStatement(sql);

			//System.out.println("the query is:" + sql);

			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				//System.out.println("this for now:"+resultSet.getString("status"));
				adminsetupDomain.setUserId(resultSet.getString("status"));
				String status = resultSet.getString("status");
				arrayList.add(status);
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
		return arrayList;
    }
}