package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.DisplayAdminDomain;

public class DisplayAdminDao  {
	
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
				
				adomain.setOrgid(rs.getString("organization_id"));
				adomain.setCreated_by(rs.getString("created_by"));
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
       
    


