package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.DisplayAdminDomain;

/**
 * Servlet implementation class DisplayFacultyDao
 */

public class DisplayFacultyDao  {
	
ArrayList<AdminDomain> al= new ArrayList<AdminDomain>();
	
	AdminDomain adomain= new AdminDomain();
	
	public  AdminDomain retriveData(DisplayAdminDomain dDomain) {
		//DBTransaction DBT = new DBTransaction();
           //System.out.println("this is data base page");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = DBTransaction.connect();
			//PreparedStatement pstmt = null;
			ResultSet rs = null;
			String quary = "select * from users_info where user_id='"+dDomain.getAdminId()+"'";
			//System.out.println("the quary is:"+quary);
			pstmt = con.prepareStatement(quary);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				
				String adminid=rs.getString("user_id");
				//System.out.println("userid is:"+adminid);
				adomain.setAdminId(rs.getString("user_id"));
				adomain.setAdminName(rs.getString("username"));
				adomain.setPwd(rs.getString("password"));
				adomain.setEmail(rs.getString("email"));
				adomain.setAddress(rs.getString("address"));
				
				adomain.setPhone(rs.getString("contact_no"));
				adomain.setPrivilege(rs.getString("privilege"));
				adomain.setDeptDetails(rs.getString("dept_details"));
				adomain.setCspecification(rs.getString("course_spec"));
				adomain.setStream(rs.getString("stream"));
				adomain.setBelongs(rs.getString("belongs"));
				adomain.setOrgDetails(rs.getString("org_details"));
				adomain.setOrgType(rs.getString("org_type"));
				
			}
			//System.out.println("the return value is:"+adomain);
				
				}catch(Exception e){
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
		return adomain;
	}
	
}
