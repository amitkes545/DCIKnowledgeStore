package com.kds.KODE_DEV.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class GetFacultyForOrgDao {
	
	FacultySetupknowStoreDao closeconn=new FacultySetupknowStoreDao();
	public List<UsersInfoDomain> getFacultyForOrganization(String orgId,String userid) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con=null;
	
		try{
		con = DBTransaction.connect();
			
			
			//String sql = "select user_id,username from users_info where organization_id=? and privilege=? and created_by=?";
		String sql = "select user_id,username from users_info where organization_id=? and privilege=? and created_by=? and user_id not in(select user_id from knowledgestore_info)";
			ps = con.prepareStatement(sql);
			ps.setString(1, orgId);
			ps.setString(2, "Faculty");
			ps.setString(3, userid);
			rs = ps.executeQuery();
			List<UsersInfoDomain> users = new  ArrayList<UsersInfoDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				UsersInfoDomain user = new UsersInfoDomain();
				System.out.println("Elements :"+rs.getString(1));
				user.setUserId(rs.getString(1));
				user.setUserName(rs.getString(2));
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closeconn.closecon();
		}
		
		return null;
	}
	
	public String getAdminEmail(String userid, String orgId) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String email = "";
		
		try{
			Connection connection = DBTransaction.connect();
			
			String sql = "select user_id, email from users_info where organization_id=? and privilege=? and user_id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, orgId);
			ps.setString(2, "Admin");
			ps.setString(3, userid);
			rs = ps.executeQuery();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				email = rs.getString(2);
			}
			return email;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closeconn.closecon();
		}
		return null;
		
	}
	
	
	public List<UsersInfoDomain> getFacultyForAdmin(String orgId, String userId) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Connection connection = DBTransaction.connect();
			String sql = "select user_id, email from users_info where organization_id=? and privilege=? and created_by=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, orgId);
			ps.setString(2, "Faculty");
			ps.setString(3, userId);
			rs = ps.executeQuery();
			List<UsersInfoDomain> users = new  ArrayList<UsersInfoDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				UsersInfoDomain user = new UsersInfoDomain();
				System.out.println("Elements :"+rs.getString(1));
				user.setUserId(rs.getString(1));
				users.add(user);
			}
			return users;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closeconn.closecon();
		}
		return null;
		
	}
	public String getUserNameDao(String userid) {
		  
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  String username = "";
		  
		  try{
		   Connection connection = DBTransaction.connect();
		   
		   String sql = "select username from users_info where  user_id='"+userid+"'";
		   ps = connection.prepareStatement(sql);
		  
		   rs = ps.executeQuery();
		   System.out.println("Query :"+sql);
		   while(rs.next()) {
		    username = rs.getString("username");
		   }
		   return username;
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  finally{
		   closeconn.closecon();
		  }
		  return username;
		  
		 }
}
