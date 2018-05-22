package com.kds.KODE_DEV.dao;

import java.io.File;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kds.KODE_DEV.dao.OwnerSelectUserIDKStoreDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SuperAdminSetupKnowStoreDomain;
import com.kds.KODE_DEV.domain.FacultySetupKnowStoreDomain;

public class FacultySetupknowStoreDao {
	
	FacultySetupKnowStoreDomain aksDomain = new FacultySetupKnowStoreDomain();
	Connection con = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public String insertAllInformation(FacultySetupKnowStoreDomain aksDomain, String user) {

		String status = "";

		try {
			

			con = DBTransaction.connect();
			PreparedStatement pstmt = null;
			
		
			//boolean isDuplicate = selectIdDao.adminDuplicate(aksDomain);
			//String emailid = selectIdDao.fetchEmailId(aksDomain);
		
				
				String sql = "insert into knowledgestore_info(organization_id, user_id, created_by, knowledge_store_space, status) values(?,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql);

			
				pstmt.setString(1, aksDomain.getOrgId());
				pstmt.setString(2, user);
				pstmt.setString(3, aksDomain.getCreatedBy());
				pstmt.setInt(4, aksDomain.getKsSize());
				pstmt.setString(5, aksDomain.getStatus());

				
				int n = pstmt.executeUpdate();
			
				if (n != 0)
					status = "success";
				else
					status = "failure";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closecon();
		}
		return status;
	}

	public String selectAllFacultySpace(String userId) {

		String allFacultyknowStoreSpace = "";

		try {

		

			con = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String selectAllAdminSize = "select sum(knowledge_store_space) from knowledgestore_info where created_by ='"
					+ userId + "'";

			preparedStatement = con.prepareStatement(selectAllAdminSize);

			
			preparedStatement = con.prepareStatement(selectAllAdminSize);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

			String	allSize = resultSet.getString(1);

		
			allFacultyknowStoreSpace=allSize;

				aksDomain.setKsId(resultSet.getString(1));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closecon();
		}
		return allFacultyknowStoreSpace;

	}

	public String selectAdminSpace(String userId) {

		String AdminknowStoreSpace = "";

		try {

		

			con = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String selectAllAdminSize = "select knowledge_store_space from knowledgestore_info where user_id ='"
					+ userId + "'";

			preparedStatement = con.prepareStatement(selectAllAdminSize);

			
			preparedStatement = con.prepareStatement(selectAllAdminSize);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

			String 	superAdminknow = resultSet.getString("knowledge_store_space");

			
				AdminknowStoreSpace =superAdminknow;

				aksDomain.setKsId(resultSet.getString("knowledge_store_space"));
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closecon();
		}
		return AdminknowStoreSpace;

	}

	public String getEmailId(String userId) {

	String emailid="";

		try {

			con = DBTransaction.connect();

		
			String sql = "select email from users_info where user_id ='"+ userId + "'";

			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

			 	emailid = resultSet.getString("email");

			
			

				
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closecon();	
		}
		
		return emailid;

			}

	public void closecon()
	{
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(preparedStatement!=null)
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(resultSet!=null)
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
/*	public void createFolders(String userid)
	{
		 File files = new File("/home/kes3/Anna univ/Admin/"+userid);
	        if (!files.exists()) {
	            if (files.mkdirs()) {
	                System.out.println("Multiple directories are created!");
	            } else {
	                System.out.println("Failed to create multiple directories!");
	            }
	        }
	}*/
	public String getAllKsData(String userId) {

		String allFacultyknowStoreSpace = "";

		try {

		

			con = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String selectAllAdminSize = "select sum(knowledge_store_space) from knowledgestore_info where created_by ='"
					+ userId + "'";

			preparedStatement = con.prepareStatement(selectAllAdminSize);

			
			preparedStatement = con.prepareStatement(selectAllAdminSize);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

			String	allSize = resultSet.getString(1);

		
			allFacultyknowStoreSpace=allSize;

				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closecon();
		}
		return allFacultyknowStoreSpace;

	}
	
	
	
	public int getallks(String userId) {

		int allFacultyknowStoreSpace = 0;

		try {

		

			con = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String selectAllAdminSize = "select sum(knowledge_store_space) from knowledgestore_info where created_by ='"
					+ userId + "'";

			preparedStatement = con.prepareStatement(selectAllAdminSize);

			
			preparedStatement = con.prepareStatement(selectAllAdminSize);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

			int	allSize = resultSet.getInt(1);

		
			allFacultyknowStoreSpace=allSize;

				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closecon();
		}
		return allFacultyknowStoreSpace;

	}
	

	public String getUsedKsSize(String userId) {

		String adminsize = "";

		try {

		

			con = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String selectAllAdminSize = "select knowledge_store_space from knowledgestore_info where user_id ='"
					+ userId + "'";

			preparedStatement = con.prepareStatement(selectAllAdminSize);

			
			preparedStatement = con.prepareStatement(selectAllAdminSize);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

			String 	admin = resultSet.getString("knowledge_store_space");

			
			adminsize =admin;

				/*aksDomain.setKsId(resultSet.getString("knowledge_store_space"));*/
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closecon();
		}
		return adminsize;

	}
	
	public int getadminkssize(String userId) {

		int allFacultyknowStoreSpace = 0;

		try {

		

			con = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String selectAllAdminSize = "select knowledge_store_space from knowledgestore_info where user_id ='"
					+ userId + "'";

			preparedStatement = con.prepareStatement(selectAllAdminSize);

			
			preparedStatement = con.prepareStatement(selectAllAdminSize);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

			int	allSize = resultSet.getInt(1);

		
			allFacultyknowStoreSpace=allSize;

				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closecon();
		}
		return allFacultyknowStoreSpace;

	}
	
	
}