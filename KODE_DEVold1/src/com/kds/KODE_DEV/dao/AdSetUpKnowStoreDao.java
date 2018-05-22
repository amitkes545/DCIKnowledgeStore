	
package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminSetUpKnowStoreDomain;

	public class AdSetUpKnowStoreDao {
		
		AdminSetUpKnowStoreDomain aksDomain = new AdminSetUpKnowStoreDomain();
		PreparedStatement pstmt = null;
		Connection con =null;
		
		public String insertAllInformationOfFaculty(AdminSetUpKnowStoreDomain aksDomain) {
			
			String status = "";

			try {
				//int size  = aksDomain.getKsSize();
				//String user1  = aksDomain.getCreatedBy();
				con = DBTransaction.connect();
				//pstmt = null;
				//PreparedStatement pstmt1 = null;
				// ResultSet rs = null;
				
				OwnerSelectUserIDKStoreDao selectIdDao = new OwnerSelectUserIDKStoreDao();
				boolean isDuplicate = selectIdDao.adminDuplicate(aksDomain);
		      
		        if(isDuplicate)
		        {
		            //System.out.println("user exist");
		        }else{
				String sql = "insert into knowledgestore_info(organization_id, user_id, created_by, knowledge_store_space, status) values(?,?,?,?,?)";
                //String updateQuery="update knowledgestore_info set knowledge_store_space=knowledge_store_space - "+size+" where user_id='"+user1+"'";
               // //System.out.println("the query is now"+updateQuery);
			    pstmt = con.prepareStatement(sql);
				//pstmt1 = con.prepareStatement(updateQuery);
				//pstmt.setString(1,aksDomain.getKsid());
				pstmt.setString(1,aksDomain.getOrgId());
				pstmt.setString(2,aksDomain.getUserId());
				pstmt.setString(3, aksDomain.getCreatedBy());
				pstmt.setInt(4,aksDomain.getKsSize());
				pstmt.setString(5,aksDomain.getStatus());
				
				//System.out.println("the query is:" +pstmt);
				int n = pstmt.executeUpdate();
				
				//System.out.println("query is inserted successfully");
				
				if (n != 0)
					status = "success";
				else
					status = "failure";
		        }	
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			   {
				   try{
				   con.close();
				   pstmt.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return status;
		}
		
		public String selectAllFacultySpace(String userId) {

			String allfacultyKnowStoreSpace = "";

			try {

				//String createdBy = aksDomain.getUserId();

				con = DBTransaction.connect();
				//pstmt = null;
				ResultSet resultSet = null;
				String selectAllFacultySize = "select sum(knowledge_store_space) from knowledgestore_info where created_by ='"
						+ userId + "'";

				pstmt = con.prepareStatement(selectAllFacultySize);

				//System.out.println("The query is:" + selectAllFacultySize);
				pstmt = con.prepareStatement(selectAllFacultySize);
				resultSet = pstmt.executeQuery();
				while(resultSet.next()) {

				String	allSize = resultSet
							.getString(1);

					//System.out.println("all Faculty know Store Space is : "	+ allSize);
					allfacultyKnowStoreSpace=allSize;

					aksDomain.setKsId(resultSet.getString(1));
				}
				// //System.out.println("The return value is:" + rDomain);

			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			   {
				   try{
				   con.close();
				   pstmt.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return allfacultyKnowStoreSpace;

		}

		public String selectAdminSpace(String userId) {

			String adminknowStoreSpace = "";
			try {

				//String sessionUser = aksDomain.getUserId();

				con = DBTransaction.connect();
				//pstmt = null;
				ResultSet resultSet = null;
				String selectAdminSize = "select knowledge_store_space from knowledgestore_info where user_id ='"
						+ userId + "'";

				pstmt = con.prepareStatement(selectAdminSize);

				//System.out.println("The query is:" + selectAdminSize);
				pstmt = con.prepareStatement(selectAdminSize);
				resultSet = pstmt.executeQuery();
				while(resultSet.next()) {

				String 	adminknow = resultSet.getString("knowledge_store_space");

					//System.out.println("Admin know Store Space is : "	+ adminknow);
					adminknowStoreSpace =adminknow;

					aksDomain.setKsId(resultSet.getString("knowledge_store_space"));
				}
				// //System.out.println("The return value is:" + rDomain);

			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			   {
				   try{
				   con.close();
				   pstmt.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return adminknowStoreSpace;

		}

	}
	