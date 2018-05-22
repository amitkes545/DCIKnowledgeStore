package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.OwnerSetUpKnowStoreDomain;

public class OwnerSetUpKnowStoreDao {

	Connection con=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	public String checkUserCredential1s(OwnerSetUpKnowStoreDomain ksDomain) {

		String status = null;

		try {

			 con = DBTransaction.connect();
			 preparedStatement=null;
			/*OwnerSelectUserIDKStoreDao selectIdDao = new OwnerSelectUserIDKStoreDao();
			boolean isDuplicate = selectIdDao.ownerDuplicate(ksDomain);
	      	 
	      	
	       /* if(isDuplicate)
	        {
	            
	        }else{*/
			String sql = "insert into knowledgestore_info(organization_id, user_id, created_by, knowledge_store_space, status) values(?,?,?,?,?)";

			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ksDomain.getOrgId());
			preparedStatement.setString(2, ksDomain.getUserId());
			preparedStatement.setString(3,"Owner");
			preparedStatement.setInt(4,Integer.parseInt(ksDomain.getKsSize()));
			preparedStatement.setString(5, ksDomain.getStatus());

			//System.out.println("the query is:" + preparedStatement);
			int n = preparedStatement.executeUpdate();
			//System.out.println("query is inserted successfully");
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public String selectAllSuperAdminSpace(String userId) {

		String allSuperAdminknowStoreSpace = "";

		try {

		

			 con = DBTransaction.connect();
			 preparedStatement = null;
			 resultSet = null;
			String selectAllAdminSize = "select sum(knowledge_store_space) from knowledgestore_info where created_by ='Owner'";

			preparedStatement = con.prepareStatement(selectAllAdminSize);

			
			preparedStatement = con.prepareStatement(selectAllAdminSize);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

			String	allSize = resultSet.getString(1);

		
			allSuperAdminknowStoreSpace=allSize;

				//aksDomain.setKsId(resultSet.getString(1));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
			if(preparedStatement!=null)
			preparedStatement.close();
			if(con!=null)
			con.close();
			if(resultSet!=null)
			resultSet.close();
			}
			catch(Exception e)
			{
				
			}
		}
		return allSuperAdminknowStoreSpace;

	}
	
	public int selectOwnerKnowSpace() {
		int OwnerKnowSpace=0;
		//int facultyknowStoreSpace = 0;//"";
		try {

			// String sessionUser = aksDomain.getUserId();

			con = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String selectFacultySize = "select total_space from knowledge_store_owner_storage_space";

			System.out.println("The query is:...." + selectFacultySize);
			preparedStatement = con.prepareStatement(selectFacultySize);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {

				OwnerKnowSpace = resultSet.getInt("total_space");

				//System.out.println("Faculty know Store Space is : " + facultyKnowSpace);
				//facultyknowStoreSpace = facultyKnowSpace;

			}
			// //System.out.println("The return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   if(con!=null)
			   con.close();
				   if(preparedStatement!=null)
			   preparedStatement.close();
				   if(resultSet!=null)
			   resultSet.close();
			  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return OwnerKnowSpace;

	}
	
	public int selectAllKnowSpace(String userid) {
		int AllKnowSpace=0;
		//int facultyknowStoreSpace = 0;//"";
		try {
			// String sessionUser = aksDomain.getUserId();
			con = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String selectAllKnowSize = "select sum(knowledge_store_space) AllKnowSpace from knowledgestore_info where created_by ='Owner'";
							System.out.println("The query is:...." + selectAllKnowSize);
			preparedStatement = con.prepareStatement(selectAllKnowSize);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				AllKnowSpace = resultSet.getInt("AllKnowSpace");
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
		return AllKnowSpace;

	}
}