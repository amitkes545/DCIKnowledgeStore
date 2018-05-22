package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SuperAdminSetupKnowStoreDomain;

public class SuperAdminSetupknowStoreDao {

	SuperAdminSetupKnowStoreDomain aksDomain = new SuperAdminSetupKnowStoreDomain();

	public String insertAllInformation(SuperAdminSetupKnowStoreDomain aksDomain, String user) {

		String status = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// int size = aksDomain.getKsSize();
			// String user1 = aksDomain.getCreatedBy();
			// String createdBy= aksDomain.getUserId();

			con = DBTransaction.connect();
			
			// PreparedStatement pstmt1 = null;
			// ResultSet rs = null;

			OwnerSelectUserIDKStoreDao selectIdDao = new OwnerSelectUserIDKStoreDao();
			boolean isDuplicate = selectIdDao.superadminDuplicate(aksDomain);

			if (isDuplicate) {
				//System.out.println("user exist");
			} else {
				// String selectAllAdminSize
				// ="select sum(knowledge_store_space) from knowledgestore_info where created_by ='"+createdBy+"'";
				String sql = "insert into knowledgestore_info(organization_id, user_id, created_by, knowledge_store_space, status) values(?,?,?,?,?)";
				// String
				// updateQuery="update knowledgestore_info set knowledge_store_space=knowledge_store_space - "+size+" where user_id='"+user1+"'";

				// //System.out.println("the query is now"+selectAllAdminSize);
				// pstmt1 = con.prepareStatement(selectAllAdminSize);
				pstmt = con.prepareStatement(sql);

				// pstmt.setString(1,aksDomain.getKsid());
				pstmt.setString(1, aksDomain.getOrgId());
				pstmt.setString(2, user);
				pstmt.setString(3, aksDomain.getCreatedBy());
				pstmt.setInt(4, aksDomain.getKsSize());
				pstmt.setString(5, aksDomain.getStatus());

				//System.out.println("the query is:" + pstmt);
				int n = pstmt.executeUpdate();
				// int updated = pstmt1.executeUpdate();
				// //System.out.println("updated size is :"+updated);
				//System.out.println("query is inserted successfully");

				if (n != 0)
					status = "success";
				else
					status = "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public String selectAllAdminSpace(String userId) {

		String allAdminknowStoreSpace = "";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {

			//String createdBy = aksDomain.getUserId();

			connection = DBTransaction.connect();
			
			ResultSet resultSet = null;
			/*String selectAllAdminSize = "select sum(knowledge_store_space) from knowledgestore_info where created_by ='"
					+ userId + "'";
*/
			String selectAllAdminSize = "select SUM(case when knowledge_store_space IS NULL then 0 else knowledge_store_space end) from knowledgestore_info where created_by='"
					+ userId + "'";

			
			preparedStatement = connection.prepareStatement(selectAllAdminSize);

			//System.out.println("The query is:" + selectAllAdminSize);
			preparedStatement = connection.prepareStatement(selectAllAdminSize);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

			String	allSize = resultSet
						.getString(1);

				//System.out.println("allAdminknowStoreSpace is : "	+ allSize);
				allAdminknowStoreSpace=allSize;

				aksDomain.setKsId(resultSet.getString(1));
			}
			// //System.out.println("The return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allAdminknowStoreSpace;

	}

	public String selectSuperAdminSpace(String userId) {

		String superAdminknowStoreSpace = "";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {

			//String sessionUser = aksDomain.getUserId();

			 connection = DBTransaction.connect();
			ResultSet resultSet = null;
			String selectAllAdminSize = "select knowledge_store_space from knowledgestore_info where user_id ='"
					+ userId + "'";

			preparedStatement = connection.prepareStatement(selectAllAdminSize);

			//System.out.println("The query is:" + selectAllAdminSize);
			preparedStatement = connection.prepareStatement(selectAllAdminSize);
			System.out.println("preparedStatement "+preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

			String 	superAdminknow = resultSet.getString("knowledge_store_space");

				//System.out.println("superAdminknowStoreSpace is : "	+ superAdminknow);
				superAdminknowStoreSpace =superAdminknow;

				aksDomain.setKsId(resultSet.getString("knowledge_store_space"));
			}
			// //System.out.println("The return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return superAdminknowStoreSpace;

	}

	public String getEmailId(String user) {
		String emailId="";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection con = null;
		try {

			 con = DBTransaction.connect();
			String sql = "select email from users_info where user_id ='" + user + "'";
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				emailId = resultSet.getString("email");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		  return emailId;
	}

}
