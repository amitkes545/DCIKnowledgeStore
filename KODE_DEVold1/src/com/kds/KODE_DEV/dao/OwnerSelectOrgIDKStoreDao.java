package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminSetUpKnowStoreDomain;
import com.kds.KODE_DEV.domain.OwnerSetUpKnowStoreDomain;
import com.kds.KODE_DEV.domain.SuperAdminSetupKnowStoreDomain;

public class OwnerSelectOrgIDKStoreDao {
	OwnerSetUpKnowStoreDomain sDomain = new OwnerSetUpKnowStoreDomain();
	ArrayList<String> arl=new ArrayList<String>();
	
	public ArrayList<String> fetchValue(String orgId) {

		try {
			Connection connection = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
		
			String sql = "select user_id from users_info where organization_id='"+ orgId + "'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				//System.out.println(resultSet.getString("user_id"));
				sDomain.setUserId(resultSet.getString("user_id"));
				String userID = resultSet.getString("user_id");
				arl.add(userID);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arl;
	}
	
	public ArrayList<String> fetchUser() {

		try {
			Connection con = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select user_id from users_info";
			preparedStatement = con.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				//System.out.println(resultSet.getString("user_id"));
				sDomain.setUserId(resultSet.getString("user_id"));
				String userID = resultSet.getString("user_id");
				arl.add(userID);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arl;
	}
	public boolean superadminDuplicate(SuperAdminSetupKnowStoreDomain aksDomain) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String userId = aksDomain.getUserId();
		boolean result = false;

		try {
			Connection connection = DBTransaction.connect();
			String query = "select user_id from knowledgestore_info where user_id='"+userId+"'";

			preparedStatement = connection.prepareStatement(query);
			//System.out.println("the duplicate query is :"+query);
			//preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean adminDuplicate(AdminSetUpKnowStoreDomain adomain) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String userId = adomain.getUserId();
		boolean result = false;

		try {
			Connection connection = DBTransaction.connect();
			String query = "select user_id from knowledgestore_info where user_id='"+userId+"'";

			preparedStatement = connection.prepareStatement(query);
			//System.out.println("the duplicate query is :"+query);
			//preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean ownerDuplicate(OwnerSetUpKnowStoreDomain aksDomain) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String orgId = aksDomain.getOrgId();
		
		boolean result = false;

		try {
			Connection connection = DBTransaction.connect();
			String query = "select organization_id from knowledgestore_info where organization_id='"+orgId+"'";

			preparedStatement = connection.prepareStatement(query);
			//System.out.println("the duplicate query is :"+query);
			//preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
    }
	
	public ArrayList<String> fetchValue() {

		try {
			Connection connection = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			//String sql = "select organization_id,organization_name from org_details";
			String sql = "select organization_id,organization_name from org_details where organization_id not in "   
			+ "(select organization_id from users_info where privilege='Super Admin')";
			preparedStatement = connection.prepareStatement(sql);

		////System.out.println("the query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				////System.out.println(resultSet.getString("organization_id"));
			     sDomain.setOrgId(resultSet.getString("organization_id"));
			     sDomain.setOrgName(resultSet.getString("organization_name"));
				String orgid=resultSet.getString("organization_id");
				String orgname=resultSet.getString("organization_name");
				//String orgidname=orgid+"("+orgname+")";
				arl.add(orgid);
				arl.add(orgname);
								 
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arl;
	}
	public ArrayList<String> fetchValueCourse() {

		try {
			Connection connection = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select organization_id,organization_name from org_details";
		
			preparedStatement = connection.prepareStatement(sql);

		////System.out.println("the query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				////System.out.println(resultSet.getString("organization_id"));
			     sDomain.setOrgId(resultSet.getString("organization_id"));
			     sDomain.setOrgName(resultSet.getString("organization_name"));
				String orgid=resultSet.getString("organization_id");
				String orgname=resultSet.getString("organization_name");
				//String orgidname=orgid+"("+orgname+")";
				arl.add(orgid);
				arl.add(orgname);
								 
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arl;
	}
	
	public ArrayList<String> fetchNewOrg() {

		try {
			Connection connection = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select distinct UI.organization_id,organization_name from org_details OD join users_info UI on OD.organization_id=UI.organization_id"
					+ "					 where privilege='Super Admin' and  UI.organization_id not in (select organization_id from knowledgestore_info)";
		//	String sql = "select organization_id,organization_name from org_details where organization_id not in (select organization_id from knowledgestore_info)";
			//String sql = "select user_id,username from users_info where privilege='Super Admin' and organization_id not in (select organization_id from knowledgestore_info)";
			
			preparedStatement = connection.prepareStatement(sql);

		//System.out.println("the query is:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
			//	//System.out.println(resultSet.getString("organization_id"));
			     sDomain.setOrgId(resultSet.getString("organization_id"));
				String orgid=resultSet.getString("organization_id");
				 sDomain.setOrgName(resultSet.getString("organization_name"));
					String orgname=resultSet.getString("organization_name");
				arl.add(orgid);
				arl.add(orgname);
								 
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arl;
	}	
}